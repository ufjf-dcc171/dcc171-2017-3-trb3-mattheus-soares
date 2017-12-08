/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho_labprog3_3;

/**
 *
 * @author Mattheus
 */
public class Projeto {

    private Integer id;
    private String nome;
    private String tipo;

    public Projeto() {
    }

    public Projeto(String nome, String tipo) {
        super();
        this.nome = nome;
        this.tipo = tipo;
    }

    public Projeto(Integer id, String nome, String tipo) {
        super();
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return this.nome;
    }

}
