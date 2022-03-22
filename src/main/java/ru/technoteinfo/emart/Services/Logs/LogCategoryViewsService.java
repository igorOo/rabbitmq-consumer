package ru.technoteinfo.emart.Services.Logs;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.vladmihalcea.hibernate.type.basic.Inet;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.technoteinfo.emart.Entity.Logs.LogCategoryViews;
import ru.technoteinfo.emart.Pojo.Response.Logs.LogCategoryViewRabbit;
import ru.technoteinfo.emart.Repositories.Logs.LogCategoryViewsRepository;
import ru.technoteinfo.emart.Services.Interfaces.LogQueryInterfase;

@Slf4j
@Service
public class LogCategoryViewsService implements LogQueryInterfase {

    @Autowired
    private LogCategoryViewsRepository logCategoryViewsRepository;

    @Override
    public void consumeQuery(String message){
        Gson gson = new Gson();
        LogCategoryViewRabbit logCategoryViewRabbit;
        try{
            logCategoryViewRabbit = gson.fromJson(message, LogCategoryViewRabbit.class);
            LogCategoryViews logCategoryViews = new LogCategoryViews(
                    logCategoryViewRabbit.getCategory_id(),
                    logCategoryViewRabbit.getUser_id(),
                    logCategoryViewRabbit.getLang(),
                    logCategoryViewRabbit.getReferer(),
                    logCategoryViewRabbit.getScreen_resolution(),
                    logCategoryViewRabbit.getBrowser_name(),
                    logCategoryViewRabbit.getBrowser_version(),
                    logCategoryViewRabbit.getOs_name(),
                    logCategoryViewRabbit.getOs_arch(),
                    new Inet(logCategoryViewRabbit.getIp_address())

            );
            logCategoryViewsRepository.save(logCategoryViews);
        }catch (JsonSyntaxException exception){
            log.error(exception.getMessage());
        }
    }
}
