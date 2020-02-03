package com.denisa.factfulness.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "USER")
@Getter
@Setter
@ToString
public class User {
    @Column(name = "\"ID\"")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SeqUser")
    @SequenceGenerator(name = "SeqUser", sequenceName = "SEQ_USER", allocationSize = 1)
    private Integer userId;

    @Column(name = "\"USERNAME\"")
    private String username;

    @Column(name = "\"PASSWORD\"")
    private String password;

    @Column(name = "\"NAME\"")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "\"ROLE\"")
    private Role role;

    @OneToMany(mappedBy = "createdBy", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Fact> createdFacts;
}
