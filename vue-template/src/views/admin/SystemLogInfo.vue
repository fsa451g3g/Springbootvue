<template>
    <Index title="系统日志详情"></Index>
    <el-row>
        <el-col :span="24">
            <div class="grid-content">
                <el-descriptions
                        class="margin-top"
                        :column="1"
                        border>
                    <el-descriptions-item>
                        <template #label>
                            <div class="cell-item">
                                用户名
                            </div>
                        </template>
                        {{ systemLog.username }}
                    </el-descriptions-item>
                    <el-descriptions-item>
                        <template #label>
                            <div class="cell-item">
                                请求路径
                            </div>
                        </template>
                        {{ systemLog.url }}
                    </el-descriptions-item>
                    <el-descriptions-item>
                        <template #label>
                            <div class="cell-item">
                                请求方法
                            </div>
                        </template>
                        {{ systemLog.method }}
                    </el-descriptions-item>
                    <el-descriptions-item>
                        <template #label>
                            <div class="cell-item">
                                请求参数
                            </div>
                        </template>
                        {{ systemLog.args }}
                    </el-descriptions-item>
                    <el-descriptions-item>
                        <template #label>
                            <div class="cell-item">
                                结果
                            </div>
                        </template>
                        {{ systemLog.result ? '成功' : '失败' }}
                    </el-descriptions-item>
                    <el-descriptions-item>
                        <template #label>
                            <div class="cell-item">
                                异常信息
                            </div>
                        </template>
                        {{ systemLog.message }}
                    </el-descriptions-item>
                    <el-descriptions-item>
                        <template #label>
                            <div class="cell-item">
                                控制器方法
                            </div>
                        </template>
                        {{ systemLog.classMethod }}
                    </el-descriptions-item>
                    <el-descriptions-item>
                        <template #label>
                            <div class="cell-item">
                                创建时间
                            </div>
                        </template>
                        {{ formatTime2(systemLog.createdTime) }}
                    </el-descriptions-item>
                </el-descriptions>
                <br>
                <el-button type="danger" @click="delByCondition(id)">删除</el-button>
                <el-button @click="router.go(-1)">返回</el-button>
            </div>
        </el-col>
    </el-row>
</template>

<script setup lang="ts">
import router from "../../router";
import Index from '../../components/title/Index.vue'
import {onMounted, reactive} from "vue";
import {formatTime2} from "../../utils/format";
import {ElMessage, ElMessageBox} from "element-plus";
import {delSystemLog, getSystemLog} from "../../api/systemLog";

const id = router.currentRoute.value.query.id;
//-----------页面初始化-----------------------------------------------------------------------------
const systemLog = reactive({
    username: '',
    url: '',
    method: '',
    args: '',
    result: '',
    message: '',
    createdTime: '',
    classMethod: '',
})
const systemLogInfo = () => {
    getSystemLog(id).then(data => {
        console.log(data.data)
        systemLog.username = data.data.username
        systemLog.url = data.data.url
        systemLog.method = data.data.method
        systemLog.args = data.data.args
        systemLog.result = data.data.result
        systemLog.message = data.data.message
        systemLog.createdTime = data.data.createdTime
        systemLog.classMethod = data.data.classMethod
    })
}
onMounted(systemLogInfo)
//------------删除记录-------------------------------------------------------------------------------------------
const delByCondition = (id: string) => {
    ElMessageBox.confirm('你确认删除该记录吗', '提示', {
        confirmButtonText: '确认', cancelButtonText: '取消', type: 'warning',
    }).then(() => {
        delSystemLog(id).then(() => {
            ElMessage.success('删除成功')
            router.go(-1)
        })
    }).catch(() => {
        ElMessage.info('已取消!')
    })
}
</script>

<style scoped lang="less">
.grid-content {
    padding: 20px;
}
</style>