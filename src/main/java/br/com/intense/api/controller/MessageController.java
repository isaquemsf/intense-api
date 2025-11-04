package br.com.intense.api.controller;

import br.com.intense.api.dto.PersonalizedMessageDTO;
import br.com.intense.api.dto.UserNameDTO;
import br.com.intense.api.service.MessageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/messages")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @PostMapping("/generate")
    public ResponseEntity<PersonalizedMessageDTO> generatePersonalizedMessage(@Valid @RequestBody UserNameDTO userNameDTO) {
        PersonalizedMessageDTO message = messageService.generateMessage(userNameDTO);
        return ResponseEntity.ok(message);
    }
}
