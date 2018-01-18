   var loginSubmit = function(){
	   
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
		   url: 'http://localhost:8080/login',
                   contentType:"application/json",     
                   data: JSON.stringify(data), 
		   success: function(result) {
			   if (result == 1) {
				   alert("用户名或密码错误!");
			   } else {
				   $(window).attr('location','./login_success.html');
			   }
		   },
		   statusCode: {
                   
		    404: function() {
		       alert("error");
		    }
		   }
		})
		
        };

    
