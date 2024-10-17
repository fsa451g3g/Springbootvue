package com.example.utils;

import jakarta.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

public class FileStream2Web {

    /**
     * 以IO流的形式返回给前端
     *
     * @param fileUrl  文件路径
     * @param response resp
     */
    public static void fileView(String fileUrl, HttpServletResponse response) {
        // 读取文件名 例：yyds.jpg
        String fileName = fileUrl.substring(fileUrl.lastIndexOf(File.separator) + 1);
        try (FileInputStream inputStream = new FileInputStream(fileUrl);
             OutputStream outputStream = response.getOutputStream()) {
            byte[] data = new byte[1024];
            // 全文件类型（传什么文件返回什么文件流）
            response.setContentType("application/x-msdownload");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
            response.setHeader("Accept-Ranges", "bytes");
            int read;
            while ((read = inputStream.read(data)) != -1) {
                outputStream.write(data, 0, read);
            }
            // 将缓存区数据进行输出
            outputStream.flush();
        } catch (IOException e) {
            throw new NullPointerException();
        }
    }


    /**
     * 将字符串渲染到客户端
     *
     * @param response 渲染对象
     * @param string   待渲染的字符串
     * @return null
     */
    public static String renderString(HttpServletResponse response, String string) {
        try {
            response.setStatus(200);
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.getWriter().print(string);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
