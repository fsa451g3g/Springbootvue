import {get, post, put} from "./request";

export const getSetting = (name: any) => {
    return get(`/admin/setting/${name}`)
}

export const postSetting = (data: object) => {
    return post(`/setting`, data)
}

export const putSetting = (name: any) => {
    return put(`/setting/${name}`)
}