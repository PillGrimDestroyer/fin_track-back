package kz.hawk.fintrack.dao;

import kz.hawk.fintrack.model.dao.UserDto;
import org.apache.ibatis.annotations.*;

import java.util.UUID;

@Mapper
public interface UserDao {

  @Select(
    "select * from users where email = #{email}"
  )
  @Results(id = "userResultMap", value = {
    @Result(column = "id", property = "id", id = true),
    @Result(column = "email", property = "email"),
    @Result(column = "password_hash", property = "passwordHash"),
    @Result(column = "first_name", property = "firstName"),
    @Result(column = "last_name", property = "lastName"),
    @Result(column = "role", property = "role"),
    @Result(column = "status", property = "status"),
    @Result(column = "registered_at", property = "registeredAt")
  })
  UserDto getByEmail(@Param("email") String email);

  @Select(
    "select * from users where id = #{userId}"
  )
  @SuppressWarnings("unused")
  UserDto getById(@Param("userId") UUID userId);

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
