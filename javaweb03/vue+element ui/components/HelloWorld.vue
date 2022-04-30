<template>
  <div>
    <div id="head">
      <el-button type="primary" @click="addFormVisible = true">添加课程</el-button>
      <el-input placeholder="请输入内容" v-model="searchValue" class="input-with-select">
        <span slot="prepend">课程名称</span>
        <el-button slot="append" icon="el-icon-search" @click="search"></el-button>
      </el-input>
      <el-button type="primary" @click="courseList">重 置</el-button>
    </div>
    <div id="list">
      <el-table :data="tableData" style="width: 100%">
        <el-table-column label="封面" width="180" align="center">
          <template slot-scope="scope">
            <img :src="scope.row.courseImg" alt="" style="width: 100px; height: 100px;">
          </template>
        </el-table-column>
        <el-table-column label="课程名称" width="180" align="center">
          <template slot-scope="scope">
            <span style="margin-left: 10px">{{ scope.row.courseName }}</span>
          </template>
        </el-table-column>
        <el-table-column label="课时" width="180" align="center">
          <template slot-scope="scope">
            <span style="margin-left: 10px">{{ scope.row.courseHours }}</span>
          </template>
        </el-table-column>
        <el-table-column label="所属学院" width="180" align="center">
          <template slot-scope="scope">
            <span style="margin-left: 10px">{{ scope.row.academyName }}</span>
          </template>
        </el-table-column>
        <el-table-column label="操作" align="center">
          <template slot-scope="scope">
            <el-button size="mini" @click="handleEdit(scope.$index, scope.row)">编 辑</el-button>
            <el-button size="mini" type="danger" @click="handleDelete(scope.$index, scope.row)">删 除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <div id="add">
      <el-dialog title="添加课程" :visible.sync="addFormVisible">
        <el-form :model="courseInfo" ref="courseInfo">
          <el-form-item label="课程名称" :label-width="formLabelWidth">
            <el-input v-model="courseInfo.courseName" autocomplete="off" placeholder="请输入课程名称"></el-input>
          </el-form-item>
          <el-form-item label="课时" :label-width="formLabelWidth">
            <el-input v-model="courseInfo.courseHours" autocomplete="off" placeholder="请输入课时"></el-input>
          </el-form-item>
          <el-form-item label="所属学院" :label-width="formLabelWidth">
            <el-select v-model="courseInfo.academyId" placeholder="请选择学院">
              <el-option label="计算机科学与工程学院" value="1"></el-option>
              <el-option label="外国语学院" value="2"></el-option>
              <el-option label="数学学院" value="3"></el-option>
              <el-option label="物理学院" value="9"></el-option>
              <el-option label="马克思主义学院" value="13"></el-option>
              <el-option label="经济学院" value="14"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="课程封面" :label-width="formLabelWidth">
            <el-upload class="avatar-uploader" ref="upload" action="http://localhost:8080/course/add"
              :auto-upload="false" :limit="1" :data="courseInfo" :on-success="addSuccess">
              <i class="el-icon-plus avatar-uploader-icon"></i>
            </el-upload>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="addFormVisible = false">取 消</el-button>
          <el-button type="primary" @click="addSubmit">确 定</el-button>
        </div>
      </el-dialog>
    </div>

    <div id="update">
      <el-dialog title="课程修改" :visible.sync="updateFormVisible">
        <el-form :model="updateCourseInfo" ref="updateCourseInfo">
          <el-form-item label="课程名称" :label-width="formLabelWidth">
            <el-input v-model="updateCourseInfo.courseName" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="课时" :label-width="formLabelWidth">
            <el-input v-model="updateCourseInfo.courseHours" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="所属学院" :label-width="formLabelWidth">
            <el-select v-model="updateCourseInfo.academyId">
              <el-option label="计算机科学与工程学院" value="1"></el-option>
              <el-option label="外国语学院" value="2"></el-option>
              <el-option label="数学学院" value="3"></el-option>
              <el-option label="物理学院" value="9"></el-option>
              <el-option label="马克思主义学院" value="13"></el-option>
              <el-option label="经济学院" value="14"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="课程封面" :label-width="formLabelWidth">
            <el-upload class="avatar-uploader" ref="update" action="http://localhost:8080/course/update"
              :auto-upload="false" :limit="1" :data="updateCourseInfo" :on-success="updateSuccess">
              <i class="el-icon-plus avatar-uploader-icon"></i>
            </el-upload>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="updateFormVisible = false">取 消</el-button>
          <el-button type="primary" @click="updateSubmit">确 定</el-button>
        </div>
      </el-dialog>
    </div>
  </div>
</template>

<script>
  import axios from "axios";
  export default {
    data() {
      return {
        length: null,
        tableData: null,
        addFormVisible: false,
        updateFormVisible: false,
        courseInfo: {
          courseId: 0,
          courseName: '',
          courseHours: '',
          academyId: ''
        },
        updateCourseInfo: {
          courseId: 0,
          courseName: '',
          courseHours: '',
          academyId: ''
        },
        formLabelWidth: '120px',
        searchValue: ''
      }
    },
    mounted() {
      this.courseList();
    },
    methods: {
      handleEdit(index, row) {
        this.updateFormVisible = true;
        this.updateCourseInfo = row;
        this.updateCourseInfo.academyId = String(row.academyId);
      },
      handleDelete(index, row) {
        this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.deleteCourse(row)
          this.$message({
            type: 'success',
            message: '删除成功!'
          });
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });
        });
      },
      courseList() {
        axios.get("http://localhost:8080/course/list")
          .then(response => {
            this.length = response.data.length;
            this.tableData = response.data.value;
          }).catch(function(error) {

          });
      },
      deleteCourse(row) {
        var url = "http://localhost:8080/course/delete?courseId=" + row.courseId;
        axios.get(url)
          .then(response => {
            this.courseList();
          }).catch(function(error) {});
      },
      async addSubmit() {
        this.$refs["courseInfo"].validate(valid => {
          this.$refs.upload.submit();
        });
      },
      addSuccess(res) {
        if (!res) {
          this.$message.error("课程名重复！");
        } else {
          this.$message.success("添加成功！");
          this.courseInfo = {};
          this.courseList();
          this.$refs.upload.clearFiles();
          this.addFormVisible = false;
        }
      },
      async updateSubmit() {
        this.$refs["updateCourseInfo"].validate(valid => {
          this.$refs.update.submit();
        });
      },
      updateSuccess(res) {
        if (!res) {
          this.$message.error("课程名重复！");
        } else {
          this.$message.success("修改成功！");
          this.updateCourseInfo = {};
          this.courseList();
          this.$refs.update.clearFiles();
          this.updateFormVisible = false;
        }
      },
      search() {
        if (this.searchValue === '') {
          this.$message.error("请输入课程名称！");
          return;
        }
        let url = "http://localhost:8080/course/search?courseName=" + this.searchValue;
        axios.get(url)
        .then(response => {
          this.length = response.data.length;
          this.tableData = response.data.value;
        })
        .catch(function(error) {
        });
        this.searchValue = ''
        }
      }
    }
</script>

<style>
  .el-table {
    margin: auto;
    max-width: 1000px;
  }

  #head {
    text-align: center;
  }

  .avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }

  .avatar-uploader .el-upload:hover {
    border-color: #409EFF;
  }

  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 100px;
    height: 100px;
    line-height: 100px;
    text-align: center;
  }

  .el-select .el-input {
    width: 130px;
  }

  .input-with-select {
    margin-left: 20px;
    margin-right: 20px;
    background-color: #fff;
    width: 600px;
  }
</style>
