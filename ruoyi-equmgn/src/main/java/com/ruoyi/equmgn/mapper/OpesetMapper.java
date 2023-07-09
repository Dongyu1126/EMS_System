package com.ruoyi.equmgn.mapper;

import java.util.List;
import com.ruoyi.equmgn.domain.Opeset;

/**
 * 工序设置Mapper接口
 * 
 * @author jiangdongyu
 * @date 2023-07-09
 */
public interface OpesetMapper 
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
     * 删除工序设置
     * 
     * @param opesetName 工序设置主键
     * @return 结果
     */
    public int deleteOpesetByOpesetName(String opesetName);

    /**
     * 批量删除工序设置
     * 
     * @param opesetNames 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteOpesetByOpesetNames(String[] opesetNames);
}
