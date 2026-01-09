package kz.hawk.risesecurity.dao;


import kz.hawk.risesecurity.model.dao.UserDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

public interface UserDaoTest {

  @Insert("insert into users " +
      "(id, email, password, first_name, last_name, role, status) " +
      "values (#{user.id}, #{user.email}, #{user.password}, #{user.firstName}, #{user.lastName}, #{user.role}, #{user.status});")
  void saveUser(@Param("user") UserDto user);

}
