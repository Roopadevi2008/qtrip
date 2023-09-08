package qtriptest;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.*;
import org.apache.poi.ss.usermodel.DataFormatter;

public class DP {

    @DataProvider(name="TestCase01")
    public Object[][] TestCase01() throws IOException
    {
        String filePath = System.getProperty("user.dir")+"/src/test/resources/DatasetsforQTrip.xlsx";
        String sheetName = "TestCase01";
        File file = new File(filePath);
        FileInputStream FileInputStream = new FileInputStream(file);
        XSSFWorkbook workbook = new XSSFWorkbook(FileInputStream);
        XSSFSheet worksheet = workbook.getSheet(sheetName);
        XSSFRow row = worksheet.getRow(0);
        XSSFCell cell;
        DataFormatter formatter = new DataFormatter();
        int rowCount = worksheet.getLastRowNum();
        int colCount = row.getPhysicalNumberOfCells();
        Object data[][] = new Object[rowCount][colCount-1];
        int dataRowIndex=0;
        int dataColIndex=0;
        for(int rowIndex=1;rowIndex<=rowCount;rowIndex++)
        {
            row=worksheet.getRow(rowIndex);
            dataColIndex=0;
            for(int cellIndex=1;cellIndex<colCount;cellIndex++)
            {
                cell=row.getCell(cellIndex);
                data[dataRowIndex][dataColIndex] = formatter.formatCellValue(cell);
                System.out.print(cell+"|");
                dataColIndex++;
            }
            dataRowIndex++;
        }
        return data;
    }
    @DataProvider(name="TestCase02")
    public Object[][] TestCase02() throws IOException
    {
        String filePath = System.getProperty("user.dir")+"/src/test/resources/DatasetsforQTrip.xlsx";
        String sheetName = "TestCase02";
        File file = new File(filePath);
        FileInputStream FileInputStream = new FileInputStream(file);
        XSSFWorkbook workbook = new XSSFWorkbook(FileInputStream);
        XSSFSheet worksheet = workbook.getSheet(sheetName);
        XSSFRow row = worksheet.getRow(0);
        XSSFCell cell;
        DataFormatter formatter = new DataFormatter();
        int rowCount = worksheet.getLastRowNum();
        int colCount = row.getPhysicalNumberOfCells();
        Object data[][] = new Object[rowCount][colCount-1];
        int dataRowIndex=0;
        int dataColIndex=0;
        for(int rowIndex=1;rowIndex<=rowCount;rowIndex++)
        {
            row=worksheet.getRow(rowIndex);
            dataColIndex=0;
            for(int cellIndex=1;cellIndex<colCount;cellIndex++)
            {
                cell=row.getCell(cellIndex);
                data[dataRowIndex][dataColIndex] = formatter.formatCellValue(cell);
                System.out.print(cell+"|");
                dataColIndex++;
            }
            dataRowIndex++;
        }
        return data;
    }

    @DataProvider(name="TestCase03")
    public Object[][] TestCase03() throws IOException
    {
        String filePath = System.getProperty("user.dir")+"/src/test/resources/DatasetsforQTrip.xlsx";
        String sheetName = "TestCase03";
        File file = new File(filePath);
        FileInputStream FileInputStream = new FileInputStream(file);
        XSSFWorkbook workbook = new XSSFWorkbook(FileInputStream);
        XSSFSheet worksheet = workbook.getSheet(sheetName);
        XSSFRow row = worksheet.getRow(0);
        XSSFCell cell;
        DataFormatter formatter = new DataFormatter();
        int rowCount = worksheet.getLastRowNum();
        int colCount = row.getPhysicalNumberOfCells();
        Object data[][] = new Object[rowCount][colCount-1];
        int dataRowIndex=0;
        int dataColIndex=0;
        for(int rowIndex=1;rowIndex<=rowCount;rowIndex++)
        {
            row=worksheet.getRow(rowIndex);
            dataColIndex=0;
            for(int cellIndex=1;cellIndex<colCount;cellIndex++)
            {
                cell=row.getCell(cellIndex);
                data[dataRowIndex][dataColIndex] = formatter.formatCellValue(cell);
                System.out.print(cell+"|");
                dataColIndex++;
            }
            dataRowIndex++;
        }
        return data;
    }
    @DataProvider(name="TestCase04")
    public Object[][] TestCase04() throws IOException
    {
        String filePath = System.getProperty("user.dir")+"/src/test/resources/DatasetsforQTrip.xlsx";
        String sheetName = "TestCase04";
        File file = new File(filePath);
        FileInputStream FileInputStream = new FileInputStream(file);
        XSSFWorkbook workbook = new XSSFWorkbook(FileInputStream);
        XSSFSheet worksheet = workbook.getSheet(sheetName);
        XSSFRow row = worksheet.getRow(0);
        XSSFCell cell;
        DataFormatter formatter = new DataFormatter();
        int rowCount = worksheet.getLastRowNum();
        int colCount = row.getPhysicalNumberOfCells();
        Object data[][] = new Object[rowCount][colCount-1];
        int dataRowIndex=0;
        int dataColIndex=0;
        for(int rowIndex=1;rowIndex<=rowCount;rowIndex++)
        {
            row=worksheet.getRow(rowIndex);
            dataColIndex=0;
            for(int cellIndex=1;cellIndex<colCount;cellIndex++)
            {
                cell=row.getCell(cellIndex);
                data[dataRowIndex][dataColIndex] = formatter.formatCellValue(cell);
                System.out.print(cell+"|");
                dataColIndex++;
            }
            dataRowIndex++;
        }
        return data;
    }
}

