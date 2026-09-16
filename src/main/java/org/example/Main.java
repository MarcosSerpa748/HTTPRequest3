package org.example;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        JsonPlaceholder objeto = new JsonPlaceholder(
                "Meu primeiro POST",
                "Meu nome é Marcos e esse é o meu primeiro POST em um servidor externo.",
                5);

        Gson gson = new Gson();

        String objetoConvertidoEmJson = gson.toJson(objeto);

        HttpClient cliente = HttpClient.newHttpClient();

        HttpRequest requisicao = HttpRequest
                .newBuilder()
                .uri(URI.create("https://jsonplaceholder.typicode.com/posts"))
                .header("Content-Type","application/json")
                .POST(HttpRequest.BodyPublishers.ofString(objetoConvertidoEmJson))
                .build();

        HttpResponse<String> resposta = cliente.send(requisicao,HttpResponse.BodyHandlers.ofString());

        System.out.println("Código de estatus da nossa requisição POST:"+resposta.statusCode());
        System.out.println("Meu json criado guardado no servidor:"+resposta.body());
    }
}