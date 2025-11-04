package br.com.intense.api.service.impl;

import br.com.intense.api.dto.PersonalizedMessageDTO;
import br.com.intense.api.dto.UserNameDTO;
import br.com.intense.api.exception.GeminiApiException;
import br.com.intense.api.service.MessageService;
import com.google.genai.Client;
import com.google.genai.types.Content;
import com.google.genai.types.GenerateContentConfig;
import com.google.genai.types.GenerateContentResponse;
import com.google.genai.types.Part;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class MessageServiceImpl implements MessageService {

    private final Client geminiClient;
    private final GenerateContentConfig generateContentConfig;

    @Value("${gemini.model-name}")
    private String modelName;

    @Value("${gemini.prompt-template}")
    private String promptTemplate;

    @Override
    @Retryable(
            retryFor = {GeminiApiException.class},
            maxAttempts = 3,
            backoff = @Backoff(delay = 2000, multiplier = 2)
    )
    public PersonalizedMessageDTO generateMessage(UserNameDTO userNameDTO) {
        log.info("Tentando gerar mensagem para: {}", userNameDTO.getName());
        String formattedName = capitalize(userNameDTO.getName());
        String finalPrompt = String.format(promptTemplate, formattedName);
        String generatedText;

        try {
            Content content = Content.fromParts(Part.fromText(finalPrompt));

            GenerateContentResponse response = geminiClient.models
                    .generateContent(modelName, content, generateContentConfig);

            generatedText = response.text().trim();
        } catch (Exception e) {
            log.error("Falha na comunicação com a API Gemini: {}", e.getMessage());
            throw new GeminiApiException("Falha ao comunicar com o Gemini após tentativa.", e);
        }

        return new PersonalizedMessageDTO(generatedText);
    }

    public PersonalizedMessageDTO recover(GeminiApiException e, UserNameDTO userNameDTO) {
        log.warn("Todas as tentativas de chamar o Gemini falharam para o usuário {}. Retornando mensagem de fallback.", userNameDTO.getName());
        String fallbackMessage = String.format("%s, sua criatividade é a única tendência que importa hoje. - Intense.", capitalize(userNameDTO.getName()));
        return new PersonalizedMessageDTO(fallbackMessage);
    }

    private String capitalize(String name) {
        if (name == null || name.isBlank()) return "Você";
        return name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
    }
}
