package br.com.antoniopedro.ws.model;


import javax.persistence.*;

/**
 * Created by pedro on 05/01/2017.
 */
public class Cliente {

    private Integer id;
    private String nome;


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
}
