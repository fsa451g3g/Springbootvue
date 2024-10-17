import store from "../../store";

export function logout() {
    store.commit('SET_HASROUTER',false)
    localStorage.removeItem('token')
    localStorage.removeItem('role')
    location.reload();
}