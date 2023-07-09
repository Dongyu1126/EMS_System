package com.ruoyi.equmgn.mapper;

import java.util.List;
import com.ruoyi.equmgn.domain.Iteminfo;

/**
 * 物料信息Mapper接口
 * 
 * @author jiangdongyu
 * @date 2023-07-09
 */
public interface IteminfoMapper 
{
    /**
     * 查询物料信息
     * 
     * @param iteminfoNumber 物料信息主键
     * @return 物料信息
     */
    public Iteminfo selectIteminfoByIteminfoNumber(Long iteminfoNumber);

    /**
     * 查询物料信息列表
     * 
     * @param iteminfo 物料信息
     * @return 物料信息集合
     */
    public List<Iteminfo> selectIteminfoList(Iteminfo iteminfo);

    /**
     * 新增物料信息
     * 
     * @param iteminfo 物料信息
     * @return 结果
     */
    public int insertIteminfo(Iteminfo iteminfo);

    /**
     * 修改物料信息
     * 
     * @param iteminfo 物料信息
     * @return 结果
     */
    public int updateIteminfo(Iteminfo iteminfo);

    /**
     * 删除物料信息
     * 
     * @param iteminfoNumber 物料信息主键
     * @return 结果
     */
    public int deleteIteminfoByIteminfoNumber(Long iteminfoNumber);

    /**
     * 批量删除物料信息
     * 
     * @param iteminfoNumbers 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteIteminfoByIteminfoNumbers(Long[] iteminfoNumbers);
}
