package online.cangjie.comumit.interfaces.service;

import online.cangjie.comumit.po.Question;
import online.cangjie.comumit.utils.PageUtil;

import java.util.List;

public interface IndexService {
    PageUtil getAllQuestion(Integer pageNo);

    PageUtil getQuestionBy(String message, Integer pageNo);

    List<Question> getHotCat();
}
