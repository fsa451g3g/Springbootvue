<template>
    <div class="common-layout">
        <el-container class="common-container">
            <el-header height="60px">
                <Header></Header>
            </el-header>
            <el-container>
                <el-aside width="12%">
                    <Menu class="aside-menu"></Menu>
                </el-aside>
                <el-container>
                    <el-main>
                        <Tag class="common-tag"></Tag>
                        <div class="common-home">
                            <div class="common-content">
                                <router-view></router-view>
                            </div>
                        </div>
                    </el-main>
                </el-container>
            </el-container>
        </el-container>
    </div>
</template>

<script setup lang="ts">
import Header from "./Header.vue";
import Menu from "./Menu.vue";
import Tag from "./Tag.vue";
</script>

<style scoped lang="less">
.common-layout {
    position: relative;
    padding: 0;
    margin: 0;
    height: 100%;

    .common-container {
        height: 100%;

        .el-header {
            padding: 0;
        }
    }
}

.el-main {
    padding: 0;

    .common-tag {
        height: 32px;
    }

    .common-home {
        background-color: #f5f7f9;
        padding: 20px;

        .common-content {
            background-color: white;
            width: 100%;
            height: 86vh;
        }
    }
}
</style>