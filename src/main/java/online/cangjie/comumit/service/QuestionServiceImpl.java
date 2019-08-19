package online.cangjie.comumit.service;

import online.cangjie.comumit.dao.QuestionDao;
import online.cangjie.comumit.interfaces.service.QuestionService;
import online.cangjie.comumit.po.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service("questionService")
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    private QuestionDao questionDao;

    @Override
    public void addQuestionService(Question question) {
        questionDao.insertQuestion(question);
    }

    @Override
    @Transactional
    public Map getQuestionById(Integer id) {
        questionDao.updateViewByQuestionId(id);
        return questionDao.queryQuestionByQuestionId(id);
    }
}
