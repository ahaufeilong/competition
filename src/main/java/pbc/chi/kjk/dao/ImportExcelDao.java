package pbc.chi.kjk.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import pbc.chi.kjk.pojo.Question;


/**
 * 导入excel
 * @author 王飞龙
 *
 */
public interface ImportExcelDao {
	/**
	 * 获取导入的Excel表中数据
     * @param file 文件
     * @param req 
     * @param resp
     * @return 返回集合
     */
    public List<Question> importExcelWithSimple(MultipartFile file,HttpServletRequest req,HttpServletResponse resp);
}
