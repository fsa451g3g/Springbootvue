import {get, post, put, remove} from "./request";

export const listUser = (pageNum: number, pageSize: number, search1: string, search2: string, role: string) => {
    return get(`/user/list/${pageNum}/${pageSize}/${role}?search1=${search1}&search2=${search2}`)
}

export const getUser = (id: any) => {
    return get(`/user/${id}`)
}

export const postUser = (data: object) => {
    return post(`/user/`, data)
}

export const putUser = (id: string) => {
    return put(`/user/${id}`)
}

export const delUser = (id: string) => {
    return remove(`/user/${id}`)
}

export const me = () => {
    return get(`/admin/me`)
}