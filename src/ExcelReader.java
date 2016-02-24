/**
 * Created by mohammad hosein on 21/02/2016.
 */


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.charts.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.SystemOutLogger;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class ExcelReader {
    private XSSFWorkbook workbook;
    //    private FormulaEvaluator evaluator;
    private String address;
    private XSSFSheet e;
    private File ex = null;
    private HashMap<Integer, Integer> year2Col;

    public ExcelReader(String address) {
        try {
            this.address = address;
            ex = new File(address);
            FileInputStream fileinput = new FileInputStream(ex);
            this.workbook = new XSSFWorkbook(fileinput);
        } catch (Exception var4) {
            System.out.println("ERROR!");
//            var4.printStackTrace();
        }
        createYearIndex();

    }

    private void createYearIndex() {
        ArrayList<Cell> data = new ArrayList<>();

        try {
            XSSFSheet e = this.workbook.getSheetAt(0);
            //this.evaluator = this.workbook.getCreationHelper().createFormulaEvaluator();
            Iterator rowIterator = e.iterator();
            //skip invalid first rows
            for (int i = 0; i < 16; i++) {
                rowIterator.next();
            }

            Row years = (Row) rowIterator.next();
            Iterator yearCells = years.iterator();
            //skip invalid first columns
            for (int i = 0; i < 5; i++) {
                yearCells.next();
            }
            year2Col = new HashMap<>();
            while (yearCells.hasNext()) {
                Cell c = (Cell) yearCells.next();
                int year = getIntValue(c);
                year2Col.put(year, c.getColumnIndex());
            }
        } catch (Exception var15) {

        }
//            projectNameCellIterator.next();
//            projectCodeCellIterator.next();
//            ArrayList projectsCode = new ArrayList();
//
//            try {
//                while(projectCodeCellIterator.hasNext()) {
//                    Cell row = (Cell)projectCodeCellIterator.next();
//                    Cell cellIterator = (Cell)projectNameCellIterator.next();
//                    switch(row.getCellType()) {
//                        case 0:
//                            projectsCode.add((new Integer((int)row.getNumericCellValue())).toString());
//                            break;
//                        case 1:
//                            projectsCode.add(row.getStringCellValue());
//                            break;
//                        case 2:
//                            projectsCode.add(this.evaluator.evaluate(row).getStringValue());
//                    }
//
//                    switch(cellIterator.getCellType()) {
//                        case 0:
//                            data.addProject((new Double(cellIterator.getNumericCellValue())).toString(), (String)projectsCode.get(projectsCode.size() - 1));
//                            break;
//                        case 1:
//                            data.addProject(cellIterator.getStringCellValue(), (String)projectsCode.get(projectsCode.size() - 1));
//                            break;
//                        case 2:
//                            data.addProject(this.evaluator.evaluate(cellIterator).getStringValue(), (String)projectsCode.get(projectsCode.size() - 1));
//                    }
//
//                    if(row.getColumnIndex() != cellIterator.getColumnIndex()) {
//                        if(row.getColumnIndex() < cellIterator.getColumnIndex()) {
//                            Program.error.append("??? ????? ?? ?? ").append((String)projectsCode.get(projectsCode.size() - 1)).append(" ???? ?????.").append("\n");
//                        } else {
//                            switch(cellIterator.getCellType()) {
//                                case 0:
//                                    Program.error.append("?? ????? ?? ??? ").append((new Double(cellIterator.getNumericCellValue())).toString()).append(" ???? ?????.").append("\n");
//                                    break;
//                                case 1:
//                                    Program.error.append("?? ????? ?? ??? ").append(cellIterator.getStringCellValue()).append(" ???? ?????.").append("\n");
//                                    break;
//                                case 2:
//                                    Program.error.append("?? ????? ?? ??? ").append(this.evaluator.evaluate(cellIterator).getStringValue()).append(" ???? ?????.").append("\n");
//                            }
//                        }
//                    }
//                }
//            } catch (NoSuchElementException var14) {
//                Program.error.append("?? ??? ?? ?? ????? ?? ????? ???? ???? ?? ???? : \n" + this.address + "\n???? ????? ????? ? ?????? ?????? ????.").append("\n");
//            }
//
//            while(true) {
//                Cell personCell;
//                String name;
//                Iterator cellIterator1;
//                do {
//                    do {
//                        do {
//                            if(!rowIterator.hasNext()) {
//                                if(data.persons.size() == 0) {
//                                    Program.error.append("??? 2 ???? " + this.address + " ???? ??????? ????? ????.").append("\n");
//                                }
//
//                                return data;
//                            }
//
//                            Row row1 = (Row)rowIterator.next();
//                            cellIterator1 = row1.cellIterator();
//                            personCell = (Cell)cellIterator1.next();
//                        } while(!cellIterator1.hasNext());
//
//                        personCell = (Cell)cellIterator1.next();
//                        name = personCell.getStringCellValue();
//                    } while(name.equals(""));
//                } while(personCell.getColumnIndex() != 1);
//
//                data.addName(name);
//
//                while(cellIterator1.hasNext()) {
//                    Cell cell = (Cell)cellIterator1.next();
//                    if(cell.getColumnIndex() - 2 != projectsCode.size()) {
//                        if(cell.getCellType() == 0) {
//                            data.addMin((String)projectsCode.get(cell.getColumnIndex() - 2), personCell.getStringCellValue(), Double.valueOf(cell.getNumericCellValue()));
//                        } else if(cell.getCellType() == 2) {
//                            data.addMin((String)projectsCode.get(cell.getColumnIndex() - 2), personCell.getStringCellValue(), Double.valueOf(this.evaluator.evaluate(cell).getNumberValue()));
//                        }
//                    }
//                }
//            }

        //return null;
    }

    public void createChart(String country, char sex) {
        Workbook wb = new XSSFWorkbook();
        Sheet sheet = wb.createSheet("Sheet 1");
        final int NUM_OF_ROWS = 2;
        final int NUM_OF_COLUMNS = year2Col.size();

        // Create a row and put some cells in it. Rows are 0 based.
        Row row;
        Cell cell;

        for (int rowIndex = 0; rowIndex < NUM_OF_ROWS; rowIndex++) {
            row = sheet.createRow((short) rowIndex);
            switch (rowIndex) {
                case 0:
                    for (Integer i : year2Col.keySet()) {

                        cell = row.createCell(year2Col.get(i) - 5);
                        cell.setCellValue(getPopulation(country, i));
                    }
                    break;
                case 1:
                    for (Integer i : year2Col.keySet()) {

                        cell = row.createCell(year2Col.get(i) - 5);
                        cell.setCellValue(i);
                    }
            }
        }

        Drawing drawing = sheet.createDrawingPatriarch();
        ClientAnchor anchor = drawing.createAnchor(0, 0, 0, 0, 0, 5, 10, 20);

        Chart chart = drawing.createChart(anchor);
        ChartLegend legend = chart.getOrCreateLegend();
        legend.setPosition(LegendPosition.TOP_RIGHT);

        ScatterChartData data = chart.getChartDataFactory().createScatterChartData();

        ValueAxis bottomAxis = chart.getChartAxisFactory().createValueAxis(AxisPosition.BOTTOM);
        ValueAxis leftAxis = chart.getChartAxisFactory().createValueAxis(AxisPosition.LEFT);
        leftAxis.setCrosses(AxisCrosses.AUTO_ZERO);

        ChartDataSource<Number> xs = DataSources.fromNumericCellRange(sheet, new CellRangeAddress(0, 0, 0, NUM_OF_COLUMNS - 1));
        ChartDataSource<Number> ys1 = DataSources.fromNumericCellRange(sheet, new CellRangeAddress(1, 1, 0, NUM_OF_COLUMNS - 1));
        ChartDataSource<Number> ys2 = DataSources.fromNumericCellRange(sheet, new CellRangeAddress(2, 2, 0, NUM_OF_COLUMNS - 1));

        data.addSerie(xs, ys1);
        data.addSerie(xs, ys2);
        chart.plot(data, bottomAxis, leftAxis);

        // Write the output to a file
        FileOutputStream fileOut = null;
        try {
            fileOut = new FileOutputStream("Data/chart-year.xlsx");
            wb.write(fileOut);
            fileOut.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public int getPopulation(String country, int year) {
        Cell cell = findCell(country, year, 0);
        if (cell != null)
            return (int) (getDoubleValue(cell) * 1000);
        return 0;
    }

    private double getDoubleValue(Cell c) {
        switch (c.getCellType()) {
            case 0:
                return c.getNumericCellValue();
            case 1:
                return Double.parseDouble(c.getStringCellValue());
        }
        return 0;
    }

    private int getIntValue(Cell c) {
        switch (c.getCellType()) {
            case 0:
                return (int) c.getNumericCellValue();
            case 1:
                return Integer.parseInt(c.getStringCellValue());
        }
        return 0;
    }

    private Cell findCell(String country, int year, int sheetNumber) {
        XSSFSheet e = this.workbook.getSheetAt(sheetNumber);
        Iterator rowIterator = e.iterator();
        while (rowIterator.hasNext()) {
            try {
                Row r = (Row) rowIterator.next();
                Cell c = r.getCell(2);
                if (c.getStringCellValue().equals(country)) {
                    Cell res = r.getCell(year2Col.get(year));
                    return res;
                }
            } catch (Exception ee) {

            }
        }
        return null;
    }

    public void setPopulation(String country, int year, double population) {
        Cell cell = findCell(country, year, 0);
        if (cell != null) {
            cell.setCellValue(population);
            try {
                FileOutputStream fos = new FileOutputStream(ex);
                workbook.write(fos);
                fos.close();
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
}
