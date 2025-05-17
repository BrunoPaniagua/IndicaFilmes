package com.brunopaniagua.IndicaFilmes.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@Service
public class GeminiService {

    private final WebClient webClient;
    private final String geminiApiKey;
    private final String geminiModel = "/gemini-2.0-flash:generateContent";

    public GeminiService(WebClient webClient, @Value("${api.key}") String geminiApiKey) {
        this.webClient = webClient;
        this.geminiApiKey = geminiApiKey;
    }

    public Mono<String> gerarIndicacao(){
        String prompt = "Indique o nome de um filme de acao lancado em 2024.";

        Map<String, Object> requestBody = Map.of(
                "contents", List.of(
                        Map.of(
                                "parts", List.of(
                                        Map.of("text", prompt)
                                )
                        )
                )
        );

        return webClient.post()
                .uri(uriBuilder -> uriBuilder.path(geminiModel).queryParam("key", geminiApiKey).build())
                .header(HttpHeaders.CONTENT_TYPE, "application/json")
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(Map.class)
                .map(response -> {
                    if (response != null && response.containsKey("candidates")) {
                        List<Map<String, Object>> candidates = (List<Map<String, Object>>) response.get("candidates");
                        if (!candidates.isEmpty()) {
                            Map<String, Object> firstCandidate = candidates.get(0);
                            Map<String, Object> content = (Map<String, Object>) firstCandidate.get("content");
                            if (content != null && content.containsKey("parts")) {
                                List<Map<String, Object>> parts = (List<Map<String, Object>>) content.get("parts");
                                if (!parts.isEmpty()) {
                                    Map<String, Object> firstPart = parts.get(0);
                                    if (firstPart != null && firstPart.containsKey("text")) {
                                        return (String) firstPart.get("text");
                                    }
                                }
                            }
                        }
                    }
                    return "Nenhuma indicação de filme foi gerada.";
                })
                .onErrorReturn("Ocorreu um erro ao gerar a indicação.");
    }

}