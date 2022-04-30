package com.nttdata.training.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

    private static final long serialVersionUID = -1593580772674505822L;

    private String id;
    private String name;
    private String lastName;
    private Integer age;

    public User updateUser(User newUser) {

        this.setName(newUser.getName());
        this.setAge(newUser.getAge());
        this.setLastName(newUser.getLastName());

        return this;
    }

}
