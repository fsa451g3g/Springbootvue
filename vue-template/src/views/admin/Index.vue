<template>
    <div class="index-page">
        <Index title="用户首页"></Index>
        <el-row>
            <el-col :span="24">
                <div class="grid-content">
                    <el-form :inline="true" class="demo-form-inline" size="large">
                        <el-form-item label="名称">
                            <el-input v-model="search1" placeholder="请输入名称"/>
                        </el-form-item>
                        <el-form-item label="状态">
                            <el-select v-model="search2" placeholder="请选择图片状态">
                                <el-option value="" label="全部"></el-option>
                                <el-option value="1" label="开启"></el-option>
                                <el-option value="0" label="关闭"></el-option>
                            </el-select>
                        </el-form-item>
                        <el-form-item>
                            <el-button type="primary" @click="carouselList">查询</el-button>
                        </el-form-item>
                        <el-form-item>
                            <el-button type="success" @click="addCarousel">添加</el-button>
                        </el-form-item>
                    </el-form>

                    <el-table :data="tableData" stripe border fit>
                        <el-table-column align="center" label="序号" width="60">
                            <template #default="slot">
                                {{ (pageNum - 1) * pageSize + slot.$index + 1 }}
                            </template>
                        </el-table-column>
                        <el-table-column align="center" prop="name" label="名称"/>
                        <el-table-column align="center" label="图片">
                            <template #default="slot">
                                <el-image :src="baseURL+'/v/'+prefix+'/'+slot.row.path"></el-image>
                            </template>
                        </el-table-column>
                        <el-table-column align="center" prop="describes" label="描述"/>
                        <el-table-column align="center" prop="accountNonLocked" label="状态" width="80">
                            <template #default="slot">
                                <el-switch v-model="slot.row.enabled" class="ml-2" active-color="#13ce66"
                                           @change="putCarousel(slot.row.id)"/>
                            </template>
                        </el-table-column>
                        <el-table-column align="center" prop="createdTime" label="创建时间" :formatter="formatTime"/>
                        <el-table-column align="center" prop="" label="操作">
                            <template #default="slot">
                                <el-button @click="seeCarousel(slot.row.path)">查看
                                </el-button>
                                <el-button type="success" @click="editCarousel(slot.row.id)">编辑</el-button>
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
                                @size-change="carouselList"
                                @current-change="carouselList">
                        </el-pagination>
                    </div>
                </div>
            </el-col>
        </el-row>

        <el-dialog v-model="dialogVisible" title="用户首页轮播图" width="40%" draggable @closed="resetForm(ruleFormRef)">
            <el-form ref="ruleFormRef" :model="ruleForm" :rules="rules" label-width="120px"
                     class="demo-ruleForm" size="large">
                <el-form-item label="名称" prop="name">
                    <el-input v-model="ruleForm.name" placeholder="请输入名称"/>
                </el-form-item>
                <el-form-item label="描述" prop="describes">
                    <el-input v-model="ruleForm.describes" placeholder="请输入描述"/>
                </el-form-item>
                <el-form-item label="图片" prop="tempName">
                    <el-upload class="upload-demo" :action="baseURL+'/carousel/upload/'" :headers="headers"
                               list-type="picture-card" :on-preview="handlePreview" :before-remove="beforeRemove"
                               multiple :limit="1" :on-exceed="handleExceed" :file-list="fileList">
                        <el-icon>
                            <Plus/>
                        </el-icon>
                        <template #tip>
                            <div class="el-upload__tip">
                                仅支持上传后缀名为"jpg", "jpeg", "png", "gif"的图片类文件
                            </div>
                        </template>
                    </el-upload>
                </el-form-item>
            </el-form>
            <template #footer>
                <span class="dialog-footer">
                    <el-button @click="resetForm(ruleFormRef)">清空</el-button>
                    <el-button type="success" @click="submitForm(ruleFormRef)">确认</el-button>
                </span>
            </template>
        </el-dialog>
        <el-dialog v-model="imgDialog" width="30%">
            <img style="width: 100%; height: 100%" w-full :src="dialogImageUrl" alt="Preview Image"/>
        </el-dialog>
    </div>
</template>

<script setup lang="ts">
import {onMounted, reactive, ref} from "vue";
import {formatTime} from "../../utils/format";
import {
    ElMessage,
    ElMessageBox,
    FormInstance,
    FormRules, UploadProps, UploadUserFile,
} from "element-plus";
import Index from '../../components/title/Index.vue'
import {delCarousel, getCarousel, listCarousel, postCarousel, putCarousel} from "../../api/carousel";
import {baseURL} from '../../api/request'

//------------按条件查询记录-----------------------------------------------------------------------------------
const search1 = ref('')
const search2 = ref('')
const pageNum = ref(1)
const pageSize = ref(10)
const total = ref(0)
const prefix = ref('')
const tableData = ref([])
const carouselList = () => {
    listCarousel(pageNum.value, pageSize.value, search1.value, search2.value).then(data => {
        tableData.value = data.data.list
        prefix.value = data.data.prefix
        total.value = data.data.total
    })
}
onMounted(carouselList)
//------------删除记录-------------------------------------------------------------------------------------------
const del = (id: string) => {
    ElMessageBox.confirm('你确认删除该记录吗', '提示', {
        confirmButtonText: '确认', cancelButtonText: '取消', type: 'warning',
    }).then(() => {
        delCarousel(id).then(() => {
            ElMessage.success('删除成功')
            carouselList()
        })
    }).catch(() => {
        ElMessage.info('已取消!')
    })
}
//------------上传图片-----------------------------------------------------------------------------------
const fileList = ref<UploadUserFile[]>([])
const headers = ref({Authorization: localStorage.getItem('token')})
const dialogImageUrl = ref('')
const imgDialog = ref(false)
const handlePreview: UploadProps['onPreview'] = (uploadFile) => {
    dialogImageUrl.value = uploadFile.url!
    imgDialog.value = true
}
const handleExceed: UploadProps['onExceed'] = (files, uploadFiles) => {
    console.log(fileList)
    ElMessage.warning(`此处限制上传一个文件，请删除后重新添加`)
}
const beforeRemove: UploadProps['beforeRemove'] = (uploadFile, uploadFiles) => {
    return ElMessageBox.confirm(`您确定取消 ${uploadFile.name} 文件吗?`).then(() => true, () => false)
}
//------------查看图片-----------------------------------------------------------------------------------
const seeCarousel = (path: string) => {
    dialogImageUrl.value = baseURL + '/v/' + prefix.value + '/' + path
    imgDialog.value = true
}
//------------添加记录的方法-----------------------------------------------------------------------------------
const addCarousel = () => {
    ruleForm.id = '0'
    dialogVisible.value = true
}
//------------编辑记录的方法-----------------------------------------------------------------------------------
const editCarousel = (id: string) => {
    ruleForm.id = id
    getCarousel(id).then(data => {
        ruleForm.name = data.data.name
        ruleForm.describes = data.data.describes
        ruleForm.tempName = data.data.path
        const s = {name: data.data.name, url: baseURL + '/v/' + prefix.value + '/' + data.data.path}
        fileList.value.push(s)
        dialogVisible.value = true
    })
}
//------------添加或者编辑记录所使用的数据-----------------------------------------------------------------------------------
const dialogVisible = ref(false)
const ruleFormRef = ref<FormInstance>()
let ruleForm = reactive({
    id: '',
    name: '',
    describes: '',
    tempName: '',
})
const rules = reactive<FormRules>({
    name: [
        {required: true, message: '请输入用户名', trigger: 'blur'},
        {min: 3, max: 64, message: '用户名长度在3到64位', trigger: 'blur'},
    ],
    describes: [
        {required: true, message: '请输入姓名', trigger: 'blur'},
        {min: 1, max: 255, message: '姓名长度在1到255位', trigger: 'blur'},
    ],
    tempName: [
        {required: true, message: '请选择图片', trigger: 'blur'},
    ],
})
const submitForm = async (formEl: FormInstance | undefined) => {
    if (fileList.value.length !== 1) return ElMessage.error("请按照要求上传图片")
    ruleForm.tempName = fileList.value[0].response.data.tempName
    if (!formEl) return
    await formEl.validate((valid, fields) => {
        if (valid) {
            postCarousel(ruleForm).then(data => {
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
    ruleForm.name = ''
    ruleForm.describes = ''
    ruleForm.tempName = ''
    fileList.value = []
}
</script>

<style scoped lang="less">
.index-page {
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