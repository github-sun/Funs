   var getRoleDatas = function(){
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
			      var strHTML = "<tr><td>" + val.id + "</td><td>" + val.rolename + "</td><td>"+val.createDate+"</td><td>"+val.updateDate+"</td><td><a onclick='updateRole("+val.id+")' href='#'>修改</a>　|　<a onclick='deleteRoleSubmit("+val.id+")' href='#'>删除</a></td></tr>";
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

   var addRoleSubmit = function(){
        var rolename = $('#item_rolename').val();
        if (rolename == null || rolename == '') {
	    	return;
	    }
        var data = {'rolename': rolename};
	    $.ajax({
                   type: "POST",
				   url: 'http://localhost:8080/admin/role',
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
			$(window).attr('location','./role.html');
		   },
		   statusCode: {
                   
		    404: function() {
		       alert("error");
		    }
		   }
		})
		
        };

   var deleteRoleSubmit = function(idx){
	    $.ajax({
                   type: "DELETE",
				   url: 'http://localhost:8080/admin/role/'+idx,
		           xhrFields: {
		               withCredentials: true
		            },
		   success: function(data) {
			   if (data["code"] == 100001) {
				   $(window).attr('location','./login.html');
				   return;
			   }
			$(window).attr('location','./role.html');
		   },
		   statusCode: {
                   
		    404: function() {
		       alert("error");
		    }
		   }
		})
        };

   var updateRole = function(idx){
            $(window).attr('location','./role_update.html?id='+idx);
        };

   var getRoleById = function(idx){
		$.ajax({
                   type: "GET",
				   url: 'http://localhost:8080/admin/role/'+idx,
		           xhrFields: {
		               withCredentials: true
		            },
		   success: function(data) {
			   if (data["code"] == 100001) {
				   $(window).attr('location','./login.html');
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

   var updateRoleSubmit = function(){
        var id = $('#item_id').val();
        var rolename = $('#item_rolename').val();
        if (rolename == null || rolename == '') {
	    	return;
	    } 
        var data = {'id':id, 'rolename': rolename};
        $.ajax({
	            type: "PUT",
			   url: 'http://localhost:8080/admin/role',
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
			$(window).attr('location','./role.html');
		   },
		   statusCode: {
	            
		    404: function() {
		       alert("error");
		    }
		   }
		})
		
    };


     

    
