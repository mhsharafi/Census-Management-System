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
                                    switch (gender) {
                                        case "F":
                                            String address = "Data/WPP2015_POP_F01_3_TOTAL_POPULATION_FEMALE.xlsx";
                                            ExcelReader er = new ExcelReader(address);
                                            int res = er.getPopulation(country, year);
                                            System.out.println(res);
                                            break;
                                        case "M":
                                            address = "Data/WPP2015_POP_F01_2_TOTAL_POPULATION_MALE.xlsx";
                                            er = new ExcelReader(address);
                                            res = er.getPopulation(country, year);
                                            System.out.println(res);
                                            break;
                                    }


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
                                    switch (gender) {
                                        case "F":
                                            String address = "Data/WPP2015_POP_F01_3_TOTAL_POPULATION_FEMALE.xlsx";
                                            ExcelReader er = new ExcelReader(address);

                                            er.setPopulation(country, year, population);
                                            int res = er.getPopulation(country, year);
                                            System.out.println(res);
                                            break;
                                        case "M":
                                            address = "Data/WPP2015_POP_F01_2_TOTAL_POPULATION_MALE.xlsx";
                                            er = new ExcelReader(address);
                                            er.setPopulation(country, year, population);
                                            res = er.getPopulation(country, year);
                                            System.out.println(res);
                                            break;
                                    }

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
                                    switch (gender) {
                                        case "F":
                                            String address = "Data/WPP2015_POP_F01_3_TOTAL_POPULATION_FEMALE.xlsx";
                                            ExcelReader er = new ExcelReader(address);
                                            er.createChart(country, 'F');
                                            Workbook workbook = null;
                                            try {
                                                workbook = new Workbook("Data/chart-year.xlsx");
                                                workbook.save("Data/MyPdfFile.pdf", SaveFormat.PDF);
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                            break;
                                        case "M":
                                            address = "Data/WPP2015_POP_F01_2_TOTAL_POPULATION_MALE.xlsx";
                                            er = new ExcelReader(address);
                                            er.createChart(country, 'M');
                                            workbook = null;
                                            try {
                                                workbook = new Workbook("Data/chart-year.xlsx");
                                                workbook.save("Data/MyPdfFile.pdf", SaveFormat.PDF);
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                            break;
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
}
//                            case "deletedoc":
//                                if (splitedCommand.length > 2) {
//                                    System.out.println("Invalid argument.");
//                                    break;
//                                }
//                                try {
//                                    docId = Integer.parseInt(splitedCommand[1]);
//                                } catch (Exception e) {
//                                    System.out.println("Invalid argument.");
//                                    break;
//                                }
//                                indexer.deleteDoc(docId);
//                                System.out.println("delete document successfully!");
//                                break;
//                            case "postinglist":
//                                if (splitedCommand.length > 2) {
//                                    System.out.println("Invalid argument.");
//                                    break;
//                                }
//                                indexer.getPostingList(splitedCommand[1]).print();
//                                break;
//                            case "query":
//                                if (splitedCommand.length == 1) {
//                                    System.out.println("Invalid argument.");
//                                    break;
//                                }
//                                String q = "";
//                                for (int i = 1; i < splitedCommand.length; i++) {
//                                    q += splitedCommand[i] + " ";
//                                }
//                                String editedQuery = query.setQuery(q);
//                                System.out.println("Edited Query : " + editedQuery);
//                                System.out.print("\tselect query mode : ");
//                                state = 1;
//                                break;
//                            case "evaluate":
//                                if (splitedCommand.length != 4) {
//                                    System.out.println("Invalid argument.");
//                                    break;
//                                }
//
//                                QueryMode qm = null;
//                                try {
//                                    switch (Integer.parseInt(splitedCommand[3])) {
//                                        case 1:
//                                            qm = QueryMode.lnc_ltc;
//                                            break;
//                                        case 2:
//                                            qm = QueryMode.lnn_ltn;
//                                            break;
//                                        default:
//                                            System.out.println("Invalid argument.");
//                                            break;
//                                    }
//                                } catch (Exception e) {
//                                    switch (splitedCommand[3]) {
//                                        case "lnc-ltc":
//                                            qm = QueryMode.lnc_ltc;
//                                            break;
//                                        case "lnn-ltn":
//                                            qm = QueryMode.lnn_ltn;
//                                            break;
//                                        default:
//                                            System.out.println("Invalid argument.");
//                                            break;
//                                    }
//                                }
//                                if (qm == null) break;
//
//                                EvaluationMethod em = null;
//                                try {
//                                    switch (Integer.parseInt(splitedCommand[2])) {
//                                        case 1:
//                                            em = EvaluationMethod.MAP;
//                                            break;
//                                        case 3:
//                                            em = EvaluationMethod.FMeasure;
//                                            break;
//                                        case 2:
//                                            em = EvaluationMethod.R_precistion;
//                                            break;
//                                        default:
//                                            System.out.println("Invalid argument.");
//                                            break;
//                                    }
//                                } catch (Exception e) {
//                                    switch (splitedCommand[3]) {
//                                        case "map":
//                                            em = EvaluationMethod.MAP;
//                                            break;
//                                        case "fmeasure":
//                                        case "f-measure":
//                                            em = EvaluationMethod.FMeasure;
//                                            break;
//                                        case "rprecision":
//                                        case "r-precision":
//                                            em = EvaluationMethod.R_precistion;
//                                            break;
//                                        default:
//                                            System.out.println("Invalid argument.");
//                                            break;
//                                    }
//                                }
//                                if (em == null) break;
//
//                                if (splitedCommand[1].equalsIgnoreCase("all")) {
//                                    evaluation.evaluateAll(qm, em);
//                                } else {
//                                    try {
//                                        docId = Integer.parseInt(splitedCommand[1]);
//                                    } catch (Exception e) {
//                                        System.out.println("Invalid argument.");
//                                        break;
//                                    }
//                                    evaluation.evaluate(docId, qm, em);
//                                }
//                                break;
//                            case "help":
//                            case "-h":
//                                System.out.println("\tindex\t:\tindex all documents");
//                                System.out.println("\taddDoc <doc number>\t:\tadd document to index");
//                                System.out.println("\tdeleteDoc <doc number>\t:\tdelete documnet from index");
//                                System.out.println("\tpostingList <term>\t:\tshow posting list of term");
//                                System.out.println("\tquery <query string>\t:\tquery");
//                                System.out.println("\tevaluate all/<query number>  <evaluation method>  <query method>\t:\t evaluate all measurement for query");
//                                System.out.println("\t\tevaluation method : MAP(1)/R-Precision(2)/F-Measure(3)");
//                                System.out.println("\t\tquery method : lnc-ltc(1)/lnn-ltn(2)");
//                                System.out.println("\tExit : exit from application");
//                                break;
//                            case "exit":
//                                return;
//                            default:
//                                System.out.println("Invalid Command. use \"help\" command to see available commands.");
//                        }
//                        break;
//                    case 1:
//                        switch (splitedCommand[0]) {
//                            case "-h":
//                            case "help":
//                                System.out.println("query method : lnc-ltc(1)/lnn-ltn(2)");
//                                System.out.println("Exit : exit from this state");
//                                break;
//                            case "exit":
//                                state = 0;
//                                break;
//                            case "1":
//                            case "lnc-ltc":
//                                printRankedQuery(query.getRankedDocs(QueryMode.lnc_ltc));
//                                state = 2;
//                                System.out.print("Enter document Number to open it : ");
//                                break;
//                            case "2":
//                            case "lnn-ltn":
//                                printRankedQuery(query.getRankedDocs(QueryMode.lnn_ltn));
//                                state = 2;
//                                System.out.print("Enter document Number to open it : ");
//                                break;
//
//                        }
//                        break;
//                    case 2:
//                        switch (splitedCommand[0]) {
//                            case "-h":
//                            case "help":
//                                System.out.print("Enter document Number to open it : ");
//                                System.out.println("Exit : exit from application");
//                            case "exit":
//                                state = 0;
//                                break;
//                            default:
//                                docId = -1;
//                                try {
//                                    docId = Integer.parseInt(splitedCommand[0]);
//                                } catch (Exception e) {
//                                    System.out.println("Invalid argument.");
//                                    break;
//                                }
//                                if (docId == -1) {
//                                    System.out.println("Invalid argument.");
//                                    break;
//                                }
//                                try {
//                                    Scanner readFile = new Scanner(new File(docFolder + docId));
//                                    while (readFile.hasNext()) {
//                                        System.out.println(readFile.nextLine());
//                                    }
//                                } catch (Exception e) {
//                                    System.out.println("Invalid argument.");
//                                    break;
//                                }
//                                break;
//                        }
//                        break;
//                }



