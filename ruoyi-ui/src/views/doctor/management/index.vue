<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="医生姓名" prop="doctorName">
        <el-input
          v-model="queryParams.doctorName"
          placeholder="请输入医生姓名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="专长领域" prop="specialty">
        <el-input
          v-model="queryParams.specialty"
          placeholder="请输入专长领域"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="资格证书" prop="qualification">
        <el-input
          v-model="queryParams.qualification"
          placeholder="请输入资格证书"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="所在医院" prop="hospital">
        <el-input
          v-model="queryParams.hospital"
          placeholder="请输入所在医院"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="所在科室" prop="department">
        <el-input
          v-model="queryParams.department"
          placeholder="请输入所在科室"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="医生联系电话" prop="phoneNumber">
        <el-input
          v-model="queryParams.phoneNumber"
          placeholder="请输入医生联系电话"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="医生邮箱" prop="email">
        <el-input
          v-model="queryParams.email"
          placeholder="请输入医生邮箱"
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
          v-hasPermi="['doctor:management:add']"
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
          v-hasPermi="['doctor:management:edit']"
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
          v-hasPermi="['doctor:management:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['doctor:management:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="managementList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="医生姓名" align="center" prop="doctorName" />
      <el-table-column label="专长领域" align="center" prop="specialty" />
      <el-table-column label="资格证书" align="center" prop="qualification" />
      <el-table-column label="所在医院" align="center" prop="hospital" />
      <el-table-column label="所在科室" align="center" prop="department" />
      <el-table-column label="医生联系电话" align="center" prop="phoneNumber" />
      <el-table-column label="医生邮箱" align="center" prop="email" />
      <el-table-column label="医生状态" align="center" prop="status">
        <template slot-scope="scope">
          <el-tag :type="Number(scope.row.status) === 0 ? 'success' : 'danger'">
            {{ Number(scope.row.status) === 0 ? '正常' : '停用' }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['doctor:management:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['doctor:management:remove']"
          >删除</el-button>
          <!-- 新增更多按钮 -->
          <el-dropdown trigger="click" @command="(command) => handleCommand(command, scope.row)">
            <span class="el-dropdown-link">
              更多<i class="el-icon-arrow-down el-icon--right"></i>
            </span>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item command="resetPwd">重置密码</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
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

    <!-- 添加或修改医生管理信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="800px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="绑定账号" prop="userId">
              <el-select v-model="form.userId" placeholder="请选择用户" clearable style="width: 100%">
                <el-option
                  v-for="item in userOptions"
                  :key="item.userId"
                  :label="item.userName"
                  :value="item.userId"
                />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="医生姓名" prop="doctorName">
              <el-input v-model="form.doctorName" placeholder="请输入医生姓名" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="专长领域" prop="specialty">
              <el-input v-model="form.specialty" placeholder="请输入专长领域" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="资格证书" prop="qualification">
              <el-input v-model="form.qualification" placeholder="请输入资格证书" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="所在医院" prop="hospital">
              <el-input v-model="form.hospital" placeholder="请输入所在医院" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="所在科室" prop="department">
              <el-input v-model="form.department" placeholder="请输入所在科室" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="联系电话" prop="phoneNumber">
              <el-input v-model="form.phoneNumber" placeholder="请输入医生联系电话" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="医生邮箱" prop="email">
              <el-input v-model="form.email" placeholder="请输入医生邮箱" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="医生状态" prop="status">
              <el-switch
                v-model="form.status"
                :active-value="1"
                :inactive-value="0"
                active-text="停用"
                inactive-text="正常"
              />
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="备注" prop="remark">
              <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" />
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listManagement, getManagement, delManagement, addManagement, updateManagement } from "@/api/team/doctor/management";
import { listUser, resetUserPwd } from '@/api/system/user'
export default {
  name: "Management",
  data() {
    return {
      // 用户下拉选项列表
      userOptions: [],
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
      // 医生管理信息表格数据
      managementList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        userId: null,
        roleId: null,
        doctorName: null,
        specialty: null,
        qualification: null,
        hospital: null,
        department: null,
        phoneNumber: null,
        email: null,
        status: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        userId: [
          { required: true, message: "用户ID，关联sys_user表中的user_id不能为空", trigger: "blur" }
        ],
        roleId: [
          { required: true, message: "角色ID，关联sys_role表中的role_id不能为空", trigger: "blur" }
        ],
        doctorName: [
          { required: true, message: "医生姓名不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
    this.getUserList();
  },
  methods: {
    /** 查询医生管理信息列表 */
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
        doctorId: null,
        userId: null,
        roleId: null,
        doctorName: null,
        specialty: null,
        qualification: null,
        hospital: null,
        department: null,
        phoneNumber: null,
        email: null,
        // 为 status 设置默认值，避免状态按钮显示异常
        status: 0, // 默认为正常状态
        createBy: null,
        createTime: null,
        updateBy: null,
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
    /** 处理更多操作命令 */
    handleCommand(command, row) {
      if (command === 'resetPwd') {
        this.handleResetPwd(row);
      }
    },
    /** 重置密码按钮操作 */
    handleResetPwd(row) {
      this.$prompt('请输入"' + row.userName + '"的新密码', "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        closeOnClickModal: false,
        inputPattern: /^.{5,20}$/,
        inputErrorMessage: "用户密码长度必须介于 5 和 20 之间",
        inputValidator: (value) => {
          if (/<|>|"|'|\||\\/.test(value)) {
            return "不能包含非法字符：< > \" ' \\\ |"
          }
        },
      }).then(({ value }) => {
        resetUserPwd(row.userId, value).then(response => {
          this.$modal.msgSuccess("修改成功，新密码是：" + value);
        });
      }).catch(() => {});
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.doctorId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加医生管理信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const doctorId = row.doctorId || this.ids;
      getManagement(doctorId).then(response => {
        // 确保 status 字段被正确转换为数字类型
        this.form = { ...response.data, status: Number(response.data.status) };
        this.open = true;
        this.title = "修改医生管理信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.doctorId != null) {
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
      const doctorIds = row.doctorId || this.ids;
      this.$modal.confirm('是否确认删除医生管理信息编号为"' + doctorIds + '"的数据项？').then(function() {
        return delManagement(doctorIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('doctor/management/export', {
        ...this.queryParams
      }, `management_${new Date().getTime()}.xlsx`)
    },
    /** 查询用户列表 */
    getUserList() {
      listUser().then(response => {
        this.userOptions = response.rows;
      });
    }
  }
};
</script>
