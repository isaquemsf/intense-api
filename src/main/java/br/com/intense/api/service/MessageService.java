package br.com.intense.api.service;

import br.com.intense.api.dto.PersonalizedMessageDTO;
import br.com.intense.api.dto.UserNameDTO;

public interface MessageService {
    PersonalizedMessageDTO generateMessage(UserNameDTO userNameDTO);
}
