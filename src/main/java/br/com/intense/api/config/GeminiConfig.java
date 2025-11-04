package br.com.intense.api.config;

import com.google.genai.Client;
import com.google.genai.types.GenerateContentConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GeminiConfig {

    @Value("${gemini.api-key}")
    private String apiKey;

    @Bean
    public Client geminiClient() {
        return new Client.Builder()
                .apiKey(apiKey)
                .build();
    }

    @Bean
    public GenerateContentConfig generateContentConfig() {
        return GenerateContentConfig.builder()
                .temperature(0.9f)
                .build();
    }

}