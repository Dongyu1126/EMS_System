package com.ruoyi.equmgn.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.equmgn.domain.Itemcaf;
import com.ruoyi.equmgn.service.IItemcafService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 物料分类Controller
 * 
 * @author jiangdongyu
 * @date 2023-07-09
 */
@RestController
@RequestMapping("/equmgn/itemcaf")
public class ItemcafController extends BaseController
{
    @Autowired
    private IItemcafService itemcafService;

    /**
     * 查询物料分类列表
     */
    @PreAuthorize("@ss.hasPermi('equmgn:itemcaf:list')")
    @GetMapping("/list")
    public TableDataInfo list(Itemcaf itemcaf)
    {
        startPage();
        List<Itemcaf> list = itemcafService.selectItemcafList(itemcaf);
        return getDataTable(list);
    }

    /**
     * 导出物料分类列表
     */
    @PreAuthorize("@ss.hasPermi('equmgn:itemcaf:export')")
    @Log(title = "物料分类", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Itemcaf itemcaf)
    {
        List<Itemcaf> list = itemcafService.selectItemcafList(itemcaf);
        ExcelUtil<Itemcaf> util = new ExcelUtil<Itemcaf>(Itemcaf.class);
        util.exportExcel(response, list, "物料分类数据");
    }

    /**
     * 获取物料分类详细信息
     */
    @PreAuthorize("@ss.hasPermi('equmgn:itemcaf:query')")
    @GetMapping(value = "/{itemcafName}")
    public AjaxResult getInfo(@PathVariable("itemcafName") String itemcafName)
    {
        return success(itemcafService.selectItemcafByItemcafName(itemcafName));
    }

    /**
     * 新增物料分类
     */
    @PreAuthorize("@ss.hasPermi('equmgn:itemcaf:add')")
    @Log(title = "物料分类", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Itemcaf itemcaf)
    {
        return toAjax(itemcafService.insertItemcaf(itemcaf));
    }

    /**
     * 修改物料分类
     */
    @PreAuthorize("@ss.hasPermi('equmgn:itemcaf:edit')")
    @Log(title = "物料分类", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Itemcaf itemcaf)
    {
        return toAjax(itemcafService.updateItemcaf(itemcaf));
    }

    /**
     * 删除物料分类
     */
    @PreAuthorize("@ss.hasPermi('equmgn:itemcaf:remove')")
    @Log(title = "物料分类", businessType = BusinessType.DELETE)
	@DeleteMapping("/{itemcafNames}")
    public AjaxResult remove(@PathVariable String[] itemcafNames)
    {
        return toAjax(itemcafService.deleteItemcafByItemcafNames(itemcafNames));
    }
}
