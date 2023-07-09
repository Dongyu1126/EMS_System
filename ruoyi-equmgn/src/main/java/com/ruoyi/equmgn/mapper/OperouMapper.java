package com.ruoyi.equmgn.mapper;

import java.util.List;
import com.ruoyi.equmgn.domain.Operou;

/**
 * 工序路线Mapper接口
 * 
 * @author jiangdongyu
 * @date 2023-07-09
 */
public interface OperouMapper 
{
    /**
     * 查询工序路线
     * 
     * @param operouName 工序路线主键
     * @return 工序路线
     */
    public Operou selectOperouByOperouName(String operouName);

    /**
     * 查询工序路线列表
     * 
     * @param operou 工序路线
     * @return 工序路线集合
     */
    public List<Operou> selectOperouList(Operou operou);

    /**
     * 新增工序路线
     * 
     * @param operou 工序路线
     * @return 结果
     */
    public int insertOperou(Operou operou);

    /**
     * 修改工序路线
     * 
     * @param operou 工序路线
     * @return 结果
     */
    public int updateOperou(Operou operou);

    /**
     * 删除工序路线
     * 
     * @param operouName 工序路线主键
     * @return 结果
     */
    public int deleteOperouByOperouName(String operouName);

    /**
     * 批量删除工序路线
     * 
     * @param operouNames 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteOperouByOperouNames(String[] operouNames);
}
