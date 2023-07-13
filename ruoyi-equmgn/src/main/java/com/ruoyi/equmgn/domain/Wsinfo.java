package com.ruoyi.equmgn.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 车间信息对象 wsinfo
 * 
 * @author jiangdongyu
 * @date 2023-07-13
 */
public class Wsinfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 车间名 */
    @Excel(name = "车间名")
    private String wsinfoName;

    /** 车间负责人 */
    @Excel(name = "车间负责人")
    private String wsinfoLeader;

    /** 车间状态（0正常1停用） */
    @Excel(name = "车间状态", readConverterExp = "0=正常1停用")
    private Integer wsinfoStatus;

    /** 车间添加时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "车间添加时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date wsinfoCreateTime;

    /** 车间备注 */
    @Excel(name = "车间备注")
    private String wsinfoRemark;

    public void setWsinfoName(String wsinfoName) 
    {
        this.wsinfoName = wsinfoName;
    }

    public String getWsinfoName() 
    {
        return wsinfoName;
    }
    public void setWsinfoLeader(String wsinfoLeader) 
    {
        this.wsinfoLeader = wsinfoLeader;
    }

    public String getWsinfoLeader() 
    {
        return wsinfoLeader;
    }
    public void setWsinfoStatus(Integer wsinfoStatus) 
    {
        this.wsinfoStatus = wsinfoStatus;
    }

    public Integer getWsinfoStatus() 
    {
        return wsinfoStatus;
    }
    public void setWsinfoCreateTime(Date wsinfoCreateTime) 
    {
        this.wsinfoCreateTime = wsinfoCreateTime;
    }

    public Date getWsinfoCreateTime() 
    {
        return wsinfoCreateTime;
    }
    public void setWsinfoRemark(String wsinfoRemark) 
    {
        this.wsinfoRemark = wsinfoRemark;
    }

    public String getWsinfoRemark() 
    {
        return wsinfoRemark;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("wsinfoName", getWsinfoName())
            .append("wsinfoLeader", getWsinfoLeader())
            .append("wsinfoStatus", getWsinfoStatus())
            .append("wsinfoCreateTime", getWsinfoCreateTime())
            .append("wsinfoRemark", getWsinfoRemark())
            .toString();
    }
}
