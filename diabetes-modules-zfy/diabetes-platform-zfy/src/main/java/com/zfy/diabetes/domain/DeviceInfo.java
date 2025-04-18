package com.zfy.diabetes.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Data;

/**
 * 设备信息表
 * @TableName device_info
 */
@TableName(value ="device_info")
@Data
public class DeviceInfo {
    /**
     * 设备ID
     */
    @TableId(type = IdType.AUTO)
    private Long deviceId;

    /**
     * 设备名称或型号
     */
    private String deviceName;

    /**
     * 设备序列号
     */
    private String serialNumber;

    /**
     * 设备类型（如血糖仪、血压计等）
     */
    private String deviceType;

    /**
     * 制造商
     */
    private String manufacturer;

    /**
     * 生产日期
     */
    private Date manufactureDate;

    /**
     * 保修期（月）
     */
    private Integer warrantyPeriod;

    /**
     * 设备状态（如active, inactive, under_maintenance等）
     */
    private String status;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

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
        DeviceInfo other = (DeviceInfo) that;
        return (this.getDeviceId() == null ? other.getDeviceId() == null : this.getDeviceId().equals(other.getDeviceId()))
            && (this.getDeviceName() == null ? other.getDeviceName() == null : this.getDeviceName().equals(other.getDeviceName()))
            && (this.getSerialNumber() == null ? other.getSerialNumber() == null : this.getSerialNumber().equals(other.getSerialNumber()))
            && (this.getDeviceType() == null ? other.getDeviceType() == null : this.getDeviceType().equals(other.getDeviceType()))
            && (this.getManufacturer() == null ? other.getManufacturer() == null : this.getManufacturer().equals(other.getManufacturer()))
            && (this.getManufactureDate() == null ? other.getManufactureDate() == null : this.getManufactureDate().equals(other.getManufactureDate()))
            && (this.getWarrantyPeriod() == null ? other.getWarrantyPeriod() == null : this.getWarrantyPeriod().equals(other.getWarrantyPeriod()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getDeviceId() == null) ? 0 : getDeviceId().hashCode());
        result = prime * result + ((getDeviceName() == null) ? 0 : getDeviceName().hashCode());
        result = prime * result + ((getSerialNumber() == null) ? 0 : getSerialNumber().hashCode());
        result = prime * result + ((getDeviceType() == null) ? 0 : getDeviceType().hashCode());
        result = prime * result + ((getManufacturer() == null) ? 0 : getManufacturer().hashCode());
        result = prime * result + ((getManufactureDate() == null) ? 0 : getManufactureDate().hashCode());
        result = prime * result + ((getWarrantyPeriod() == null) ? 0 : getWarrantyPeriod().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", deviceId=").append(deviceId);
        sb.append(", deviceName=").append(deviceName);
        sb.append(", serialNumber=").append(serialNumber);
        sb.append(", deviceType=").append(deviceType);
        sb.append(", manufacturer=").append(manufacturer);
        sb.append(", manufactureDate=").append(manufactureDate);
        sb.append(", warrantyPeriod=").append(warrantyPeriod);
        sb.append(", status=").append(status);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
}