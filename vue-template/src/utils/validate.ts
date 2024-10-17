//校验邮箱格式
export const validateEmail = (rule: any, value: any, callback: any) => {
    if (value === '') {
        return callback()
    }
    // 邮箱正则表达式
    const regEmail = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/
    if (regEmail.test(value)) {
        // True 合法的邮箱
        return callback()
    }
    callback(new Error('请输入合法的邮箱'))
}
//校验电话号码格式
export const validateTel = (rule: any, value: any, callback: any) => {
    if (value === '') {
        callback();
    } else {
        if (value !== '') {
            const reg = /^1[3456789]\d{9}$/;
            if (!reg.test(value)) {
                callback(new Error('请输入有效的手机号码'));
            }
        }
        callback();
    }
}