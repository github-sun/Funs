   var getAdminRoleDatas = function(){
		$.ajax({
                   type: "GET",
		   url: 'http://localhost:8080/adminrole',
		   success: function(data) {
			jQuery.each(data, function(i, val) {
				 var strHTML = "<tr><td>" + val.adminId + "</td><td>" + val.roleId + "</td><td>"+val.username+"</td><td>"+val.rolename+"</td><td><a onclick='deleteAdminRoleSubmit("+val.adminId+","+val.roleId+")' href='#'>删除</a></td></tr>";
                 $('table#tblUser tbody').append(strHTML);
			});  
		   },
		   statusCode: {
                   
		    404: function() {
		       alert("error");
		    }
		   }
		})
   };
   
   
   var getAdminRoleAdminDatas = function(){
	   var selector_username=$('#item_username'); 
	   var selector_rolename=$('#item_rolename'); 
		$.ajax({
                  type: "GET",
		   url: 'http://localhost:8080/admin',
		   success: function(data) {
				jQuery.each(data, function(i, val) {
					selector_username.append('<option value="'+val.id+'">'+val.username+'</option>');  
				});  
		   },
		   statusCode: {
                  
		    404: function() {
		       alert("error");
		    }
		   }
		})
		
		$.ajax({
                  type: "GET",
		   url: 'http://localhost:8080/role',
		   success: function(data) {
				jQuery.each(data, function(i, val) {
					selector_rolename.append('<option value="'+val.id+'">'+val.rolename+'</option>');  
				});  
		   },
		   statusCode: {
                  
		    404: function() {
		       alert("error");
		    }
		   }
		})
  };

  var getAdminRoleAdminDatasByAdminIdAndRoleId = function(admin_id, role_id){
	   var selector_username=$('#item_username'); 
	   var selector_rolename=$('#item_rolename'); 
		$.ajax({
                 type: "GET",
		   url: 'http://localhost:8080/admin',
		   success: function(data) {
				jQuery.each(data, function(i, val) {
					if (admin_id == val.id) {
						selector_username.append('<option value="'+val.id+'" selected=true>'+val.username+'</option>');
					} else {
						selector_username.append('<option value="'+val.id+'">'+val.username+'</option>');
					}
					  
				});  
		   },
		   statusCode: {
                 
		    404: function() {
		       alert("error");
		    }
		   }
		})
		
		$.ajax({
                 type: "GET",
		   url: 'http://localhost:8080/role',
		   success: function(data) {
				jQuery.each(data, function(i, val) {
					if (role_id == val.id) {
						selector_rolename.append('<option value="'+val.id+'" selected=true>'+val.rolename+'</option>');
					} else {
						selector_rolename.append('<option value="'+val.id+'">'+val.rolename+'</option>');
					}
				});  
		   },
		   statusCode: {
                 
		    404: function() {
		       alert("error");
		    }
		   }
		})
 };
 
   var addAdminRoleSubmit = function(){
	  
        var admin_id = $('#item_username').val();
        if (admin_id == null || admin_id == '') {
	    	return;
	    }
        var role_id = $('#item_rolename').val();
        if (role_id == null || role_id == '') {
	    	return;
	    }
        var data = {'adminId': admin_id, 'roleId':role_id};
	    $.ajax({
                   type: "POST",
		   url: 'http://localhost:8080/adminrole',
                   contentType:"application/json",     
                   data: JSON.stringify(data), 
		   success: function(result) {
			   if (result == 0) {
				   alert("对照关系已存在!");
				   return;
			   }
			$(window).attr('location','./adminrole.html');
		   },
		   statusCode: {
                   
		    404: function() {
		       alert("error");
		    }
		   }
		})
		
        };

   var deleteAdminRoleSubmit = function(adminId, roleId){
	    $.ajax({
                   type: "DELETE",
		   url: 'http://localhost:8080/adminrole/'+adminId + "/"+roleId,
		   success: function() {
			$(window).attr('location','./adminrole.html');
		   },
		   statusCode: {
                   
		    404: function() {
		       alert("error");
		    }
		   }
		})
        };

   var updateAdminRole = function(adminId, roleId){
            $(window).attr('location','./adminrole_update.html?adminId='+adminId+"&roleId="+roleId);
        };

   var getAdminRoleById = function(adminId, roleId){
		$.ajax({
                   type: "GET",
		   url: 'http://localhost:8080/adminrole/'+adminid+"/"+roleId,
		   success: function(data) {
                        $('#item_id').val(data.id);
                        $('#item_rolename').val(data.rolename);
		   },
		   statusCode: {
                   
		    404: function() {
		       alert("error");
		    }
		   }
		})
   };

   var updateAdminRoleSubmit = function(){
        var admin_id = $('#item_username').val();
        if (admin_id == null || admin_id == '') {
	    	return;
	    }
        var role_id = $('#item_rolename').val();
        if (role_id == null || role_id == '') {
	    	return;
	    }
        var data = {'adminId': admin_id, 'roleId':role_id};
        $.ajax({
	            type: "PUT",
		   url: 'http://localhost:8080/adminrole',
	            contentType:"application/json",     
	            data: JSON.stringify(data), 
		   success: function(result) {
			   if (result == 0) {
				   alert("对照关系已存在!");
				   return;
			   }
			$(window).attr('location','./adminrole.html');
		   },
		   statusCode: {
	            
		    404: function() {
		       alert("error");
		    }
		   }
		})
		
    };


     

    
