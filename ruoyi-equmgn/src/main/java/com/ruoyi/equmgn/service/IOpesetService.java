package com.ruoyi.equmgn.service;

import java.util.List;
import com.ruoyi.equmgn.domain.Opeset;

/**
 * 工序设置Service接口
 * 
 * @author jiangdongyu
 * @date 2023-07-09
 */
public interface IOpesetService 
{
    /**
     * 查询工序设置
     * 
     * @param opesetName 工序设置主键
     * @return 工序设置
     */
    public Opeset selectOpesetByOpesetName(String opesetName);

    /**
     * 查询工序设置列表
     * 
     * @param opeset 工序设置
     * @return 工序设置集合
     */
    public List<Opeset> selectOpesetList(Opeset opeset);

    /**
     * 新增工序设置
     * 
     * @param opeset 工序设置
     * @return 结果
     */
    public int insertOpeset(Opeset opeset);

    /**
     * 修改工序设置
     * 
     * @param opeset 工序设置
     * @return 结果
     */
    public int updateOpeset(Opeset opeset);

    /**
     * 批量删除工序设置
     * 
     * @param opesetNames 需要删除的工序设置主键集合
     * @return 结果
     */
    public int deleteOpesetByOpesetNames(String[] opesetNames);

    /**
     * 删除工序设置信息
     * 
     * @param opesetName 工序设置主键
     * @return 结果
     */
    public int deleteOpesetByOpesetName(String opesetName);
}
