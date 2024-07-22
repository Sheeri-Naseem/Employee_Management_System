package org.kane.entities;

import javax.persistence.*;

@Entity
@Table(name = "permission")
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "permission_type", nullable = false)
    private String permission_type;

    public Permission() {
    }

    public Permission(int id) {
        this.id = id;
    }

    public Permission(int id, String permission_type) {
        this.id = id;
        this.permission_type = permission_type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPermission_type() {
        return permission_type;
    }

    public void setPermission_type(String permission_type) {
        this.permission_type = permission_type;
    }

    @Override
    public String toString() {
        return "Permission{" +
                "id=" + id +
                ", permission_type='" + permission_type + '\'' +
                '}';
    }

}
