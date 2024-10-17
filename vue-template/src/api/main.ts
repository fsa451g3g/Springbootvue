import {post} from "./request";

export const postMain = (data: object) => {
    return post(`/main/`, data)
}
