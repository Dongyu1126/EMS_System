<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="生产状态" prop="proworkordersProductStatus">
        <el-select v-model="queryParams.proworkordersProductStatus" placeholder="请选择生产状态" clearable>
          <el-option
            v-for="dict in dict.type.pwo_product_status"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="工单编号" prop="proworkordersOrderNumber">
        <el-input
          v-model="queryParams.proworkordersOrderNumber"
          placeholder="请输入工单编号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="产品名称" prop="proworkordersProductName">
        <el-input
          v-model="queryParams.proworkordersProductName"
          placeholder="请输入产品名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="批次号" prop="proworkordersBatchNumber">
        <el-input
          v-model="queryParams.proworkordersBatchNumber"
          placeholder="请输入批次号"
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
          v-hasPermi="['equmgn:proworkorders:add']"
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
          v-hasPermi="['equmgn:proworkorders:edit']"
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
          v-hasPermi="['equmgn:proworkorders:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['equmgn:proworkorders:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="proworkordersList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="生产状态" align="center" prop="proworkordersProductStatus">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.pwo_product_status" :value="scope.row.proworkordersProductStatus"/>
        </template>
      </el-table-column>
      <el-table-column label="工单编号" align="center" prop="proworkordersOrderNumber" />
      <el-table-column label="产品编号" align="center" prop="proworkordersProductNumber" />
      <el-table-column label="产品名称" align="center" prop="proworkordersProductName" />
      <el-table-column label="批次号" align="center" prop="proworkordersBatchNumber" />
      <el-table-column label="单位" align="center" prop="proworkordersUint" />
      <el-table-column label="生产数量" align="center" prop="proworkordersProductionQuantity" />
      <el-table-column label="计划完成日期" align="center" prop="proworkordersPlanCompDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.proworkordersPlanCompDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="开始完成日期" align="center" prop="proworkordersStartProduceDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.proworkordersStartProduceDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="实际完成日期" align="center" prop="proworkordersActualCompDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.proworkordersActualCompDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="创建时间" align="center" prop="proworkordersCreateTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.proworkordersCreateTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['equmgn:proworkorders:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['equmgn:proworkorders:remove']"
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

    <!-- 添加或修改生产工单对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="批次号" prop="proworkordersBatchNumber">
          <el-input v-model="form.proworkordersBatchNumber" placeholder="请输入批次号" />
        </el-form-item>
        <el-form-item label="单位" prop="proworkordersUint">
          <el-input v-model="form.proworkordersUint" placeholder="请输入单位" />
        </el-form-item>
        <el-form-item label="生产数量" prop="proworkordersProductionQuantity">
          <el-input v-model="form.proworkordersProductionQuantity" placeholder="请输入生产数量" />
        </el-form-item>
        <el-form-item label="计划完成日期" prop="proworkordersPlanCompDate">
          <el-date-picker clearable
            v-model="form.proworkordersPlanCompDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择计划完成日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="开始完成日期" prop="proworkordersStartProduceDate">
          <el-date-picker clearable
            v-model="form.proworkordersStartProduceDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择开始完成日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="实际完成日期" prop="proworkordersActualCompDate">
          <el-date-picker clearable
            v-model="form.proworkordersActualCompDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择实际完成日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="创建时间" prop="proworkordersCreateTime">
          <el-date-picker clearable
            v-model="form.proworkordersCreateTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择创建时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="创建人" prop="proworkordersInventor">
          <el-input v-model="form.proworkordersInventor" placeholder="请输入创建人" />
        </el-form-item>
        <el-form-item label="物料" prop="proworkordersMaterial">
          <el-input v-model="form.proworkordersMaterial" placeholder="请输入物料" />
        </el-form-item>
        <el-form-item label="状态" prop="proworkordersStatus">
          <el-radio-group v-model="form.proworkordersStatus">
            <el-radio
              v-for="dict in dict.type.sys_normal_disable"
              :key="dict.value"
              :label="parseInt(dict.value)"
            >{{dict.label}}</el-radio>
          </el-radio-group>
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
import { listProworkorders, getProworkorders, delProworkorders, addProworkorders, updateProworkorders } from "@/api/equmgn/proworkorders";

export default {
  name: "Proworkorders",
  dicts: ['pwo_product_status', 'sys_normal_disable'],
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
      // 生产工单表格数据
      proworkordersList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        proworkordersProductStatus: null,
        proworkordersOrderNumber: null,
        proworkordersProductName: null,
        proworkordersBatchNumber: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        proworkordersOrderNumber: [
          { required: true, message: "工单编号不能为空", trigger: "blur" }
        ],
        proworkordersProductionQuantity: [
          { required: true, message: "生产数量不能为空", trigger: "blur" }
        ],
        proworkordersMaterial: [
          { required: true, message: "物料不能为空", trigger: "blur" }
        ],
        proworkordersStatus: [
          { required: true, message: "状态不能为空", trigger: "change" }
        ]
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询生产工单列表 */
    getList() {
      this.loading = true;
      listProworkorders(this.queryParams).then(response => {
        this.proworkordersList = response.rows;
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
        proworkordersProductStatus: null,
        proworkordersOrderNumber: null,
        proworkordersProductNumber: null,
        proworkordersProductName: null,
        proworkordersBatchNumber: null,
        proworkordersUint: null,
        proworkordersProductionQuantity: null,
        proworkordersPlanCompDate: null,
        proworkordersStartProduceDate: null,
        proworkordersActualCompDate: null,
        proworkordersCreateTime: null,
        proworkordersInventor: null,
        proworkordersMaterial: null,
        proworkordersStatus: null
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
      this.ids = selection.map(item => item.proworkordersProductStatus)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加生产工单";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const proworkordersProductStatus = row.proworkordersProductStatus || this.ids
      getProworkorders(proworkordersProductStatus).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改生产工单";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.proworkordersProductStatus != null) {
            updateProworkorders(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addProworkorders(this.form).then(response => {
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
      const proworkordersProductStatuss = row.proworkordersProductStatus || this.ids;
      this.$modal.confirm('是否确认删除生产工单编号为"' + proworkordersProductStatuss + '"的数据项？').then(function() {
        return delProworkorders(proworkordersProductStatuss);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('equmgn/proworkorders/export', {
        ...this.queryParams
      }, `proworkorders_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
