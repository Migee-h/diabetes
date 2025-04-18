package com.zfy.diabetes.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.core.utils.DateUtils;
import com.zfy.diabetes.domain.DailyLifeRecord;
import com.zfy.diabetes.service.DailyLifeRecordService;
import com.zfy.diabetes.mapper.DailyLifeRecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;

/**
 * @author 16654
 * @description 针对表【daily_life_record(日常生活记录表)】的数据库操作Service实现
 * @createDate 2025-04-16 23:43:33
 */
@Service
public class DailyLifeRecordServiceImpl extends ServiceImpl<DailyLifeRecordMapper, DailyLifeRecord>
        implements DailyLifeRecordService {

    private static final Logger logger = LoggerFactory.getLogger(DailyLifeRecordServiceImpl.class);

    @Autowired
    private DailyLifeRecordMapper dailyLifeRecordMapper;

    /**
     * 查询日常生活记录
     *
     * @param recordId 日常生活记录主键
     * @return 日常生活记录
     */
    @Override
    public DailyLifeRecord selectDailyLifeRecordByRecordId(Long recordId) {
        logger.info("查询日常生活记录，recordId: {}", recordId);
        if (recordId == null) {
            logger.warn("查询日常生活记录时，recordId为空");
            return null;
        }
        return dailyLifeRecordMapper.selectById(recordId);
    }

    /**
     * 查询日常生活记录列表
     *
     * @param dailyLifeRecord 查询条件
     * @return 日常生活记录列表
     */
    @Override
    public List<DailyLifeRecord> selectDailyLifeRecordList(DailyLifeRecord dailyLifeRecord) {
        logger.info("查询日常生活记录列表，条件: {}", dailyLifeRecord);
        QueryWrapper<DailyLifeRecord> queryWrapper = new QueryWrapper<>(dailyLifeRecord);
        return dailyLifeRecordMapper.selectList(queryWrapper);
    }

    /**
     * 新增日常生活记录
     *
     * @param dailyLifeRecord 日常生活记录
     * @return 结果
     */
    @Override
    public int insertDailyLifeRecord(DailyLifeRecord dailyLifeRecord) {
        logger.info("新增日常生活记录，数据: {}", dailyLifeRecord);
        if (dailyLifeRecord == null) {
            logger.warn("新增日常生活记录时，数据为空");
            return 0;
        }
        setCreateTime(dailyLifeRecord);
        return dailyLifeRecordMapper.insert(dailyLifeRecord);
    }

    /**
     * 修改日常生活记录
     *
     * @param dailyLifeRecord 日常生活记录
     * @return 结果
     */
    @Override
    public int updateDailyLifeRecord(DailyLifeRecord dailyLifeRecord) {
        logger.info("修改日常生活记录，数据: {}", dailyLifeRecord);
        if (dailyLifeRecord == null || dailyLifeRecord.getRecordId() == null) {
            logger.warn("修改日常生活记录时，数据或recordId为空");
            return 0;
        }
        // 保护创建时间不被修改
        DailyLifeRecord existingRecord = dailyLifeRecordMapper.selectById(dailyLifeRecord.getRecordId());
        if (existingRecord != null) {
            dailyLifeRecord.setCreateTime(existingRecord.getCreateTime());
        }
        return dailyLifeRecordMapper.updateById(dailyLifeRecord);
    }

    /**
     * 批量删除日常生活记录
     *
     * @param recordIds 需要删除的日常生活记录主键
     * @return 结果
     */
    @Override
    public int deleteDailyLifeRecordByRecordIds(Long[] recordIds) {
        if (recordIds == null || recordIds.length == 0) {
            logger.warn("批量删除日常生活记录时，传入的recordIds为空");
            return 0; // 如果传入的recordIds为空，直接返回0，避免不必要的数据库操作
        }
        logger.info("批量删除日常生活记录，recordIds: {}", recordIds);
        return dailyLifeRecordMapper.deleteDailyLifeRecordByRecordIds(recordIds);
    }

    /**
     * 删除日常生活记录信息
     *
     * @param recordId 日常生活记录主键
     * @return 结果
     */
    @Override
    public int deleteDailyLifeRecordByRecordId(Long recordId) {
        logger.info("删除日常生活记录，recordId: {}", recordId);
        if (recordId == null) {
            logger.warn("删除日常生活记录时，recordId为空");
            return 0;
        }
        return dailyLifeRecordMapper.deleteById(recordId);
    }

    /**
     * 设置创建时间
     *
     * @param dailyLifeRecord 日常生活记录
     */
    private void setCreateTime(DailyLifeRecord dailyLifeRecord) {
        dailyLifeRecord.setCreateTime(DateUtils.getNowDate());
    }

    /**
     * 分页查询日常生活记录列表
     *
     * @param page 分页对象
     * @param dailyLifeRecord 查询条件
     * @return 分页结果
     */

    public Page<DailyLifeRecord> selectDailyLifeRecordPage(Page<DailyLifeRecord> page, DailyLifeRecord dailyLifeRecord) {
        logger.info("分页查询日常生活记录列表，条件: {}, 分页信息: {}", dailyLifeRecord, page);
        QueryWrapper<DailyLifeRecord> queryWrapper = new QueryWrapper<>(dailyLifeRecord);
        return dailyLifeRecordMapper.selectPage(page, queryWrapper);
    }

    /**
     * 按时间范围查询日常生活记录列表
     *
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return 日常生活记录列表
     */

    public List<DailyLifeRecord> selectDailyLifeRecordByTimeRange(Date startTime, Date endTime) {
        logger.info("按时间范围查询日常生活记录列表，开始时间: {}, 结束时间: {}", startTime, endTime);
        if (startTime == null || endTime == null) {
            logger.warn("按时间范围查询日常生活记录时，startTime或endTime为空");
            return null;
        }
        QueryWrapper<DailyLifeRecord> queryWrapper = new QueryWrapper<>();
        queryWrapper.between("create_time", startTime, endTime);
        return dailyLifeRecordMapper.selectList(queryWrapper);
    }

    /**
     * 按患者ID查询日常生活记录列表
     *
     * @param patientId 患者ID
     * @return 日常生活记录列表
     */

    public List<DailyLifeRecord> selectDailyLifeRecordByPatientId(Long patientId) {
        logger.info("按患者ID查询日常生活记录列表，patientId: {}", patientId);
        if (patientId == null) {
            logger.warn("按患者ID查询日常生活记录时，patientId为空");
            return null;
        }
        QueryWrapper<DailyLifeRecord> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("patient_id", patientId);
        return dailyLifeRecordMapper.selectList(queryWrapper);
    }

    /**
     * 按记录日期查询日常生活记录列表
     *
     * @param recordDate 记录日期
     * @return 日常生活记录列表
     */

    public List<DailyLifeRecord> selectDailyLifeRecordByRecordDate(Date recordDate) {
        logger.info("按记录日期查询日常生活记录列表，recordDate: {}", recordDate);
        if (recordDate == null) {
            logger.warn("按记录日期查询日常生活记录时，recordDate为空");
            return null;
        }
        QueryWrapper<DailyLifeRecord> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("record_date", recordDate);
        return dailyLifeRecordMapper.selectList(queryWrapper);
    }
}
