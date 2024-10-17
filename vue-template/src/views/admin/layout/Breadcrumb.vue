<template>
    <el-breadcrumb class="header-breadcrumb">
        <el-breadcrumb-item v-for="item in list" :key="item.path">
            <!--            <router-link :to="{ path: item.path }">{{ item.meta.title }}</router-link>-->
            <span>{{ item.meta.title }}</span>
        </el-breadcrumb-item>
    </el-breadcrumb>
</template>

<script setup lang="ts">
import {computed} from "vue";
import {useRoute} from "vue-router";
import store from "../../../store";

const list = computed(() => {
    if (useRoute().matched[useRoute().matched.length - 1].path === store.getters.getActiveIndex){
        store.dispatch('ADD_TAGS')
    }
    // return router.matched.filter(r => r.name !== 'admin') //去掉标题最前面的左斜线
    return useRoute().matched // 标题最前面有一个左斜线
})
</script>

<style scoped lang="less">

</style>