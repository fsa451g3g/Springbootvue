<template>
  <div class="header-page">
    <router-link to="/admin" class="header-title">
      <div class="title">{{ title }}</div>
    </router-link>
    <div class="header-content">
      <div class="header-left">
        <Breadcrumb></Breadcrumb>
      </div>
      <div class="header-right">
        <el-dropdown trigger="click" class="header-user">
                    <span class="el-dropdown-link">
                        <span style="font-size: 16px">{{ nickname }}</span>
                        <component is="arrow-down" style="width: 16px; height:16px;"/>
                    </span>
          <template #dropdown>
            <el-dropdown-menu>
              <el-dropdown-item @click="$router.push({path:'/main/main'})">个人中心</el-dropdown-item>
              <el-dropdown-item @click="logout">退出登录</el-dropdown-item>
            </el-dropdown-menu>
          </template>
        </el-dropdown>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import {ref} from "vue";
import {logout} from "../../logout/logout";
import Breadcrumb from "./Breadcrumb.vue";
import {me} from "../../../api/user";
import {getSetting} from "../../../api/setting";

const title = ref('测试管理系统')
const nickname = ref('')
getSetting('setName').then(data => {
  title.value = data.data.currentValue
})

me().then(data => {
  nickname.value = data.data.nickname
})

</script>

<style scoped lang="less">
.header-page {
  display: flex;
  height: 60px;

  .header-title {
    width: 12%;
    background-color: #001529;
    padding: 0;
    display: flex;
    align-items: center;

    .title {
      color: #fff;
      font-size: 20px;
      margin: auto;
    }
  }

  .header-content {
    display: flex;
    align-items: center;
    border: 1px solid #a8a0a0;
    border-left: none;
    border-right: none;
    width: 88%;

    .header-left {
      display: flex;
      padding-left: 10px;
      margin-left: 10px;
    }

    .header-right {
      display: inline-block;
      position: absolute;
      right: 5px;

      .header-user {
        right: 10px;
      }
    }
  }
}

.router-link-active {
  text-decoration: none;
}

.el-breadcrumb {
  font-size: 16px;
}
</style>