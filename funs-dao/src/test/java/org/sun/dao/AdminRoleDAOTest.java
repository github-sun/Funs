package org.sun.dao;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.sun.model.Admin;
import org.sun.model.AdminRole;
import org.sun.model.Role;

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
	public void queryAll() {
		insert();
		Assert.assertNotNull(adminRoleDAO.queryAll());
	}

	@Test
	public void insert() {
		Admin admin = new Admin();
		admin.setUsername("admin");
		admin.setPassword("111111");
		admin.setCreateDate(new Date());
		admin.setUpdateDate(new Date());
		Assert.assertEquals(1, adminDAO.insert(admin));
		
		Role role = new Role();
		role.setRolename("system");
		role.setCreateDate(new Date());
		role.setUpdateDate(new Date());
		Assert.assertEquals(1, roleDAO.insert(role));
		
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
		Assert.assertEquals(1, adminDAO.insert(admin));
		
		Role role = new Role();
		role.setRolename("system");
		role.setCreateDate(new Date());
		role.setUpdateDate(new Date());
		Assert.assertEquals(1, roleDAO.insert(role));
		
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
		Assert.assertEquals(1, adminDAO.insert(admin));
		
		Role role = new Role();
		role.setRolename("system");
		role.setCreateDate(new Date());
		role.setUpdateDate(new Date());
		Assert.assertEquals(1, roleDAO.insert(role));
		
		AdminRole adminRole = new AdminRole();
		adminRole.setAdminId(admin.getId());
		adminRole.setRoleId(role.getId());
		Assert.assertEquals(1, adminRoleDAO.insert(adminRole));
		
		role = new Role();
		role.setRolename("admin");
		role.setCreateDate(new Date());
		role.setUpdateDate(new Date());
		Assert.assertEquals(1, roleDAO.insert(role));
		
		adminRole.setRoleId(role.getId());
		Assert.assertEquals(1, adminRoleDAO.update(adminRole));
	}
}
