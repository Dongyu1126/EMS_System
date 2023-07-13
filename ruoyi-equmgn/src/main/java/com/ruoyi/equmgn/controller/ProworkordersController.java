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
import com.ruoyi.equmgn.domain.Proworkorders;
import com.ruoyi.equmgn.service.IProworkordersService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 生产工单Controller
 * 
 * @author jiangdongyu
 * @date 2023-07-13
 */
@RestController
@RequestMapping("/equmgn/proworkorders")
public class ProworkordersController extends BaseController
{
    @Autowired
    private IProworkordersService proworkordersService;

    /**
     * 查询生产工单列表
     */
    @PreAuthorize("@ss.hasPermi('equmgn:proworkorders:list')")
    @GetMapping("/list")
    public TableDataInfo list(Proworkorders proworkorders)
    {
        startPage();
        List<Proworkorders> list = proworkordersService.selectProworkordersList(proworkorders);
        return getDataTable(list);
    }

    /**
     * 导出生产工单列表
     */
    @PreAuthorize("@ss.hasPermi('equmgn:proworkorders:export')")
    @Log(title = "生产工单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Proworkorders proworkorders)
    {
        List<Proworkorders> list = proworkordersService.selectProworkordersList(proworkorders);
        ExcelUtil<Proworkorders> util = new ExcelUtil<Proworkorders>(Proworkorders.class);
        util.exportExcel(response, list, "生产工单数据");
    }

    /**
     * 获取生产工单详细信息
     */
    @PreAuthorize("@ss.hasPermi('equmgn:proworkorders:query')")
    @GetMapping(value = "/{proworkordersProductStatus}")
    public AjaxResult getInfo(@PathVariable("proworkordersProductStatus") String proworkordersProductStatus)
    {
        return success(proworkordersService.selectProworkordersByProworkordersProductStatus(proworkordersProductStatus));
    }

    /**
     * 新增生产工单
     */
    @PreAuthorize("@ss.hasPermi('equmgn:proworkorders:add')")
    @Log(title = "生产工单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Proworkorders proworkorders)
    {
        return toAjax(proworkordersService.insertProworkorders(proworkorders));
    }

    /**
     * 修改生产工单
     */
    @PreAuthorize("@ss.hasPermi('equmgn:proworkorders:edit')")
    @Log(title = "生产工单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Proworkorders proworkorders)
    {
        return toAjax(proworkordersService.updateProworkorders(proworkorders));
    }

    /**
     * 删除生产工单
     */
    @PreAuthorize("@ss.hasPermi('equmgn:proworkorders:remove')")
    @Log(title = "生产工单", businessType = BusinessType.DELETE)
	@DeleteMapping("/{proworkordersProductStatuss}")
    public AjaxResult remove(@PathVariable String[] proworkordersProductStatuss)
    {
        return toAjax(proworkordersService.deleteProworkordersByProworkordersProductStatuss(proworkordersProductStatuss));
    }
}
