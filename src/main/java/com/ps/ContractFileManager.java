package com.ps;

import java.io.*;
import java.util.ArrayList;

public class ContractFileManager {





//    private static ArrayList<Contract> allContracts = new ArrayList<>();
//
//    public static Contract getContract(){
//        try {
//            BufferedReader bufferedReader = new BufferedReader(new FileReader("contract.csv"));
//
//            String input = bufferedReader.readLine();
//            ArrayList<SalesContract> salesContracts = new ArrayList<>();
//
//            while((input = bufferedReader.readLine()) != null){
//                String[] contractDetails = input.split("\\|");
//
//                if(contractDetails[0].equalsIgnoreCase("Sale")){
//                    String contractType = contractDetails[0];
//                    String contractDate = contractDetails[1];
//                    String customerName = contractDetails[2];
//                    String customerEmail = contractDetails[3];
//                    int vin = Integer.parseInt(contractDetails[4]);
//                    int year = Integer.parseInt(contractDetails[5]);
//                    String make = contractDetails[6];
//                    String model = contractDetails[7];
//                    String type = contractDetails[8];
//                    String color = contractDetails[9];
//                    int odometer = Integer.parseInt(contractDetails[10]);
//                    double price = Double.parseDouble(contractDetails[11]);
//                    double salesTaxAmount = Double.parseDouble(contractDetails[12]);
//                    double recordingFee = Double.parseDouble(contractDetails[13]);
//                    double processingFee = Double.parseDouble(contractDetails[14]);
//                    double totalPrice = Double.parseDouble(contractDetails[15]);
//                    boolean isFinanced = Boolean.parseBoolean(contractDetails[16]);
//                    double monthlyPayment = Double.parseDouble(contractDetails[17]);
//
//                    Vehicle vehicle = new Vehicle(vin, year, make, model, type, color, odometer, price);
//
//                    SalesContract salesContract = new SalesContract(
//                            contractDate,
//                            customerName,
//                            customerEmail,
//                            vehicle,
//                            totalPrice,
//                            monthlyPayment,
//                            salesTaxAmount,
//                            processingFee,
//                            isFinanced
//                    );
//
//                    allContracts.add(salesContract);
//                }
//
//                //todo: leaseContract
//
//            }
//            bufferedReader.close();
//
//
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//
//        return null;
//    }
//    public static ArrayList<Contract> getAllContracts() {
//        return allContracts;
//    }

}
