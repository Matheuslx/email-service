package com.matheuscesar.emailservice.application;

import com.matheuscesar.emailservice.dto.EnviarEmailRequest;
import com.matheuscesar.emailservice.exception.EnviarEmailException;
import com.matheuscesar.emailservice.infrastructure.SendGrid.EnviarEmailSendGrid;
import com.matheuscesar.emailservice.infrastructure.amazon.EnviarEmailSES;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import static org.mockito.Mockito.*;

import static org.springframework.test.util.ReflectionTestUtils.setField;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class EnviarEmailServiceTest {

    @InjectMocks
    private EnviarEmailService enviarEmailService;

    @Mock
    private EnviarEmailSES enviarEmailSES;

    @Mock
    private EnviarEmailSendGrid enviarEmailSendGrid;

    @BeforeEach
    void setUp() {
        setField(enviarEmailService, "enviarEmailSendGrid", enviarEmailSendGrid);
        setField(enviarEmailService, "enviarEmailSES", enviarEmailSES);
    }


    @Test
    void deveEnviarEmailPeloSendGridComSucesso() {
        EnviarEmailRequest request =  EnviarEmailRequest.builder()
                .assunto("Assunto")
                .corpo("Corpo")
                .destinatario("destinatario")
                .build();

        enviarEmailService.enviarEmail(request);

        verify(enviarEmailSendGrid, times(1))
                .enviarEmail(request.getDestinatario(), request.getAssunto(), request.getCorpo());

        verify(enviarEmailSES, times(0))
                .enviarEmail(anyString(), anyString(), anyString());
    }

    @Test
    void deveEnviarEmailPeloAmazonSeSQuandoEnvioPeloSendGridFalhar() {

        EnviarEmailRequest request =  EnviarEmailRequest.builder()
                .assunto("Assunto")
                .corpo("Corpo")
                .destinatario("destinatario")
                .build();


        doThrow(new EnviarEmailException("Erro ao enviar e-mail pelo SendGrid"))
                .when(enviarEmailSendGrid).enviarEmail(anyString(), anyString(), anyString());

        enviarEmailService.enviarEmail(request);

        verify(enviarEmailSendGrid, times(1))
                .enviarEmail(request.getDestinatario(), request.getAssunto(), request.getCorpo());

        verify(enviarEmailSES, times(1))
                .enviarEmail(request.getDestinatario(), request.getAssunto(), request.getCorpo());
    }
}
