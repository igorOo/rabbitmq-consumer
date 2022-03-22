package ru.technoteinfo.emart.Controllers;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.technoteinfo.emart.Services.Logs.LogCategoryViewsService;

@Component

public class LogCategoryController {

    @Autowired
    private LogCategoryViewsService logCategoryViewsService;

    @RabbitListener(queues = "category-statistic")
    public void logViewCategory(String message){
        this.logCategoryViewsService.consumeQuery(message);
    }
}
