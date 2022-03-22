package ru.technoteinfo.emart.Services.Logs;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.vladmihalcea.hibernate.type.basic.Inet;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.technoteinfo.emart.Entity.Logs.LogCategoryViews;
import ru.technoteinfo.emart.Entity.Logs.LogProductViews;
import ru.technoteinfo.emart.Pojo.Response.Logs.LogProductViewsRabbit;
import ru.technoteinfo.emart.Repositories.Logs.LogProductViewsRepository;
import ru.technoteinfo.emart.Services.Interfaces.LogQueryInterfase;

@Component
@Slf4j
public class LogProductViewsService implements LogQueryInterfase {

    @Autowired
    private LogProductViewsRepository logProductViewsRepository;

    @Override
    public void consumeQuery(String message) {
        Gson gson = new Gson();
        LogProductViewsRabbit logProductViewsRabbit;
        try{
            logProductViewsRabbit = gson.fromJson(message, LogProductViewsRabbit.class);
            LogProductViews logProductViews = new LogProductViews(
                    logProductViewsRabbit.getProduct_id(),
                    logProductViewsRabbit.getUser_id(),
                    logProductViewsRabbit.getLang(),
                    logProductViewsRabbit.getReferer(),
                    logProductViewsRabbit.getScreen_resolution(),
                    logProductViewsRabbit.getBrowser_name(),
                    logProductViewsRabbit.getBrowser_version(),
                    logProductViewsRabbit.getOs_name(),
                    logProductViewsRabbit.getOs_arch(),
                    new Inet(logProductViewsRabbit.getIp_address())

            );
            logProductViewsRepository.save(logProductViews);
        }catch (JsonSyntaxException exception){
            log.error(exception.getMessage());
        }
    }
}
