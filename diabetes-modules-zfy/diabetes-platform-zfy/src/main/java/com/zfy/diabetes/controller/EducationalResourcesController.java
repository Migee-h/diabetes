package com.zfy.diabetes.controller;


import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.zfy.diabetes.domain.EducationalResources;
import com.zfy.diabetes.service.EducationalResourcesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 教育资源管理Controller
 *
 * @author zfy
 * @date 2025-05-06
 */
@RestController
@RequestMapping("/education/resources")
public class EducationalResourcesController extends BaseController
{
    @Autowired
    private EducationalResourcesService educationalResourcesService;

    /**
     * 查询教育资源管理列表
     */
//    @RequiresPermissions("education:resources:list")
    @GetMapping("/list")
    public TableDataInfo list(EducationalResources educationalResources)
    {
        startPage();
        List<EducationalResources> list = educationalResourcesService.selectEducationalResourcesList(educationalResources);
        return getDataTable(list);
    }

    @GetMapping("/listNoPage")
    public TableDataInfo listNoPage(EducationalResources educationalResources)
    {
        List<EducationalResources> list = educationalResourcesService.selectEducationalResourcesList(educationalResources);
        return getDataTable(list);
    }

    /**
     * 导出教育资源管理列表
     */
    @RequiresPermissions("education:resources:export")
    @Log(title = "教育资源管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EducationalResources educationalResources)
    {
        List<EducationalResources> list = educationalResourcesService.selectEducationalResourcesList(educationalResources);
        ExcelUtil<EducationalResources> util = new ExcelUtil<EducationalResources>(EducationalResources.class);
        util.exportExcel(response, list, "教育资源管理数据");
    }

    /**
     * 获取教育资源管理详细信息
     */
    @RequiresPermissions("education:resources:query")
    @GetMapping(value = "/{resourceId}")
    public AjaxResult getInfo(@PathVariable("resourceId") Long resourceId)
    {
        return success(educationalResourcesService.selectEducationalResourcesByResourceId(resourceId));
    }

    /**
     * 新增教育资源管理
     */
    @RequiresPermissions("education:resources:add")
    @Log(title = "教育资源管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EducationalResources educationalResources)
    {
        return toAjax(educationalResourcesService.insertEducationalResources(educationalResources));
    }

    /**
     * 修改教育资源管理
     */
    @RequiresPermissions("education:resources:edit")
    @Log(title = "教育资源管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EducationalResources educationalResources)
    {
        return toAjax(educationalResourcesService.updateEducationalResources(educationalResources));
    }

    /**
     * 删除教育资源管理
     */
    @RequiresPermissions("education:resources:remove")
    @Log(title = "教育资源管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{resourceIds}")
    public AjaxResult remove(@PathVariable Long[] resourceIds)
    {
        return toAjax(educationalResourcesService.deleteEducationalResourcesByResourceIds(resourceIds));
    }
}

