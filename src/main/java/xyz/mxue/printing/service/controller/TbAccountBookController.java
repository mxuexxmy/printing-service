package xyz.mxue.printing.service.controller;

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
@RestController
@RequestMapping("/printing/tb-account-book")
public class TbAccountBookController {

    @Resource
    private TbAccountBookService accountBookService;

    @GetMapping("/")
    public Map<String, List<CategoriesNameDTO>> index() {
        Map<String, List<CategoriesNameDTO>> map = new HashMap<>();
        map.put("categories", accountBookService.categoriesNames());
        return map;
    }

    @GetMapping("/add-account")
    public Map<String, List<CategoriesNameDTO>> addAccount() {
        Map<String, List<CategoriesNameDTO>> map = new HashMap<>();
        map.put("categories", accountBookService.categoriesNames());
        return map;
    }

    @PostMapping("/save-account")
    public Result saveAccount(@RequestBody TbAccountBook tbAccountBook) {
        tbAccountBook.setCreateTime(new Date());
        tbAccountBook.setUpdateTime(new Date());
        boolean save = accountBookService.save(tbAccountBook);
        return save ? Result.success("添加账单成功!") : Result.fail("添加账单失败，请重试！");
    }

    @GetMapping("/update/{id}")
    public Map<String, AccountUpdateDTO> update(@PathVariable Long id) {
        Map<String, AccountUpdateDTO> map = new HashMap<>();
        map.put("accountUpdateDTO", accountBookService.accountUpdate(id));
        return map;
    }

    @PostMapping("/save-update")
    public Result saveUpdate(@RequestBody TbAccountBook tbAccountBook) {
        tbAccountBook.setUpdateTime(new Date());
        boolean b = accountBookService.saveOrUpdate(tbAccountBook);
        return b ? Result.success("修改账单消息成功！") : Result.fail("修改账单消息失败！");
    }

    @GetMapping("/delete/{id}")
    public Result deleteOrder(@PathVariable Long id, ModelMap map) {
        boolean b = accountBookService.removeById(id);
        if (b) {
            return Result.success("序号" + id + "的账单删除成功!");
        }
        return Result.fail("序号" + id + "的账单删除失败!");
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

