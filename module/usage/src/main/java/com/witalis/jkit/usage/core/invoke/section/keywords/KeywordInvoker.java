package com.witalis.jkit.usage.core.invoke.section.keywords;

import com.witalis.jkit.usage.core.invoke.Invoker;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * Desc: keywords declaration
 * User: Wellaxis
 * Date: 2019/11/18
 */
@Slf4j
@Data
@EqualsAndHashCode(callSuper=false)
public class KeywordInvoker extends Invoker {

    public KeywordInvoker() {
        setTitle("Keywords chapter.");
    }

    @Override
    public void invoke() {
        // basis
        log.info("## Basis");
        invokeBasis();
        // tab
        log.info("");
        // var
        log.info("## var");
        invokeVar();
    }

    /**
     * Basic postulates of keywords.
     */
    private void invokeBasis() {
        // In Java there are four types of references differentiated on the way by which they are garbage collected.
        // 1) Strong References
        // 2) Weak References
        // 3) Soft References
        // 4) Phantom References

        log.info("There are four types of references: strong, weak, soft, phantom.");
    }

    private void invokeVar() {
        // the first appearance - in JDK 10
        // the latest features - since JDK 12

        // Type inference is used in var keyword in which it detects automatically
        // the datatype of a variable based on the surrounding context.

        log.info("1. var can declare any datatype with the var keyword.");
        log.info("2. var can be used in a local variable declaration.");

        // type inference - context-sensitive (static strong typing)
        {
            var num = 10.0;
            var str = "Text";
            var arr = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 0};

            log.info("* var (context-sensitive identifier)");
            log.info(" - number = {}", num);
            log.info(" - string = {}", str);
            log.info(" - array = {}", Arrays.toString(arr));

        }

        // var is keyword in type context, so var can be literal
        {
            var var = "var";
            log.info(" - var = {}", var);
        }

        log.info("3. var cannot be used in an instance and global variable declaration.");
        log.info("4. var cannot be used as a generic type.");
        log.info("5. var cannot be used with the generic type.");
        log.info("6. var cannot be used without explicit initialization.");
        log.info("7. var cannot be used with lambda expression.");
        log.info("8. var cannot be used for method parameters and return type.");
    }
}
