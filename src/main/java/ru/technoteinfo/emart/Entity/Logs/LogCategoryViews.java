package ru.technoteinfo.emart.Entity.Logs;

import com.vladmihalcea.hibernate.type.basic.Inet;
import com.vladmihalcea.hibernate.type.basic.PostgreSQLInetType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.tomcat.jni.Local;
import org.hibernate.annotations.TypeDef;
import org.springframework.lang.Nullable;
import ru.technoteinfo.emart.Entity.Categories;


import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "log_category_views")
@NoArgsConstructor
@TypeDef(typeClass = PostgreSQLInetType.class, name = "inet", defaultForType = Inet.class)
public class LogCategoryViews {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "category_id")
    private Long categoryId;

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

    public LogCategoryViews(
            Long categoryId,
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
        this.categoryId = categoryId;
        this.userId = userId;
        this.lang = lang;
        this.referer = referer;
        this.screenResolution = screenResolution;
        this.browserName = browserName;
        this.browserVersion = browserVersion;
        this.osName = osName;
        this.osArch = osArch;
        this.setIpAddress(ipAddress);
    }
}
