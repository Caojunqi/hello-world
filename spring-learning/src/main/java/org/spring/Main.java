package org.spring;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    private static final String DEFAULT_APPLICATION_CONTEXT = "applicationContext.xml";

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(DEFAULT_APPLICATION_CONTEXT);
        Parent parent = context.getBean(Parent.class);
        if (parent == null) {
            System.out.println("Parent is null!!");
        } else {
            System.out.println("Parent name == " + parent.getName());
        }
    }
}
