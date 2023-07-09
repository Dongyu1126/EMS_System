package com.ruoyi.equmgn.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.equmgn.mapper.ItemcafMapper;
import com.ruoyi.equmgn.domain.Itemcaf;
import com.ruoyi.equmgn.service.IItemcafService;

/**
 * 物料分类Service业务层处理
 * 
 * @author jiangdongyu
 * @date 2023-07-09
 */
@Service
public class ItemcafServiceImpl implements IItemcafService 
{
    @Autowired
    private ItemcafMapper itemcafMapper;

    /**
     * 查询物料分类
     * 
     * @param itemcafName 物料分类主键
     * @return 物料分类
     */
    @Override
    public Itemcaf selectItemcafByItemcafName(String itemcafName)
    {
        return itemcafMapper.selectItemcafByItemcafName(itemcafName);
    }

    /**
     * 查询物料分类列表
     * 
     * @param itemcaf 物料分类
     * @return 物料分类
     */
    @Override
    public List<Itemcaf> selectItemcafList(Itemcaf itemcaf)
    {
        return itemcafMapper.selectItemcafList(itemcaf);
    }

    /**
     * 新增物料分类
     * 
     * @param itemcaf 物料分类
     * @return 结果
     */
    @Override
    public int insertItemcaf(Itemcaf itemcaf)
    {
        return itemcafMapper.insertItemcaf(itemcaf);
    }

    /**
     * 修改物料分类
     * 
     * @param itemcaf 物料分类
     * @return 结果
     */
    @Override
    public int updateItemcaf(Itemcaf itemcaf)
    {
        return itemcafMapper.updateItemcaf(itemcaf);
    }

    /**
     * 批量删除物料分类
     * 
     * @param itemcafNames 需要删除的物料分类主键
     * @return 结果
     */
    @Override
    public int deleteItemcafByItemcafNames(String[] itemcafNames)
    {
        return itemcafMapper.deleteItemcafByItemcafNames(itemcafNames);
    }

    /**
     * 删除物料分类信息
     * 
     * @param itemcafName 物料分类主键
     * @return 结果
     */
    @Override
    public int deleteItemcafByItemcafName(String itemcafName)
    {
        return itemcafMapper.deleteItemcafByItemcafName(itemcafName);
    }
}
