package com.ruoyi.equmgn.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.equmgn.mapper.ProworkordersMapper;
import com.ruoyi.equmgn.domain.Proworkorders;
import com.ruoyi.equmgn.service.IProworkordersService;

/**
 * 生产工单Service业务层处理
 * 
 * @author jiangdongyu
 * @date 2023-07-13
 */
@Service
public class ProworkordersServiceImpl implements IProworkordersService 
{
    @Autowired
    private ProworkordersMapper proworkordersMapper;

    /**
     * 查询生产工单
     * 
     * @param proworkordersProductStatus 生产工单主键
     * @return 生产工单
     */
    @Override
    public Proworkorders selectProworkordersByProworkordersProductStatus(String proworkordersProductStatus)
    {
        return proworkordersMapper.selectProworkordersByProworkordersProductStatus(proworkordersProductStatus);
    }

    /**
     * 查询生产工单列表
     * 
     * @param proworkorders 生产工单
     * @return 生产工单
     */
    @Override
    public List<Proworkorders> selectProworkordersList(Proworkorders proworkorders)
    {
        return proworkordersMapper.selectProworkordersList(proworkorders);
    }

    /**
     * 新增生产工单
     * 
     * @param proworkorders 生产工单
     * @return 结果
     */
    @Override
    public int insertProworkorders(Proworkorders proworkorders)
    {
        return proworkordersMapper.insertProworkorders(proworkorders);
    }

    /**
     * 修改生产工单
     * 
     * @param proworkorders 生产工单
     * @return 结果
     */
    @Override
    public int updateProworkorders(Proworkorders proworkorders)
    {
        return proworkordersMapper.updateProworkorders(proworkorders);
    }

    /**
     * 批量删除生产工单
     * 
     * @param proworkordersProductStatuss 需要删除的生产工单主键
     * @return 结果
     */
    @Override
    public int deleteProworkordersByProworkordersProductStatuss(String[] proworkordersProductStatuss)
    {
        return proworkordersMapper.deleteProworkordersByProworkordersProductStatuss(proworkordersProductStatuss);
    }

    /**
     * 删除生产工单信息
     * 
     * @param proworkordersProductStatus 生产工单主键
     * @return 结果
     */
    @Override
    public int deleteProworkordersByProworkordersProductStatus(String proworkordersProductStatus)
    {
        return proworkordersMapper.deleteProworkordersByProworkordersProductStatus(proworkordersProductStatus);
    }
}
