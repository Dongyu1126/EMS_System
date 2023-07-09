package com.ruoyi.equmgn.mapper;

import java.util.List;
import com.ruoyi.equmgn.domain.Equinfo;

/**
 * 设备信息Mapper接口
 * 
 * @author jiangdongyu
 * @date 2023-07-09
 */
public interface EquinfoMapper 
{
    /**
     * 查询设备信息
     * 
     * @param equinfoName 设备信息主键
     * @return 设备信息
     */
    public Equinfo selectEquinfoByEquinfoName(String equinfoName);

    /**
     * 查询设备信息列表
     * 
     * @param equinfo 设备信息
     * @return 设备信息集合
     */
    public List<Equinfo> selectEquinfoList(Equinfo equinfo);

    /**
     * 新增设备信息
     * 
     * @param equinfo 设备信息
     * @return 结果
     */
    public int insertEquinfo(Equinfo equinfo);

    /**
     * 修改设备信息
     * 
     * @param equinfo 设备信息
     * @return 结果
     */
    public int updateEquinfo(Equinfo equinfo);

    /**
     * 删除设备信息
     * 
     * @param equinfoName 设备信息主键
     * @return 结果
     */
    public int deleteEquinfoByEquinfoName(String equinfoName);

    /**
     * 批量删除设备信息
     * 
     * @param equinfoNames 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteEquinfoByEquinfoNames(String[] equinfoNames);
}
