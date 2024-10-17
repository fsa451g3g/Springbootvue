package com.example.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.FileSystemException;

public class DeleteFile {

    /***
     * 删除文件夹下所有文件以及文件夹
     */
    public static void delFolder(String folderPath) {
        try {
            File file = new File(folderPath);
            if (!file.exists()) {
                throw new FileNotFoundException("文件未找到");
            }
            if (file.isDirectory()) {
                delAllFile(folderPath);
            }
            boolean delete = file.delete();
            if (!delete) throw new FileSystemException("文件删除失败");
        } catch (FileNotFoundException | FileSystemException e) {
            throw new RuntimeException(e);
        }
    }

    /***
     * 删除指定文件夹下所有文件
     */
    public static void delAllFile(String path) throws FileSystemException {
        assert new File(path).isDirectory();
        File file = new File(path);
        String[] files = file.list();
        assert files != null;
        for (String s : files) {
            File f = new File(path + File.separator + s);
            if (f.isDirectory()) {
                delAllFile(f.getPath());
            }
            if (f.isFile()) {
                boolean delete = f.delete();
                if (!delete) throw new FileSystemException("文件删除失败");
            }
        }
    }
}
