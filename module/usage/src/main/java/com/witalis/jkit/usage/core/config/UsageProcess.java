package com.witalis.jkit.usage.core.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Configuration
@ConfigurationProperties(prefix = "process")
public class UsageProcess {
    private Invoker invoker;
    private Handler handler;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Invoker {
        private boolean activate;
        private List<String> sections;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Handler {
        private boolean activate;
        private List<String> sections;
    }
}
