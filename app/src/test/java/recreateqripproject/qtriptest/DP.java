package recreateqripproject.qtriptest;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class DP {

    public FileInputStream Input;
    public FileOutputStream output;
    public XSSFWorkbook workbook;
    public XSSFSheet sheet;
    public XSSFRow row;
    public XSSFCell cell;

    String path = System.getProperty("user.dir") + "/src/test/resources/DatasetsforQTrip.xlsx";
    public int getRowCount(String sheetName) throws IOException
    {
        Input=new FileInputStream(path);
        workbook=new XSSFWorkbook(Input);
        sheet = workbook.getSheet(sheetName);
        int row =sheet.getLastRowNum();
        workbook.close();
        Input.close();
        return row;
    }

    public int getColumnCount(String sheetName, int row_num) throws IOException
    {
        Input=new FileInputStream(path);
        workbook=new XSSFWorkbook(Input);
        sheet = workbook.getSheet(sheetName);
        row=sheet.getRow(row_num);
        int cell_count =row.getLastCellNum();
        workbook.close();
        Input.close();
        return cell_count;
    }

    public String getCellData(String sheetName, int row_num, int column) throws IOException
    {
        Input=new FileInputStream(path);
        workbook=new XSSFWorkbook(Input);
        sheet = workbook.getSheet(sheetName);
        row=sheet.getRow(row_num);
        cell=row.getCell(column);
        DataFormatter formatter = new DataFormatter();
        String data;
        try
        {
            data =formatter.formatCellValue(cell);
        }
        catch(Exception e)
        {
            data="";
        }
        workbook.close();
        Input.close();
        return data;
    }

    @DataProvider (name = "data-provider1")
    public Object[][] dpMethod (Method m) throws IOException{
        System.out.println("DP executed for test case : "+m.getName());
        int total_rows=getRowCount(m.getName());
        int total_columns=getColumnCount(m.getName(), 1);

        String testcaseData[][] = new String[total_rows][total_columns-1];

        for(int i=1 ; i<=total_rows; i++){
            for(int j=1; j<total_columns; j++){
                testcaseData[i-1][j-1]= getCellData(m.getName(), i, j);
            }
        }
        System.out.println(Arrays.deepToString(testcaseData));
        return testcaseData;
    }
}
