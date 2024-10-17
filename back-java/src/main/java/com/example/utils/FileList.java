package com.example.utils;

import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class FileList {

    /**
     * <h1>获取指定文件夹下所有文件，不含文件夹</h1>
     *
     * @param dirFilePath 文件夹路径
     * @return
     */
    public static List<File> getAllFile(String dirFilePath) {
        if (StringUtils.isBlank(dirFilePath))
            return null;
        return getAllFile(new File(dirFilePath));
    }

    /**
     * <h1>获取指定文件夹下所有文件，不含文件夹</h1>
     *
     * @param dirFile 文件夹
     * @return
     */
    public static List<File> getAllFile(File dirFile) {
        // 如果文件夹不存在或着不是文件夹，则返回 null
        if (Objects.isNull(dirFile) || !dirFile.exists() || dirFile.isFile())
            return null;

        File[] childrenFiles = dirFile.listFiles();
        if (Objects.isNull(childrenFiles) || childrenFiles.length == 0)
            return null;
        List<File> files = new ArrayList<>();
        for (File childFile : childrenFiles) {
            // 如果时文件，直接添加到结果集合
            if (childFile.isFile()) {
                files.add(childFile);
            } else {
                // 如果是文件夹，则将其内部文件添加进结果集合
                List<File> cFiles = getAllFile(childFile);
                if (Objects.isNull(cFiles) || cFiles.isEmpty()) continue;
                files.addAll(cFiles);
            }
        }
        return files;
    }


    /**
     * <h1>获取指定文件夹下所有文件，含文件夹</h1>
     *
     * @param dirFilePath 文件夹路径
     * @return
     */
    public static List<File> getAllFileAndDir(String dirFilePath) {
        if (StringUtils.isBlank(dirFilePath))
            return null;
        return getAllFileAndDir(new File(dirFilePath));
    }

    /**
     * <h1>获取指定文件夹下所有文件，不含文件夹</h1>
     *
     * @param dirFile 文件夹
     * @return
     */
    public static List<File> getAllFileAndDir(File dirFile) {
        // 如果文件夹不存在或着不是文件夹，则返回 null
        if (Objects.isNull(dirFile) || !dirFile.exists() || dirFile.isFile())
            return null;
        File[] childrenFiles = dirFile.listFiles();
        if (Objects.isNull(childrenFiles) || childrenFiles.length == 0)
            return null;
        List<File> files = new ArrayList<>();
        for (File childFile : childrenFiles) {
            // 如果时文件，直接添加到结果集合
            if (childFile.isFile()) {
                files.add(childFile);
            } else {
                // 如果是文件夹。则先将其添加到结果集合，再将其内部文件添加进结果集合。
                files.addAll((Collection<? extends File>) childFile);
                List<File> cFiles = getAllFileAndDir(childFile);
                if (Objects.isNull(cFiles) || cFiles.isEmpty()) continue;
                files.addAll(cFiles);
            }
        }
        return files;
    }
}
