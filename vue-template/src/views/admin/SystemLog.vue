<template>
    <div class="systemLog-page">
        <Index title="系统日志"></Index>
        <el-row>
            <el-col :span="24">
                <div class="grid-content">
                    <el-form :inline="true" class="demo-form-inline" size="large">
                        <el-form-item label="用户名">
                            <el-input v-model="search1" placeholder="请输入用户名"/>
                        </el-form-item>
                        <el-form-item>
                            <el-button type="primary" @click="systemLogList">查询</el-button>
                        </el-form-item>
                        <el-form-item>
                            <el-button type="danger" @click="delBatch">批量删除</el-button>
                        </el-form-item>
                        <el-form-item>
                            <el-button type="danger" @click="delByCondition('0')">删除全部</el-button>
                        </el-form-item>
                    </el-form>

                    <el-table :data="tableData" stripe border fit ref="multipleTableRef"
                              @selection-change="handleSelectionChange">
                        <el-table-column type="selection" width="55"/>
                        <el-table-column align="center" label="序号" width="60">
                            <template #default="slot">
                                {{ (pageNum - 1) * pageSize + slot.$index + 1 }}
                            </template>
                        </el-table-column>
                        <el-table-column align="center" prop="username" label="用户名"/>
                        <el-table-column align="center" prop="createdTime" label="创建时间" :formatter="formatTime"/>
                        <el-table-column align="center" prop="" label="操作" width="200">
                            <template #default="slot">
                                <el-button @click="$router.push({path:'/admin/systemLogInfo',query:{id:slot.row.id}})">
                                    查看
                                </el-button>
                                <el-button type="danger" @click="delByCondition(slot.row.id)">删除</el-button>
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
                                @size-change="systemLogList"
                                @current-change="systemLogList">
                        </el-pagination>
                    </div>
                </div>
            </el-col>
        </el-row>
    </div>
</template>

<script setup lang="ts">
import {onMounted, ref} from "vue";
import {delSystemLog, listSystemLog} from "../../api/systemLog";
import Index from '../../components/title/Index.vue'
import {formatTime} from "../../utils/format";
import {ElMessage, ElMessageBox, ElTable} from "element-plus";

//------------按条件查询记录-----------------------------------------------------------------------------------
const search1 = ref('')
const pageNum = ref(1)
const pageSize = ref(10)
const total = ref(0)
const tableData = ref([])
const systemLogList = () => {
    listSystemLog(pageNum.value, pageSize.value, search1.value).then(data => {
        tableData.value = data.data.list
        total.value = data.data.total
    })
}
onMounted(systemLogList)

//------------删除记录-----------------------------------------------------------------------------------
interface ids {
    id: string
}

const multipleTableRef = ref<InstanceType<typeof ElTable>>()
const multipleSelection = ref<ids[]>([])
const handleSelectionChange = (val: ids[]) => {
    multipleSelection.value = val
}
const delBatch = () => {
    let ids = ''
    multipleSelection.value.forEach(i => {
        ids = ids === '' ? ids + i.id : ids + ',' + i.id;
    })
    delByCondition(ids)
}
const delByCondition = (ids: string) => {
    ElMessageBox.confirm('你确认删除该记录吗', '提示', {
        confirmButtonText: '确认', cancelButtonText: '取消', type: 'warning',
    }).then(() => {
        delSystemLog(ids).then(() => {
            ElMessage.success('删除成功')
            systemLogList()
        })
    }).catch(() => {
        ElMessage.info('已取消!')
    })
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