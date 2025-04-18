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
      <el-form-item label="评估日期" prop="assessmentDate">
        <el-date-picker clearable
          v-model="queryParams.assessmentDate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择评估日期">
        </el-date-picker>
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
          v-hasPermi="['health:assessment:add']"
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
          v-hasPermi="['health:assessment:edit']"
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
          v-hasPermi="['health:assessment:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['health:assessment:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="assessmentList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="评估记录ID" align="center" prop="assessmentId" />
      <el-table-column label="患者ID" align="center" prop="patientId" />
      <el-table-column label="患者姓名" align="center" prop="patientName" />
      <el-table-column label="评估日期" align="center" prop="assessmentDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.assessmentDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="评估结果" align="center" prop="assessmentResult" />
      <el-table-column label="干预计划" align="center" prop="interventionPlan" />
      <el-table-column label="创建者" align="center" prop="createdBy" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['health:assessment:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['health:assessment:remove']"
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

    <!-- 添加或修改健康评估与干预记录对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="患者" prop="patientId">
          <el-autocomplete
            v-model="form.patientName"
            :fetch-suggestions="searchPatients"
            placeholder="请输入患者姓名搜索"
            @select="handlePatientSelect"
            :trigger-on-focus="true"
          ></el-autocomplete>
        </el-form-item>
        <el-form-item label="评估日期" prop="assessmentDate">
          <el-date-picker clearable
            v-model="form.assessmentDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择评估日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="评估结果" prop="assessmentResult">
          <el-input v-model="form.assessmentResult" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="干预计划" prop="interventionPlan">
          <el-input v-model="form.interventionPlan" type="textarea" placeholder="请输入内容" />
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
import { listAssessment, getAssessment, delAssessment, addAssessment, updateAssessment } from "@/api/archive/health/assessment";
import { listPatient } from "@/api/archive/patient/patient";
export default {
  name: "Assessment",
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
      // 健康评估与干预记录表格数据
      assessmentList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        patientId: null,
        patientName: null,
        assessmentDate: null,
        assessmentResult: null,
        interventionPlan: null,
        createdBy: null,
      },
      // 患者列表数据
      patientOptions: [],
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        patientId: [
          { required: true, message: "患者ID不能为空", trigger: "blur" }
        ],
        assessmentDate: [
          { required: true, message: "评估日期不能为空", trigger: "blur" }
        ],
        createdBy: [
          { required: true, message: "创建者不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询健康评估与干预记录列表 */
    getList() {
      this.loading = true;
      listAssessment(this.queryParams).then(response => {
        this.assessmentList = response.rows;
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
        assessmentId: null,
        patientId: null,
        patientName: null,
        assessmentDate: null,
        assessmentResult: null,
        interventionPlan: null,
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
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.assessmentId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加健康评估与干预记录";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const assessmentId = row.assessmentId || this.ids
      getAssessment(assessmentId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改健康评估与干预记录";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.assessmentId != null) {
            updateAssessment(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addAssessment(this.form).then(response => {
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
      const assessmentIds = row.assessmentId || this.ids;
      this.$modal.confirm('是否确认删除健康评估与干预记录编号为"' + assessmentIds + '"的数据项？').then(function() {
        return delAssessment(assessmentIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('health/assessment/export', {
        ...this.queryParams
      }, `assessment_${new Date().getTime()}.xlsx`)
    },
    /** 搜索患者 */
    searchPatients(query, cb) {
      if (query) {
        listPatient({ patientName: query }).then(response => {
          const patients = response.rows.map(item => ({
            value: item.patientName,
            patientId: item.patientId
          }));
          cb(patients);
        });
      } else {
        cb([]);
      }
    },
    /** 患者选择事件 */
    handlePatientSelect(item) {
      this.form.patientId = item.patientId;
      this.form.patientName = item.value;
    }
  }
};
</script>
