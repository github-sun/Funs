

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.sun.dao.AdminDAOTest;
import org.sun.dao.AdminRoleDAOTest;
import org.sun.dao.RoleDAOTest;

@RunWith(Suite.class)
@SuiteClasses({AdminDAOTest.class, RoleDAOTest.class, AdminRoleDAOTest.class}) 
public class AppSuite {

}
