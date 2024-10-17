import axios from "axios";
import {ElMessage} from "element-plus";

const baseURL = 'http://127.0.0.1:8080'

const instance = axios.create({
    baseURL
})

instance.interceptors.request.use(config => {
    if (localStorage.getItem('token')) {
        config.headers = {...config.headers, 'Authorization': `${localStorage.getItem('token')}`}
    }
    return config
}, error => {
    return Promise.reject(error)
})

instance.interceptors.response.use(response => {
    if (response.status !== 200) {
        ElMessage.error(response.data.message)
        return Promise.reject(response.data)
    }
    return response.data
}, error => {
    ElMessage.error(error.response.data.message)
    return Promise.reject(error)
})

const get = instance.get, post = instance.post, put = instance.put, remove = instance.delete

export {get, post, put, remove, baseURL}