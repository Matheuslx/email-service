package com.matheuscesar.emailservice.core;

import com.matheuscesar.emailservice.dto.EnviarEmailRequest;
import com.matheuscesar.emailservice.exception.EnviarEmailException;

public interface EnviarEmailUseCase {

    void enviarEmail(EnviarEmailRequest request) throws EnviarEmailException;
}
