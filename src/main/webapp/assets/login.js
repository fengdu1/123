var Login = function(){
	function validateLogin(){
		//为登录表单注册验证
		$("form").bootstrapValidator({
			feedbackIcons: {
	            valid:      "glyphicon glyphicon-ok",
	            invalid:    "glyphicon glyphicon-remove",
	            validating: "glyphicon glyphicon-refresh"
	        },
	        fields: {
	        	sn: {
	        		validators: {
	        			notEmpty: {
	        				message: "请输入工号"
	        			}
	        		}
	        	},
	        	password: {
	        		validators: {
	        			notEmpty: {
	        				message: "请输入密码"
	        			}
	        		}
	        	}
	        }
		});
	}
	return{
		init:function(){
			validateLogin();
		}
	}
}();

$(function(){
	Login.init();
})
