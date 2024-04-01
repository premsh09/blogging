package com.blogapp56;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
    Employee e1 = new Employee(1,"ravi","kumar");
    Employee e2 = new Employee(2,"amit", "singh");
    Employee e3 = new Employee(3,"arun", "verma");

    LinkedList<Employee> emp = new LinkedList<>();
    emp.add(e1);
    emp.add(e2);
    emp.add(e3);
        System.out.println(emp);

        for( Employee empDetails:emp){
            System.out.println(empDetails.getId());
            System.out.println(empDetails.getFirstName());
            System.out.println(empDetails.getLastName());
        }
    }
}
