package org.kane.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int empId;

    @Column(name="user_name", nullable = false)
    private String uname;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "age",nullable = false)
    private int age;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "password", nullable = false)
    private String password;

    //    private String address;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
//            (cascade = CascadeType.ALL)
    @JoinTable(
            name = "employee_role",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roles;

    public Employee() {
    }

    public Employee(String uname, String password) {
        this.uname = uname;
        this.password = password;
    }

    public Employee(String uname, String email, int age, Address address, String password, String phone, List<Role> roles) {
        this.uname = uname;
        this.email = email;
        this.age = age;
        this.address = address;
        this.password = password;
        this.phone = phone;
        this.roles = roles;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "empId=" + empId +
                ", name='" + uname + '\'' +
                ", age=" + age +
                ", phone=" + phone +
                ", address=" + address +
                ", password='" + password + '\'' +
                ", roles=" + roles +
                '}';
    }
}
