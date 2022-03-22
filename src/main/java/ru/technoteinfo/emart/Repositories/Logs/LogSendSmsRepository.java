package ru.technoteinfo.emart.Repositories.Logs;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.technoteinfo.emart.Entity.Logs.LogSendSms;

public interface LogSendSmsRepository extends JpaRepository<LogSendSms, Long> {
    public LogSendSms findByPhone(String phone);
}
