import {get, remove} from "./request";

export const listRunLog = (pageNum: number, pageSize: number) => {
    return get(`/runLog/list/${pageNum}/${pageSize}`)
}

export const delRunLog = (id: string) => {
    return remove(`/runLog/${id}`,)
}

export const downloadFile = (fileName: string) => {
    return get(`/runLog/download/${fileName}`, {
        responseType: 'blob'
    })
}