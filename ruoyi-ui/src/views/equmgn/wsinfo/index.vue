<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="车间名" prop="wsinfoName">
        <el-input
          v-model="queryParams.wsinfoName"
          placeholder="请输入车间名"
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
          v-hasPermi="['equmgn:wsinfo:add']"
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
          v-hasPermi="['equmgn:wsinfo:edit']"
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
          v-hasPermi="['equmgn:wsinfo:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['equmgn:wsinfo:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="wsinfoList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="车间名" align="center" prop="wsinfoName" />
      <el-table-column label="车间负责人" align="center" prop="wsinfoLeader" />
      <el-table-column label="车间状态" align="center" prop="wsinfoStatus" />
      <el-table-column label="车间添加时间" align="center" prop="wsinfoCreateTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.wsinfoCreateTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="车间备注" align="center" prop="wsinfoRemark" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['equmgn:wsinfo:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['equmgn:wsinfo:remove']"
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

    <!-- 添加或修改车间信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="车间名" prop="wsinfoName">
          <el-input v-model="form.wsinfoName" placeholder="请输入车间名" />
        </el-form-item>
        <el-form-item label="车间负责人" prop="wsinfoLeader">
          <el-input v-model="form.wsinfoLeader" placeholder="请输入车间负责人" />
        </el-form-item>
        <el-form-item label="车间添加时间" prop="wsinfoCreateTime">
          <el-date-picker clearable
            v-model="form.wsinfoCreateTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择车间添加时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="车间备注" prop="wsinfoRemark">
          <el-input v-model="form.wsinfoRemark" placeholder="请输入车间备注" />
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
import { listWsinfo, getWsinfo, delWsinfo, addWsinfo, updateWsinfo } from "@/api/equmgn/wsinfo";

export default {
  name: "Wsinfo",
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
      // 车间信息表格数据
      wsinfoList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        wsinfoName: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        wsinfoName: [
          { required: true, message: "车间名不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询车间信息列表 */
    getList() {
      this.loading = true;
      listWsinfo(this.queryParams).then(response => {
        this.wsinfoList = response.rows;
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
        wsinfoName: null,
        wsinfoLeader: null,
        wsinfoStatus: null,
        wsinfoCreateTime: null,
        wsinfoRemark: null
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
      this.ids = selection.map(item => item.wsinfoName)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加车间信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const wsinfoName = row.wsinfoName || this.ids
      getWsinfo(wsinfoName).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改车间信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.wsinfoName != null) {
            updateWsinfo(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addWsinfo(this.form).then(response => {
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
      const wsinfoNames = row.wsinfoName || this.ids;
      this.$modal.confirm('是否确认删除车间信息编号为"' + wsinfoNames + '"的数据项？').then(function() {
        return delWsinfo(wsinfoNames);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('equmgn/wsinfo/export', {
        ...this.queryParams
      }, `wsinfo_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
