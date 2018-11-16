package com.javaee.artastic.Artastic.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
	private int id;
    private String userName;
    private int userAge;
    private String userAddress;
}
