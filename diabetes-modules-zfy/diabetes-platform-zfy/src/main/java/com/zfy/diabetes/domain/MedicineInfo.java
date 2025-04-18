package com.zfy.diabetes.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Data;

/**
 * 药品管理表，用于存储药品相关的信息
 * @TableName medicine_info
 */
@TableName(value ="medicine_info")
@Data
public class MedicineInfo {
    /**
     * 药品ID，主键，自增
     */
    @TableId(type = IdType.AUTO)
    private Integer medicineId;

    /**
     * 药品名称
     */
    private String medicineName;

    /**
     * 药品分类，例如：处方药、非处方药、中药等
     */
    private String category;

    /**
     * 剂量信息，例如：一次一片、每日三次等
     */
    private String dosage;

    /**
     * 用法说明，详细描述药品的使用方法
     */
    private String usage;

    /**
     * 副作用信息，描述可能的不良反应
     */
    private String sideEffects;

    /**
     * 生产厂家，记录药品的生产厂商名称
     */
    private String manufacturer;

    /**
     * 有效期，记录药品的有效期限
     */
    private Date expirationDate;

    /**
     * 组成信息，描述药品的具体成分，如活性成分及其含量
     */
    private String composition;

    /**
     * 创建时间，默认为当前时间
     */
    private Date createdAt;

    /**
     * 更新时间，默认为当前时间，自动更新
     */
    private Date updatedAt;

    /**
     * 备注信息，用于补充说明
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
        MedicineInfo other = (MedicineInfo) that;
        return (this.getMedicineId() == null ? other.getMedicineId() == null : this.getMedicineId().equals(other.getMedicineId()))
            && (this.getMedicineName() == null ? other.getMedicineName() == null : this.getMedicineName().equals(other.getMedicineName()))
            && (this.getCategory() == null ? other.getCategory() == null : this.getCategory().equals(other.getCategory()))
            && (this.getDosage() == null ? other.getDosage() == null : this.getDosage().equals(other.getDosage()))
            && (this.getUsage() == null ? other.getUsage() == null : this.getUsage().equals(other.getUsage()))
            && (this.getSideEffects() == null ? other.getSideEffects() == null : this.getSideEffects().equals(other.getSideEffects()))
            && (this.getManufacturer() == null ? other.getManufacturer() == null : this.getManufacturer().equals(other.getManufacturer()))
            && (this.getExpirationDate() == null ? other.getExpirationDate() == null : this.getExpirationDate().equals(other.getExpirationDate()))
            && (this.getComposition() == null ? other.getComposition() == null : this.getComposition().equals(other.getComposition()))
            && (this.getCreatedAt() == null ? other.getCreatedAt() == null : this.getCreatedAt().equals(other.getCreatedAt()))
            && (this.getUpdatedAt() == null ? other.getUpdatedAt() == null : this.getUpdatedAt().equals(other.getUpdatedAt()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getMedicineId() == null) ? 0 : getMedicineId().hashCode());
        result = prime * result + ((getMedicineName() == null) ? 0 : getMedicineName().hashCode());
        result = prime * result + ((getCategory() == null) ? 0 : getCategory().hashCode());
        result = prime * result + ((getDosage() == null) ? 0 : getDosage().hashCode());
        result = prime * result + ((getUsage() == null) ? 0 : getUsage().hashCode());
        result = prime * result + ((getSideEffects() == null) ? 0 : getSideEffects().hashCode());
        result = prime * result + ((getManufacturer() == null) ? 0 : getManufacturer().hashCode());
        result = prime * result + ((getExpirationDate() == null) ? 0 : getExpirationDate().hashCode());
        result = prime * result + ((getComposition() == null) ? 0 : getComposition().hashCode());
        result = prime * result + ((getCreatedAt() == null) ? 0 : getCreatedAt().hashCode());
        result = prime * result + ((getUpdatedAt() == null) ? 0 : getUpdatedAt().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", medicineId=").append(medicineId);
        sb.append(", medicineName=").append(medicineName);
        sb.append(", category=").append(category);
        sb.append(", dosage=").append(dosage);
        sb.append(", usage=").append(usage);
        sb.append(", sideEffects=").append(sideEffects);
        sb.append(", manufacturer=").append(manufacturer);
        sb.append(", expirationDate=").append(expirationDate);
        sb.append(", composition=").append(composition);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", updatedAt=").append(updatedAt);
        sb.append(", remark=").append(remark);
        sb.append("]");
        return sb.toString();
    }
}