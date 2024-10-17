package com.example.modules.log.service.impl;

import com.example.config.WebConfig;
import com.example.exception.CustomException;
import com.example.exception.ExceptionType;
import com.example.modules.log.service.RunLogService;
import com.example.result.Result;
import com.example.utils.FileList;
import com.example.utils.FileStream2Web;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.example.utils.DeleteFile.delFolder;

@Service("runLogService")
public class RunLogServiceImpl implements RunLogService {

    @Override
    public Result list(Integer pageNum, Integer pageSize) {
        Map<String, Object> map = new HashMap<>();
        List<File> allFile = FileList.getAllFile(WebConfig.LOGFILEPATH);
        List<String> all = allFile.stream().map(file -> file.getName()).collect(Collectors.toList());
        Integer fromIndex = Math.min(((pageNum - 1) * pageSize), all.size());
        Integer toIndex = Math.min(pageNum * pageSize - 1, all.size());
        List<String> list = all.subList(fromIndex, toIndex);
        map.put("list", list);
        map.put("total", allFile.size());
        return Result.success("获取文件列表成功", map);
    }

    @Override
    public Result delete(String id) {
        if (id.equals("0")) {
            delFolder(WebConfig.LOGFILEPATH);
        } else {
            boolean delete = new File(WebConfig.LOGFILEPATH + File.separator + id).delete();
            if (!delete) throw new CustomException(ExceptionType.RUN_LOG_DELETE_FAIL);
        }
        return Result.success("删除运行日志成功");
    }

    @Override
    public Result download(String fileName, HttpServletResponse response) {
        FileStream2Web.fileView(WebConfig.LOGFILEPATH + File.separator + fileName, response);
        return Result.success("下载成功");
    }

}
