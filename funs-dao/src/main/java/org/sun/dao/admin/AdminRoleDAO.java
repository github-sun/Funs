package org.sun.dao.admin;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.transaction.annotation.Transactional;
import org.sun.model.admin.AdminRole;
import org.sun.model.admin.bo.AdminRoleBO;

/**
* @author sun 
* @date Jan 15, 2018 9:17:40 AM
* 
*/

@Mapper
public interface AdminRoleDAO {
	
	@Select("SELECT ADMIN_ID as adminId, ROLE_ID as roleId  FROM ADMIN_ROLE WHERE ADMIN_ID = #{adminId} and ROLE_ID = #{roleId}")
	AdminRole queryById(@Param("adminId") int adminId, @Param("roleId") int roleId);
	
	@Select("SELECT ADMIN_ID as adminId, ROLE_ID as roleId  FROM ADMIN_ROLE")
	List<AdminRole> queryAll();
	
	@Insert("INSERT INTO ADMIN_ROLE(ADMIN_ID,ROLE_ID) VALUES(#{adminId}, #{roleId})")
	@Transactional
	int insert(AdminRole model);
	
	@Delete("DELETE FROM ADMIN_ROLE WHERE ADMIN_ID =#{adminId} and ROLE_ID =#{roleId}")
	@Transactional
	int deleteById(@Param("adminId") int adminId, @Param("roleId") int roleId);
	
	@Update("UPDATE ADMIN_ROLE SET ADMIN_ID=#{adminId},ROLE_ID=#{roleId}")
	@Transactional
	int update(AdminRole model);
	
	@Select("SELECT a.ADMIN_ID as adminId, a.ROLE_ID as roleId, b.USER_NAME as username, c.ROLE_NAME as rolename FROM ADMIN_ROLE a, ADMIN b, ROLE c WHERE a.ADMIN_ID=b.ID and a.ROLE_ID=c.ID")
	List<AdminRoleBO> query();
}
