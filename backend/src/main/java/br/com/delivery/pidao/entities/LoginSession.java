package br.com.delivery.pidao.entities;

import lombok.Getter;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.UUID;

@Entity
@Getter
@Table(name = "loginsession")
public class LoginSession {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, unique = true, nullable = false)
    private UUID uuid;

    @Column(name = "useridentifier")
    private String userIdentifier;

    @Column(nullable = false)
    private String token;

    @Column(name = "logindate",nullable = false)
    private Date loginDate;

    @Column(name = "expirationdate",nullable = false)
    private Date expirationDate;

    @Column(name = "logoutdate")
    private Date logoutDate;

    //TODO: implementar o login session para os tipos devidos de usuarios
    public LoginSession(User user) {
        this.userIdentifier = user.getUserIdentifier().toString();
        this.token = UUID.randomUUID().toString();
        this.loginDate = new Date();
        this.expirationDate = Date.from(LocalDateTime.now().plus(Duration.of(10, ChronoUnit.MINUTES)).atZone(ZoneId.systemDefault()).toInstant());
    }

    public LoginSession() {
    }

    public String getToken() {
        return token;
    }

    public String getUserIdentifier() {
        return userIdentifier;
    }


    public void finish() {
        this.logoutDate = new Date();
    }
    public Date getLogoutDate() {
        return logoutDate;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }
}