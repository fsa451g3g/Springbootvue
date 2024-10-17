const state = () => ({
    activeIndex: '/admin/home',
})

const getters = {
    getActiveIndex(state: any) {
        return state.activeIndex
    },
}

const actions = {}

const mutations = {
    SET_ACTIVEINDEX(state: any, activeIndex: string) {
        state.activeIndex = activeIndex
    },
}

export default {
    namespace: true,
    state,
    getters,
    actions,
    mutations
}