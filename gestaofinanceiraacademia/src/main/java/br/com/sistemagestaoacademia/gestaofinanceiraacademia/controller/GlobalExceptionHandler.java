package br.com.sistemagestaoacademia.gestaofinanceiraacademia.controller;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
/**
 * @author Pedro Ivo Barreto Gomes Patrício Ysbá Lima Serafim
 * Created on ${DATE}.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    //tratamento de algumas exception deixei no controller mas deveria criar uma package Exception.
    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleDataIntegrityViolationException(DataIntegrityViolationException ex, Model model) {
        // Ação apropriada quando ocorre uma violação de integridade de dados (por exemplo, CPF duplicado)
        model.addAttribute("error", "CPF já cadastrado. Por favor, forneça um CPF único.");
        return "error";  // Substitua "error" pelo nome da sua página de erro
    }

}
