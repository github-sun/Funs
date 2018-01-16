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
import org.sun.model.Admin;

/**
* @author sun 
* @date Jan 15, 2018 9:17:40 AM
* 
*/

@Mapper
public interface AdminDAO {
	
	@Select("SELECT ID as id,USER_NAME as username, PASSWORD as password, CREATE_DATE as createDate, UPDATE_DATE as updateDate FROM ADMIN WHERE ID = #{id}")
	Admin queryById(@Param("id") int id);
	
	@Select("SELECT ID as id,USER_NAME as username, PASSWORD as password, CREATE_DATE as createDate, UPDATE_DATE as updateDate FROM ADMIN ORDER BY UPDATE_DATE DESC")
	List<Admin> queryAll();
	
	@Insert("INSERT INTO ADMIN(ID,USER_NAME,PASSWORD,CREATE_DATE,UPDATE_DATE) VALUES(#{id}, #{username},#{password}, #{createDate},#{updateDate})")
	@Options(useGeneratedKeys = true, keyProperty = "id", keyColumn="id")
	@Transactional
	int insert(Admin model);
	
	@Delete("DELETE FROM ADMIN WHERE ID =#{id}")
	@Transactional
	int deleteById(@Param("id") int id);
	
	@Update("UPDATE ADMIN SET USER_NAME=#{username},PASSWORD=#{password},UPDATE_DATE=#{updateDate} WHERE ID=#{id}")
	@Transactional
	int update(Admin model);
}