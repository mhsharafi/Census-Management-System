import com.aspose.cells.SaveFormat;
import com.aspose.cells.Workbook;
import ui.Action;

import java.awt.geom.Arc2D;
import java.io.*;
import java.util.Map;
import java.awt.*;
import java.util.Scanner;
import java.util.TreeMap;


/**
 * Created by mohammad hosein on 21/02/2016.
 */
public class Main {
    private static Scanner consoleIn;


    public static void main(String[] args) {
        //File Address
//        String address = "Data/WPP2015_POP_F01_3_TOTAL_POPULATION_FEMALE.xlsx";
//        ExcelReader er = new ExcelReader(address);
//        int res = er.getPopulation("Djibouti", 1952);
//        System.out.println(res);
//        er.setPopulation("Djibouti", 1952, 1.7);
//        res = er.getPopulation("Djibouti", 1952);
//        System.out.println(res);
//        er.createChart("Djibouti", 'F');
//        Workbook workbook = null;
//        try {
//            workbook = new Workbook("Data/chart-year.xlsx");
//            workbook.save("Data/MyPdfFile.pdf", SaveFormat.PDF);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        int state = 0;

        String command;
        String[] splitedCommand;
        int docId = 0;
        Scanner input = new Scanner(System.in);
        while (true) {
            try {


                command = input.nextLine();
                splitedCommand = command.split(" ");
                switch (state) {
                    case 0:
                        switch (splitedCommand[0]) {

                            case "info":
                                if (splitedCommand.length > 4) {
                                    System.out.println("Invalid argument.");
                                    break;
                                }
                                try {
                                    String country = splitedCommand[1];
                                    int year = Integer.parseInt(splitedCommand[2]);
                                    String gender = splitedCommand[3];

                                    ExcelReader er = getExcelReader(gender);
                                    int res = er.getPopulation(country, year);
                                    System.out.println(res);

                                    break;
                                } catch (Exception e) {
                                    System.out.println("Invalid argument.");
                                    break;
                                }
                            case "set":
                                if (splitedCommand.length > 5) {
                                    System.out.println("Invalid argument.");
                                    break;
                                }
                                try {
                                	
                                    String country = splitedCommand[1];
                                    if (isProtect(country)){
                                    	System.out.println("The country is protected.");
                                    	break;
                                    }
                                    int year = Integer.parseInt(splitedCommand[2]);
                                    double population = Double.parseDouble(splitedCommand[3]);
                                    String gender = splitedCommand[4];

                                    ExcelReader er = getExcelReader(gender);

                                    er.setPopulation(country, year, population);
                                    int res = er.getPopulation(country, year);
                                    System.out.println(res);
                                    break;
                                } catch (Exception e) {
                                    System.out.println("Invalid argument.");
                                    break;
                                }
                            case "plot":
                                if (splitedCommand.length > 3) {
                                    System.out.println("Invalid argument.");
                                    break;
                                }
                                try {
                                    String country = splitedCommand[1];
                                    String gender = splitedCommand[2];

                                    ExcelReader er = getExcelReader(gender);

                                    er.createChart(country);
                                    Workbook workbook = null;
                                    try {
                                        workbook = new Workbook("Data/chart-year.xlsx");
                                        workbook.save("Data/MyPdfFile.pdf", SaveFormat.PDF);
                                        System.out.print("success");
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    break;
                                } catch (Exception e) {
                                    System.out.println("Invalid argument.");
                                    break;
                                }
                            case "protect":
                            	if (splitedCommand.length != 2){
                            		System.out.println("Invalid argument.");
                                    break;
                            	}
                            	try{
                            		String country = splitedCommand[1];
                            		setProtect(country);
                            	} catch (Exception e){
                            		System.out.println("Invalid argument.");
                                    break;
                            	}
                        }
                }
            } catch (Exception e) {
                System.out.println();
            }
        }
    }

    private static ExcelReader getExcelReader(String Gender) {
        switch (Gender) {
            case "F":
                if (female == null)
                    female = new ExcelReader("Data/WPP2015_POP_F01_3_TOTAL_POPULATION_FEMALE.xlsx");
                return female;

            case "M":
                if (male == null)
                    male = new ExcelReader("Data/WPP2015_POP_F01_2_TOTAL_POPULATION_MALE.xlsx");
                return male;
        }
        return null;

    }

    static ExcelReader female, male;




    private static boolean isProtect(String countryName) {
        File file = new File("src/ui/protectedCountries.txt");
        Scanner sc = null;
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int flag = 0;
        while (sc.hasNext()) {
            if (countryName.equals(sc.nextLine())) {
                flag = 1;
                break;
            }
        }
        if (flag == 1)
            return true;
        return false;
    }

    private static void setProtect(String CountryName) {
        Writer output;
        try {
            output = new BufferedWriter(new FileWriter("src/ui/protectedCountries.txt", true));
            output.append(CountryName);
            output.close();
        } catch (Exception e) {

        }

    }

}




