var AddCheckResult = function() {
	var form = $("form");
	var formValidator;
	
	function validateForm() {
		form.bootstrapValidator({
			feedbackIcons: {
	            valid:      "glyphicon glyphicon-ok",
	            invalid:    "glyphicon glyphicon-remove",
	            validating: "glyphicon glyphicon-refresh"
	        },
	        fields: {
	        	comment: {
	        		validators: {
	        			notEmpty: {
	        				message: "请输入审批意见"
	        			},
	        			stringLength: {
	        				max: 500,
	        				message: "审批意见长度不能超过500个字符"
	        			}
	        		}
	        	}
	        }
		});
		formValidator= form.data("bootstrapValidator");
	}
	
	function submitForm() {
		$("button").click(function() {
			formValidator.validate();
			if (formValidator.isValid()) {
				//移除所有验证项
				formValidator.destroy();
				$("input[name=result]").val($(this).attr("data"));
				form.submit();
			}
		});
	}
	
	return {
		init: function() {
			validateForm();
			submitForm();
		}
	};
}();

$(function() {
	AddCheckResult.init();
});