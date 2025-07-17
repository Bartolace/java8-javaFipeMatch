package br.com.javawebfipematch.validacoes;

import java.util.List;

public interface ValidacaoBuscaDados {
    <T> void validar(List<T> lista, String mensagemErro);
}
