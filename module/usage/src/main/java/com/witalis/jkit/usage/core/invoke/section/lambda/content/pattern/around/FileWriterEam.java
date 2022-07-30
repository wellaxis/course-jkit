package com.witalis.jkit.usage.core.invoke.section.lambda.content.pattern.around;

import lombok.extern.slf4j.Slf4j;

import java.io.FileWriter;
import java.io.IOException;

@Slf4j
public class FileWriterEam  {
    private final FileWriter writer;

    private FileWriterEam(final String fileName) throws IOException {
        writer = new FileWriter(fileName);
    }

    private void close() throws IOException {
        log.info("  close called automatically...");
        writer.close();
    }

    public void writeStuff(final String message) throws IOException {
        writer.write(message + System.lineSeparator());
    }

    public void writeSlogan() throws IOException {
        writeStuff(getSlogan());
    }

    private static String getSlogan() {
        return """
            Long methods are bad, but long lambda expressions are evil—we would lose
            the benefit of code that’s concise, easy to understand, and simple to maintain.
            Instead of writing long lambda expressions, we should move the code into
            other methods and then use method references for them if possible, or invoke
            them from within a lambda expression.
            """;
    }

    public static void use(
        final String fileName,
        final UseInstance<FileWriterEam, IOException> block
    ) throws IOException {
        final FileWriterEam writerEAM = new FileWriterEam(fileName);
        try {
            block.accept(writerEAM);
        } finally {
            writerEAM.close();
        }
    }
}
