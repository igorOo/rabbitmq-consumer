package ru.technoteinfo.emart.Pojo.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class SmsRabbit {

    private String phone;
    private Integer code;

}
