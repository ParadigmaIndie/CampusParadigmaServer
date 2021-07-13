package com.germany.paradigmaindie.ParadigmaIndieServer.services;

import com.germany.paradigmaindie.ParadigmaIndieServer.models.ConfirmationToken;
import com.germany.paradigmaindie.ParadigmaIndieServer.repositories.ConfimrationTokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ConfirmationTokenServices {
    private  final ConfimrationTokenRepository confimrationTokenRepository;

    public void saveConfirmationToken(ConfirmationToken token){
        confimrationTokenRepository.save(token);
    }
    public Optional<ConfirmationToken> getToken(String token){
        return confimrationTokenRepository.findByToken(token);
    }

    public int setConfirmedAt(String token) {
        return confimrationTokenRepository.updateConfirmedAt(
                token, LocalDateTime.now());

    }
}
