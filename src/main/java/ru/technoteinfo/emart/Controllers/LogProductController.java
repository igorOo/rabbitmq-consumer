package ru.technoteinfo.emart.Controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.technoteinfo.emart.Services.Logs.LogProductViewsService;

@Component
@Slf4j
public class LogProductController {

    @Autowired
    private LogProductViewsService logProductViewsService;

    @RabbitListener(queues = "product-statistic")
    public void getMessage(String message){
        logProductViewsService.consumeQuery(message);
    }
}
