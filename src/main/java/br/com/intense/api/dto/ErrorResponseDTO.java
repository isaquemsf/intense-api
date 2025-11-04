package br.com.intense.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;
import java.util.List;

@Getter
@AllArgsConstructor
public class ErrorResponseDTO {
    private Instant timestamp;
    private int status;
    private List<String> errors;
    private String path;
}
