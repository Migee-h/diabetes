package com.zfy.diabetes.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.core.utils.DateUtils;
import com.zfy.diabetes.domain.BloodSugarData;
import com.zfy.diabetes.domain.vo.BloodSugarStatsVO;
import com.zfy.diabetes.service.BloodSugarDataService;
import com.zfy.diabetes.mapper.BloodSugarDataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

/**
* @author 16654
* @description 针对表【blood_sugar_data(血糖数据表)】的数据库操作Service实现
* @createDate 2025-04-17 15:56:44
*/
@Service
public class BloodSugarDataServiceImpl extends ServiceImpl<BloodSugarDataMapper, BloodSugarData>
    implements BloodSugarDataService{

    @Autowired
    private BloodSugarDataMapper bloodSugarDataMapper;

    /**
     * 查询血糖数据
     *
     * @param dataId 血糖数据主键
     * @return 血糖数据
     */
    @Override
    public BloodSugarData selectBloodSugarDataByDataId(Long dataId)
    {
        return bloodSugarDataMapper.selectBloodSugarDataByDataId(dataId);
    }

    /**
     * 查询血糖数据列表
     *
     * @param bloodSugarData 血糖数据
     * @return 血糖数据
     */
    @Override
    public List<BloodSugarData> selectBloodSugarDataList(BloodSugarData bloodSugarData)
    {
        return bloodSugarDataMapper.selectBloodSugarDataList(bloodSugarData);
    }

    /**
     * 新增血糖数据
     *
     * @param bloodSugarData 血糖数据
     * @return 结果
     */
    @Override
    public int insertBloodSugarData(BloodSugarData bloodSugarData)
    {
        bloodSugarData.setCreateTime(DateUtils.getNowDate());
        return bloodSugarDataMapper.insertBloodSugarData(bloodSugarData);
    }

    /**
     * 修改血糖数据
     *
     * @param bloodSugarData 血糖数据
     * @return 结果
     */
    @Override
    public int updateBloodSugarData(BloodSugarData bloodSugarData)
    {
        return bloodSugarDataMapper.updateBloodSugarData(bloodSugarData);
    }

    /**
     * 批量删除血糖数据
     *
     * @param dataIds 需要删除的血糖数据主键
     * @return 结果
     */
    @Override
    public int deleteBloodSugarDataByDataIds(Long[] dataIds)
    {
        return baseMapper.deleteBatchIds(Arrays.asList(dataIds));
    }

    @Override
    public BloodSugarStatsVO getBloodSugarStats(Long patientId) {
        // 获取当前日期
        LocalDate endDate = LocalDate.now();
        LocalDate startDate = endDate.minusDays(14); // 获取15天前的日期

        // 构建查询条件
        List<BloodSugarData> bloodSugarList = this.lambdaQuery()
                .eq(BloodSugarData::getPatientId, patientId)
                .ge(BloodSugarData::getRecordDate, Date.from(startDate.atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .le(BloodSugarData::getRecordDate, Date.from(endDate.plusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .orderByAsc(BloodSugarData::getRecordDate)
                .list();

        BloodSugarStatsVO statsVO = new BloodSugarStatsVO();
        statsVO.setPatientId(patientId);
        
        if (bloodSugarList.isEmpty()) {
            return statsVO;
        }

        // 设置患者姓名
        statsVO.setPatientName(bloodSugarList.get(0).getPatientName());

        // 计算平均血糖值
        BigDecimal totalSugar = bloodSugarList.stream()
                .map(BloodSugarData::getSugarValue)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal averageSugar = totalSugar.divide(new BigDecimal(bloodSugarList.size()), 2, BigDecimal.ROUND_HALF_UP);
        statsVO.setAverageSugarValue(averageSugar);

        // 计算每日血糖值列表
        List<BigDecimal> dailyValues = bloodSugarList.stream()
                .map(BloodSugarData::getSugarValue)
                .collect(Collectors.toList());
        statsVO.setDailySugarValues(dailyValues);

        // 计算每日达标率
        Map<String, List<BloodSugarData>> dailyDataMap = bloodSugarList.stream()
                .collect(Collectors.groupingBy(data -> 
                    new Date(data.getRecordDate().getTime()).toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDate().toString()));

        Map<String, BigDecimal> dailyQualifiedStats = new HashMap<>();
        int totalQualified = 0;
        int totalDays = dailyDataMap.size();

        for (Map.Entry<String, List<BloodSugarData>> entry : dailyDataMap.entrySet()) {
            List<BloodSugarData> dayData = entry.getValue();
            long qualifiedCount = dayData.stream()
                    .filter(data -> isQualified(data.getSugarValue()))
                    .count();
            BigDecimal dayRate = new BigDecimal(qualifiedCount)
                    .divide(new BigDecimal(dayData.size()), 2, BigDecimal.ROUND_HALF_UP)
                    .multiply(new BigDecimal(100));
            dailyQualifiedStats.put(entry.getKey(), dayRate);
            
            if (qualifiedCount == dayData.size()) {
                totalQualified++;
            }
        }

        statsVO.setDailyQualifiedStats(dailyQualifiedStats);
        
        // 计算总体达标率
        BigDecimal totalQualifiedRate = new BigDecimal(totalQualified)
                .divide(new BigDecimal(totalDays), 2, BigDecimal.ROUND_HALF_UP)
                .multiply(new BigDecimal(100));
        statsVO.setDailyQualifiedRate(totalQualifiedRate);

        // 检查是否需要警告
        boolean hasWarning = bloodSugarList.stream()
                .anyMatch(data -> !isQualified(data.getSugarValue()));
        statsVO.setHasWarning(hasWarning);
        
        if (hasWarning) {
            statsVO.setWarningMessage("存在血糖值异常，请注意监测和调整！");
        }

        return statsVO;
    }

    /**
     * 判断血糖值是否达标
     * 正常血糖范围：空腹3.9-6.1 mmol/L，餐后2小时<7.8 mmol/L
     */
    private boolean isQualified(BigDecimal sugarValue) {
        // 这里使用一个简化的判断标准，实际应用中可能需要根据具体情况（空腹/餐后）来判断
        return sugarValue.compareTo(new BigDecimal("3.9")) >= 0 
                && sugarValue.compareTo(new BigDecimal("7.8")) <= 0;
    }

    /**
     * 删除血糖数据信息
     *
     * @param dataId 血糖数据主键
     * @return 结果
     */
    @Override
    public int deleteBloodSugarDataByDataId(Long dataId)
    {
        return bloodSugarDataMapper.deleteBloodSugarDataByDataId(dataId);
    }

}




