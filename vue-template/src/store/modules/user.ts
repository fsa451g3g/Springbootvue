const state = () => ({
    hasRouter: false,
    token: localStorage.getItem('token'),
    role: localStorage.getItem('role'),
})

const getters = {
    getHasRouter(state: any) {
        return state.hasRouter
    },
    getToken(state: any) {
        return state.token
    },
    getRole(state: any) {
        return state.role
    },
}

const actions = {}

const mutations = {
    SET_HASROUTER(state: any, hasRouter: boolean) {
        state.hasRouter = hasRouter
    },
    SET_TOKEN(state: any, token: any) {
        localStorage.setItem('token', token)
    },
    SET_ROLE(state: any, role: any) {
        localStorage.setItem('role', role)
    },
}

export default {
    namespace: true,
    state,
    getters,
    actions,
    mutations
}