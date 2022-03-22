package ru.technoteinfo.emart.Entity.Logs;

import com.vladmihalcea.hibernate.type.basic.Inet;
import com.vladmihalcea.hibernate.type.basic.PostgreSQLInetType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.TypeDef;
import org.springframework.lang.Nullable;
import ru.technoteinfo.emart.Entity.Categories;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "log_product_views")
@NoArgsConstructor
@TypeDef(typeClass = PostgreSQLInetType.class, name = "inet", defaultForType = Inet.class)
public class LogProductViews {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "user_id")
    private Long userId;
    private String lang;
    private String referer;

    @Column(name = "screen_resolution")
    private String screenResolution;

    @Column(name = "browser_name")
    private String browserName;

    @Column(name = "browser_version")
    private String browserVersion;

    @Column(name = "os_name")
    private String osName;

    @Column(name = "os_arch")
    private String osArch;

    @Column(name = "ip_address", columnDefinition = "inet")
    private Inet ipAddress;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    private LocalDateTime updatedAt = LocalDateTime.now();

    @OneToOne
    @PrimaryKeyJoinColumn
    private Categories Category;

    public LogProductViews(
            Long productId,
            @Nullable Long userId,
            @Nullable String lang,
            @Nullable String referer,
            @Nullable String screenResolution,
            @Nullable String browserName,
            @Nullable String browserVersion,
            @Nullable String osName,
            @Nullable String osArch,
            @Nullable Inet ipAddress
    ){
        this.setProductId(productId);
        this.setUserId(userId);
        this.setLang(lang);
        this.setReferer(referer);
        this.setScreenResolution(screenResolution);
        this.setBrowserName(browserName);
        this.setBrowserVersion(browserVersion);
        this.setOsName(osName);
        this.setOsArch(osArch);
        this.setIpAddress(ipAddress);
    }
}
