<template>
    <div class="systemLog-page">
        <Index title="运行日志"></Index>
        <el-row>
            <el-col :span="24">
                <div class="grid-content">
                    <el-form :inline="true" class="demo-form-inline" size="large">
                        <el-form-item>
                            <el-button type="danger" @click="delByCondition('0')">删除全部</el-button>
                        </el-form-item>
                    </el-form>

                    <el-table :data="tableData" stripe border fit ref="multipleTableRef">
                        <!--                        <el-table-column type="selection" width="55"/>-->
                        <el-table-column align="center" label="序号" width="60">
                            <template #default="slot">
                                {{ (pageNum - 1) * pageSize + slot.$index + 1 }}
                            </template>
                        </el-table-column>
                        <el-table-column align="center" label="文件名">
                            <template #default="slot">
                                {{ slot.row }}
                            </template>
                        </el-table-column>
                        <el-table-column align="center" prop="" label="操作" width="200">
                            <template #default="slot">
                                <el-button type="success" @click="download(slot.row)">下载</el-button>
                                <el-button type="danger" @click="delByCondition(slot.row)">删除</el-button>
                            </template>
                        </el-table-column>
                    </el-table>

                    <div class="demo-pagination-block">
                        <el-pagination
                                v-model:currentPage="pageNum"
                                v-model:page-size="pageSize"
                                :page-sizes="[10, 20, 50, 100]"
                                layout="total, sizes, prev, pager, next, jumper"
                                :total="total"
                                @size-change="runLogList"
                                @current-change="runLogList">
                        </el-pagination>
                    </div>
                </div>
            </el-col>
        </el-row>
    </div>
</template>

<script setup lang="ts">
import {onMounted, ref} from "vue";
import Index from '../../components/title/Index.vue'
import {ElMessage, ElMessageBox, ElTable} from "element-plus";
import {delRunLog, downloadFile, listRunLog} from "../../api/runLog";

//------------按条件查询记录-----------------------------------------------------------------------------------
const pageNum = ref(1)
const pageSize = ref(10)
const total = ref(0)
const tableData = ref([])
const runLogList = () => {
    listRunLog(pageNum.value, pageSize.value).then(data => {
        tableData.value = data.data.list
        total.value = data.data.total
    })
}
onMounted(runLogList)

//------------删除记录-----------------------------------------------------------------------------------
const delByCondition = (id: string) => {
    ElMessageBox.confirm('你确认删除该记录吗', '提示', {
        confirmButtonText: '确认', cancelButtonText: '取消', type: 'warning',
    }).then(() => {
        delRunLog(id).then(() => {
            ElMessage.success('删除成功')
            runLogList()
        })
    }).catch(() => {
        ElMessage.info('已取消!')
    })
}
//------------删除记录-----------------------------------------------------------------------------------
const download = (fileName: string) => {
    downloadFile(fileName).then(data => {
        const reader = new FileReader()
        reader.readAsDataURL(data)
        reader.onload = (e) => {
            const a = document.createElement('a')
            a.download = fileName
            a.href = e.target.result
            document.body.appendChild(a)
            a.click()
            document.body.removeChild(a)
        }
        ElMessage.success('下载成功')
    }).catch(res => {
        ElMessage.success('下载失败')
    });
}
</script>

<style scoped lang="less">
.systemLog-page {
    .grid-content {
        padding: 20px;

        .el-table {

        }

        .demo-pagination-block {
            float: right;
            margin-right: 20px;
            margin-top: 10px;
            margin-bottom: 16px;
        }
    }
}

.dialog-footer button:first-child {
    margin-right: 10px;
}
</style>