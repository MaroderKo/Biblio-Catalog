package com.smart.catalog.domain;

import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity
public class Teacher extends Domain{
    private String pib;
    private int birthDate;
    private String phone;
    private String address;
    private String profession;
}
