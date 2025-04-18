<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="药品名称" prop="medicineName">
        <el-input
          v-model="queryParams.medicineName"
          placeholder="请输入药品名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="药品分类" prop="category">
        <el-select v-model="queryParams.category" placeholder="请选择药品分类" clearable>
          <el-option
            v-for="dict in dict.type.medicine_category"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="剂量信息" prop="dosage">
        <el-select v-model="queryParams.dosage" placeholder="请选择剂量信息" clearable>
          <el-option
            v-for="dict in dict.type.medicine_dosage"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="生产厂家" prop="manufacturer">
        <el-input
          v-model="queryParams.manufacturer"
          placeholder="请输入生产厂家"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="有效期" prop="expirationDate">
        <el-date-picker clearable
          v-model="queryParams.expirationDate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择有效期">
        </el-date-picker>
      </el-form-item>
      <!-- <el-form-item label="创建时间" prop="createdAt">
        <el-date-picker clearable
          v-model="queryParams.createdAt"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择创建时间，默认为当前时间">
        </el-date-picker>
      </el-form-item> -->
      <!-- <el-form-item label="更新时间，默认为当前时间，自动更新" prop="updatedAt">
        <el-date-picker clearable
          v-model="queryParams.updatedAt"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择更新时间，默认为当前时间，自动更新">
        </el-date-picker>
      </el-form-item> -->
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
          v-hasPermi="['medicine:info:add']"
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
          v-hasPermi="['medicine:info:edit']"
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
          v-hasPermi="['medicine:info:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['medicine:info:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="infoList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <!-- <el-table-column label="药品ID，主键，自增" align="center" prop="medicineId" /> -->
      <el-table-column label="药品名称" align="center" prop="medicineName" />
      <el-table-column label="药品分类" align="center" prop="category">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.medicine_category" :value="scope.row.category"/>
        </template>
      </el-table-column>
      <el-table-column label="剂量" align="center" prop="dosage">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.medicine_dosage" :value="scope.row.dosage"/>
        </template>
      </el-table-column>
      <el-table-column label="用法说明" align="center" prop="usage" />
      <el-table-column label="副作用" align="center" prop="sideEffects" />
      <el-table-column label="生产厂家" align="center" prop="manufacturer" />
      <el-table-column label="有效期" align="center" prop="expirationDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.expirationDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="组成" align="center" prop="composition" />
      <!-- <el-table-column label="创建时间，默认为当前时间" align="center" prop="createdAt" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createdAt, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column> -->
      <!-- <el-table-column label="更新时间，默认为当前时间，自动更新" align="center" prop="updatedAt" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.updatedAt, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column> -->
      <el-table-column label="备注信息，用于补充说明" align="center" prop="remark" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['medicine:info:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['medicine:info:remove']"
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

    <!-- 添加或修改药品管理，用于存储药品相关的信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="药品名称" prop="medicineName">
          <el-input v-model="form.medicineName" placeholder="请输入药品名称" />
        </el-form-item>
        <el-form-item label="药品分类" prop="category">
          <el-select v-model="form.category" placeholder="请选择药品分类">
            <el-option
              v-for="dict in dict.type.medicine_category"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="剂量" prop="dosage">
          <el-select v-model="form.dosage" placeholder="请选择剂量">
            <el-option
              v-for="dict in dict.type.medicine_dosage"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="用法说明" prop="usage">
          <el-input v-model="form.usage" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="副作用" prop="sideEffects">
          <el-input v-model="form.sideEffects" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="生产厂家" prop="manufacturer">
          <el-input v-model="form.manufacturer" placeholder="请输入生产厂家" />
        </el-form-item>
        <el-form-item label="有效期" prop="expirationDate">
          <el-date-picker clearable
            v-model="form.expirationDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择有效期，记录药品的有效期限">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="组成" prop="composition">
          <el-input v-model="form.composition" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <!-- <el-form-item label="创建时间，默认为当前时间" prop="createdAt">
          <el-date-picker clearable
            v-model="form.createdAt"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择创建时间，默认为当前时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="更新时间，默认为当前时间，自动更新" prop="updatedAt">
          <el-date-picker clearable
            v-model="form.updatedAt"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择更新时间，默认为当前时间，自动更新">
          </el-date-picker>
        </el-form-item> -->
        <el-form-item label="备注信息，用于补充说明" prop="remark">
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
import { listInfo, getInfo, delInfo, addInfo, updateInfo } from "@/api/database/medicine/info";

export default {
  name: "Info",
  dicts: ['medicine_category', 'medicine_dosage'],
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
      // 药品管理，用于存储药品相关的信息表格数据
      infoList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        medicineName: null,
        category: null,
        dosage: null,
        usage: null,
        sideEffects: null,
        manufacturer: null,
        expirationDate: null,
        composition: null,
        createdAt: null,
        updatedAt: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        medicineName: [
          { required: true, message: "药品名称不能为空", trigger: "blur" }
        ],
        category: [
          { required: true, message: "药品分类，例如：处方药、非处方药、中药等不能为空", trigger: "change" }
        ],
        dosage: [
          { required: true, message: "剂量信息，例如：一次一片、每日三次等不能为空", trigger: "change" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询药品管理，用于存储药品相关的信息列表 */
    getList() {
      this.loading = true;
      listInfo(this.queryParams).then(response => {
        this.infoList = response.rows;
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
        medicineId: null,
        medicineName: null,
        category: null,
        dosage: null,
        usage: null,
        sideEffects: null,
        manufacturer: null,
        expirationDate: null,
        composition: null,
        createdAt: null,
        updatedAt: null,
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
      this.ids = selection.map(item => item.medicineId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加药品管理，用于存储药品相关的信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const medicineId = row.medicineId || this.ids
      getInfo(medicineId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改药品管理，用于存储药品相关的信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.medicineId != null) {
            updateInfo(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addInfo(this.form).then(response => {
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
      const medicineIds = row.medicineId || this.ids;
      this.$modal.confirm('是否确认删除药品管理，用于存储药品相关的信息编号为"' + medicineIds + '"的数据项？').then(function() {
        return delInfo(medicineIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('medicine/info/export', {
        ...this.queryParams
      }, `info_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
