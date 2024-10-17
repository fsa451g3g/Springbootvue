import {useRoute} from "vue-router";
import store from "../index";
import {ref} from "vue";
import router from "../../router";

const state = () => ({
    tags: [],
    activeTag: '',
})

const getters = {
    getTags(state: any) {
        return state.tags
    },
    getActiveTag(state: any) {
        return state.activeTag
    },
}

const actions = {
    ADD_TAGS(state: any) {
        const tag = new Map()
        useRoute().matched.forEach((item, index) => {
            tag.set('name', item.meta.title)
            tag.set('path', item.path)
        })
        store.commit('SET_ACTIVETAG', tag.get('name'))
        const result = ref(true)
        store.getters.getTags.forEach((t: Map<string, string>) => {
            if (t.get('name') === tag.get('name')) result.value = false

        });
        if (result.value) {
            store.commit('SET_TAGS', tag)
        }
    },
    CLICK_TAG(state: any, path: string) {
        store.commit('SET_ACTIVEINDEX', path)
        router.push({path: path})
    },
    SUB_TAGS(state: any, tag: Map<string, string>) {
        if (tag.get('path') === store.getters.getActiveIndex) {
            const indexOf = store.getters.getTags.indexOf(tag);
            let activePath = ''
            if (indexOf > 0) {
                activePath = store.getters.getTags[indexOf - 1].get('path')
            } else if (store.getters.getTags.length > 1) {
                activePath = store.getters.getTags[indexOf + 1].get('path')
            } else {
                return;
            }
            store.commit('SET_ACTIVEINDEX', activePath)
            router.push({path:activePath})
        }
        store.commit('SUB_TAGS', tag)
    }
}

const mutations = {
    SET_ACTIVETAG(state: any, activeTag: string) {
        state.activeTag = activeTag
    },
    SET_TAGS(state: any, tag: Map<string, string>) {
        state.tags.push(tag)
    },
    SUB_TAGS(state: any, tag: Map<string, string>) {
        state.tags = store.getters.getTags.filter((item: any) => item.get('path') !== tag.get('path'));
    },
}

export default {
    namespace: true,
    state,
    getters,
    actions,
    mutations
}