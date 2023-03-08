package com.smart.catalog.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@RequiredArgsConstructor
public class Student extends Domain{
    private String pib;
    private int birthDate;
    private String phone;
    private String address;

    @ToString.Exclude
    @JsonBackReference
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "schoolclass_id", unique = true)
    private SchoolClass schoolclass;

    public Student(int id, String pib, int birthDate, String phone, String address, SchoolClass schoolclass) {
        this.id = id;
        this.pib = pib;
        this.birthDate = birthDate;
        this.phone = phone;
        this.address = address;
        this.schoolclass = schoolclass;
    }
}
