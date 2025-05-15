package com.zfy.diabetes.mapper;

import com.zfy.diabetes.domain.EducationalResources;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author 16654
* @description 针对表【educational_resources(教育资源管理表)】的数据库操作Mapper
* @createDate 2025-05-06 14:06:53
* @Entity com.zfy.diabetes.domain.EducationalResources
*/
public interface EducationalResourcesMapper extends BaseMapper<EducationalResources> {
    /**
     * 查询教育资源管理
     *
     * @param resourceId 教育资源管理主键
     * @return 教育资源管理
     */
    public EducationalResources selectEducationalResourcesByResourceId(Long resourceId);

    /**
     * 查询教育资源管理列表
     *
     * @param educationalResources 教育资源管理
     * @return 教育资源管理集合
     */
    public List<EducationalResources> selectEducationalResourcesList(EducationalResources educationalResources);

    /**
     * 新增教育资源管理
     *
     * @param educationalResources 教育资源管理
     * @return 结果
     */
    public int insertEducationalResources(EducationalResources educationalResources);

    /**
     * 修改教育资源管理
     *
     * @param educationalResources 教育资源管理
     * @return 结果
     */
    public int updateEducationalResources(EducationalResources educationalResources);

    /**
     * 删除教育资源管理
     *
     * @param resourceId 教育资源管理主键
     * @return 结果
     */
    public int deleteEducationalResourcesByResourceId(Long resourceId);

    /**
     * 批量删除教育资源管理
     *
     * @param resourceIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteEducationalResourcesByResourceIds(Long[] resourceIds);
}




