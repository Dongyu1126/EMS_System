package com.ruoyi.equmgn.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.equmgn.mapper.EquinfoMapper;
import com.ruoyi.equmgn.domain.Equinfo;
import com.ruoyi.equmgn.service.IEquinfoService;

/**
 * 设备信息Service业务层处理
 * 
 * @author jiangdongyu
 * @date 2023-07-09
 */
@Service
public class EquinfoServiceImpl implements IEquinfoService 
{
    @Autowired
    private EquinfoMapper equinfoMapper;

    /**
     * 查询设备信息
     * 
     * @param equinfoName 设备信息主键
     * @return 设备信息
     */
    @Override
    public Equinfo selectEquinfoByEquinfoName(String equinfoName)
    {
        return equinfoMapper.selectEquinfoByEquinfoName(equinfoName);
    }

    /**
     * 查询设备信息列表
     * 
     * @param equinfo 设备信息
     * @return 设备信息
     */
    @Override
    public List<Equinfo> selectEquinfoList(Equinfo equinfo)
    {
        return equinfoMapper.selectEquinfoList(equinfo);
    }

    /**
     * 新增设备信息
     * 
     * @param equinfo 设备信息
     * @return 结果
     */
    @Override
    public int insertEquinfo(Equinfo equinfo)
    {
        return equinfoMapper.insertEquinfo(equinfo);
    }

    /**
     * 修改设备信息
     * 
     * @param equinfo 设备信息
     * @return 结果
     */
    @Override
    public int updateEquinfo(Equinfo equinfo)
    {
        return equinfoMapper.updateEquinfo(equinfo);
    }

    /**
     * 批量删除设备信息
     * 
     * @param equinfoNames 需要删除的设备信息主键
     * @return 结果
     */
    @Override
    public int deleteEquinfoByEquinfoNames(String[] equinfoNames)
    {
        return equinfoMapper.deleteEquinfoByEquinfoNames(equinfoNames);
    }

    /**
     * 删除设备信息信息
     * 
     * @param equinfoName 设备信息主键
     * @return 结果
     */
    @Override
    public int deleteEquinfoByEquinfoName(String equinfoName)
    {
        return equinfoMapper.deleteEquinfoByEquinfoName(equinfoName);
    }
}
