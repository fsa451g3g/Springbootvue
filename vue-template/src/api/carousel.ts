import {get, post, put, remove} from "./request";

export const listCarousel = (pageNum: number, pageSize: number, search1: string, search2: string) => {
    return get(`/carousel/list/${pageNum}/${pageSize}?search1=${search1}&search2=${search2}`)
}

export const postCarousel = (data: object) => {
    return post(`/carousel`, data)
}

export const getCarousel = (id: any) => {
    return get(`/carousel/${id}`)
}

export const putCarousel = (id: string) => {
    return put(`/carousel/${id}`)
}

export const delCarousel = (id: string) => {
    return remove(`/carousel/${id}`)
}

export const listCarouselUser = () => {
    return get(`/carousel/list/user`)
}