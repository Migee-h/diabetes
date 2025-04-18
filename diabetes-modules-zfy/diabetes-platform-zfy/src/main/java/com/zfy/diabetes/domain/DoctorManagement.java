package com.zfy.diabetes.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Data;

/**
 * 医生管理信息表
 * @TableName doctor_management
 */
@TableName(value ="doctor_management")
@Data
public class DoctorManagement {
    /**
     * 医生ID
     */
    @TableId(type = IdType.AUTO)
    private Long doctorId;

    /**
     * 用户ID，关联sys_user表中的user_id
     */
    private Long userId;

    /**
     * 角色ID，关联sys_role表中的role_id
     */
    private Long roleId;

    /**
     * 医生姓名
     */
    private String doctorName;

    /**
     * 专长领域
     */
    private String specialty;

    /**
     * 资格证书
     */
    private String qualification;

    /**
     * 所在医院
     */
    private String hospital;

    /**
     * 所在科室
     */
    private String department;

    /**
     * 医生联系电话
     */
    private String phoneNumber;

    /**
     * 医生邮箱
     */
    private String email;

    /**
     * 医生状态（0正常 1停用）
     */
    private String status;

    /**
     * 创建者
     */
    private String createBy;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新者
     */
    private String updateBy;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 备注
     */
    private String remark;

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
        DoctorManagement other = (DoctorManagement) that;
        return (this.getDoctorId() == null ? other.getDoctorId() == null : this.getDoctorId().equals(other.getDoctorId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getRoleId() == null ? other.getRoleId() == null : this.getRoleId().equals(other.getRoleId()))
            && (this.getDoctorName() == null ? other.getDoctorName() == null : this.getDoctorName().equals(other.getDoctorName()))
            && (this.getSpecialty() == null ? other.getSpecialty() == null : this.getSpecialty().equals(other.getSpecialty()))
            && (this.getQualification() == null ? other.getQualification() == null : this.getQualification().equals(other.getQualification()))
            && (this.getHospital() == null ? other.getHospital() == null : this.getHospital().equals(other.getHospital()))
            && (this.getDepartment() == null ? other.getDepartment() == null : this.getDepartment().equals(other.getDepartment()))
            && (this.getPhoneNumber() == null ? other.getPhoneNumber() == null : this.getPhoneNumber().equals(other.getPhoneNumber()))
            && (this.getEmail() == null ? other.getEmail() == null : this.getEmail().equals(other.getEmail()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getCreateBy() == null ? other.getCreateBy() == null : this.getCreateBy().equals(other.getCreateBy()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateBy() == null ? other.getUpdateBy() == null : this.getUpdateBy().equals(other.getUpdateBy()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getDoctorId() == null) ? 0 : getDoctorId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getRoleId() == null) ? 0 : getRoleId().hashCode());
        result = prime * result + ((getDoctorName() == null) ? 0 : getDoctorName().hashCode());
        result = prime * result + ((getSpecialty() == null) ? 0 : getSpecialty().hashCode());
        result = prime * result + ((getQualification() == null) ? 0 : getQualification().hashCode());
        result = prime * result + ((getHospital() == null) ? 0 : getHospital().hashCode());
        result = prime * result + ((getDepartment() == null) ? 0 : getDepartment().hashCode());
        result = prime * result + ((getPhoneNumber() == null) ? 0 : getPhoneNumber().hashCode());
        result = prime * result + ((getEmail() == null) ? 0 : getEmail().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getCreateBy() == null) ? 0 : getCreateBy().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateBy() == null) ? 0 : getUpdateBy().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", doctorId=").append(doctorId);
        sb.append(", userId=").append(userId);
        sb.append(", roleId=").append(roleId);
        sb.append(", doctorName=").append(doctorName);
        sb.append(", specialty=").append(specialty);
        sb.append(", qualification=").append(qualification);
        sb.append(", hospital=").append(hospital);
        sb.append(", department=").append(department);
        sb.append(", phoneNumber=").append(phoneNumber);
        sb.append(", email=").append(email);
        sb.append(", status=").append(status);
        sb.append(", createBy=").append(createBy);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateBy=").append(updateBy);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", remark=").append(remark);
        sb.append("]");
        return sb.toString();
    }
}
