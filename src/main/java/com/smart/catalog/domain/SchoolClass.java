package com.smart.catalog.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@RequiredArgsConstructor
public class SchoolClass extends Domain{
    private String name;
    @JsonManagedReference
    @OneToMany(mappedBy = "schoolclass", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Student> students;

    private String classTeacher;

    public SchoolClass(int id, String name, List<Student> students, String classTeacher) {
        this.id = id;
        this.name = name;
        this.students = students;
        this.classTeacher = classTeacher;
    }
}
