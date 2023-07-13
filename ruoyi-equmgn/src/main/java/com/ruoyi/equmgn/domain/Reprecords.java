package com.ruoyi.equmgn.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 报工记录对象 reprecords
 * 
 * @author jiangdongyu
 * @date 2023-07-13
 */
public class Reprecords extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 工单号 */
    @Excel(name = "工单号")
    private String reprecordsTicketNumber;

    /** 产品 */
    @Excel(name = "产品")
    private String reprecordsProduct;

    /** 排产数量 */
    @Excel(name = "排产数量")
    private Long reprecordsScheduleNumber;

    /** 工序 */
    @Excel(name = "工序")
    private String reprecordsProcess;

    /** 报工单号 */
    @Excel(name = "报工单号")
    private String reprecordsReportTicketNumber;

    /** 报工时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "报工时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date reprecordsReportTime;

    /** 报工数量 */
    @Excel(name = "报工数量")
    private Long reprecordsReportNumber;

    /** 报工人 */
    @Excel(name = "报工人")
    private String reprecordsReportName;

    /** 车间 */
    @Excel(name = "车间")
    private String reprecordsWorkshop;

    /** 工位 */
    @Excel(name = "工位")
    private Integer reprecordsWorkLocation;

    public void setReprecordsTicketNumber(String reprecordsTicketNumber) 
    {
        this.reprecordsTicketNumber = reprecordsTicketNumber;
    }

    public String getReprecordsTicketNumber() 
    {
        return reprecordsTicketNumber;
    }
    public void setReprecordsProduct(String reprecordsProduct) 
    {
        this.reprecordsProduct = reprecordsProduct;
    }

    public String getReprecordsProduct() 
    {
        return reprecordsProduct;
    }
    public void setReprecordsScheduleNumber(Long reprecordsScheduleNumber) 
    {
        this.reprecordsScheduleNumber = reprecordsScheduleNumber;
    }

    public Long getReprecordsScheduleNumber() 
    {
        return reprecordsScheduleNumber;
    }
    public void setReprecordsProcess(String reprecordsProcess) 
    {
        this.reprecordsProcess = reprecordsProcess;
    }

    public String getReprecordsProcess() 
    {
        return reprecordsProcess;
    }
    public void setReprecordsReportTicketNumber(String reprecordsReportTicketNumber) 
    {
        this.reprecordsReportTicketNumber = reprecordsReportTicketNumber;
    }

    public String getReprecordsReportTicketNumber() 
    {
        return reprecordsReportTicketNumber;
    }
    public void setReprecordsReportTime(Date reprecordsReportTime) 
    {
        this.reprecordsReportTime = reprecordsReportTime;
    }

    public Date getReprecordsReportTime() 
    {
        return reprecordsReportTime;
    }
    public void setReprecordsReportNumber(Long reprecordsReportNumber) 
    {
        this.reprecordsReportNumber = reprecordsReportNumber;
    }

    public Long getReprecordsReportNumber() 
    {
        return reprecordsReportNumber;
    }
    public void setReprecordsReportName(String reprecordsReportName) 
    {
        this.reprecordsReportName = reprecordsReportName;
    }

    public String getReprecordsReportName() 
    {
        return reprecordsReportName;
    }
    public void setReprecordsWorkshop(String reprecordsWorkshop) 
    {
        this.reprecordsWorkshop = reprecordsWorkshop;
    }

    public String getReprecordsWorkshop() 
    {
        return reprecordsWorkshop;
    }
    public void setReprecordsWorkLocation(Integer reprecordsWorkLocation) 
    {
        this.reprecordsWorkLocation = reprecordsWorkLocation;
    }

    public Integer getReprecordsWorkLocation() 
    {
        return reprecordsWorkLocation;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("reprecordsTicketNumber", getReprecordsTicketNumber())
            .append("reprecordsProduct", getReprecordsProduct())
            .append("reprecordsScheduleNumber", getReprecordsScheduleNumber())
            .append("reprecordsProcess", getReprecordsProcess())
            .append("reprecordsReportTicketNumber", getReprecordsReportTicketNumber())
            .append("reprecordsReportTime", getReprecordsReportTime())
            .append("reprecordsReportNumber", getReprecordsReportNumber())
            .append("reprecordsReportName", getReprecordsReportName())
            .append("reprecordsWorkshop", getReprecordsWorkshop())
            .append("reprecordsWorkLocation", getReprecordsWorkLocation())
            .toString();
    }
}
