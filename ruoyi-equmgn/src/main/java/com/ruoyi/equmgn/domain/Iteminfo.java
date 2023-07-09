package com.ruoyi.equmgn.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 物料信息对象 iteminfo
 * 
 * @author jiangdongyu
 * @date 2023-07-09
 */
public class Iteminfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long iteminfoNumber;

    /** 名称 */
    @Excel(name = "名称")
    private String iteminfoName;

    /** 规格型号 */
    @Excel(name = "规格型号")
    private String iteminfoSpecificationsModels;

    /** 计量单位 */
    @Excel(name = "计量单位")
    private String iteminfoMeasureUnit;

    /** 物料来源 */
    @Excel(name = "物料来源")
    private String iteminfoSource;

    /** 状态 */
    @Excel(name = "状态")
    private Long iteminfoStatus;

    /** 添加时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "添加时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date iteminfoCreateTime;

    public void setIteminfoNumber(Long iteminfoNumber) 
    {
        this.iteminfoNumber = iteminfoNumber;
    }

    public Long getIteminfoNumber() 
    {
        return iteminfoNumber;
    }
    public void setIteminfoName(String iteminfoName) 
    {
        this.iteminfoName = iteminfoName;
    }

    public String getIteminfoName() 
    {
        return iteminfoName;
    }
    public void setIteminfoSpecificationsModels(String iteminfoSpecificationsModels) 
    {
        this.iteminfoSpecificationsModels = iteminfoSpecificationsModels;
    }

    public String getIteminfoSpecificationsModels() 
    {
        return iteminfoSpecificationsModels;
    }
    public void setIteminfoMeasureUnit(String iteminfoMeasureUnit) 
    {
        this.iteminfoMeasureUnit = iteminfoMeasureUnit;
    }

    public String getIteminfoMeasureUnit() 
    {
        return iteminfoMeasureUnit;
    }
    public void setIteminfoSource(String iteminfoSource) 
    {
        this.iteminfoSource = iteminfoSource;
    }

    public String getIteminfoSource() 
    {
        return iteminfoSource;
    }
    public void setIteminfoStatus(Long iteminfoStatus) 
    {
        this.iteminfoStatus = iteminfoStatus;
    }

    public Long getIteminfoStatus() 
    {
        return iteminfoStatus;
    }
    public void setIteminfoCreateTime(Date iteminfoCreateTime) 
    {
        this.iteminfoCreateTime = iteminfoCreateTime;
    }

    public Date getIteminfoCreateTime() 
    {
        return iteminfoCreateTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("iteminfoNumber", getIteminfoNumber())
            .append("iteminfoName", getIteminfoName())
            .append("iteminfoSpecificationsModels", getIteminfoSpecificationsModels())
            .append("iteminfoMeasureUnit", getIteminfoMeasureUnit())
            .append("iteminfoSource", getIteminfoSource())
            .append("iteminfoStatus", getIteminfoStatus())
            .append("iteminfoCreateTime", getIteminfoCreateTime())
            .toString();
    }
}
