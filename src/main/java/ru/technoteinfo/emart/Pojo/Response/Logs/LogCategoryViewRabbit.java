package ru.technoteinfo.emart.Pojo.Response.Logs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class LogCategoryViewRabbit {

    private Long category_id;
    private Long user_id;
    private String lang;
    private String referer;
    private String screen_resolution;
    private String browser_name;
    private String browser_version;
    private String os_name;
    private String os_arch;
    private String ip_address;
}
