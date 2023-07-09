package com.ruoyi.equmgn.mapper;

import java.util.List;
import com.ruoyi.equmgn.domain.Locinfo;

/**
 * 工位信息Mapper接口
 * 
 * @author jiangdongyu
 * @date 2023-07-09
 */
public interface LocinfoMapper 
{
    /**
     * 查询工位信息
     * 
     * @param locinfoAffiliatedWorkshop 工位信息主键
     * @return 工位信息
     */
    public Locinfo selectLocinfoByLocinfoAffiliatedWorkshop(String locinfoAffiliatedWorkshop);

    /**
     * 查询工位信息列表
     * 
     * @param locinfo 工位信息
     * @return 工位信息集合
     */
    public List<Locinfo> selectLocinfoList(Locinfo locinfo);

    /**
     * 新增工位信息
     * 
     * @param locinfo 工位信息
     * @return 结果
     */
    public int insertLocinfo(Locinfo locinfo);

    /**
     * 修改工位信息
     * 
     * @param locinfo 工位信息
     * @return 结果
     */
    public int updateLocinfo(Locinfo locinfo);

    /**
     * 删除工位信息
     * 
     * @param locinfoAffiliatedWorkshop 工位信息主键
     * @return 结果
     */
    public int deleteLocinfoByLocinfoAffiliatedWorkshop(String locinfoAffiliatedWorkshop);

    /**
     * 批量删除工位信息
     * 
     * @param locinfoAffiliatedWorkshops 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteLocinfoByLocinfoAffiliatedWorkshops(String[] locinfoAffiliatedWorkshops);
}
