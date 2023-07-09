<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="所属车间" prop="locinfoAffiliatedWorkshop">
        <el-input
          v-model="queryParams.locinfoAffiliatedWorkshop"
          placeholder="请输入所属车间"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="工位编号" prop="locinfoNumber">
        <el-input
          v-model="queryParams.locinfoNumber"
          placeholder="请输入工位编号"
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
          v-hasPermi="['equmgn:locinfo:add']"
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
          v-hasPermi="['equmgn:locinfo:edit']"
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
          v-hasPermi="['equmgn:locinfo:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['equmgn:locinfo:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="locinfoList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="所属车间" align="center" prop="locinfoAffiliatedWorkshop" />
      <el-table-column label="工位名称" align="center" prop="locinfoName" />
      <el-table-column label="工位编号" align="center" prop="locinfoNumber" />
      <el-table-column label="绑定工序" align="center" prop="locinfoBindOperation" />
      <el-table-column label="绑定设备" align="center" prop="locinfoBindEquipment" />
      <el-table-column label="状态" align="center" prop="locinfoStatus" />
      <el-table-column label="备注" align="center" prop="locinfoRemark" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['equmgn:locinfo:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['equmgn:locinfo:remove']"
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

    <!-- 添加或修改工位信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="所属车间" prop="locinfoAffiliatedWorkshop">
          <el-input v-model="form.locinfoAffiliatedWorkshop" placeholder="请输入所属车间" />
        </el-form-item>
        <el-form-item label="工位名称" prop="locinfoName">
          <el-input v-model="form.locinfoName" placeholder="请输入工位名称" />
        </el-form-item>
        <el-form-item label="工位编号" prop="locinfoNumber">
          <el-input v-model="form.locinfoNumber" placeholder="请输入工位编号" />
        </el-form-item>
        <el-form-item label="绑定工序" prop="locinfoBindOperation">
          <el-input v-model="form.locinfoBindOperation" placeholder="请输入绑定工序" />
        </el-form-item>
        <el-form-item label="绑定设备" prop="locinfoBindEquipment">
          <el-input v-model="form.locinfoBindEquipment" placeholder="请输入绑定设备" />
        </el-form-item>
        <el-form-item label="备注" prop="locinfoRemark">
          <el-input v-model="form.locinfoRemark" placeholder="请输入备注" />
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
import { listLocinfo, getLocinfo, delLocinfo, addLocinfo, updateLocinfo } from "@/api/equmgn/locinfo";

export default {
  name: "Locinfo",
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
      // 工位信息表格数据
      locinfoList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        locinfoAffiliatedWorkshop: null,
        locinfoNumber: null,
        locinfoStatus: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        locinfoName: [
          { required: true, message: "工位名称不能为空", trigger: "blur" }
        ],
        locinfoNumber: [
          { required: true, message: "工位编号不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询工位信息列表 */
    getList() {
      this.loading = true;
      listLocinfo(this.queryParams).then(response => {
        this.locinfoList = response.rows;
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
        locinfoAffiliatedWorkshop: null,
        locinfoName: null,
        locinfoNumber: null,
        locinfoBindOperation: null,
        locinfoBindEquipment: null,
        locinfoStatus: null,
        locinfoRemark: null
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
      this.ids = selection.map(item => item.locinfoAffiliatedWorkshop)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加工位信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const locinfoAffiliatedWorkshop = row.locinfoAffiliatedWorkshop || this.ids
      getLocinfo(locinfoAffiliatedWorkshop).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改工位信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.locinfoAffiliatedWorkshop != null) {
            updateLocinfo(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addLocinfo(this.form).then(response => {
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
      const locinfoAffiliatedWorkshops = row.locinfoAffiliatedWorkshop || this.ids;
      this.$modal.confirm('是否确认删除工位信息编号为"' + locinfoAffiliatedWorkshops + '"的数据项？').then(function() {
        return delLocinfo(locinfoAffiliatedWorkshops);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('equmgn/locinfo/export', {
        ...this.queryParams
      }, `locinfo_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
