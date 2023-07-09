package com.ruoyi.equmgn.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.equmgn.mapper.OpesetMapper;
import com.ruoyi.equmgn.domain.Opeset;
import com.ruoyi.equmgn.service.IOpesetService;

/**
 * 工序设置Service业务层处理
 * 
 * @author jiangdongyu
 * @date 2023-07-09
 */
@Service
public class OpesetServiceImpl implements IOpesetService 
{
    @Autowired
    private OpesetMapper opesetMapper;

    /**
     * 查询工序设置
     * 
     * @param opesetName 工序设置主键
     * @return 工序设置
     */
    @Override
    public Opeset selectOpesetByOpesetName(String opesetName)
    {
        return opesetMapper.selectOpesetByOpesetName(opesetName);
    }

    /**
     * 查询工序设置列表
     * 
     * @param opeset 工序设置
     * @return 工序设置
     */
    @Override
    public List<Opeset> selectOpesetList(Opeset opeset)
    {
        return opesetMapper.selectOpesetList(opeset);
    }

    /**
     * 新增工序设置
     * 
     * @param opeset 工序设置
     * @return 结果
     */
    @Override
    public int insertOpeset(Opeset opeset)
    {
        return opesetMapper.insertOpeset(opeset);
    }

    /**
     * 修改工序设置
     * 
     * @param opeset 工序设置
     * @return 结果
     */
    @Override
    public int updateOpeset(Opeset opeset)
    {
        return opesetMapper.updateOpeset(opeset);
    }

    /**
     * 批量删除工序设置
     * 
     * @param opesetNames 需要删除的工序设置主键
     * @return 结果
     */
    @Override
    public int deleteOpesetByOpesetNames(String[] opesetNames)
    {
        return opesetMapper.deleteOpesetByOpesetNames(opesetNames);
    }

    /**
     * 删除工序设置信息
     * 
     * @param opesetName 工序设置主键
     * @return 结果
     */
    @Override
    public int deleteOpesetByOpesetName(String opesetName)
    {
        return opesetMapper.deleteOpesetByOpesetName(opesetName);
    }
}
