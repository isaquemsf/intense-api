package br.com.intense.api.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserNameDTO {

    @NotBlank(message = "O nome é obrigatório e não pode estar em branco.")
    private String name;
}
