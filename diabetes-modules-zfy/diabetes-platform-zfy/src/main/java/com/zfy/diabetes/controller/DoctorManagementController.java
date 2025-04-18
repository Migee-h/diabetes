package com.zfy.diabetes.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.zfy.diabetes.domain.DoctorManagement;
import com.zfy.diabetes.service.DoctorManagementService;
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
 * 医生管理信息Controller
 *
 * @author zfy
 * @date 2025-04-16
 */
@RestController
@RequestMapping("/doctor/management")
public class DoctorManagementController extends BaseController
{
    @Autowired
    private DoctorManagementService doctorManagementService;

    /**
     * 查询医生管理信息列表
     */
    @RequiresPermissions("doctor:management:list")
    @GetMapping("/list")
    public TableDataInfo list(DoctorManagement doctorManagement)
    {
        startPage();
        List<DoctorManagement> list = doctorManagementService.selectDoctorManagementList(doctorManagement);
        return getDataTable(list);
    }

    /**
     * 导出医生管理信息列表
     */
    @RequiresPermissions("doctor:management:export")
    @Log(title = "医生管理信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, DoctorManagement doctorManagement)
    {
        List<DoctorManagement> list = doctorManagementService.selectDoctorManagementList(doctorManagement);
        ExcelUtil<DoctorManagement> util = new ExcelUtil<DoctorManagement>(DoctorManagement.class);
        util.exportExcel(response, list, "医生管理信息数据");
    }

    /**
     * 获取医生管理信息详细信息
     */
    @RequiresPermissions("doctor:management:query")
    @GetMapping(value = "/{doctorId}")
    public AjaxResult getInfo(@PathVariable("doctorId") Long doctorId)
    {
        return success(doctorManagementService.selectDoctorManagementByDoctorId(doctorId));
    }

    /**
     * 新增医生管理信息
     */
    @RequiresPermissions("doctor:management:add")
    @Log(title = "医生管理信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody DoctorManagement doctorManagement)
    {
        return toAjax(doctorManagementService.insertDoctorManagement(doctorManagement));
    }

    /**
     * 修改医生管理信息
     */
    @RequiresPermissions("doctor:management:edit")
    @Log(title = "医生管理信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody DoctorManagement doctorManagement)
    {
        return toAjax(doctorManagementService.updateDoctorManagement(doctorManagement));
    }

    /**
     * 删除医生管理信息
     */
    @RequiresPermissions("doctor:management:remove")
    @Log(title = "医生管理信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{doctorIds}")
    public AjaxResult remove(@PathVariable Long[] doctorIds)
    {
        return toAjax(doctorManagementService.deleteDoctorManagementByDoctorIds(doctorIds));
    }
}
