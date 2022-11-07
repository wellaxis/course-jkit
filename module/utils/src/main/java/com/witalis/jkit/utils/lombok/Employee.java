package com.witalis.jkit.utils.lombok;

import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

@Data
@Builder
@Slf4j
public class Employee {
    private String id;
    private String firstName;
    private String lastname;
    private Date birthday;
    private String phoneNo;
}
