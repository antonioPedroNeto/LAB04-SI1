package br.com.antoniopedro.ws.controller;

import br.com.antoniopedro.ws.model.Lista;
import br.com.antoniopedro.ws.model.SubTarefa;
import br.com.antoniopedro.ws.model.Tarefa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Null;
import java.util.*;

/**
 * Created by pedro on 16/01/2017.
 */
@Service
public class TarefaService {

    @Autowired
    TarefaRepository tarefaRepository;

    @Autowired
    ListaRepository listaRepository;

    @Autowired
    SubTarefaRepository subTarefaRepository;




    public boolean criarLista(Lista lista) {



        if(buscaLista(lista.getNome()) == null) {
            lista.setQuantidadeTarefas(0);
        }else{
            return false;
        }



        listaRepository.save(lista);

        return true;

    }


    public boolean criarAtividade(Tarefa tarefa) {


        if(buscaTarefa(tarefa.getNome(), tarefa.getNomeLista()) != null){
            return false;
        }



        Lista lista = buscaLista(tarefa.getNomeLista());

        lista.add(tarefa);

        listaRepository.save(lista);

        tarefaRepository.save(tarefa);

        return true;

    }

    public boolean criarSubTarefa(SubTarefa subTarefa) {


        if(buscaSubTarefa(subTarefa.getNome(), subTarefa.getNomeTarefa()) != null){
            return false;
        }



        Tarefa tarefa = buscaTarefa(subTarefa.getNomeTarefa());

        tarefa.addSubTarefa(subTarefa);

        tarefaRepository.save(tarefa);

        subTarefaRepository.save(subTarefa);

        return true;

    }



    public boolean excluiAtividade(String nomeTarefa, String nomeLista) {

        if (nomeTarefa == null || nomeTarefa.isEmpty() || nomeLista == null || nomeLista.isEmpty()){
            return false;
        }

        Tarefa tarefa = buscaTarefa(nomeTarefa, nomeLista);

        if(tarefa == null){
            return false;
        }

        tarefaRepository.delete(tarefa);

        return true;
    }

    public boolean editarNomeTarefa(String nomeAntigo, String lista, String novoNome) {

        if(nomeAntigo == null || nomeAntigo.isEmpty() || lista == null || lista.isEmpty() || novoNome == null || novoNome.isEmpty()){
            return false;
        }

        Tarefa tarefa = buscaTarefa(nomeAntigo, lista);

        Lista lista1 = buscaLista(tarefa.getNomeLista());

        lista1.remove(tarefa);

        if(tarefa == null){
            return false;
        }

        tarefa.setNome(novoNome);

        tarefaRepository.save(tarefa);

        lista1.add(tarefa);

        listaRepository.save(lista1);

        ArrayList<SubTarefa> subTarefas = tarefa.getSubTarefas();

        for(int i = 0; i < subTarefas.size(); i++){

            subTarefaRepository.save(subTarefas.get(i));

        }


        return true;

    }


    public boolean editarNomeSubTarefa(String nomeAntigoSubTarefa, String tarefa, String novoNomeSubTarefa) {

        if(nomeAntigoSubTarefa == null || nomeAntigoSubTarefa.isEmpty() || tarefa == null || tarefa.isEmpty() || novoNomeSubTarefa == null || novoNomeSubTarefa.isEmpty()){
            return false;
        }

        SubTarefa subTarefa = buscaSubTarefa(nomeAntigoSubTarefa, tarefa);

        Tarefa tarefaAtual = buscaTarefa(tarefa);

        tarefaAtual.removeSubTarefa(subTarefa);

        if(subTarefa == null){
            return false;
        }

        subTarefa.setNome(novoNomeSubTarefa);


        tarefaAtual.addSubTarefa(subTarefa);

        tarefaRepository.save(tarefaAtual);

        subTarefaRepository.save(subTarefa);

        return true;

    }

    public boolean trocarPrioridadeTarefa(String nome, String lista, String prioridade) {

        if(nome == null|| nome.isEmpty() || lista == null|| lista.isEmpty() || prioridade == null || prioridade.isEmpty()){
            return false;
        }

        Tarefa tarefa = buscaTarefa(nome, lista);

        if(tarefa == null){
            return false;
        }

        tarefa.setPrioridade(prioridade);

        tarefaRepository.save(tarefa);

        return true;
    }

    public boolean trocarPrioridadeSubTarefa(String nomeSubTarefa, String nomeTarefa, String prioridade) {

        if(nomeSubTarefa == null|| nomeSubTarefa.isEmpty() || nomeTarefa == null|| nomeTarefa.isEmpty() || prioridade == null || prioridade.isEmpty()){
            return false;
        }

        Tarefa tarefa = buscaTarefa(nomeTarefa);

        SubTarefa subTarefa = buscaSubTarefa(nomeSubTarefa, nomeTarefa);

        if(subTarefa == null){
            return false;
        }

        tarefa.removeSubTarefa(subTarefa);

        subTarefa.setPrioridade(prioridade);

        tarefa.addSubTarefa(subTarefa);

        tarefaRepository.save(tarefa);

        subTarefaRepository.save(subTarefa);

        return true;
    }



    public boolean editarDescricaoTarefa(String nome, String lista, String descricao) {

        if(nome == null|| nome.isEmpty() || lista == null|| lista.isEmpty() || descricao == null || descricao.isEmpty()){
            return false;
        }

        Tarefa tarefa = buscaTarefa(nome, lista);

        if(tarefa == null){
            return false;
        }

        tarefa.setDescricao(descricao);

        tarefaRepository.save(tarefa);

        return true;

    }


    public boolean editarDescricaoSubTarefa(String nomeSubTarefa, String nomeTarefa, String descricao) {

        if (nomeSubTarefa == null || nomeSubTarefa.isEmpty() || nomeTarefa == null || nomeTarefa.isEmpty() || descricao == null || descricao.isEmpty()) {
            return false;
        }

        Tarefa tarefa = buscaTarefa(nomeTarefa);

        SubTarefa subTarefa = buscaSubTarefa(nomeSubTarefa, nomeTarefa);

        if (tarefa == null) {
            return false;
        }

        tarefa.removeSubTarefa(subTarefa);

        subTarefa.setDescricao(descricao);

        tarefa.addSubTarefa(subTarefa);

        tarefaRepository.save(tarefa);

        subTarefaRepository.save(subTarefa);

        return true;

    }

    public boolean editarDataTarefa(String nomeTarefa, String lista, String data) {

        if(nomeTarefa == null|| nomeTarefa.isEmpty() || lista == null|| lista.isEmpty() || data == null || data.isEmpty()){
            return false;
        }

        Tarefa tarefa = buscaTarefa(nomeTarefa);

        if(tarefa == null){
            return false;
        }

        tarefa.setData(data);

        tarefaRepository.save(tarefa);

        return true;

    }



    public boolean editarDataSubTarefa(String nomeSubTarefa, String nomeTarefa, String data) {

        if(nomeSubTarefa == null|| nomeSubTarefa.isEmpty() || nomeTarefa == null|| nomeTarefa.isEmpty() || data == null || data.isEmpty()){
            return false;
        }

        Tarefa tarefa = buscaTarefa(nomeTarefa);

        SubTarefa subTarefa = buscaSubTarefa(nomeSubTarefa, nomeTarefa);

        if(tarefa == null){
            return false;
        }

        tarefa.removeSubTarefa(subTarefa);

        subTarefa.setData(data);

        tarefa.addSubTarefa(subTarefa);

        tarefaRepository.save(tarefa);

        subTarefaRepository.save(subTarefa);

        return true;

    }


    public boolean editarCategoriaSubTarefa(String nomeSubTarefa, String nomeTarefa, String categoria) {

        if(nomeSubTarefa == null|| nomeSubTarefa.isEmpty() || nomeTarefa == null|| nomeTarefa.isEmpty() || categoria == null || categoria.isEmpty()){
            return false;
        }

        Tarefa tarefa = buscaTarefa(nomeTarefa);

        SubTarefa subTarefa = buscaSubTarefa(nomeSubTarefa, nomeTarefa);

        if(tarefa == null){
            return false;
        }

        tarefa.removeSubTarefa(subTarefa);

        subTarefa.setCategoria(categoria);

        tarefa.addSubTarefa(subTarefa);

        tarefaRepository.save(tarefa);

        subTarefaRepository.save(subTarefa);

        return true;

    }

    public boolean editarCategoriaTarefa(String nomeTarefa, String lista, String categoria) {

        if(nomeTarefa == null|| nomeTarefa.isEmpty() || lista == null|| lista.isEmpty() || categoria == null || categoria.isEmpty()){
            return false;
        }

        Tarefa tarefa = buscaTarefa(nomeTarefa);

        if(tarefa == null){
            return false;
        }


        tarefa.setCategoria(categoria);

        tarefaRepository.save(tarefa);

        return true;

    }

    public boolean marcarTarefa(String nome, String lista){
        if(nome == null|| nome.isEmpty() || lista == null|| lista.isEmpty()){
            return false;
        }

        Tarefa tarefa = buscaTarefa(nome, lista);

        if(tarefa == null){
            return false;
        }

        if(tarefa.isComplete()){
            tarefa.setComplete(false);
        }else{
            tarefa.setComplete(true);
        }

        tarefaRepository.save(tarefa);

        return true;
    }

    public boolean trocarTarefaLista(String nome, String lista, String novaLista){
        if(nome == null|| nome.isEmpty() || lista == null|| lista.isEmpty() || novaLista == null || novaLista.isEmpty()){
            return false;
        }

        Tarefa tarefa = buscaTarefa(nome, lista);

        if(tarefa == null){
            return false;
        }

        tarefa.setNomeLista(novaLista);

        tarefaRepository.save(tarefa);

        return true;

    }

    /**
     * Metodo para buscar uma tarefa
     * @param nome - nome de uma tarefa
     * @param lista - lista que contem a tarefa
     * @return - null caso nao exista ou a tarefa encontrada
     */
    public Tarefa buscaTarefa(String nome, String lista) {


        ArrayList<Tarefa> tarefaArrayList = (ArrayList<Tarefa>) tarefaRepository.findAll();

        for(int i = 0; i < tarefaArrayList.size(); i++){

            Tarefa tarefa = tarefaArrayList.get(i);

            if(tarefa.getNome().equals(nome) && tarefa.getNomeLista().equals(lista)){
                return tarefa;
            }

        }

        return null;
    }

    /**
     * Metodo para buscar uma tarefa
     * @param nome - nome de uma tarefa
     * @return - null caso nao exista ou a tarefa encontrada
     */
    public SubTarefa buscaSubTarefa(String nome, String nomeTarefa) {


        ArrayList<SubTarefa> subTarefaArrayList = (ArrayList<SubTarefa>) subTarefaRepository.findAll();

        for(int i = 0; i < subTarefaArrayList.size(); i++){

            SubTarefa subTarefa = subTarefaArrayList.get(i);

            if(subTarefa.getNome().equals(nome) && subTarefa.getNomeTarefa().equals(nomeTarefa)){
                return subTarefa;
            }

        }

        return null;
    }

    /**
     * Metodo para buscar uma tarefa
     * @param nome - nome de uma tarefa
     * @return - null caso nao exista ou a tarefa encontrada
     */
    public SubTarefa buscaSubTarefa(String nome) {


        ArrayList<SubTarefa> subTarefaArrayList = (ArrayList<SubTarefa>) subTarefaRepository.findAll();

        for(int i = 0; i < subTarefaArrayList.size(); i++){

            SubTarefa subTarefa = subTarefaArrayList.get(i);

            if(subTarefa.getNome().equals(nome)){
                return subTarefa;
            }

        }

        return null;
    }

    /**
     * Metodo para buscar uma tarefa
     * @param nome - nome de uma tarefa
     * @return - null caso nao exista ou a tarefa encontrada
     */
    public Tarefa buscaTarefa(String nome) {


        ArrayList<Tarefa> tarefaArrayList = (ArrayList<Tarefa>) tarefaRepository.findAll();

        for(int i = 0; i < tarefaArrayList.size(); i++){

            Tarefa tarefa = tarefaArrayList.get(i);

            if(tarefa.getNome().equals(nome)){
                return tarefa;
            }

        }

        return null;
    }


    /**
     * Metodo para buscar uma tarefa
     * @return - null caso nao exista ou a tarefa encontrada
     */
    public ArrayList<Tarefa> buscaTarefasNaoFeitas() {


        ArrayList<Tarefa> tarefaArrayList = (ArrayList<Tarefa>) tarefaRepository.findAll();

        ArrayList<Tarefa> tarefasNaoFeitas = new ArrayList<>();

        for(int i = 0; i < tarefaArrayList.size(); i++){

            Tarefa tarefa = tarefaArrayList.get(i);

            if(!tarefa.isComplete()){
                tarefasNaoFeitas.add(tarefa);
            }

        }

        return tarefasNaoFeitas;
    }


    public Lista buscaLista(String nome){

        if(nome == null){
            return null;
        }


        ArrayList<Lista> listas = (ArrayList<Lista>) listaRepository.findAll();

        for(int i = 0; i < listas.size(); i++){

            Lista lista = listas.get(i);

            if(lista.getNome().equals(nome)){
                return lista;
            }

        }

        return null;
    }


    /**
     * Metodo que remove todas as atividades de uma determinada lista
     * @param lista - nome da lista
     */
    public void removeTodasLista(String lista){

        Lista listaAtual = buscaLista(lista);

        ArrayList<Tarefa> tarefaArrayList = listaAtual.getTarefas();

        for(int i = 0; i < tarefaArrayList.size(); i++){

            removeTarefa(tarefaArrayList.get(i).getNome(), tarefaArrayList.get(i).getNomeLista());
        }

        listaRepository.delete(listaAtual);



    }

    /**
     * Metodo que remove todas as atividades de uma determinada lista
     */
    public void removeListas(){

        ArrayList<Lista> listaArrayList = (ArrayList<Lista>) listaRepository.findAll();


        for(int i = 0; i < listaArrayList.size(); i++){
            removeTodasLista(listaArrayList.get(i).getNome());
        }

    }

    /**
     * Metodo que remove todas as atividades de uma determinada lista
     * @param lista - nome da lista
     */
    public void removeTarefa(String tarefa, String lista){

        Lista listaAtual = buscaLista(lista);

        Tarefa tarefaAtual = buscaTarefa(tarefa, lista);

        listaAtual.remove(tarefaAtual);

        ArrayList<SubTarefa> subTarefaArrayList = tarefaAtual.getSubTarefas();

        for(int i = 0; i < subTarefaArrayList.size(); i++){
            subTarefaRepository.delete(subTarefaArrayList.get(i));
        }

        listaRepository.save(listaAtual);
        tarefaRepository.delete(tarefaAtual);


    }



    /**
     * Metodo que remove todas as atividades de uma determinada lista
     */
    public void removeSubTarefa(String subTarefa, String nomeTarefa){

        Tarefa tarefaAtual = buscaTarefa(nomeTarefa);

        SubTarefa subTarefaAtual = buscaSubTarefa(subTarefa, nomeTarefa);

        System.out.println(subTarefaAtual.getNome() + subTarefaAtual.getNomeTarefa());

        System.out.println(tarefaAtual.getNome() + tarefaAtual.getSubTarefas().get(0).getNomeTarefa());

        tarefaAtual.removeSubTarefa(subTarefaAtual);

        subTarefaRepository.delete(subTarefaAtual);

        tarefaRepository.save(tarefaAtual);



    }

    /**
     * Metodo que remove todas as subtarefas de uma tarefa e a propria tarefa
     */
    public void removeTodasSubTarefas(String tarefa){

        Tarefa tarefaAtual = buscaTarefa(tarefa);

        List<SubTarefa> subTarefaArrayList = tarefaAtual.getSubTarefas();

        for(int i = 0; i < subTarefaArrayList.size(); i++){

            subTarefaRepository.delete(subTarefaArrayList.get(i));

        }

        tarefaRepository.delete(tarefaAtual);



    }


}
