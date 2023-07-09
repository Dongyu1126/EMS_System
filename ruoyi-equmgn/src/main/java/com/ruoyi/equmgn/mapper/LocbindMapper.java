package com.ruoyi.equmgn.mapper;

import java.util.List;
import com.ruoyi.equmgn.domain.Locbind;

/**
 * 工位绑定Mapper接口
 * 
 * @author jiangdongyu
 * @date 2023-07-09
 */
public interface LocbindMapper 
{
    /**
     * 查询工位绑定
     * 
     * @param locbindWorkshop 工位绑定主键
     * @return 工位绑定
     */
    public Locbind selectLocbindByLocbindWorkshop(String locbindWorkshop);

    /**
     * 查询工位绑定列表
     * 
     * @param locbind 工位绑定
     * @return 工位绑定集合
     */
    public List<Locbind> selectLocbindList(Locbind locbind);

    /**
     * 新增工位绑定
     * 
     * @param locbind 工位绑定
     * @return 结果
     */
    public int insertLocbind(Locbind locbind);

    /**
     * 修改工位绑定
     * 
     * @param locbind 工位绑定
     * @return 结果
     */
    public int updateLocbind(Locbind locbind);

    /**
     * 删除工位绑定
     * 
     * @param locbindWorkshop 工位绑定主键
     * @return 结果
     */
    public int deleteLocbindByLocbindWorkshop(String locbindWorkshop);

    /**
     * 批量删除工位绑定
     * 
     * @param locbindWorkshops 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteLocbindByLocbindWorkshops(String[] locbindWorkshops);
}
