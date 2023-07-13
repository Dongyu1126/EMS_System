package com.ruoyi.equmgn.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 生产工单对象 proworkorders
 * 
 * @author jiangdongyu
 * @date 2023-07-13
 */
public class Proworkorders extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 生产状态 */
    @Excel(name = "生产状态")
    private String proworkordersProductStatus;

    /** 工单编号 */
    @Excel(name = "工单编号")
    private String proworkordersOrderNumber;

    /** 产品编号 */
    @Excel(name = "产品编号")
    private String proworkordersProductNumber;

    /** 产品名称 */
    @Excel(name = "产品名称")
    private String proworkordersProductName;

    /** 批次号 */
    @Excel(name = "批次号")
    private String proworkordersBatchNumber;

    /** 单位 */
    @Excel(name = "单位")
    private String proworkordersUint;

    /** 生产数量 */
    @Excel(name = "生产数量")
    private Long proworkordersProductionQuantity;

    /** 计划完成日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "计划完成日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date proworkordersPlanCompDate;

    /** 开始完成日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "开始完成日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date proworkordersStartProduceDate;

    /** 实际完成日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "实际完成日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date proworkordersActualCompDate;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date proworkordersCreateTime;

    /** 创建人 */
    private String proworkordersInventor;

    /** 物料 */
    private String proworkordersMaterial;

    /** 状态 */
    private Long proworkordersStatus;

    public void setProworkordersProductStatus(String proworkordersProductStatus) 
    {
        this.proworkordersProductStatus = proworkordersProductStatus;
    }

    public String getProworkordersProductStatus() 
    {
        return proworkordersProductStatus;
    }
    public void setProworkordersOrderNumber(String proworkordersOrderNumber) 
    {
        this.proworkordersOrderNumber = proworkordersOrderNumber;
    }

    public String getProworkordersOrderNumber() 
    {
        return proworkordersOrderNumber;
    }
    public void setProworkordersProductNumber(String proworkordersProductNumber) 
    {
        this.proworkordersProductNumber = proworkordersProductNumber;
    }

    public String getProworkordersProductNumber() 
    {
        return proworkordersProductNumber;
    }
    public void setProworkordersProductName(String proworkordersProductName) 
    {
        this.proworkordersProductName = proworkordersProductName;
    }

    public String getProworkordersProductName() 
    {
        return proworkordersProductName;
    }
    public void setProworkordersBatchNumber(String proworkordersBatchNumber) 
    {
        this.proworkordersBatchNumber = proworkordersBatchNumber;
    }

    public String getProworkordersBatchNumber() 
    {
        return proworkordersBatchNumber;
    }
    public void setProworkordersUint(String proworkordersUint) 
    {
        this.proworkordersUint = proworkordersUint;
    }

    public String getProworkordersUint() 
    {
        return proworkordersUint;
    }
    public void setProworkordersProductionQuantity(Long proworkordersProductionQuantity) 
    {
        this.proworkordersProductionQuantity = proworkordersProductionQuantity;
    }

    public Long getProworkordersProductionQuantity() 
    {
        return proworkordersProductionQuantity;
    }
    public void setProworkordersPlanCompDate(Date proworkordersPlanCompDate) 
    {
        this.proworkordersPlanCompDate = proworkordersPlanCompDate;
    }

    public Date getProworkordersPlanCompDate() 
    {
        return proworkordersPlanCompDate;
    }
    public void setProworkordersStartProduceDate(Date proworkordersStartProduceDate) 
    {
        this.proworkordersStartProduceDate = proworkordersStartProduceDate;
    }

    public Date getProworkordersStartProduceDate() 
    {
        return proworkordersStartProduceDate;
    }
    public void setProworkordersActualCompDate(Date proworkordersActualCompDate) 
    {
        this.proworkordersActualCompDate = proworkordersActualCompDate;
    }

    public Date getProworkordersActualCompDate() 
    {
        return proworkordersActualCompDate;
    }
    public void setProworkordersCreateTime(Date proworkordersCreateTime) 
    {
        this.proworkordersCreateTime = proworkordersCreateTime;
    }

    public Date getProworkordersCreateTime() 
    {
        return proworkordersCreateTime;
    }
    public void setProworkordersInventor(String proworkordersInventor) 
    {
        this.proworkordersInventor = proworkordersInventor;
    }

    public String getProworkordersInventor() 
    {
        return proworkordersInventor;
    }
    public void setProworkordersMaterial(String proworkordersMaterial) 
    {
        this.proworkordersMaterial = proworkordersMaterial;
    }

    public String getProworkordersMaterial() 
    {
        return proworkordersMaterial;
    }
    public void setProworkordersStatus(Long proworkordersStatus) 
    {
        this.proworkordersStatus = proworkordersStatus;
    }

    public Long getProworkordersStatus() 
    {
        return proworkordersStatus;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("proworkordersProductStatus", getProworkordersProductStatus())
            .append("proworkordersOrderNumber", getProworkordersOrderNumber())
            .append("proworkordersProductNumber", getProworkordersProductNumber())
            .append("proworkordersProductName", getProworkordersProductName())
            .append("proworkordersBatchNumber", getProworkordersBatchNumber())
            .append("proworkordersUint", getProworkordersUint())
            .append("proworkordersProductionQuantity", getProworkordersProductionQuantity())
            .append("proworkordersPlanCompDate", getProworkordersPlanCompDate())
            .append("proworkordersStartProduceDate", getProworkordersStartProduceDate())
            .append("proworkordersActualCompDate", getProworkordersActualCompDate())
            .append("proworkordersCreateTime", getProworkordersCreateTime())
            .append("proworkordersInventor", getProworkordersInventor())
            .append("proworkordersMaterial", getProworkordersMaterial())
            .append("proworkordersStatus", getProworkordersStatus())
            .toString();
    }
}
