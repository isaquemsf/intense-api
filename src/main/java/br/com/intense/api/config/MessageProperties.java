package br.com.intense.api.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "intense.messages")
@Getter
@Setter
public class MessageProperties {
    private List<String> templates;
}
