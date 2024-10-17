import {get, remove} from "./request";

export const listSystemLog = (pageNum: number, pageSize: number, search1: string) => {
    return get(`/systemLog/list/${pageNum}/${pageSize}?search1=${search1}`)
}

export const delSystemLog = (ids: string) => {
    return remove(`/systemLog`, {params: {ids}})
}

export const getSystemLog = (id: any) => {
    return get(`/systemLog/${id}`,)
}