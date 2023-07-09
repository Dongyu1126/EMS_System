package com.ruoyi.equmgn.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 设备信息对象 equinfo
 * 
 * @author jiangdongyu
 * @date 2023-07-09
 */
public class Equinfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 名称 */
    @Excel(name = "名称")
    private String equinfoName;

    /** 品牌 */
    @Excel(name = "品牌")
    private String equinfoBrand;

    /** 型号 */
    @Excel(name = "型号")
    private String equinfoModel;

    /** 设备类型 */
    @Excel(name = "设备类型")
    private String equinfoType;

    /** 设备状态（0待机1运行中2关机） */
    @Excel(name = "设备状态", readConverterExp = "0=待机1运行中2关机")
    private Long equinfoStatus;

    /** 启动状态（0正常1停用） */
    @Excel(name = "启动状态", readConverterExp = "0=正常1停用")
    private Long equinfoStartStatus;

    /** 添加时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "添加时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date equinfoCreateTime;

    /** 设备序列号 */
    private String equinfoSerialNumber;

    public void setEquinfoName(String equinfoName) 
    {
        this.equinfoName = equinfoName;
    }

    public String getEquinfoName() 
    {
        return equinfoName;
    }
    public void setEquinfoBrand(String equinfoBrand) 
    {
        this.equinfoBrand = equinfoBrand;
    }

    public String getEquinfoBrand() 
    {
        return equinfoBrand;
    }
    public void setEquinfoModel(String equinfoModel) 
    {
        this.equinfoModel = equinfoModel;
    }

    public String getEquinfoModel() 
    {
        return equinfoModel;
    }
    public void setEquinfoType(String equinfoType) 
    {
        this.equinfoType = equinfoType;
    }

    public String getEquinfoType() 
    {
        return equinfoType;
    }
    public void setEquinfoStatus(Long equinfoStatus) 
    {
        this.equinfoStatus = equinfoStatus;
    }

    public Long getEquinfoStatus() 
    {
        return equinfoStatus;
    }
    public void setEquinfoStartStatus(Long equinfoStartStatus) 
    {
        this.equinfoStartStatus = equinfoStartStatus;
    }

    public Long getEquinfoStartStatus() 
    {
        return equinfoStartStatus;
    }
    public void setEquinfoCreateTime(Date equinfoCreateTime) 
    {
        this.equinfoCreateTime = equinfoCreateTime;
    }

    public Date getEquinfoCreateTime() 
    {
        return equinfoCreateTime;
    }
    public void setEquinfoSerialNumber(String equinfoSerialNumber) 
    {
        this.equinfoSerialNumber = equinfoSerialNumber;
    }

    public String getEquinfoSerialNumber() 
    {
        return equinfoSerialNumber;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("equinfoName", getEquinfoName())
            .append("equinfoBrand", getEquinfoBrand())
            .append("equinfoModel", getEquinfoModel())
            .append("equinfoType", getEquinfoType())
            .append("equinfoStatus", getEquinfoStatus())
            .append("equinfoStartStatus", getEquinfoStartStatus())
            .append("equinfoCreateTime", getEquinfoCreateTime())
            .append("equinfoSerialNumber", getEquinfoSerialNumber())
            .toString();
    }
}
