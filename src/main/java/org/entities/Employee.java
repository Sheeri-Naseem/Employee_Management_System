package org.entities;

public class Employee {

    private int empId;
    private String name;
    private int age;
    private String address;
    private String password;

    public Employee(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public Employee(int empId, String name, int age, String password, String address) {
        this.empId = empId;
        this.name = name;
        this.age = age;
        this.password = password;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "address='" + address + '\'' +
                ", empId=" + empId +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
