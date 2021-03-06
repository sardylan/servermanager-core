package org.thehellnet.onlinegaming.servermanager.core.model.persistence;

import org.joda.time.DateTime;
import org.thehellnet.utility.TokenUtility;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "appusertoken")
public class AppUserToken implements Serializable {

    @Id
    @Column(name = "id", updatable = false, nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "appusertoken_id_seq")
    @SequenceGenerator(name = "appusertoken_id_seq", sequenceName = "appusertoken_id_seq")
    private Long id;

    @Basic
    @Column(name = "token", nullable = false, unique = true)
    private String token;

    @ManyToOne
    @JoinColumn(name = "appuser_id", nullable = false)
    private AppUser appUser;

    @Basic
    @Column(name = "creation_datetime", nullable = false)
    private DateTime creationDateTime = new DateTime();

    @Basic
    @Column(name = "expiration_datetime")
    private DateTime expirationDateTime;

    public AppUserToken() {
        expirationDateTime = TokenUtility.generateExpiration(creationDateTime);
    }

    public AppUserToken(String token, AppUser appUser) {
        this();
        this.token = token;
        this.appUser = appUser;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    public DateTime getCreationDateTime() {
        return creationDateTime;
    }

    public void setCreationDateTime(DateTime creationDatetime) {
        this.creationDateTime = creationDatetime;
    }

    public DateTime getExpirationDateTime() {
        return expirationDateTime;
    }

    public void setExpirationDateTime(DateTime expirationDateTime) {
        this.expirationDateTime = expirationDateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppUserToken that = (AppUserToken) o;
        return id.equals(that.id) &&
                token.equals(that.token) &&
                appUser.equals(that.appUser) &&
                creationDateTime.equals(that.creationDateTime) &&
                Objects.equals(expirationDateTime, that.expirationDateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, token);
    }

    @Override
    public String toString() {
        return token;
    }
}
