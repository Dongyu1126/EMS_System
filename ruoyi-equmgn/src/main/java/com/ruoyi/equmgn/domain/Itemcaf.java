package com.ruoyi.equmgn.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 物料分类对象 itemcaf
 * 
 * @author jiangdongyu
 * @date 2023-07-09
 */
public class Itemcaf extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 名称 */
    @Excel(name = "名称")
    private String itemcafName;

    /** 父顶id */
    private String itemcafFatherid;

    /** 显示顺序 */
    private Long itemcafDisplayOrder;

    public void setItemcafName(String itemcafName) 
    {
        this.itemcafName = itemcafName;
    }

    public String getItemcafName() 
    {
        return itemcafName;
    }
    public void setItemcafFatherid(String itemcafFatherid) 
    {
        this.itemcafFatherid = itemcafFatherid;
    }

    public String getItemcafFatherid() 
    {
        return itemcafFatherid;
    }
    public void setItemcafDisplayOrder(Long itemcafDisplayOrder) 
    {
        this.itemcafDisplayOrder = itemcafDisplayOrder;
    }

    public Long getItemcafDisplayOrder() 
    {
        return itemcafDisplayOrder;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("itemcafName", getItemcafName())
            .append("itemcafFatherid", getItemcafFatherid())
            .append("itemcafDisplayOrder", getItemcafDisplayOrder())
            .toString();
    }
}
