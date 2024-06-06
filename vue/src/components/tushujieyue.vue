<template>
  <el-table :data="filterTableData" style="width: 100%">
    <el-table-column label="图书名称" prop="book_name" />
    <el-table-column label="图书作者" prop="book_author" />
    <el-table-column label="出版社" prop="book_press" />
    <el-table-column label="书籍状态" prop="book_status" />
    <el-table-column label="借阅人" prop="book_borrower" />
    <el-table-column label="借阅时间" prop="book_returntime" />
    <el-table-column label="预计归还时间" prop="book_uploadtime" />
    <el-table-column align="right">
      <template #header>
        <el-input v-model="search" size="small" placeholder="查找图书" />
      </template>
      <template #default="scope">
        <el-button size="small" @click="open()">
          编辑
        </el-button>
        <el-button
            size="small"
            type="danger"
            @click="handleDelete(scope.$index, scope.row)"
        >
          借阅
        </el-button>
      </template>
    </el-table-column>
  </el-table>
  <div class="page_">
    <el-pagination
        small
        background
        layout="prev, pager, next"
        :total="50"
        class="mt-4"
        style="margin-left: 36%"
    />
  </div>
</template>
<style scoped>
.page_{
  position: absolute;
  bottom: 7%;
  width: 100%;
  height: 3%;
}
</style>
<script lang="ts" setup>

import { computed, ref } from 'vue'
import axios from "axios"

const getBookData = () => {
  axios.get(
      "api/getPageBooks",
      {
        headers:{
          token:getTokenCookie()
        }
      }
  ).then((res) => {
    if (res.data.code == 200) {
      tableData.value = res.data.data.rows
    }else {
      alert("图书数据获取失败,或者未登录")
      router.push("/")
    }
  })
}

import { ElMessage, ElMessageBox } from 'element-plus'
import {getTokenCookie} from "@/components/TokenService";
import router from "@/router";

const open = (book_data) => {
  const data_ = tableData.value.at(book_data)
  ElMessageBox.prompt("是否要编辑 《"+data_.book_name+"》 ?", '图书编辑', {
    confirmButtonText: 'OK',
    cancelButtonText: 'Cancel',
    inputPattern:
        /[\w!#$%&'*+/=?^_`{|}~-]+(?:\.[\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\w](?:[\w-]*[\w])?\.)+[\w](?:[\w-]*[\w])?/,
    inputErrorMessage: 'Invalid Email',
  })
      .then(({ value }) => {
        ElMessage({
          type: 'success',
          message: `Your email is:${value}`,
        })
      })
      .catch(() => {
        ElMessage({
          type: 'info',
          message: 'Input canceled',
        })
      })
}
getBookData()
interface User {
  date: string
  name: string
  address: string
}
const tableData = ref([])
const search = ref('')
const filterTableData = computed(() =>
    tableData.value.filter(
        (data) =>
            !search.value ||
            data.book_name.toLowerCase().includes(search.value.toLowerCase())
    )
)
const handleEdit = (index: number, row: User) => {
  console.log(index, row)
}
const handleDelete = (index: number, row: User) => {
  console.log(index, row)
}






</script>
`