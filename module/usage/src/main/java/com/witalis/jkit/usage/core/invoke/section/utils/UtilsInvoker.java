package com.witalis.jkit.usage.core.invoke.section.utils;

import com.witalis.jkit.usage.core.invoke.Invoker;
import com.witalis.jkit.usage.core.invoke.section.utils.content.bundle.*;
import com.witalis.jkit.usage.core.invoke.section.utils.content.timer.*;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.*;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Desc: utilities
 * User: Wellaxis
 * Date: 2019/11/16
 */
@Slf4j
@Data
@EqualsAndHashCode(callSuper=false)
@SuppressWarnings({"deprecation", "unchecked"})
public class UtilsInvoker extends Invoker {

    public UtilsInvoker() {
        setTitle("Utils chapter.");
    }

    @Override
    public void invoke() {
        // basis
        log.info("## Basis");
        invokeBasis();
        // tab
        log.info("");
        // tokenizer
        log.info("## Tokenizer");
        invokeTokenizer();
        // tab
        log.info("");
        // formatter
        log.info("## Formatter");
        invokeFormatter();
        // tab
        log.info("");
        // scanner
        log.info("## Scanner");
        invokeScanner();
        // tab
        log.info("");
        // bits
        log.info("## Bits");
        invokeBits();
        // tab
        log.info("");
        // locale
        log.info("## Locale");
        invokeLocale();
        // tab
        log.info("");
        // random
        log.info("## Random");
        invokeRandom();
        // tab
        log.info("");
        // timer
        log.info("## Timer");
        invokeTimer();
        // tab
        log.info("");
        // localization
        log.info("## Localization");
        invokeLocalization();
        // tab
        log.info("");
        // observer
        log.info("## Observer");
        invokeObserver();
        // tab
        log.info("");
        // optional
        log.info("## Optional");
        invokeOptional();
        // tab
        log.info("");
        // others
        log.info("## Others");
        invokeOthers();
    }

    /**
     * Basic postulates of utilities.
     */
    private void invokeBasis() {
        log.info("There are a lot of different utilities (internal & external).");
    }

    /**
     * Tokenizer functionality.
     */
    private void invokeTokenizer() {
        log.info("---------------- Tokenizer ----------------");

        final String data =
            """
            Book=Java in Action;
            Version=1.99;
            Chapter=Java Utils;
            Price=23.99
            """;

        StringTokenizer tokenizer = new StringTokenizer(data, "=;\n");
        while (tokenizer.hasMoreTokens()) {
            String key = tokenizer.nextToken();
            String value = tokenizer.nextToken();
            log.info(" * Item: " + key + " => " + value);
        }
    }

    /**
     * Formatter functionality.
     */
    private void invokeFormatter() {
        log.info("---------------- Formatter ----------------");

        // formatter
        {
            Formatter formatter = new Formatter();
            // format
            formatter.format(
                "%n Object is '%s', Value is %s, but %S, Others - %c vs %d vs %f vs %e vs %b vs %tT",
                "formatter",
                "'initial'",
                "'initial'",
                '\007',
                Integer.MAX_VALUE,
                7.7F,
                Math.E,
                true,
                Calendar.getInstance()
            );
            log.info(" * Formatter: " + formatter.toString());
            // out
            var appendable = formatter.out();
            log.info(" * Appendable is string builder: " + (appendable instanceof StringBuilder));
            // io exception
            var exception = formatter.ioException();
            log.info(" * IOException: " + exception);
            // flush
            formatter.flush();
            // close
            formatter.close();

            // resources
            {
                var usaLocale = Locale.US;
                var chyLocale = Locale.CHINA;
                var ukrLocale = new Locale("uk", "UA");
                var format = "Day is %te of %<tB - %<tA, %<tj of %<tY. Time is %<tH : %<tM : %<tS . %<tL. Zone is %<tZ";
                var calendar = Calendar.getInstance();
                // auto-closable
                try (var def = new Formatter(Locale.getDefault());
                     var ukr = new Formatter(ukrLocale);
                     var usa = new Formatter(usaLocale);
                     var chy = new Formatter(chyLocale)
                ) {
                    log.info(" * Date[DEF]: " + def.format(format, calendar).toString());
                    log.info(" * Date[UKR]: " + ukr.format(format, calendar).toString());
                    log.info(" * Date[USA]: " + usa.format(format, calendar).toString());
                    log.info(" * Date[CHY]: " + chy.format(format, calendar).toString());
                }
            }

            // multiple
            {
                var indexing = new Formatter();
                indexing.format(
                    "%4$d, %2$d, %5$d, %3$d, %1$d.",
                    10L, 20L, 30L, 40L, 50L
                );
                log.info(" * Indexing[$]: " + indexing.toString());
                var repeating = new Formatter();
                repeating.format(
                    "%d, %<d, %<d, %<d, %<d.",
                    10L
                );
                log.info(" * Repeating[<]: " + repeating.toString());
            }

            // special
            {
                log.info(" * Special[" + Locale.getDefault() + "]:");
                // value
                var value = 1234.1234D;
                log.info("   ! value = " + value);
                // format
                log.info("   > format [%f]: " + new Formatter().format("[%f]", value).toString());
                // scale
                log.info("   > scale [%.2f]: " + new Formatter().format("[%.2f]", value).toString());
                // groups
                log.info("   > groups [%,15f]: " + new Formatter().format("[%,15f]", value).toString());
                // length
                log.info("   > length [%15f]: " + new Formatter().format("[%15f]", value).toString());
                // spaces
                log.info("   > spaces [% 15f]: " + new Formatter().format("[% 15f]", value).toString());
                // zeroes
                log.info("   > zeroes [%015f]: " + new Formatter().format("[%015f]", value).toString());
                // plus
                log.info("   > plus [%+15f]: " + new Formatter().format("[%+15f]", value).toString());
                // minus
                log.info("   > minus [%(15f]: " + new Formatter().format("[%(15f]", -value).toString());
                // left
                log.info("   > left [%-15.1f]: " + new Formatter().format("[%-15.1f]", value).toString());
            }
        }

        // string formatting
        {
            var format = String.format(Locale.US, "|%,d|", 10000000);
            log.info(" * String formatter: " + format);
        }
    }

    /**
     * Scanner functionality.
     */
    private void invokeScanner() {
        log.info("---------------- Scanner ----------------");

        String data = "To scan some string values...";
        Scanner scanner = new Scanner(data);
        log.info(" * Scanner: " + scanner.toString());
        // sample
        var builder = new StringBuilder("BEGIN >>> ");
        int index = 0;
        while (scanner.hasNext()) {
            var next = scanner.next();
            builder.append(++index).append(") ").append(next).append(" ");
        }
        builder.append(" <<< END");
        log.info(" * Item: " + builder.toString());

        // resources
        URL sourceUrl = getClass().getClassLoader().getResource("file/source.txt");
        if (sourceUrl != null) {
            try (var scn = new Scanner(new FileReader(new File(sourceUrl.getFile())))) {
                builder = new StringBuilder("File [source.txt], content: \"");
                while (scn.hasNext()) {
                    var next = scn.next();
                    builder.append(next).append(" ");
                }
                builder.append("\" Done");
                log.info(" * File: " + builder.toString());
            } catch (FileNotFoundException e) {
                log.error("Resource file not found: " + e.getMessage());
            }
        }

        // different data
        sourceUrl = getClass().getClassLoader().getResource("file/data.txt");
        if (sourceUrl != null) {
            try (var scn = new Scanner(new FileReader(new File(sourceUrl.getFile())))) {
                scn.useDelimiter(" ");
                log.info(" * File [data.txt], delimiter[" + scn.delimiter() + "], content:");
                // scan
                while (scn.hasNext()) {
                    if (scn.hasNextInt())
                        log.info("   > int: " + scn.nextInt());
                    else if (scn.hasNextDouble())
                        log.info("   > double: " + scn.nextDouble());
                    else if (scn.hasNextBoolean())
                        log.info("   > boolean: " + scn.nextBoolean());
                    else
                        log.info("   > string: " + scn.next());
                }
                log.info(" * File: " + builder.toString());
            } catch (FileNotFoundException e) {
                log.error("Resource file not found: " + e.getMessage());
            }
        }

        // search data
        final var info = "Name: Alex, Age: 77, Phone: +30857204983.";
        log.info(" * Search[info]: " + info);
        var searcher = new Scanner(info);
        searcher.useDelimiter(", ");
        // skip
        var skip = searcher.skip("Name:");
        log.info(" * Search[skip]: " + skip);
        // find
        var token = searcher.findInLine("Age:");
        log.info(" * Search[find]: " + token);
        if (searcher.hasNext()) {
            log.info(" * Search[age]: " + searcher.next());
        }
        // horizon
        var horizon = Optional.ofNullable(searcher.findWithinHorizon("Phone:", 1));
        log.info(" * Search[horizon]: " + horizon);
        searcher.close();
    }

    /**
     * Bits functionality.
     */
    private void invokeBits() {
        log.info("---------------- Bits ----------------");

        final int len = 16;

        // creation
        BitSet bs1 = new BitSet(len);
        var bs2 = new BitSet(len);
        log.info(" * BitSet[1][before]: {}", bs1);
        log.info(" * BitSet[2][before]: {}", bs2);

        // initialization
        for (int i = 0; i < len; i++) {
            if ((i % 2) == 0) bs1.set(i);
            if ((i % 5) != 0 ) bs2.set(i);
        }
        log.info(" * BitSet[1][after]: {}", bs1);
        log.info(" * BitSet[2][after]: {}", bs2);

        var size = bs1.size();
        log.info(" * BitSet[size]: {}", size);
        var cardinality = bs1.cardinality();
        log.info(" * BitSet[cardinality]: {}", cardinality);
        var fifth = bs1.get(5);
        log.info(" * BitSet[get]: {}", fifth);
        bs1.set(5, true);
        log.info(" * BitSet[set]: {}", bs1.get(5));
        bs1.flip(5);
        log.info(" * BitSet[flip]: {}", bs1.get(5));

        //  logical AND
        bs2.and(bs1);
        log.info(" * BitSet[&]: {}", bs2);
        //  logical OR
        bs2.or(bs1);
        log.info(" * BitSet[|]: {}", bs2);
        //  logical XOR
        bs2.xor(bs1);
        log.info(" * BitSet[^]: {}", bs2);
    }

    /**
     * Locale functionality.
     */
    private void invokeLocale() {
        // locale
        log.info("---------------- Locale ----------------");
        {
            var localeDefault = Locale.getDefault();
            log.info(" * Locale default: " + localeDefault);
            var french = Locale.FRENCH;
            log.info(" * Locale french: " + french);

            Locale ukrainian = new Locale("uk", "UA");
            log.info(" * Locale ukrainian: " + ukrainian);
            var displayName = ukrainian.getDisplayName();
            log.info("   - display name: " + displayName);
            var displayCountry = ukrainian.getDisplayCountry();
            log.info("   - display country: " + displayCountry);
            var displayLanguage = ukrainian.getDisplayLanguage();
            log.info("   - display language: " + displayLanguage);
            var displayScript = ukrainian.getDisplayScript();
            log.info("   - display script: " + displayScript);
            var iso3Country = ukrainian.getISO3Country();
            log.info("   - ISO3 language: " + iso3Country);
            var iso3Language = ukrainian.getISO3Language();
            log.info("   - ISO3 language: " + iso3Language);
            var languageTag = ukrainian.toLanguageTag();
            log.info("   - Tag language: " + languageTag);

            var locales = Locale.getAvailableLocales();
            log.info(" * Locale list size: " + locales.length);
            var isoCountries = Locale.getISOCountries();
            log.info(" * ISO Countries: " + isoCountries.length);
            var isoLanguages = Locale.getISOLanguages();
            log.info(" * ISO Languages: " + isoLanguages.length);
        }

        // currency
        log.info("---------------- Currency ----------------");
        {
            Currency currencyDef = Currency.getInstance(Locale.getDefault());
            log.info(" * Currency default: " + currencyDef);
            log.info(" * Currency symbol: " + currencyDef.getSymbol());
            log.info(" * Currency code: " + currencyDef.getCurrencyCode());
            log.info(" * Currency numeric code [ISO 4217]: " + currencyDef.getNumericCode());
            log.info(" * Currency display name: " + currencyDef.getDisplayName());
            log.info(" * Currency fraction: " + currencyDef.getDefaultFractionDigits());

            Currency currencyUkr = Currency.getInstance(new Locale("uk", "UA"));
            log.info(" * Currency ukrainian: " + currencyUkr);
            log.info(" * Currency symbol: " + currencyUkr.getSymbol());
            log.info(" * Currency code: " + currencyUkr.getCurrencyCode());
            log.info(" * Currency numeric code [ISO 4217]: " + currencyUkr.getNumericCode());
            log.info(" * Currency display name: " + currencyUkr.getDisplayName());
            log.info(" * Currency fraction: " + currencyUkr.getDefaultFractionDigits());
        }
    }

    /**
     * Random functionality.
     */
    private void invokeRandom() {
        log.info("---------------- Random ----------------");

        // random
        var random = ThreadLocalRandom.current();
        // next
        log.info(" * Random boolean: " + random.nextBoolean());
        log.info(" * Random int: " + random.nextInt());
        log.info(" * Random long: " + random.nextLong());
        log.info(" * Random float: " + random.nextFloat());
        log.info(" * Random double: " + random.nextDouble());
        // bell curve
        log.info(" * Random gaussian: " + random.nextGaussian());
        // next bound
        int bound = 100;
        log.info(" * Random int bound: " + random.nextInt(bound));
        // next bytes
        byte[] bytes = new byte[10];
        random.nextBytes(bytes);
        log.info(" * Random bytes: " + Arrays.toString(bytes));
        // seed
        var seed = 1000L;
        Random seededRandom = new Random(seed);
        for (int i = 0; i < 5; i++)
            log.info(" * Random[" + i + "] seed: " + seededRandom.nextInt());
        // stream
        var ints = new Random().ints();
        var longs = new Random().longs();
        var doubles = new Random().doubles();
        // sample
        {
            Random r = new Random();
            double value, sum = 0;
            var bell = new int[10];
            for (int i = 0; i < 100; i++) {
                value = r.nextGaussian();
                sum += value;
                double t = -2;
                for (int j = 0; j < 10; j++, t += 0.5) {
                    if (value < t) {
                        bell[j]++;
                        break;
                    }
                }
            }
            log.info(" * Average value: " + (sum / 100));
            log.info(" * Gaussian graph:");
            var builder = new StringBuilder();
            for (int i = 0; i < 10; i++) {
                builder.delete(0, builder.capacity());
                for (int j = bell[i]; j > 0; j--)
                    builder.append("~");
                log.info(builder.toString());
            }
        }
        // mathematics
        var mathRandom = Math.random();
        log.info(" * Random math: " + mathRandom);
    }

    /**
     * Timer functionality.
     */
    private void invokeTimer() {
        log.info("---------------- Timer ----------------");

        // #1 - create task (invoke every second)
        UtilsTimerTask utilsTask = new UtilsTimerTask();
        Timer timer1 = new Timer();
        timer1.scheduleAtFixedRate(utilsTask, 100, 1000);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            log.error("Timer errors " + e.getMessage());
        }
        timer1.cancel();

        // #2 - schedule task (invoke in 1 second)
        Timer timer2 = new Timer();
        timer2.schedule(
            new TimerTask() {
                @Override
                public void run() {
                    log.info(" > One time task -> executed: " + LocalTime.now());
                }
            },
            100
        );
        timer2.cancel();
    }

    /**
     * Localization functionality.
     */
    private void invokeLocalization() {
        log.info("---------------- Localization ----------------");

        // The language codes are defined by ISO standard 639
        // The country codes are defined by ISO standard 3166
        log.info(" * Revised 9");

        // reader resource bundle
        {
            log.info(" *** Resource Bundle [Reader]");
            // handle
            try {
                Reader reader = new StringReader(
                    "language=ISO639\n country=ISO3166"
                );
                ResourceBundle resourceBundle = new PropertyResourceBundle(reader);
                var name = resourceBundle.getBaseBundleName();
                log.info(" * Base bundle name: " + name);
                var keys = resourceBundle.keySet();
                log.info(" * Bundle keys: " + Arrays.toString(keys.toArray()));
                var values = new LinkedHashSet<String>(keys.size());
                for (String key : resourceBundle.keySet())
                    values.add(resourceBundle.getString(key));
                log.info(" * Bundle values: " + Arrays.toString(values.toArray()));
            } catch (Exception e) {
                log.error("Reader resource bundle errors: " + e.getLocalizedMessage());
            }
        }

        // file resource bundle
        {
            log.info(" *** Resource Bundle [File]");
            // handle
            try {
                var fileName = "bundle/kit.properties";
                var url = getClass().getClassLoader().getResource(fileName);
                assert url != null : "bundle file with resources has not been found";
                var file = new File(url.getFile());
                try (var fis = new FileInputStream(file)) {
                    var bundle = new PropertyResourceBundle(fis);
                    var iterator = bundle.getKeys().asIterator();
                    // keys
                    var keys = new LinkedHashSet<String>();
                    while (iterator.hasNext()) {
                        keys.add(iterator.next());
                    }
                    log.info(" * Properties[keys]: " + Arrays.toString(keys.toArray()));
                    // values
                    var values = new LinkedHashSet<String>();
                    for (String key : keys) {
                        values.add(bundle.getString(key));
                    }
                    log.info(" * Properties[values]: " + Arrays.toString(values.toArray()));
                }
            } catch (Exception e) {
                log.error("Property resource bundle errors: " + e.getLocalizedMessage());
            }
        }

        // list resource bundle
        {
            log.info(" *** Resource Bundle [List]");
            // handle
            try {
                var locale = Locale.getDefault();
                var currency = new CurrencyBundle();
                var canonicalName = currency.getClass().getCanonicalName();
                var bundle = ResourceBundle.getBundle(canonicalName, locale);
                // default
                log.info(" * Locale: " + locale);
                log.info("   - currency: " + bundle.getObject("currency"));
                log.info("   - price   : " + bundle.getObject("price"));
                // american
                locale = Locale.US;
                bundle = ResourceBundle.getBundle(canonicalName, locale);
                log.info(" * Locale: " + locale);
                log.info("   - currency: " + bundle.getObject("currency"));
                log.info("   - price   : " + bundle.getObject("price"));
                // ukrainian
                locale = new Locale("uk", "UA");
                bundle = ResourceBundle.getBundle(canonicalName, locale);
                log.info(" * Locale: " + locale);
                log.info("   - currency: " + bundle.getObject("currency"));
                log.info("   - price   : " + bundle.getObject("price"));
                // polish
                locale = new Locale("pl", "PL");
                bundle = ResourceBundle.getBundle(canonicalName, locale);
                log.info(" * Locale: " + locale);
                log.info("   - currency: " + bundle.getObject("currency"));
                log.info("   - price   : " + bundle.getObject("price"));
            } catch (Exception e) {
                log.error("List resource bundle errors: " + e.getLocalizedMessage());
            }
        }

        // property resource bundle
        {
            log.info(" *** Resource Bundle [Property]");
            // handle
            Locale current = Locale.getDefault();
            try {
                var baseName = "bundle/kit";
                // default
                var locale = current;
                var bundle = ResourceBundle.getBundle(baseName, locale);
                var builder = new StringBuilder(" > locale[" + locale + "], values: ");
                for (String key: bundle.keySet())
                    builder.append(bundle.getString(key)).append(" ");
                log.info(builder.toString());
                // ukrainian
                locale = new Locale("uk", "UA");
                Locale.setDefault(locale);
                bundle = ResourceBundle.getBundle(baseName, locale);
                builder = new StringBuilder(" > locale[" + locale + "], values: ");
                for (String key: bundle.keySet()) {
                    var value = new String(bundle.getString(key).getBytes("Cp1252"), "Cp1251");
                    builder.append(value).append(" ");
                }
                log.info(builder.toString());
                // polish
                locale = new Locale("pl", "PL");
                Locale.setDefault(locale);
                bundle = ResourceBundle.getBundle(baseName, locale);
                builder = new StringBuilder(" > locale[" + locale + "], values: ");
                for (String key: bundle.keySet()) {
                    var value = bundle.getString(key);
                    builder.append(value).append(" ");
                }
                log.info(builder.toString());
                // spanish
                locale = new Locale("es", "ES");
                Locale.setDefault(locale);
                bundle = ResourceBundle.getBundle(baseName, locale);
                builder = new StringBuilder(" > locale[" + locale + "], values: ");
                for (String key: bundle.keySet()) {
                    var value = bundle.getString(key);
                    builder.append(value).append(" ");
                }
                log.info(builder.toString());
                // german
                locale = Locale.GERMANY;
                Locale.setDefault(locale);
                bundle = ResourceBundle.getBundle(baseName, locale);
                builder = new StringBuilder(" > locale[" + locale + "], values: ");
                for (String key: bundle.keySet()) {
                    var value = bundle.getString(key);
                    builder.append(value).append(" ");
                }
                log.info(builder.toString());
                // french
                locale = Locale.FRANCE;
                Locale.setDefault(locale);
                bundle = ResourceBundle.getBundle(baseName, locale);
                builder = new StringBuilder(" > locale[" + locale + "], values: ");
                for (String key: bundle.keySet()) {
                    var value = bundle.getString(key);
                    builder.append(value).append(" ");
                }
                log.info(builder.toString());
                // italian
                locale = Locale.ITALY;
                Locale.setDefault(locale);
                bundle = ResourceBundle.getBundle(baseName, locale);
                builder = new StringBuilder(" > locale[" + locale + "], values: ");
                for (String key: bundle.keySet()) {
                    var value = bundle.getString(key);
                    builder.append(value).append(" ");
                }
                log.info(builder.toString());
            } catch (Exception e) {
                log.error("Property resource bundle errors: " + e.getLocalizedMessage());
            } finally {
                Locale.setDefault(current);
            }
        }
    }

    /**
     * Observer functionality.
     */
    private void invokeObserver() {
        log.info("---------------- Observer ----------------");

        log.info(" It is deprecated since java 9.");

        // who watch the changes
        class Watcher implements Observer {
            @Override
            public void update(Observable o, Object arg) {
                var counter = (Integer) arg;
                log.info(" [Watcher] update() has been called, counter = " + counter);
            }
        }

        // who watch the even numbers
        class Evener implements Observer {
            @Override
            public void update(Observable o, Object arg) {
                var value = (Integer) arg;
                if (value % 2 == 0)
                    log.info(" > [Evener] update() has been called, even value = " + value);
            }
        }

        // who watch the odd numbers
        class Odder implements Observer {
            @Override
            public void update(Observable o, Object arg) {
                var value = (Integer) arg;
                if (value % 2 != 0)
                    log.info(" > [Odder] update() has been called, odd value = " + value);
            }
        }

        // who watch the finish
        class Finisher implements Observer {
            @Override
            public void update(Observable o, Object arg) {
                if (((Integer) arg) == 0)
                    log.info(" >> [Finisher] update() has been called, finish. " + '\007');
            }
        }

        // who is being watched
        class Watched extends Observable {
            void counter(int period) {
                for (; period >= 0; period--) {
                    // #1 - changed
                    setChanged();
                    // #2 - notify
                    notifyObservers(period);
                    try {
                        Thread.sleep(100);
                    } catch (Exception e) {
                        log.error("Observer thread errors " + e.getMessage());
                    }
                }
            }
        }

        // who watch
        var observing = new Watcher();
        var evener = new Evener();
        var odder = new Odder();
        var finisher = new Finisher();

        // be watched
        var observed = new Watched();
        observed.addObserver(observing);
        observed.addObserver(evener);
        observed.addObserver(odder);
        observed.addObserver(finisher);
        observed.counter(5);
    }

    /**
     * Optional functionality.
     */
    private void invokeOptional() {
        log.info("---------------- Optional ----------------");

        Optional optional = Optional.empty();
        try {
            log.info(" * Value empty: " + optional.get());
            optional = Optional.ofNullable(null);
            log.info(" * Value of nullable: " + optional.get());
        } catch (Throwable t) {
            log.error("Optional get empty value: " + t.getMessage());
        }
        try {
            optional.orElseThrow(() -> new NullPointerException("npe simulation"));
            optional.orElseThrow(NullPointerException::new);
        } catch (Throwable t) {
            log.error("Optional orElseThrow error: " + t.getMessage());
        }

        String nullable = null;
        var defaultValue = Optional.ofNullable(nullable).orElse("Default value");
        log.info(" * Value default: " + defaultValue);

        // verify value
        Optional<String> value = Optional.of("Optional String Value");
        log.info(" * Value of: " + value.get());
        log.info(" * Value is present: " + value.isPresent());
        log.info(" * Value is empty: " + value.isEmpty());

        // filter value
        var filtered = value.filter(v -> v.startsWith("Opt"));
        log.info(" * Value filter: " + filtered.get());

        // transform value
        var mapped = value.map(String::toUpperCase);
        log.info(" * Value mapped: " + mapped.get());

        // primitives
        OptionalInt optionalInt = OptionalInt.of(100);
        log.info(" * Optional Int: " + optionalInt.getAsInt());
        OptionalLong optionalLong = OptionalLong.of(100L);
        log.info(" * Optional Long: " + optionalLong.getAsLong());
        OptionalDouble optionalDouble = OptionalDouble.of(100D);
        log.info(" * Optional Double: " + optionalDouble.getAsDouble());
    }

    /**
     * Others functionality.
     */
    private void invokeOthers() {
        log.info("---------------- Others ----------------");

        // objects
        {
            log.info(" * Objects:");
            var compare = Objects.compare(
                new String("Aa"),
                new String("aA"),
                String::compareToIgnoreCase
            );
            log.info("  > compare: " + compare);
            var hash = Objects.hash(new String("A"), Integer.valueOf("10"));
            log.info("  > hash: " + hash);
            var isNull = Objects.isNull(null);
            log.info("  > is null: " + isNull);
            var nonNull = Objects.nonNull(10);
            log.info("  > non null: " + nonNull);
            var toString = Objects.toString(LocalDateTime.now());
            log.info("  > to string: " + toString);
            var checkIndex = Objects.checkIndex(5, 10);
            log.info("  > check index: " + checkIndex);
            var fromToIndex = Objects.checkFromToIndex(3, 5, 10);
            log.info("  > check from to: " + fromToIndex);
            var requireNonNull = Objects.requireNonNull(new Object(), "Object should be specified");
            log.info("  > require non null: " + requireNonNull);
        }

        log.info("");

        // statistics
        {
            log.info(" * Statistics:");
            var stats = new IntSummaryStatistics();
            List<Integer> list = Arrays.asList(10, 60, 40, 50, 20, 30, 70);
            for (Integer integer : list)
                stats.accept(integer);
            log.info("  > instance: " + stats.toString());
            // obtain the data
            log.info("  > count of values is " + stats.getCount());
            log.info("  > average of values is " + stats.getAverage());
            log.info("  > sum of values is " + stats.getSum());
            log.info("  > maximum of values is " + stats.getMax());
            log.info("  > minimum of values is " + stats.getMin());
            stats.combine(new IntSummaryStatistics(10, 1, 100, 1000));
            log.info("  > combine: " + stats.toString());
        }

        log.info("");

        // encoding
        {
            log.info(" * Encoding: ");
            // original
            var value = "public static void main(String[] args) throws Exception {...}";
            log.info("  >> original: " + value);
            // encrypt
            Base64.Encoder encoder = Base64.getEncoder();
            var encrypted = new String(encoder.encode(value.getBytes(StandardCharsets.UTF_8)));
            log.info("  > encrypted: " + encrypted);
            // decrypt
            Base64.Decoder decoder = Base64.getDecoder();
            String decrypted = new String(decoder.decode(encrypted));
            log.info("  > decrypted: " + decrypted);
        }

        log.info("");

        // generation
        {
            log.info(" * Generation:");
            // random
            var uuid = UUID.randomUUID();
            log.info("  > UUID: " + uuid.toString());
            try {
                log.info("  > UUID variant: " + uuid.variant());
                log.info("  > UUID version: " + uuid.version());
                log.info("  > UUID node: " + uuid.node());
                log.info("  > UUID timestamp: " + uuid.timestamp());
            } catch (UnsupportedOperationException uoe) {
                log.error("UUID attributes errors: " + uoe.getLocalizedMessage());
            }
            // from string
            try {
                var id = "38400000-8cf0-11bd-b23e-10b96e4ef00d";
                var generation = UUID.fromString(id);
                log.info("  > UUID from string: " + generation.toString());
                generation = UUID.fromString("generation");
            } catch (IllegalArgumentException iae) {
                log.error("UUID from string errors: " + iae.getLocalizedMessage());
            }
        }
    }
}
