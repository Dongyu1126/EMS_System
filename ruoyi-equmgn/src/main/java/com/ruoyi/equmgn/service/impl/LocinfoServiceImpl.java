package com.ruoyi.equmgn.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.equmgn.mapper.LocinfoMapper;
import com.ruoyi.equmgn.domain.Locinfo;
import com.ruoyi.equmgn.service.ILocinfoService;

/**
 * 工位信息Service业务层处理
 * 
 * @author jiangdongyu
 * @date 2023-07-09
 */
@Service
public class LocinfoServiceImpl implements ILocinfoService 
{
    @Autowired
    private LocinfoMapper locinfoMapper;

    /**
     * 查询工位信息
     * 
     * @param locinfoAffiliatedWorkshop 工位信息主键
     * @return 工位信息
     */
    @Override
    public Locinfo selectLocinfoByLocinfoAffiliatedWorkshop(String locinfoAffiliatedWorkshop)
    {
        return locinfoMapper.selectLocinfoByLocinfoAffiliatedWorkshop(locinfoAffiliatedWorkshop);
    }

    /**
     * 查询工位信息列表
     * 
     * @param locinfo 工位信息
     * @return 工位信息
     */
    @Override
    public List<Locinfo> selectLocinfoList(Locinfo locinfo)
    {
        return locinfoMapper.selectLocinfoList(locinfo);
    }

    /**
     * 新增工位信息
     * 
     * @param locinfo 工位信息
     * @return 结果
     */
    @Override
    public int insertLocinfo(Locinfo locinfo)
    {
        return locinfoMapper.insertLocinfo(locinfo);
    }

    /**
     * 修改工位信息
     * 
     * @param locinfo 工位信息
     * @return 结果
     */
    @Override
    public int updateLocinfo(Locinfo locinfo)
    {
        return locinfoMapper.updateLocinfo(locinfo);
    }

    /**
     * 批量删除工位信息
     * 
     * @param locinfoAffiliatedWorkshops 需要删除的工位信息主键
     * @return 结果
     */
    @Override
    public int deleteLocinfoByLocinfoAffiliatedWorkshops(String[] locinfoAffiliatedWorkshops)
    {
        return locinfoMapper.deleteLocinfoByLocinfoAffiliatedWorkshops(locinfoAffiliatedWorkshops);
    }

    /**
     * 删除工位信息信息
     * 
     * @param locinfoAffiliatedWorkshop 工位信息主键
     * @return 结果
     */
    @Override
    public int deleteLocinfoByLocinfoAffiliatedWorkshop(String locinfoAffiliatedWorkshop)
    {
        return locinfoMapper.deleteLocinfoByLocinfoAffiliatedWorkshop(locinfoAffiliatedWorkshop);
    }
}
