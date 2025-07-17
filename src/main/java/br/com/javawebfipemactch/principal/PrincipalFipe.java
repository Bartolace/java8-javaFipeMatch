package br.com.javawebfipemactch.principal;

import br.com.javawebfipemactch.model.*;
import br.com.javawebfipemactch.service.ConsumoApiFipe;
import br.com.javawebfipemactch.service.ConverteDadosFipe;
import br.com.javawebfipemactch.validacoes.ValidarDados;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class PrincipalFipe {
    private ValidarDados validarDados = new ValidarDados();
    private Scanner leitura = new Scanner(System.in);
    private ConsumoApiFipe consumo = new ConsumoApiFipe();
    private ConverteDadosFipe conversor = new ConverteDadosFipe();
    private static final String URL_BASE = "https://parallelum.com.br/fipe/api/v1/";


    public void exibeMenu(){
        System.out.println("""
                Bem-vindo ao sistema de consulta de veículos da Tabela Fipe!
                Tipos disponíveis:
                - Carros
                - Motos
                - Caminhões
                
                Digite o tipo de veículo:
        """);

        String tipoVeiculo = selecionaTipoVeiculo();
        List<DadosFipe> marcas = buscaMarcas(tipoVeiculo);
        validarDados.validar(marcas, "Nenhuma marca encontrada para o tipo de veículo informado.");

        System.out.println("===================== Marcas =====================");
        marcas.stream()
                .sorted(Comparator.comparing(m -> m.nome().toLowerCase()))
                .forEach(System.out::println);

        System.out.println("""
                Escolha um código de marca disponível acima: 
        """);
        String codigoMarca = "/"+ leitura.nextLine() + "/modelos";

        List<DadosFipe> modelos = buscaModelos(tipoVeiculo, codigoMarca);
        validarDados.validar(modelos, "Nenhum modelo encontrado para o código de marca informada.");


        System.out.println("===================== Modelos =====================");
        modelos.stream()
                .sorted(Comparator.comparing(m -> m.nome().toLowerCase()))
                .forEach(System.out::println);

        System.out.println("""
                Digite o nome do modelo que deseja consultar: 
        """);
        String modeloEscolhido = leitura.nextLine().toLowerCase().replaceAll(" ", "");

        List<DadosFipe> modelosFiltrados = modelos.stream()
                .filter(m -> m.nome().toLowerCase().replaceAll(" ","").contains(modeloEscolhido))
                .sorted(Comparator.comparing(DadosFipe::nome))
                .toList();


        System.out.println("==================== Modelos Filtrados ====================");
        validarDados.validar(modelosFiltrados, "Nenhum modelo encontrado com o nome informado.");
        modelosFiltrados.forEach(System.out::println);

        System.out.println("""
            Digite o código do modelo que deseja consultar: 
        """);
        String codigoModelo = "/" + leitura.nextLine() + "/anos";

        List<DadosFipe> dadosAnoModelos = buscaAnosModelos(tipoVeiculo, codigoMarca, codigoModelo);
        validarDados.validar(dadosAnoModelos, "Nenhum ano modelo encontrado com o código informado.");


        System.out.println("==================== Avaliações ====================");
        buscaAvaliacoes(dadosAnoModelos, tipoVeiculo, codigoMarca, codigoModelo)
                .stream()
                .sorted(Comparator.comparing(DadosAvaliacao::anoModelo))
                .forEach(a -> System.out.println(
                    a.modelo() + "  ano: " + a.anoModelo() + "  valor: " + a.valor() + "  combustível: " + a.combustivel(
                )));
    }

    private String selecionaTipoVeiculo(){
        String carro = "carros";
        String moto = "motos";
        String caminhao = "caminhoes";
        String tipoVeiculo = leitura.nextLine().toLowerCase();

        if (carro.contains(tipoVeiculo)){
            return carro + "/marcas";
        }else if (moto.contains(tipoVeiculo)) {
            return moto + "/marcas";
        }else {
            return caminhao + "/marcas";
        }
    }

    private List<DadosFipe> buscaMarcas(String tipoVeiculo) {
        String json = consumo.obterDadosFipe(URL_BASE + tipoVeiculo);
        if (json.contains("error")) return List.of();

        return conversor.converterLista(json, DadosFipe.class);
    }

    private List<DadosFipe> buscaModelos(String tipoVeiculo, String codigoMarca){
        var json = consumo.obterDadosFipe(URL_BASE + tipoVeiculo + codigoMarca);
        if(json.contains("error")) {
            return List.of();
        }

        DadosModeloWrapper modeloWrapper = conversor.converter(json, DadosModeloWrapper.class);
        List<DadosFipe> modelos = modeloWrapper.modelos();
        return modelos;
    }

    private List<DadosFipe> buscaAnosModelos(String tipoVeiculo, String codigoMarca, String codigoModelo){
        var json = consumo.obterDadosFipe(URL_BASE + tipoVeiculo + codigoMarca + codigoModelo);
        if(json.contains("error")) return List.of();

        List<DadosFipe> dadosAnoModelos = conversor.converterLista(json, DadosFipe.class);
        return dadosAnoModelos;
    }

    private List<DadosAvaliacao> buscaAvaliacoes(List<DadosFipe> dadosAnoModelos, String tipoVeiculo, String codigoMarca, String codigoModelo) {
        List<DadosAvaliacao> avaliacoes = new ArrayList<>();
        for (DadosFipe dadoAnoModelo: dadosAnoModelos){
            var json = consumo.obterDadosFipe(URL_BASE + tipoVeiculo + codigoMarca + codigoModelo + "/" + dadoAnoModelo.codigo());

            DadosAvaliacao avaliacao = conversor.converter(json, DadosAvaliacao.class);
            avaliacoes.add(avaliacao);
        }
        return avaliacoes;
    }
}

















