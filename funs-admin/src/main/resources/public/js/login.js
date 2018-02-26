        var hideErrorMsg = function(){
            $("#dvErrorMsg").hide();
        };
        var showErrorMsg = function(msg){
        	$("#spnErrorMsg").html(msg);
            $("#dvErrorMsg").show();
        };
        
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
                   xhrFields: {
                       withCredentials: true
                    },
                   data: JSON.stringify(data), 
		   success: function(data) {
			      if (data["code"] == 000000) {
			    	  $(window).attr('location','./login_success.html');
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

    
