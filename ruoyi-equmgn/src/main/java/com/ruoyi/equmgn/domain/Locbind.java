package com.ruoyi.equmgn.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 工位绑定对象 locbind
 * 
 * @author jiangdongyu
 * @date 2023-07-09
 */
public class Locbind extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 车间 */
    @Excel(name = "车间")
    private String locbindWorkshop;

    /** 工位 */
    @Excel(name = "工位")
    private String locbindLocation;

    /** 用户名 */
    @Excel(name = "用户名")
    private String locbindUsername;

    /** 昵称 */
    @Excel(name = "昵称")
    private String locbindNickname;

    /** 操作人 */
    @Excel(name = "操作人")
    private String locbindOperator;

    /** 操作时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "操作时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date locbindOperationTime;

    public void setLocbindWorkshop(String locbindWorkshop) 
    {
        this.locbindWorkshop = locbindWorkshop;
    }

    public String getLocbindWorkshop() 
    {
        return locbindWorkshop;
    }
    public void setLocbindLocation(String locbindLocation) 
    {
        this.locbindLocation = locbindLocation;
    }

    public String getLocbindLocation() 
    {
        return locbindLocation;
    }
    public void setLocbindUsername(String locbindUsername) 
    {
        this.locbindUsername = locbindUsername;
    }

    public String getLocbindUsername() 
    {
        return locbindUsername;
    }
    public void setLocbindNickname(String locbindNickname) 
    {
        this.locbindNickname = locbindNickname;
    }

    public String getLocbindNickname() 
    {
        return locbindNickname;
    }
    public void setLocbindOperator(String locbindOperator) 
    {
        this.locbindOperator = locbindOperator;
    }

    public String getLocbindOperator() 
    {
        return locbindOperator;
    }
    public void setLocbindOperationTime(Date locbindOperationTime) 
    {
        this.locbindOperationTime = locbindOperationTime;
    }

    public Date getLocbindOperationTime() 
    {
        return locbindOperationTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("locbindWorkshop", getLocbindWorkshop())
            .append("locbindLocation", getLocbindLocation())
            .append("locbindUsername", getLocbindUsername())
            .append("locbindNickname", getLocbindNickname())
            .append("locbindOperator", getLocbindOperator())
            .append("locbindOperationTime", getLocbindOperationTime())
            .toString();
    }
}
