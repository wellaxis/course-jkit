package com.witalis.jkit.usage.core.invoke.section.strings;

import com.witalis.jkit.usage.core.invoke.Invoker;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.StringUtils;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * Desc: String declaration
 * User: Wellaxis
 * Date: 2019/11/16
 */
@Slf4j
@Data
@EqualsAndHashCode(callSuper=false)
public class StringInvoker extends Invoker {

    public StringInvoker() {
        setTitle("String chapter.");
    }

    @Override
    public void invoke() {
        // basis
        log.info("## Basis");
        invokeBasis();
        // tab
        log.info("");
        // innovations
        log.info("## Innovations");
        invokeInnovation();
        // tab
        log.info("");
        // builders
        log.info("## Builders");
        invokeBuilder();
        // tab
        log.info("");
        // joiners
        log.info("## Joiners");
        invokeJoiner();
        // tab
        log.info("");
        // encoding
        log.info("## Encoding");
        invokeEncoding();
        // tab
        log.info("");
        // utilities
        log.info("## Utils");
        invokeUtils();
    }

    /**
     * Basic string operations.
     */
    private void invokeBasis() {
        // The String class represents character strings.
        // All string literals in Java programs, such as "abc", are implemented as instances of this class.

        log.info("Strings are immutable: their values cannot be changed after they are created.");

        // string pool value
        String java = "Java is a high-level, class-based, object-oriented programming language.";
        // another string instance
        String text = new String(java);

        // size
        log.info("Size=" + text.length());
        // char
        log.info("Char[0]=" + text.charAt(0));
        // part
        log.info("Sub=" + text.substring(5, 10));
        // trim
        log.info("Trim=" + "   It is a foo   ".trim());
        // index
        int index = text.indexOf(',', 20);
        log.info("Index=" + index);
        // bytes
        byte[] bytes = text.getBytes(Charset.defaultCharset());
        log.info("Bytes=" + bytes.length);
        // chars
        char[] chars = text.toCharArray();
        log.info("Count=" + chars.length);
        // start
        log.info("Starts=" + "FooBar".startsWith("Bar", 3));
        // concat
        log.info("Concat=" + "Foo".concat("Bar").concat("End"));
        // contain
        log.info("Contain=" + "FooBar".contains("Bar"));
        // compare
        log.info("Compare=" + "FooBar".compareToIgnoreCase("Foo"));
        // replace
        log.info("Replace=" + "FooBar".replace("oo", "ee"));
        // format
        log.info("Format=" + String.format("The 1st arg is %s and the 2nd is %s.%n", "foo", "bar"));
        // matches
        log.info("Match=" + "FooBar".matches("\\s+"));
        // split
        String[] split = text.split(",");
        for (String item : split) log.info("  -> " + item);
        // intern (from pool, if absent - put)
        String s1 = new String("Java");
        String s2 = "Java";
        String s3 = s1.intern();
        log.info("s3 != s1 [{}], but s2 = s3 [{}]", s1 == s3, s2 == s3);
    }

    /**
     * Innovation string operations.
     */
    private void invokeInnovation() {
        // String features since jdk8 and more.

        // text block
        String text =
            """
                Wikipedia is a multilingual open online encyclopedia written
                and maintained by a community of volunteers through a model
                of open collaboration, using a wiki-based editing system.
                """;

        // strip
        log.info("Strip=" + "  \t \n \n  The text content.  \f \u000B  ".strip());
        // repeat
        log.info("Repeat=" + "ABC".repeat(5));
        // lines
        String multiline = "ABC\nDEF\nGHI\n.";
        log.info("Lines:");
        multiline.lines().forEach(log::info);
    }

    /**
     * String builders.
     */
    private void invokeBuilder() {
        // string builder - not synchronized [+ performance]
        {
            log.info(" * String Builder");
            StringBuilder builder = new StringBuilder("Begin");
            builder.append(" ").append("class").append(".")
                .append("method")
                .append("(").append("builder").append(")")
                .append(";");
            builder.append(" End");
            builder.insert(27, " body");
            log.info(builder.toString());
            log.info(" - Length=" + builder.length());
            log.info(" - Capacity=" + builder.capacity());
            builder.substring(10, 20);
        }

        // string buffer - synchronized [+ thread safe]
        {
            log.info(" * String Buffer");
            StringBuffer buffer = new StringBuffer("Begin");
            buffer.append(" ").append("class").append(".")
                .append("method")
                .append("(").append("buffer").append(")")
                .append(";");
            buffer.append("TEST");
            buffer.append(" End");
            buffer.delete(27, 31);
            log.info(buffer.toString());
            log.info(" - Length=" + buffer.length());
            log.info(" - Capacity=" + buffer.capacity());
            buffer.reverse();
        }
    }

    /**
     * String joiners.
     */
    private void invokeJoiner() {
        // string joiner - used to construct a sequence of characters separated by a delimiter
        {
            log.info(" * String Joiner");
            String delimiter = "|";

            StringJoiner joiner1 = new StringJoiner(delimiter);
            joiner1.add("One").add("Two").add("Three").add("Four").add("Five");
            log.info("Joiner[1]: {}", joiner1);

            String prefix = "<";
            String suffix = ">";
            StringJoiner joiner2 = new StringJoiner(delimiter, prefix, suffix);
            joiner2.add("Two").add("Four").add("Six").add("Eight").add("Ten");
            log.info("Joiner[2]: {}", joiner2);

            StringJoiner merge = joiner1.merge(joiner2);
            log.info("Joiner[merge]: {}, len = {}", merge, merge.length());

            // sample
            log.info(" * Sample Joiner");
            var names = new StringJoiner(":", "[", "]");
            names.add("George").add("Sally").add("Fred");
            log.info("Names: {}, len = {}", names, names.length());
            var appender = new StringJoiner(",").add("Bob").add("Alex");
            names.merge(appender);
            log.info("Names: {}", names);
        }

        // string join - inner method
        {
            log.info(" * String join method");
            String delimiter = "|";

            String result = String.join(delimiter, "One", "Two", "Three", "Four", "Five");
            log.info("Join[method]: {}", result);
        }
    }

    /**
     * String encoding.
     */
    private void invokeEncoding() {
        log.info(" * String Encoding");

        var charset = Charset.defaultCharset();
        log.info("Default charset: {}", charset.name());

        SortedMap<String,Charset> charsets = Charset.availableCharsets();
        log.info("Available charsets: {}", charsets.size());

        // encoding vs decoding
        {
            var message = "Dziękuję";

            // default
            byte[] defEncoding = message.getBytes();
            // charset
            Charset cp866Charset = Charset.forName("cp866");
            byte[] cp866Encoding = message.getBytes(cp866Charset);
            // by name
            byte[] winEncoding = new byte[0];
            try {
                winEncoding = message.getBytes("Windows-1251");
            } catch (UnsupportedEncodingException e) {
                log.error("Unable to encode message", e);
            }

            // decoding
            var defMessage = new String(defEncoding);
            var cp866Message = new String(cp866Encoding, cp866Charset);
            String winMessage = null;
            try {
                winMessage = new String(winEncoding, "Windows-1251");
            } catch (UnsupportedEncodingException e) {
                log.error("Unable to decode message", e);
            }

            log.info("En/De-coding [{}]: {}, {}, {}",
                message,
                message.equals(defMessage),
                message.equals(cp866Message),
                message.equals(winMessage)
            );
        }

        // ok
        var english = "Develop with pleasure";
        var englishBytes = english.getBytes();
        var asciiEnglish = new String(englishBytes, StandardCharsets.US_ASCII);
        log.info(
            "ENG [{}]: {}, {}",
            english.equals(asciiEnglish),
            english,
            asciiEnglish
        );

        // problem
        var german = "Entwickeln Sie mit Vergnügen";
        var germanBytes = german.getBytes();
        var asciiGerman = new String(germanBytes, StandardCharsets.US_ASCII);
        log.info(
            "GER [{}]: {}, {}",
            german.equals(asciiGerman),
            german,
            asciiGerman
        );
        // encoding vs decoding synchronized
        germanBytes = german.getBytes(StandardCharsets.UTF_16);
        asciiGerman = new String(germanBytes, StandardCharsets.UTF_16);
        log.info(
            "GER [{}]: {}, {}",
            german.equals(asciiGerman),
            german,
            asciiGerman
        );

        // java 7
        ByteBuffer germanBuffer = StandardCharsets.UTF_8.encode(german);
        String utf8German = StandardCharsets.UTF_8.decode(germanBuffer).toString();
        log.info(
            "GER [{}]: {}, {}",
            german.equals(utf8German),
            german,
            utf8German
        );

        // commons codec - apache
        byte[] utf8Bytes = StringUtils.getBytesUtf8(german);
        String utf8String = StringUtils.newStringUtf8(utf8Bytes);
        log.info(
            "GER [{}]: {}, {}",
            german.equals(utf8String),
            german,
            utf8String
        );
    }

    /**
     * String utilities.
     */
    private void invokeUtils() {
        // apache commons
        String text = "Text";
        byte[] utf16Bytes = StringUtils.getBytesUtf16(text);
        String utf16String = StringUtils.newStringUtf16(utf16Bytes);
        log.info("Encoding: {} -> {} -> {}", text, Arrays.toString(utf16Bytes), utf16String);
    }
}
