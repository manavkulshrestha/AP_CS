/*
    Manav Kulshrestha
    OOPDesign.java
    3/27/18
*/

import java.io.IOException;
import java.util.Scanner;

public class OOPDesign {
    public static void main(String args[]) throws IOException {
        Scanner in = new Scanner(System.in);
        menu input;
        final String payrollFile = "payroll.txt";
        Payroll payroll = new Payroll();
        payroll.readFromFile(payrollFile);

        do {
            pMenu();
            p('\n');
            switch(input = menu.values()[in.nextInt()]) {
                case PRINT:
                    payroll.print();
                    break;
                case ADD_SALARIED_EMPLOYEE:
                    p("Enter name followed by yearly salary in dollars (Example- John 150000):\n");
                    Employee salariedEmployee = new SalariedEmployee(in.next().replace(":", ""), in.nextDouble());
                    payroll.addEmployee(salariedEmployee);
                    pf("Employee added- %s\n", salariedEmployee);
                    break;
                case ADD_HOURLY_EMPLOYEE:
                    p("Enter name followed by hourly pay in dollars (Example- John 11):\n");
                    Employee hourlyEmployee = new HourlyEmployee(in.next(), in.nextDouble());
                    payroll.addEmployee(hourlyEmployee);
                    pf("Employee added- %s\n", hourlyEmployee);
                    break;
                case REMOVE_EMPLOYEE:
                    p("Enter id: ");
                    if(payroll.removeEmployee(in.nextInt()))
                        p("Employee removed\n");
                    else
                        p("Employee with that id is not in the Payroll\n");
                    break;
                case ACCESS_EMPLOYEE:
                    p("Enter id: ");
                    Employee accessedEmployee = payroll.findEmployee(in.nextInt());
                    pf("%s\n", (accessedEmployee != null) ? accessedEmployee : "Employee with that id is not in the Payroll");
                    break;
                case GIVE_RAISE:
                    p("Enter id: ");
                    int id = in.nextInt();
                    Employee raiseEmployee = payroll.findEmployee(id);
                    if(raiseEmployee != null) {
                        pf("%s\nRaise pay by: ", raiseEmployee);
                        raiseEmployee.giveRaise(in.nextDouble());
                        pf("%s\n", payroll.findEmployee(id));
                    } else
                        p("Employee with that id is not in the Payroll\n");
                    break;
                case LOG_HOURLY_EMPLOYEE_HOURS:
                    p("Enter id: ");
                    Employee hourlyEmployeeProbably = payroll.findEmployee(in.nextInt());
                    if(hourlyEmployeeProbably != null) {
                        pf("%s\n", hourlyEmployeeProbably);
                        if(hourlyEmployeeProbably instanceof HourlyEmployee) {
                            p("Enter number of hours worked: ");
                            ((HourlyEmployee) hourlyEmployeeProbably).incrementHoursWorked(in.nextDouble());
                            pf("%s\n", hourlyEmployeeProbably);
                        } else
                            p("Employee with id provided does not log hours\n");
                    } else
                        p("Employee with that id is not in the Payroll\n");
                    break;
                case GET_WEEK_PAYROLL:
                    pf("Payroll for the week: $%s\n", payroll.getWeekPayroll());
                    break;
                case START_NEW_WEEK:
                    payroll.startNewWeek();
                    p("New week started\n");
                    break;
                case QUIT:
                    payroll.saveToFile(payrollFile);
                    break;
            }
            p('\n');
        } while(input != menu.QUIT);
    }

    public static void pMenu() {
        for(menu option: menu.values())
            pf("%d. %s%n", option.ordinal(), option.toString().replace('_', ' '));
    }

    public static void p(Object o) {
        System.out.print(o);
    }
    public static void pf(String s, Object... o) {
        System.out.printf(s, o);
    }
}

enum menu {PRINT, ADD_SALARIED_EMPLOYEE, ADD_HOURLY_EMPLOYEE, REMOVE_EMPLOYEE, ACCESS_EMPLOYEE, GIVE_RAISE, LOG_HOURLY_EMPLOYEE_HOURS, GET_WEEK_PAYROLL, START_NEW_WEEK, QUIT}