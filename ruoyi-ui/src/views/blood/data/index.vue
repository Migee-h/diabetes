<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="患者ID" prop="patientId">
        <el-input
          v-model="queryParams.patientId"
          placeholder="请输入患者ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="患者姓名" prop="patientName">
        <el-input
          v-model="queryParams.patientName"
          placeholder="请输入患者姓名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="记录日期" prop="recordDate">
        <el-date-picker clearable
          v-model="queryParams.recordDate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择记录日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="血糖值" prop="sugarValue">
        <el-input
          v-model="queryParams.sugarValue"
          placeholder="请输入血糖值"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="创建者" prop="createdBy">
        <el-input
          v-model="queryParams.createdBy"
          placeholder="请输入创建者"
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
          v-hasPermi="['blood:data:add']"
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
          v-hasPermi="['blood:data:edit']"
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
          v-hasPermi="['blood:data:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['blood:data:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="dataList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="数据ID" align="center" prop="dataId" />
      <el-table-column label="患者ID" align="center" prop="patientId" />
      <el-table-column label="患者姓名" align="center" prop="patientName" />
      <el-table-column label="记录日期" align="center" prop="recordDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.recordDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="血糖值" align="center" prop="sugarValue" />
      <el-table-column label="上传类型" align="center" prop="uploadType" />
      <el-table-column label="创建者" align="center" prop="createdBy" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['blood:data:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['blood:data:remove']"
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

    <!-- 添加或修改血糖数据对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="患者" prop="patientId">
          <el-select
            v-model="form.patientId"
            filterable
            remote
            reserve-keyword
            placeholder="请输入关键词搜索患者"
            :remote-method="remotePatientSearch"
            :loading="patientLoading"
            @change="handlePatientSelect"
          >
            <el-option
              v-for="item in patientOptions"
              :key="item.patientId"
              :label="item.patientName"
              :value="item.patientId"
            >
              <span style="float: left">{{ item.patientName }}</span>
              <span style="float: right; color: #8492a6; font-size: 13px">{{ item.patientNum }}</span>
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="记录日期" prop="recordDate">
          <el-date-picker clearable
            v-model="form.recordDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择记录日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="血糖值" prop="sugarValue">
          <el-input v-model="form.sugarValue" placeholder="请输入血糖值" />
        </el-form-item>
        <!-- <el-form-item label="创建者" prop="createdBy">
          <el-input v-model="form.createdBy" placeholder="请输入创建者" />
        </el-form-item> -->
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listData, getData, delData, addData, updateData } from "@/api/earlywarning/blood/data";
import { listPatient } from "@/api/archive/patient/patient";
export default {
  name: "Data",
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
      // 血糖数据表格数据
      dataList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 患者搜索关键词
      patientSearchKey: "",
      // 患者列表数据
      patientOptions: [],
      // 患者搜索加载状态
      patientLoading: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        patientId: null,
        patientName: null,
        recordDate: null,
        sugarValue: null,
        uploadType: null,
        createdBy: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        patientId: [
          { required: true, message: "患者ID不能为空", trigger: "blur" }
        ],
        recordDate: [
          { required: true, message: "记录日期不能为空", trigger: "blur" }
        ],
        sugarValue: [
          { required: true, message: "血糖值不能为空", trigger: "blur" }
        ],
      
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询血糖数据列表 */
    getList() {
      this.loading = true;
      listData(this.queryParams).then(response => {
        this.dataList = response.rows;
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
        dataId: null,
        patientId: null,
        patientName: null,
        recordDate: null,
        sugarValue: null,
        uploadType: null,
        createdBy: null,
        createTime: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 远程搜索患者 */
    remotePatientSearch(query) {
      if (query !== '') {
        this.patientLoading = true;
        listPatient({
          pageNum: 1,
          pageSize: 10,
          patientName: query
          // patientNum: query
        }).then(response => {
          this.patientOptions = response.rows;
          this.patientLoading = false;
        });
      } else {
        this.patientOptions = [];
      }
    },
    /** 选择患者 */
    handlePatientSelect(patientId) {
      const selectedPatient = this.patientOptions.find(item => item.patientId === patientId);
      if (selectedPatient) {
        this.form.patientName = selectedPatient.patientName;
      }
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.dataId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加血糖数据";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const dataId = row.dataId || this.ids
      getData(dataId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改血糖数据";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.dataId != null) {
            updateData(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addData(this.form).then(response => {
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
      const dataIds = row.dataId || this.ids;
      this.$modal.confirm('是否确认删除血糖数据编号为"' + dataIds + '"的数据项？').then(function() {
        return delData(dataIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('blood/data/export', {
        ...this.queryParams
      }, `data_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
