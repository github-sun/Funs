package org.sun.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.transaction.annotation.Transactional;
import org.sun.model.Role;

/**
* @author sun 
* @date Jan 15, 2018 9:17:40 AM
* 
*/

@Mapper
public interface RoleDAO {
	
	@Select("SELECT * FROM ROLE ORDER BY UPDATE_DATE DESC")
	List<Role> queryAll();
	
	@Insert("INSERT INTO ROLE(ID,ROLE_NAME,CREATE_DATE,UPDATE_DATE) VALUES(#{id}, #{rolename},#{createDate},#{updateDate})")
	@Options(useGeneratedKeys = true, keyProperty = "id", keyColumn="id")
	@Transactional
	int insert(Role model);
	
	@Delete("DELETE FROM ROLE WHERE ID =#{id}")
	@Transactional
	int deleteById(@Param("id") int id);
	
	@Update("UPDATE ROLE SET ROLE_NAME=#{rolename},CREATE_DATE=#{createDate},UPDATE_DATE=#{updateDate} WHERE ID=#{id}")
	@Transactional
	int update(Role model);
}
