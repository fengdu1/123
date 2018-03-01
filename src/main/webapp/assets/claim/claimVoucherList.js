var ClaimVoucherList = function(){
	function initDateTime() {
		$("#beginTime,#endTime").datetimepicker({format: "YYYY-MM-DD HH:mm", locale: moment.locale("zh-cn")});
	}
	
	function pagination(){
		$(".pagination a").click(function(){
				var _this=$(this);
				var pageIndex=_this.attr("pageIndex");
				var pageSize=_this.attr("pageSize");
				
				var status = $("#status").val();
				var beginTime=$("#beginTime").val();
				var endTime=$("#endTime").val();
				
				location.href="claimVoucherList.action?pageIndex=" + pageIndex + "&pageSize=" + pageSize + "&status=" + status + "&beginTime=" + beginTime + "&endTime=" + endTime;
			}
		)
	}
	
	return {
		init:function(){
			initDateTime();
			pagination();
		}
	}
}();

$(function() {
	ClaimVoucherList.init();
});