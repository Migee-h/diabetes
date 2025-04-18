package com.zfy.diabetes.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Data;

/**
 * 患者基本信息表
 * @TableName patient
 */
@TableName(value ="patient")
@Data
public class Patient {
    /**
     * 患者编号
     */
    @TableId(type = IdType.AUTO)
    private Long patientId;

    /**
     * 患者姓名
     */
    private String patientName;

    /**
     * 绑定设备id
     */
    private Long deviceId;

    /**
     * 患者性别
     */
    private String patientSex;

    /**
     * 患者身份证号
     */
    private String patientNum;

    /**
     * 患者生日
     */
    private Date patientBirthday;

    /**
     * 患者年龄
     */
    private Integer patientAge;

    /**
     * 患者手机号
     */
    private String patientPhone;

    /**
     * 患者邮箱
     */
    private String patientEmail;

    /**
     * 患者身高
     */
    private Double patientHeight;

    /**
     * 患者体重
     */
    private Double patientWeight;

    /**
     * 患者BIM值
     */
    private Double patientBim;

    /**
     * 患者状态
     */
    private String patientStatus;

    /**
     * 患者疾病
     */
    private String disease;

    /**
     * 患者过敏史
     */
    private String allergy;

    /**
     * 省份
     */
    private String province;

    /**
     * 城市
     */
    private String city;

    /**
     * 县城
     */
    private String county;

    /**
     * 患者详细地址
     */
    private String address;

    /**
     * 近期就诊时间
     */
    private Date clinicTime;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更改时间
     */
    private Date updateTime;

    /**
     * 
     */
    private String op;

    /**
     * 创建用户ID
     */
    private Long userId;

    /**
     * 创建人姓名
     */
    private String userName;

    /**
     * 部门ID
     */
    private Long deptId;

    /**
     * 删除标志（0代表存在 2代表删除）
     */
    private String delFlag;

    /**
     * 用户名称拼音
     */
    private String pinyin;

    /**
     * 病史
     */
    private String medicalHistory;

    /**
     * 民族
     */
    private String nation;

    /**
     * 职业
     */
    private String occupation;

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
        Patient other = (Patient) that;
        return (this.getPatientId() == null ? other.getPatientId() == null : this.getPatientId().equals(other.getPatientId()))
            && (this.getPatientName() == null ? other.getPatientName() == null : this.getPatientName().equals(other.getPatientName()))
            && (this.getDeviceId() == null ? other.getDeviceId() == null : this.getDeviceId().equals(other.getDeviceId()))
            && (this.getPatientSex() == null ? other.getPatientSex() == null : this.getPatientSex().equals(other.getPatientSex()))
            && (this.getPatientNum() == null ? other.getPatientNum() == null : this.getPatientNum().equals(other.getPatientNum()))
            && (this.getPatientBirthday() == null ? other.getPatientBirthday() == null : this.getPatientBirthday().equals(other.getPatientBirthday()))
            && (this.getPatientAge() == null ? other.getPatientAge() == null : this.getPatientAge().equals(other.getPatientAge()))
            && (this.getPatientPhone() == null ? other.getPatientPhone() == null : this.getPatientPhone().equals(other.getPatientPhone()))
            && (this.getPatientEmail() == null ? other.getPatientEmail() == null : this.getPatientEmail().equals(other.getPatientEmail()))
            && (this.getPatientHeight() == null ? other.getPatientHeight() == null : this.getPatientHeight().equals(other.getPatientHeight()))
            && (this.getPatientWeight() == null ? other.getPatientWeight() == null : this.getPatientWeight().equals(other.getPatientWeight()))
            && (this.getPatientBim() == null ? other.getPatientBim() == null : this.getPatientBim().equals(other.getPatientBim()))
            && (this.getPatientStatus() == null ? other.getPatientStatus() == null : this.getPatientStatus().equals(other.getPatientStatus()))
            && (this.getDisease() == null ? other.getDisease() == null : this.getDisease().equals(other.getDisease()))
            && (this.getAllergy() == null ? other.getAllergy() == null : this.getAllergy().equals(other.getAllergy()))
            && (this.getProvince() == null ? other.getProvince() == null : this.getProvince().equals(other.getProvince()))
            && (this.getCity() == null ? other.getCity() == null : this.getCity().equals(other.getCity()))
            && (this.getCounty() == null ? other.getCounty() == null : this.getCounty().equals(other.getCounty()))
            && (this.getAddress() == null ? other.getAddress() == null : this.getAddress().equals(other.getAddress()))
            && (this.getClinicTime() == null ? other.getClinicTime() == null : this.getClinicTime().equals(other.getClinicTime()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getOp() == null ? other.getOp() == null : this.getOp().equals(other.getOp()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getUserName() == null ? other.getUserName() == null : this.getUserName().equals(other.getUserName()))
            && (this.getDeptId() == null ? other.getDeptId() == null : this.getDeptId().equals(other.getDeptId()))
            && (this.getDelFlag() == null ? other.getDelFlag() == null : this.getDelFlag().equals(other.getDelFlag()))
            && (this.getPinyin() == null ? other.getPinyin() == null : this.getPinyin().equals(other.getPinyin()))
            && (this.getMedicalHistory() == null ? other.getMedicalHistory() == null : this.getMedicalHistory().equals(other.getMedicalHistory()))
            && (this.getNation() == null ? other.getNation() == null : this.getNation().equals(other.getNation()))
            && (this.getOccupation() == null ? other.getOccupation() == null : this.getOccupation().equals(other.getOccupation()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getPatientId() == null) ? 0 : getPatientId().hashCode());
        result = prime * result + ((getPatientName() == null) ? 0 : getPatientName().hashCode());
        result = prime * result + ((getDeviceId() == null) ? 0 : getDeviceId().hashCode());
        result = prime * result + ((getPatientSex() == null) ? 0 : getPatientSex().hashCode());
        result = prime * result + ((getPatientNum() == null) ? 0 : getPatientNum().hashCode());
        result = prime * result + ((getPatientBirthday() == null) ? 0 : getPatientBirthday().hashCode());
        result = prime * result + ((getPatientAge() == null) ? 0 : getPatientAge().hashCode());
        result = prime * result + ((getPatientPhone() == null) ? 0 : getPatientPhone().hashCode());
        result = prime * result + ((getPatientEmail() == null) ? 0 : getPatientEmail().hashCode());
        result = prime * result + ((getPatientHeight() == null) ? 0 : getPatientHeight().hashCode());
        result = prime * result + ((getPatientWeight() == null) ? 0 : getPatientWeight().hashCode());
        result = prime * result + ((getPatientBim() == null) ? 0 : getPatientBim().hashCode());
        result = prime * result + ((getPatientStatus() == null) ? 0 : getPatientStatus().hashCode());
        result = prime * result + ((getDisease() == null) ? 0 : getDisease().hashCode());
        result = prime * result + ((getAllergy() == null) ? 0 : getAllergy().hashCode());
        result = prime * result + ((getProvince() == null) ? 0 : getProvince().hashCode());
        result = prime * result + ((getCity() == null) ? 0 : getCity().hashCode());
        result = prime * result + ((getCounty() == null) ? 0 : getCounty().hashCode());
        result = prime * result + ((getAddress() == null) ? 0 : getAddress().hashCode());
        result = prime * result + ((getClinicTime() == null) ? 0 : getClinicTime().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getOp() == null) ? 0 : getOp().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getUserName() == null) ? 0 : getUserName().hashCode());
        result = prime * result + ((getDeptId() == null) ? 0 : getDeptId().hashCode());
        result = prime * result + ((getDelFlag() == null) ? 0 : getDelFlag().hashCode());
        result = prime * result + ((getPinyin() == null) ? 0 : getPinyin().hashCode());
        result = prime * result + ((getMedicalHistory() == null) ? 0 : getMedicalHistory().hashCode());
        result = prime * result + ((getNation() == null) ? 0 : getNation().hashCode());
        result = prime * result + ((getOccupation() == null) ? 0 : getOccupation().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", patientId=").append(patientId);
        sb.append(", patientName=").append(patientName);
        sb.append(", deviceId=").append(deviceId);
        sb.append(", patientSex=").append(patientSex);
        sb.append(", patientNum=").append(patientNum);
        sb.append(", patientBirthday=").append(patientBirthday);
        sb.append(", patientAge=").append(patientAge);
        sb.append(", patientPhone=").append(patientPhone);
        sb.append(", patientEmail=").append(patientEmail);
        sb.append(", patientHeight=").append(patientHeight);
        sb.append(", patientWeight=").append(patientWeight);
        sb.append(", patientBim=").append(patientBim);
        sb.append(", patientStatus=").append(patientStatus);
        sb.append(", disease=").append(disease);
        sb.append(", allergy=").append(allergy);
        sb.append(", province=").append(province);
        sb.append(", city=").append(city);
        sb.append(", county=").append(county);
        sb.append(", address=").append(address);
        sb.append(", clinicTime=").append(clinicTime);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", op=").append(op);
        sb.append(", userId=").append(userId);
        sb.append(", userName=").append(userName);
        sb.append(", deptId=").append(deptId);
        sb.append(", delFlag=").append(delFlag);
        sb.append(", pinyin=").append(pinyin);
        sb.append(", medicalHistory=").append(medicalHistory);
        sb.append(", nation=").append(nation);
        sb.append(", occupation=").append(occupation);
        sb.append("]");
        return sb.toString();
    }
}