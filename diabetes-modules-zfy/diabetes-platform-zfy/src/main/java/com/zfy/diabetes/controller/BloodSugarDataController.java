package com.zfy.diabetes.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.zfy.diabetes.domain.BloodSugarData;
import com.zfy.diabetes.domain.vo.BloodSugarStatsVO;
import com.zfy.diabetes.service.BloodSugarDataService;
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

import static com.ruoyi.common.security.utils.SecurityUtils.getUsername;

/**
 * 血糖数据Controller
 *
 * @author zfy
 * @date 2025-04-17
 */
@RestController
@RequestMapping("/blood/data")
public class BloodSugarDataController extends BaseController
{
    @Autowired
    private BloodSugarDataService bloodSugarDataService;

    /**
     * 查询血糖数据列表
     */
    @RequiresPermissions("blood:data:list")
    @GetMapping("/list")
    public TableDataInfo list(BloodSugarData bloodSugarData)
    {
        startPage();
        List<BloodSugarData> list = bloodSugarDataService.selectBloodSugarDataList(bloodSugarData);
        return getDataTable(list);
    }

    /**
     * 导出血糖数据列表
     */
    @RequiresPermissions("blood:data:export")
    @Log(title = "血糖数据", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BloodSugarData bloodSugarData)
    {
        List<BloodSugarData> list = bloodSugarDataService.selectBloodSugarDataList(bloodSugarData);
        ExcelUtil<BloodSugarData> util = new ExcelUtil<BloodSugarData>(BloodSugarData.class);
        util.exportExcel(response, list, "血糖数据数据");
    }

    /**
     * 获取血糖数据详细信息
     */
    @RequiresPermissions("blood:data:query")
    @GetMapping(value = "/{dataId}")
    public AjaxResult getInfo(@PathVariable("dataId") Long dataId)
    {
        return success(bloodSugarDataService.selectBloodSugarDataByDataId(dataId));
    }

    /**
     * 新增血糖数据
     */
    @RequiresPermissions("blood:data:add")
    @Log(title = "血糖数据", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BloodSugarData bloodSugarData)
    {
        bloodSugarData.setUploadType("手动");
        bloodSugarData.setCreatedBy(getUsername());
        return toAjax(bloodSugarDataService.insertBloodSugarData(bloodSugarData));
    }

    /**
     * 修改血糖数据
     */
    @RequiresPermissions("blood:data:edit")
    @Log(title = "血糖数据", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BloodSugarData bloodSugarData)
    {
        return toAjax(bloodSugarDataService.updateBloodSugarData(bloodSugarData));
    }

    /**
     * 删除血糖数据
     */
    @RequiresPermissions("blood:data:remove")
    @Log(title = "血糖数据", businessType = BusinessType.DELETE)
    @DeleteMapping("/{dataIds}")
    public AjaxResult remove(@PathVariable Long[] dataIds)
    {
        return toAjax(bloodSugarDataService.deleteBloodSugarDataByDataIds(dataIds));
    }

    /**
     * 获取患者血糖统计数据
     */
//    @RequiresPermissions("blood:data:stats")
    @PostMapping("/stats")
    public AjaxResult getBloodSugarStats(@RequestBody BloodSugarData bloodSugarData)
    {
        return success(bloodSugarDataService.getBloodSugarStats(bloodSugarData.getPatientId()));
    }
}
