package com.zfy.diabetes.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.zfy.diabetes.domain.FoodDataManagement;
import com.zfy.diabetes.service.FoodDataManagementService;
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
 * 膳食数据管理，用于存储膳食相关的信息Controller
 *
 * @author zfy
 * @date 2025-04-16
 */
@RestController
@RequestMapping("/food/management")
public class FoodDataManagementController extends BaseController
{
    @Autowired
    private FoodDataManagementService foodDataManagementService;

    /**
     * 查询膳食数据管理，用于存储膳食相关的信息列表
     */
    @RequiresPermissions("food:management:list")
    @GetMapping("/list")
    public TableDataInfo list(FoodDataManagement foodDataManagement)
    {
        startPage();
        List<FoodDataManagement> list = foodDataManagementService.selectFoodDataManagementList(foodDataManagement);
        return getDataTable(list);
    }

    /**
     * 导出膳食数据管理，用于存储膳食相关的信息列表
     */
    @RequiresPermissions("food:management:export")
    @Log(title = "膳食数据管理，用于存储膳食相关的信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, FoodDataManagement foodDataManagement)
    {
        List<FoodDataManagement> list = foodDataManagementService.selectFoodDataManagementList(foodDataManagement);
        ExcelUtil<FoodDataManagement> util = new ExcelUtil<FoodDataManagement>(FoodDataManagement.class);
        util.exportExcel(response, list, "膳食数据管理，用于存储膳食相关的信息数据");
    }

    /**
     * 获取膳食数据管理，用于存储膳食相关的信息详细信息
     */
    @RequiresPermissions("food:management:query")
    @GetMapping(value = "/{foodId}")
    public AjaxResult getInfo(@PathVariable("foodId") Long foodId)
    {
        return success(foodDataManagementService.selectFoodDataManagementByFoodId(foodId));
    }

    /**
     * 新增膳食数据管理，用于存储膳食相关的信息
     */
    @RequiresPermissions("food:management:add")
    @Log(title = "膳食数据管理，用于存储膳食相关的信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FoodDataManagement foodDataManagement)
    {
        String username = getUsername();
        foodDataManagement.setCreateBy(username);
        return toAjax(foodDataManagementService.insertFoodDataManagement(foodDataManagement));
    }

    /**
     * 修改膳食数据管理，用于存储膳食相关的信息
     */
    @RequiresPermissions("food:management:edit")
    @Log(title = "膳食数据管理，用于存储膳食相关的信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FoodDataManagement foodDataManagement)
    {
        return toAjax(foodDataManagementService.updateFoodDataManagement(foodDataManagement));
    }

    /**
     * 删除膳食数据管理，用于存储膳食相关的信息
     */
    @RequiresPermissions("food:management:remove")
    @Log(title = "膳食数据管理，用于存储膳食相关的信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{foodIds}")
    public AjaxResult remove(@PathVariable Long[] foodIds)
    {
        return toAjax(foodDataManagementService.deleteFoodDataManagementByFoodIds(foodIds));
    }
}
