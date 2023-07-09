package com.ruoyi.equmgn.mapper;

import java.util.List;
import com.ruoyi.equmgn.domain.Itemcaf;

/**
 * 物料分类Mapper接口
 * 
 * @author jiangdongyu
 * @date 2023-07-09
 */
public interface ItemcafMapper 
{
    /**
     * 查询物料分类
     * 
     * @param itemcafName 物料分类主键
     * @return 物料分类
     */
    public Itemcaf selectItemcafByItemcafName(String itemcafName);

    /**
     * 查询物料分类列表
     * 
     * @param itemcaf 物料分类
     * @return 物料分类集合
     */
    public List<Itemcaf> selectItemcafList(Itemcaf itemcaf);

    /**
     * 新增物料分类
     * 
     * @param itemcaf 物料分类
     * @return 结果
     */
    public int insertItemcaf(Itemcaf itemcaf);

    /**
     * 修改物料分类
     * 
     * @param itemcaf 物料分类
     * @return 结果
     */
    public int updateItemcaf(Itemcaf itemcaf);

    /**
     * 删除物料分类
     * 
     * @param itemcafName 物料分类主键
     * @return 结果
     */
    public int deleteItemcafByItemcafName(String itemcafName);

    /**
     * 批量删除物料分类
     * 
     * @param itemcafNames 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteItemcafByItemcafNames(String[] itemcafNames);
}
