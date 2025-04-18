package com.zfy.diabetes.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Data;

/**
 * 膳食数据管理表，用于存储膳食相关的信息
 * @TableName food_data_management
 */
@TableName(value ="food_data_management")
@Data
public class FoodDataManagement {
    /**
     * 膳食ID，主键，自增
     */
    @TableId(type = IdType.AUTO)
    private Long foodId;

    /**
     * 膳食名称
     */
    private String foodName;

    /**
     * 膳食图片url
     */
    private String foodImg;

    /**
     * 膳食分类，例如：主食、蔬菜、水果等
     */
    private String category;

    /**
     * 卡路里，单位为千卡（kcal）
     */
    private Integer calories;

    /**
     * 营养素信息，JSON格式存储，包含蛋白质、脂肪、碳水化合物等
     */
    private String nutrients;

    /**
     * 组成信息，描述膳食的具体成分，如主要原料及其比例
     */
    private String composition;

    /**
     * 创建者，记录操作员的用户名或ID
     */
    private String createBy;

    /**
     * 创建时间，默认为当前时间
     */
    private Date createTime;

    /**
     * 更新时间，默认为当前时间，自动更新
     */
    private Date updateTime;

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
        FoodDataManagement other = (FoodDataManagement) that;
        return (this.getFoodId() == null ? other.getFoodId() == null : this.getFoodId().equals(other.getFoodId()))
            && (this.getFoodName() == null ? other.getFoodName() == null : this.getFoodName().equals(other.getFoodName()))
            && (this.getFoodImg() == null ? other.getFoodImg() == null : this.getFoodImg().equals(other.getFoodImg()))
            && (this.getCategory() == null ? other.getCategory() == null : this.getCategory().equals(other.getCategory()))
            && (this.getCalories() == null ? other.getCalories() == null : this.getCalories().equals(other.getCalories()))
            && (this.getNutrients() == null ? other.getNutrients() == null : this.getNutrients().equals(other.getNutrients()))
            && (this.getComposition() == null ? other.getComposition() == null : this.getComposition().equals(other.getComposition()))
            && (this.getCreateBy() == null ? other.getCreateBy() == null : this.getCreateBy().equals(other.getCreateBy()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getFoodId() == null) ? 0 : getFoodId().hashCode());
        result = prime * result + ((getFoodName() == null) ? 0 : getFoodName().hashCode());
        result = prime * result + ((getFoodImg() == null) ? 0 : getFoodImg().hashCode());
        result = prime * result + ((getCategory() == null) ? 0 : getCategory().hashCode());
        result = prime * result + ((getCalories() == null) ? 0 : getCalories().hashCode());
        result = prime * result + ((getNutrients() == null) ? 0 : getNutrients().hashCode());
        result = prime * result + ((getComposition() == null) ? 0 : getComposition().hashCode());
        result = prime * result + ((getCreateBy() == null) ? 0 : getCreateBy().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
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
        sb.append(", foodId=").append(foodId);
        sb.append(", foodName=").append(foodName);
        sb.append(", foodImg=").append(foodImg);
        sb.append(", category=").append(category);
        sb.append(", calories=").append(calories);
        sb.append(", nutrients=").append(nutrients);
        sb.append(", composition=").append(composition);
        sb.append(", createBy=").append(createBy);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", remark=").append(remark);
        sb.append("]");
        return sb.toString();
    }
}