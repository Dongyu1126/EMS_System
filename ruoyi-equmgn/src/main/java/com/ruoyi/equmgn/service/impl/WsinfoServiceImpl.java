package com.ruoyi.equmgn.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.equmgn.mapper.WsinfoMapper;
import com.ruoyi.equmgn.domain.Wsinfo;
import com.ruoyi.equmgn.service.IWsinfoService;

/**
 * 车间信息Service业务层处理
 * 
 * @author jiangdongyu
 * @date 2023-07-13
 */
@Service
public class WsinfoServiceImpl implements IWsinfoService 
{
    @Autowired
    private WsinfoMapper wsinfoMapper;

    /**
     * 查询车间信息
     * 
     * @param wsinfoName 车间信息主键
     * @return 车间信息
     */
    @Override
    public Wsinfo selectWsinfoByWsinfoName(String wsinfoName)
    {
        return wsinfoMapper.selectWsinfoByWsinfoName(wsinfoName);
    }

    /**
     * 查询车间信息列表
     * 
     * @param wsinfo 车间信息
     * @return 车间信息
     */
    @Override
    public List<Wsinfo> selectWsinfoList(Wsinfo wsinfo)
    {
        return wsinfoMapper.selectWsinfoList(wsinfo);
    }

    /**
     * 新增车间信息
     * 
     * @param wsinfo 车间信息
     * @return 结果
     */
    @Override
    public int insertWsinfo(Wsinfo wsinfo)
    {
        return wsinfoMapper.insertWsinfo(wsinfo);
    }

    /**
     * 修改车间信息
     * 
     * @param wsinfo 车间信息
     * @return 结果
     */
    @Override
    public int updateWsinfo(Wsinfo wsinfo)
    {
        return wsinfoMapper.updateWsinfo(wsinfo);
    }

    /**
     * 批量删除车间信息
     * 
     * @param wsinfoNames 需要删除的车间信息主键
     * @return 结果
     */
    @Override
    public int deleteWsinfoByWsinfoNames(String[] wsinfoNames)
    {
        return wsinfoMapper.deleteWsinfoByWsinfoNames(wsinfoNames);
    }

    /**
     * 删除车间信息信息
     * 
     * @param wsinfoName 车间信息主键
     * @return 结果
     */
    @Override
    public int deleteWsinfoByWsinfoName(String wsinfoName)
    {
        return wsinfoMapper.deleteWsinfoByWsinfoName(wsinfoName);
    }
}
