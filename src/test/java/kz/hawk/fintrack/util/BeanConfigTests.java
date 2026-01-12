package kz.hawk.fintrack.util;

import kz.hawk.fintrack.FinTrackApplication;
import kz.hawk.fintrack.dao.BeanConfigTestDao;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Import;

@Import({FinTrackApplication.class})
@MapperScan(basePackageClasses = BeanConfigTestDao.class)
public class BeanConfigTests {
}
