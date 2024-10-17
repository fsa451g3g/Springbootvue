<template>
    <el-row>
        <el-col :span="24">
            <div class="title">
                <div class="title-box">
                    <strong>{{ props.title }}</strong>
                </div>
            </div>
        </el-col>
    </el-row>
</template>

<script setup lang="ts">
const props = defineProps(['title'])
</script>

<style scoped lang="less">
.title {
    height: 52px;
    border-bottom: 1px solid #a8a0a0;

    .title-box {
        padding: 18px 20px;
        color: #303133;
    }
}
</style>