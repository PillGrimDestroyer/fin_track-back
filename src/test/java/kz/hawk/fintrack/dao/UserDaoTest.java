package kz.hawk.fintrack.dao;


import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

public interface UserDaoTest {

  @Insert("insert into users " +
      "(email, password_hash, first_name, last_name) " +
      "values (#{email}, #{passwordHash}, #{firstName}, #{lastName});")
  void saveUser(@Param("email") String email,
                @Param("passwordHash") String passwordHash,
                @Param("firstName") String firstName,
                @Param("lastName") String lastName);

}
