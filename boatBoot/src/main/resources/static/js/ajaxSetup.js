require([ 'jquery', 'alert'], function($, ko) {
	/**
	 * ����δ�(ȫ��)��AJAX����Ĭ��ѡ��
	 * ��Ҫ������AJAX��������Session���ڵ�����
	 */
	$.ajaxSetup({
		type: 'POST',
		cache:false,
		complete: function(xhr, status) {
			var sessionStatus = xhr.getResponseHeader('sessionstatus');
			if (xhr.status == 518) {
				//ȥ���������ش��󵯳���
				$(".jconfirm").remove();
				var top = getTopWinow();
				$.alert({
					title: "操作提示",
					content: "请您先登陆再操作",
					confirmButton: "登陆",
					confirm: function() {
						top.location.href = $ctx + "/login?sysid=apilink&service=" + location.href;
					}
				});
			} else if (xhr.status == 401) {
				//ȥ���������ش��󵯳���
				$(".jconfirm").remove();
				var top = getTopWinow();
				$.confirm({
					title: "操作提示",
					content: "请您联系管理员审批此操作权限",
					confirmButton: "关闭",
					cancelButton: "返回",
					confirm: function() {
						window.close();
					},
					cancel: function() {
						history.back();
					}
				});
			}
		}
	});
	
	function getTopWinow() {
		var p = window;
		while (p != p.parent) {
			p = p.parent;
		}
		return p;
	}

});
