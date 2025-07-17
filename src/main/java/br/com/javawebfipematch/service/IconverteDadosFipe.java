package br.com.javawebfipematch.service;


import java.util.List;

public interface IconverteDadosFipe {
    <T> T converter(String json, Class<T > classe);
    <T> List<T> converterLista(String json, Class<T> classe);
}

