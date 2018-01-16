   var getAdminDatas = function(){
		$.ajax({
                   type: "GET",
		   url: 'http://localhost:8080/admin',
		   success: function(data) {
			jQuery.each(data, function(i, val) {
			      var strHTML = "<tr><td>" + val.id + "</td><td>" + val.username + "</td><td>"+val.password+"</td><td>"+val.createDate+"</td><td>"+val.updateDate+"</td><td><a onclick='updateAdmin("+val.id+")' href='#'>修改</a>　|　<a onclick='deleteAdminSubmit("+val.id+")' href='#'>删除</a></td></tr>";
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

   var addAdminSubmit = function(){
	   
            var name = $('#item_username').val();
            if (name == null || name == '') {
	    	return;
	    }
	    var password = $('#item_password').val();
	    if (password == null || password == '') {
	    	return;
	    }  
	    
        var data = {'username': name, 'password': password};
	    $.ajax({
                   type: "POST",
		   url: 'http://localhost:8080/admin',
                   contentType:"application/json",     
                   data: JSON.stringify(data), 
		   success: function() {
			$(window).attr('location','./admin.html');
		   },
		   statusCode: {
                   
		    404: function() {
		       alert("error");
		    }
		   }
		})
		
        };

   var deleteAdminSubmit = function(idx){
	    $.ajax({
                   type: "DELETE",
		   url: 'http://localhost:8080/admin/'+idx,
		   success: function() {
			$(window).attr('location','./admin.html');
		   },
		   statusCode: {
                   
		    404: function() {
		       alert("error");
		    }
		   }
		})
        };

   var updateAdmin = function(idx){
            $(window).attr('location','./admin_update.html?id='+idx);
        };

   var getAdminById = function(idx){
		$.ajax({
                   type: "GET",
		   url: 'http://localhost:8080/admin/'+idx,
		   success: function(data) {
                        $('#item_id').val(data.id);
                        $('#item_username').val(data.username);
                        $('#item_password').val(data.password);
		   },
		   statusCode: {
                   
		    404: function() {
		       alert("error");
		    }
		   }
		})
   };

   var updateAdminSubmit = function(){
        var id = $('#item_id').val();
        var username = $('#item_username').val();
        if (username == null || username == '') {
	    	return;
	    }
	    var password = $('#item_password').val();
	    if (password == null || password == '') {
	    	return;
	    }  
        var data = {'id':id, 'username': username, 'password': password};
        $.ajax({
	            type: "PUT",
		   url: 'http://localhost:8080/admin',
	            contentType:"application/json",     
	            data: JSON.stringify(data), 
		   success: function() {
			$(window).attr('location','./admin.html');
		   },
		   statusCode: {
	            
		    404: function() {
		       alert("error");
		    }
		   }
		})
		
    };


     

    
