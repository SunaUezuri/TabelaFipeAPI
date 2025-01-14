package br.com.suna.TabelaFipe.main;

import br.com.suna.TabelaFipe.model.DadosMarca;
import br.com.suna.TabelaFipe.model.DadosModelo;
import br.com.suna.TabelaFipe.model.DadosVeiculo;
import br.com.suna.TabelaFipe.service.ConsumoApi;
import br.com.suna.TabelaFipe.service.ConverteDados;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    private Scanner leitura = new Scanner(System.in);
    private ConverteDados conversor = new ConverteDados();

    private final String URL_BASE = "https://parallelum.com.br/fipe/api/v1";

    private ConsumoApi consumoApi = new ConsumoApi();


    public void exibeMenu() {

        var menu = "Qual tipo de veículo você deseja consultar?: " +
                "\n1. Carro" +
                "\n2. Moto" +
                "\n3. Caminhão\n";

        System.out.println(menu);

        var opcao = leitura.nextLine();

        String endereco = switch (opcao) {
            case "1" -> URL_BASE + "/carros/marcas";
            case "2" -> URL_BASE + "/motos/marcas";
            case "3" -> URL_BASE + "/caminhoes/marcas";
            default -> "Opção inválida.";
        };

        var json = consumoApi.obterDados(endereco);
        System.out.println(json);

        var marcas = conversor.obterLista(json, DadosMarca.class);
        marcas.stream()
                .sorted(Comparator.comparing(DadosMarca::id))
                .forEach(System.out::println);

        System.out.println("Insira o ID da marca desejada: ");

        var codigo = leitura.nextLine();

        endereco = endereco + "/" + codigo + "/modelos";
        json = consumoApi.obterDados(endereco);

        var modeloLista = conversor.obterDados(json, DadosModelo.class);

        System.out.println("Modelos da marca: \n");
        modeloLista.modelos().stream()
                .sorted(Comparator.comparing(DadosMarca::id))
                .forEach(System.out::println);

        System.out.println("Digite um trecho do nome do veículo a ser buscado: ");
        var nomeVeiculo = leitura.nextLine();

        List<DadosMarca> modelosFiltrados = modeloLista.modelos().stream()
                .filter(m -> m.nome().toLowerCase().contains(nomeVeiculo))
                .toList();

        System.out.println("\nModelos filtrados");
        modelosFiltrados.forEach(System.out::println);

        System.out.println("Insira o código do veículo desejado");
        var codigoModelo = leitura.nextLine();

        endereco = endereco + "/" + codigoModelo + "/anos";
        json = consumoApi.obterDados(endereco);
        List<DadosMarca> anos = conversor.obterLista(json, DadosMarca.class);
        List<DadosVeiculo> veiculos = new ArrayList<>();

        for (int i = 0; i < anos.size(); i++){
            var enderecoAnos = endereco + "/" + anos.get(i).id();
            json = consumoApi.obterDados(enderecoAnos);
            DadosVeiculo veiculo = conversor.obterDados(json, DadosVeiculo.class);
            veiculos.add(veiculo);
        }

        System.out.println("Dados do veículo solicitado por ano: \n");
        veiculos.forEach(System.out::println);
    }
}
