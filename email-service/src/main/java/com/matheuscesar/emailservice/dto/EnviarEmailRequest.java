package com.matheuscesar.emailservice.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class EnviarEmailRequest {

    private String destinatario;
    private String assunto;
    private String corpo;

}
