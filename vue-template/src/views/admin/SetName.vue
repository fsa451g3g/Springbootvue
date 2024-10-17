<template>
    <div class="setName-page">
        <Index title="系统名称"></Index>
        <el-row>
            <el-col :span="24">
                <div class="grid-content">
                    <Setting name="setName"></Setting>
                </div>
            </el-col>
        </el-row>
    </div>
</template>

<script setup lang="ts">
import Index from '../../components/title/Index.vue'
import Setting from '../../components/setting/Index.vue'
</script>

<style scoped lang="less">
.setName-page {
    .grid-content {
        padding: 20px;

    }
}
</style>