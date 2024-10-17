import {createStore} from 'vuex'
import user from "./modules/user";
import menu from "./modules/menu";
import tag from "./modules/tag";

export default createStore({
    state: {},
    mutations: {},
    actions: {},
    modules: {
        user,
        menu,
        tag,
    }
})
