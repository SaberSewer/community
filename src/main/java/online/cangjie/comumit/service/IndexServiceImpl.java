package online.cangjie.comumit.service;

import online.cangjie.comumit.dao.QuestionDao;
import online.cangjie.comumit.interfaces.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("indexService")
public class IndexServiceImpl implements IndexService {
    @Autowired
    private QuestionDao questionDao;
    @Override
    public List<Map> getAllQuestion() {

        return questionDao.queryAllQuestion();
    }
}
