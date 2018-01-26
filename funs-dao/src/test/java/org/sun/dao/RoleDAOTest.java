package org.sun.dao;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.sun.dao.admin.RoleDAO;
import org.sun.model.admin.Role;

/**
* @author sun 
* @date Jan 15, 2018 10:19:55 AM
* 
*/

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class RoleDAOTest {
	
	@Autowired
	private RoleDAO roleDAO;
	
	@Test
	public void queryById() {
		Role model = new Role();
		model.setRolename("system");
		model.setCreateDate(new Date());
		model.setUpdateDate(new Date());
		Assert.assertEquals(true, roleDAO.insert(model) > 0);
		Assert.assertNotNull(roleDAO.queryById(model.getId()));
	}
	
	@Test
	public void queryAll() {
		insert();
		Assert.assertNotNull(roleDAO.queryAll());
	}

	@Test
	public void insert() {
		Role model = new Role();
		model.setRolename("system");
		model.setCreateDate(new Date());
		model.setUpdateDate(new Date());
		Assert.assertEquals(true, roleDAO.insert(model) > 0);
	}
	
	@Test
	public void deleteById() {
		Role model = new Role();
		model.setRolename("system");
		model.setCreateDate(new Date());
		model.setUpdateDate(new Date());
		Assert.assertEquals(true, roleDAO.insert(model) > 0);
		Assert.assertEquals(1, roleDAO.deleteById(model.getId()));
	}
	
	@Test
	public void update() {
		Role model = new Role();
		model.setRolename("system");
		model.setCreateDate(new Date());
		model.setUpdateDate(new Date());
		Assert.assertEquals(true, roleDAO.insert(model) > 0);
		
		model.setRolename("admin");
		
		Assert.assertEquals(1, roleDAO.update(model));
	}
}
