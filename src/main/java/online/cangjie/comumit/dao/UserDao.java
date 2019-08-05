package online.cangjie.comumit.dao;

import online.cangjie.comumit.po.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserDao {
    @Insert("insert into user(id, access_id, name, token, gmt_create, gmt_ified) value(#{id}, #{access_id}, #{name}, #{token}, #{gmt_create}, #{gmt_ified})")
    Integer insertUser(User user);

    @Select("select * from user where access_id = #{access_id}")
    User queryById(String access_id);

    @Update("update user set token = #{token} where access_id = #{access_id}")
    Integer updateTokenByAccess_id(String token, String access_id);
}
