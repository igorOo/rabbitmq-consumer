package ru.technoteinfo.emart.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.technoteinfo.emart.Entity.LogSendSms;

public interface LogSendSmsRepository extends JpaRepository<LogSendSms, Long> {
    public LogSendSms findByPhone(String phone);
}
