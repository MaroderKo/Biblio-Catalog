package com.smart.catalog.Domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class SchoolClass extends Domain{
    // TODO: 30.05.2022
    String name;
    @JsonManagedReference
    @OneToMany(mappedBy = "schoolclass", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<Student> students;

    String classTeacher;

}
