package com.zfy.diabetes.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson2.JSONObject;
import com.zfy.diabetes.domain.HealthAssessment;
import com.zfy.diabetes.domain.Patient;
import com.zfy.diabetes.service.PatientService;
import lombok.extern.slf4j.Slf4j;
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

import static com.zfy.diabetes.utils.DeepSeekUtils.deepSeek;

/**
 * 患者基本信息Controller
 *
 * @author zfy
 * @date 2025-04-16
 */
@Slf4j
@RestController
@RequestMapping("/patient")
public class PatientController extends BaseController
{
    @Autowired
    private PatientService patientService;

    /**
     * 查询患者基本信息列表
     */
    @RequiresPermissions("diabetes:patient:list")
    @GetMapping("/list")
    public TableDataInfo list(Patient patient)
    {
        startPage();
        List<Patient> list = patientService.selectPatientList(patient);
        return getDataTable(list);
    }

    /**
     * 导出患者基本信息列表
     */
    @RequiresPermissions("diabetes:patient:export")
    @Log(title = "患者基本信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Patient patient)
    {
        List<Patient> list = patientService.selectPatientList(patient);
        ExcelUtil<Patient> util = new ExcelUtil<Patient>(Patient.class);
        util.exportExcel(response, list, "患者基本信息数据");
    }

    /**
     * 获取患者基本信息详细信息
     */
    @RequiresPermissions("diabetes:patient:query")
    @GetMapping(value = "/{patientId}")
    public AjaxResult getInfo(@PathVariable("patientId") Long patientId)
    {
        return success(patientService.selectPatientByPatientId(patientId));
    }

    /**
     * 新增患者基本信息
     */
    @RequiresPermissions("diabetes:patient:add")
    @Log(title = "患者基本信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Patient patient)
    {
        return toAjax(patientService.insertPatient(patient));
    }

    /**
     * 修改患者基本信息
     */
    @RequiresPermissions("diabetes:patient:edit")
    @Log(title = "患者基本信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Patient patient)
    {
        return toAjax(patientService.updatePatient(patient));
    }


    /**
     * ai识别患者健康评估
     */
//    @RequiresPermissions("diabetes:patient:edit")
    @Log(title = "AI健康评估", businessType = BusinessType.OTHER)
    @PostMapping("/aiHealth")
    public AjaxResult aiHealth(@RequestBody Patient patient) {
        String patientInfo = "性别"+patient.getPatientSex() + "年龄"+patient.getPatientAge() + "身高"+patient.getPatientHeight() + "体重"+patient.getPatientWeight()+ "患病"+patient.getDisease()+"过敏史"+ patient.getAllergy()+ "疾病史"+patient.getMedicalHistory()+"民族"+ patient.getNation()+ "职业"+patient.getOccupation();
        String system = "根据给出的患者信息进行健康评估，输出json格式数据，result为评估结果就是预测该患者的身体这状况怎么样，以后可能会出现什么风险疾病，一段话就行，plan为干预计划，对以后可能会出现的问题进行一个计划";
        String s = deepSeek(patientInfo,system);
        HealthAssessment healthAssessment = new HealthAssessment();
        try {
            //解析JSON字符串
            JSONObject jsonObject = JSONObject.parseObject(s);
            String result = jsonObject.getString("result");
            String plan = jsonObject.getString("plan");
            healthAssessment.setAssessmentResult(result);
            healthAssessment.setInterventionPlan(plan);
        }catch (Exception e){
            log.error("json解析失败");
            healthAssessment.setAssessmentResult("暂无");
            healthAssessment.setInterventionPlan("暂无");
        }
        return success(healthAssessment);
    }

    /**
     * 删除患者基本信息
     */
    @RequiresPermissions("diabetes:patient:remove")
    @Log(title = "患者基本信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{patientIds}")
    public AjaxResult remove(@PathVariable Long[] patientIds)
    {
        return toAjax(patientService.deletePatientByPatientIds(patientIds));
    }



}
