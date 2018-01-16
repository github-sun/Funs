   var getRoleDatas = function(){
		$.ajax({
                   type: "GET",
		   url: 'http://localhost:8080/role',
		   success: function(data) {
			jQuery.each(data, function(i, val) {
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
		   url: 'http://localhost:8080/role',
                   contentType:"application/json",     
                   data: JSON.stringify(data), 
		   success: function() {
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
		   url: 'http://localhost:8080/role/'+idx,
		   success: function() {
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
		   url: 'http://localhost:8080/role/'+idx,
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

   var updateRoleSubmit = function(){
        var id = $('#item_id').val();
        var rolename = $('#item_rolename').val();
        if (rolename == null || rolename == '') {
	    	return;
	    } 
        var data = {'id':id, 'rolename': rolename};
        $.ajax({
	            type: "PUT",
		   url: 'http://localhost:8080/role',
	            contentType:"application/json",     
	            data: JSON.stringify(data), 
		   success: function() {
			$(window).attr('location','./role.html');
		   },
		   statusCode: {
	            
		    404: function() {
		       alert("error");
		    }
		   }
		})
		
    };


     

    
