<template>
    <Index title="个人中心"></Index>
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
                        {{ user.username }}
                    </el-descriptions-item>
                    <el-descriptions-item>
                        <template #label>
                            <div class="cell-item">
                                姓名
                            </div>
                        </template>
                        {{ user.nickname }}
                    </el-descriptions-item>
                    <el-descriptions-item>
                        <template #label>
                            <div class="cell-item">
                                电话号码
                            </div>
                        </template>
                        {{ user.tel }}
                    </el-descriptions-item>
                    <el-descriptions-item>
                        <template #label>
                            <div class="cell-item">
                                邮箱
                            </div>
                        </template>
                        {{ user.email }}
                    </el-descriptions-item>
                    <el-descriptions-item>
                        <template #label>
                            <div class="cell-item">
                                创建时间
                            </div>
                        </template>
                        {{ formatTime2(user.createdTime) }}
                    </el-descriptions-item>
                </el-descriptions>
                <br>
                <el-button type="success" @click="editUser()">编辑</el-button>
                <el-dialog v-model="dialogVisible" title="修改账号信息" width="40%" draggable
                           @closed="resetForm(ruleFormRef)">
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
        </el-col>
    </el-row>
</template>

<script setup lang="ts">
import Index from '../../components/title/Index.vue'
import {onMounted, reactive, ref} from "vue";
import {me} from "../../api/user";
import {formatTime2} from "../../utils/format";
import {ElMessage, FormInstance, FormRules} from "element-plus";
import {validateEmail, validateTel} from "../../utils/validate";
import {postMain} from "../../api/main";

//-----------页面初始化-----------------------------------------------------------------------------
const user = reactive({
    username: '',
    nickname: '',
    tel: '',
    email: '',
    createdTime: '',
})
const userInfo = () => {
    me().then(data => {
        user.username = data.data.username
        user.nickname = data.data.nickname
        user.tel = data.data.tel
        user.email = data.data.email
        user.createdTime = data.data.createdTime
    })
}
onMounted(userInfo)
//-----------编辑用户-----------------------------------------------------------------------------
const editUser = () => {
    me().then(data => {
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
    username: '',
    nickname: '',
    password: '',
    password2: '',
    tel: '',
    email: '',
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
            postMain(ruleForm).then(data => {
                ElMessage.success(data.message)
                userInfo()
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
.grid-content {
    padding: 20px;
}
</style>