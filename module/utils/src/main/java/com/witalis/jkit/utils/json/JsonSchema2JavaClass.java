package com.witalis.jkit.utils.json;

import com.sun.codemodel.JCodeModel;
import lombok.extern.slf4j.Slf4j;
import org.jsonschema2pojo.*;
import org.jsonschema2pojo.rules.RuleFactory;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.util.stream.Collectors;

/**
 *  JsonSchema2 Hello World - generate java class from json, GSON, Jackson and other lib
 */
@Slf4j
public class JsonSchema2JavaClass {

    public static void main(String[] args) throws Exception {
        // Init config
        JCodeModel codeModel = new JCodeModel();

        URL source = JsonSchema2JavaClass.class.getClassLoader().getResource("json/quiz.json");
        assert source != null : "Json resource has not been found";
        InputStream urlStream = source.openStream();
        try (var reader = new BufferedReader(new InputStreamReader(urlStream))) {
            String resource = reader.lines().collect(Collectors.joining("\n"));
            log.info("Json: {}", '\n' + resource);
        }

        GenerationConfig config = new DefaultGenerationConfig() {
            @Override
            public boolean isGenerateBuilders() {
                return true;
            }
            @Override
            public SourceType getSourceType(){
                return SourceType.JSON;
            }
        };

        // Generate Java POJO from JSON
        SchemaMapper mapper = new SchemaMapper(new RuleFactory(config, new Jackson2Annotator(config), new SchemaStore()), new SchemaGenerator());
        mapper.generate(codeModel, "JavaClass", "com.json", source);

        boolean tmp = false;
        if (tmp) {
            codeModel.build(Files.createTempDirectory("result").toFile());
        } else {
            // generate
            File directory = Files.createTempDirectory("result").toFile();
            boolean creation = directory.mkdirs();
            codeModel.build(directory);
            // output
            File file = new File(directory + File.separator  + "com/json/" + "JavaClass.java");
            InputStream fileStream = new FileInputStream(file);
            try (var reader = new BufferedReader(new InputStreamReader(fileStream))) {
                String content = reader.lines().collect(Collectors.joining("\n"));;
                log.info("Class: {}", '\n' + content);
            }
        }
    }
}
