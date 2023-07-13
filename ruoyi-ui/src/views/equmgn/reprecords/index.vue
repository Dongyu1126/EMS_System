<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="工单号" prop="reprecordsTicketNumber">
        <el-input
          v-model="queryParams.reprecordsTicketNumber"
          placeholder="请输入工单号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="产品" prop="reprecordsProduct">
        <el-input
          v-model="queryParams.reprecordsProduct"
          placeholder="请输入产品"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="工序" prop="reprecordsProcess">
        <el-input
          v-model="queryParams.reprecordsProcess"
          placeholder="请输入工序"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="报工人" prop="reprecordsReportName">
        <el-input
          v-model="queryParams.reprecordsReportName"
          placeholder="请输入报工人"
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
          v-hasPermi="['equmgn:reprecords:add']"
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
          v-hasPermi="['equmgn:reprecords:edit']"
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
          v-hasPermi="['equmgn:reprecords:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['equmgn:reprecords:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="reprecordsList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="工单号" align="center" prop="reprecordsTicketNumber" />
      <el-table-column label="产品" align="center" prop="reprecordsProduct" />
      <el-table-column label="排产数量" align="center" prop="reprecordsScheduleNumber" />
      <el-table-column label="工序" align="center" prop="reprecordsProcess" />
      <el-table-column label="报工单号" align="center" prop="reprecordsReportTicketNumber" />
      <el-table-column label="报工时间" align="center" prop="reprecordsReportTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.reprecordsReportTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="报工数量" align="center" prop="reprecordsReportNumber" />
      <el-table-column label="报工人" align="center" prop="reprecordsReportName" />
      <el-table-column label="车间" align="center" prop="reprecordsWorkshop" />
      <el-table-column label="工位" align="center" prop="reprecordsWorkLocation" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['equmgn:reprecords:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['equmgn:reprecords:remove']"
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

    <!-- 添加或修改报工记录对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listReprecords, getReprecords, delReprecords, addReprecords, updateReprecords } from "@/api/equmgn/reprecords";

export default {
  name: "Reprecords",
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
      // 报工记录表格数据
      reprecordsList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        reprecordsTicketNumber: null,
        reprecordsProduct: null,
        reprecordsProcess: null,
        reprecordsReportName: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询报工记录列表 */
    getList() {
      this.loading = true;
      listReprecords(this.queryParams).then(response => {
        this.reprecordsList = response.rows;
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
        reprecordsTicketNumber: null,
        reprecordsProduct: null,
        reprecordsScheduleNumber: null,
        reprecordsProcess: null,
        reprecordsReportTicketNumber: null,
        reprecordsReportTime: null,
        reprecordsReportNumber: null,
        reprecordsReportName: null,
        reprecordsWorkshop: null,
        reprecordsWorkLocation: null
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
      this.ids = selection.map(item => item.reprecordsTicketNumber)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加报工记录";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const reprecordsTicketNumber = row.reprecordsTicketNumber || this.ids
      getReprecords(reprecordsTicketNumber).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改报工记录";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.reprecordsTicketNumber != null) {
            updateReprecords(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addReprecords(this.form).then(response => {
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
      const reprecordsTicketNumbers = row.reprecordsTicketNumber || this.ids;
      this.$modal.confirm('是否确认删除报工记录编号为"' + reprecordsTicketNumbers + '"的数据项？').then(function() {
        return delReprecords(reprecordsTicketNumbers);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('equmgn/reprecords/export', {
        ...this.queryParams
      }, `reprecords_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
