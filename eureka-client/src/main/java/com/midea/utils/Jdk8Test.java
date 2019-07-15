package com.midea.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Jdk8Test {
    public static void main(String[] args) {

 /*       List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6, 7);

        long count = integers.parallelStream().filter(integer -> integer == 1).count();

        System.out.println(count);

        integers.parallelStream().forEachOrdered(integer -> System.out.println(integer));
*/


        String json = "";
        if (json != null) {

        }

        List<Person> persons = new ArrayList();
        for (int i = 1; i <= 5; i++) {
            Person person = new Person(i, "name" + i);
            persons.add(person);
        }
        List<Person> personList2 = persons.stream().limit(4).collect(Collectors.toList());
        System.out.println(personList2);
    }
}

class Person{
    int i;

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    String name;

    public Person(int i,String name){
        this.name=name;
        this.i=i;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "i=" + i +
                ", name='" + name + '\'' +
                '}';
    }
}