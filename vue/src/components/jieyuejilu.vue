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
import axios from "axios";
const token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyIjp7InJvbGUiOiJ1c2VyIiwiaWQiOjIsImVtYWlsIjoiYWFhIn0sImV4cCI6MTcxNzY0MDcwN30.T0ZlfJjlFONwjdkxImgNJ62LYAXxmM32_vh6WxiGwbU"
const getBookData = () => {
  axios.get(
      "api/searchRecord",
      {
        headers:{
          token:token
        }
      }
  ).then((res) => {
    if (res.data.code == 200) {
      tableData.value = res.data.data.rows
    }else {
      alert("图书数据获取失败")
    }
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