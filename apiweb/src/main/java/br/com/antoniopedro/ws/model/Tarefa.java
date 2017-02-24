package br.com.antoniopedro.ws.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by pedro on 16/01/2017.
 */

@Entity
public class Tarefa implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;
    private String nome;
    private String data;
    private boolean isComplete;
    private String descricao;
    private String prioridade; //A, M, B (Alta, Media, BAIXA)
    private String categoria;
    private String nomeLista;

    private ArrayList<SubTarefa> subTarefas = new ArrayList<>();


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

        for(int i = 0; i < subTarefas.size(); i++){

            subTarefas.get(i).setNomeTarefa(nome);

        }

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

    public String getNomeLista() {
        return nomeLista;
    }

    public void setNomeLista(String nomeLista) {
        this.nomeLista = nomeLista;
    }

    public void addSubTarefa(SubTarefa subTarefa) {

        subTarefas.add(subTarefa);

    }

    public ArrayList<SubTarefa> getSubTarefas() {

        return subTarefas;

    }

    public void removeSubTarefa(SubTarefa subTarefa){
        subTarefas.remove(subTarefa);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tarefa tarefa = (Tarefa) o;

        if (id != null ? !id.equals(tarefa.id) : tarefa.id != null) return false;
        if (nome != null ? !nome.equals(tarefa.nome) : tarefa.nome != null) return false;
        return nomeLista != null ? nomeLista.equals(tarefa.nomeLista) : tarefa.nomeLista == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (nome != null ? nome.hashCode() : 0);
        result = 31 * result + (nomeLista != null ? nomeLista.hashCode() : 0);
        return result;
    }
}
