package com.zfy.diabetes.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.core.utils.DateUtils;
import com.zfy.diabetes.domain.HealthAssessment;
import com.zfy.diabetes.service.HealthAssessmentService;
import com.zfy.diabetes.mapper.HealthAssessmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 16654
* @description 针对表【health_assessment(健康评估与干预记录表)】的数据库操作Service实现
* @createDate 2025-04-16 18:44:12
*/
@Service
public class HealthAssessmentServiceImpl extends ServiceImpl<HealthAssessmentMapper, HealthAssessment>
    implements HealthAssessmentService{

    @Autowired
    private HealthAssessmentMapper healthAssessmentMapper;

    /**
     * 查询健康评估与干预记录
     *
     * @param assessmentId 健康评估与干预记录主键
     * @return 健康评估与干预记录
     */
    @Override
    public HealthAssessment selectHealthAssessmentByAssessmentId(Long assessmentId)
    {
        return healthAssessmentMapper.selectHealthAssessmentByAssessmentId(assessmentId);
    }

    /**
     * 查询健康评估与干预记录列表
     *
     * @param healthAssessment 健康评估与干预记录
     * @return 健康评估与干预记录
     */
    @Override
    public List<HealthAssessment> selectHealthAssessmentList(HealthAssessment healthAssessment)
    {
        return healthAssessmentMapper.selectHealthAssessmentList(healthAssessment);
    }

    /**
     * 新增健康评估与干预记录
     *
     * @param healthAssessment 健康评估与干预记录
     * @return 结果
     */
    @Override
    public int insertHealthAssessment(HealthAssessment healthAssessment)
    {

        healthAssessment.setCreateTime(DateUtils.getNowDate());
        return healthAssessmentMapper.insertHealthAssessment(healthAssessment);
    }

    /**
     * 修改健康评估与干预记录
     *
     * @param healthAssessment 健康评估与干预记录
     * @return 结果
     */
    @Override
    public int updateHealthAssessment(HealthAssessment healthAssessment)
    {
        return healthAssessmentMapper.updateHealthAssessment(healthAssessment);
    }

    /**
     * 批量删除健康评估与干预记录
     *
     * @param assessmentIds 需要删除的健康评估与干预记录主键
     * @return 结果
     */
    @Override
    public int deleteHealthAssessmentByAssessmentIds(Long[] assessmentIds)
    {
        return healthAssessmentMapper.deleteHealthAssessmentByAssessmentIds(assessmentIds);
    }

    /**
     * 删除健康评估与干预记录信息
     *
     * @param assessmentId 健康评估与干预记录主键
     * @return 结果
     */
    @Override
    public int deleteHealthAssessmentByAssessmentId(Long assessmentId)
    {
        return healthAssessmentMapper.deleteHealthAssessmentByAssessmentId(assessmentId);
    }

}




