package pbc.chi.kjk.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import pbc.chi.kjk.dao.QuestionDao;
import pbc.chi.kjk.pojo.Question;

@Repository
public class QuestionService implements QuestionDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<Question> getQuestionList(Integer flag) {
		List<Question> questionList = jdbcTemplate.query("select * from question where flag = ? and status = 0", new Object[] {flag},
				new BeanPropertyRowMapper<>(Question.class));
		if (questionList != null && questionList.size() > 0) {
			return questionList;
		} else {
			return null;
		}
	}
	
	
	@Override
	public Question findQuestion(int que_num) {
		List<Question> questionList = jdbcTemplate.query("select * from question where que_num=? ", new Object[] {que_num},
				new BeanPropertyRowMapper<>(Question.class));
		if (questionList != null && questionList.size() > 0) {
			return questionList.get(0);
		} else {
			return null;
		}
	}
	
	public List<Question> getQuestionAllList() {
		List<Question> questionList = jdbcTemplate.query("select * from question ", new Object[] {},
				new BeanPropertyRowMapper<>(Question.class));
		if (questionList != null && questionList.size() > 0) {
			return questionList;
		} else {
			return null;
		}
	}
	
	@Override
	public int add(Question qu) {
		jdbcTemplate.update("insert into question(que_num,que_title,que_answer,status,backtime,flag,fenzhi) "
			+ "values(?, ?, ?, ?, ?, ?, ?)", qu.getQue_num(),qu.getQue_title(),qu.getQue_answer(),qu.getStatus(),qu.getBacktime(),qu.getFlag(),qu.getFenzhi());
		return this.getQuestionAllList().size();
	}
	
	@Override
	public int update(Question qu) {
		return jdbcTemplate.update("update question set status = 1 where que_id=? ",  qu.getQue_id());
	}

}
