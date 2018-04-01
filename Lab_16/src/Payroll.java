/*
    Manav Kulshrestha
    Payroll.java
    3/24/18
*/

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Payroll {
    private List<Employee> employees;
    private int employeeID = 0;

    public Payroll() {
        employees = new ArrayList<>();
    }

    public double getWeekPayroll() {
        double weekPayroll = 0;

        for(Employee employee: this.employees)
            weekPayroll += employee.getWeekPay();

        return weekPayroll;
    }

    public void startNewWeek() {
        for(Employee employee: this.employees)
            if(employee instanceof HourlyEmployee)
                ((HourlyEmployee)employee).setHoursWorked(0);
    }

    public Employee findEmployee(int id) {
        for(Employee employee: this.employees)
            if(employee.getID() == id)
                return employee;

        return null;
    }

    public Employee addEmployee(Employee employee) {
        employee.setID(this.employeeID++);
        this.employees.add(employee);

        return employee;
    }

    public boolean removeEmployee(int id) {
        Employee employee = findEmployee(id);
        if(employee == null)
            return false;

        this.employees.remove(employee);

        return true;
    }

    public boolean print() {
        if(this.employees.size() > 0) {
            Collections.sort(this.employees, new Comparator<Employee>() {
                @Override
                public int compare(Employee a, Employee b) {
                    String aName = a.getName(), bName = b.getName();

                    if (a instanceof HourlyEmployee) {
                        aName = " "+aName;
                    }

                    if (b instanceof HourlyEmployee) {
                        bName = " "+bName;
                    }

                    return aName.compareTo(bName);
                }
            });
            for(Employee employee: this.employees)
                System.out.println(employee);
            return true;
        }
        return false;
    }
}