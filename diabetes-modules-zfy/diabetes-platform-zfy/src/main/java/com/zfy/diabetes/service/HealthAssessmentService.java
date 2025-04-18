package com.zfy.diabetes.service;

import com.zfy.diabetes.domain.HealthAssessment;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author 16654
* @description 针对表【health_assessment(健康评估与干预记录表)】的数据库操作Service
* @createDate 2025-04-16 18:44:12
*/
public interface HealthAssessmentService extends IService<HealthAssessment> {

    /**
     * 查询健康评估与干预记录
     *
     * @param assessmentId 健康评估与干预记录主键
     * @return 健康评估与干预记录
     */
    public HealthAssessment selectHealthAssessmentByAssessmentId(Long assessmentId);

    /**
     * 查询健康评估与干预记录列表
     *
     * @param healthAssessment 健康评估与干预记录
     * @return 健康评估与干预记录集合
     */
    public List<HealthAssessment> selectHealthAssessmentList(HealthAssessment healthAssessment);

    /**
     * 新增健康评估与干预记录
     *
     * @param healthAssessment 健康评估与干预记录
     * @return 结果
     */
    public int insertHealthAssessment(HealthAssessment healthAssessment);

    /**
     * 修改健康评估与干预记录
     *
     * @param healthAssessment 健康评估与干预记录
     * @return 结果
     */
    public int updateHealthAssessment(HealthAssessment healthAssessment);

    /**
     * 批量删除健康评估与干预记录
     *
     * @param assessmentIds 需要删除的健康评估与干预记录主键集合
     * @return 结果
     */
    public int deleteHealthAssessmentByAssessmentIds(Long[] assessmentIds);

    /**
     * 删除健康评估与干预记录信息
     *
     * @param assessmentId 健康评估与干预记录主键
     * @return 结果
     */
    public int deleteHealthAssessmentByAssessmentId(Long assessmentId);

}
