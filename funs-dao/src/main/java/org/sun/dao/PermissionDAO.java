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
import org.sun.model.Permission;

/**
* @author sun 
* @date Jan 15, 2018 9:17:40 AM
* 
*/

@Mapper
public interface PermissionDAO {
	
	@Select("SELECT ID as id,PERMISSION_NAME as name, PERMISSION_CODE as code, CREATE_DATE as createDate, UPDATE_DATE as updateDate FROM PERMISSION WHERE ID = #{id}")
	Permission queryById(@Param("id") int id);
	
	@Select("SELECT ID as id,PERMISSION_NAME as name, PERMISSION_CODE as code, CREATE_DATE as createDate, UPDATE_DATE as updateDate FROM PERMISSION ORDER BY UPDATE_DATE DESC")
	List<Permission> queryAll();
	
	@Insert("INSERT INTO PERMISSION(ID,PERMISSION_NAME,PERMISSION_CODE,CREATE_DATE,UPDATE_DATE) VALUES(#{id}, #{name},#{code},#{createDate},#{updateDate})")
	@Options(useGeneratedKeys = true, keyProperty = "id", keyColumn="id")
	@Transactional
	int insert(Permission model);
	
	@Delete("DELETE FROM PERMISSION WHERE ID =#{id}")
	@Transactional
	int deleteById(@Param("id") int id);
	
	@Update("UPDATE PERMISSION SET PERMISSION_NAME=#{name},PERMISSION_CODE=#{code},UPDATE_DATE=#{updateDate} WHERE ID=#{id}")
	@Transactional
	int update(Permission model);
}
