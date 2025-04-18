package com.zfy.diabetes.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.core.utils.DateUtils;
import com.zfy.diabetes.domain.FoodDataManagement;
import com.zfy.diabetes.service.FoodDataManagementService;
import com.zfy.diabetes.mapper.FoodDataManagementMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 16654
* @description 针对表【food_data_management(膳食数据管理表，用于存储膳食相关的信息)】的数据库操作Service实现
* @createDate 2025-04-16 15:22:25
*/
@Service
public class FoodDataManagementServiceImpl extends ServiceImpl<FoodDataManagementMapper, FoodDataManagement>
    implements FoodDataManagementService{
    @Autowired
    private FoodDataManagementMapper foodDataManagementMapper;

    /**
     * 查询膳食数据管理，用于存储膳食相关的信息
     *
     * @param foodId 膳食数据管理，用于存储膳食相关的信息主键
     * @return 膳食数据管理，用于存储膳食相关的信息
     */
    @Override
    public FoodDataManagement selectFoodDataManagementByFoodId(Long foodId)
    {
        return foodDataManagementMapper.selectFoodDataManagementByFoodId(foodId);
    }

    /**
     * 查询膳食数据管理，用于存储膳食相关的信息列表
     *
     * @param foodDataManagement 膳食数据管理，用于存储膳食相关的信息
     * @return 膳食数据管理，用于存储膳食相关的信息
     */
    @Override
    public List<FoodDataManagement> selectFoodDataManagementList(FoodDataManagement foodDataManagement)
    {
        return foodDataManagementMapper.selectFoodDataManagementList(foodDataManagement);
    }

    /**
     * 新增膳食数据管理，用于存储膳食相关的信息
     *
     * @param foodDataManagement 膳食数据管理，用于存储膳食相关的信息
     * @return 结果
     */
    @Override
    public int insertFoodDataManagement(FoodDataManagement foodDataManagement)
    {
        foodDataManagement.setCreateTime(DateUtils.getNowDate());
        return foodDataManagementMapper.insertFoodDataManagement(foodDataManagement);
    }

    /**
     * 修改膳食数据管理，用于存储膳食相关的信息
     *
     * @param foodDataManagement 膳食数据管理，用于存储膳食相关的信息
     * @return 结果
     */
    @Override
    public int updateFoodDataManagement(FoodDataManagement foodDataManagement)
    {
        foodDataManagement.setUpdateTime(DateUtils.getNowDate());
        return foodDataManagementMapper.updateFoodDataManagement(foodDataManagement);
    }

    /**
     * 批量删除膳食数据管理，用于存储膳食相关的信息
     *
     * @param foodIds 需要删除的膳食数据管理，用于存储膳食相关的信息主键
     * @return 结果
     */
    @Override
    public int deleteFoodDataManagementByFoodIds(Long[] foodIds)
    {
        return foodDataManagementMapper.deleteFoodDataManagementByFoodIds(foodIds);
    }

    /**
     * 删除膳食数据管理，用于存储膳食相关的信息信息
     *
     * @param foodId 膳食数据管理，用于存储膳食相关的信息主键
     * @return 结果
     */
    @Override
    public int deleteFoodDataManagementByFoodId(Long foodId)
    {
        return foodDataManagementMapper.deleteFoodDataManagementByFoodId(foodId);
    }
}




