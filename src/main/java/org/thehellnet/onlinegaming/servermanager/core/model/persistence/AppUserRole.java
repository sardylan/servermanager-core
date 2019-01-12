package org.thehellnet.onlinegaming.servermanager.core.model.persistence;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "appuserrole")
public class AppUserRole implements Serializable {

    @Id
    @Column(name = "id", updatable = false, nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "appuserrole_id_seq")
    @SequenceGenerator(name = "appuserrole_id_seq", sequenceName = "appuserrole_id_seq")
    private Long id;

    @Basic
    @Column(name = "tag", nullable = false, unique = true)
    private String tag;

    @Basic
    @Column(name = "description", nullable = false)
    private String description = "";

    @ManyToMany(mappedBy = "appUserRoles")
    private Set<AppUser> appUsers = new HashSet<>();

    public AppUserRole() {
    }

    public AppUserRole(String tag) {
        this.tag = tag;
    }

    public AppUserRole(String tag, String description) {
        this.tag = tag;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<AppUser> getAppUsers() {
        return appUsers;
    }

    public void setAppUsers(Set<AppUser> appUsers) {
        this.appUsers = appUsers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppUserRole that = (AppUserRole) o;
        return id.equals(that.id) &&
                tag.equals(that.tag) &&
                description.equals(that.description) &&
                appUsers.equals(that.appUsers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tag);
    }

    @Override
    public String toString() {
        return description.length() > 0 ? description : tag;
    }
}
