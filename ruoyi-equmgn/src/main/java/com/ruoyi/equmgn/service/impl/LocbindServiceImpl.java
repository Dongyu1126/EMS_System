package com.ruoyi.equmgn.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.equmgn.mapper.LocbindMapper;
import com.ruoyi.equmgn.domain.Locbind;
import com.ruoyi.equmgn.service.ILocbindService;

/**
 * 工位绑定Service业务层处理
 * 
 * @author jiangdongyu
 * @date 2023-07-09
 */
@Service
public class LocbindServiceImpl implements ILocbindService 
{
    @Autowired
    private LocbindMapper locbindMapper;

    /**
     * 查询工位绑定
     * 
     * @param locbindWorkshop 工位绑定主键
     * @return 工位绑定
     */
    @Override
    public Locbind selectLocbindByLocbindWorkshop(String locbindWorkshop)
    {
        return locbindMapper.selectLocbindByLocbindWorkshop(locbindWorkshop);
    }

    /**
     * 查询工位绑定列表
     * 
     * @param locbind 工位绑定
     * @return 工位绑定
     */
    @Override
    public List<Locbind> selectLocbindList(Locbind locbind)
    {
        return locbindMapper.selectLocbindList(locbind);
    }

    /**
     * 新增工位绑定
     * 
     * @param locbind 工位绑定
     * @return 结果
     */
    @Override
    public int insertLocbind(Locbind locbind)
    {
        return locbindMapper.insertLocbind(locbind);
    }

    /**
     * 修改工位绑定
     * 
     * @param locbind 工位绑定
     * @return 结果
     */
    @Override
    public int updateLocbind(Locbind locbind)
    {
        return locbindMapper.updateLocbind(locbind);
    }

    /**
     * 批量删除工位绑定
     * 
     * @param locbindWorkshops 需要删除的工位绑定主键
     * @return 结果
     */
    @Override
    public int deleteLocbindByLocbindWorkshops(String[] locbindWorkshops)
    {
        return locbindMapper.deleteLocbindByLocbindWorkshops(locbindWorkshops);
    }

    /**
     * 删除工位绑定信息
     * 
     * @param locbindWorkshop 工位绑定主键
     * @return 结果
     */
    @Override
    public int deleteLocbindByLocbindWorkshop(String locbindWorkshop)
    {
        return locbindMapper.deleteLocbindByLocbindWorkshop(locbindWorkshop);
    }
}
