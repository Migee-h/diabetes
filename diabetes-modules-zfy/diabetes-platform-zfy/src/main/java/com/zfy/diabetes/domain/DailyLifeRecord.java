package com.zfy.diabetes.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.NotBlank;

/**
 * 日常生活记录表
 * @TableName daily_life_record
 */
@TableName(value ="daily_life_record")
@Data
public class DailyLifeRecord {
    /**
     * 记录ID
     */
    @TableId(type = IdType.AUTO)
    private Long recordId;

    /**
     * 患者ID
     */
    private Long patientId;

    /**
     * 记录日期
     */
    private Date recordDate;

    /**
     * 运动量记录
     */
    private String exerciseRecord;

    /**
     * 每日饮食记录
     */
    private String dietRecord;

    /**
     * 口服药记录
     */
    private String oralMedicationRecord;

    /**
     * 胰岛素记录
     */
    private String insulinRecord;

    /**
     * 血糖记录
     */
    private String bloodSugarRecord;

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
        DailyLifeRecord other = (DailyLifeRecord) that;
        return (this.getRecordId() == null ? other.getRecordId() == null : this.getRecordId().equals(other.getRecordId()))
            && (this.getPatientId() == null ? other.getPatientId() == null : this.getPatientId().equals(other.getPatientId()))
            && (this.getRecordDate() == null ? other.getRecordDate() == null : this.getRecordDate().equals(other.getRecordDate()))
            && (this.getExerciseRecord() == null ? other.getExerciseRecord() == null : this.getExerciseRecord().equals(other.getExerciseRecord()))
            && (this.getDietRecord() == null ? other.getDietRecord() == null : this.getDietRecord().equals(other.getDietRecord()))
            && (this.getOralMedicationRecord() == null ? other.getOralMedicationRecord() == null : this.getOralMedicationRecord().equals(other.getOralMedicationRecord()))
            && (this.getInsulinRecord() == null ? other.getInsulinRecord() == null : this.getInsulinRecord().equals(other.getInsulinRecord()))
            && (this.getBloodSugarRecord() == null ? other.getBloodSugarRecord() == null : this.getBloodSugarRecord().equals(other.getBloodSugarRecord()))
            && (this.getCreatedBy() == null ? other.getCreatedBy() == null : this.getCreatedBy().equals(other.getCreatedBy()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getRecordId() == null) ? 0 : getRecordId().hashCode());
        result = prime * result + ((getPatientId() == null) ? 0 : getPatientId().hashCode());
        result = prime * result + ((getRecordDate() == null) ? 0 : getRecordDate().hashCode());
        result = prime * result + ((getExerciseRecord() == null) ? 0 : getExerciseRecord().hashCode());
        result = prime * result + ((getDietRecord() == null) ? 0 : getDietRecord().hashCode());
        result = prime * result + ((getOralMedicationRecord() == null) ? 0 : getOralMedicationRecord().hashCode());
        result = prime * result + ((getInsulinRecord() == null) ? 0 : getInsulinRecord().hashCode());
        result = prime * result + ((getBloodSugarRecord() == null) ? 0 : getBloodSugarRecord().hashCode());
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
        sb.append(", recordId=").append(recordId);
        sb.append(", patientId=").append(patientId);
        sb.append(", recordDate=").append(recordDate);
        sb.append(", exerciseRecord=").append(exerciseRecord);
        sb.append(", dietRecord=").append(dietRecord);
        sb.append(", oralMedicationRecord=").append(oralMedicationRecord);
        sb.append(", insulinRecord=").append(insulinRecord);
        sb.append(", bloodSugarRecord=").append(bloodSugarRecord);
        sb.append(", createdBy=").append(createdBy);
        sb.append(", createTime=").append(createTime);
        sb.append("]");
        return sb.toString();
    }
}
