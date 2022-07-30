package com.witalis.jkit.usage.core.config;

import com.witalis.jkit.usage.core.action.IProcessor;
import com.witalis.jkit.usage.core.handle.AppHandler;
import com.witalis.jkit.usage.core.invoke.AppInvoker;
import com.witalis.jkit.usage.core.process.AppProcessor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource(
    value = "classpath:application.yml",
    ignoreResourceNotFound=true
)
@EnableAutoConfiguration
public class UsageConfig {
    private final Environment environment;
    private final UsageProcess properties;

    @Value("${spring.application.name:usage}")
    private String name;

    @Autowired
    public UsageConfig(final Environment environment, final UsageProcess properties) {
        this.environment = environment;
        this.properties = properties;
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyTranslation() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean("usageInvoker")
    @Qualifier("invoker")
    public IProcessor appInvoker() {
        return new AppInvoker(properties);
    }

    @Bean("usageHandler")
    @Qualifier("handler")
    public IProcessor appHandler() {
        return new AppHandler(properties);
    }

    @Bean("usageProcessor")
    @Qualifier("processor")
    public CommandLineRunner invoke(ApplicationContext context) {
        var processor = new AppProcessor(name, context);
        return args -> processor.process();
    }
}
