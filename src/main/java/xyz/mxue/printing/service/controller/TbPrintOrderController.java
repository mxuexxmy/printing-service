package xyz.mxue.printing.service.controller;


import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import xyz.mxue.printing.service.common.enums.OrderStatusEnum;
import xyz.mxue.printing.service.common.model.PageInfo;
import xyz.mxue.printing.service.common.model.Result;
import xyz.mxue.printing.service.entity.TbPrintOrder;
import xyz.mxue.printing.service.entity.TbPrintfInfo;
import xyz.mxue.printing.service.entity.vo.PrintOrderDetailVO;
import xyz.mxue.printing.service.entity.dto.PrintfInfoDTO;
import xyz.mxue.printing.service.entity.dto.PrintfOrderInfoDTO;
import xyz.mxue.printing.service.entity.vo.PrintfNumberInfoVO;
import xyz.mxue.printing.service.service.TbPrintOrderService;
import xyz.mxue.printing.service.service.TbPrintfInfoService;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

/**
 * <p>
 * 打印订单 前端控制器
 * </p>
 *
 * @author mxuexxmy
 * @since 2020-12-06
 */
@Api(tags = "打印订单管理")
@RestController
@RequestMapping("/printing/tb-print-order")
public class TbPrintOrderController {

    // 双面
    private static final Integer DOUBLE = 2;

    @Resource
    private TbPrintOrderService orderService;

    @Resource
    private TbPrintfInfoService printfInfoService;

    @ApiOperation(value = "添加打印计算")
    @PostMapping(value = "/calculate")
    @SaCheckLogin
    public Result<Boolean> calculate(@RequestBody PrintfOrderInfoDTO printfOrderInfoDTO) {
        if (StrUtil.isBlank(printfOrderInfoDTO.getUserName())) {
            return Result.fail("请输入姓名！");
        }
        // 分离订单
        TbPrintOrder tbPrintOrder = getPrintfOrderInfo(printfOrderInfoDTO);
        // 订单详细信息
        List<TbPrintfInfo> printfInfoList = getPrintfInfoList(printfOrderInfoDTO.getPrintfInfos());

        // 检查订单详细信息是否有为空
        String printfInfoTips = checkPrintfInfo(printfInfoList);

        if (StrUtil.isNotBlank(printfInfoTips)) {
            return Result.fail(printfInfoTips, false);
        }
        // 计算价格
        tbPrintOrder.setTotalAmount(calculationTotalAmount(printfInfoList));

        boolean save = orderService.saveOrderInfoAndPrintfInfo(tbPrintOrder, printfInfoList);

        return save ? Result.success("添加打印录入成功， 总价格为：" + tbPrintOrder.getTotalAmount() + "元!", true)
                : Result.fail("添加打印录入失败，请重新录入！", false);
    }

    // 计算总价格
    private BigDecimal calculationTotalAmount(List<TbPrintfInfo> printfInfoList) {
        BigDecimal totalAmount = BigDecimal.valueOf(0D);
        for (TbPrintfInfo info : printfInfoList) {
            // 打印双面，判断页数是都是奇数，奇数加 1， 变成 偶数
            if (info.getSingleDoubleSided().equals(DOUBLE)) {
                if (info.getPagesNumber() % 2 == 1) {
                    info.setPagesNumber(info.getPagesNumber() + 1);
                }
            }
            BigDecimal papers = BigDecimal.valueOf(Integer.toUnsignedLong(info.getPagesNumber() / info.getSingleDoubleSided()));
            BigDecimal totalPapers = papers.multiply(BigDecimal.valueOf(Integer.toUnsignedLong(info.getPrintfNumber())));
            BigDecimal printfMoney = totalPapers.multiply(info.getAmount());
            info.setPrintfMoney(printfMoney);
            totalAmount = totalAmount.add(printfMoney);
        }
        return totalAmount;
    }

    private String checkPrintfInfo(List<TbPrintfInfo> printfInfoList) {
        String tips = "";
        for (TbPrintfInfo printfInfo : printfInfoList) {
            if (Objects.isNull(printfInfo.getPagesNumber())) {
                tips = "打印页数不能为空！";
            }
            if (Objects.isNull(printfInfo.getPrintfNumber())) {
                tips = "打印份数不能为空！";
            }
            if (Objects.isNull(printfInfo.getAmount())) {
                tips = "价格不能为空！";
            }
        }
        return tips;
    }

    // 订单详细信息
    private List<TbPrintfInfo> getPrintfInfoList(List<PrintfInfoDTO> printfInfoDTOs) {
        List<TbPrintfInfo> printfInfoList = new ArrayList<>();

        for (PrintfInfoDTO printfInfoDTO : printfInfoDTOs) {
            TbPrintfInfo printfInfo = new TbPrintfInfo();
            printfInfo.setSingleDoubleSided(printfInfoDTO.getSingleDoubleSided());
            printfInfo.setPagesNumber(printfInfoDTO.getPagesNumber());
            printfInfo.setPrintfNumber(printfInfoDTO.getPrintfNumber());
            printfInfo.setAmount(printfInfoDTO.getAmount());
            if (StrUtil.isNotBlank(printfInfo.getFileName())) {
                printfInfo.setFileName(printfInfoDTO.getFileName());
            }
            printfInfoList.add(printfInfo);
        }

        return printfInfoList;
    }

    // 分离订单
    private TbPrintOrder getPrintfOrderInfo(PrintfOrderInfoDTO printfOrderInfoDTO) {
        TbPrintOrder tbPrintOrder = new TbPrintOrder();
        tbPrintOrder.setUserName(printfOrderInfoDTO.getUserName());
        if (StrUtil.isNotBlank(tbPrintOrder.getUserPhone())) {
            tbPrintOrder.setUserPhone(printfOrderInfoDTO.getUserPhone());
        }
        if (StrUtil.isNotBlank(tbPrintOrder.getUserQq())) {
            tbPrintOrder.setUserQq(printfOrderInfoDTO.getUserQq());
        }
        if (StrUtil.isNotBlank(tbPrintOrder.getUserWxchat())) {
            tbPrintOrder.setUserWxchat(printfOrderInfoDTO.getUserWxchat());
        }
        if (StrUtil.isNotBlank(printfOrderInfoDTO.getAddress())) {
            tbPrintOrder.setAddress(printfOrderInfoDTO.getAddress());
        }
        if (StrUtil.isNotBlank(printfOrderInfoDTO.getNote())) {
            tbPrintOrder.setNote(printfOrderInfoDTO.getNote());
        }
        tbPrintOrder.setOrderStatus(OrderStatusEnum.UNDONE.getDesc());
        tbPrintOrder.setCreateTime(new Date());
        tbPrintOrder.setUpdateTime(new Date());
        return tbPrintOrder;
    }

    @ApiOperation(value = "添加打印")
    @PostMapping(value = "/add")
    @SaCheckLogin
    public Result<Boolean> addPrintingOrder(@RequestBody PrintfOrderInfoDTO printfOrderInfoDTO) {
        if (StrUtil.isBlank(printfOrderInfoDTO.getUserName())) {
            return Result.fail("请输入姓名！");
        }

        // 分离订单
        TbPrintOrder tbPrintOrder = getPrintfOrderInfo(printfOrderInfoDTO);

        // 订单详细信息
        List<TbPrintfInfo> printfInfoList = getPrintfInfoList(printfOrderInfoDTO.getPrintfInfos());

        // 检查订单详细信息是否有为空
        String printfInfoTips = checkPrintfInfo(printfInfoList);

        if (!printfInfoTips.isEmpty()) {
            return Result.fail(printfInfoTips);
        }
        // 计算价格
        tbPrintOrder.setTotalAmount(calculationTotalAmount(printfInfoList));

        boolean save = orderService.saveOrderInfoAndPrintfInfo(tbPrintOrder, printfInfoList);

        return save ? Result.success("添加打印详细录入成功， 总价格为：" + tbPrintOrder.getTotalAmount() + "元!")
                : Result.fail("添加打印详细录入失败，请重新录入！");

    }

    @ApiOperation(value = "打印订单详情")
    @GetMapping("/show/{id}")
    @SaCheckLogin
    public Result<PrintOrderDetailVO> orderDetail(@PathVariable Long id) {
        Map<String, Object> map = new HashMap<>();
        // 订单
        TbPrintOrder order = orderService.getById(id);
        // 份数详情
        List<TbPrintfInfo> printfInfoList = printfInfoService.queryPrintfInfos(order.getId());
        PrintOrderDetailVO printOrderDetailVO = new PrintOrderDetailVO();
        printOrderDetailVO.setOrder(order);
        printOrderDetailVO.setPrintfInfos(convertSingleAndDoubleSidedName(printfInfoList));
        return Result.success(printOrderDetailVO);
    }

    // 转换单双面名称
    private List<PrintfNumberInfoVO> convertSingleAndDoubleSidedName(List<TbPrintfInfo> printfInfoList) {
        List<PrintfNumberInfoVO> printfNumberInfoVOS = new ArrayList<>();
        // 1是单面。2是双面
        String[] str = {"单面", "双面"};
        for (TbPrintfInfo info : printfInfoList) {
            PrintfNumberInfoVO infoVO = new PrintfNumberInfoVO();
            infoVO.setAmount(info.getAmount());
            infoVO.setPrintfMoney(info.getPrintfMoney());
            infoVO.setPagesNumber(info.getPagesNumber());
            infoVO.setPrintfNumber(info.getPrintfNumber());
            infoVO.setSingleDoubleSided(str[info.getSingleDoubleSided() - 1]);
            infoVO.setFileName(info.getFileName());
            printfNumberInfoVOS.add(infoVO);
        }
        return printfNumberInfoVOS;
    }

    @ApiOperation(value = "确认订单")
    @GetMapping("/confirm/{id}")
    @SaCheckLogin
    public Result<Boolean> confirmOrder(@PathVariable Long id) {

        TbPrintOrder tbPrintOrder = orderService.getById(id);
        if (tbPrintOrder.getOrderStatus().equals(OrderStatusEnum.COMPLETE.getDesc())) {
            return Result.fail(tbPrintOrder.getUserName() + "的订单已确认，无需再更改!");
        }

        tbPrintOrder.setOrderStatus(OrderStatusEnum.COMPLETE.getDesc());
        tbPrintOrder.setUpdateTime(new Date());
        boolean b = orderService.saveOrUpdate(tbPrintOrder);
        if (b) {
            if (tbPrintOrder.getUserName().isEmpty()) {
                return Result.success("序号" + tbPrintOrder.getId() + "用户的订单已完成");
            }
            return Result.success(tbPrintOrder.getUserName() + "的订单已完成");
        }
        return Result.fail("订单确认失败，请稍后再试！");
    }

    @ApiOperation(value = "删除订单")
    @GetMapping("/delete/{id}")
    @SaCheckLogin
    public Result<Boolean> deleteOrder(@PathVariable Long id, ModelMap map) {
        // 先删除打印详情
        QueryWrapper<TbPrintfInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("order_id", id);
        boolean remove = printfInfoService.remove(queryWrapper);

        if (remove) {
            boolean b = orderService.removeById(id);
            if (b) {
                return Result.success("序号" + id + "的打印记录删除成功!");
            }
        }

        return Result.fail("序号" + id + "的打印记录删除失败!");
    }

    @ApiOperation(value = "分页查询")
    @GetMapping("/page")
    @SaCheckLogin
    public PageInfo<TbPrintOrder> page(@RequestParam(value = "draw", required = false, defaultValue = "0") Integer draw,
                                       @RequestParam(value = "start", required = false, defaultValue = "0")Integer start,
                                       @RequestParam(value = "length", required = false, defaultValue = "10") Integer length,
                                       TbPrintOrder tbPrintOrder) {

        // 对输入的值进行处理 orderStatus
        if (Objects.nonNull(tbPrintOrder)) {
            if (tbPrintOrder.getUserName() != null) {
                if (tbPrintOrder.getOrderStatus().equals("请选择"))
                    tbPrintOrder.setOrderStatus(null);
            }
        }

        // 封装 Datatables 需要的结果

        return orderService.page(start, length, draw, tbPrintOrder);
    }


}

