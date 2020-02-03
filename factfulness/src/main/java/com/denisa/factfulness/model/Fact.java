package com.denisa.factfulness.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "FACT")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Fact {
    @Column(name = "\"ID\"")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SeqFact")
    @SequenceGenerator(name = "SeqFact", sequenceName = "SEQ_FACT", allocationSize = 1)
    private Integer factId;

    @Column(name = "\"TEXT\"")
    private String text;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "\"CREATED_BY_USER_ID\"")
    private User createdBy;
}
