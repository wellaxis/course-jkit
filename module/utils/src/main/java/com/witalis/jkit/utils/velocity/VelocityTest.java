package com.witalis.jkit.utils.velocity;

import org.apache.velocity.*;
import org.apache.velocity.app.Velocity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Properties;

public class VelocityTest {
    static Logger logger = LoggerFactory.getLogger(VelocityTest.class.getName());

    public static void main(String[] args) throws Exception {
        logger.info("VelocityTest: Begin");
        // instance
        VelocityTest velocityTest = new VelocityTest();
        // properties
        Properties properties = new Properties();
        properties.setProperty("resource.loader", "class");
        properties.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        // initialization
        Velocity.init(properties);
        // context
        VelocityContext context = new VelocityContext();
        String name = "Velocity";
        // variable $foo for template
        context.put("foo", name);
        // file
        String velocityRes = "velocity/template.vm";
        URL resource = velocityTest.getClass().getClassLoader().getResource(velocityRes);
        File file = Paths.get(resource.toURI()).toFile();
        assert file.exists() : "Velocity resource doesn't exists.";
        // template
        Template template = Velocity.getTemplate(velocityRes, "utf-8");
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        template.merge(context, writer);
        writer.flush();
        writer.close();
        logger.info("VelocityTest: End");
    }
}
