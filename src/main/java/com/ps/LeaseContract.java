package com.ps;

public class LeaseContract extends Contract {
    private double expectedEndingValue;
    private double leaseFee;
    private double monthlyPayment;

    public LeaseContract(String date, String customerName, String email, Vehicle vehicleSold ) {
        super(date, customerName, email, vehicleSold, 0, 0);
        this.expectedEndingValue = expectedEndingValue;
        this.leaseFee = leaseFee;
        this.monthlyPayment = monthlyPayment;
    }

    public double getExpectedEndingValue() {
        double vehiclePrice = 0.5 * (getVehicleSold().getPrice());
            return vehiclePrice;
    }

    public void setExpectedEndingValue(double expectedEndingValue) {
        this.expectedEndingValue = expectedEndingValue;
    }

    public double getLeaseFee() {
        double vehiclePrice = getVehicleSold().getPrice();
        return vehiclePrice * .07;
    }

    public void setLeaseFee(double leaseFee) {
        this.leaseFee = leaseFee;
    }

    @Override
    public void setMonthlyPayment(double monthlyPayment) {
        this.monthlyPayment = monthlyPayment;
    }

    @Override
    public double getTotalPrice() {
//price of vehciel + total amoutn they apy
        double vehiclePrice = getVehicleSold().getPrice();

        return (vehiclePrice - expectedEndingValue) + leaseFee;
dfs
    }

    @Override
    public double getMonthlyPayment() {
        double totalPrice = getTotalPrice();
        double rate = 0.04/12;
        int term =  36;

        return  totalPrice * (rate * Math.pow(1+rate, term)) / (Math.pow(1+rate,term)-1);

    }
}
//toal cost /2
//1st half ask customer to pay that up front
//2nd half is fiananced
