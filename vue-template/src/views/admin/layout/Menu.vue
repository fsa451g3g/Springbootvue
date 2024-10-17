<template>
  <el-menu
      active-text-color="#ffd04b"
      background-color="#001529"
      class="el-menu-vertical-demo"
      text-color="#fff"
      router
      unique-opened
  >
    <el-menu-item index="/admin/home">
      <el-icon><House /></el-icon>首页
    </el-menu-item>
    <el-sub-menu>
      <template #title>
        <el-icon>
          <user/>
        </el-icon>
        账号管理
      </template>
      <el-menu-item-group>
        <el-menu-item index="/admin/admin">
          <template #title>
            <el-icon>
              <Sunny/>
            </el-icon>
            管理账号
          </template>
        </el-menu-item>
      </el-menu-item-group>
      <el-menu-item-group>
        <el-menu-item index="/admin/user">
          <template #title>
            <el-icon>
              <Sunny/>
            </el-icon>
            用户账号
          </template>
        </el-menu-item>
      </el-menu-item-group>
    </el-sub-menu>
    <el-sub-menu>
      <template #title>
        <el-icon>
          <Setting/>
        </el-icon>
        系统管理
      </template>
      <el-menu-item-group>
        <el-menu-item index="/admin/systemName">
          <template #title>
            <el-icon>
              <Sunny/>
            </el-icon>
            系统名称
          </template>
        </el-menu-item>
      </el-menu-item-group>
    </el-sub-menu>
  </el-menu>
</template>

<script setup lang="ts">

import store from "../../../store";
</script>

<style scoped lang="less">
.el-menu-vertical-demo {
    height: 100%;
}
</style>