package com.blogapp56;

import java.util.HashMap;

public class Test {
    public static void main(String[] args) {
        HashMap<Integer, String> studentInfo = new HashMap<>();
        studentInfo.put(100,"pankaj");
        studentInfo.put(101,"ravi");
        studentInfo.put(102,"arun");

        System.out.println(studentInfo);
        System.out.println(studentInfo.get(102));
        System.out.println(studentInfo.keySet());
        System.out.println(studentInfo.values());
    }
}
