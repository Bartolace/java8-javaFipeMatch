package br.com.javawebfipematch.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosFipe(String codigo,
                        String nome) {
}
