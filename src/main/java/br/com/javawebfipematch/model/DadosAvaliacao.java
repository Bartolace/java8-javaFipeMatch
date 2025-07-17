package br.com.javawebfipematch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosAvaliacao(@JsonAlias("Valor") String valor,
                                @JsonAlias("Modelo") String modelo,
                                @JsonAlias("AnoModelo") Integer anoModelo,
                                @JsonAlias("Combustivel") String combustivel) {
}
