package org.sun.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.transaction.annotation.Transactional;
import org.sun.model.RolePermission;
import org.sun.model.bo.AdminRoleBO;

/**
* @author sun 
* @date Jan 15, 2018 9:17:40 AM
* 
*/

@Mapper
public interface RolePermissionDAO {
	
	@Select("SELECT ROLE_ID as roleId, PERMISSION_ID as permissionId  FROM ROLE_PERMISSION WHERE ROLE_ID = #{roleId} and PERMISSION_ID = #{permissionId}")
	RolePermission queryById(@Param("adminId") int adminId, @Param("roleId") int roleId);
	
	@Select("SELECT ROLE_ID as roleId, PERMISSION_ID as permissionId  FROM ROLE_PERMISSION")
	List<RolePermission> queryAll();
	
	@Insert("INSERT INTO ROLE_PERMISSION(ROLE_ID,PERMISSION_ID) VALUES(#{roleId}, #{permissionId})")
	@Transactional
	int insert(RolePermission model);
	
	@Delete("DELETE FROM ROLE_PERMISSION WHERE ROLE_ID =#{roleId} and PERMISSION_ID =#{permissionId}")
	@Transactional
	int deleteById(@Param("roleId") int roleId, @Param("permissionId") int permissionId);
	
	@Update("UPDATE ROLE_PERMISSION SET ROLE_ID=#{roleId},PERMISSION_ID=#{permissionId}")
	@Transactional
	int update(RolePermission model);
	
	@Select("SELECT a.ADMIN_ID as adminId, a.ROLE_ID as roleId, b.USER_NAME as username, c.ROLE_NAME as rolename FROM ADMIN_ROLE a, ADMIN b, ROLE c WHERE a.ADMIN_ID=b.ID and a.ROLE_ID=c.ID")
	List<AdminRoleBO> query();
}
