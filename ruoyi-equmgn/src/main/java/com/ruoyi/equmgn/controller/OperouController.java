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
import com.ruoyi.equmgn.domain.Operou;
import com.ruoyi.equmgn.service.IOperouService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 工序路线Controller
 * 
 * @author jiangdongyu
 * @date 2023-07-09
 */
@RestController
@RequestMapping("/equmgn/operou")
public class OperouController extends BaseController
{
    @Autowired
    private IOperouService operouService;

    /**
     * 查询工序路线列表
     */
    @PreAuthorize("@ss.hasPermi('equmgn:operou:list')")
    @GetMapping("/list")
    public TableDataInfo list(Operou operou)
    {
        startPage();
        List<Operou> list = operouService.selectOperouList(operou);
        return getDataTable(list);
    }

    /**
     * 导出工序路线列表
     */
    @PreAuthorize("@ss.hasPermi('equmgn:operou:export')")
    @Log(title = "工序路线", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Operou operou)
    {
        List<Operou> list = operouService.selectOperouList(operou);
        ExcelUtil<Operou> util = new ExcelUtil<Operou>(Operou.class);
        util.exportExcel(response, list, "工序路线数据");
    }

    /**
     * 获取工序路线详细信息
     */
    @PreAuthorize("@ss.hasPermi('equmgn:operou:query')")
    @GetMapping(value = "/{operouName}")
    public AjaxResult getInfo(@PathVariable("operouName") String operouName)
    {
        return success(operouService.selectOperouByOperouName(operouName));
    }

    /**
     * 新增工序路线
     */
    @PreAuthorize("@ss.hasPermi('equmgn:operou:add')")
    @Log(title = "工序路线", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Operou operou)
    {
        return toAjax(operouService.insertOperou(operou));
    }

    /**
     * 修改工序路线
     */
    @PreAuthorize("@ss.hasPermi('equmgn:operou:edit')")
    @Log(title = "工序路线", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Operou operou)
    {
        return toAjax(operouService.updateOperou(operou));
    }

    /**
     * 删除工序路线
     */
    @PreAuthorize("@ss.hasPermi('equmgn:operou:remove')")
    @Log(title = "工序路线", businessType = BusinessType.DELETE)
	@DeleteMapping("/{operouNames}")
    public AjaxResult remove(@PathVariable String[] operouNames)
    {
        return toAjax(operouService.deleteOperouByOperouNames(operouNames));
    }
}
