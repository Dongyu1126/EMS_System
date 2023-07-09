package com.ruoyi.equmgn.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.equmgn.domain.Equinfo;
import com.ruoyi.equmgn.service.IEquinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 设备信息Controller
 * 
 * @author jiangdongyu
 * @date 2023-07-09
 */
@RestController
@RequestMapping("/equmgn/equinfo")
public class EquinfoController extends BaseController
{
    @Autowired
    private IEquinfoService equinfoService;

    /**
     * 查询设备信息列表
     */
    @PreAuthorize("@ss.hasPermi('equmgn:equinfo:list')")
    @GetMapping("/list")
    public TableDataInfo list(Equinfo equinfo)
    {
        startPage();
        List<Equinfo> list = equinfoService.selectEquinfoList(equinfo);
        return getDataTable(list);
    }

    /**
     * 导出设备信息列表
     */
    @PreAuthorize("@ss.hasPermi('equmgn:equinfo:export')")
    @Log(title = "设备信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Equinfo equinfo)
    {
        List<Equinfo> list = equinfoService.selectEquinfoList(equinfo);
        ExcelUtil<Equinfo> util = new ExcelUtil<Equinfo>(Equinfo.class);
        util.exportExcel(response, list, "设备信息数据");
    }

    /**
     * 获取设备信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('equmgn:equinfo:query')")
    @GetMapping(value = "/{equinfoName}")
    public AjaxResult getInfo(@PathVariable("equinfoName") String equinfoName)
    {
        return success(equinfoService.selectEquinfoByEquinfoName(equinfoName));
    }

    /**
     * 新增设备信息
     */
    @PreAuthorize("@ss.hasPermi('equmgn:equinfo:add')")
    @Log(title = "设备信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Equinfo equinfo)
    {
        return toAjax(equinfoService.insertEquinfo(equinfo));
    }

    /**
     * 修改设备信息
     */
    @PreAuthorize("@ss.hasPermi('equmgn:equinfo:edit')")
    @Log(title = "设备信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Equinfo equinfo)
    {
        return toAjax(equinfoService.updateEquinfo(equinfo));
    }

    /**
     * 删除设备信息
     */
    @PreAuthorize("@ss.hasPermi('equmgn:equinfo:remove')")
    @Log(title = "设备信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{equinfoNames}")
    public AjaxResult remove(@PathVariable String[] equinfoNames)
    {
        return toAjax(equinfoService.deleteEquinfoByEquinfoNames(equinfoNames));
    }
}
