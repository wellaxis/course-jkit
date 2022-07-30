package com.witalis.jkit.usage.core.invoke.section.patterns.content.structural.proxy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

/**
 * Desc: Security Proxy Room class.
 * User: Wellaxis
 * Date: 4/28/2022
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Slf4j
public class SecurityProxyRoom implements Room {
    private Room room;
    private String password;

    @Override
    public void open() {
        if (authenticate()) {
            room.open();
        } else {
            log.info("It's impossible! Password is incorrect..");
        }
    }

    @Override
    public void close() {
        room.close();
    }

    private boolean authenticate() {
        return getPassword().equals("$ecr@t");
    }
}
