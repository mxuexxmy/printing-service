package xyz.mxue.printing.service.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import xyz.mxue.printing.service.common.model.PageInfo;
import xyz.mxue.printing.service.common.model.Result;
import xyz.mxue.printing.service.entity.TbAccountBook;
import xyz.mxue.printing.service.entity.dto.AccountUpdateDTO;
import xyz.mxue.printing.service.entity.dto.CategoriesNameDTO;
import xyz.mxue.printing.service.entity.vo.AccountVO;
import xyz.mxue.printing.service.service.TbAccountBookService;

import javax.annotation.Resource;
import java.util.*;


/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author mxuexxmy
 * @since 2021-03-14
 */
@Api(tags = "记账管理")
@RestController
@RequestMapping("/printing/tb-account-book")
public class TbAccountBookController {

    @Resource
    private TbAccountBookService accountBookService;

    @ApiOperation(value = "记账分类")
    @GetMapping("/")
    public Result<List<CategoriesNameDTO>> index() {
        return Result.success(accountBookService.categoriesNames());
    }

    @ApiOperation(value = "添加分类")
    @GetMapping("/add-account")
    public Result<List<CategoriesNameDTO>> addAccount() {
        return Result.success(accountBookService.categoriesNames());
    }

    @ApiOperation(value = "保持账单")
    @PostMapping("/save-account")
    public Result<Boolean> saveAccount(@RequestBody TbAccountBook tbAccountBook) {
        tbAccountBook.setCreateTime(new Date());
        tbAccountBook.setUpdateTime(new Date());
        boolean save = accountBookService.save(tbAccountBook);
        return save ? Result.success("添加账单成功!", true) : Result.fail("添加账单失败，请重试！", false);
    }

    @ApiOperation(value = "查询需要更新的账单")
    @GetMapping("/update/{id}")
    public Result<AccountUpdateDTO> update(@PathVariable Long id) {
        return Result.success(accountBookService.accountUpdate(id));
    }

    @ApiOperation(value = "更新账单")
    @PostMapping("/save-update")
    public Result<Boolean> saveUpdate(@RequestBody TbAccountBook tbAccountBook) {
        tbAccountBook.setUpdateTime(new Date());
        boolean b = accountBookService.saveOrUpdate(tbAccountBook);
        return b ? Result.success("修改账单消息成功！", true) : Result.fail("修改账单消息失败！", false);
    }

    @ApiOperation(value = "删除账单")
    @GetMapping("/delete/{id}")
    public Result<Boolean> deleteOrder(@PathVariable Long id) {
        boolean b = accountBookService.removeById(id);
        if (b) {
            return Result.success("序号" + id + "的账单删除成功!", true);
        }
        return Result.fail("序号" + id + "的账单删除失败!", false);
    }

    @GetMapping("/page")
    public PageInfo<AccountVO> page(@RequestParam(value = "draw", required = false, defaultValue = "0") Integer draw,
                                    @RequestParam(value = "start", required = false, defaultValue = "0") Integer start,
                                    @RequestParam(value = "length", required = false, defaultValue = "10") Integer length,
                                    TbAccountBook tbAccountBook) {
        // 对输入的值进行处理
        Integer checkSpendType = 2;
        Integer checkCategoriesId = -1;
        if (Objects.nonNull(tbAccountBook)) {
            // 处理  spendType
            if (tbAccountBook.getSpendType() != null) {
                if (checkSpendType.equals(tbAccountBook.getSpendType())) {
                    tbAccountBook.setSpendType(null);
                }
            }

            // 处理类别
            if (tbAccountBook.getCategoriesId() != null) {
                if (checkCategoriesId.equals(tbAccountBook.getCategoriesId())) {
                    tbAccountBook.setCategoriesId(null);
                }
            }
        }

        // 封装 Datatables 需要的结果
        return accountBookService.page(start, length, draw, tbAccountBook);
    }

}

