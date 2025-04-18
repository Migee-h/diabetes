package com.zfy.diabetes.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.zfy.diabetes.domain.MedicineInfo;
import com.zfy.diabetes.service.MedicineInfoService;
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
 * 药品管理，用于存储药品相关的信息Controller
 *
 * @author zfy
 * @date 2025-04-16
 */
@RestController
@RequestMapping("/medicine/info")
public class MedicineInfoController extends BaseController
{
    @Autowired
    private MedicineInfoService medicineInfoService;

    /**
     * 查询药品管理，用于存储药品相关的信息列表
     */
    @RequiresPermissions("medicine:info:list")
    @GetMapping("/list")
    public TableDataInfo list(MedicineInfo medicineInfo)
    {
        startPage();
        List<MedicineInfo> list = medicineInfoService.selectMedicineInfoList(medicineInfo);
        return getDataTable(list);
    }

    /**
     * 导出药品管理，用于存储药品相关的信息列表
     */
    @RequiresPermissions("medicine:info:export")
    @Log(title = "药品管理，用于存储药品相关的信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MedicineInfo medicineInfo)
    {
        List<MedicineInfo> list = medicineInfoService.selectMedicineInfoList(medicineInfo);
        ExcelUtil<MedicineInfo> util = new ExcelUtil<MedicineInfo>(MedicineInfo.class);
        util.exportExcel(response, list, "药品管理，用于存储药品相关的信息数据");
    }

    /**
     * 获取药品管理，用于存储药品相关的信息详细信息
     */
    @RequiresPermissions("medicine:info:query")
    @GetMapping(value = "/{medicineId}")
    public AjaxResult getInfo(@PathVariable("medicineId") Long medicineId)
    {
        return success(medicineInfoService.selectMedicineInfoByMedicineId(medicineId));
    }

    /**
     * 新增药品管理，用于存储药品相关的信息
     */
    @RequiresPermissions("medicine:info:add")
    @Log(title = "药品管理，用于存储药品相关的信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MedicineInfo medicineInfo)
    {
        return toAjax(medicineInfoService.insertMedicineInfo(medicineInfo));
    }

    /**
     * 修改药品管理，用于存储药品相关的信息
     */
    @RequiresPermissions("medicine:info:edit")
    @Log(title = "药品管理，用于存储药品相关的信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MedicineInfo medicineInfo)
    {
        return toAjax(medicineInfoService.updateMedicineInfo(medicineInfo));
    }

    /**
     * 删除药品管理，用于存储药品相关的信息
     */
    @RequiresPermissions("medicine:info:remove")
    @Log(title = "药品管理，用于存储药品相关的信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{medicineIds}")
    public AjaxResult remove(@PathVariable Long[] medicineIds)
    {
        return toAjax(medicineInfoService.deleteMedicineInfoByMedicineIds(medicineIds));
    }
}
