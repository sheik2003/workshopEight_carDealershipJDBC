package com.ps;

public abstract class Contract {
    private String date;
    private String customerName;
    private Vehicle vehicleSold;
    private double totalPrice;
    private double monthlyPayment;

    public Contract(String date, String customerName, Vehicle vehicleSold, double totalPrice, double monthlyPayment) {
        this.date = date;
        this.customerName = customerName;
        this.vehicleSold = vehicleSold;
        this.totalPrice = totalPrice;
        this.monthlyPayment = monthlyPayment;


    }



    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Vehicle getVehicleSold() {
        return vehicleSold;
    }

    public void setVehicleSold(Vehicle vehicleSold) {
        this.vehicleSold = vehicleSold;
    }

    public abstract double getTotalPrice();

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public abstract double getMonthlyPayment ();


    public void setMonthlyPayment(double monthlyPayment) {
        this.monthlyPayment = monthlyPayment;
    }


}
