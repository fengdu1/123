var AddClaimVoucher = function() {
	var infoModal = $("#infoModal");
	var form = $("form");
	var event = $("#event");
	var eventFormGroup = event.parents(".form-group");
	
	//查找模板
	var detail = $("#detail").html();
	//编译模板，获得模板对象
	var detailTempldate = Handlebars.compile(detail);
	
	//基于jQuery Validation的自定义验证器
	$.validator.addMethod("selected", function(value, element) {
		return !this.optional(element) && value != 0;
	}, "请选择");
	$.validator.addMethod("money", function(value, element) {
		return !this.optional(element) && value > 0;
	}, "请输入正确的金额");
	
	//定义验证规则
	var itemRules = {selected: true, messages: {selected: "请选择类别"}};
	var accountRules = {required: true, money: true, messages: {required: "请输入总金额", money: "总金额必须大于0"}};
	var descriptionRules = {required: true, maxlength: 200, messages: {required: "请输入费用说明", maxlength: "费用说明不能超过200个字符"}};
	var eventRules = {required: true, maxlength: 255, messages: {required: "请输入报销事由", maxlength: "报销事由不能超过255个字符"}};
	
	//添加一项“报销详情”的表单组，同时为所有表单元素注册验证规则
	function registerValidate() {
		$(detailTempldate()).insertBefore(eventFormGroup);
		form.find("select").each(function(index, item) {
			$(item).prop("name", "item" + index).rules("add", itemRules);
		});
		form.find("input[name^=account]").each(function(index, item) {
			$(item).prop("name", "account" + index).rules("add", accountRules);
		});
		form.find("input[name^=description]").each(function(index, item) {
			$(item).prop("name", "description" + index).rules("add", descriptionRules);
		});
	}
	
	function validateClaimVoucherDetail() {
		//为表单注册验证
		form.validate({
			errorElement: "span",
			errorClass: "help-block help-block-error",
			highlight: function(element) {
				$(element).closest(".form-group").addClass("has-error");
			},
			success: function(label) {
			    label.closest(".form-group").removeClass("has-error");
			}
		});
		
		registerValidate();
	}
	
	function removeClaimVoucherDetail() {
		form.on("click", "button", function() {
			if (form.find(".form-group").length > 3) {
				$(this).parents(".form-group").remove();
			} else {
				infoModal.modal("show");
			}
		});
	}
	
	function addClaimVoucherDetail() {
		$("#btnAddClaimVoucherDetail").click(function(e) {
			e.preventDefault();
			
			//移除“事由”的验证规则
			event.rules("remove");
			eventFormGroup.removeClass("has-error");
			
			if (!form.valid()) return;
			
			registerValidate();
		});
	}
	
	function saveOrSubmitClaimVoucher() {
		form.find("a").click(function(e) {
			e.preventDefault();
			
			//单独添加“事由”验证规则
			event.rules("add", eventRules);
			
			if (!form.valid()) return;
			
			//修改表单
			form.find("input[type=hidden]").val($(this).attr("data"));
			form.find("select").each(function(index, item) {
				$(item).prop("name", "claimVoucherDetails[" + index + "].item");
			});
			form.find("input[name^=account]").each(function(index, item) {
				$(item).prop("name", "claimVoucherDetails[" + index + "].account");
			});
			form.find("input[name^=description]").each(function(index, item) {
				$(item).prop("name", "claimVoucherDetails[" + index + "].description");
			});
			
			//提交
			form.submit();
		});
	}
	
	return {
		init: function() {
			validateClaimVoucherDetail();
			addClaimVoucherDetail();
			removeClaimVoucherDetail();
			saveOrSubmitClaimVoucher();
		}
	};
}();

$(function() {
	AddClaimVoucher.init();
});