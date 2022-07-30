package com.witalis.jkit.usage.core.invoke;

import com.witalis.jkit.usage.core.config.UsageProcess;
import com.witalis.jkit.usage.core.invoke.section.abstraction.AbstractionInvoker;
import com.witalis.jkit.usage.core.invoke.section.annotation.AnnotationInvoker;
import com.witalis.jkit.usage.core.invoke.section.applets.AppletInvoker;
import com.witalis.jkit.usage.core.invoke.section.assertion.AssertionInvoker;
import com.witalis.jkit.usage.core.invoke.section.beans.BeanInvoker;
import com.witalis.jkit.usage.core.invoke.section.bits.BitInvoker;
import com.witalis.jkit.usage.core.invoke.section.blocks.BlockInvoker;
import com.witalis.jkit.usage.core.invoke.section.classes.ClassInvoker;
import com.witalis.jkit.usage.core.invoke.section.cloning.CloningInvoker;
import com.witalis.jkit.usage.core.invoke.section.collection.CollectionInvoker;
import com.witalis.jkit.usage.core.invoke.section.constant.ConstantInvoker;
import com.witalis.jkit.usage.core.invoke.section.constructors.ConstructorInvoker;
import com.witalis.jkit.usage.core.invoke.section.core.CoreInvoker;
import com.witalis.jkit.usage.core.invoke.section.dates.DateInvoker;
import com.witalis.jkit.usage.core.invoke.section.encapsulation.EncapsulationInvoker;
import com.witalis.jkit.usage.core.invoke.section.enumeration.EnumerationInvoker;
import com.witalis.jkit.usage.core.invoke.section.events.EventInvoker;
import com.witalis.jkit.usage.core.invoke.section.exception.ExceptionInvoker;
import com.witalis.jkit.usage.core.invoke.section.formats.FormatInvoker;
import com.witalis.jkit.usage.core.invoke.section.garbage.GarbageInvoker;
import com.witalis.jkit.usage.core.invoke.section.generics.GenericInvoker;
import com.witalis.jkit.usage.core.invoke.section.graphics.GraphicInvoker;
import com.witalis.jkit.usage.core.invoke.section.inheritance.InheritanceInvoker;
import com.witalis.jkit.usage.core.invoke.section.interfaces.InterfaceInvoker;
import com.witalis.jkit.usage.core.invoke.section.io.IOInvoker;
import com.witalis.jkit.usage.core.invoke.section.keywords.KeywordInvoker;
import com.witalis.jkit.usage.core.invoke.section.lambda.LambdaInvoker;
import com.witalis.jkit.usage.core.invoke.section.methods.MethodInvoker;
import com.witalis.jkit.usage.core.invoke.section.modifiers.ModifierInvoker;
import com.witalis.jkit.usage.core.invoke.section.modules.ModuleInvoker;
import com.witalis.jkit.usage.core.invoke.section.multithreading.MultithreadingInvoker;
import com.witalis.jkit.usage.core.invoke.section.networks.NetworkInvoker;
import com.witalis.jkit.usage.core.invoke.section.numbers.NumberInvoker;
import com.witalis.jkit.usage.core.invoke.section.oop.OOPInvoker;
import com.witalis.jkit.usage.core.invoke.section.operators.OperatorInvoker;
import com.witalis.jkit.usage.core.invoke.section.overloading.OverloadingInvoker;
import com.witalis.jkit.usage.core.invoke.section.overriding.OverridingInvoker;
import com.witalis.jkit.usage.core.invoke.section.packages.PackageInvoker;
import com.witalis.jkit.usage.core.invoke.section.patterns.PatternInvoker;
import com.witalis.jkit.usage.core.invoke.section.polymorphism.PolymorphismInvoker;
import com.witalis.jkit.usage.core.invoke.section.primitives.PrimitiveInvoker;
import com.witalis.jkit.usage.core.invoke.section.profile.ProfileInvoker;
import com.witalis.jkit.usage.core.invoke.section.properties.PropertyInvoker;
import com.witalis.jkit.usage.core.invoke.section.recursion.RecursionInvoke;
import com.witalis.jkit.usage.core.invoke.section.references.ReferenceInvoker;
import com.witalis.jkit.usage.core.invoke.section.reflection.ReflectionInvoker;
import com.witalis.jkit.usage.core.invoke.section.regexp.RegexpInvoker;
import com.witalis.jkit.usage.core.invoke.section.security.SecurityInvoker;
import com.witalis.jkit.usage.core.invoke.section.servlet.ServletInvoker;
import com.witalis.jkit.usage.core.invoke.section.statements.StatementInvoker;
import com.witalis.jkit.usage.core.invoke.section.streams.StreamInvoker;
import com.witalis.jkit.usage.core.invoke.section.strings.StringInvoker;
import com.witalis.jkit.usage.core.invoke.section.system.SystemInvoker;
import com.witalis.jkit.usage.core.invoke.section.tokens.TokenInvoker;
import com.witalis.jkit.usage.core.invoke.section.types.DataTypeInvoker;
import com.witalis.jkit.usage.core.invoke.section.utils.UtilsInvoker;
import com.witalis.jkit.usage.core.invoke.section.variable.VariableInvoker;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

import static com.witalis.jkit.usage.core.invoke.AppSection.*;

/**
 * Desc: Java Kit invoker
 * User: Wellaxis
 * Date: 2019/11/16
 */
@Slf4j
@Data
@EqualsAndHashCode(callSuper=false)
public class AppInvoker extends Invoker {
    private UsageProcess properties;
    private static Map<AppSection, Class<? extends Invoker>> sections;

    static {
        sections = new EnumMap<>(AppSection.class);
        sections.put(ABSTRACTION, AbstractionInvoker.class);
        sections.put(ANNOTATION, AnnotationInvoker.class);
        sections.put(APPLETS, AppletInvoker.class);
        sections.put(ASSERTION, AssertionInvoker.class);
        sections.put(BEANS, BeanInvoker.class);
        sections.put(BITS, BitInvoker.class);
        sections.put(BLOCKS, BlockInvoker.class);
        sections.put(CLASSES, ClassInvoker.class);
        sections.put(CLONING, CloningInvoker.class);
        sections.put(COLLECTION, CollectionInvoker.class);
        sections.put(CONSTANTS, ConstantInvoker.class);
        sections.put(CONSTRUCTORS, ConstructorInvoker.class);
        sections.put(CORE, CoreInvoker.class);
        sections.put(DATES, DateInvoker.class);
        sections.put(ENCAPSULATION, EncapsulationInvoker.class);
        sections.put(ENUMERATION, EnumerationInvoker.class);
        sections.put(EVENTS, EventInvoker.class);
        sections.put(EXCEPTION, ExceptionInvoker.class);
        sections.put(FORMATS, FormatInvoker.class);
        sections.put(GARBAGE, GarbageInvoker.class);
        sections.put(GENERICS, GenericInvoker.class);
        sections.put(GRAPHICS, GraphicInvoker.class);
        sections.put(INHERITANCE, InheritanceInvoker.class);
        sections.put(INTERFACES, InterfaceInvoker.class);
        sections.put(IO, IOInvoker.class);
        sections.put(KEYWORDS, KeywordInvoker.class);
        sections.put(LAMBDA, LambdaInvoker.class);
        sections.put(METHODS, MethodInvoker.class);
        sections.put(MODIFIERS, ModifierInvoker.class);
        sections.put(MODULES, ModuleInvoker.class);
        sections.put(MULTITHREADING, MultithreadingInvoker.class);
        sections.put(NETWORKS, NetworkInvoker.class);
        sections.put(NUMBERS, NumberInvoker.class);
        sections.put(OOP, OOPInvoker.class);
        sections.put(OPERATORS, OperatorInvoker.class);
        sections.put(OVERLOADING, OverloadingInvoker.class);
        sections.put(OVERRIDING, OverridingInvoker.class);
        sections.put(PACKAGES, PackageInvoker.class);
        sections.put(PATTERNS, PatternInvoker.class);
        sections.put(POLYMORPHISM, PolymorphismInvoker.class);
        sections.put(PRIMITIVES, PrimitiveInvoker.class);
        sections.put(PROFILE, ProfileInvoker.class);
        sections.put(PROPERTIES, PropertyInvoker.class);
        sections.put(RECURSION, RecursionInvoke.class);
        sections.put(REFERENCES, ReferenceInvoker.class);
        sections.put(REFLECTION, ReflectionInvoker.class);
        sections.put(REGEXP, RegexpInvoker.class);
        sections.put(SECURITY, SecurityInvoker.class);
        sections.put(SERVLETS, ServletInvoker.class);
        sections.put(STATEMENTS, StatementInvoker.class);
        sections.put(STREAMS, StreamInvoker.class);
        sections.put(STRINGS, StringInvoker.class);
        sections.put(SYSTEM, SystemInvoker.class);
        sections.put(TOKENS, TokenInvoker.class);
        sections.put(TYPES, DataTypeInvoker.class);
        sections.put(UTILS, UtilsInvoker.class);
        sections.put(VARIABLES, VariableInvoker.class);
    }

    public AppInvoker(UsageProcess properties) {
        this.properties = properties;
    }

    public static void main(String[] args) {
        UsageProcess process = UsageProcess.builder()
            .invoker(
                UsageProcess.Invoker.builder()
                    .activate(true)
                    .sections(List.of("all"))
                    .build()
            )
            .build();

        AppInvoker invoker = new AppInvoker(process);
        invoker.header();
        invoker.invoke();
        invoker.footer();
    }

    @Override
    public void header() {
        log.info("BEGIN.");
    }

    @Override
    public void invoke() {
        if (ObjectUtils.isEmpty(properties)) return;

        Stream.of(properties)
            .map(UsageProcess::getInvoker)
            .filter(Objects::nonNull)
            .filter(UsageProcess.Invoker::isActivate)
            .map(UsageProcess.Invoker::getSections)
            .flatMap(List::stream)
            .map(AppSection::getSection)
            .filter(Objects::nonNull)
            .filter(sections::containsKey)
            .map(sections::get)
            .filter(Objects::nonNull)
            .map(AppInvoker::initiateInvoker)
            .filter(Objects::nonNull)
            .forEach(Invoker::run);
    }

    @Override
    public void footer() {
        log.info("END.");
    }

    @Override
    public void output() {
        log.info("");
    }

    private static Invoker initiateInvoker(Class<? extends Invoker> clazz) {
        if (ObjectUtils.isEmpty(clazz)) return null;

        Invoker invoker = null;
        try {
            invoker = clazz.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            log.error("Unable to initiate section invoker: {}", clazz.getCanonicalName());
        }
        return invoker;
    }
}
