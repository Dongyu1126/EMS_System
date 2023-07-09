package com.ruoyi.equmgn.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 工序路线对象 operou
 * 
 * @author jiangdongyu
 * @date 2023-07-09
 */
public class Operou extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 工序路线名称 */
    @Excel(name = "工序路线名称")
    private String operouName;

    /** 工序路线说明 */
    @Excel(name = "工序路线说明")
    private String operouDescription;

    /** 状态 */
    @Excel(name = "状态")
    private Long operouStatus;

    /** 添加时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "添加时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date operouCreateTime;

    public void setOperouName(String operouName) 
    {
        this.operouName = operouName;
    }

    public String getOperouName() 
    {
        return operouName;
    }
    public void setOperouDescription(String operouDescription) 
    {
        this.operouDescription = operouDescription;
    }

    public String getOperouDescription() 
    {
        return operouDescription;
    }
    public void setOperouStatus(Long operouStatus) 
    {
        this.operouStatus = operouStatus;
    }

    public Long getOperouStatus() 
    {
        return operouStatus;
    }
    public void setOperouCreateTime(Date operouCreateTime) 
    {
        this.operouCreateTime = operouCreateTime;
    }

    public Date getOperouCreateTime() 
    {
        return operouCreateTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("operouName", getOperouName())
            .append("operouDescription", getOperouDescription())
            .append("operouStatus", getOperouStatus())
            .append("operouCreateTime", getOperouCreateTime())
            .toString();
    }
}
