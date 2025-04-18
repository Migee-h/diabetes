package com.zfy.diabetes.service;

import com.zfy.diabetes.domain.BloodSugarData;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zfy.diabetes.domain.vo.BloodSugarStatsVO;

import java.util.List;

/**
* @author 16654
* @description 针对表【blood_sugar_data(血糖数据表)】的数据库操作Service
* @createDate 2025-04-17 15:56:44
*/
public interface BloodSugarDataService extends IService<BloodSugarData> {

    /**
     * 查询血糖数据
     *
     * @param dataId 血糖数据主键
     * @return 血糖数据
     */
    public BloodSugarData selectBloodSugarDataByDataId(Long dataId);

    /**
     * 查询血糖数据列表
     *
     * @param bloodSugarData 血糖数据
     * @return 血糖数据集合
     */
    public List<BloodSugarData> selectBloodSugarDataList(BloodSugarData bloodSugarData);

    /**
     * 新增血糖数据
     *
     * @param bloodSugarData 血糖数据
     * @return 结果
     */
    public int insertBloodSugarData(BloodSugarData bloodSugarData);

    /**
     * 修改血糖数据
     *
     * @param bloodSugarData 血糖数据
     * @return 结果
     */
    public int updateBloodSugarData(BloodSugarData bloodSugarData);

    /**
     * 批量删除血糖数据
     *
     * @param dataIds 需要删除的血糖数据主键集合
     * @return 结果
     */
    public int deleteBloodSugarDataByDataIds(Long[] dataIds);

    /**
     * 获取患者血糖统计数据
     * 
     * @param patientId 患者ID
     * @return 血糖统计数据
     */
    BloodSugarStatsVO getBloodSugarStats(Long patientId);

    /**
     * 删除血糖数据信息
     *
     * @param dataId 血糖数据主键
     * @return 结果
     */
    public int deleteBloodSugarDataByDataId(Long dataId);

}
