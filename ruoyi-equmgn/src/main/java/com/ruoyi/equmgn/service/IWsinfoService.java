package com.ruoyi.equmgn.service;

import java.util.List;
import com.ruoyi.equmgn.domain.Wsinfo;

/**
 * 车间信息Service接口
 * 
 * @author jiangdongyu
 * @date 2023-07-09
 */
public interface IWsinfoService 
{
    /**
     * 查询车间信息
     * 
     * @param wsinfoName 车间信息主键
     * @return 车间信息
     */
    public Wsinfo selectWsinfoByWsinfoName(String wsinfoName);

    /**
     * 查询车间信息列表
     * 
     * @param wsinfo 车间信息
     * @return 车间信息集合
     */
    public List<Wsinfo> selectWsinfoList(Wsinfo wsinfo);

    /**
     * 新增车间信息
     * 
     * @param wsinfo 车间信息
     * @return 结果
     */
    public int insertWsinfo(Wsinfo wsinfo);

    /**
     * 修改车间信息
     * 
     * @param wsinfo 车间信息
     * @return 结果
     */
    public int updateWsinfo(Wsinfo wsinfo);

    /**
     * 批量删除车间信息
     * 
     * @param wsinfoNames 需要删除的车间信息主键集合
     * @return 结果
     */
    public int deleteWsinfoByWsinfoNames(String[] wsinfoNames);

    /**
     * 删除车间信息信息
     * 
     * @param wsinfoName 车间信息主键
     * @return 结果
     */
    public int deleteWsinfoByWsinfoName(String wsinfoName);
}
