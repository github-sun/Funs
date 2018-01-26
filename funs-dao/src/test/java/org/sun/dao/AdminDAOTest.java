package org.sun.dao;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.sun.model.admin.Admin;

/**
* @author sun 
* @date Jan 15, 2018 10:19:55 AM
* 
*/

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class AdminDAOTest {
	
	@Autowired
	private AdminDAO adminDAO;
	
	@Test
	public void queryById() {
		Admin model = new Admin();
		model.setUsername("admin");
		model.setPassword("111111");
		model.setCreateDate(new Date());
		model.setUpdateDate(new Date());
		Assert.assertEquals(true, adminDAO.insert(model) > 0);
		Assert.assertNotNull(adminDAO.queryById(model.getId()));
	}
	
	@Test
	public void queryAll() {
		insert();
		Assert.assertNotNull(adminDAO.queryAll());
	}

	@Test
	public void insert() {
		Admin model = new Admin();
		model.setUsername("admin");
		model.setPassword("111111");
		model.setCreateDate(new Date());
		model.setUpdateDate(new Date());
		Assert.assertEquals(true, adminDAO.insert(model) > 0);
	}
	
	@Test
	public void deleteById() {
		Admin model = new Admin();
		model.setUsername("admin");
		model.setPassword("111111");
		model.setCreateDate(new Date());
		model.setUpdateDate(new Date());
		Assert.assertEquals(true, adminDAO.insert(model) > 0);
		Assert.assertEquals(1, adminDAO.deleteById(model.getId()));
	}
	
	@Test
	public void update() {
		Admin model = new Admin();
		model.setUsername("admin");
		model.setPassword("111111");
		model.setCreateDate(new Date());
		model.setUpdateDate(new Date());
		Assert.assertEquals(true, adminDAO.insert(model) > 0);
		
		model.setPassword("22222");
		
		Assert.assertEquals(1, adminDAO.update(model));
	}
}
