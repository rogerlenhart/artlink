package br.com.cwi.crescer.tcc.service.core;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class NowService {

    public LocalDate getDate() {
        return LocalDate.now();
    }

    public LocalDateTime getDateTime() { return LocalDateTime.now(); }
}
