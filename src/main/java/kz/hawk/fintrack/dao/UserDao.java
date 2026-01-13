package kz.hawk.fintrack.dao;

import kz.hawk.fintrack.model.dao.UserDto;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserDao {

  @Select(
    "select * from users where email = #{email}"
  )
  UserDto getByEmail(@Param("email") String email);

  @Select(
    "select exists(select 1 from users where email = #{email})"
  )
  boolean checkEmailExists(@Param("email") String email);

  @Insert("insert into users " +
    "(email, password_hash, first_name, last_name, role) " +
    "values (#{email}, #{passwordHash}, #{firstName}, #{lastName}, #{role})")
  @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
  void insertUser(UserDto user);

}
