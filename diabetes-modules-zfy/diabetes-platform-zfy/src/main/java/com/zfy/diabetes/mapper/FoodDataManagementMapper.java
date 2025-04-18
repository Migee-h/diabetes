package com.zfy.diabetes.mapper;

import com.zfy.diabetes.domain.FoodDataManagement;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author 16654
* @description 针对表【food_data_management(膳食数据管理表，用于存储膳食相关的信息)】的数据库操作Mapper
* @createDate 2025-04-16 15:22:25
* @Entity com.zfy.diabetes.domain.FoodDataManagement
*/
public interface FoodDataManagementMapper extends BaseMapper<FoodDataManagement> {
    /**
     * 查询膳食数据管理，用于存储膳食相关的信息
     *
     * @param foodId 膳食数据管理，用于存储膳食相关的信息主键
     * @return 膳食数据管理，用于存储膳食相关的信息
     */
    public FoodDataManagement selectFoodDataManagementByFoodId(Long foodId);

    /**
     * 查询膳食数据管理，用于存储膳食相关的信息列表
     *
     * @param foodDataManagement 膳食数据管理，用于存储膳食相关的信息
     * @return 膳食数据管理，用于存储膳食相关的信息集合
     */
    public List<FoodDataManagement> selectFoodDataManagementList(FoodDataManagement foodDataManagement);

    /**
     * 新增膳食数据管理，用于存储膳食相关的信息
     *
     * @param foodDataManagement 膳食数据管理，用于存储膳食相关的信息
     * @return 结果
     */
    public int insertFoodDataManagement(FoodDataManagement foodDataManagement);

    /**
     * 修改膳食数据管理，用于存储膳食相关的信息
     *
     * @param foodDataManagement 膳食数据管理，用于存储膳食相关的信息
     * @return 结果
     */
    public int updateFoodDataManagement(FoodDataManagement foodDataManagement);

    /**
     * 删除膳食数据管理，用于存储膳食相关的信息
     *
     * @param foodId 膳食数据管理，用于存储膳食相关的信息主键
     * @return 结果
     */
    public int deleteFoodDataManagementByFoodId(Long foodId);

    /**
     * 批量删除膳食数据管理，用于存储膳食相关的信息
     *
     * @param foodIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteFoodDataManagementByFoodIds(Long[] foodIds);
}




