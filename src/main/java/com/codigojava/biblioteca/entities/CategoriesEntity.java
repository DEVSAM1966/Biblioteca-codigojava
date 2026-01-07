package com.codigojava.biblioteca.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "categories")
public class CategoriesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Integer categoryId;

    @Column(name = "name_category", length = 30)
    private String nameCategory;

    @Column(name = "subtopic_category", length = 30)
    private String subtopicCategory;

}
