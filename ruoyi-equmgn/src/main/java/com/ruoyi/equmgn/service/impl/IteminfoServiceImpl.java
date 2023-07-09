package com.ruoyi.equmgn.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.equmgn.mapper.IteminfoMapper;
import com.ruoyi.equmgn.domain.Iteminfo;
import com.ruoyi.equmgn.service.IIteminfoService;

/**
 * 物料信息Service业务层处理
 * 
 * @author jiangdongyu
 * @date 2023-07-09
 */
@Service
public class IteminfoServiceImpl implements IIteminfoService 
{
    @Autowired
    private IteminfoMapper iteminfoMapper;

    /**
     * 查询物料信息
     * 
     * @param iteminfoNumber 物料信息主键
     * @return 物料信息
     */
    @Override
    public Iteminfo selectIteminfoByIteminfoNumber(Long iteminfoNumber)
    {
        return iteminfoMapper.selectIteminfoByIteminfoNumber(iteminfoNumber);
    }

    /**
     * 查询物料信息列表
     * 
     * @param iteminfo 物料信息
     * @return 物料信息
     */
    @Override
    public List<Iteminfo> selectIteminfoList(Iteminfo iteminfo)
    {
        return iteminfoMapper.selectIteminfoList(iteminfo);
    }

    /**
     * 新增物料信息
     * 
     * @param iteminfo 物料信息
     * @return 结果
     */
    @Override
    public int insertIteminfo(Iteminfo iteminfo)
    {
        return iteminfoMapper.insertIteminfo(iteminfo);
    }

    /**
     * 修改物料信息
     * 
     * @param iteminfo 物料信息
     * @return 结果
     */
    @Override
    public int updateIteminfo(Iteminfo iteminfo)
    {
        return iteminfoMapper.updateIteminfo(iteminfo);
    }

    /**
     * 批量删除物料信息
     * 
     * @param iteminfoNumbers 需要删除的物料信息主键
     * @return 结果
     */
    @Override
    public int deleteIteminfoByIteminfoNumbers(Long[] iteminfoNumbers)
    {
        return iteminfoMapper.deleteIteminfoByIteminfoNumbers(iteminfoNumbers);
    }

    /**
     * 删除物料信息信息
     * 
     * @param iteminfoNumber 物料信息主键
     * @return 结果
     */
    @Override
    public int deleteIteminfoByIteminfoNumber(Long iteminfoNumber)
    {
        return iteminfoMapper.deleteIteminfoByIteminfoNumber(iteminfoNumber);
    }
}
