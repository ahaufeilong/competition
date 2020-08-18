package pbc.chi.kjk.dao;

import java.util.List;

import pbc.chi.kjk.pojo.Question;

public interface QuestionDao {
	
	public List<Question> getQuestionList(Integer flag) ;
	public Question findQuestion(int que_num);
	public int add(Question qu);
	public int update(Question qu);

}
