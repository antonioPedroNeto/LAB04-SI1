package br.com.antoniopedro.ws.controller;

import br.com.antoniopedro.ws.model.SubTarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by pedro on 28/01/2017.
 */
@Repository
public interface SubTarefaRepository extends JpaRepository<SubTarefa, Integer> {

}
