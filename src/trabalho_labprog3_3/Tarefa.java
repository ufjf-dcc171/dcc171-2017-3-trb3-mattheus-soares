/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho_labprog3_3;

import java.sql.Date;

/**
 *
 * @author Mattheus
 */
public class Tarefa {
    private Integer id;
    private String descricao;
    private Integer duracao_esperada;
    private Double percentual;
    private Date data_inicio;
    private Date data_final;
    private Integer status;
    private Integer ID_Projeto;

    public Tarefa() {
    }

    public Tarefa(String descricao, Integer duracao_esperada, Double percentual, Date data_inicio, Date data_final, Integer status, Integer ID_Projeto) {
        super();
        this.descricao = descricao;
        this.duracao_esperada = duracao_esperada;
        this.percentual = percentual;
        this.data_inicio = data_inicio;
        this.data_final = data_final;
        this.status = status;
        this.ID_Projeto = ID_Projeto;
    }

    public Tarefa(Integer id, String descricao, Integer duracao_esperada, Double percentual, Date data_inicio, Date data_final, Integer status, Integer ID_Projeto) {
        super();
        this.id = id;
        this.descricao = descricao;
        this.duracao_esperada = duracao_esperada;
        this.percentual = percentual;
        this.data_inicio = data_inicio;
        this.data_final = data_final;
        this.status = status;
        this.ID_Projeto = ID_Projeto;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getDuracao_esperada() {
        return duracao_esperada;
    }

    public void setDuracao_esperada(Integer duracao_esperada) {
        this.duracao_esperada = duracao_esperada;
    }

    public Double getPercentual() {
        return percentual;
    }

    public void setPercentual(Double percentual) {
        this.percentual = percentual;
    }

    public Date getData_inicio() {
        return data_inicio;
    }

    public void setData_inicio(Date data_inicio) {
        this.data_inicio = data_inicio;
    }

    public Date getData_final() {
        return data_final;
    }

    public void setData_final(Date data_final) {
        this.data_final = data_final;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getID_Projeto() {
        return ID_Projeto;
    }

    public void setID_Projeto(Integer ID_Projeto) {
        this.ID_Projeto = ID_Projeto;
    }
    @Override
    public String toString(){
        return this.descricao;
    }

}
