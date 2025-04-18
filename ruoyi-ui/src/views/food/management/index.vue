<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="膳食名称" prop="foodName">
        <el-input
          v-model="queryParams.foodName"
          placeholder="请输入膳食名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="卡路里，单位为千卡" prop="calories">
        <el-input
          v-model="queryParams.calories"
          placeholder="请输入卡路里，单位为千卡"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['food:management:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['food:management:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['food:management:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['food:management:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="managementList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <!-- <el-table-column label="膳食ID，主键，自增" align="center" prop="foodId" /> -->
      <el-table-column label="膳食名称" align="center" prop="foodName" />
      <el-table-column label="膳食图片url" align="center" prop="foodImg" width="100">
        <template slot-scope="scope">
          <image-preview :src="scope.row.foodImg" :width="50" :height="50"/>
        </template>
      </el-table-column>
      <el-table-column label="膳食分类，例如：主食、蔬菜、水果等" align="center" prop="category">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.food_category" :value="scope.row.category ? scope.row.category.split(',') : []"/>
        </template>
      </el-table-column>
      <el-table-column label="卡路里，单位为千卡" align="center" prop="calories" />
      <el-table-column label="营养素" align="center" prop="nutrients" />
      <el-table-column label="组成" align="center" prop="composition" />
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['food:management:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['food:management:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改膳食数据管理，用于存储膳食相关的信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="膳食名称" prop="foodName">
          <el-input v-model="form.foodName" placeholder="请输入膳食名称" />
        </el-form-item>
        <el-form-item label="膳食图片url" prop="foodImg">
          <image-upload v-model="form.foodImg"/>
        </el-form-item>
        <el-form-item label="膳食分类" prop="category">
          <el-checkbox-group v-model="form.category">
            <el-checkbox
              v-for="dict in dict.type.food_category"
              :key="dict.value"
              :label="dict.value">
              {{dict.label}}
            </el-checkbox>
          </el-checkbox-group>
        </el-form-item>
        <el-form-item label="卡路里，单位为千卡" prop="calories">
          <el-input v-model="form.calories" placeholder="请输入卡路里，单位为千卡" />
        </el-form-item>
        <el-form-item label="营养素" prop="nutrients">
          <el-input v-model="form.nutrients" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="组成" prop="composition">
          <el-input v-model="form.composition" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listManagement, getManagement, delManagement, addManagement, updateManagement } from "@/api/database/food/management";

export default {
  name: "Management",
  dicts: ['food_category'],
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 膳食数据管理，用于存储膳食相关的信息表格数据
      managementList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        foodName: null,
        foodImg: null,
        category: null,
        calories: null,
        nutrients: null,
        composition: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        foodName: [
          { required: true, message: "膳食名称不能为空", trigger: "blur" }
        ],
        foodImg: [
          { required: true, message: "膳食图片url不能为空", trigger: "blur" }
        ],
        category: [
          { required: true, message: "膳食分类，例如：主食、蔬菜、水果等不能为空", trigger: "blur" }
        ],
        createBy: [
          { required: true, message: "创建者，记录操作员的用户名或ID不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询膳食数据管理，用于存储膳食相关的信息列表 */
    getList() {
      this.loading = true;
      listManagement(this.queryParams).then(response => {
        this.managementList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        foodId: null,
        foodName: null,
        foodImg: null,
        category: [],
        calories: null,
        nutrients: null,
        composition: null,
        createBy: null,
        createTime: null,
        updateTime: null,
        remark: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.foodId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加膳食数据管理，用于存储膳食相关的信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const foodId = row.foodId || this.ids
      getManagement(foodId).then(response => {
        this.form = response.data;
        this.form.category = this.form.category.split(",");
        this.open = true;
        this.title = "修改膳食数据管理，用于存储膳食相关的信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.form.category = this.form.category.join(",");
          if (this.form.foodId != null) {
            updateManagement(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addManagement(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const foodIds = row.foodId || this.ids;
      this.$modal.confirm('是否确认删除膳食数据管理，用于存储膳食相关的信息编号为"' + foodIds + '"的数据项？').then(function() {
        return delManagement(foodIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('food/management/export', {
        ...this.queryParams
      }, `management_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
