package org.sun.dao;

import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.sun.model.admin.Admin;
import org.sun.model.admin.AdminRole;
import org.sun.model.admin.Role;

/**
* @author sun 
* @date Jan 15, 2018 10:19:55 AM
* 
*/

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class AdminRoleDAOTest {
	
	@Autowired
	private AdminDAO adminDAO;
	
	@Autowired
	private RoleDAO roleDAO;
	
	@Autowired
	private AdminRoleDAO adminRoleDAO;
	
	@Test
	public void queryById() {
		Admin admin = new Admin();
		admin.setUsername("admin");
		admin.setPassword("111111");
		admin.setCreateDate(new Date());
		admin.setUpdateDate(new Date());
		Assert.assertEquals(true, adminDAO.insert(admin) > 0);
		
		Role role = new Role();
		role.setRolename("system");
		role.setCreateDate(new Date());
		role.setUpdateDate(new Date());
		Assert.assertEquals(true, roleDAO.insert(role) > 0);
		
		AdminRole adminRole = new AdminRole();
		adminRole.setAdminId(admin.getId());
		adminRole.setRoleId(role.getId());
		Assert.assertEquals(1, adminRoleDAO.insert(adminRole));
		
		System.out.println("queryById adminRole "+adminRole.toString());
		AdminRole a = adminRoleDAO.queryById(adminRole.getAdminId(), adminRole.getRoleId());
		System.out.println("queryById a "+a);
	}
	
	@Test
	public void queryAll() {
		insert();
		List<AdminRole> list = adminRoleDAO.queryAll();
		Assert.assertNotNull(adminRoleDAO.queryAll());
		System.out.println("queryAll list "+list);
	}

	@Test
	public void insert() {
		Admin admin = new Admin();
		admin.setUsername("admin");
		admin.setPassword("111111");
		admin.setCreateDate(new Date());
		admin.setUpdateDate(new Date());
		Assert.assertEquals(true, adminDAO.insert(admin) > 0);
		
		Role role = new Role();
		role.setRolename("system");
		role.setCreateDate(new Date());
		role.setUpdateDate(new Date());
		Assert.assertEquals(true, roleDAO.insert(role) > 0);
		
		AdminRole adminRole = new AdminRole();
		adminRole.setAdminId(admin.getId());
		adminRole.setRoleId(role.getId());
		Assert.assertEquals(1, adminRoleDAO.insert(adminRole));
	}
	
	@Test
	public void deleteById() {
		Admin admin = new Admin();
		admin.setUsername("admin");
		admin.setPassword("111111");
		admin.setCreateDate(new Date());
		admin.setUpdateDate(new Date());
		Assert.assertEquals(true, adminDAO.insert(admin) > 0);
		
		Role role = new Role();
		role.setRolename("system");
		role.setCreateDate(new Date());
		role.setUpdateDate(new Date());
		Assert.assertEquals(true, roleDAO.insert(role) > 0);
		
		AdminRole adminRole = new AdminRole();
		adminRole.setAdminId(admin.getId());
		adminRole.setRoleId(role.getId());
		Assert.assertEquals(1, adminRoleDAO.insert(adminRole));
		Assert.assertEquals(1, adminRoleDAO.deleteById(adminRole.getAdminId(), adminRole.getRoleId()));
	}
	
	@Test
	public void update() {
		Admin admin = new Admin();
		admin.setUsername("admin");
		admin.setPassword("111111");
		admin.setCreateDate(new Date());
		admin.setUpdateDate(new Date());
		Assert.assertEquals(true, adminDAO.insert(admin) > 0);
		
		Role role = new Role();
		role.setRolename("system");
		role.setCreateDate(new Date());
		role.setUpdateDate(new Date());
		Assert.assertEquals(true, roleDAO.insert(role) > 0);
		
		AdminRole adminRole = new AdminRole();
		adminRole.setAdminId(admin.getId());
		adminRole.setRoleId(role.getId());
		Assert.assertEquals(true, adminRoleDAO.insert(adminRole) > 0);
		
		role = new Role();
		role.setRolename("admin");
		role.setCreateDate(new Date());
		role.setUpdateDate(new Date());
		Assert.assertEquals(1, roleDAO.insert(role));
		
		adminRole.setRoleId(role.getId());
		Assert.assertEquals(1, adminRoleDAO.update(adminRole));
	}
}
