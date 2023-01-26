package model;

import java.math.BigDecimal;

public class Director extends Employee {
    private final BigDecimal serviceAllowance;
    private final String businessCard;
    private final BigDecimal costLimit;
    public Director(String pesel, String firstName, String lastName, String phoneNumber, BigDecimal salary,
                    BigDecimal serviceAllowance, String businessCard, BigDecimal costLimit) {
        super(pesel, firstName, lastName, phoneNumber, salary);
        this.serviceAllowance = serviceAllowance;
        this.businessCard = businessCard;
        this.costLimit = costLimit;
    }

    public BigDecimal getServiceAllowance() {
        return serviceAllowance;
    }

    public String getBusinessCard() {
        return businessCard;
    }

    public BigDecimal getCostLimit() {
        return costLimit;
    }

    @Override
    public void showEmployee(){
        System.out.println("Position: " + this.getPosition());
        System.out.println("PESEL: " + this.getPesel());
        System.out.println("First name: " + this.getFirstName());
        System.out.println("Last name: " + this.getLastName());
        System.out.println("Salary (PLN): " + this.getSalary());
        System.out.println("Phone number: " + this.getPhoneNumber());
        System.out.println("Service allowance: " + this.getServiceAllowance() + "%");
        System.out.println("Business card number: " + this.getBusinessCard());
        System.out.println("Month cost limit (PLN): " + this.getCostLimit());
    }
}
