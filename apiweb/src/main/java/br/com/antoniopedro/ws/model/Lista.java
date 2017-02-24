package br.com.antoniopedro.ws.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.ArrayList;

/**
 * Created by pedro on 22/01/2017.
 */
@Entity
public class Lista {

    @Id
    @GeneratedValue
    private Integer id;
    private String nome;
    private String descricao;
    private Integer quantidadeTarefas;
    private ArrayList<Tarefa> tarefaArrayList = new ArrayList<>();

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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getQuantidadeTarefas() {
        return quantidadeTarefas;
    }

    public void setQuantidadeTarefas(Integer quantidadeTarefas) {
        this.quantidadeTarefas = quantidadeTarefas;
    }

    public void add(Tarefa tarefa) {

        tarefaArrayList.add(tarefa);

    }

    public void remove(Tarefa tarefa){

        tarefaArrayList.remove(tarefa);

    }

    public ArrayList<Tarefa> getTarefas(){

        return this.tarefaArrayList;

    }


    /**
     * Metodo que pega uma tarefa pelo nome
     * @param nome - nome da tarefa
     * @return - retrona uma tarefa caso encontrada ou null caso nao encontre
     */
    public Tarefa getTarefaNome(String nome){

        for(int i = 0; i < tarefaArrayList.size(); i++){

            if(tarefaArrayList.get(i).getNome().equals(nome)){
                return tarefaArrayList.get(i);
            }

        }

        return null;

    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Lista lista = (Lista) o;

        return id != null ? id.equals(lista.id) : lista.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
