package br.com.javawebfipematch.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosModeloWrapper(List<DadosFipe> modelos) {
}
