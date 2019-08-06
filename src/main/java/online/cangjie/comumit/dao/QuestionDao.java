package online.cangjie.comumit.dao;

import online.cangjie.comumit.po.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface QuestionDao {
    @Insert("insert into question(title, description, gmt_create, gmt_modified, creator, tag) value(#{title}, #{description}, #{gmt_create}, #{gmt_modified}, #{creator}, #{tag})")
    Integer insertQuestion(Question question);

    @Select("select question.id, title, question.gmt_create, comment_count, view_count, like_count, avatar_url from question join user on creator = user.id")
    List<Map> queryAllQuestion();
}
