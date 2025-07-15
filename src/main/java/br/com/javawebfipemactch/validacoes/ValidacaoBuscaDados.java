package br.com.javawebfipemactch.validacoes;

import java.util.List;

public interface ValidacaoBuscaDados {
    <T> void validar(List<T> lista, String mensagemErro);
}
