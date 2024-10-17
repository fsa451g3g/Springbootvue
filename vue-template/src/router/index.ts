import {createRouter, createWebHistory, RouteRecordRaw} from 'vue-router'
import store from "../store";

const whiteList = ['/login', '/404', '/']

const routes: Array<RouteRecordRaw> = [
    {
        path: '/admin',
        name: 'admin',
        redirect: '/admin/home',
        component: () => import('../views/admin/layout/Index.vue'),
        children: [
            {
                path: '/admin/home',
                name: 'aHome',
                meta: {title: '首页', icon: 'grid'},
                component: () => import('../views/admin/Home/Home.vue')
            },
            {
                path: '/admin/admin',
                name: 'aAdmin',
                meta: {title: '管理账号', icon: 'grid'},
                component: () => import('../views/admin/account/Admin.vue')
            },
            {
                path: '/admin/user',
                name: 'aUser',
                meta: {title: '用户账号', icon: 'grid'},
                component: () => import('../views/admin/account/User.vue')
            },
            {
                path: '/admin/systemName',
                name: 'aSystemName',
                meta: {title: '系统名称', icon: 'grid'},
                component: () => import('../views/admin/system/SystemName.vue')
            },
        ],
    },
    {
        path: '/user',
        name: 'user',
        component: () => import('../views/admin/layout/Index.vue'),
        children: [
            {
                path: '/user/index',
                name: 'index',
                meta: {title: '首页', icon: 'home-filled'},
                component: () => import('../views/user/Index.vue')
            },
        ],
    },
    {
        path: '/login',
        name: 'Login',
        component: () => import('../views/login/Login.vue'),
    },
    {
        path: '/:pathMatch(.*)',
        redirect: '/login',
    }
]

const router = createRouter({
    history: createWebHistory(),
    routes
})

router.beforeEach(async (to, from, next) => {
    const token = localStorage.getItem('token')
    const role = localStorage.getItem('role')
    if (token) {
        if (to.path == '/login') {
            next({path: `/${role}/home`})
        } else {
            const hasRouter = store.getters.getHasRouter
            if (!hasRouter) {
                store.commit('SET_HASROUTER', true)
                next({...to, replace: true})
            } else {
                store.commit('SET_ACTIVEINDEX', to.path)
                next()
            }
        }
    } else if (whiteList.indexOf(to.path) !== -1) {
        next()
    } else {
        next({path: `/login?redirect=${to.path}`})
    }
})

export default router