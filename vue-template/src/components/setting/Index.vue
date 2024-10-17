<template>
    <el-form :inline="true" class="demo-form-inline" size="large">
        <el-form-item :label="title">
            <el-input v-model="currentValue"/>
        </el-form-item>
        <el-form-item :label="placeholder"/>
        <br>
        <el-form-item>
            <el-button type="success" @click="onSubmit">设置</el-button>
        </el-form-item>
        <el-form-item>
            <el-button @click="reduction">还原</el-button>
        </el-form-item>
    </el-form>
</template>

<script setup lang="ts">
import {onMounted, reactive, ref} from 'vue'
import {getSetting, postSetting, putSetting} from "../../api/setting";
import {ElMessage} from "element-plus";

const props = defineProps(['name'])
const id = ref('')
const title = ref('')
const currentValue = ref('')
const placeholder = ref('')
const setting = () => {
    getSetting(props.name).then(data => {
        id.value = data.data.id
        title.value = data.data.title
        currentValue.value = data.data.currentValue
        placeholder.value = data.data.placeholder
    })
}
onMounted(setting)
const onSubmit = () => {
    const data = reactive({id: id.value, currentValue: currentValue.value})
    postSetting(data).then(data => {
        ElMessage.success(data.message)
        setting()
    })
}
const reduction = () => {
    putSetting(props.name).then(data => {
        ElMessage.success(data.message)
        setting()
    })
}
</script>

<style scoped lang="less">
.el-form {
    padding: 40px;
}
</style>