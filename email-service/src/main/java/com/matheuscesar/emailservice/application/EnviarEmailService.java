package com.matheuscesar.emailservice.application;

import com.matheuscesar.emailservice.adapters.EnviarEmailGateway;
import com.matheuscesar.emailservice.dto.EnviarEmailRequest;
import com.matheuscesar.emailservice.core.EnviarEmailUseCase;
import com.matheuscesar.emailservice.exception.EnviarEmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class EnviarEmailService implements EnviarEmailUseCase {

    @Autowired
    @Qualifier("EnviarEmailSendGrid")
    private EnviarEmailGateway enviarEmailSendGrid;

    @Autowired
    @Qualifier("EnviarEmailSES")
    private EnviarEmailGateway enviarEmailSES;

    @Override
    public void enviarEmail(EnviarEmailRequest request) throws EnviarEmailException {
        try {
            enviarEmailSendGrid.enviarEmail(request.getDestinatario(), request.getAssunto(), request.getCorpo());
        } catch (EnviarEmailException e) {
            enviarEmailSES.enviarEmail(request.getDestinatario(), request.getAssunto(), request.getCorpo());
        }
    }
}
