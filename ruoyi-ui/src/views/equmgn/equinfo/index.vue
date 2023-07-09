<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="名称" prop="equinfoName">
        <el-input
          v-model="queryParams.equinfoName"
          placeholder="请输入名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="设备类型" prop="equinfoType">
        <el-select v-model="queryParams.equinfoType" placeholder="请选择设备类型" clearable>
          <el-option
            v-for="dict in dict.type.equinfo_type"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="设备状态" prop="equinfoStatus">
        <el-select v-model="queryParams.equinfoStatus" placeholder="请选择设备状态" clearable>
          <el-option
            v-for="dict in dict.type.equinfo_status"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
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
          v-hasPermi="['equmgn:equinfo:add']"
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
          v-hasPermi="['equmgn:equinfo:edit']"
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
          v-hasPermi="['equmgn:equinfo:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['equmgn:equinfo:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="equinfoList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="名称" align="center" prop="equinfoName" />
      <el-table-column label="品牌" align="center" prop="equinfoBrand" />
      <el-table-column label="型号" align="center" prop="equinfoModel" />
      <el-table-column label="设备类型" align="center" prop="equinfoType">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.equinfo_type" :value="scope.row.equinfoType"/>
        </template>
      </el-table-column>
      <el-table-column label="设备状态" align="center" prop="equinfoStatus">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.equinfo_status" :value="scope.row.equinfoStatus"/>
        </template>
      </el-table-column>
      <el-table-column label="启动状态" align="center" prop="equinfoStartStatus" />
      <el-table-column label="添加时间" align="center" prop="equinfoCreateTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.equinfoCreateTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['equmgn:equinfo:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['equmgn:equinfo:remove']"
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

    <!-- 添加或修改设备信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="名称" prop="equinfoName">
          <el-input v-model="form.equinfoName" placeholder="请输入名称" />
        </el-form-item>
        <el-form-item label="品牌" prop="equinfoBrand">
          <el-input v-model="form.equinfoBrand" placeholder="请输入品牌" />
        </el-form-item>
        <el-form-item label="型号" prop="equinfoModel">
          <el-input v-model="form.equinfoModel" placeholder="请输入型号" />
        </el-form-item>
        <el-form-item label="设备类型" prop="equinfoType">
          <el-select v-model="form.equinfoType" placeholder="请选择设备类型">
            <el-option
              v-for="dict in dict.type.equinfo_type"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="设备状态" prop="equinfoStatus">
          <el-select v-model="form.equinfoStatus" placeholder="请选择设备状态">
            <el-option
              v-for="dict in dict.type.equinfo_status"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="添加时间" prop="equinfoCreateTime">
          <el-date-picker clearable
            v-model="form.equinfoCreateTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择添加时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="设备序列号" prop="equinfoSerialNumber">
          <el-input v-model="form.equinfoSerialNumber" placeholder="请输入设备序列号" />
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
import { listEquinfo, getEquinfo, delEquinfo, addEquinfo, updateEquinfo } from "@/api/equmgn/equinfo";

export default {
  name: "Equinfo",
  dicts: ['equinfo_status', 'equinfo_type'],
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
      // 设备信息表格数据
      equinfoList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        equinfoName: null,
        equinfoType: null,
        equinfoStatus: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        equinfoName: [
          { required: true, message: "名称不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询设备信息列表 */
    getList() {
      this.loading = true;
      listEquinfo(this.queryParams).then(response => {
        this.equinfoList = response.rows;
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
        equinfoName: null,
        equinfoBrand: null,
        equinfoModel: null,
        equinfoType: null,
        equinfoStatus: null,
        equinfoStartStatus: null,
        equinfoCreateTime: null,
        equinfoSerialNumber: null
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
      this.ids = selection.map(item => item.equinfoName)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加设备信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const equinfoName = row.equinfoName || this.ids
      getEquinfo(equinfoName).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改设备信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.equinfoName != null) {
            updateEquinfo(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addEquinfo(this.form).then(response => {
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
      const equinfoNames = row.equinfoName || this.ids;
      this.$modal.confirm('是否确认删除设备信息编号为"' + equinfoNames + '"的数据项？').then(function() {
        return delEquinfo(equinfoNames);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('equmgn/equinfo/export', {
        ...this.queryParams
      }, `equinfo_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
