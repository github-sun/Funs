   var getRolePermissionDatas = function(){
		$.ajax({
                   type: "GET",
				   url: 'http://localhost:8080/admin/rolepermission',
		           xhrFields: {
		               withCredentials: true
		            },
		   success: function(data) {
			   if (data["code"] == 100001) {
				   $(window).attr('location','./login.html');
				   return;
			   }
			jQuery.each(data["data"], function(i, val) {
				 var strHTML = "<tr><td>" + val.roleId + "</td><td>" + val.permissionId + "</td><td>"+val.rolename+"</td><td>"+val.permissionname+"</td><td>"+val.permissioncode+"</td><td><a onclick='deleteRolePermissionSubmit("+val.roleId+","+val.permissionId+")' href='#'>删除</a></td></tr>";
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
   
   
   var getRolePermissionAdminDatas = function(){
	   var selector_username=$('#item_rolename'); 
	   var selector_rolename=$('#item_permissionname'); 
		$.ajax({
                  type: "GET",
				   url: 'http://localhost:8080/admin/role',
		           xhrFields: {
		               withCredentials: true
		            },
		   success: function(data) {
			   if (data["code"] == 100001) {
				   $(window).attr('location','./login.html');
				   return;
			   }
				jQuery.each(data["data"], function(i, val) {
					selector_username.append('<option value="'+val.id+'">'+val.rolename+'</option>');  
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
				   url: 'http://localhost:8080/admin/permission',
		           xhrFields: {
		               withCredentials: true
		            },
		   success: function(data) {
				jQuery.each(data["data"], function(i, val) {
					selector_rolename.append('<option value="'+val.id+'">'+val.name+'</option>');  
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
				   url: 'http://localhost:8080/admin/admin',
		           xhrFields: {
		               withCredentials: true
		            },
		   success: function(data) {
			   if (data["code"] == 100001) {
				   $(window).attr('location','./login.html');
				   return;
			   }
				jQuery.each(data["data"], function(i, val) {
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
				   url: 'http://localhost:8080/admin/role',
		           xhrFields: {
		               withCredentials: true
		            },
		   success: function(data) {
				jQuery.each(data["data"], function(i, val) {
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
 
   var addRolePermissionSubmit = function(){
	  
        var role_id = $('#item_rolename').val();
        if (role_id == null || role_id == '') {
	    	return;
	    }
        var permission_id = $('#item_permissionname').val();
        if (permission_id == null || permission_id == '') {
	    	return;
	    }
        var data = {'roleId': role_id, 'permissionId':permission_id};
	    $.ajax({
                   type: "POST",
				   url: 'http://localhost:8080/admin/rolepermission',
		           xhrFields: {
		               withCredentials: true
		            },
                   contentType:"application/json",     
                   data: JSON.stringify(data), 
		   success: function(result) {
			   if (data["code"] == 100001) {
				   $(window).attr('location','./login.html');
				   return;
			   }
			   if (data["code"] == 100004) {
				   alert(data["msg"]);
				   return;
			   }
			$(window).attr('location','./rolepermission.html');
		   },
		   statusCode: {
                   
		    404: function() {
		       alert("error");
		    }
		   }
		})
		
        };

   var deleteRolePermissionSubmit = function(roleId, permissionId){
	    $.ajax({
           type: "DELETE",
           xhrFields: {
               withCredentials: true
            },
		   url: 'http://localhost:8080/admin/rolepermission/'+roleId + "/"+permissionId,

		   success: function(data) {
			   if (data["code"] == 100001) {
				   $(window).attr('location','./login.html');
				   return;
			   }
			$(window).attr('location','./rolepermission.html');
		   },
		   statusCode: {
                   
		    404: function() {
		       alert("error");
		    }
		   }
		})
        };


    
