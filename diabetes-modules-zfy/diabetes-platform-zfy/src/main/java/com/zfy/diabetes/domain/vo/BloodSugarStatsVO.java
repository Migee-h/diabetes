package com.zfy.diabetes.domain.vo;

import lombok.Data;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Data
public class BloodSugarStatsVO {
    /**
     * 患者ID
     */
    private Long patientId;

    /**
     * 患者姓名
     */
    private String patientName;

    /**
     * 15天平均血糖值
     */
    private BigDecimal averageSugarValue;

    /**
     * 每日达标率（百分比）
     */
    private BigDecimal dailyQualifiedRate;

    /**
     * 是否有异常警告
     */
    private Boolean hasWarning;

    /**
     * 警告信息
     */
    private String warningMessage;

    /**
     * 最近15天的每日血糖值
     */
    private List<BigDecimal> dailySugarValues;

    /**
     * 每日达标情况统计 key:日期 value:达标率
     */
    private Map<String, BigDecimal> dailyQualifiedStats;
}
