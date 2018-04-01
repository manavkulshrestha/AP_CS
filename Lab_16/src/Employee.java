/*
    Manav Kulshrestha
    Employee.java
    3/24/18
*/

import java.util.Comparator;

public abstract class Employee {
    private String name;
    private int id;
    private double pay;

    public Employee(String name, double pay) {
        this.name = name;
        this.pay = pay;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setID(int id) {
        this.id = id;
    }

    public int getID() {
        return this.id;
    }

    public void setPay(double pay) {
        this.pay = pay;
    }

    public double getPay() {
        return this.pay;
    }

    public void giveRaise(double delta) {
        this.pay += delta;
    }

    public abstract double getWeekPay();

    public abstract String toString();
}