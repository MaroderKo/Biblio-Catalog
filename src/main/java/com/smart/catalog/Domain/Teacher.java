package com.smart.catalog.Domain;

import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity
public class Teacher extends Domain{
    String pib;
    int birthDate;
    String phone;
    String address;
    String profession;
}
