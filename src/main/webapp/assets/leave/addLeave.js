var AddLeave = function() {
	var leaveDay = $("#leaveDay");
	
	function initDateTime() {
		//渲染日期控件								 
		var $startTime = $("#startTime").click(function() {
			$(this)
			.datetimepicker({language: "zh-CN", autoclose: true, todayBtn: true})
			.datetimepicker("show")
			.on("changeDate", function(e) {
				$endTime.datetimepicker("setStartDate", e.date);
				var endTime = $endTime.val();
				if (endTime.length != 0) {
					var endMoment = moment(endTime, "YYYY-MM-DD HH:mm");
					var startMoment = moment(e.date);
					leaveDay.val(endMoment.diff(startMoment, "days"));
				}
			});
		});
		//渲染日期控件								 
		var $endTime = $("#endTime").click(function() {
			$(this)
			.datetimepicker({language: "zh-CN", autoclose: true, todayBtn: true})
			.datetimepicker("show")
			.on("changeDate", function(e) {
				$startTime.datetimepicker("setEndDate", e.date);
				var startTime = $startTime.val();
				if (startTime.length != 0) {
					var startMoment = moment(startTime, "YYYY-MM-DD HH:mm");
					var endMoment = moment(e.date);
					leaveDay.val(endMoment.diff(startMoment, "days"));
				}
			});
		});
	}
	
	return {
		init: function() {
			initDateTime();
		}
	};
}();
$(function() {
	AddLeave.init();
});