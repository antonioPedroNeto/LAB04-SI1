package br.com.antoniopedro.ws.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by pedro on 28/01/2017.
 */
@Entity
public class SubTarefa implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;
    private String nome;
    private String data;
    private boolean isComplete;
    private String descricao;
    private String prioridade; //A, M, B (Alta, Media, BAIXA)
    private String categoria;
    private String nomeTarefa;



    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(String prioridade) {
        this.prioridade = prioridade;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }

    public String getNomeTarefa() {
        return nomeTarefa;
    }

    public void setNomeTarefa(String nomeTarefa) {
        this.nomeTarefa = nomeTarefa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SubTarefa subTarefa = (SubTarefa) o;

        if (id != null ? !id.equals(subTarefa.id) : subTarefa.id != null) return false;
        if (nome != null ? !nome.equals(subTarefa.nome) : subTarefa.nome != null) return false;
        return nomeTarefa != null ? nomeTarefa.equals(subTarefa.nomeTarefa) : subTarefa.nomeTarefa == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (nome != null ? nome.hashCode() : 0);
        result = 31 * result + (nomeTarefa != null ? nomeTarefa.hashCode() : 0);
        return result;
    }
}
