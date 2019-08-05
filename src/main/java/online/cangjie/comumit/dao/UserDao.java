package online.cangjie.comumit.dao;

import online.cangjie.comumit.po.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserDao {
    @Insert("insert into user(id, access_id, name, token, gmt_create, gmt_ified) value(#{id}, #{access_id}, #{name}, #{token}, #{gmt_create}, #{gmt_ified})")
    Integer insertUser(User user);

    @Select("select * from user where id = #{access_id}")
    User queryById(String access_id);
}
