import com.aspose.cells.SaveFormat;
import com.aspose.cells.Workbook;

import java.io.File;

/**
 * Created by mohammad hosein on 21/02/2016.
 */
public class Main {
    public static void main(String[] args) {
        //File Address
        String address = "Data/WPP2015_POP_F01_3_TOTAL_POPULATION_FEMALE.xlsx";
        ExcelReader er = new ExcelReader(address);
        int res = er.getPopulation("Djibouti", 1952);
        System.out.println(res);
        er.setPopulation("Djibouti", 1952, 1.7);
        res = er.getPopulation("Djibouti", 1952);
        System.out.println(res);
        er.createChart("Djibouti", 'F');
        Workbook workbook = null;
        try {
            workbook = new Workbook("Data/chart-year.xlsx");
            workbook.save("Data/MyPdfFile.pdf", SaveFormat.PDF);
        } catch (Exception e) {
            e.printStackTrace();
        }

//Save the document in PDF format
    }
}
