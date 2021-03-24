package com.example23.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
public class Person {
    @Id
    private Long id;

    private String HeadLine;
    private String Author;
    private LocalDate CreationDate;
    private LocalDate UpdateDate;
    private String Text;
    private String Category;
}