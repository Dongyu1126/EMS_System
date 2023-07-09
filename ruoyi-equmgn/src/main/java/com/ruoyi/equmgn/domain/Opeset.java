package com.ruoyi.equmgn.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 工序设置对象 opeset
 * 
 * @author jiangdongyu
 * @date 2023-07-09
 */
public class Opeset extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 工序名 */
    @Excel(name = "工序名")
    private String opesetName;

    /** 工序描述 */
    @Excel(name = "工序描述")
    private String opesetDescription;

    /** 状态 */
    @Excel(name = "状态")
    private Long opesetStatus;

    /** 添加时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "添加时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date opesetCreateTime;

    public void setOpesetName(String opesetName) 
    {
        this.opesetName = opesetName;
    }

    public String getOpesetName() 
    {
        return opesetName;
    }
    public void setOpesetDescription(String opesetDescription) 
    {
        this.opesetDescription = opesetDescription;
    }

    public String getOpesetDescription() 
    {
        return opesetDescription;
    }
    public void setOpesetStatus(Long opesetStatus) 
    {
        this.opesetStatus = opesetStatus;
    }

    public Long getOpesetStatus() 
    {
        return opesetStatus;
    }
    public void setOpesetCreateTime(Date opesetCreateTime) 
    {
        this.opesetCreateTime = opesetCreateTime;
    }

    public Date getOpesetCreateTime() 
    {
        return opesetCreateTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("opesetName", getOpesetName())
            .append("opesetDescription", getOpesetDescription())
            .append("opesetStatus", getOpesetStatus())
            .append("opesetCreateTime", getOpesetCreateTime())
            .toString();
    }
}
