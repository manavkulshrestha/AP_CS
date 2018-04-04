/*
    Manav Kulshrestha
    Payroll.java
    3/24/18
*/

import java.io.*;
import java.util.*;

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

    public Employee addEmployee(int id, Employee employee) {
        employee.setID(id);
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

    public void readFromFile(String fileName) throws IOException {
        Scanner fileReader = new Scanner(new File(fileName));

        if(fileReader.hasNextLine()) {
            this.employeeID = fileReader.nextInt();
            fileReader.nextLine();

            while(fileReader.hasNextLine()) {
                String[] lineArray = fileReader.nextLine().split(":");
                if(lineArray[0].equals("H"))
                    this.addEmployee(Integer.parseInt(lineArray[1]), new HourlyEmployee(lineArray[2], Double.parseDouble(lineArray[3])));
                else if(lineArray[0].equals("S"))
                    this.addEmployee(Integer.parseInt(lineArray[1]), new SalariedEmployee(lineArray[2], Double.parseDouble(lineArray[3])));
                else
                    System.out.print("ERROR: Employee type not recognized");
            }
        }
    }

    public void saveToFile(String fileName) throws IOException {
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(fileName)));

        out.println(this.employeeID);
        for(Employee employee: this.employees)
            out.println(employee.info());

        out.close();
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