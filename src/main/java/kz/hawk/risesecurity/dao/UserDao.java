package kz.hawk.risesecurity.dao;

import kz.hawk.risesecurity.model.dao.UserDto;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserDao {

  @Select(
    "select * from users where email = #{email}"
  )
  UserDto getByEmail(@Param("email") String email);

}
