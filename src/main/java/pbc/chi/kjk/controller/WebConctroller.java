package pbc.chi.kjk.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import pbc.chi.kjk.dao.ImportExcelDao;
import pbc.chi.kjk.pojo.Question;
import pbc.chi.kjk.service.QuestionService;

@Controller
public class WebConctroller {


	@Autowired
    private ImportExcelDao importExcelDao;
	@Autowired
    private QuestionService questionService;
	
	/**
	 * 首页
	 * @param model
	 * @return
	 */
	@RequestMapping("index")
	public String index(Model model) {
		return "index";
	}
	
	/**
	 * 管理页面
	 * @param model
	 * @return
	 */
	@RequestMapping("manage")
	public String manage(Model model) {
		return "manage";
	}
	
	/**
	 * 跳转至抽题页面	
	 * @param flag
	 * @param model
	 * @return
	 */
	@RequestMapping("select/{flag}")
	public String selecttype(@PathVariable Integer flag,Model model) {
		String qutype="";
		if(flag==1) {
			qutype="必答题";
		}else if(flag==2) {
			qutype="抢答题";
		}else {
			qutype="风险题";
		}
		model.addAttribute("qutype", qutype);
		model.addAttribute("flag", flag);
		return "select";
	}
	
	/**
	 * 试题展示页面
	 * @param model
	 * @return
	 */
	@RequestMapping("questionshow")
	public String questionshow(HttpServletRequest request,Model model) {
		String qutype="";
		String que_num = request.getParameter("id");
		Integer flag = Integer.parseInt(request.getParameter("flag"));
		if(flag==1) {
			qutype="必答题";
		}else if(flag==2) {
			qutype="抢答题";
		}else {
			qutype="风险题";
		}
		Question qu = questionService.findQuestion(Integer.parseInt(que_num));
		//将题库中该题出除
		questionService.update(qu);
		model.addAttribute("qu", qu);
		model.addAttribute("qutype", qutype);
		model.addAttribute("flag", flag);
		return "questionshow";
	}
	
	
	/**
	 * 导入试题库
	 * @param excel
	 * @param req
	 * @param resp
	 * @return
	 */
	@RequestMapping(value="importExcel",method=RequestMethod.POST)
	@ResponseBody
    public String importExcel(MultipartFile excel,HttpServletRequest req,HttpServletResponse resp) {
		List<Question> list = importExcelDao.importExcelWithSimple(excel, req, resp);
        if(list == null || list.size() == 0 ) {
            return "fail";
        }
              
        //批量插入list到数据库
        for(int i=0;i<list.size();i++) {
        	System.out.println((i+1)+"==="+list.get(i).getQue_num()+"---"+list.get(i).getQue_title()+"---"+list.get(i).getFenzhi());
        }
        return "success";
    }

	
	/**
	    * 每次获取最新的题库试题，出除已被抽到的试题
	 * @param req
	 * @param resp
	 * @return
	 */
	@RequestMapping(value="getQuestionListExselect",method=RequestMethod.POST)
	@ResponseBody
    public List<Question> getQuestionListExselect(HttpServletRequest req,HttpServletResponse resp) {
		Integer flag = Integer.parseInt(req.getParameter("flag"));
		List<Question> qlist = questionService.getQuestionList(flag);		
        return qlist;
    }

}
