package com.example.config;

import com.example.utils.DeleteFile;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.nio.file.FileSystemException;

@Configuration
@EnableScheduling
public class ScheduleConfig {

    @Scheduled(cron = "0 0 2 * * *")
    public void delTempFile() {
        try {
            DeleteFile.delAllFile(WebConfig.UPLOADFILEPATH);
        } catch (FileSystemException e) {
            DeleteFile.delFolder(WebConfig.UPLOADFILEPATH);
        }
    }
}
