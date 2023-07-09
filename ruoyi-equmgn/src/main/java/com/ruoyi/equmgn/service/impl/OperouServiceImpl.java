package com.ruoyi.equmgn.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.equmgn.mapper.OperouMapper;
import com.ruoyi.equmgn.domain.Operou;
import com.ruoyi.equmgn.service.IOperouService;

/**
 * 工序路线Service业务层处理
 * 
 * @author jiangdongyu
 * @date 2023-07-09
 */
@Service
public class OperouServiceImpl implements IOperouService 
{
    @Autowired
    private OperouMapper operouMapper;

    /**
     * 查询工序路线
     * 
     * @param operouName 工序路线主键
     * @return 工序路线
     */
    @Override
    public Operou selectOperouByOperouName(String operouName)
    {
        return operouMapper.selectOperouByOperouName(operouName);
    }

    /**
     * 查询工序路线列表
     * 
     * @param operou 工序路线
     * @return 工序路线
     */
    @Override
    public List<Operou> selectOperouList(Operou operou)
    {
        return operouMapper.selectOperouList(operou);
    }

    /**
     * 新增工序路线
     * 
     * @param operou 工序路线
     * @return 结果
     */
    @Override
    public int insertOperou(Operou operou)
    {
        return operouMapper.insertOperou(operou);
    }

    /**
     * 修改工序路线
     * 
     * @param operou 工序路线
     * @return 结果
     */
    @Override
    public int updateOperou(Operou operou)
    {
        return operouMapper.updateOperou(operou);
    }

    /**
     * 批量删除工序路线
     * 
     * @param operouNames 需要删除的工序路线主键
     * @return 结果
     */
    @Override
    public int deleteOperouByOperouNames(String[] operouNames)
    {
        return operouMapper.deleteOperouByOperouNames(operouNames);
    }

    /**
     * 删除工序路线信息
     * 
     * @param operouName 工序路线主键
     * @return 结果
     */
    @Override
    public int deleteOperouByOperouName(String operouName)
    {
        return operouMapper.deleteOperouByOperouName(operouName);
    }
}
