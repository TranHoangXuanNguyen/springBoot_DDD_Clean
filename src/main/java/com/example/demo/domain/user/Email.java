//Value object
package com.example.demo.domain.user;
public final class Email {
    private final String value;

    public Email(String value){
        if(value == null || !value.contains("@")) throw new IllegalArgumentException("Invalid email");
        this.value = value;
    }

    public String value(){ return value; }
}