package org.fasttrackit.nationsmatch.domain;

import javax.persistence.*;

@Entity
public class Role {

    @Id
    private Long id;
    private String role;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private User user;

    public Role() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
