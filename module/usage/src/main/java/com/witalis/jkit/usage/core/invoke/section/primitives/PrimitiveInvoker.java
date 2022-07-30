package com.witalis.jkit.usage.core.invoke.section.primitives;

import com.witalis.jkit.usage.core.invoke.Invoker;
import com.witalis.jkit.usage.core.invoke.section.types.context.DataHolder;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Flow;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Desc: Primitive data types
 * User: Wellaxis
 * Date: 2019/11/16
 */
@Slf4j
@Data
@EqualsAndHashCode(callSuper=false)
public class PrimitiveInvoker extends Invoker {

    public PrimitiveInvoker() {
        setTitle("Primitives chapter.");
    }

    @Override
    public void invoke() {
        // basis
        log.info("## Basis");
        invokeBasis();
        // tab
        log.info("");
        // defaults
        log.info("## Default values");
        invokeDefault();
        // tab
        log.info("");
        // initialization
        log.info("## Initialization");
        invokeInitialization();
        // tab
        log.info("");
        // specification
        log.info("## Specification");
        invokeSpecification();
        // tab
        log.info("");
        // conversion
        log.info("## Conversion");
        invokeConversion();
        // tab
        log.info("");
        // wrappers
        log.info("## Wrappers");
        invokeWrapper();
    }

    /**
     * Primitive definitions.
     */
    private void invokeBasis() {
        // 1. Primitive data types in Java programming is used to store a single value. It is also known as fundamental data types.
        // 2. Non-primitive data types known as advanced data types store several values or a group of values.
        // 3. The basic and most common data types are integer, floating-point number, character, string, and boolean.
        // 4. Data types are used in java because java is a strongly typed language. Java compiler checks type compatibility. Illegal operations cannot be compiled.

        log.info("Primitive data types: intrinsic");

        // primitives
        char c = 'a';
        byte b = (byte) 0x11;
        short s = (short) 123;
        int i = 1234;
        long l = 12_345L;
        float f = 12.34F;
        double d = 123_456.789D;
        boolean a = true;

        log.info(
            "Primitives: {} | {} | {} | {} | {} | {} | {} | {}",
            c, b, s, i, l, f, d, a
        );
    }

    /**
     * Default values of primitives.
     */
    private void invokeDefault() {
        // default initialization
        DataHolder dataHolder = new DataHolder();
        log.info("DataHolder [default]: {}", dataHolder);
    }

    /**
     * Primitives initialization.
     */
    private void invokeInitialization() {
        // assigned initialization
        var random = ThreadLocalRandom.current();
        DataHolder initHolder = DataHolder.builder()
            .ofChar$((char) random.nextInt(Short.MAX_VALUE))
            .ofByte$((byte) random.nextInt(Byte.MAX_VALUE))
            .ofShort$((short) random.nextInt(Short.MAX_VALUE))
            .ofInt$(random.nextInt(Integer.MAX_VALUE))
            .ofLong$(random.nextLong(Long.MAX_VALUE))
            .ofFloat$(random.nextFloat(Float.MAX_VALUE))
            .ofDouble$(random.nextDouble(Double.MAX_EXPONENT))
            .ofBoolean$(random.nextBoolean())
            .build();
        log.info("DataHolder [define]: {}", initHolder);
    }

    /**
     * Primitives specification.
     */
    @SuppressWarnings("checked")
    public void invokeSpecification() {
        // integer base
        int value = 123_456;
        log.info(Integer.toHexString(value));
        log.info(Integer.toOctalString(value));
        log.info(Integer.toBinaryString(value));

        // underscores will be ignored
        int x = 123_456_789;
        double y = 124_324_234.345_56;

        // hexadecimal char
        char z = '\u0061';

        // dynamic initialization
        double a = 2.4, b = 4.5, c = 3.6, d;

        // var - local variable type inference
        var num = 100;
        var str = "Hi";
    }

    /**
     * Primitives conversions.
     */
    @SuppressWarnings("checked")
    private void invokeConversion() {
        // type promotion rules
        byte _byte = 2;
        // widening conversion
        int _int = _byte;
        // casting conversion
        _byte = (byte) _int;

        int doubleInt = (int) 97d;
        log.info("Double -> Integer: {}", doubleInt);

        // number to string conversion
        int intString = 12_345;
        log.info("Integer -> String: {}", "" + intString);

        // float to string conversion
        float expFloat = 1.39E-43f;
        log.info("Float -> String: {}", "" + expFloat);
    }

    /**
     * Primitives wrappers.
     */
    @SuppressWarnings("checked")
    private void invokeWrapper() {
        // primitives
        char char$ = '1';
        byte byte$ = (byte) 0xfa;
        short short$ = (short) 125;
        int int$ = 12_345;
        long long$ = 123_456_789L;
        float float$ = 234.56F;
        double double$ = 123_456.789D;
        boolean boolean$ = true;

        // wrappers
        Character characterWrapper = Character.valueOf(char$);
        Byte byteWrapper = Byte.valueOf(byte$);
        Short shortWrapper = Short.valueOf(short$);
        Integer integerWrapper = Integer.valueOf(int$);
        Long longWrapper = Long.valueOf(long$);
        Float floatWrapper = Float.valueOf(float$);
        Double doubleWrapper = Double.valueOf(double$);
        Boolean booleanWrapper = Boolean.valueOf(boolean$);

        // validation
        log.info("Character: {} -> {}", char$, characterWrapper);
        log.info("Byte: {} -> {}", byte$, byteWrapper);
        log.info("Short: {} -> {}", short$, shortWrapper);
        log.info("Integer: {} -> {}", int$, integerWrapper);
        log.info("Long: {} -> {}", long$, longWrapper);
        log.info("Float: {} -> {}", float$, floatWrapper);
        log.info("Double: {} -> {}", double$, doubleWrapper);
        log.info("Boolean: {} -> {}", boolean$, booleanWrapper);

        // data type boxing
        {
            // code casting
            Integer intToInteger = Integer.valueOf(100);
            int IntegerToInt = intToInteger.intValue();

            // auto boxing/unboxing
            Integer autoInteger = 100;
            int autoInt = autoInteger;
        }
    }
}
