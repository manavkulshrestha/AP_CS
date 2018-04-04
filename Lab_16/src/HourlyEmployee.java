/*
    Manav Kulshrestha
    HourlyEmployee.java
    3/24/18
*/

public class HourlyEmployee extends Employee {
    private double hoursWorked;
    private final int OVERTIME_START = 40;
    private final double OVERTIME_MULTIPLIER = 1.5;

    public HourlyEmployee(String name, double hourlyPay) {
        super(name, hourlyPay);
        this.hoursWorked = 0;
    }

    public void setHoursWorked(double hours) {
        this.hoursWorked = hours;
    }

    public double getHoursWorked() {
        return this.hoursWorked;
    }

    public void incrementHoursWorked(double hours) {
        this.hoursWorked += hours;
    }

    @Override
    public double getWeekPay() {
        double normalHours = this.hoursWorked, overtimeHours = 0;

        if(this.hoursWorked > OVERTIME_START) {
            overtimeHours = this.hoursWorked-OVERTIME_START;
            normalHours = OVERTIME_START;
        }

        return this.getPay()*(normalHours+(overtimeHours*OVERTIME_MULTIPLIER));
    }

    @Override
    public String toString() {
        return this.getID()+". "+this.getName()+" worked for "+this.hoursWorked+" hr"+((this.hoursWorked > 1 || this.hoursWorked == 0) ? "s" : "")+" at $"+this.getPay()+"/hr";
    }

    @Override
    public String info() {
        return "H:"+this.getID()+":"+this.getName()+":"+this.getPay();
    }
}