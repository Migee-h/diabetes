package com.zfy.diabetes.controller;


import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.zfy.diabetes.domain.DailyLifeRecord;
import com.zfy.diabetes.service.DailyLifeRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
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
 * 日常生活记录Controller
 *
 * @author zfy
 * @date 2025-04-16
 */
@RestController
@RequestMapping("/daily/record")
public class DailyLifeRecordController extends BaseController
{
    @Autowired
    private DailyLifeRecordService dailyLifeRecordService;

    /**
     * 查询日常生活记录列表
     */
    @RequiresPermissions("daily:record:list")
    @GetMapping("/list")
    public TableDataInfo list(DailyLifeRecord dailyLifeRecord)
    {
        startPage();
        List<DailyLifeRecord> list = dailyLifeRecordService.selectDailyLifeRecordList(dailyLifeRecord);
        return getDataTable(list);
    }

    /**
     * 导出日常生活记录列表
     */
    @RequiresPermissions("daily:record:export")
    @Log(title = "日常生活记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, DailyLifeRecord dailyLifeRecord)
    {
        List<DailyLifeRecord> list = dailyLifeRecordService.selectDailyLifeRecordList(dailyLifeRecord);
        ExcelUtil<DailyLifeRecord> util = new ExcelUtil<DailyLifeRecord>(DailyLifeRecord.class);
        util.exportExcel(response, list, "日常生活记录数据");
    }

    /**
     * 获取日常生活记录详细信息
     */
    @RequiresPermissions("daily:record:query")
    @GetMapping(value = "/{recordId}")
    public AjaxResult getInfo(@PathVariable("recordId") Long recordId)
    {
        return success(dailyLifeRecordService.selectDailyLifeRecordByRecordId(recordId));
    }

    /**
     * 新增日常生活记录
     */
    @RequiresPermissions("daily:record:add")
    @Log(title = "日常生活记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody DailyLifeRecord dailyLifeRecord)
    {
        String username = getUsername();
        dailyLifeRecord.setCreatedBy(username);
        return toAjax(dailyLifeRecordService.insertDailyLifeRecord(dailyLifeRecord));
    }

    /**
     * 修改日常生活记录
     */
    @RequiresPermissions("daily:record:edit")
    @Log(title = "日常生活记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody DailyLifeRecord dailyLifeRecord)
    {
        return toAjax(dailyLifeRecordService.updateDailyLifeRecord(dailyLifeRecord));
    }

    /**
     * 删除日常生活记录
     */
    @RequiresPermissions("daily:record:remove")
    @Log(title = "日常生活记录", businessType = BusinessType.DELETE)
    @DeleteMapping("/{recordIds}")
    public AjaxResult remove(@PathVariable Long[] recordIds)
    {
        return toAjax(dailyLifeRecordService.deleteDailyLifeRecordByRecordIds(recordIds));
    }
}
