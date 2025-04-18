package com.zfy.diabetes.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Data;

/**
 * 健康评估与干预记录表
 * @TableName health_assessment
 */
@TableName(value ="health_assessment")
@Data
public class HealthAssessment {
    /**
     * 评估记录ID
     */
    @TableId(type = IdType.AUTO)
    private Long assessmentId;

    /**
     * 患者ID
     */
    private Long patientId;

    /**
     * 患者姓名
     */
    private String patientName;

    /**
     * 评估日期
     */
    private Date assessmentDate;

    /**
     * 评估结果
     */
    private String assessmentResult;

    /**
     * 干预计划
     */
    private String interventionPlan;

    /**
     * 创建者
     */
    private String createdBy;

    /**
     * 创建时间
     */
    private Date createTime;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        HealthAssessment other = (HealthAssessment) that;
        return (this.getAssessmentId() == null ? other.getAssessmentId() == null : this.getAssessmentId().equals(other.getAssessmentId()))
            && (this.getPatientId() == null ? other.getPatientId() == null : this.getPatientId().equals(other.getPatientId()))
            && (this.getAssessmentDate() == null ? other.getAssessmentDate() == null : this.getAssessmentDate().equals(other.getAssessmentDate()))
            && (this.getAssessmentResult() == null ? other.getAssessmentResult() == null : this.getAssessmentResult().equals(other.getAssessmentResult()))
            && (this.getInterventionPlan() == null ? other.getInterventionPlan() == null : this.getInterventionPlan().equals(other.getInterventionPlan()))
            && (this.getCreatedBy() == null ? other.getCreatedBy() == null : this.getCreatedBy().equals(other.getCreatedBy()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getAssessmentId() == null) ? 0 : getAssessmentId().hashCode());
        result = prime * result + ((getPatientId() == null) ? 0 : getPatientId().hashCode());
        result = prime * result + ((getAssessmentDate() == null) ? 0 : getAssessmentDate().hashCode());
        result = prime * result + ((getAssessmentResult() == null) ? 0 : getAssessmentResult().hashCode());
        result = prime * result + ((getInterventionPlan() == null) ? 0 : getInterventionPlan().hashCode());
        result = prime * result + ((getCreatedBy() == null) ? 0 : getCreatedBy().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", assessmentId=").append(assessmentId);
        sb.append(", patientId=").append(patientId);
        sb.append(", assessmentDate=").append(assessmentDate);
        sb.append(", assessmentResult=").append(assessmentResult);
        sb.append(", interventionPlan=").append(interventionPlan);
        sb.append(", createdBy=").append(createdBy);
        sb.append(", createTime=").append(createTime);
        sb.append("]");
        return sb.toString();
    }
}
