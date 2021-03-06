package org.thehellnet.onlinegaming.servermanager.core.model.persistence;

import org.thehellnet.onlinegaming.servermanager.core.model.constant.Role;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "appuser")
public class AppUser implements Serializable {

    @Id
    @Column(name = "id", updatable = false, nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_seq")
    @SequenceGenerator(name = "user_id_seq", sequenceName = "user_id_seq")
    private Long id;

    @Basic
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Basic
    @Column(name = "password", nullable = false)
    private String password;

    @OneToMany(mappedBy = "appUser", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private Set<AppUserToken> appUserTokens = new HashSet<>();

    @ElementCollection(targetClass = Role.class)
    @JoinTable(name = "appuser_role", joinColumns = @JoinColumn(name = "appuser_id"))
    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private Set<Role> appUserRoles = new HashSet<>();

    public AppUser() {
    }

    public AppUser(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<AppUserToken> getAppUserTokens() {
        return appUserTokens;
    }

    public void setAppUserTokens(Set<AppUserToken> appUserTokens) {
        this.appUserTokens = appUserTokens;
    }

    public Set<Role> getAppUserRoles() {
        return appUserRoles;
    }

    public void setAppUserRoles(Set<Role> appUserRoles) {
        this.appUserRoles = appUserRoles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppUser appUser = (AppUser) o;
        return id.equals(appUser.id) &&
                email.equals(appUser.email) &&
                Objects.equals(password, appUser.password) &&
                appUserTokens.equals(appUser.appUserTokens) &&
                appUserRoles.equals(appUser.appUserRoles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email);
    }

    @Override
    public String toString() {
        return email;
    }
}
