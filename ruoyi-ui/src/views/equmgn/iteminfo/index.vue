<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="名称" prop="iteminfoName">
        <el-input
          v-model="queryParams.iteminfoName"
          placeholder="请输入名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="规格型号" prop="iteminfoSpecificationsModels">
        <el-input
          v-model="queryParams.iteminfoSpecificationsModels"
          placeholder="请输入规格型号"
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
          v-hasPermi="['equmgn:iteminfo:add']"
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
          v-hasPermi="['equmgn:iteminfo:edit']"
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
          v-hasPermi="['equmgn:iteminfo:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['equmgn:iteminfo:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="iteminfoList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="编号" align="center" prop="iteminfoNumber" />
      <el-table-column label="名称" align="center" prop="iteminfoName" />
      <el-table-column label="规格型号" align="center" prop="iteminfoSpecificationsModels" />
      <el-table-column label="计量单位" align="center" prop="iteminfoMeasureUnit" />
      <el-table-column label="物料来源" align="center" prop="iteminfoSource" />
      <el-table-column label="状态" align="center" prop="iteminfoStatus" />
      <el-table-column label="添加时间" align="center" prop="iteminfoCreateTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.iteminfoCreateTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['equmgn:iteminfo:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['equmgn:iteminfo:remove']"
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

    <!-- 添加或修改物料信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="名称" prop="iteminfoName">
          <el-input v-model="form.iteminfoName" placeholder="请输入名称" />
        </el-form-item>
        <el-form-item label="规格型号" prop="iteminfoSpecificationsModels">
          <el-input v-model="form.iteminfoSpecificationsModels" placeholder="请输入规格型号" />
        </el-form-item>
        <el-form-item label="计量单位" prop="iteminfoMeasureUnit">
          <el-input v-model="form.iteminfoMeasureUnit" placeholder="请输入计量单位" />
        </el-form-item>
        <el-form-item label="物料来源" prop="iteminfoSource">
          <el-input v-model="form.iteminfoSource" placeholder="请输入物料来源" />
        </el-form-item>
        <el-form-item label="添加时间" prop="iteminfoCreateTime">
          <el-date-picker clearable
            v-model="form.iteminfoCreateTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择添加时间">
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
import { listIteminfo, getIteminfo, delIteminfo, addIteminfo, updateIteminfo } from "@/api/equmgn/iteminfo";

export default {
  name: "Iteminfo",
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
      // 物料信息表格数据
      iteminfoList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        iteminfoName: null,
        iteminfoSpecificationsModels: null,
        iteminfoStatus: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        iteminfoNumber: [
          { required: true, message: "编号不能为空", trigger: "blur" }
        ],
        iteminfoName: [
          { required: true, message: "名称不能为空", trigger: "blur" }
        ],
        iteminfoMeasureUnit: [
          { required: true, message: "计量单位不能为空", trigger: "blur" }
        ],
        iteminfoSource: [
          { required: true, message: "物料来源不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询物料信息列表 */
    getList() {
      this.loading = true;
      listIteminfo(this.queryParams).then(response => {
        this.iteminfoList = response.rows;
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
        iteminfoNumber: null,
        iteminfoName: null,
        iteminfoSpecificationsModels: null,
        iteminfoMeasureUnit: null,
        iteminfoSource: null,
        iteminfoStatus: null,
        iteminfoCreateTime: null
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
      this.ids = selection.map(item => item.iteminfoNumber)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加物料信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const iteminfoNumber = row.iteminfoNumber || this.ids
      getIteminfo(iteminfoNumber).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改物料信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.iteminfoNumber != null) {
            updateIteminfo(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addIteminfo(this.form).then(response => {
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
      const iteminfoNumbers = row.iteminfoNumber || this.ids;
      this.$modal.confirm('是否确认删除物料信息编号为"' + iteminfoNumbers + '"的数据项？').then(function() {
        return delIteminfo(iteminfoNumbers);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('equmgn/iteminfo/export', {
        ...this.queryParams
      }, `iteminfo_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
