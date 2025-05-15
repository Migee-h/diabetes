package com.zfy.diabetes.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zfy.diabetes.domain.EducationalResources;
import com.zfy.diabetes.service.EducationalResourcesService;
import com.zfy.diabetes.mapper.EducationalResourcesMapper;
import org.springframework.stereotype.Service;
import java.util.List;
import com.ruoyi.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
* @author 16654
* @description 针对表【educational_resources(教育资源管理表)】的数据库操作Service实现
* @createDate 2025-05-06 14:06:53
*/
@Service
public class EducationalResourcesServiceImpl extends ServiceImpl<EducationalResourcesMapper, EducationalResources>
    implements EducationalResourcesService{

    @Autowired
    private EducationalResourcesMapper educationalResourcesMapper;

    /**
     * 查询教育资源管理
     *
     * @param resourceId 教育资源管理主键
     * @return 教育资源管理
     */
    @Override
    public EducationalResources selectEducationalResourcesByResourceId(Long resourceId)
    {
        return educationalResourcesMapper.selectEducationalResourcesByResourceId(resourceId);
    }

    /**
     * 查询教育资源管理列表
     *
     * @param educationalResources 教育资源管理
     * @return 教育资源管理
     */
    @Override
    public List<EducationalResources> selectEducationalResourcesList(EducationalResources educationalResources)
    {
        return educationalResourcesMapper.selectEducationalResourcesList(educationalResources);
    }

    /**
     * 新增教育资源管理
     *
     * @param educationalResources 教育资源管理
     * @return 结果
     */
    @Override
    public int insertEducationalResources(EducationalResources educationalResources)
    {
        educationalResources.setCreateTime(DateUtils.getNowDate());
        return educationalResourcesMapper.insertEducationalResources(educationalResources);
    }

    /**
     * 修改教育资源管理
     *
     * @param educationalResources 教育资源管理
     * @return 结果
     */
    @Override
    public int updateEducationalResources(EducationalResources educationalResources)
    {
        educationalResources.setUpdateTime(DateUtils.getNowDate());
        return educationalResourcesMapper.updateEducationalResources(educationalResources);
    }

    /**
     * 批量删除教育资源管理
     *
     * @param resourceIds 需要删除的教育资源管理主键
     * @return 结果
     */
    @Override
    public int deleteEducationalResourcesByResourceIds(Long[] resourceIds)
    {
        return educationalResourcesMapper.deleteEducationalResourcesByResourceIds(resourceIds);
    }

    /**
     * 删除教育资源管理信息
     *
     * @param resourceId 教育资源管理主键
     * @return 结果
     */
    @Override
    public int deleteEducationalResourcesByResourceId(Long resourceId)
    {
        return educationalResourcesMapper.deleteEducationalResourcesByResourceId(resourceId);
    }

}




