package com.ruoyi.equmgn.service;

import java.util.List;
import com.ruoyi.equmgn.domain.Proworkorders;

/**
 * 生产工单Service接口
 * 
 * @author jiangdongyu
 * @date 2023-07-13
 */
public interface IProworkordersService 
{
    /**
     * 查询生产工单
     * 
     * @param proworkordersProductStatus 生产工单主键
     * @return 生产工单
     */
    public Proworkorders selectProworkordersByProworkordersProductStatus(String proworkordersProductStatus);

    /**
     * 查询生产工单列表
     * 
     * @param proworkorders 生产工单
     * @return 生产工单集合
     */
    public List<Proworkorders> selectProworkordersList(Proworkorders proworkorders);

    /**
     * 新增生产工单
     * 
     * @param proworkorders 生产工单
     * @return 结果
     */
    public int insertProworkorders(Proworkorders proworkorders);

    /**
     * 修改生产工单
     * 
     * @param proworkorders 生产工单
     * @return 结果
     */
    public int updateProworkorders(Proworkorders proworkorders);

    /**
     * 批量删除生产工单
     * 
     * @param proworkordersProductStatuss 需要删除的生产工单主键集合
     * @return 结果
     */
    public int deleteProworkordersByProworkordersProductStatuss(String[] proworkordersProductStatuss);

    /**
     * 删除生产工单信息
     * 
     * @param proworkordersProductStatus 生产工单主键
     * @return 结果
     */
    public int deleteProworkordersByProworkordersProductStatus(String proworkordersProductStatus);
}
