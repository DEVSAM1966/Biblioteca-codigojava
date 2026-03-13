package com.codigojava.biblioteca.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "histories")
public class HistoriesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "history_id")
    private Integer historyId;

    @Column(name = "date_feedback")
    private LocalDate dateFeedback;

    @Column(name = "feedback", length = 255)
    private String feedback;

    @ManyToOne
    @JoinColumn(name = "loan_id")
    private LoansEntity loan;

}
