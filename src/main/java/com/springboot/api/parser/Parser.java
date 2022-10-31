package com.springboot.api.parser;

public interface Parser<T> {
    T parse(String str);
}
