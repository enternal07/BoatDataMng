require(['jquery', 'knockout', 'ujs', 'director', 'alert','jquery.file.upload'], function($, ko) {

	window.addRouter = function(path, func) {
		var truePath = path.replace('/:id', '');
		func = func || function(id) {
			initPage('/pages' + truePath+".js", id)
		}
		router.on(path, func)
	}
	  //1.select options
	//add dingqi0+
	window.optionValues= function (text,value){
			this.text = text;
			this.value = value;
	};
	
	
	window.router = Router();
	
	window.router.init();
	
	initMenuTree = function(){
		$('#show_side').click(function() {
			var $leftpanel = $('.leftpanel');
			//չ��
			if ($leftpanel.hasClass('leftpanel-collapse')) {
				$leftpanel.removeClass('leftpanel-collapse')
				$('.content').removeClass('content-collapse')
				$('.left-menu').children('li').children('a').children('.title').show();
				$('.left-menu').children('li').children('a').children('.arrow').show();
			} else {
				//�ϱ�
				$leftpanel.addClass('leftpanel-collapse')
				$('.content').addClass('content-collapse')
				$('.left-menu').children('li').children('a').children('.title').hide();
				$('.left-menu').children('li').children('a').children('.arrow').hide();
				$('.left-menu').children('li.open').children('a').children('.arrow').removeClass('open').removeClass('glyphicon-chevron-down').addClass('glyphicon-chevron-left');
				$('.left-menu').children('li.open').children('a').children('.arrow').removeClass('active');
				$('.left-menu').children('li.open').children('.sub-menu').slideUp(200);
			}
		});

		$('.left-menu li > a').on('click', function(e) {
			if ($(this).children('.title').length > 0 && !$(this).children('.title').is(':visible')) {
				$('#show_side').click();
			}
			if ($(this).next().hasClass('sub-menu') === false) {
				return;
			}
			var parent = $(this).parent().parent();
			parent.children('li.open').children('a').children('.arrow').removeClass('open').removeClass('glyphicon-chevron-down').addClass('glyphicon-chevron-left');
			parent.children('li.open').children('a').children('.arrow').removeClass('active');
			parent.children('li.open').children('.sub-menu').slideUp(200);
			parent.children('li').removeClass('open');
			//  parent.children('li').removeClass('active');

			var sub = $(this).next();
			if (sub.is(":visible")) {
				$('.arrow', $(this)).removeClass("open").removeClass('glyphicon-chevron-down').addClass('glyphicon-chevron-left');
				$(this).parent().removeClass("active");
				sub.slideUp(200);
			} else {
				$('.arrow', $(this)).addClass("open").removeClass('glyphicon-chevron-left').addClass('glyphicon-chevron-down');
				$(this).parent().addClass("open");
				sub.slideDown(200);
			}

			e.preventDefault();
		})		
	}
	
	initFileUpload = function() {
		$("input[type='file']").fileupload({
			url: "./excelUpload/uploadExcle",
			autoUpload: true,
			dataType: 'json',
			limitMultiFileUploads: 1,
			//replaceFileInput: false,
			limitMultiFileUploadSize: 100 * 1024 * 1024,
			done: function (e, data) {
				if(data.result.success){
					$(this).parents(".fileBtnGroup").siblings(".help-block").remove();
					$(this).parents(".form-group").removeClass("has-error");
					//$(this).parents(".fileBtnGroup").siblings(".imgPreview").attr("src", $ctx + "/userFile/" + data.result.userPk + "/service/" + data.result.imgName + "?v=" + (new Date()).getTime());
					//viewModelService.data.servicePO().logoPath = data.result.imgName;
					window.showSuccessMsg("处理成功");
				} else {
					window.showFailMsg("处理失败"+data.result.message);
					$(this).parents(".form-group").addClass("has-error");
					$(this).parent().siblings(".help-block").remove();
					$(this).parents(".fileBtnGroup").prepend('<span for="serviceLogo" class="help-block" style="float: left;">' + data.result.errorMsg + '</span>');
				}
			}
		}).bind('fileuploadsubmit', function (e, data) {
//			if(data.files.length > 0) {
//				if(data.files[0].size > 10 * 1024 * 1024) {
//					$(this).parents(".form-group").addClass("has-error");
//					$(this).parent().siblings(".help-block").remove();
//					$(this).parents(".fileBtnGroup").prepend('<span for="serviceLogo" class="help-block" style="float: left;">ͼƬ�Ѿ�����10M</span>');
//					return false;
//				} else
//					$(this).parent().siblings(".help-block").hide()
//			}
//			var name;
//			var logoPath = viewModelService.data.servicePO().logoPath;
//			if(!logoPath) {
//				var timestamp=new Date().getTime();
//				name = $(this).attr("id") + timestamp;
//			} else {
//				name = logoPath.substring(0,logoPath.lastIndexOf("."));
//			}
		    data.formData = {catalog: "smallDemo",file:data.files[0]};
		});
	}
	
	$(function(){
//		initMenuTree();
		var isLocated = false;
		$('.nav').find("a[href*='#']").each(function() {
			var path = this.hash.replace('#', '');
			addRouter(path);
			
			if (window.location.hash == this.hash) {
				window.router.setRoute("");
				window.router.setRoute(path);
				$(this).parent().addClass("focus");
				isLocated = true;
			}
     	});
		$(".nav div a").click(function() {
			$('.nav div.nav_list_focus').removeClass("nav_list_focus");
			$(this).parent().addClass("nav_list_focus");
		});
		$(".headertopright .notification").click(function() {
			$('.left-menu li.focus').removeClass("focus");
			$('a[href="#/userCenter/notification"]').parent().addClass("focus");
		});
		
		if(!isLocated) {
			addRouter("/small/small");
	
		}
		initFileUpload();
	})
	
	
	
	initFuncTree = function(menuData){
		if(menuData.id == 0){
			var rootMenuArray = menuData.children;
			for (var i = 0; i < rootMenuArray.length; i++) {
				var menu = rootMenuArray[i];
				var liObj = $("<li class=\"\">");
				var aObj = $("<a href=\"javascript:;\"> <i class=\"fa fa-file-text\"></i> <span class=\"title\">"+menu.funcName+"</span> <span class=\"arrow glyphicon glyphicon-chevron-left\"></span> </a>");
				var ulObj = $("<ul class=\"sub-menu\">");
				if(menu.children.length > 0){
					for (var j = 0; j < menu.children.length; j++) {
						var subMenuObj = menu.children[j];
						var subLiObj = $("<li> <a href=\"#"+ subMenuObj.funcUrl +"\" >"+ subMenuObj.funcName +"</a> </li>");
						$(ulObj).append(subLiObj);
					}
				}
				$(liObj).append(aObj).append(ulObj);
				$(".left-menu").append(liObj);
			}
		}
		initMenuTree();
		
		$('.left-menu').find("a[href*='#']").each(function() {
			var path = this.hash.replace('#', '');
			addRouter(path);
		});
	}


	/**
	 * 
	 */
	window.initTargetElement = function(truePath, pk, target) {
		initTargetPage('static/pages' + truePath, pk, target);

	}
	function initTargetPage(path, pk, target) {
		var module = path;
		require([module], function(module) {
			ko.cleanNode($('#' + target)[0]);
			$('#' + target).html('');
			$('#' + target).html(module.template);
//			module.model.translate=function(key){
//				return translate(key); 
//			}
			ko.applyBindings(module.model, $('#' + target)[0]);
			module.init(pk);
			//i18n("body");
		});
	}
	
	function initPage(p, id) {
		var module = p;
		require([module], function(module) {
			app.baseModel.data.content = ko.observableArray([]);
			ko.cleanNode($('#content')[0]);
			$('#content').html('');
			$('#content').html(module.template);
			ko.applyBindings(module.model, $('#content')[0]);
			module.init(id);
		})
	}

	window.app = {};
	
	app.baseModel = {
		data : {
			content : ko.observableArray([]),
			firstPage : ko.observable(true),
			lastPage : ko.observable(false),
			totalPages : ko.observable(0),
			totalElements : ko.observable(0),
			last : ko.observable(false),
			size : ko.observable(10),
			number : ko.observable(0),
			numberOfElements : ko.observable(10),
			first : ko.observable(true)
		},
		searchText : ko.observable(""),
		setData : function(data) {
			this.data.content(data.content);
			this.data.firstPage(data.firstPage);
			this.data.lastPage(data.lastPage);
			this.data.totalPages(data.totalPages);
			this.data.totalElements(data.totalElements);
			this.data.last(data.last);
			this.data.size(data.size);
			this.data.number(data.number + 1);
			this.data.numberOfElements(data.numberOfElements);
			this.data.first(data.first);
		},
		infoUrl : "",
		addUrl : "",
		deleteUrl : "",
		pageUrl : ""
	};

	app.baseModel.add = function(){
		window.router.setRoute(this.addUrl);	
	}
	
	app.baseModel.update = function(){
	}
	
	app.baseModel.del = function() {
		var me = this;
		$.ajax({
			type : 'DELETE',
			dataType : 'json',
			async : false,
			url : $ctx + this.deleteUrl + this.id,
			success : function(data) {
				if (data){
					jAlert('ɾ���ɹ�!')
					var pageNum = me.data.number();
					me.data.content.remove(me);
					me.load(pageNum);
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				jAlert("����ɾ�����񱨴�!!");
			}
		});
		
	}
	
	app.baseModel.load = function(pageIndex){
		var me = this;
		$.ajax({
			type : 'GET',
			cache: false,
			url : $ctx + this.pageUrl + pageIndex + "&searchText=" + this.searchText(),
			data : '',
			dataType : 'json',
			success : function(data) {
				me.setData(data);
				$("#pagination").pagination({
					totalPages : me.data.totalPages(),
					currentPage : me.data.number(),
					page : function(page) {
						me.load(page);
					}
				})							
			}
		});
		
	}
	
	app.baseModel.searchPage = function() {
		this.load(1);                                                                                          
	}
	
	baseViewModel = function(controller) {
		var base = this;
		this.data = {};
		this.data.content = ko.observableArray([]);
		
		this.data.firstPage = ko.observable(true);
		this.data.lastPage = ko.observable(false);
		this.data.totalPages = ko.observable(0);
		this.data.totalElements = ko.observable(0);
		this.data.last = ko.observable(false);
		this.data.size = ko.observable(10);
		this.data.number = ko.observable(0);
		this.data.numberOfElements = ko.observable(10);
		this.data.first = ko.observable(true);
		
		this.searchText = ko.observable("");
		
		this.setData = function(data) {
			base.data.content(data.content);
			base.data.firstPage(data.firstPage);
			base.data.lastPage(data.lastPage);
			base.data.totalPages(data.totalPages);
			base.data.totalElements(data.totalElements);
			base.data.last(data.last);
			base.data.size(data.size);
			base.data.number(data.number + 1);
			base.data.numberOfElements(data.numberOfElements);
			base.data.first(data.first);
		};
	 
		this.infoUrl = controller.infoUrl;
		this.addUrl = controller.addUrl;
		this.deleteUrl = controller.deleteUrl;
		this.pageUrl = controller.pageUrl;
	 
		this.add = function(){
			//alert(base.addUrl);
			window.router.setRoute(base.addUrl);	
		}
		
		this.update = function(){
		}
		
		this.del = function() {
			var me = this;
			$.ajax({
				type : 'DELETE',
				dataType : 'json',
				async : false,
				url : $ctx + base.deleteUrl + this.id,
				success : function(data) {
					if (data){
						jAlert('ɾ���ɹ�!')
						var pageNum = base.data.number();
						base.data.content.remove(me);
						base.load(pageNum);
					}
				},
				error : function(req, textStatus, errorThrown) {
					jAlert("����ɾ�����񱨴�!!");
				}
			});
			
		}
		
		this.load = function(pageIndex){
			$.ajax({
				type : 'GET',
				cache: false,
				url : $ctx + this.pageUrl + pageIndex + "&searchText=" + this.searchText(),
				data : '',
				dataType : 'json',
				success : function(data) {
					base.setData(data);
					$("#pagination").pagination({
						totalPages : base.data.totalPages(),
						currentPage : base.data.number(),
						page : function(page) {
							base.load(page);
						}
					})							
				}
			});
			
		}
		
		this.searchPage = function() {
			base.load(1);                                                                                          
		}
	};
});