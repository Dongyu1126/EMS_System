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
import com.ruoyi.equmgn.domain.Iteminfo;
import com.ruoyi.equmgn.service.IIteminfoService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 物料信息Controller
 * 
 * @author jiangdongyu
 * @date 2023-07-09
 */
@RestController
@RequestMapping("/equmgn/iteminfo")
public class IteminfoController extends BaseController
{
    @Autowired
    private IIteminfoService iteminfoService;

    /**
     * 查询物料信息列表
     */
    @PreAuthorize("@ss.hasPermi('equmgn:iteminfo:list')")
    @GetMapping("/list")
    public TableDataInfo list(Iteminfo iteminfo)
    {
        startPage();
        List<Iteminfo> list = iteminfoService.selectIteminfoList(iteminfo);
        return getDataTable(list);
    }

    /**
     * 导出物料信息列表
     */
    @PreAuthorize("@ss.hasPermi('equmgn:iteminfo:export')")
    @Log(title = "物料信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Iteminfo iteminfo)
    {
        List<Iteminfo> list = iteminfoService.selectIteminfoList(iteminfo);
        ExcelUtil<Iteminfo> util = new ExcelUtil<Iteminfo>(Iteminfo.class);
        util.exportExcel(response, list, "物料信息数据");
    }

    /**
     * 获取物料信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('equmgn:iteminfo:query')")
    @GetMapping(value = "/{iteminfoNumber}")
    public AjaxResult getInfo(@PathVariable("iteminfoNumber") Long iteminfoNumber)
    {
        return success(iteminfoService.selectIteminfoByIteminfoNumber(iteminfoNumber));
    }

    /**
     * 新增物料信息
     */
    @PreAuthorize("@ss.hasPermi('equmgn:iteminfo:add')")
    @Log(title = "物料信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Iteminfo iteminfo)
    {
        return toAjax(iteminfoService.insertIteminfo(iteminfo));
    }

    /**
     * 修改物料信息
     */
    @PreAuthorize("@ss.hasPermi('equmgn:iteminfo:edit')")
    @Log(title = "物料信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Iteminfo iteminfo)
    {
        return toAjax(iteminfoService.updateIteminfo(iteminfo));
    }

    /**
     * 删除物料信息
     */
    @PreAuthorize("@ss.hasPermi('equmgn:iteminfo:remove')")
    @Log(title = "物料信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{iteminfoNumbers}")
    public AjaxResult remove(@PathVariable Long[] iteminfoNumbers)
    {
        return toAjax(iteminfoService.deleteIteminfoByIteminfoNumbers(iteminfoNumbers));
    }
}
