package com.springboot.api.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
public class User {
    private String id;
    private String name;
    private String password;

    public String toString() {
        return String.format("%s %s %s", this.id, this.name, this.password);
    }
}
