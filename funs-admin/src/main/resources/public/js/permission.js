   var getPermissionDatas = function(){
		$.ajax({
                   type: "GET",
		   url: 'http://localhost:8080/admin/permission',
           xhrFields: {
               withCredentials: true
            },
		   success: function(data) {
			   if (data == 2) {
				   $(window).attr('location','./login.html');
				   return;
			   }
			jQuery.each(data, function(i, val) {
			      var strHTML = "<tr><td>" + val.id + "</td><td>" + val.name + "</td><td>"+val.code+"</td><td>"+val.createDate+"</td><td>"+val.updateDate+"</td><td><a onclick='updatePermission("+val.id+")' href='#'>修改</a>　|　<a onclick='deletePermissionSubmit("+val.id+")' href='#'>删除</a></td></tr>";
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

   var addPermissionSubmit = function(){
	   
            var name = $('#item_name').val();
            if (name == null || name == '') {
	    	return;
	    }
	    var code = $('#item_code').val();
	    if (code == null || code == '') {
	    	return;
	    }  
	    
        var data = {'name': name, 'code': code};
	    $.ajax({
                   type: "POST",
				   url: 'http://localhost:8080/admin/permission',
		           xhrFields: {
		               withCredentials: true
		            },
                   contentType:"application/json",     
                   data: JSON.stringify(data), 
		   success: function(data) {
			   if (data == 2) {
				   $(window).attr('location','./login.html');
				   return;
			   }
			$(window).attr('location','./permission.html');
		   },
		   statusCode: {
                   
		    404: function() {
		       alert("error");
		    }
		   }
		})
		
        };

   var deletePermissionSubmit = function(idx){
	    $.ajax({
                   type: "DELETE",
		   url: 'http://localhost:8080/admin/permission/'+idx,
           xhrFields: {
               withCredentials: true
            },
		   success: function(data) {
			   if (data == 2) {
				   $(window).attr('location','./login.html');
				   return;
			   }
			$(window).attr('location','./permission.html');
		   },
		   statusCode: {
                   
		    404: function() {
		       alert("error");
		    }
		   }
		})
        };

   var updatePermission = function(idx){
            $(window).attr('location','./permission_update.html?id='+idx);
        };

   var getPermissionById = function(idx){
		$.ajax({
                   type: "GET",
		   url: 'http://localhost:8080/admin/permission/'+idx,
           xhrFields: {
               withCredentials: true
            },
		   success: function(data) {
			   if (data == 2) {
				   $(window).attr('location','./login.html');
				   return;
			   }
                        $('#item_id').val(data.id);
                        $('#item_name').val(data.name);
                        $('#item_code').val(data.code);
		   },
		   statusCode: {
                   
		    404: function() {
		       alert("error");
		    }
		   }
		})
   };

   var updatePermissionSubmit = function(){
        var id = $('#item_id').val();
        var name = $('#item_name').val();
        if (name == null || name == '') {
	    	return;
	    }
	    var code = $('#item_code').val();
	    if (code == null || code == '') {
	    	return;
	    }  
        var data = {'id':id, 'name': name, 'code': code};
        $.ajax({
	            type: "PUT",
			   url: 'http://localhost:8080/admin/permission',
	           xhrFields: {
	               withCredentials: true
	            },
	            contentType:"application/json",     
	            data: JSON.stringify(data), 
		   success: function(data) {
			   if (data == 2) {
				   $(window).attr('location','./login.html');
				   return;
			   }
			$(window).attr('location','./permission.html');
		   },
		   statusCode: {
	            
		    404: function() {
		       alert("error");
		    }
		   }
		})
		
    };


     

    
