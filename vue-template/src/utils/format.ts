import moment from "moment";

export const formatTime = (row: any, column: any) => {
    const date = row[column.property];
    if (date == undefined) {
        return ''
    }
    return moment(date).format("YYYY-MM-DD HH:mm:ss")
}

export const formatDate = (row: any, column: any) => {
    const date = row[column.property];
    if (date == undefined) {
        return ''
    }
    return moment(date).format("YYYY-MM-DD")
}

export const formatTime2 = (value: any) => {
    if (value == undefined) {
        return ''
    }
    return moment(value).format("YYYY-MM-DD HH:mm:ss")
}