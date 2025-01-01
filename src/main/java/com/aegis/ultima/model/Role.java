package com.aegis.ultima.model;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "user_roles")
public class Role {
    @Id
    @Column(name = "role_id")
    private String id;

    @Column
    private String name;

    @Column
    private String description;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @PrePersist
    private void generateUUID() {
        if (this.id == null) {
            this.id = UUID.randomUUID().toString();
        }
    }
}
