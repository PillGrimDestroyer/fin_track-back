package kz.hawk.fintrack.dao;

import kz.hawk.fintrack.model.dao.UserDto;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserDao {

  @Select(
    "select * from users where email = #{email}"
  )
  UserDto getByEmail(@Param("email") String email);

  @Select(
    "select exists(select 1 from users where email = #{email})"
  )
  boolean checkEmailExists(@Param("email") String email);

}
