package com.zfy.diabetes.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * 血糖数据表
 * @TableName blood_sugar_data
 */
@TableName(value ="blood_sugar_data")
@Data
public class BloodSugarData {
    /**
     * 数据ID
     */
    @TableId(type = IdType.AUTO)
    private Long dataId;

    /**
     * 患者ID
     */
    private Long patientId;

    /**
     * 患者姓名
     */
    private String patientName;

    /**
     * 记录日期
     */
    private Date recordDate;

    /**
     * 血糖值
     */
    private BigDecimal sugarValue;

    /**
     * 上传类型（自动/手动）
     */
    private String uploadType;

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
        BloodSugarData other = (BloodSugarData) that;
        return (this.getDataId() == null ? other.getDataId() == null : this.getDataId().equals(other.getDataId()))
            && (this.getPatientId() == null ? other.getPatientId() == null : this.getPatientId().equals(other.getPatientId()))
            && (this.getRecordDate() == null ? other.getRecordDate() == null : this.getRecordDate().equals(other.getRecordDate()))
            && (this.getSugarValue() == null ? other.getSugarValue() == null : this.getSugarValue().equals(other.getSugarValue()))
            && (this.getUploadType() == null ? other.getUploadType() == null : this.getUploadType().equals(other.getUploadType()))
            && (this.getCreatedBy() == null ? other.getCreatedBy() == null : this.getCreatedBy().equals(other.getCreatedBy()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getDataId() == null) ? 0 : getDataId().hashCode());
        result = prime * result + ((getPatientId() == null) ? 0 : getPatientId().hashCode());
        result = prime * result + ((getRecordDate() == null) ? 0 : getRecordDate().hashCode());
        result = prime * result + ((getSugarValue() == null) ? 0 : getSugarValue().hashCode());
        result = prime * result + ((getUploadType() == null) ? 0 : getUploadType().hashCode());
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
        sb.append(", dataId=").append(dataId);
        sb.append(", patientId=").append(patientId);
        sb.append(", recordDate=").append(recordDate);
        sb.append(", sugarValue=").append(sugarValue);
        sb.append(", uploadType=").append(uploadType);
        sb.append(", createdBy=").append(createdBy);
        sb.append(", createTime=").append(createTime);
        sb.append("]");
        return sb.toString();
    }
}
