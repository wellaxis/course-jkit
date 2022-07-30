package com.witalis.jkit.usage.core.invoke.section.properties;

import com.witalis.jkit.usage.core.invoke.Invoker;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.util.*;

/**
 * Desc: system properties
 * User: Wellaxis
 * Date: 2019/11/17
 */
@Slf4j
@Data
@EqualsAndHashCode(callSuper=false)
public class PropertyInvoker extends Invoker {
    public static final String PROPERTY = "invoker.property";

    public PropertyInvoker() {
        setTitle("Properties chapter.");
    }

    @Override
    public void invoke() {
        // basis
        log.info("## Basis");
        invokeBasis();
        // tab
        log.info("");
        // properties
        log.info("## Properties");
        invokeProperties();
        // tab
        log.info("");
        // environment
        log.info("## Environment");
        invokeEnvironment();
    }

    /**
     * Basic postulates of properties.
     */
    private void invokeBasis() {
        // The properties object contains key and value pair both as a string.
        // The java.util.Properties class is the subclass of Hashtable.

        log.info("Properties contains key and value pair both as a string.");

        // If any information is changed from the properties file, you don't need to recompile the java class.
        // It is used to store information which is to be changed frequently.
    }

    /**
     * Properties declarations.
     */
    private void invokeProperties() {
        // system properties
        {
            log.info("* System properties");

            log.info("User name is " + System.getProperty("user.name"));
            log.info("Java path is " + System.getProperty("java.library.path"));

            log.info("");

            log.info("List of system properties:");
            Properties properties = System.getProperties();
            properties.forEach(
                (key, value) -> log.info(key + ": " + value)
            );
        }

        log.info("");

        // custom properties
        {
            log.info("* Custom properties");

            System.setProperty(PROPERTY, String.valueOf(UUID.randomUUID()));

            log.info("Invoker property: '{}' -> {}", PROPERTY, System.getProperty(PROPERTY));
        }

        log.info("");

        // default values
        {
            log.info("* Default values");

            var property = System.getProperty("undefined", "default");
            log.info("Default property: value = {}", property);
        }

        log.info("");

        // reader as loader
        {
            log.info("* Loading properties");

            URL url = getClass().getClassLoader().getResource("file");
            assert url != null : "Unable to detect application resource from files";
            File file = new File(url.getPath(), "system.properties");

            try (FileReader reader = new FileReader(file);) {
                Properties properties = new Properties();
                properties.load(reader);

                log.info(
                    "Properties from file: ID = {}, name = {}, active = {}",
                    properties.get("id"),
                    properties.get("name"),
                    properties.get("active")
                );
            } catch (Exception e) {
                log.error("Properties loading errors: {}", e.getMessage());
            }
        }
    }

    /**
     * Environment variables.
     */
    private void invokeEnvironment() {
        log.info("* Environment variables");

        // Like properties, environment variables are key/value pairs, where both the key and the value are strings.
        // On the Java platform, an application uses System.getenv to retrieve environment variable values.

        // We can update Properties at runtime while Environment Variables
        // are an immutable copy of the Operating System's variables.

        Map<String, String> env = System.getenv();

        env.forEach(
            (k, v) -> log.info(String.format("%s=%s%n", k, v))
        );
    }
}
