package kz.hawk.fintrack.dao;

import kz.hawk.fintrack.model.dao.UserDto;
import kz.hawk.fintrack.model.enums.Role;
import org.apache.ibatis.annotations.Insert;
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


  @Insert("insert into users " +
    "(email, password_hash, first_name, last_name, role) " +
    "values (#{email}, #{passwordHash}, #{firstName}, #{lastName}, #{role});")
  void createUser(@Param("email") String email,
                  @Param("passwordHash") String passwordHash,
                  @Param("firstName") String firstName,
                  @Param("lastName") String lastName,
                  @Param("role") Role role);

}
