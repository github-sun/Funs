   var getAdminRoleDatas = function(){
		$.ajax({
                   type: "GET",
				   url: 'http://localhost:8080/admin/adminrole',
		           xhrFields: {
		               withCredentials: true
		            },
		   success: function(data) {
			   if (data["code"] == 100001) {
				   $(window).attr('location','./login.html');
				   return;
			   }
		      if (data["code"] == 100002) {
		    	  alert(data["msg"]);
				   return;
		      }
			jQuery.each(data["data"], function(i, val) {
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
				   url: 'http://localhost:8080/admin/admin',
		           xhrFields: {
		               withCredentials: true
		            },
		   success: function(data) {
			   if (data["code"] == 100001) {
				   $(window).attr('location','./login.html');
				   return;
			   }
			      if (data["code"] == 100002) {
			    	  alert(data["msg"]);
					   return;
			      }
				jQuery.each(data["data"], function(i, val) {
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
				   url: 'http://localhost:8080/admin/role',
		           xhrFields: {
		               withCredentials: true
		            },
		   success: function(data) {
				jQuery.each(data["data"], function(i, val) {
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
				   url: 'http://localhost:8080/admin/admin',
		           xhrFields: {
		               withCredentials: true
		            },
		   success: function(data) {
			   if (data["code"] == 100001) {
				   $(window).attr('location','./login.html');
				   return;
			   }
			      if (data["code"] == 100002) {
			    	  alert(data["msg"]);
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
				   url: 'http://localhost:8080/admin/adminrole',
		           xhrFields: {
		               withCredentials: true
		            },
                   contentType:"application/json",     
                   data: JSON.stringify(data), 
		   success: function(data) {
			   if (data["code"] == 100001) {
				   $(window).attr('location','./login.html');
				   return;
			   }
			      if (data["code"] == 100002) {
			    	  alert(data["msg"]);
					   return;
			      }
			   if (data["code"] == 100004) {
				   alert(data["msg"]);
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
           xhrFields: {
               withCredentials: true
            },
		   url: 'http://localhost:8080/admin/adminrole/'+adminId + "/"+roleId,

		   success: function(data) {
			   if (data["code"] == 100001) {
				   $(window).attr('location','./login.html');
				   return;
			   }
			      if (data["code"] == 100002) {
			    	  alert(data["msg"]);
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

   var updateAdminRole = function(adminId, roleId){
            $(window).attr('location','./adminrole_update.html?adminId='+adminId+"&roleId="+roleId);
        };

   var getAdminRoleById = function(adminId, roleId){
		$.ajax({
                   type: "GET",
		   url: 'http://localhost:8080/admin/adminrole/'+adminid+"/"+roleId,
           xhrFields: {
               withCredentials: true
            },
		   success: function(data) {
			   if (data["code"] == 100001) {
				   $(window).attr('location','./login.html');
				   return;
			   }
			      if (data["code"] == 100002) {
			    	  alert(data["msg"]);
					   return;
			      }
                        $('#item_id').val(data["data"].id);
                        $('#item_rolename').val(data["data"].rolename);
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
		   url: 'http://localhost:8080/admin/adminrole',
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
			      if (data["code"] == 100002) {
			    	  alert(data["msg"]);
					   return;
			      }
			   if (data["code"] == 100004) {
				   alert(data["msg"]);
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


     

    
