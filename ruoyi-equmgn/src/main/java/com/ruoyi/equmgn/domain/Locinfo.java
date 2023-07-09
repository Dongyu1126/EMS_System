package com.ruoyi.equmgn.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 工位信息对象 locinfo
 * 
 * @author jiangdongyu
 * @date 2023-07-09
 */
public class Locinfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 所属车间 */
    @Excel(name = "所属车间")
    private String locinfoAffiliatedWorkshop;

    /** 工位名称 */
    @Excel(name = "工位名称")
    private String locinfoName;

    /** 工位编号 */
    @Excel(name = "工位编号")
    private String locinfoNumber;

    /** 绑定工序 */
    @Excel(name = "绑定工序")
    private String locinfoBindOperation;

    /** 绑定设备 */
    @Excel(name = "绑定设备")
    private String locinfoBindEquipment;

    /** 状态 */
    @Excel(name = "状态")
    private Long locinfoStatus;

    /** 备注 */
    @Excel(name = "备注")
    private String locinfoRemark;

    public void setLocinfoAffiliatedWorkshop(String locinfoAffiliatedWorkshop) 
    {
        this.locinfoAffiliatedWorkshop = locinfoAffiliatedWorkshop;
    }

    public String getLocinfoAffiliatedWorkshop() 
    {
        return locinfoAffiliatedWorkshop;
    }
    public void setLocinfoName(String locinfoName) 
    {
        this.locinfoName = locinfoName;
    }

    public String getLocinfoName() 
    {
        return locinfoName;
    }
    public void setLocinfoNumber(String locinfoNumber) 
    {
        this.locinfoNumber = locinfoNumber;
    }

    public String getLocinfoNumber() 
    {
        return locinfoNumber;
    }
    public void setLocinfoBindOperation(String locinfoBindOperation) 
    {
        this.locinfoBindOperation = locinfoBindOperation;
    }

    public String getLocinfoBindOperation() 
    {
        return locinfoBindOperation;
    }
    public void setLocinfoBindEquipment(String locinfoBindEquipment) 
    {
        this.locinfoBindEquipment = locinfoBindEquipment;
    }

    public String getLocinfoBindEquipment() 
    {
        return locinfoBindEquipment;
    }
    public void setLocinfoStatus(Long locinfoStatus) 
    {
        this.locinfoStatus = locinfoStatus;
    }

    public Long getLocinfoStatus() 
    {
        return locinfoStatus;
    }
    public void setLocinfoRemark(String locinfoRemark) 
    {
        this.locinfoRemark = locinfoRemark;
    }

    public String getLocinfoRemark() 
    {
        return locinfoRemark;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("locinfoAffiliatedWorkshop", getLocinfoAffiliatedWorkshop())
            .append("locinfoName", getLocinfoName())
            .append("locinfoNumber", getLocinfoNumber())
            .append("locinfoBindOperation", getLocinfoBindOperation())
            .append("locinfoBindEquipment", getLocinfoBindEquipment())
            .append("locinfoStatus", getLocinfoStatus())
            .append("locinfoRemark", getLocinfoRemark())
            .toString();
    }
}
