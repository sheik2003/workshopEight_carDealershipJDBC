package com.ps;

import java.io.*;
import java.util.ArrayList;

public class ContractFileManager {

    public static Contract saveContract(Contract contract){

        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("contract.csv",true));

            if (contract instanceof  SalesContract){
                String contractType = "SALE";
                String isFinacedYesNo;
                if (((SalesContract) contract).isFinanced()) {
                    isFinacedYesNo = "YES";
                } else {
                    isFinacedYesNo = "NO";
                }
                String contractLine = String.format(
                        "%s|%s|%s|%s|%d|%d|\n\t%s|%s|%s|%s|%d|%.2f|\n\t%.2f|%.2f|%.2f|%.2f|%s|%.2f",
                        contractType,                 //  1 %s
                        contract.getDate(),           //  2 %s
                        contract.getCustomerName(),   //  3 %s
                        contract.getEmail(),          //  4 %s
                        contract.getVehicleSold().getVin(),        // 5 %d
                        contract.getVehicleSold().getYear(),       // 6 %d
                        contract.getVehicleSold().getMake(),       // 7 %s
                        contract.getVehicleSold().getModel(),      // 8 %s
                        contract.getVehicleSold().getVehicleType(),// 9 %s
                        contract.getVehicleSold().getColor(),      //10 %s
                        contract.getVehicleSold().getOdometer(),   //11 %d
                        contract.getVehicleSold().getPrice(),      //12 %.2f
                        ((SalesContract) contract).getSalesTaxAmount(),   //13 %.2f
                        ((SalesContract) contract).getRecordingFee(),     //14 %.2f
                        ((SalesContract) contract).getProcessingFee(),    //15 %.2f
                        contract.getTotalPrice(),                //16 **%.2f**  ← added
                        isFinacedYesNo,                          //17 %s
                        contract.getMonthlyPayment()             //18 %.2f
                );

                bufferedWriter.write(contractLine);
                bufferedWriter.newLine();
                bufferedWriter.close();

            }else if (contract instanceof LeaseContract){

                String contractType = "LEASE";

                String contractLine = String.format(
                        "%s|%s|%s|%s|%d|%d|\n\t%s|%s|%s|%s|%d|%.2f|\n\t%.2f|%.2f|%.2f|%.2f",

                        contractType,
                        contract.getDate(),
                        contract.getCustomerName(),
                        contract.getEmail(),
                        contract.getVehicleSold().getVin(),
                        contract.getVehicleSold().getYear(),

                        contract.getVehicleSold().getMake(),
                        contract.getVehicleSold().getModel(),
                        contract.getVehicleSold().getVehicleType(),
                        contract.getVehicleSold().getColor(),
                        contract.getVehicleSold().getOdometer(),
                        contract.getVehicleSold().getPrice(),

                        ((LeaseContract) contract).getExpectedEndingValue(),
                        ((LeaseContract) contract).getLeaseFee(),
                        contract.getTotalPrice(),
                        contract.getMonthlyPayment()
                );


                bufferedWriter.write(contractLine);
                bufferedWriter.newLine();
                bufferedWriter.close();

            }else{
                System.out.println("error");
            }





        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        return contract;
    }




















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
