package com.ruoyi.equmgn.mapper;

import java.util.List;
import com.ruoyi.equmgn.domain.Reprecords;

/**
 * 报工记录Mapper接口
 * 
 * @author jiangdongyu
 * @date 2023-07-13
 */
public interface ReprecordsMapper 
{
    /**
     * 查询报工记录
     * 
     * @param reprecordsTicketNumber 报工记录主键
     * @return 报工记录
     */
    public Reprecords selectReprecordsByReprecordsTicketNumber(String reprecordsTicketNumber);

    /**
     * 查询报工记录列表
     * 
     * @param reprecords 报工记录
     * @return 报工记录集合
     */
    public List<Reprecords> selectReprecordsList(Reprecords reprecords);

    /**
     * 新增报工记录
     * 
     * @param reprecords 报工记录
     * @return 结果
     */
    public int insertReprecords(Reprecords reprecords);

    /**
     * 修改报工记录
     * 
     * @param reprecords 报工记录
     * @return 结果
     */
    public int updateReprecords(Reprecords reprecords);

    /**
     * 删除报工记录
     * 
     * @param reprecordsTicketNumber 报工记录主键
     * @return 结果
     */
    public int deleteReprecordsByReprecordsTicketNumber(String reprecordsTicketNumber);

    /**
     * 批量删除报工记录
     * 
     * @param reprecordsTicketNumbers 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteReprecordsByReprecordsTicketNumbers(String[] reprecordsTicketNumbers);
}
