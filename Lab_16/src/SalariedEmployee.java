/*
    Manav Kulshrestha
    SalariedEmployee.java
    3/24/18
*/

public class SalariedEmployee extends Employee {
    private final int WEEKS_IN_A_YEAR = 52;

    public SalariedEmployee(String name, double yearlySalary) {
        super(name, yearlySalary);
    }

    public SalariedEmployee(int id, String name, double yearlySalary) {
        super(id, name, yearlySalary);
    }

    @Override
    public double getWeekPay() {
        return this.getPay()/WEEKS_IN_A_YEAR;
    }

    @Override
    public String toString() {
        return this.getID()+". "+this.getName()+" earns $"+this.getPay()+"/yr";
    }

    @Override
    public String info() {
        return "H:"+this.getID()+":"+this.getName()+":"+this.getPay();
    }
}