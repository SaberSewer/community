package online.cangjie.comumit.service;

import online.cangjie.comumit.dao.QuestionDao;
import online.cangjie.comumit.interfaces.service.ProfileService;
import online.cangjie.comumit.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("profileService")
public class ProfileServiceImpl implements ProfileService {
    @Value("${page.size}")
    private Integer size;
    @Autowired
    private QuestionDao questionDao;

    @Override
    public PageUtil myQuestion(Integer id, Integer pageNo) {
        if(pageNo == null){
            pageNo = 1;
        }
        List<Map> questionList = questionDao.queryQuestionById(id, (pageNo - 1) * size, size);
        Integer count = questionDao.queryQuestionCount(id, null);
        StringBuffer stringBuffer = new StringBuffer("/profile/questions?userId=");
        stringBuffer.append(id);
        PageUtil pageUtil = new PageUtil(new String(stringBuffer), count, size, pageNo);
        pageUtil.setPageData(questionList);
        return pageUtil;
    }
}
