<template>
    <div class="user-page">
        <Index :title="props.title"></Index>
        <el-row>
            <el-col :span="24">
                <div class="grid-content">
                    <el-form :inline="true" class="demo-form-inline" size="large">
                        <el-form-item label="用户名">
                            <el-input v-model="search1" placeholder="请输入用户名"/>
                        </el-form-item>
                        <el-form-item label="姓名">
                            <el-input v-model="search2" placeholder="请输入姓名"/>
                        </el-form-item>
                        <el-form-item>
                            <el-button type="primary" @click="userList">查询</el-button>
                        </el-form-item>
                        <el-form-item>
                            <el-button type="success" @click="addUser">添加{{ props.title }}</el-button>
                        </el-form-item>
                    </el-form>

                    <el-table :data="tableData" stripe border fit>
                        <el-table-column align="center" label="序号" width="60">
                            <template #default="slot">
                                {{ (pageNum - 1) * pageSize + slot.$index + 1 }}
                            </template>
                        </el-table-column>
                        <el-table-column align="center" prop="username" label="用户名"/>
                        <el-table-column align="center" prop="nickname" label="姓名"/>
                        <el-table-column align="center" prop="accountNonLocked" label="状态" width="80">
                            <template #default="slot">
                                <el-switch v-model="slot.row.accountNonLocked" class="ml-2" active-color="#13ce66"
                                           @change="putUser(slot.row.id)"/>
                            </template>
                        </el-table-column>
                        <el-table-column align="center" prop="createdTime" label="创建时间" :formatter="formatTime"/>
                        <el-table-column align="center" prop="" label="操作">
                            <template #default="slot">
                                <el-button @click="$router.push({path:'/admin/userInfo',query:{id:slot.row.id}})">查看
                                </el-button>
                                <el-button type="success" @click="editUser(slot.row.id)">编辑</el-button>
                                <el-button type="danger" @click="del(slot.row.id)">删除</el-button>
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
                                @size-change="userList"
                                @current-change="userList">
                        </el-pagination>
                    </div>
                </div>
            </el-col>
        </el-row>

        <el-dialog v-model="dialogVisible" :title="props.title" width="40%" draggable @closed="resetForm(ruleFormRef)">
            <el-form ref="ruleFormRef" :model="ruleForm" :rules="rules" label-width="120px"
                     class="demo-ruleForm" size="large">
                <el-form-item label="用户名" prop="username">
                    <el-input v-model="ruleForm.username" placeholder="请输入用户名"/>
                </el-form-item>
                <el-form-item label="姓名" prop="nickname">
                    <el-input v-model="ruleForm.nickname" placeholder="请输入姓名"/>
                </el-form-item>
                <el-form-item label="密码" prop="password">
                    <el-input type="password" v-model="ruleForm.password" placeholder="请输入密码"/>
                </el-form-item>
                <el-form-item label="重复密码" prop="password2">
                    <el-input type="password" v-model="ruleForm.password2" placeholder="请输入重复密码"/>
                </el-form-item>
                <el-form-item label="电话号码" prop="tel">
                    <el-input v-model.number="ruleForm.tel" placeholder="请输入电话号码"/>
                </el-form-item>
                <el-form-item label="邮箱" prop="email">
                    <el-input v-model="ruleForm.email" placeholder="请输入邮箱"/>
                </el-form-item>
            </el-form>
            <template #footer>
                <span class="dialog-footer">
                    <el-button @click="resetForm(ruleFormRef)">清空</el-button>
                    <el-button type="success" @click="submitForm(ruleFormRef)">确认</el-button>
                </span>
            </template>
        </el-dialog>
    </div>
</template>

<script setup lang="ts">
import {onMounted, reactive, ref} from "vue";
import {formatTime} from "../../utils/format";
import {delUser, getUser, listUser, postUser, putUser} from "../../api/user";
import {ElMessage, ElMessageBox, FormInstance, FormRules} from "element-plus";
import {validateEmail, validateTel} from "../../utils/validate";
import Index from '../title/Index.vue'

const props = defineProps(['roleId', 'title'])
//------------按条件查询记录-----------------------------------------------------------------------------------
const search1 = ref('')
const search2 = ref('')
const pageNum = ref(1)
const pageSize = ref(10)
const total = ref(0)
const tableData = ref([])
const userList = () => {
    listUser(pageNum.value, pageSize.value, search1.value, search2.value, props.roleId).then(data => {
        tableData.value = data.data.list
        total.value = data.data.total
    })
}
onMounted(userList)
//------------删除记录-------------------------------------------------------------------------------------------
const del = (id: string) => {
    ElMessageBox.confirm('你确认删除该记录吗', '提示', {
        confirmButtonText: '确认', cancelButtonText: '取消', type: 'warning',
    }).then(() => {
        delUser(id).then(() => {
            ElMessage.success('删除成功')
            userList()
        })
    }).catch(() => {
        ElMessage.info('已取消!')
    })
}
//------------添加记录的方法-----------------------------------------------------------------------------------
const addUser = () => {
    ruleForm.id = '0'
    dialogVisible.value = true
}
//------------编辑记录的方法-----------------------------------------------------------------------------------
const editUser = (id: string) => {
    ruleForm.id = id
    getUser(id).then(data => {
        ruleForm.username = data.data.username
        ruleForm.nickname = data.data.nickname
        ruleForm.tel = data.data.tel
        ruleForm.email = data.data.email
        dialogVisible.value = true
    })
}
//------------添加或者编辑记录所使用的数据-----------------------------------------------------------------------------------
const dialogVisible = ref(false)
const ruleFormRef = ref<FormInstance>()
let ruleForm = reactive({
    id: '',
    username: '',
    nickname: '',
    password: '',
    password2: '',
    tel: '',
    email: '',
    roleId: props.roleId,
})
//校验重复密码
const validatePassword2 = (rule: any, value: any, callback: any) => {
    if (value === '') {
        callback(new Error('请再次输入密码!'))
    } else if (value !== ruleForm.password2) {
        callback(new Error("两次密码输入不一致!"))
    } else {
        callback()
    }
}
const rules = reactive<FormRules>({
    username: [
        {required: true, message: '请输入用户名', trigger: 'blur'},
        {min: 3, max: 64, message: '用户名长度在3到64位', trigger: 'blur'},
    ],
    nickname: [
        {required: true, message: '请输入姓名', trigger: 'blur'},
        {min: 3, max: 64, message: '姓名长度在3到64位', trigger: 'blur'},
    ],
    password: [
        {required: true, message: '请输入密码', trigger: 'blur'},
        {min: 3, max: 64, message: '用户名长度在3到64位', trigger: 'blur'},
    ],
    password2: [
        {required: true, message: '请再次输入密码', trigger: 'blur'},
        {validator: validatePassword2, trigger: 'blur'},
    ],
    tel: [
        {required: true, message: '请输入电话号码', trigger: 'blur'},
        {validator: validateTel, trigger: 'blur'},
    ],
    email: [
        {required: true, message: '请输入邮箱', trigger: 'blur'},
        {validator: validateEmail, trigger: 'blur'},
    ],
})
const submitForm = async (formEl: FormInstance | undefined) => {
    if (!formEl) return
    await formEl.validate((valid, fields) => {
        if (valid) {
            postUser(ruleForm).then(data => {
                ElMessage.success(data.message)
                dialogVisible.value = false
            })
        } else {
            ElMessage.error('校验不通过，请重新输入')
        }
    })
}
const resetForm = (formEl: FormInstance | undefined) => {
    if (!formEl) return
    formEl.resetFields()
    ruleForm.username = ''
    ruleForm.nickname = ''
    ruleForm.password = ''
    ruleForm.password2 = ''
    ruleForm.tel = ''
    ruleForm.email = ''
}
</script>

<style scoped lang="less">
.user-page {
    .grid-content {
        padding: 20px;

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