package com.ruoyi.equmgn.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.equmgn.mapper.ReprecordsMapper;
import com.ruoyi.equmgn.domain.Reprecords;
import com.ruoyi.equmgn.service.IReprecordsService;

/**
 * 报工记录Service业务层处理
 * 
 * @author jiangdongyu
 * @date 2023-07-13
 */
@Service
public class ReprecordsServiceImpl implements IReprecordsService 
{
    @Autowired
    private ReprecordsMapper reprecordsMapper;

    /**
     * 查询报工记录
     * 
     * @param reprecordsTicketNumber 报工记录主键
     * @return 报工记录
     */
    @Override
    public Reprecords selectReprecordsByReprecordsTicketNumber(String reprecordsTicketNumber)
    {
        return reprecordsMapper.selectReprecordsByReprecordsTicketNumber(reprecordsTicketNumber);
    }

    /**
     * 查询报工记录列表
     * 
     * @param reprecords 报工记录
     * @return 报工记录
     */
    @Override
    public List<Reprecords> selectReprecordsList(Reprecords reprecords)
    {
        return reprecordsMapper.selectReprecordsList(reprecords);
    }

    /**
     * 新增报工记录
     * 
     * @param reprecords 报工记录
     * @return 结果
     */
    @Override
    public int insertReprecords(Reprecords reprecords)
    {
        return reprecordsMapper.insertReprecords(reprecords);
    }

    /**
     * 修改报工记录
     * 
     * @param reprecords 报工记录
     * @return 结果
     */
    @Override
    public int updateReprecords(Reprecords reprecords)
    {
        return reprecordsMapper.updateReprecords(reprecords);
    }

    /**
     * 批量删除报工记录
     * 
     * @param reprecordsTicketNumbers 需要删除的报工记录主键
     * @return 结果
     */
    @Override
    public int deleteReprecordsByReprecordsTicketNumbers(String[] reprecordsTicketNumbers)
    {
        return reprecordsMapper.deleteReprecordsByReprecordsTicketNumbers(reprecordsTicketNumbers);
    }

    /**
     * 删除报工记录信息
     * 
     * @param reprecordsTicketNumber 报工记录主键
     * @return 结果
     */
    @Override
    public int deleteReprecordsByReprecordsTicketNumber(String reprecordsTicketNumber)
    {
        return reprecordsMapper.deleteReprecordsByReprecordsTicketNumber(reprecordsTicketNumber);
    }
}
