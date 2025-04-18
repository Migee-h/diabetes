<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="患者姓名" prop="patientName">
        <el-input
          v-model="queryParams.patientName"
          placeholder="请输入患者姓名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="患者身份证号" prop="patientNum">
        <el-input
          v-model="queryParams.patientNum"
          placeholder="请输入患者身份证号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="患者手机号" prop="patientPhone">
        <el-input
          v-model="queryParams.patientPhone"
          placeholder="请输入患者手机号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="患者邮箱" prop="patientEmail">
        <el-input
          v-model="queryParams.patientEmail"
          placeholder="请输入患者邮箱"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>

      <el-form-item label="省份" prop="province">
        <el-input
          v-model="queryParams.province"
          placeholder="请输入省份"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="近期就诊时间" prop="clinicTime">
        <el-date-picker clearable
          v-model="queryParams.clinicTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择近期就诊时间">
        </el-date-picker>
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
          v-hasPermi="['diabetes:patient:add']"
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
          v-hasPermi="['diabetes:patient:edit']"
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
          v-hasPermi="['diabetes:patient:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['diabetes:patient:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="patientList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="患者编号" align="center" prop="patientId" />
      <el-table-column label="患者姓名" align="center" prop="patientName" />
      <!-- <el-table-column label="绑定设备id" align="center" prop="deviceId" /> -->
      <el-table-column label="患者性别" align="center" prop="patientSex" />
      <el-table-column label="患者身份证号" align="center" prop="patientNum" />
      <el-table-column label="患者生日" align="center" prop="patientBirthday" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.patientBirthday, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="患者年龄" align="center" prop="patientAge" />
      <el-table-column label="患者手机号" align="center" prop="patientPhone" />
      <el-table-column label="患者邮箱" align="center" prop="patientEmail" />
      <el-table-column label="患者身高" align="center" prop="patientHeight" />
      <el-table-column label="患者体重" align="center" prop="patientWeight" />
      <el-table-column label="患者BIM值" align="center" prop="patientBim" />
      <el-table-column label="省份" align="center" prop="province" />
      <!-- <el-table-column label="城市" align="center" prop="city" /> -->
      <el-table-column label="近期就诊时间" align="center" prop="clinicTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.clinicTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['diabetes:patient:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-view"
            @click="handleViewDetails(scope.row)"
          >查看详情</el-button>
          <el-dropdown trigger="click" @command="handleCommand($event, scope.row)">
            <el-button size="mini" type="text">
              更多<i class="el-icon-arrow-down el-icon--right"></i>
            </el-button>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item command="healthAssessment">健康评估</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['diabetes:patient:remove']"
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

    <!-- 添加或修改患者基本信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="1000px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="100px">
        <el-row :gutter="20">
          <el-col :span="8">
            <el-form-item label="患者姓名" prop="patientName">
              <el-input v-model="form.patientName" placeholder="请输入患者姓名" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="性别" prop="patientSex">
              <el-select v-model="form.patientSex" placeholder="请选择性别">
                <el-option label="男" value="男" />
                <el-option label="女" value="女" />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="身份证号" prop="patientNum">
              <el-input v-model="form.patientNum" placeholder="请输入患者身份证号" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="出生日期" prop="patientBirthday">
              <el-date-picker clearable
                v-model="form.patientBirthday"
                type="date"
                value-format="yyyy-MM-dd"
                placeholder="请选择患者生日"
                style="width: 100%"
                @change="calculateAge">
              </el-date-picker>
            </el-form-item>
            <el-form-item label="年龄" prop="patientAge">
              <el-input v-model="form.patientAge" placeholder="年龄将自动计算" readonly />
            </el-form-item>
          </el-col>
        </el-row>

        <el-row :gutter="20">
          <el-col :span="8">
            <el-form-item label="手机号码" prop="patientPhone">
              <el-input v-model="form.patientPhone" placeholder="请输入患者手机号" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="电子邮箱" prop="patientEmail">
              <el-input v-model="form.patientEmail" placeholder="请输入患者邮箱" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="绑定设备" prop="deviceId">
              <el-select v-model="form.deviceId" placeholder="请选择设备">
                <el-option
                  v-for="item in deviceOptions"
                  :key="item.deviceId"
                  :label="item.deviceName"
                  :value="item.deviceId"
                />
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row :gutter="20">
          <el-col :span="8">
            <el-form-item label="身高(cm)" prop="patientHeight">
              <el-input v-model="form.patientHeight" placeholder="请输入患者身高" @input="calculateBMI" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="体重(kg)" prop="patientWeight">
              <!-- 添加 + - 按钮 -->
              <div class="weight-input-group">
                <el-button type="primary" size="mini" @click="decreaseWeight">-</el-button>
                <el-input v-model="form.patientWeight" placeholder="请输入患者体重" @input="calculateBMI" />
                <el-button type="primary" size="mini" @click="increaseWeight">+</el-button>
              </div>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="BMI值" prop="patientBim">
              <el-input v-model="form.patientBim" placeholder="BMI值将自动计算" readonly />
            </el-form-item>
          </el-col>
        </el-row>

        <el-row :gutter="20">
          <el-col :span="8">
            <el-form-item label="民族" prop="nation">
              <el-input v-model="form.nation" placeholder="请输入民族" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="职业" prop="occupation">
              <el-input v-model="form.occupation" placeholder="请输入职业" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="就诊时间" prop="clinicTime">
              <el-date-picker clearable
                v-model="form.clinicTime"
                type="date"
                value-format="yyyy-MM-dd"
                placeholder="请选择近期就诊时间"
                style="width: 100%">
              </el-date-picker>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row :gutter="20">
          <el-col :span="24">
            <el-form-item label="所在地区" prop="region">
              <el-cascader
                v-model="form.region"
                :options="regionData"
                :props="{ checkStrictly: false }"
                @change="handleRegionChange"
                style="width: 100%"
                placeholder="请选择所在地区"
              />
            </el-form-item>
          </el-col>
        </el-row>

        <el-form-item label="详细地址" prop="address">
          <el-input v-model="form.address" placeholder="请输入患者详细地址" />
        </el-form-item>

        <el-form-item label="患者疾病" prop="disease">
          <el-input v-model="form.disease" type="textarea" :rows="2" placeholder="请输入内容" />
        </el-form-item>

        <el-form-item label="过敏史" prop="allergy">
          <el-input v-model="form.allergy" type="textarea" :rows="2" placeholder="请输入患者过敏史" />
        </el-form-item>

        <el-form-item label="病史" prop="medicalHistory">
          <el-input v-model="form.medicalHistory" type="textarea" :rows="3" placeholder="请输入内容" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 查看患者详情对话框 -->
    <el-dialog :title="'患者详情 - ' + detailForm.patientName" :visible.sync="detailDialogVisible" width="1000px" append-to-body>
      <el-tabs v-model="activeTab">
        <el-tab-pane label="基本信息" name="basic">
          <el-descriptions class="margin-top" title="基本信息" :column="2" border>
            <el-descriptions-item label="姓名">{{ detailForm.patientName }}</el-descriptions-item>
            <el-descriptions-item label="性别">{{ detailForm.patientSex }}</el-descriptions-item>
            <el-descriptions-item label="身份证号">{{ detailForm.patientNum }}</el-descriptions-item>
            <el-descriptions-item label="出生日期">{{ detailForm.patientBirthday }}</el-descriptions-item>
            <el-descriptions-item label="年龄">{{ detailForm.patientAge }}</el-descriptions-item>
            <el-descriptions-item label="民族">{{ detailForm.nation }}</el-descriptions-item>
            <el-descriptions-item label="职业">{{ detailForm.occupation }}</el-descriptions-item>
            <el-descriptions-item label="手机号码">{{ detailForm.patientPhone }}</el-descriptions-item>
            <el-descriptions-item label="电子邮箱">{{ detailForm.patientEmail }}</el-descriptions-item>
          </el-descriptions>

          <el-descriptions class="margin-top" title="健康信息" :column="2" border>
            <el-descriptions-item label="身高">{{ detailForm.patientHeight }} cm</el-descriptions-item>
            <el-descriptions-item label="体重">{{ detailForm.patientWeight }} kg</el-descriptions-item>
            <el-descriptions-item label="BMI值">{{ detailForm.patientBim }}</el-descriptions-item>
            <el-descriptions-item label="绑定设备">{{ getDeviceName(detailForm.deviceId) }}</el-descriptions-item>
          </el-descriptions>

          <el-descriptions class="margin-top" title="地址信息" :column="1" border>
            <el-descriptions-item label="所在地区">{{ detailForm.province }} {{ detailForm.city }} {{ detailForm.county }}</el-descriptions-item>
            <el-descriptions-item label="详细地址">{{ detailForm.address }}</el-descriptions-item>
          </el-descriptions>

          <el-descriptions class="margin-top" title="病史信息" :column="1" border>
            <el-descriptions-item label="患者疾病">{{ detailForm.disease || '无' }}</el-descriptions-item>
            <el-descriptions-item label="过敏史">{{ detailForm.allergy || '无' }}</el-descriptions-item>
            <el-descriptions-item label="病史">{{ detailForm.medicalHistory || '无' }}</el-descriptions-item>
          </el-descriptions>
        </el-tab-pane>

        <el-tab-pane label="日常记录" name="daily">
          <el-table v-loading="dailyLoading" :data="dailyRecords">
            <el-table-column label="记录日期" align="center" prop="recordDate" />
            <el-table-column label="饮食记录" align="center" prop="dietRecord" />
            <el-table-column label="运动记录" align="center" prop="exerciseRecord" />
            <el-table-column label="睡眠记录" align="center" prop="sleepRecord" />
            <el-table-column label="其他记录" align="center" prop="otherRecord" />
          </el-table>
        </el-tab-pane>

        <el-tab-pane label="血糖统计" name="sugar">
          <div class="sugar-stats">
            <el-row :gutter="24" class="mb20">
              <el-col :span="8">
                <div class="stats-card">
                  <div class="stats-title">15天平均血糖值</div>
                  <div class="stats-value">{{ sugarStatsData.averageSugarValue || '-' }}</div>
                </div>
              </el-col>
              <el-col :span="8">
                <div class="stats-card">
                  <div class="stats-title">每日达标率</div>
                  <div class="stats-value">{{ sugarStatsData.dailyQualifiedRate || '-' }}%</div>
                </div>
              </el-col>
              <el-col :span="8">
                <div class="stats-card warning" v-if="sugarStatsData.hasWarning">
                  <div class="stats-title">警告信息</div>
                  <div class="stats-value">{{ sugarStatsData.warningMessage }}</div>
                </div>
              </el-col>
            </el-row>

            <el-collapse v-model="activeCollapse" class="mb20">
              <el-collapse-item title="血糖趋势图" name="sugarTrend">
                <div class="chart-container sugar-chart-container">
                  <div ref="sugarChart" style="width: 800px; height: 400px;"></div>
                </div>
              </el-collapse-item>
            </el-collapse>

            <el-collapse v-model="activeCollapse">
              <el-collapse-item title="达标率统计" name="qualifiedRate">
                <div class="chart-container qualified-chart-container">
                  <div ref="qualifiedChart" style="width: 800px; height: 500px;"></div>
                </div>
              </el-collapse-item>
            </el-collapse>
          </div>
        </el-tab-pane>
      </el-tabs>

      <div slot="footer" class="dialog-footer">
        <el-button @click="detailDialogVisible = false">关 闭</el-button>
      </div>
    </el-dialog>

    <!-- 健康评估对话框 -->
    <el-dialog :title="assessmentTitle" :visible.sync="assessmentDialogVisible" width="500px" append-to-body>
      <el-form ref="assessmentForm" :model="assessmentForm" :rules="assessmentRules" label-width="80px">
        <el-form-item label="患者" prop="patientId">
          <el-autocomplete
            v-model="assessmentForm.patientName"
            :fetch-suggestions="searchPatients"
            placeholder="请输入患者姓名搜索"
            @select="handlePatientSelect"
            :trigger-on-focus="true"
            disabled
          ></el-autocomplete>
        </el-form-item>
        <el-form-item label="评估日期" prop="assessmentDate">
          <el-date-picker
            clearable
            v-model="assessmentForm.assessmentDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择评估日期"
          ></el-date-picker>
        </el-form-item>
        <el-form-item label="评估结果" prop="assessmentResult">
          <el-input v-model="assessmentForm.assessmentResult" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="干预计划" prop="interventionPlan">
          <el-input v-model="assessmentForm.interventionPlan" type="textarea" placeholder="请输入内容" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <!-- 新增AI健康评估按钮 -->
        <el-button type="primary" @click="callAiHealth">AI健康评估</el-button>
        <el-button type="primary" @click="submitAssessmentForm">确 定</el-button>
        <el-button @click="cancelAssessment">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 健康评估组件 -->
    <!-- <Assessment ref="assessmentComponent" /> -->
  </div>
</template>

<script>
import * as echarts from 'echarts';
import { listPatient, getPatient, delPatient, addPatient, updatePatient ,aiHealth} from "@/api/archive/patient/patient";
import { listInfo } from "@/api/archive/device/info";
import { listRecord } from "@/api/archive/daily/record";
import { sugarStats } from "@/api/earlywarning/blood/data";
import {addAssessment  } from "@/api/archive/health/assessment";
import { regionData } from "@/utils/region-data";
import Assessment from "@/views/health/assessment/index";
export default {
  name: "Patient",
  components: {
    Assessment
  },
  data() {
    return {
      // 地区数据
      regionData,
      // 详情对话框显示状态
      detailDialogVisible: false,
      // 详情表单
      detailForm: {},
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
      // 患者基本信息表格数据
      patientList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 当前激活的标签页
      activeTab: "basic",
      // 日常记录加载状态
      dailyLoading: false,
      // 日常记录数据
      dailyRecords: [],
      // 血糖统计数据
      sugarStatsData: {
        averageSugarValue: 0,
        dailyQualifiedRate: 0,
        hasWarning: false,
        warningMessage: "",
        dailySugarValues: [],
        dailyQualifiedStats: {}
      },
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        patientName: null,
        patientSex: null,
        patientNum: null,
        patientBirthday: null,
        patientAge: null,
        patientPhone: null,
        patientEmail: null,
        patientHeight: null,
        patientWeight: null,
        patientBim: null,
        patientStatus: null,
        disease: null,
        allergy: null,
        province: null,
        city: null,
        county: null,
        address: null,
        clinicTime: null,
        op: null,
        userId: null,
        userName: null,
        deptId: null,
        pinyin: null,
        medicalHistory: null,
        nation: null,
        occupation: null
      },
      // 表单参数
      form: {},
      // 设备列表数据
      deviceOptions: [],
      // 表单校验
      rules: {
        patientName: [
          { required: true, message: "患者姓名不能为空", trigger: "blur" }
        ],
        patientSex: [
          { required: true, message: "性别不能为空", trigger: "change" }
        ],
      },

      // 健康评估对话框相关数据
      assessmentDialogVisible: false,
      assessmentTitle: "新增健康评估",
      assessmentForm: {
        patientId: null,
        patientName: "",
        assessmentDate: "",
        assessmentResult: "",
        interventionPlan: ""
      },
      assessmentRules: {
        assessmentDate: [
          { required: true, message: "评估日期不能为空", trigger: "change" }
        ],
        assessmentResult: [
          { required: true, message: "评估结果不能为空", trigger: "blur" }
        ],
        interventionPlan: [
          { required: true, message: "干预计划不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询患者基本信息列表 */
    getList() {
      this.loading = true;
      listPatient(this.queryParams).then(response => {
        this.patientList = response.rows;
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
        patientId: null,
        patientName: null,
        deviceId: null,
        patientSex: null,
        patientNum: null,
        patientBirthday: null,
        patientAge: null,
        patientPhone: null,
        patientEmail: null,
        patientHeight: null,
        patientWeight: null,
        patientBim: null,
        patientStatus: null,
        disease: null,
        allergy: null,
        province: null,
        city: null,
        county: null,
        address: null,
        clinicTime: null,
        createTime: null,
        updateTime: null,
        op: null,
        userId: null,
        userName: null,
        deptId: null,
        delFlag: null,
        pinyin: null,
        medicalHistory: null,
        nation: null,
        occupation: null
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
      this.ids = selection.map(item => item.patientId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加患者基本信息";
      // 获取设备列表
      listInfo().then(response => {
        this.deviceOptions = response.rows;
      });
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const patientId = row.patientId || this.ids
      getPatient(patientId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改患者基本信息";
        // 获取设备列表
        listInfo().then(response => {
          this.deviceOptions = response.rows;
        });
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.patientId != null) {
            updatePatient(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addPatient(this.form).then(response => {
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
      const patientIds = row.patientId || this.ids;
      this.$modal.confirm('是否确认删除患者基本信息编号为"' + patientIds + '"的数据项？').then(function() {
        return delPatient(patientIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('diabetes/patient/export', {
        ...this.queryParams
      }, `patient_${new Date().getTime()}.xlsx`)
    },
    /** 处理下拉菜单命令 */
    handleCommand(command, row) {
      switch (command) {
        case 'healthAssessment':
          this.handleHealthAssessment(row);
          break;
      }
    },
    /** 健康评估按钮操作 */
    handleHealthAssessment(row) {
      this.assessmentForm.patientId = row.patientId;
      this.assessmentForm.patientName = row.patientName;
      this.assessmentDialogVisible = true;
    },

    /** 查看详情按钮操作 */
    handleViewDetails(row) {
      this.detailForm = { ...row };
      this.detailDialogVisible = true;
      this.activeTab = "basic";

      // 获取日常记录数据
      this.dailyLoading = true;
      listRecord({
        patientId: row.patientId
      }).then(response => {
        this.dailyRecords = response.rows;
        this.dailyLoading = false;
      });

      // 获取血糖统计数据
      sugarStats({
        patientId: row.patientId
      }).then(response => {
        this.sugarStatsData = response.data || {
          averageSugarValue: 0,
          dailyQualifiedRate: 0,
          hasWarning: false,
          warningMessage: "",
          dailySugarValues: [],
          dailyQualifiedStats: {}
        };
        this.initCharts();
      });
    },
    initCharts() {
      // 初始化折线图
      const sugarChart = echarts.init(this.$refs.sugarChart);
      const sugarOption = {
        title: {
          text: '近15天血糖值变化趋势',
          left: 'center'
        },
        tooltip: {
          trigger: 'axis'
        },
        xAxis: {
          type: 'category',
          data: Array.from({ length: this.sugarStatsData.dailySugarValues.length }, (_, i) => `第${i + 1}天`)
        },
        yAxis: {
          type: 'value',
          name: '血糖值 (mmol/L)'
        },
        series: [{
          data: this.sugarStatsData.dailySugarValues,
          type: 'line',
          smooth: true
        }]
      };
      sugarChart.setOption(sugarOption);

      // 初始化饼图
      const qualifiedChart = echarts.init(this.$refs.qualifiedChart);
      const qualifiedData = Object.entries(this.sugarStatsData.dailyQualifiedStats).map(([date, rate]) => ({
        name: date,
        value: rate
      }));
      const qualifiedOption = {
        title: {
          text: '每日血糖达标率',
          left: 'center'
        },
        tooltip: {
          trigger: 'item',
          formatter: '{a} <br/>{b}: {c} ({d}%)'
        },
        legend: {
          orient: 'vertical',
          left: 'left'
        },
        series: [{
          name: '达标率',
          type: 'pie',
          radius: '50%',
          data: qualifiedData,
          emphasis: {
            itemStyle: {
              shadowBlur: 10,
              shadowOffsetX: 0,
              shadowColor: 'rgba(0, 0, 0, 0.5)'
            }
          }
        }]
      };
      qualifiedChart.setOption(qualifiedOption);
    },
    /** 获取设备名称 */
    getDeviceName(deviceId) {
      const device = this.deviceOptions.find(item => item.deviceId === deviceId);
      return device ? device.deviceName : '未绑定设备';
    },
    // 处理地区选择变化
    handleRegionChange(value) {
      if (value && value.length === 3) {
        this.form.province = value[0];
        this.form.city = value[1];
        this.form.county = value[2];
      } else {
        this.form.province = null;
        this.form.city = null;
        this.form.county = null;
      }
    },
    calculateBMI() {
      if (this.form.patientHeight && this.form.patientWeight) {
        const height = this.form.patientHeight / 100; // 转换为米
        const weight = this.form.patientWeight;
        this.form.patientBim = (weight / (height * height)).toFixed(2);
      } else {
        this.form.patientBim = null;
      }
    },
    // 计算年龄
    calculateAge() {
      if (this.form.patientBirthday) {
        const birthDate = new Date(this.form.patientBirthday);
        const today = new Date();
        let age = today.getFullYear() - birthDate.getFullYear();
        const m = today.getMonth() - birthDate.getMonth();
        if (m < 0 || (m === 0 && today.getDate() < birthDate.getDate())) {
          age--;
        }
        this.form.patientAge = age;
      } else {
        this.form.patientAge = null;
      }
    },
    // 新增方法：增加体重
    increaseWeight() {
      if (this.form.patientWeight) {
        this.form.patientWeight = parseFloat(this.form.patientWeight) + 1;
      } else {
        this.form.patientWeight = 1;
      }
      this.calculateBMI();
    },
    // 新增方法：减少体重
    decreaseWeight() {
      if (this.form.patientWeight && parseFloat(this.form.patientWeight) > 0) {
        this.form.patientWeight = parseFloat(this.form.patientWeight) - 1;
      } else {
        this.form.patientWeight = 0;
      }
      this.calculateBMI();
    },

    /** 提交健康评估表单 */
    submitAssessmentForm() {
      this.$refs["assessmentForm"].validate(valid => {
        if (valid) {
          addAssessment(this.assessmentForm).then(response => {
            this.$modal.msgSuccess("新增健康评估成功");
            this.assessmentDialogVisible = false;
            this.getList();
          });
        }
      });
    },

    /** 取消健康评估 */
    cancelAssessment() {
      this.assessmentDialogVisible = false;
      this.resetAssessmentForm();
    },

    /** 重置健康评估表单 */
    resetAssessmentForm() {
      this.assessmentForm = {
        patientId: null,
        patientName: "",
        assessmentDate: "",
        assessmentResult: "",
        interventionPlan: ""
      };
      this.$refs["assessmentForm"] && this.$refs["assessmentForm"].resetFields();
    },

    /** 处理患者选择 */
    handlePatientSelect(item) {
      if (item && item.patientId) {
        this.assessmentForm.patientId = item.patientId;
        this.assessmentForm.patientName = item.value;
        console.log("患者选择成功，ID:", this.assessmentForm.patientId, "姓名:", this.assessmentForm.patientName);
      } else {
        this.$modal.msgError("选择的患者信息不完整，请重新选择");
        this.assessmentForm.patientId = null;
        this.assessmentForm.patientName = "";
      }
    },

    /** 调用AI健康评估方法 */
    callAiHealth() {
      if (!this.assessmentForm.patientId) {
        this.$modal.msgError("患者ID不能为空");
        return;
      }
      // 直接使用搜索出来的 patientName 和 patientId
      const patientData = {
        patientId: this.assessmentForm.patientId,
        patientName: this.assessmentForm.patientName
      };
      console.log("传递给 AI 健康评估的患者信息:", patientData); // 增加日志输出
      aiHealth(patientData).then(response => {
        console.log("AI健康评估返回的结果:", response); // 增加日志输出
        // 确保返回的结果正确赋值到对应的字段
        if (response.data) { // 检查 response.data 是否存在
          this.assessmentForm.assessmentResult = response.data.assessmentResult || "";
          this.assessmentForm.interventionPlan = response.data.interventionPlan || "";
        } else {
          console.error("AI健康评估返回的数据结构不符合预期:", response);
          this.$modal.msgError("AI健康评估返回的数据结构不符合预期");
        }
        this.$modal.msgSuccess("AI健康评估完成");
      }).catch(error => {
        console.error("AI健康评估失败:", error); // 增加错误日志输出
        this.$modal.msgError("AI健康评估失败：" + (error.message || "未知错误"));
      });
    },

    /** 搜索患者建议 */
    searchPatients(queryString, cb) {
      // 实现患者搜索逻辑，返回匹配的患者列表
      const results = queryString
        ? this.patientList.filter(item => item.patientName.includes(queryString))
        : this.patientList;
      cb(results.map(item => ({
        value: item.patientName,
        patientId: item.patientId // 确保返回的患者数据包含 patientId
      })));
    }
  }
};
</script>

<style scoped>
/* 新增和修改的样式 */
.sugar-stats {
  padding: 20px;
}

.mb20 {
  margin-bottom: 20px;
}

.stats-card {
  background: #fff;
  border-radius: 4px;
  padding: 20px;
  box-shadow: 0 2px 12px 0 rgba(0,0,0,0.1);
}

.stats-title {
  font-size: 14px;
  color: #606266;
  margin-bottom: 10px;
}

.stats-value {
  font-size: 24px;
  color: #303133;
}

.warning {
  background: #fef0f0;
}

.warning .stats-value {
  color: #f56c6c;
}

/* 图表容器样式 */
.chart-container {
  background: #fff;
  border-radius: 4px;
  width: 100%;
  box-sizing: border-box;
  margin-bottom: 20px;
}

.sugar-chart-container {
  height: 400px;
  padding: 20px;
}

.qualified-chart-container {
  height: 400px;
  padding: 20px;
}

/* 折叠面板样式调整 */
.el-collapse {
  border-top: none;
  border-bottom: none;
}

.el-collapse-item__header {
  font-size: 16px;
  font-weight: bold;
  padding-left: 10px;
}

.el-collapse-item__content {
  padding-bottom: 0;
}
</style>
