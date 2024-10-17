<template>
    <div class="index-page">
        <Index title="首页"></Index>
        <el-row>
            <el-col :span="24">
                <div class="grid-content">
                    <el-carousel :interval="5000" arrow="always">
                        <el-carousel-item v-for="item in carouselList" :key="item.name" :name="item.name">
                            <el-image style="width: 100%; height: 100%" :src="baseURL+'/v/'+prefix+'/'+item.path"
                                      fit="fill"/>
                        </el-carousel-item>
                    </el-carousel>
                </div>
            </el-col>
        </el-row>
    </div>
</template>

<script setup lang="ts">
import Index from '../../components/title/Index.vue'
import {listCarouselUser} from "../../api/carousel";
import {onMounted, ref} from "vue";
import {baseURL} from "../../api/request";

const carouselList = ref([])
const prefix = ref('')
const list = () => {
    listCarouselUser().then(data => {
        carouselList.value = data.data.list
        prefix.value = data.data.prefix
    })
}
onMounted(list)
</script>

<style scoped lang="less">
.index-page {
    .grid-content {
        padding: 20px;
    }
}
</style>