package com.ps;

import java.sql.Date;
import java.util.ArrayList;

public class SalesContract extends Contract{
    private  double salesTaxAmount;
    private  double recordingFee;
    private double processingFee;
    private boolean isFinanced;
    private double monthlyPayment;

    public SalesContract(String date, String customerName, String email, Vehicle vehicleSold, boolean isFinanced) {
        super(date, customerName, email, vehicleSold, 0.0, 0.0);
        this.isFinanced = isFinanced;
        this.salesTaxAmount = getSalesTaxAmount();
        this.recordingFee = getRecordingFee();
        this.processingFee = getProcessingFee();
        this.monthlyPayment = getMonthlyPayment();


    }



    public double getSalesTaxAmount() {
        double vehiclePrice = getVehicleSold().getPrice();
        double salesTaxAmount = vehiclePrice * 0.05;

        return salesTaxAmount;
    }

    public void setSalesTaxAmount(double salesTaxAmount) {
        this.salesTaxAmount = salesTaxAmount;
    }

    public  double getRecordingFee() {
        recordingFee = 100;
        return recordingFee;
    }

    public  void setRecordingFee(double recordingFee) {
        this.recordingFee = recordingFee;
    }

    public double getProcessingFee() {
        double vechicelPrice = getVehicleSold().getPrice();
        if (vechicelPrice < 10000){
            processingFee = 295;
        }else {
            processingFee = 495;
        }

        return processingFee;
    }

    public void setProcessingFee(double processingFee) {
        this.processingFee = processingFee;
    }

    public boolean isFinanced() {

        return isFinanced;
    }

    public void setFinanced(boolean financed) {
        isFinanced = financed;
    }

    @Override
    public void setMonthlyPayment(double monthlyPayment) {
        this.monthlyPayment = monthlyPayment;
    }

    @Override
    public double getTotalPrice() {
        double vehiclePrice =  getVehicleSold().getPrice();
        double salesTax = getSalesTaxAmount();
        double recordingFee = getRecordingFee();
        double processingFee = getProcessingFee();

        return vehiclePrice + salesTax + recordingFee + processingFee;
    }

    @Override
    public double getMonthlyPayment() {
        if (isFinanced && getVehicleSold().getPrice() >= 10000){
            double loanAmount = getTotalPrice();
            double rate = 0.0425/12;
            int term = 48;
            return loanAmount * (rate * Math.pow(1 + rate, term)) / (Math.pow(1 + rate, term) - 1);

        }else if(isFinanced && getVehicleSold().getPrice() < 10000){
            double loanAmount = getTotalPrice();
            double rate = 0.0525 / 12;
            int term = 24;
            return loanAmount * (rate * Math.pow(1 + rate, term)) / (Math.pow(1 + rate, term) - 1);

        }

        else {
            return 0.0;
        }
    }
}
