package ru.technoteinfo.emart.Entity.Logs;

import com.vladmihalcea.hibernate.type.basic.PostgreSQLInetType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import ru.technoteinfo.emart.Helpers.Inet;

import javax.persistence.*;
import java.net.InetAddress;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "log_send_sms")
@NoArgsConstructor
@TypeDef(typeClass = PostgreSQLInetType.class, name = "inet")
public class LogSendSms {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "phone")
    private String phone;

    @Column(name = "code")
    private Integer code;

    @Column(name = "response")
    private String response;

    @Type(type = "inet")
    @Column(name = "ip_address", columnDefinition = "inet")
    private InetAddress ipAddress;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt = new Date();

    public LogSendSms(String phone, Integer code){
        this.setPhone(phone);
        this.setCode(code);
        this.setCreatedAt(new Date());
    }

    public LogSendSms(String phone, Integer code, String response){
        this.setPhone(phone);
        this.setCode(code);
        this.setResponse(response);
        this.setCreatedAt(new Date());
    }

    public LogSendSms(String phone, Integer code, String response, InetAddress ipAddress){
        this.setPhone(phone);
        this.setCode(code);
        this.setResponse(response);
        this.setIpAddress(ipAddress);
        this.setCreatedAt(new Date());
    }

}
