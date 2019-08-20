package online.cangjie.comumit.dao;

import online.cangjie.comumit.po.Question;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface QuestionDao {
    @Insert("insert into question(title, description, gmt_create, gmt_modified, creator, tag) value(#{title}, #{description}, #{gmt_create}, #{gmt_modified}, #{creator}, #{tag})")
    Integer insertQuestion(Question question);

    @Select("select question.id, title, question.gmt_create, comment_count, view_count, like_count, avatar_url, user.id as userId from question join user on creator = user.id order by gmt_create desc limit #{startPage}, #{pageSize}")
    List<Map> queryAllQuestion(@Param("startPage") Integer startPage, @Param("pageSize") Integer pageSize);

    @Select("<script>select COUNT(1) from question <where><if test=\"id != null\">creator = #{id}</if><if test=\"message != null\">title like '%${message}%'</if></where></script>")
    Integer queryQuestionCount(@Param("id") Integer id, @Param("message") String message);

    @Select("select question.id, title, question.gmt_create, comment_count, view_count, like_count, avatar_url, user.id as userId from question join user on creator = user.id where user.id = #{id} order by gmt_create limit #{startPage}, #{pageSize}")
    List<Map> queryQuestionById(@Param("id") Integer id, @Param("startPage") Integer startPage, @Param("pageSize") Integer pageSize);

    @Select("<script>select question.id, title, question.gmt_create, comment_count, view_count, like_count, avatar_url, user.id as userId from question join user on creator = user.id <where> <if test=\"message != null or message != ''\"> title like '%${message}%'</if> </where> order by gmt_create limit #{startPage}, #{pageSize}</script>")
    List<Map> queryQuestionByMessage(@Param("message") String message, @Param("startPage") Integer startPage, @Param("pageSize") Integer pageSize);

    @Select("select question.id, title, question.gmt_create, comment_count, view_count, like_count, avatar_url, user.id as userId from question join user on creator = user.id where question.id = #{id}")
    Map queryQuestionByQuestionId(Integer id);

    @Update("update question set view_count = view_count + 1 where id = #{id}")
    void updateViewByQuestionId(Integer id);

    @Select("select id, title from question order by like_count limit 0, 5")
    List<Question> queryTitleAndIdByLikeCount();

}
