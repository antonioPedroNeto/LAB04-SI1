package br.com.antoniopedro.ws.controller;

import br.com.antoniopedro.ws.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by pedro on 27/01/2017.
 */
@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Integer> {


}
