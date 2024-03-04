package com.matheuscesar.emailservice.infrastructure.amazon;

import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.model.*;
import com.matheuscesar.emailservice.adapters.EnviarEmailGateway;
import com.matheuscesar.emailservice.exception.EnviarEmailException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Slf4j
@Service("EnviarEmailSES")
public class EnviarEmailSES implements EnviarEmailGateway {

    @Autowired
    private AmazonSimpleEmailService amazonSimpleEmailService;

    @Override
    public void enviarEmail(String destinatario, String assunto, String corpo) throws EnviarEmailException {
        log.info("Enviando email pelo SES da amazon");
        try{
            SendEmailRequest request = new SendEmailRequest()
                    .withSource("desafioUber@teste.com")
                    .withDestination(new Destination().withToAddresses(destinatario))
                    .withMessage(new Message()
                            .withSubject(new Content(destinatario))
                            .withBody(new Body().withText(new Content().withCharset("UTF-8").withData(corpo)))
                    );
            amazonSimpleEmailService.sendEmail(request);
        }catch (Exception e){
            log.error("Erro ao enviar pelo SES", e);
            throw new EnviarEmailException("Erro ao enviar email pelo SES");
        }
    }
}
