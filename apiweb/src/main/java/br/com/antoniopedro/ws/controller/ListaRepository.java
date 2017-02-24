package br.com.antoniopedro.ws.controller;

import br.com.antoniopedro.ws.model.Lista;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by pedro on 27/01/2017.
 */
public interface ListaRepository extends JpaRepository<Lista, Integer>{
}
