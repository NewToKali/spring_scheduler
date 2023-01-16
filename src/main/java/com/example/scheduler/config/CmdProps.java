package com.example.scheduler.config;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Component
@Data
@ConfigurationProperties(prefix = "cmd")
public class CmdProps {
    private String exportMode1;
    private String exportMode2;
    private String exportMode3;


    private String importMode1;
    private String importMode2;
    private String importMode3;

}
