package model;

import java.math.BigDecimal;

public class Trader extends Employee {
    private final int commission;
    private final BigDecimal commissionLimit;

    public Trader(String pesel, String firstName, String lastName, String phoneNumber, BigDecimal salary, int commission,
                  BigDecimal commissionLimit) {
        super(pesel, firstName, lastName, phoneNumber, salary);
        this.commission = commission;
        this.commissionLimit = commissionLimit;
    }

    public int getCommission() {
        return commission;
    }

    public BigDecimal getCommissionLimit(){
        return commissionLimit;
    }

    @Override
    public void showEmployee(){
        System.out.println("Position: " + this.getPosition());
        System.out.println("PESEL: " + this.getPesel());
        System.out.println("First name: " + this.getFirstName());
        System.out.println("Last name: " + this.getLastName());
        System.out.println("Salary (PLN): " + this.getSalary());
        System.out.println("Phone number: " + this.getPhoneNumber());
        System.out.println("Commission: " + this.getCommission() + "%");
        System.out.println("Month commission limit (PLN): " + this.getCommissionLimit());
    }
}
