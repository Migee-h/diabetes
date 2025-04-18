package com.zfy.diabetes.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.zfy.diabetes.domain.HealthAssessment;
import com.zfy.diabetes.service.HealthAssessmentService;
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

import static com.ruoyi.common.core.context.SecurityContextHolder.getUserName;

/**
 * 健康评估与干预记录Controller
 *
 * @author zfy
 * @date 2025-04-16
 */
@RestController
@RequestMapping("/health/assessment")
public class HealthAssessmentController extends BaseController {
    @Autowired
    private HealthAssessmentService healthAssessmentService;

    /**
     * 查询健康评估与干预记录列表
     */
    @RequiresPermissions("health:assessment:list")
    @GetMapping("/list")
    public TableDataInfo list(HealthAssessment healthAssessment) {
        startPage();
        List<HealthAssessment> list = healthAssessmentService.selectHealthAssessmentList(healthAssessment);
        return getDataTable(list);
    }

    /**
     * 导出健康评估与干预记录列表
     */
    @RequiresPermissions("health:assessment:export")
    @Log(title = "健康评估与干预记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, HealthAssessment healthAssessment) {
        List<HealthAssessment> list = healthAssessmentService.selectHealthAssessmentList(healthAssessment);
        ExcelUtil<HealthAssessment> util = new ExcelUtil<HealthAssessment>(HealthAssessment.class);
        util.exportExcel(response, list, "健康评估与干预记录数据");
    }

    /**
     * 获取健康评估与干预记录详细信息
     */
    @RequiresPermissions("health:assessment:query")
    @GetMapping(value = "/{assessmentId}")
    public AjaxResult getInfo(@PathVariable("assessmentId") Long assessmentId) {
        return success(healthAssessmentService.selectHealthAssessmentByAssessmentId(assessmentId));
    }

    /**
     * 新增健康评估与干预记录
     */
    @RequiresPermissions("health:assessment:add")
    @Log(title = "健康评估与干预记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody HealthAssessment healthAssessment) {
        String userName = getUserName();
        healthAssessment.setCreatedBy(userName);
        return toAjax(healthAssessmentService.insertHealthAssessment(healthAssessment));
    }

    /**
     * 修改健康评估与干预记录
     */
    @RequiresPermissions("health:assessment:edit")
    @Log(title = "健康评估与干预记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody HealthAssessment healthAssessment) {
        return toAjax(healthAssessmentService.updateHealthAssessment(healthAssessment));
    }

    /**
     * 删除健康评估与干预记录
     */
    @RequiresPermissions("health:assessment:remove")
    @Log(title = "健康评估与干预记录", businessType = BusinessType.DELETE)
    @DeleteMapping("/{assessmentIds}")
    public AjaxResult remove(@PathVariable Long[] assessmentIds) {
        return toAjax(healthAssessmentService.deleteHealthAssessmentByAssessmentIds(assessmentIds));
    }
}
