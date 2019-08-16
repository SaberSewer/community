package online.cangjie.comumit.service;

import online.cangjie.comumit.dao.QuestionDao;
import online.cangjie.comumit.interfaces.service.IndexService;
import online.cangjie.comumit.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("indexService")
public class IndexServiceImpl implements IndexService {
    @Value("${page.size}")
    private Integer size;
    @Autowired
    private QuestionDao questionDao;
    @Override
    public PageUtil getAllQuestion(Integer pageNo) {
        //获取要展示的数据
        if(pageNo == null){
            pageNo = 1;
        }
        List<Map> questionList = questionDao.queryAllQuestion((pageNo - 1) * size, size);
        PageUtil pageUtil = new PageUtil("/?", questionDao.queryQuestionCount(null, null), size, pageNo);
        pageUtil.setPageData(questionList);
        return pageUtil;
    }

    @Override
    public PageUtil getQuestionBy(String message, Integer pageNo) {
        if(pageNo == null){
            pageNo = 1;
        }
        StringBuffer stringBuffer = new StringBuffer("/searcher?message=");
        if(message != null){
            stringBuffer.append(message);
        }
        List<Map> questionList = questionDao.queryQuestionByMessage(message, (pageNo - 1) * size, size);
        PageUtil pageUtil = new PageUtil(new String(stringBuffer), questionDao.queryQuestionCount(null, message), size, pageNo);
        pageUtil.setPageData(questionList);
        return pageUtil;
    }
}
