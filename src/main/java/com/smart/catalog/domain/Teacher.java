package com.smart.catalog.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Data
@Entity
@NoArgsConstructor
public class Teacher extends Domain{
    private String pib;
    private int birthDate;
    private String phone;
    private String address;
    private String profession;

    public Teacher(int id, String pib, int birthDate, String phone, String address, String profession) {
        this.id = id;
        this.pib = pib;
        this.birthDate = birthDate;
        this.phone = phone;
        this.address = address;
        this.profession = profession;
    }
}
