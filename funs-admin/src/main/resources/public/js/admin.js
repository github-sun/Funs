        var hideErrorMsg = function(){
            $("#dvErrorMsg").hide();
        };
        var showErrorMsg = function(msg){
        	$("#spnErrorMsg").html(msg);
            $("#dvErrorMsg").show();
        };
        
var getAdminDatas = function(){
		$.ajax({
                   type: "GET",
		   url: 'http://localhost:8080/admin/admin',
           xhrFields: {
               withCredentials: true
            },
		   success: function(data) {
			      if (data["code"] == 000000) {
						jQuery.each(data["data"], function(i, val) {
						      var strHTML = "<tr><td>" + val.id + "</td><td>" + val.username + "</td><td>"+val.password+"</td><td>"+val.state+"</td><td>"+val.salt+"</td><td>"+val.createDate+"</td><td>"+val.updateDate+"</td><td><a onclick='updateAdmin("+val.id+")' href='#'>修改</a>　|　<a onclick='deleteAdminSubmit("+val.id+")' href='#'>删除</a></td></tr>";
			                        $('table#tblUser tbody').append(strHTML);
						}); 
						return;
			      }
			      showErrorMsg(data["msg"]);
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
				   url: 'http://localhost:8080/admin/admin',
		           xhrFields: {
		               withCredentials: true
		            },
                   contentType:"application/json",     
                   data: JSON.stringify(data), 
		   success: function(data) {
			      if (data["code"] == 000000) {
			    	  $(window).attr('location','./admin.html');
			    	  return;
			      }
			      showErrorMsg(data["msg"]);
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
		   url: 'http://localhost:8080/admin/admin/'+idx,
           xhrFields: {
               withCredentials: true
            },
		   success: function(data) {
			      if (data["code"] == 000000) {
			    	  $(window).attr('location','./admin.html');
			    	  return;
			      }
			      showErrorMsg(data["msg"]);
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
		   url: 'http://localhost:8080/admin/admin/'+idx,
           xhrFields: {
               withCredentials: true
            },
		   success: function(data) {
			      if (data["code"] == 000000) {
			    	  $('#item_id').val(data["data"].id);
                      $('#item_username').val(data["data"].username);
                      $('#item_password').val(data["data"].password);
                      return;
			      }
			      showErrorMsg(data["msg"]);     
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
			   url: 'http://localhost:8080/admin/admin',
	           xhrFields: {
	               withCredentials: true
	            },
	            contentType:"application/json",     
	            data: JSON.stringify(data), 
		   success: function(data) {
			      if (data["code"] == 000000) {
			    	  $(window).attr('location','./admin.html');
			    	  return;
			      }
			      showErrorMsg(data["msg"]);
		   },
		   statusCode: {
	            
		    404: function() {
		       alert("error");
		    }
		   }
		})
		
    };


     

    
