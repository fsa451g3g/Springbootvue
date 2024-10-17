<template>
    <div class="page">
        <div class="error-code">404</div>
        <div class="tips">抱歉，你访问的页面不存在！</div>
        <div class="action-area">
            <q-btn @click="() => {$router.back()}" color="primary" label="返回"></q-btn>
            <q-btn @click="() => {$router.push('/')}" color="black" label="首页" outline></q-btn>
        </div>
    </div>
</template>

<style scoped lang="less">
.page{
    width: 100vw;
    height: 100vh;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    .error-code{
        font-size: 300px;
        line-height: 350px;
    }
    .tips{
        color: #999999;
        margin-bottom: 30px;
    }
    .action-area{
        display: flex;
        width: 140px;
        flex-direction: row;
        justify-content: space-between;
    }
}

</style>