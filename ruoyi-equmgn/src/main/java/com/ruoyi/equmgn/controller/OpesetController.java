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
import com.ruoyi.equmgn.domain.Opeset;
import com.ruoyi.equmgn.service.IOpesetService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 工序设置Controller
 * 
 * @author jiangdongyu
 * @date 2023-07-09
 */
@RestController
@RequestMapping("/equmgn/opeset")
public class OpesetController extends BaseController
{
    @Autowired
    private IOpesetService opesetService;

    /**
     * 查询工序设置列表
     */
    @PreAuthorize("@ss.hasPermi('equmgn:opeset:list')")
    @GetMapping("/list")
    public TableDataInfo list(Opeset opeset)
    {
        startPage();
        List<Opeset> list = opesetService.selectOpesetList(opeset);
        return getDataTable(list);
    }

    /**
     * 导出工序设置列表
     */
    @PreAuthorize("@ss.hasPermi('equmgn:opeset:export')")
    @Log(title = "工序设置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Opeset opeset)
    {
        List<Opeset> list = opesetService.selectOpesetList(opeset);
        ExcelUtil<Opeset> util = new ExcelUtil<Opeset>(Opeset.class);
        util.exportExcel(response, list, "工序设置数据");
    }

    /**
     * 获取工序设置详细信息
     */
    @PreAuthorize("@ss.hasPermi('equmgn:opeset:query')")
    @GetMapping(value = "/{opesetName}")
    public AjaxResult getInfo(@PathVariable("opesetName") String opesetName)
    {
        return success(opesetService.selectOpesetByOpesetName(opesetName));
    }

    /**
     * 新增工序设置
     */
    @PreAuthorize("@ss.hasPermi('equmgn:opeset:add')")
    @Log(title = "工序设置", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Opeset opeset)
    {
        return toAjax(opesetService.insertOpeset(opeset));
    }

    /**
     * 修改工序设置
     */
    @PreAuthorize("@ss.hasPermi('equmgn:opeset:edit')")
    @Log(title = "工序设置", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Opeset opeset)
    {
        return toAjax(opesetService.updateOpeset(opeset));
    }

    /**
     * 删除工序设置
     */
    @PreAuthorize("@ss.hasPermi('equmgn:opeset:remove')")
    @Log(title = "工序设置", businessType = BusinessType.DELETE)
	@DeleteMapping("/{opesetNames}")
    public AjaxResult remove(@PathVariable String[] opesetNames)
    {
        return toAjax(opesetService.deleteOpesetByOpesetNames(opesetNames));
    }
}
