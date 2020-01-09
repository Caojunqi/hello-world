package org.spring;

public class Parent {

    private String name;
    private Child child;

    public Parent(String name, Child child) {
        this.name = name;
        this.child = child;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Child getChild() {
        return child;
    }

    public void setChild(Child child) {
        this.child = child;
    }
}
