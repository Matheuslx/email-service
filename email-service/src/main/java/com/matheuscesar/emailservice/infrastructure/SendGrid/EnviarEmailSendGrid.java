package com.matheuscesar.emailservice.infrastructure.SendGrid;

import com.matheuscesar.emailservice.adapters.EnviarEmailGateway;
import com.matheuscesar.emailservice.exception.EnviarEmailException;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service("EnviarEmailSendGrid")
public class EnviarEmailSendGrid  implements EnviarEmailGateway  {

    @Override
    public void enviarEmail(String destinatario, String assunto, String corpo) throws EnviarEmailException {
        log.info("Enviando email pelo SendGrid" );
        try{
            Email remetente = new Email("desafioUber@teste.com");
            Email emailDestinatario = new Email(destinatario);
            Content conteudoEmail = new Content("text/plain", corpo);
            Mail sendGridMail = new Mail(remetente, assunto, emailDestinatario, conteudoEmail);

            SendGrid sg = new SendGrid("SENDGRID_API_KEY");
            Request request = new Request();

            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(sendGridMail.build());

            sg.api(request);
        }catch (Exception e){
            log.error("Erro ao enviar email pelo SendGrid", e);
            throw new EnviarEmailException("Erro ao enviar email pelo SendGrid");
        }
    }
}
