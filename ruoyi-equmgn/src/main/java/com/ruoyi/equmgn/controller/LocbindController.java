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
import com.ruoyi.equmgn.domain.Locbind;
import com.ruoyi.equmgn.service.ILocbindService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 工位绑定Controller
 * 
 * @author jiangdongyu
 * @date 2023-07-09
 */
@RestController
@RequestMapping("/equmgn/locbind")
public class LocbindController extends BaseController
{
    @Autowired
    private ILocbindService locbindService;

    /**
     * 查询工位绑定列表
     */
    @PreAuthorize("@ss.hasPermi('equmgn:locbind:list')")
    @GetMapping("/list")
    public TableDataInfo list(Locbind locbind)
    {
        startPage();
        List<Locbind> list = locbindService.selectLocbindList(locbind);
        return getDataTable(list);
    }

    /**
     * 导出工位绑定列表
     */
    @PreAuthorize("@ss.hasPermi('equmgn:locbind:export')")
    @Log(title = "工位绑定", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Locbind locbind)
    {
        List<Locbind> list = locbindService.selectLocbindList(locbind);
        ExcelUtil<Locbind> util = new ExcelUtil<Locbind>(Locbind.class);
        util.exportExcel(response, list, "工位绑定数据");
    }

    /**
     * 获取工位绑定详细信息
     */
    @PreAuthorize("@ss.hasPermi('equmgn:locbind:query')")
    @GetMapping(value = "/{locbindWorkshop}")
    public AjaxResult getInfo(@PathVariable("locbindWorkshop") String locbindWorkshop)
    {
        return success(locbindService.selectLocbindByLocbindWorkshop(locbindWorkshop));
    }

    /**
     * 新增工位绑定
     */
    @PreAuthorize("@ss.hasPermi('equmgn:locbind:add')")
    @Log(title = "工位绑定", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Locbind locbind)
    {
        return toAjax(locbindService.insertLocbind(locbind));
    }

    /**
     * 修改工位绑定
     */
    @PreAuthorize("@ss.hasPermi('equmgn:locbind:edit')")
    @Log(title = "工位绑定", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Locbind locbind)
    {
        return toAjax(locbindService.updateLocbind(locbind));
    }

    /**
     * 删除工位绑定
     */
    @PreAuthorize("@ss.hasPermi('equmgn:locbind:remove')")
    @Log(title = "工位绑定", businessType = BusinessType.DELETE)
	@DeleteMapping("/{locbindWorkshops}")
    public AjaxResult remove(@PathVariable String[] locbindWorkshops)
    {
        return toAjax(locbindService.deleteLocbindByLocbindWorkshops(locbindWorkshops));
    }
}
