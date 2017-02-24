package br.com.antoniopedro.ws.controller;

import br.com.antoniopedro.ws.model.Cliente;
import br.com.antoniopedro.ws.model.Lista;
import br.com.antoniopedro.ws.model.SubTarefa;
import br.com.antoniopedro.ws.model.Tarefa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;


/**
 * Created by pedro on 05/01/2017.
 */
@RestController
public class TarefasController {

    @Autowired
    TarefaService tarefaService;


    /**
     * End-Point para criar uma lista de tarefas
     * @param lista - lista de tarefas
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/criaLista", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Lista> criaLista1(@RequestBody Lista lista){

        System.out.println("criou a lista");
        tarefaService.criarLista(lista);
        return new ResponseEntity<>(lista , HttpStatus.CREATED);

    }
    //TODO metodo para adicionar uma tarefa a uma lista ja criada X

    /**
     * End-Point para criar uma tarefa
     * @param tarefa
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/criaTarefa", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Tarefa> adicionarTarefa(@RequestBody Tarefa tarefa){

        if(tarefaService.buscaLista(tarefa.getNomeLista()) == null){

            return new ResponseEntity<Tarefa>(tarefa, HttpStatus.INTERNAL_SERVER_ERROR);

        }

        if(tarefaService.criarAtividade(tarefa)){

//            System.out.println("cricou"+tarefaService.tarefasHashMap.size());

            return new ResponseEntity<Tarefa>(tarefa, HttpStatus.CREATED);

        }else{

            System.out.println("Não Criou");
            return new ResponseEntity<Tarefa>(tarefa, HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }


    /**
     * End-Point para criar uma tarefa
     * @param subTarefa
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/criaSubTarefa", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SubTarefa> adicionarSubTarefa(@RequestBody SubTarefa subTarefa){

        if(tarefaService.buscaTarefa(subTarefa.getNomeTarefa()) == null){

            return new ResponseEntity<SubTarefa>(subTarefa, HttpStatus.INTERNAL_SERVER_ERROR);

        }

        if(tarefaService.criarSubTarefa(subTarefa)){

//            System.out.println("cricou"+tarefaService.tarefasHashMap.size());

            return new ResponseEntity<SubTarefa>(subTarefa, HttpStatus.CREATED);

        }else{

            System.out.println("Não Criou");
            return new ResponseEntity<SubTarefa>(subTarefa, HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }


    /**
     * End-Point que busca listas de tarefas
     */
    @RequestMapping(method = RequestMethod.GET, value = "/listarTarefas", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Tarefa>> buscaTodasTarefas(){
//
//        if(nome == null || nome.isEmpty()){
//            return new ResponseEntity<Collection<Cliente>>(HttpStatus.BAD_REQUEST);
//        }


        System.out.println("passou aki");
        Collection<Tarefa> clienteCollection = tarefaService.tarefaRepository.findAll();

        return new ResponseEntity<Collection<Tarefa>>(clienteCollection, HttpStatus.OK);

    }

    /**
     * End-Point que busca listas de tarefas
     */
    @RequestMapping(method = RequestMethod.GET, value = "/buscaTodasSubTarefas", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<SubTarefa>> buscaTodasSubTarefas(){



        System.out.println("passou aki");
        Collection<SubTarefa> clienteCollection = tarefaService.subTarefaRepository.findAll();

        return new ResponseEntity<Collection<SubTarefa>>(clienteCollection, HttpStatus.OK);

    }

    /**
     * End-Point que busca listas de tarefas
     */
    @RequestMapping(method = RequestMethod.GET, value = "/buscarSubTarefa/{subTarefa}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SubTarefa> buscarSubTarefa(@PathVariable String subTarefa){

        System.out.println("passou aki");
        SubTarefa subTarefaAtual = tarefaService.buscaSubTarefa(subTarefa);

        return new ResponseEntity<SubTarefa>(subTarefaAtual, HttpStatus.OK);

    }

    /**
     * End-Point que busca listas de tarefas
     */
    @RequestMapping(method = RequestMethod.GET, value = "/listas", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Lista>> buscaTodasListas(){
//
//        if(nome == null || nome.isEmpty()){
//            return new ResponseEntity<Collection<Cliente>>(HttpStatus.BAD_REQUEST);
//        }


        System.out.println("passou aki");
        Collection<Lista> clienteCollection = tarefaService.listaRepository.findAll();

        return new ResponseEntity<Collection<Lista>>(clienteCollection, HttpStatus.OK);

    }

    /**
     * End-Point que busca listas de tarefas
     */
    @RequestMapping(method = RequestMethod.GET, value = "/tarefasNaoFeitas", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Tarefa>> buscaTarefasNaoFeitas(){
//
//        if(nome == null || nome.isEmpty()){
//            return new ResponseEntity<Collection<Cliente>>(HttpStatus.BAD_REQUEST);
//        }


        System.out.println("passou aki");
        Collection<Tarefa> clienteCollection = tarefaService.buscaTarefasNaoFeitas();

        return new ResponseEntity<Collection<Tarefa>>(clienteCollection, HttpStatus.OK);

    }


    /**
     * End-Point que busca listas de tarefas
     */
    @RequestMapping(method = RequestMethod.GET, value = "/listas/{nomeLista}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Tarefa>> buscaTodasListas(@PathVariable String nomeLista){
//
//        if(nome == null || nome.isEmpty()){
//            return new ResponseEntity<Collection<Cliente>>(HttpStatus.BAD_REQUEST);
//        }


        System.out.println("passou aki");

        Lista lista = tarefaService.buscaLista(nomeLista);

        if(lista == null){
            return new ResponseEntity<Collection<Tarefa>>(HttpStatus.NOT_FOUND);
        }

        Collection<Tarefa> clienteCollection = lista.getTarefas();

        return new ResponseEntity<Collection<Tarefa>>(clienteCollection, HttpStatus.FOUND);

    }


    /**
     * end-point para editar o nome de uma terefa
     * @param nome - nome da tarefa
     * @param lista - lista de tarefas
     * @param novoNome - novo nome da tarefa
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/editarNomeTarefa/{nome}/{lista}/{novoNome}")
    public ResponseEntity<Tarefa> editarNomeTarefa(@PathVariable String nome, @PathVariable String lista, @PathVariable String novoNome) {

        if(tarefaService.editarNomeTarefa(nome, lista, novoNome)){

            return new ResponseEntity<Tarefa>(HttpStatus.OK);

        }else {

            return new ResponseEntity<Tarefa>(HttpStatus.NOT_MODIFIED);

        }

    }

    /**
     * end-point para editar o nome de uma subTarefa
     * @param nomeSubTarefa - nome da subTarefa
     * @param tarefa - lista de subTarefa
     * @param novoNomeSubTarefa - novo nome da subTarefa
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/editarNomeSubTarefa/{nomeSubTarefa}/{tarefa}/{novoNomeSubTarefa}")
    public ResponseEntity<SubTarefa> editarNomeSubTarefa(@PathVariable String nomeSubTarefa, @PathVariable String tarefa, @PathVariable String novoNomeSubTarefa) {

        if(tarefaService.editarNomeSubTarefa(nomeSubTarefa, tarefa, novoNomeSubTarefa)){

            return new ResponseEntity<SubTarefa>(HttpStatus.OK);

        }else {

            return new ResponseEntity<SubTarefa>(HttpStatus.NOT_MODIFIED);

        }

    }


    /**
     * end-point para remover todas as tarefas de uma lista
     * @param lista - lista de tarefas
     * @return - resultado dessa remoção
     */
    @RequestMapping(method = RequestMethod.POST, value = "/removerTodasLista/{lista}")
    public ResponseEntity<Tarefa> removerTodasLista(@PathVariable String lista) {


        tarefaService.removeTodasLista(lista);

        return new ResponseEntity<Tarefa>(HttpStatus.OK);

    }

    /**
     * end-point para remover todas as tarefas de uma lista
     * @return - resultado dessa remoção
     */
    @RequestMapping(method = RequestMethod.POST, value = "/removerListas")
    public ResponseEntity<Tarefa> removerListas() {


        tarefaService.removeListas();

        return new ResponseEntity<Tarefa>(HttpStatus.OK);

    }


    /**
     * end-point para remover todas as tarefas de uma lista
     * @param lista - lista de tarefas
     * @return - resultado dessa remoção
     */
    @RequestMapping(method = RequestMethod.POST, value = "/removerTarefa/{tarefa}/{lista}")
    public ResponseEntity<Tarefa> removerTarefa(@PathVariable String tarefa, @PathVariable String lista) {

        Tarefa tarefaAtual = tarefaService.buscaTarefa(tarefa, lista);

        if(tarefaAtual == null){
            return new ResponseEntity<Tarefa>(HttpStatus.NOT_FOUND);
        }

        tarefaService.removeTarefa(tarefa, lista);

        return new ResponseEntity<Tarefa>(tarefaAtual, HttpStatus.OK);

    }


    /**
     * end-point para remover todas as tarefas de uma lista
     * @return - resultado dessa remoção
     */
    @RequestMapping(method = RequestMethod.POST, value = "/removeSubTarefa/{subTarefa}/{nomeTarefa}")
    public ResponseEntity<SubTarefa> removerSubTarefa(@PathVariable String subTarefa, @PathVariable String nomeTarefa) {

        SubTarefa subTarefaAtual = tarefaService.buscaSubTarefa(subTarefa, nomeTarefa);

        if(subTarefaAtual == null){
            return new ResponseEntity<SubTarefa>(HttpStatus.NOT_FOUND);
        }

        tarefaService.removeSubTarefa(subTarefa, nomeTarefa);

        return new ResponseEntity<SubTarefa>(subTarefaAtual, HttpStatus.OK);

    }


    /**
     * End-Point para trocar a prioridade de uma tarefa
     * @param nome - nome da tarefa
     * @param lista - lista que a tarefa está
     * @param prioridade - nova prioridade
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/trocarPrioridadeTarefa/{nome}/{lista}/{prioridade}")
    public ResponseEntity<Tarefa> trocarPrioridadeTarefa(@PathVariable String nome, @PathVariable String lista,
                                                         @PathVariable String prioridade){

        if(tarefaService.trocarPrioridadeTarefa(nome, lista, prioridade)){
            return new ResponseEntity<Tarefa>(HttpStatus.OK);
        }else{
            return new ResponseEntity<Tarefa>(HttpStatus.NOT_MODIFIED);
        }



    }


    /**
     * End-Point para trocar a prioridade de uma SubTarefa
     * @param nomeSubTarefa - nome da SubTarefa
     * @param nomeTarefa - lista que a SubTarefa está
     * @param prioridade - nova prioridade
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/trocarPrioridadeSubTarefa/{nomeSubTarefa}/{nomeTarefa}/{prioridade}")
    public ResponseEntity<SubTarefa> trocarPrioridadeSubTarefa(@PathVariable String nomeSubTarefa, @PathVariable String nomeTarefa,
                                                         @PathVariable String prioridade){

        if(tarefaService.trocarPrioridadeSubTarefa(nomeSubTarefa, nomeTarefa, prioridade)){
            return new ResponseEntity<SubTarefa>(HttpStatus.OK);
        }else{
            return new ResponseEntity<SubTarefa>(HttpStatus.NOT_MODIFIED);
        }



    }

    /**
     * End-Point para editar a descricao de uma tarefa
     * @param nome - nome da tarefa
     * @param lista - lista da tarefa
     * @param descricao - nova descricao da tarefa
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/editarDescricaoTarefa/{nome}/{lista}/{descricao}")
    public ResponseEntity<Tarefa> editarDescricaoTarefa(@PathVariable String nome, @PathVariable String lista,
                                                         @PathVariable String descricao){

        if(tarefaService.editarDescricaoTarefa(nome, lista, descricao)){
            return new ResponseEntity<Tarefa>(HttpStatus.OK);
        }else{
            return new ResponseEntity<Tarefa>(HttpStatus.NOT_MODIFIED);
        }



    }

    /**
     * End-Point para editar a descricao de uma SubTarefa
     * @param nomeSubTarefa - nome da SubTarefa
     * @param nomeTarefa - lista da SubTarefa
     * @param descricao - nova descricao da SubTarefa
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/editarDescricaoSubTarefa/{nomeSubTarefa}/{nomeTarefa}/{descricao}")
    public ResponseEntity<SubTarefa> editarDescricaoSubTarefa(@PathVariable String nomeSubTarefa, @PathVariable String nomeTarefa,
                                                        @PathVariable String descricao){

        if(tarefaService.editarDescricaoSubTarefa(nomeSubTarefa, nomeTarefa, descricao)){
            return new ResponseEntity<SubTarefa>(HttpStatus.OK);
        }else{
            return new ResponseEntity<SubTarefa>(HttpStatus.NOT_MODIFIED);
        }



    }


    /**
     * End-Point para editar a descricao de uma SubTarefa
     * @param nomeSubTarefa - nome da SubTarefa
     * @param nomeTarefa - lista da SubTarefa
     * @param data - nova descricao da SubTarefa
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/editarDataSubTarefa/{nomeSubTarefa}/{nomeTarefa}/{data}")
    public ResponseEntity<SubTarefa> editarDataSubTarefa(@PathVariable String nomeSubTarefa, @PathVariable String nomeTarefa,
                                                              @PathVariable String data){

        if(tarefaService.editarDataSubTarefa(nomeSubTarefa, nomeTarefa, data)){
            return new ResponseEntity<SubTarefa>(HttpStatus.OK);
        }else{
            return new ResponseEntity<SubTarefa>(HttpStatus.NOT_MODIFIED);
        }



    }


    /**
     * End-Point para editar a descricao de uma SubTarefa
     * @param nomeTarefa - nome da SubTarefa
     * @param lista - lista da SubTarefa
     * @param data - nova descricao da SubTarefa
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/editarDataTarefa/{nomeTarefa}/{lista}/{data}")
    public ResponseEntity<Tarefa> editarDataTarefa(@PathVariable String nomeTarefa, @PathVariable String lista,
                                                         @PathVariable String data){

        if(tarefaService.editarDataTarefa(nomeTarefa, lista, data)){
            return new ResponseEntity<Tarefa>(HttpStatus.OK);
        }else{
            return new ResponseEntity<Tarefa>(HttpStatus.NOT_MODIFIED);
        }



    }

    /**
     * End-Point para editar a descricao de uma SubTarefa
     * @param nomeSubTarefa - nome da SubTarefa
     * @param nomeTarefa - lista da SubTarefa
     * @param categoria - nova descricao da SubTarefa
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/editarCategoriaSubTarefa/{nomeSubTarefa}/{nomeTarefa}/{categoria}")
    public ResponseEntity<SubTarefa> editarCategoriaSubTarefa(@PathVariable String nomeSubTarefa, @PathVariable String nomeTarefa,
                                                         @PathVariable String categoria){

        if(tarefaService.editarCategoriaSubTarefa(nomeSubTarefa, nomeTarefa, categoria)){
            return new ResponseEntity<SubTarefa>(HttpStatus.OK);
        }else{
            return new ResponseEntity<SubTarefa>(HttpStatus.NOT_MODIFIED);
        }



    }

    /**
     * End-Point para editar a descricao de uma SubTarefa
     * @param nomeTarefa - nome da SubTarefa
     * @param nomeTarefa - lista da SubTarefa
     * @param categoria - nova descricao da SubTarefa
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/editarCategoriaTarefa/{nomeTarefa}/{lista}/{categoria}")
    public ResponseEntity<Tarefa> editarCategoriaTarefa(@PathVariable String nomeTarefa, @PathVariable String lista,
                                                              @PathVariable String categoria){

        if(tarefaService.editarCategoriaTarefa(nomeTarefa, lista, categoria)){
            return new ResponseEntity<Tarefa>(HttpStatus.OK);
        }else{
            return new ResponseEntity<Tarefa>(HttpStatus.NOT_MODIFIED);
        }



    }


    /**
     * End-Point para marcar uma tarefa como feita ou nao feita
     * @param nome - nome da tarefa
     * @param lista - lista da tarefa
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/marcarTarefa/{nome}/{lista}")
    public ResponseEntity<Tarefa> marcarTarefa(@PathVariable String nome, @PathVariable String lista){

        if(tarefaService.marcarTarefa(nome, lista)){
            return new ResponseEntity<Tarefa>(HttpStatus.OK);
        }else{
            return new ResponseEntity<Tarefa>(HttpStatus.NOT_MODIFIED);
        }



    }





    //TODO Metodo para remover uma tarefa de uma lista ja criada X

    //TODO Metodo para criar uma lista de tarefas

    //TODO Metodo para remover uma lista ja criada

    //TODO Metodo para remover uma atividade de uma lista ja criada X

    //TODO Metodo para remover todas as atividades de uma lista ja criada X X

    //TODO Metodo para editar nome de atividades de uma lista ja criada X X

    //TODO Metodo para trocar prioridade de uma atividade X X

    //TODO Metodo para editar a descricao de uma atividade X X

    //TODO Metodo para marcar/desmarcar como concluido uma atividade X X

    //TODO Metodo para mover uma atividade de uma lista para outra X


//
//    ClienteService clienteService = new ClienteService();
//
//    /**
//     * End-Point que cadastra um cliente
//     * @param cliente
//     */
//    @RequestMapping(method = RequestMethod.POST, value = "/clientes", consumes = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Cliente> cadastraClientes(@RequestBody Cliente cliente){
//
//        clienteService.cadastra(cliente);
//
//        return new ResponseEntity<Cliente>(cliente, HttpStatus.CREATED);//Retrona para a pagina um objeto json
//
//    }
//
//
//
//
//
//    /**
//     * End-Point que busca um cliente
//     */
//    @RequestMapping(method = RequestMethod.GET, value = "/clientes", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Collection<Cliente>> buscaTodosClientes(){
////
////        if(nome == null || nome.isEmpty()){
////            return new ResponseEntity<Collection<Cliente>>(HttpStatus.BAD_REQUEST);
////        }
//
//        Collection<Cliente> clienteCollection = clienteService.buscarTodos();
//
//        return new ResponseEntity<Collection<Cliente>>(clienteCollection, HttpStatus.FOUND);
//
//    }
//
//
//
//    /**
//     * End-Point que cadastra um cliente
//     */
//    @RequestMapping(method = RequestMethod.DELETE, value = "/clientes/{id}")
//    public ResponseEntity<Cliente> excluirClientes(@PathVariable Integer id){
//
//
//        if(clienteService.buscaPorId(id) == null){
//
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);//Retrona para a pagina um objeto json
//
//        }
//
//        clienteService.excluir(id);
//
//        return new ResponseEntity<>(HttpStatus.OK);//Retrona para a pagina um objeto json
//
//    }
//
//
//    /**
//     * End-Point que cadastra um cliente
//     * @param cliente
//     */
//    @RequestMapping(method = RequestMethod.PUT, value = "/clientes/{nome}")
//    public ResponseEntity<Cliente> alterarClientes(@RequestBody Cliente cliente,@PathVariable String nome) {
//
//        if (clienteService.alterar(cliente.getId(), nome)) {
//            return new ResponseEntity<>(HttpStatus.OK);//Retrona para a pagina um objeto json
//        }
//
//        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);//Retrona para a pagina um objeto json
//
//    }



}
