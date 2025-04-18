package com.zfy.diabetes.service;

import com.zfy.diabetes.domain.DailyLifeRecord;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author 16654
* @description 针对表【daily_life_record(日常生活记录表)】的数据库操作Service
* @createDate 2025-04-16 23:43:33
*/
public interface DailyLifeRecordService extends IService<DailyLifeRecord> {

    /**
     * 查询日常生活记录
     *
     * @param recordId 日常生活记录主键
     * @return 日常生活记录
     */
    public DailyLifeRecord selectDailyLifeRecordByRecordId(Long recordId);

    /**
     * 查询日常生活记录列表
     *
     * @param dailyLifeRecord 日常生活记录
     * @return 日常生活记录集合
     */
    public List<DailyLifeRecord> selectDailyLifeRecordList(DailyLifeRecord dailyLifeRecord);

    /**
     * 新增日常生活记录
     *
     * @param dailyLifeRecord 日常生活记录
     * @return 结果
     */
    public int insertDailyLifeRecord(DailyLifeRecord dailyLifeRecord);

    /**
     * 修改日常生活记录
     *
     * @param dailyLifeRecord 日常生活记录
     * @return 结果
     */
    public int updateDailyLifeRecord(DailyLifeRecord dailyLifeRecord);

    /**
     * 批量删除日常生活记录
     *
     * @param recordIds 需要删除的日常生活记录主键集合
     * @return 结果
     */
    public int deleteDailyLifeRecordByRecordIds(Long[] recordIds);

    /**
     * 删除日常生活记录信息
     *
     * @param recordId 日常生活记录主键
     * @return 结果
     */
    public int deleteDailyLifeRecordByRecordId(Long recordId);

}
