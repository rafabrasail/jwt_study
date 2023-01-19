package com.demo.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="funcionalidade")
public class FuncionalidadeModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @ManyToOne
    @JoinColumn(name="reports_id")
    private ChaveFuncionalidadeModel reports;

    @ManyToOne
    @JoinColumn(name = "techPubs_id")
    private ChaveFuncionalidadeModel techPubs;

    @ManyToOne
    @JoinColumn(name = "alerts_id")
    private ChaveFuncionalidadeModel alerts;

    @ManyToOne
    @JoinColumn(name = "history_id")
    private ChaveFuncionalidadeModel history;
}
