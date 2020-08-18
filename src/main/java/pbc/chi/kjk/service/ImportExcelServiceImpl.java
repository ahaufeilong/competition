package pbc.chi.kjk.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import pbc.chi.kjk.dao.ImportExcelDao;
import pbc.chi.kjk.pojo.Question;

@Repository
public class ImportExcelServiceImpl extends ImportExcelBaseService implements ImportExcelDao {

	@Override
    public List<Question> importExcelWithSimple(MultipartFile file,HttpServletRequest req,HttpServletResponse resp) {
    	ArrayList<Question> list = new ArrayList<>();
    	try{
    			InputStream inputStream = file.getInputStream();
    	        HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
    	        HSSFSheet sheetAt = workbook.getSheetAt(0);
    	  for (Row row : sheetAt) {
    	             if (row.getRowNum() == 0) {
    	                 continue;
    	             }
    	             
    	             //读取当前行中单元格数据，索引从0开始
    	             row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);  
    	             String que_num = row.getCell(0).getStringCellValue(); 
    	             row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);  
    	             String que_title = row.getCell(1).getStringCellValue();
    	             row.getCell(2).setCellType(Cell.CELL_TYPE_STRING);  
    	             String que_answer = row.getCell(2).getStringCellValue();
    	             row.getCell(3).setCellType(Cell.CELL_TYPE_STRING);  
    	             String que_flag = row.getCell(3).getStringCellValue();
    	             row.getCell(4).setCellType(Cell.CELL_TYPE_STRING);  
    	             String que_status = row.getCell(4).getStringCellValue();
    	             row.getCell(5).setCellType(Cell.CELL_TYPE_STRING);  
    	             String que_time = row.getCell(5).getStringCellValue();
    	             row.getCell(6).setCellType(Cell.CELL_TYPE_STRING);  
    	             String que_fenzhi = row.getCell(6).getStringCellValue();
    	             Question qu = new Question();
    	             qu.setQue_num(Integer.parseInt(que_num));
    	             qu.setQue_title(que_title);
    	             qu.setQue_answer(que_answer);
    	             qu.setFlag(Integer.parseInt(que_flag));
    	             qu.setStatus(Integer.parseInt(que_status));
    	             qu.setBacktime(Integer.parseInt(que_time));
    	             qu.setFenzhi(que_fenzhi);
    	             list.add(qu);
    	         }
    	         //5、关闭流
    	         workbook.close();
    	     } catch (IOException e) {
    	        e.printStackTrace();
    	     }
    	return list;
           
    }
}