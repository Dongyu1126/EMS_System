<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="用户名" prop="locbindUsername">
        <el-input
          v-model="queryParams.locbindUsername"
          placeholder="请输入用户名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="操作时间" prop="locbindOperationTime">
        <el-date-picker clearable
          v-model="queryParams.locbindOperationTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择操作时间">
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
          v-hasPermi="['equmgn:locbind:add']"
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
          v-hasPermi="['equmgn:locbind:edit']"
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
          v-hasPermi="['equmgn:locbind:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['equmgn:locbind:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="locbindList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="车间" align="center" prop="locbindWorkshop" />
      <el-table-column label="工位" align="center" prop="locbindLocation" />
      <el-table-column label="用户名" align="center" prop="locbindUsername" />
      <el-table-column label="昵称" align="center" prop="locbindNickname" />
      <el-table-column label="操作人" align="center" prop="locbindOperator" />
      <el-table-column label="操作时间" align="center" prop="locbindOperationTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.locbindOperationTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['equmgn:locbind:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['equmgn:locbind:remove']"
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

    <!-- 添加或修改工位绑定对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="车间" prop="locbindWorkshop">
          <el-input v-model="form.locbindWorkshop" placeholder="请输入车间" />
        </el-form-item>
        <el-form-item label="用户名" prop="locbindUsername">
          <el-input v-model="form.locbindUsername" placeholder="请输入用户名" />
        </el-form-item>
        <el-form-item label="昵称" prop="locbindNickname">
          <el-input v-model="form.locbindNickname" placeholder="请输入昵称" />
        </el-form-item>
        <el-form-item label="操作人" prop="locbindOperator">
          <el-input v-model="form.locbindOperator" placeholder="请输入操作人" />
        </el-form-item>
        <el-form-item label="操作时间" prop="locbindOperationTime">
          <el-date-picker clearable
            v-model="form.locbindOperationTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择操作时间">
          </el-date-picker>
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
import { listLocbind, getLocbind, delLocbind, addLocbind, updateLocbind } from "@/api/equmgn/locbind";

export default {
  name: "Locbind",
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
      // 工位绑定表格数据
      locbindList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        locbindLocation: null,
        locbindUsername: null,
        locbindOperationTime: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        locbindLocation: [
          { required: true, message: "工位不能为空", trigger: "change" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询工位绑定列表 */
    getList() {
      this.loading = true;
      listLocbind(this.queryParams).then(response => {
        this.locbindList = response.rows;
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
        locbindWorkshop: null,
        locbindLocation: null,
        locbindUsername: null,
        locbindNickname: null,
        locbindOperator: null,
        locbindOperationTime: null
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
      this.ids = selection.map(item => item.locbindWorkshop)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加工位绑定";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const locbindWorkshop = row.locbindWorkshop || this.ids
      getLocbind(locbindWorkshop).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改工位绑定";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.locbindWorkshop != null) {
            updateLocbind(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addLocbind(this.form).then(response => {
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
      const locbindWorkshops = row.locbindWorkshop || this.ids;
      this.$modal.confirm('是否确认删除工位绑定编号为"' + locbindWorkshops + '"的数据项？').then(function() {
        return delLocbind(locbindWorkshops);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('equmgn/locbind/export', {
        ...this.queryParams
      }, `locbind_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
