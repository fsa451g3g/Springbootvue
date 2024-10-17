import {get} from "./request";

export const getMenuList = () => {
    return get('/menu/list')
}