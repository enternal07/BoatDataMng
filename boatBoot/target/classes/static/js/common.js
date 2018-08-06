//��ʾ�ɹ���Ϣ
	window.showSuccessMsg=function(mess){
		var mess="<div class=' mess mess-success'>"
				  +"<button type='button' class='close succClose' onclick='javascript:hideSuccessMsg();' ><span aria-hidden='true'>&times;</span></button>"
				  +"<i class='iconfont mess-success-iconfont' style='height:46px;'><img src='/images/icon-complate.png' style='width:24;height:24px;vertical-align: unset !important;margin-top: 10px;'/></i><div class='mess-content'>"
				  +mess+
				  "</div>"
				  +"</div>";
		$("body").append(mess);
		$(".mess-success").fadeIn("slow");
		setTimeout("hideSuccessMsg()",3000);
	}
	
	window.hideSuccessMsg=function(){
		$(".mess-success").fadeOut("slow");
		$(".mess-success").remove();
	}
	//��ʾʧ����Ϣ
	window.showFailMsg=function(mess){
		var mess="<div class=' mess mess-fail'>"
			  +"<button type='button' class='close failClose' onclick='javascript:hideFailMsg();' ><span aria-hidden='true'>&times;</span></button>"
			  +"<i class='iconfont mess-fail-iconfont' style='height:46px;'><img src='/images/icon-info-close.png' style='width:24;height:24px;vertical-align: unset !important;margin-top: 10px;'/></i><div class='mess-content'>"
			  +mess+
			  "</div>"
			  +"</div>";
		$("body").append(mess);
		$(".mess-fail").fadeIn("slow");
		setTimeout("hideFailMsg()",3000);
	}
	window.hideFailMsg=function(){
		$(".mess-fail").fadeOut("slow");
		$(".mess-fail").remove();
	}
	
	/**
	������ĵ��������룬�����޸�add by dingqi0+
	**/
	window.showModal = function(modalId) {
		$(modalId).modal({
			show: true,
			keyboard: true
		});
	}
	/**
	 * �������¼������ݴ��룬����-add by dingqi0+
	 * ��Ҫ����table�е�list���¼��أ�ֻ��Ҫ����list����·���Ϳ����磺
	 * static/pages/esb/sviceRepository/appSysMng/applicationSystemlist
	 */
	window.reLoadData= function(path){
		require([path], function(path) {
			path.init();
		});
	}
	
	window.getTs = function(ts) {
		var date = new Date(ts);
		return date.getFullYear() + "-" + generateLength((date.getMonth() + 1))
				+ "-" + generateLength(date.getDate()) + " " + generateLength(date.getHours())
				+ ":" + generateLength(date.getMinutes()) + ":" + generateLength(date.getSeconds());
	}
	
	function generateLength(number) {
		return number > 9 ? number : ('0' + number)
	}
