package model;

import java.io.Serializable;
import java.math.BigDecimal;

public abstract class Employee implements Serializable {
    private final String pesel;
    private final String firstName;
    private final String lastName;
    private final String position;
    private final String phoneNumber;
    private final BigDecimal salary;

    public Employee(String pesel, String firstName, String lastName, String phoneNumber, BigDecimal salary) {
        try {
            validatePesel(pesel);
        } catch (IllegalArgumentException e){
            System.err.println(e.getMessage());
        }
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.pesel = pesel;
        this.salary = salary;
        position = this.getClass().getSimpleName();
    }
    public String getPesel(){
        return pesel;
    }
    static public void validatePesel(String pesel){
        if (pesel.length() != 11)
            throw new IllegalArgumentException("PESEL should be 11 digits long");
        int sum = 0;
        int[] weight = {1, 3, 7, 9, 1, 3, 7 ,9 ,1 ,3};
        for (int i = 0; i < 10; i++)
            sum += Integer.parseInt(pesel.substring(i, i+1)) * weight[i];
        int controlDigit = Integer.parseInt(pesel.substring(10, 11));
        sum %= 10;
        sum = 10 - sum;
        sum %= 10;

        if (sum != controlDigit)
            throw new IllegalArgumentException("Control sum and control digit does not match");
    }
    public String getFirstName(){
        return firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public String getPosition(){
        return position;
    }

    public String getPhoneNumber(){
        return phoneNumber;
    }

    public BigDecimal getSalary(){
        return salary;
    }

    public abstract void showEmployee();
}