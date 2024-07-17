package xyz.mxue.printing.service.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import xyz.mxue.printing.service.common.model.PageInfo;
import xyz.mxue.printing.service.common.model.Result;
import xyz.mxue.printing.service.entity.TbAccountBook;
import xyz.mxue.printing.service.entity.TbCategories;
import xyz.mxue.printing.service.service.TbAccountBookService;
import xyz.mxue.printing.service.service.TbCategoriesService;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


/**
 * <p>
 * 账单分类类型 前端控制器
 * </p>
 *
 * @author mxuexxmy
 * @since 2021-03-14
 */
@RestController
@RequestMapping("/printing/tb-categories")
public class TbCategoriesController {

    @Resource
    private TbCategoriesService categoriesService;

    @Resource
    private TbAccountBookService accountBookService;

    @PostMapping("save")
    public Result saveCategories(@RequestBody TbCategories tbCategories) {

        if (tbCategories.getName().isEmpty()) {
            return Result.fail("类别不能为空！");
        }

        // 查询是否存在相同的类别
        QueryWrapper<TbCategories> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", tbCategories.getName());
        TbCategories tbCategories1 = categoriesService.getOne(queryWrapper);
        // 如果不为空，返回
        if (Objects.nonNull(tbCategories1)) {
            return Result.fail("存在相同的类别，请检查！");
        }

        tbCategories.setCreateTime(new Date());
        tbCategories.setUpdateTime(new Date());
        boolean save = categoriesService.save(tbCategories);
        return save ? Result.success("添加类别成功！") : Result.fail("添加类别失败！");
    }

    @GetMapping("update/{id}")
    public Map<String, TbCategories> update(@PathVariable Long id) {
        Map<String, TbCategories> map = new HashMap<>();
        map.put("categories", categoriesService.getById(id));
        return map;
    }

    @PostMapping("saveUpdate")
    public Result saveUpdate(@RequestBody TbCategories tbCategories) {
        tbCategories.setUpdateTime(new Date());
        boolean b = categoriesService.saveOrUpdate(tbCategories);
        return b ? Result.success("修改类别成功！") : Result.fail("修改类别失败，请重修修改！");
    }

    @GetMapping("delete/{id}")
    public Result deleteOrder(@PathVariable Long id, ModelMap map) {
        QueryWrapper<TbAccountBook> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("categories_id", id);
        long count = accountBookService.count(queryWrapper);
        if (count > 0) {
            return Result.fail("序号为" + id + "的类别不能删除," + "如需删除，请删除所有相关的账单记录！");
        }
        boolean b = categoriesService.removeById(id);
        if (b) {
            return Result.success("序号" + id + "的类别删除成功!");
        }
        return Result.fail("序号" + id + "的类别删除失败!");
    }

    @GetMapping("page")
    public PageInfo<TbCategories> page(@RequestParam(value = "draw", required = false, defaultValue = "0") Integer draw,
                                       @RequestParam(value = "start", required = false, defaultValue = "0")Integer start,
                                       @RequestParam(value = "length", required = false, defaultValue = "10") Integer length,
                                       TbCategories tbCategories) {

        // 封装 Datatables 需要的结果
        return categoriesService.page(start, length, draw, tbCategories);
    }

}

