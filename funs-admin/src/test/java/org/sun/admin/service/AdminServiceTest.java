package org.sun.admin.service;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.sun.model.admin.Admin;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class AdminServiceTest {
	
	@Autowired
	private AdminService adminService;

	@Test
	public void getAdminById() {
		Admin model = new Admin();
		model.setUsername("admin");
		model.setPassword("111111");
		model.setCreateDate(new Date());
		model.setUpdateDate(new Date());
		Assert.assertEquals(true, adminService.addAdmin(model) > 0);
		Assert.assertNotNull(adminService.getAdminById(model.getId()));
	}
	
}
