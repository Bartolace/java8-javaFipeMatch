package br.com.javawebfipemactch.validacoes;

import br.com.javawebfipemactch.exception.semDadosException;

import java.util.List;

public class ValidarDados implements ValidacaoBuscaDados{

    @Override
    public <T> void validar(List<T> lista, String mensagemErro) {
        if(lista.isEmpty()){
            throw new semDadosException(mensagemErro);
        }
        return;
    }
}
