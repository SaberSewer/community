package online.cangjie.comumit.service;

import online.cangjie.comumit.dao.QuestionDao;
import online.cangjie.comumit.interfaces.service.IndexService;
import online.cangjie.comumit.po.Question;
import online.cangjie.comumit.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service("indexService")
public class IndexServiceImpl implements IndexService {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Value("${page.size}")
    private Integer size;
    @Autowired
    private QuestionDao questionDao;

    @Override
    public PageUtil getAllQuestion(Integer pageNo) {
        //获取要展示的数据
        if (pageNo == null) {
            pageNo = 1;
        }
        List<Map> questionList = questionDao.queryAllQuestion((pageNo - 1) * size, size);
        PageUtil pageUtil = new PageUtil("/?", questionDao.queryQuestionCount(null, null), size, pageNo);
        pageUtil.setPageData(questionList);
        return pageUtil;
    }

    @Override
    public PageUtil getQuestionBy(String message, Integer pageNo) {
        if (pageNo == null) {
            pageNo = 1;
        }
        StringBuffer stringBuffer = new StringBuffer("/searcher?message=");
        if (message != null) {
            stringBuffer.append(message);
        }
        List<Map> questionList = questionDao.queryQuestionByMessage(message, (pageNo - 1) * size, size);
        PageUtil pageUtil = new PageUtil(new String(stringBuffer), questionDao.queryQuestionCount(null, message), size, pageNo);
        pageUtil.setPageData(questionList);
        return pageUtil;
    }

    @Override
    public List<Question> getHotCat() {
        if (stringRedisTemplate.hasKey("hot_title")) {
            List<Question> list = new ArrayList<Question>();
            Map<Object, Object> map = stringRedisTemplate.opsForHash().entries("hot_title");
            map.entrySet().forEach(entry -> {
                Question question = new Question();
                question.setId(Integer.parseInt((String) entry.getKey()));
                question.setTitle((String) entry.getValue());
                list.add(question);
            });
            return list;
        } else {
            List<Question> list = questionDao.queryTitleAndIdByLikeCount();
            list.forEach(question -> {
                stringRedisTemplate.opsForHash().put("hot_title", String.valueOf(question.getId()), question.getTitle());
                stringRedisTemplate.expire("hot_title", 1800, TimeUnit.SECONDS);
            });
            return list;
        }
    }
}
