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
import com.ruoyi.equmgn.domain.Wsinfo;
import com.ruoyi.equmgn.service.IWsinfoService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 车间信息Controller
 * 
 * @author jiangdongyu
 * @date 2023-07-13
 */
@RestController
@RequestMapping("/equmgn/wsinfo")
public class WsinfoController extends BaseController
{
    @Autowired
    private IWsinfoService wsinfoService;

    /**
     * 查询车间信息列表
     */
    @PreAuthorize("@ss.hasPermi('equmgn:wsinfo:list')")
    @GetMapping("/list")
    public TableDataInfo list(Wsinfo wsinfo)
    {
        startPage();
        List<Wsinfo> list = wsinfoService.selectWsinfoList(wsinfo);
        return getDataTable(list);
    }

    /**
     * 导出车间信息列表
     */
    @PreAuthorize("@ss.hasPermi('equmgn:wsinfo:export')")
    @Log(title = "车间信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Wsinfo wsinfo)
    {
        List<Wsinfo> list = wsinfoService.selectWsinfoList(wsinfo);
        ExcelUtil<Wsinfo> util = new ExcelUtil<Wsinfo>(Wsinfo.class);
        util.exportExcel(response, list, "车间信息数据");
    }

    /**
     * 获取车间信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('equmgn:wsinfo:query')")
    @GetMapping(value = "/{wsinfoName}")
    public AjaxResult getInfo(@PathVariable("wsinfoName") String wsinfoName)
    {
        return success(wsinfoService.selectWsinfoByWsinfoName(wsinfoName));
    }

    /**
     * 新增车间信息
     */
    @PreAuthorize("@ss.hasPermi('equmgn:wsinfo:add')")
    @Log(title = "车间信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Wsinfo wsinfo)
    {
        return toAjax(wsinfoService.insertWsinfo(wsinfo));
    }

    /**
     * 修改车间信息
     */
    @PreAuthorize("@ss.hasPermi('equmgn:wsinfo:edit')")
    @Log(title = "车间信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Wsinfo wsinfo)
    {
        return toAjax(wsinfoService.updateWsinfo(wsinfo));
    }

    /**
     * 删除车间信息
     */
    @PreAuthorize("@ss.hasPermi('equmgn:wsinfo:remove')")
    @Log(title = "车间信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{wsinfoNames}")
    public AjaxResult remove(@PathVariable String[] wsinfoNames)
    {
        return toAjax(wsinfoService.deleteWsinfoByWsinfoNames(wsinfoNames));
    }
}
