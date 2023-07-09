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
import com.ruoyi.equmgn.domain.Locinfo;
import com.ruoyi.equmgn.service.ILocinfoService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 工位信息Controller
 * 
 * @author jiangdongyu
 * @date 2023-07-09
 */
@RestController
@RequestMapping("/equmgn/locinfo")
public class LocinfoController extends BaseController
{
    @Autowired
    private ILocinfoService locinfoService;

    /**
     * 查询工位信息列表
     */
    @PreAuthorize("@ss.hasPermi('equmgn:locinfo:list')")
    @GetMapping("/list")
    public TableDataInfo list(Locinfo locinfo)
    {
        startPage();
        List<Locinfo> list = locinfoService.selectLocinfoList(locinfo);
        return getDataTable(list);
    }

    /**
     * 导出工位信息列表
     */
    @PreAuthorize("@ss.hasPermi('equmgn:locinfo:export')")
    @Log(title = "工位信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Locinfo locinfo)
    {
        List<Locinfo> list = locinfoService.selectLocinfoList(locinfo);
        ExcelUtil<Locinfo> util = new ExcelUtil<Locinfo>(Locinfo.class);
        util.exportExcel(response, list, "工位信息数据");
    }

    /**
     * 获取工位信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('equmgn:locinfo:query')")
    @GetMapping(value = "/{locinfoAffiliatedWorkshop}")
    public AjaxResult getInfo(@PathVariable("locinfoAffiliatedWorkshop") String locinfoAffiliatedWorkshop)
    {
        return success(locinfoService.selectLocinfoByLocinfoAffiliatedWorkshop(locinfoAffiliatedWorkshop));
    }

    /**
     * 新增工位信息
     */
    @PreAuthorize("@ss.hasPermi('equmgn:locinfo:add')")
    @Log(title = "工位信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Locinfo locinfo)
    {
        return toAjax(locinfoService.insertLocinfo(locinfo));
    }

    /**
     * 修改工位信息
     */
    @PreAuthorize("@ss.hasPermi('equmgn:locinfo:edit')")
    @Log(title = "工位信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Locinfo locinfo)
    {
        return toAjax(locinfoService.updateLocinfo(locinfo));
    }

    /**
     * 删除工位信息
     */
    @PreAuthorize("@ss.hasPermi('equmgn:locinfo:remove')")
    @Log(title = "工位信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{locinfoAffiliatedWorkshops}")
    public AjaxResult remove(@PathVariable String[] locinfoAffiliatedWorkshops)
    {
        return toAjax(locinfoService.deleteLocinfoByLocinfoAffiliatedWorkshops(locinfoAffiliatedWorkshops));
    }
}
