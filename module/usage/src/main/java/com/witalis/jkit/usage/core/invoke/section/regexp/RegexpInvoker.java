package com.witalis.jkit.usage.core.invoke.section.regexp;

import com.witalis.jkit.usage.core.invoke.Invoker;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static com.witalis.jkit.usage.core.invoke.section.regexp.content.RegexpUtils.qty;

/**
 * Desc: Regular expressions
 * User: Wellaxis
 * Date: 2021/03/29
 */
@Slf4j
@Data
@EqualsAndHashCode(callSuper=false)
public class RegexpInvoker extends Invoker {

    public RegexpInvoker() {
        setTitle("Regular chapter.");
    }

    @Override
    public void invoke() {
        // basis
        log.info("## Basis");
        invokeBasis();
        // tab
        log.info("");
        // regex
        log.info("## Regex");
        invokeRegex();
        // tab
        log.info("");
        // wildcard
        log.info("## Wildcards");
        invokeWildcard();
        // tab
        log.info("");
        // matches
        log.info("## Matches");
        invokeMatch();
        // tab
        log.info("");
        // filters
        log.info("## Filters");
        invokeFilter();
        // tab
        log.info("");
        // scenarios
        log.info("## Scenarios");
        invokeScenario();
    }

    /**
     * Basic postulates of regular expression.
     */
    private void invokeBasis() {
        // pattern - is the regular expression that you want to use
        // matcher - matches the pattern against another sequence
        // * matches() - the entire sequence must match the pattern
        // * find() - a subsequence of the input sequence matches the pattern

        log.info("A regular expression (regexp) is a sequence of characters that specifies a search pattern in text.");

        // Usually such patterns are used by string-searching algorithms
        // for "find" or "find and replace" operations on strings, or for input validation.
    }

    /**
     * Regular expressions.
     */
    private void invokeRegex() {
        String regex = "Java";
        String value = "Java";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(value);

        boolean matches = matcher.matches();
        log.info(
            " - Pattern [match]: {}, regex  = '{}', value = '{}'",
            matches, regex, value
        );

        value = value.toLowerCase();
        matches = pattern.matcher(value).matches();
        log.info(
            " - Pattern [match]: {}, regex  = '{}', value = '{}'",
            matches, regex, value
        );

        value = "Java in Action";
        var find = pattern.matcher(value).find();
        log.info(
            " - Pattern [find]: {}, regex  = '{}', value = '{}'",
            find, regex, value
        );

        value = "I like Java, we like Java and he likes Java too...";
        matcher = pattern.matcher(value);
        while (matcher.find()) {
            int start = matcher.start();
            int end = matcher.end();
            log.info(
                " - Pattern [find] '{}' since {} till {}: regex  = '{}', value = '{}'",
                value.substring(start, end), start, (end - 1), regex, value
            );
        }

        var kotlin = matcher.replaceFirst("Kotlin");
        log.info(" - Matcher [replaceFirst]: {}", kotlin);

        var go = matcher.replaceAll("Go");
        log.info(" - Matcher [replaceAll]: {}", go);
    }

    /**
     * Wildcards & quantifiers
     */
    private void invokeWildcard() {
        // group
        {
            final var regex = "[a-z]+";
            var input = "It is just a test...";

            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(input);

            while (matcher.find())
                log.info(" - Pattern [words]: {}", matcher.group());
        }
        // replace
        {
            final var regex = "Jon.*? ";
            var input = "Jon Jonathan Frank Ken Jonson";

            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(input);

            log.info(" - Pattern [replace]: before = {}", input);
            var output = matcher.replaceAll("Eric ");
            log.info(" - Pattern [replace]: after = {}", output);
        }
        // split
        {
            final var regex = "[ ,.!)]";
            var input = "one two, three!four,,five.six   last)";

            Pattern pattern = Pattern.compile(regex);

            // limit = 0, default
            String[] items = pattern.split(input);
            var collect = Arrays.stream(items)
                .filter(i -> !i.isBlank())
                .collect(Collectors.toList());
            log.info(" - Pattern [split,0]: {}", collect.toString());

            // limit > 0, (limit - 1) groups
            items = pattern.split(input, 5);
            collect = Arrays.stream(items)
                .filter(i -> !i.isBlank())
                .collect(Collectors.toList());
            log.info(" - Pattern [split,3]: {}", collect.toString());

            // limit < 0, all group, incl. empty at the end
            items = pattern.split(input, -1);
            collect = Arrays.stream(items)
                .collect(Collectors.toList());
            log.info(" - Pattern [split,-1]: {}", collect.toString());
        }
    }

    /**
     * Quantity of matches.
     */
    private void invokeMatch() {
        var input = "1) AnElvis is not an elvis yet, and 2) elvisOf not an elvis too cool ... 9) done";

        // check whether input matches the regex
        final var apply = ".*\\belvis\\b.*";
        Pattern pattern = Pattern.compile(apply);
        Matcher matcher = pattern.matcher(input);
        var matches = matcher.matches();
        log.info(" - Pattern [matches]: {}", matches);

        // check whether input matches the regex
        pattern = Pattern.compile(apply, Pattern.CASE_INSENSITIVE);
        matches = pattern.matcher(input).matches();
        log.info(" - Pattern [matches insensitive]: {}", matches);

        // chars
        log.info(" - Pattern [qty of elvis]: {}", qty("\\belvis\\b", input));
        log.info(" - Pattern [qty of chars]: {}", qty("[abcd]", input));
        log.info(" - Pattern [qty of Uppers]: {}", qty("[A-Z]", input));
        log.info(" - Pattern [qty of alphas]: {}", qty("[a-zA-Z]", input));

        // digits
        log.info(" - Pattern [qty of digits 0..5]: {}", qty("[0-5]", input));
        log.info(" - Pattern [union of digits]: {}", qty("[1-3[7-9]]", "123456789"));
        log.info(" - Pattern [intersection of digits]: {}", qty("[1-6&&[3-9]]", "123456789"));
        log.info(" - Pattern [subtraction digits - odds]: {}", qty("[0-9&&[^2468]]", "123456789"));

        // specs
        log.info(" - Pattern [qty of digits]: {}", qty("\\d", input));
        log.info(" - Pattern [qty of non-digits]: {}", qty("\\D", input));
        log.info(" - Pattern [qty of spaces]: {}", qty("\\s", input));
        log.info(" - Pattern [qty of non-spaces]: {}", qty("\\S", input));
        log.info(" - Pattern [qty of chars]: {}", qty("\\w", input));
        log.info(" - Pattern [qty of non-chars]: {}", qty("\\W", input));

        // quantifiers
        // To match a text zero or one time, we use the '?' quantifier
        // - The third match is zero-length empty String.
        log.info(" - Pattern [0 or 1 time]: {}", qty("\\a?", "Hi"));
        log.info(" - Pattern [0 or 1 time]: {}", qty("\\a{0,1}", "Hi"));
        // To match a text zero or limitless times, we us '*' quantifier
        log.info(" - Pattern [0 or many times]: {}", qty("\\a*", "Hi"));
        log.info(" - Pattern [0 or many times]: {}", qty("\\a{0,}", "Hi"));
        // To match a text one or one time, we use the '+' quantifier
        log.info(" - Pattern [1 or many times]: {}", qty("\\a+", "Hi"));
        log.info(" - Pattern [1 or many times]: {}", qty("\\a{1,}", "Hi"));
    }

    /**
     * Regex filtering.
     */
    private void invokeFilter() {
        // file names
        {
            var regex = "^(pg_[[r[R]]{1}|[n[N]]{1}]{1}\\d{8})(.+)$";
            Pattern pattern = Pattern.compile(regex);
            String[] names = new String[]{
                "pg_r20170707_18_p_grt9562zt_20170707_1.dbf",
                "pg_b_18_zt_102017_err.dbf",
                "pg_n20170707_18_p_grt9562zt_20170707_1.dbf",
                "pg_R20171013_18_p_osh_0005zt_20171012_2860520.dbf"
            };
            for (String name : names) {
                Matcher matcher = pattern.matcher(name);
                String mode = "";
                if (matcher.matches()) {
                    mode = matcher.group(1);
                    if (mode != null) {
                        mode = mode.substring(3, 4).toLowerCase();
                    }
                } else {
                    mode = "e";
                }
                log.info("File [{}] -> {}", mode, name);
            }
        }
    }

    /**
     * Frequent use-cases.
     */
    private void invokeScenario() {
        // string exact matches
        {
            log.info("Case-1: Is string an exact match for a specific pattern?");
            final var regex = "^#{1}[\\w]+[^#]\\b$";
            var pattern = Pattern.compile(regex);
            var hashtags= List.of("#hello", "hi#all", "#hash ", "#12345", "#", " #like#ok");
            for (String hashtag: hashtags) {
                var match = pattern.matcher(hashtag).matches();
                log.info(" - Hashtag '{}' matches -> {}", hashtag, match);
            }
        }

        // sub-string contains
        {
            log.info("Case-2: Is sub-string contains into a specific pattern?");
            final var regex = ".*(\\bbegin\\b)\\D*ID=\\d{3}\\D*(\\bend\\b).*";
            var pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
            var programs= List.of(
                " program: begin test(id=345) end;",
                " program: begin test(id=6789) end;",
                " Job: Begin. Run(Id=359). Log. End.",
                " Todo - Start - ID=123 - End - Done"
            );
            for (String program: programs) {
                var match = pattern.matcher(program).matches();
                log.info(" - Program '{}' matches -> {}", program, match);
            }
        }

        // quantity of matches
        {
            log.info("Case-3: How many times the pattern is in the original string?");
            final var regex = "[A-Z]{2}\\s{1}\\d{4}\\s{1}[A-Z]{2}";
            var pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
            var vehicles = " Auto numbers: 'AA 1234 AB', '001', 'KA 4567 CE', '22 AH 0990', 'Kitty', 'MM 2974', 'S 004015'. Take one ";
            var matcher = pattern.matcher(vehicles);
            int counter = 0;
            while (matcher.find()) {
                var group = matcher.group();
                int start = matcher.start();
                int end = matcher.end();
                log.info(
                    " - Vehicle [number] '{}', group /{}/: since {} till {}",
                    vehicles.substring(start, end), group, start, (end - 1)
                );
                counter++;
            }
            log.info(" - Vehicle: total numbers: {}", counter);
        }

        // match & replace string
        {
            log.info("Case-4: Find patterns in the original string and replace them with substrings!");
            final var regex = "\\bK{1}\\w+[v|w]{1}\\b";
            var pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE|Pattern.MULTILINE);
            var cities = " The best cities are Kyiv, Koeln, Kyoto, Kirov, Kovel, Krakow, etc";
            var matcher = pattern.matcher(cities);
            log.info(" - Cities: original: {}", cities);
            log.info(" - Cities: single: {}", matcher.replaceFirst("Kharkiv"));
            log.info(" - Cities: all: {}", matcher.replaceAll("Kharkiv"));
        }

        // split string by separator
        {
            log.info("Case-5: Split the original string into an array of substrings by pattern separator!");
            final var regex = "[\\s,|]+ # group with delimiters";
            var pattern = Pattern.compile(regex, Pattern.COMMENTS);
            var names = " Petro, Vasya, Mila, Damon|Bob |Sam. Kitty! Hank, Dan ";
            var string = names.split(regex);
            var split = pattern.split(names);
            var list = pattern.splitAsStream(names)
                .filter(s -> !s.isEmpty())
                .sorted(Comparator.naturalOrder())
                .collect(Collectors.toUnmodifiableList());
            log.info(" - Names [all]: {}", names);
            log.info(" - Names [string]: {}", Arrays.toString(string));
            log.info(" - Names [split]: {}", Arrays.toString(split));
            log.info(" - Names [list]: {}", list);
        }

        // find duplicates
        {
            log.info("Case-6: Finding duplicated words!");
            final var regex = "\\b(\\w+)\\s+\\1\\b";
            var pattern = Pattern.compile(regex, Pattern.MULTILINE);
            var colors = " red blue green  green black red yellow   grey grey brown";
            var matcher = pattern.matcher(colors);
            log.info(" - Colors: original: {}", colors);
            int counter = 0;
            while (matcher.find()) {
                var group = matcher.group();
                int start = matcher.start();
                int end = matcher.end();
                log.info(
                    " - Colors [duplicate] '{}', group /{}/: since {} till {}",
                    colors.substring(start, end), group, start, (end - 1)
                );
                counter++;
            }
            log.info(" - Colors: total duplicates: {}", counter);
        }

        // find prefix duplicates
        {
            log.info("Case-7: Finding duplicated words (not start with 'gr')!");
            final var regex = "(?!gr)\\b(\\w+)\\s+\\1\\b";
            var pattern = Pattern.compile(regex, Pattern.MULTILINE);
            var colors = " red blue green  green black black red yellow   grey grey brown";
            var matcher = pattern.matcher(colors);
            log.info(" - Colors: original: {}", colors);
            int counter = 0;
            var finds = matcher.find();
            log.info(" - Colors: find duplicates: {}", finds);
        }
    }
}
