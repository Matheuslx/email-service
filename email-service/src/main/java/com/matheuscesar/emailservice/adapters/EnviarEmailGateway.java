package com.matheuscesar.emailservice.adapters;

import com.matheuscesar.emailservice.exception.EnviarEmailException;

public interface EnviarEmailGateway {

    void enviarEmail(String destinatario, String assunto, String corpo) throws EnviarEmailException;
}
