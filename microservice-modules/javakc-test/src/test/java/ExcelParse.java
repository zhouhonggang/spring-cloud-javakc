import com.zhou.javakc.component.data.entity.test.Test;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhou
 * @version v0.0.1
 * @date 2019-10-21 13:42
 */
public class ExcelParse {

    public static void main(String[] args) throws Exception {
        //1.模拟获取到用户上传的excel文档
        String path = "C:\\Users\\thinkpad\\Desktop\\b.xlsx";
        File file = new File(path);
        if(file.exists())
        {
            InputStream input = new FileInputStream(file);
            //1.根据用户上传的excel文档, 动态创建响应的解析对象
            Workbook workbook = null;
            if(path.endsWith("xlsx"))
            {
                //version 07+
                workbook = new XSSFWorkbook(input);
                //Name: /xl/workbook.xml - Content Type: application/vnd.openxmlformats-officedocument.spreadsheetml.sheet.main+xml
            }
            else
            {
                //version 97-03
                workbook = new HSSFWorkbook(input);
                //org.apache.poi.hssf.usermodel.HSSFWorkbook@86be70a
            }
            //2.准备从excel中进行数据提取
            //excel中sheets的总个数
            int sheets = workbook.getNumberOfSheets();

            List<Test> list = new ArrayList<>();

            for(int i=0;i<sheets;i++)
            {
                Sheet sheet = workbook.getSheetAt(i);
                //从sheet中获取总行数
                int rows = sheet.getPhysicalNumberOfRows();
                for(int j=1;j<rows;j++)
                {
                    Row row = sheet.getRow(j);
                    //从row中获取总列数
//                    int cells = row.getPhysicalNumberOfCells();
//                    System.out.println( cells );
                    Cell cell1 = row.getCell(0);
                    Cell cell2 = row.getCell(1);
                    Cell cell3 = row.getCell(2);
                    Cell cell4 = row.getCell(3);
                    Cell cell5 = row.getCell(4);
//                    System.out.println( cell1.getStringCellValue() +" : "+cell2.getNumericCellValue()
//                            +" : "+cell3.getNumericCellValue() +" : "+cell4.getDateCellValue()  );
                    Test entity = new Test();
                    entity.setAddress(cell5.getStringCellValue());
                    entity.setAge((int)cell2.getNumericCellValue());
                    entity.setBirthday(cell4.getDateCellValue());
                    entity.setName(cell1.getStringCellValue());
                    entity.setSex((int)cell3.getNumericCellValue());

                    list.add(entity);
                }
            }
        }
        else
        {
            System.out.println("文件不存在...");
        }
    }

}
