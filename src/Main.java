import com.aspose.cells.SaveFormat;
import com.aspose.cells.Workbook;
import ui.Action;

import java.awt.geom.Arc2D;
import java.util.Map;
import java.awt.*;
import java.io.File;
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
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    break;
                                } catch (Exception e) {
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
    private static ExcelReader getExcelReader(String Gender)
    {
        switch (Gender)
        {
            case "F":
                if(female == null)
                    female = new ExcelReader("Data/WPP2015_POP_F01_3_TOTAL_POPULATION_FEMALE.xlsx");
                return  female;

            case "M":
                if(male == null)
                    male = new ExcelReader("Data/WPP2015_POP_F01_2_TOTAL_POPULATION_MALE.xlsx");
                return male;
        }
        return null;
    }
    static ExcelReader female,male;
}




