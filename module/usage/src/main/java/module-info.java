open module com.witalis.jkit.usage.core {
    // requires
    requires java.base;
    requires java.desktop;
    requires java.management;
    requires java.logging;
    requires java.net.http;
    // spring
    requires spring.beans;
    requires spring.core;
    requires spring.context;
    requires spring.boot;
    requires spring.boot.autoconfigure;
    // logging
    requires org.slf4j;
    // apache
    requires org.apache.tomcat.embed.websocket;
    requires org.apache.commons.lang3;
    requires org.apache.commons.codec;
    requires com.google.common;
    // lombok
    requires static lombok;
}
