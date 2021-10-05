package kz.hawk.risesecurity.util;

import kz.hawk.risesecurity.RiseSecurityApplication;
import kz.hawk.risesecurity.dao.BeanConfigTestDao;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Import;

@Import({RiseSecurityApplication.class})
@MapperScan(basePackageClasses = BeanConfigTestDao.class)
public class BeanConfigTests {
}
