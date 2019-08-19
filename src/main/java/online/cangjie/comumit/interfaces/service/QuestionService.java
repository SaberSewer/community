package online.cangjie.comumit.interfaces.service;

import online.cangjie.comumit.po.Question;

import java.util.Map;

public interface QuestionService {
    void addQuestionService(Question question);

    Map getQuestionById(Integer id);
}
