<template>
    <div class="tag-page">
        <el-tag v-for="tag in store.getters.getTags" :key="tag.get('name')"
                :effect="tag.get('name') === store.getters.getActiveTag? 'dark':'plain'"
                size="large" closable :disable-transitions="false"
                @close.prevent="store.dispatch('SUB_TAGS',tag)"
                @click="store.dispatch('CLICK_TAG',tag.get('path'))">
            {{ tag.get('name') }}
        </el-tag>
    </div>
</template>

<script setup lang="ts">
import store from "../../../store";
</script>

<style scoped lang="less">
.tag-page {
    height: 16px;
    border-bottom: 1px solid #a8a0a0;

    .tag-component {

    }
}
</style>