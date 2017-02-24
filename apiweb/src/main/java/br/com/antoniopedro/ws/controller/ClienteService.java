package br.com.antoniopedro.ws.controller;

import br.com.antoniopedro.ws.model.Cliente;
import org.springframework.stereotype.Service;


import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;


/**
 * Created by pedro on 05/01/2017.
 */
@Service
public class ClienteService {


    private HashMap<Integer, Cliente> clienteMap = new HashMap<>();
    private Integer id = 0;


    //Negocios

    /**
     * Cadastra um cliente no "banco"
     * @param cliente
     * @return
     */
    public Cliente cadastra(Cliente cliente) {

        Iterator iterator = clienteMap.values().iterator();

        while (iterator.hasNext()){

            if(((Cliente)iterator.next()).getNome().equals(cliente.getNome())){
                return null;
            }


        }

        cliente.setId(id++);
        clienteMap.put(cliente.getId(), cliente);

        return cliente;
    }


    public Collection<Cliente> buscarTodos() {

        return clienteMap.values();

    }


    public void excluir(Integer id) {

        clienteMap.remove(id);

    }

    public Cliente buscaPorId(Integer id){

        return clienteMap.get(id);

    }

    public boolean alterar(Integer id, String nome){

        if(nome != null && !nome.isEmpty()){

            clienteMap.get(id).setNome(nome);
            return true;
        }


        return false;

    }

}
