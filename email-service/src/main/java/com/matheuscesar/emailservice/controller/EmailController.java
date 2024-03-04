package com.matheuscesar.emailservice.controller;

import com.matheuscesar.emailservice.application.EnviarEmailService;
import com.matheuscesar.emailservice.dto.ApiResponse;
import com.matheuscesar.emailservice.dto.EnviarEmailRequest;
import com.matheuscesar.emailservice.exception.EnviarEmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/email")
public class EmailController {

    @Autowired
    private EnviarEmailService emailService;

    @PostMapping("/enviar")
    public ResponseEntity<ApiResponse<Void>> enviarEmail(@RequestBody EnviarEmailRequest request) throws EnviarEmailException {
        emailService.enviarEmail(request);
        ApiResponse<Void> retornoApi = ApiResponse.<Void>builder()
                .message("Email enviado com sucesso")
                .build();
        return ResponseEntity.ok().body(retornoApi);

    }
}
