import {post} from "./request";

export const login = (data: object) => {
    return post('/token', data)
}