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
import com.ruoyi.equmgn.domain.Reprecords;
import com.ruoyi.equmgn.service.IReprecordsService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 报工记录Controller
 * 
 * @author jiangdongyu
 * @date 2023-07-13
 */
@RestController
@RequestMapping("/equmgn/reprecords")
public class ReprecordsController extends BaseController
{
    @Autowired
    private IReprecordsService reprecordsService;

    /**
     * 查询报工记录列表
     */
    @PreAuthorize("@ss.hasPermi('equmgn:reprecords:list')")
    @GetMapping("/list")
    public TableDataInfo list(Reprecords reprecords)
    {
        startPage();
        List<Reprecords> list = reprecordsService.selectReprecordsList(reprecords);
        return getDataTable(list);
    }

    /**
     * 导出报工记录列表
     */
    @PreAuthorize("@ss.hasPermi('equmgn:reprecords:export')")
    @Log(title = "报工记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Reprecords reprecords)
    {
        List<Reprecords> list = reprecordsService.selectReprecordsList(reprecords);
        ExcelUtil<Reprecords> util = new ExcelUtil<Reprecords>(Reprecords.class);
        util.exportExcel(response, list, "报工记录数据");
    }

    /**
     * 获取报工记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('equmgn:reprecords:query')")
    @GetMapping(value = "/{reprecordsTicketNumber}")
    public AjaxResult getInfo(@PathVariable("reprecordsTicketNumber") String reprecordsTicketNumber)
    {
        return success(reprecordsService.selectReprecordsByReprecordsTicketNumber(reprecordsTicketNumber));
    }

    /**
     * 新增报工记录
     */
    @PreAuthorize("@ss.hasPermi('equmgn:reprecords:add')")
    @Log(title = "报工记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Reprecords reprecords)
    {
        return toAjax(reprecordsService.insertReprecords(reprecords));
    }

    /**
     * 修改报工记录
     */
    @PreAuthorize("@ss.hasPermi('equmgn:reprecords:edit')")
    @Log(title = "报工记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Reprecords reprecords)
    {
        return toAjax(reprecordsService.updateReprecords(reprecords));
    }

    /**
     * 删除报工记录
     */
    @PreAuthorize("@ss.hasPermi('equmgn:reprecords:remove')")
    @Log(title = "报工记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{reprecordsTicketNumbers}")
    public AjaxResult remove(@PathVariable String[] reprecordsTicketNumbers)
    {
        return toAjax(reprecordsService.deleteReprecordsByReprecordsTicketNumbers(reprecordsTicketNumbers));
    }
}
