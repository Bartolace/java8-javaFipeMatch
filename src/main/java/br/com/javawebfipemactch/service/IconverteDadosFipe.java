package br.com.javawebfipemactch.service;


import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;

public interface IconverteDadosFipe {
    <T> T converter(String json, Class<T > classe);
    <T> List<T> converterLista(String json, Class<T> classe);
}

