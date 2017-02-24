package br.com.antoniopedro.ws.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by pedro on 26/01/2017.
 */
@Controller
public class HomeController {

    @RequestMapping("/")
    public String irParaHome() {

        return "index";

    }

//    @RequestMapping("/editar")
//    public String irParaEditar() {
//
//        return "editar";
//
//    }
//
//    @RequestMapping("/criaTarefa")
//    public String irParaCriaTarefa() {
//
//        return "criaTarefa";
//
//    }

}
