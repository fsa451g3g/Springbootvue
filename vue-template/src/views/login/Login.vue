<template>
  <div style=";width: 100vw;height: 100vh;display: flex;overflow: hidden">
    <div style="width: 75%;height:100vh;background-color: blue">
      <el-image style="width: 100%;height: 100%;" fit="cover"
                src="https://q6.itc.cn/q_70/images03/20240126/4eaa7950e4214e8e83965f851318a03d.jpeg"/>
    </div>
    <div class="welcome-title">
      <div style="font-size: 30px;font-weight: bold">欢迎来到某某平台</div>
      <div style="margin-top: 10px">
        一起来玩耍啊！一起来玩耍啊一起来玩耍啊一起来玩耍啊一起来玩耍啊一起来玩耍啊一起来玩耍啊一起来玩耍啊
      </div>
    </div>
    <div style="width: 25%;height:100vh;background-color: white">
      <div style="text-align: center;margin: 0 20px;">
        <div style="margin-top: 20vh">
          <div style="font-size: 40px;font-weight: bold">登录</div>
        </div>
        <div style="margin-top: 20px">
          <el-form
              size="large"
              ref="ruleFormRef"
              :model="formData"
              status-icon
              :rules="rules"
              label-width="auto"
              class="demo-ruleForm"
          >
            <el-form-item label="账号" prop="username">
              <el-input v-model="formData.username" type="text" placeholder="请输入用户名或邮箱"/>
            </el-form-item>
            <el-form-item label="密码" prop="password">
              <el-input v-model="formData.password" type="password" placeholder="请输入密码"/>
            </el-form-item>
            <div style="margin-top: 10px">
              <el-row>
                <el-col :span="12" style="text-align: left"></el-col>
                <el-col :span="12" style="text-align: right">
                  <el-link>忘记密码？</el-link>
                </el-col>
              </el-row>
            </div>
            <el-form-item style="margin-left: 130px">
              <el-button type="success" @click="submitForm(ruleFormRef)">登录</el-button>
              <el-button @click="resetForm(ruleFormRef)">清空</el-button>
            </el-form-item>
          </el-form>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import {reactive, ref} from "vue";
import {ElMessage, FormInstance, FormRules} from "element-plus";
import {login} from "../../api/token";
import store from "../../store";
import router from "../../router";


const ruleFormRef = ref<FormInstance>()
const formData = reactive({
  username: 'admin',
  password: '123',
})
const rules = reactive<FormRules>({
  username: [
    {required: true, message: '用户名不能为空', trigger: 'blur'},
    {min: 3, max: 64, message: '用户名长度为3到64位', trigger: 'blur'},
  ],
  password: [
    {required: true, message: '密码不能为空', trigger: 'blur'},
    {min: 3, max: 64, message: '密码长度为3到64位', trigger: 'blur'},
  ],
})
const submitForm = async (formEl: FormInstance | undefined) => {
  if (!formEl) return
  await formEl.validate((valid, fields) => {
    if (valid) {
      login(formData).then(data => {
        console.log(data)
        store.commit('SET_TOKEN', data.data.jwt)
        store.commit('SET_ROLE', data.data.role)
        router.push({path: `/admin/home`})
      })
    } else {
      ElMessage.error('请重新尝试!')
    }
  })
}
const resetForm = (formEl: FormInstance | undefined) => {
  formEl?.resetFields()
  formData.password = ''
  formData.username = ''
}
</script>

<style scoped lang="less">
.login-page {
  width: 100vw;
  height: 100vh;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;

  .login-form-content {
    width: 400px;
    padding: 30px;

    .login-title {
      font-size: 40px;
      text-align: center;
      margin-bottom: 30px;
    }
  }
}

.welcome-title {
  position: absolute;
  bottom: 30px;
  left: 30px;
  color: white;
  text-shadow: 0 0 10px black;
}
</style>