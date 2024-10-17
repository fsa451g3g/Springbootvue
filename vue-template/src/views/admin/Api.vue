<template>
    <div class="api-page">
        <Index title="接口文档"></Index>
        <iframe :src="baseURL+'/doc.html'" height="760px" width="100%">

        </iframe>
    </div>
</template>

<script setup lang="ts">
import Index from '../../components/title/Index.vue'
import {baseURL} from "../../api/request";
</script>

<style scoped lang="less">
.api-page {

}
</style>