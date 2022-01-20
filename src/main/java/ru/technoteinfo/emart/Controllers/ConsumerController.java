package ru.technoteinfo.emart.Controllers;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.technoteinfo.emart.Entity.LogSendSms;
import ru.technoteinfo.emart.Pojo.Response.SmsRabbit;
import ru.technoteinfo.emart.Repositories.LogSendSmsRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Component
@Slf4j
public class ConsumerController {

    @Autowired
    private LogSendSmsRepository logSendSmsRepository;

    @RabbitListener(queues = "sms")
    public void getMessage(String message){
        log.info(message);
        Gson gson = new Gson();
        SmsRabbit rabbitMessage;
        try {
            rabbitMessage = gson.fromJson(message, SmsRabbit.class);
        }catch (JsonSyntaxException exception){
            log.error(exception.getMessage());
            return;
        }
        LogSendSms logSendSms = new LogSendSms(rabbitMessage.getPhone(), rabbitMessage.getCode());
        logSendSmsRepository.save(logSendSms);
        try
        {
            Process proc = Runtime.getRuntime().exec("cmd /c echo \"phone: "+rabbitMessage.getPhone()+", code: "+rabbitMessage.getCode()+" \"");
//            Process proc = Runtime.getRuntime().exec("gammu sendsms TEXT "+ rabbitMessage.getPhone()
//                    +" -unicode -text \"Ваш код подтверждения - "+rabbitMessage.getCode()+"\"");
            proc.waitFor();
            BufferedReader output = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String responseSms = output.readLine();
            log.info(responseSms);
            logSendSms.setResponse(responseSms);
            logSendSmsRepository.save(logSendSms);
        }
        catch (IOException | InterruptedException e)
        {
            log.error(e.getMessage());
        }
    }
}
