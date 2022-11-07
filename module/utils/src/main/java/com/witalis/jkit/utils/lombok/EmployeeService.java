package com.witalis.jkit.utils.lombok;

import lombok.extern.slf4j.Slf4j;

import java.util.Date;

@Slf4j
public class EmployeeService {
    public static void main(String[] args) {
        String id = "5N7B89N0B";
        String firstName = "Ara";
        String lastName = "Bond";
        Date birthday = new Date();
        String phoneNo = "+8(033)294-58-33";
        Employee employee = new Employee(
            id,
            firstName,
            lastName,
            birthday,
            phoneNo
        );
        log.info(employee.toString());

        Employee another = Employee
            .builder()
            .id("JTBHR$B")
            .firstName("Alex")
            .lastname("Ixter")
            .birthday(new Date())
            .phoneNo("+34(555)29-49-95")
            .build();
        log.info(another.toString());
    }
}
