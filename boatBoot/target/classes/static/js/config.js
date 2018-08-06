require.config({
	urlArgs: window.$ts ? "v=" + window.$ts : "",
	//baseUrl: ,
	paths: {
		text: "/lib/requirejs/text",
		css: "/lib/requirejs/css",
		jquery: "/lib/jquery/jquery-1.9.1.min",
		knockout: "/lib/knockout/knockout-3.1.0",
		ujs: "/lib/uui/js/a",
		wizard:"/lib/jquery-bootstrap-wizard/jquery.bootstrap.wizard",
		director:"/lib/director/director.min",
		'jquery.file.upload' : "/lib/juqery-file-upload/9.9.2/js/jquery.fileupload",
		'jquery.ui.widget':"/lib/jquery-ui/jquery.ui.widget",
		'jquery.iframe.transport':"/lib/jquery-iframe-transport/jquery.iframe-transport",
		'val':"/lib/jquery-validation/1.11.1/jquery.validate.min",
		'alert':"/lib/jquery-confirm/dist/jquery-confirm.min",
		'numTextarea':"/js/textarea/auto-line-number",
		'echarts-all':'/lib/echarts-2.2.7/build/dist/echarts-all',
		highcharts:"/lib/Highcharts-6.0.4/highcharts",
		'highcharts.data':"/lib/Highcharts-6.0.4/modules/data",
		'highcharts.nodata':"/lib/Highcharts-6.0.4/modules/no-data-to-display",
		'highcharts.export':"/lib/Highcharts-6.0.4/modules/exporting",
		'bootstrap': '/lib/bootstrap/js/bootstrap.min',
		'jquery.ztree.core':"/lib/zTree_v3-master/js/jquery.ztree.core",
		'jquery.ztree.excheck':"/lib/zTree_v3-master/js/jquery.ztree.excheck",
		'jquery.ztree.exedit':"/lib/zTree_v3-master/js/jquery.ztree.exedit",
		//'base64': "/lib/jquery/jquery.base64",
		//'base64trans': "/js/base64trans",
		'ajaxSetup': '/js/ajaxSetup',
		'datepicker': '/lib/bootstrap-datepicker/dist/js/bootstrap-datepicker.min',
		'datepicker-cn': '/lib/bootstrap-datepicker/dist/locales/bootstrap-datepicker.zh-CN.min',
		"switchBtn":"/lib/bootstrap-switch-master/dist/js/bootstrap-switch.min",
		"iconfont":"/css/iconfont/iconfont",
		'selectPage':"/lib/SelectPage-master/selectpage",
		'Sortable':'/js/Sortable',
		'Sortable.min':'/js/Sortable.min',
		'jquery.ui':'/js/jquery-ui',
		'ckeditor':'/lib/ckeditor/ckeditor'
	},
	shim: {
		'switchBtn':{
			deps:["css!/lib/bootstrap-switch-master/dist/css/bootstrap3/bootstrap-switch.min.css","css!/lib/bootstrap/css/bootstrap.min.css"]
		},
		'selectPage':{
			deps:["css!/lib/SelectPage-master/selectpage.bootstrap3.css","css!/lib/SelectPage-master/font-awesome.min.css"]
		},
		'iconfont':{
			deps:["css!/css/iconfont/iconfont.css"]
		},
		'highcharts.export':{
			deps: ["highcharts"]
		},
		'highcharts.nodata':{
			deps: ["highcharts"]
		},
		'highcharts.data':{
			deps: ["highcharts", 'highcharts.export', 'highcharts.nodata']
		},
		ujs:{
			deps: ["jquery"]
		},
		jquery:{
			deps: ["ajaxSetup"]
		},
		'jquery.file.upload':{
			deps: ["jquery","jquery.ui.widget","jquery.iframe.transport","css!/lib/juqery-file-upload/9.9.2/css/jquery.fileupload.css"]
		},
//		'alert': {
//			deps: ["jquery", "base64trans", "css!/lib/jquery-confirm/dist/jquery-confirm.min.css"]
//		},
		'alert': {
			deps: ["jquery",  "css!/lib/jquery-confirm/dist/jquery-confirm.min.css"]
		},
		'jquery.ztree.core':{
			deps: ["jquery"]
		},
		'jquery.ztree.excheck':{
			deps: ["jquery","jquery.ztree.core"]
		},
		'jquery.ztree.exedit':{
			deps: ["jquery","jquery.ztree.core", 'jquery.ztree.excheck']
		},
//		'base64': {
//			deps: ["jquery"]
//		},
//		'base64trans': {
//			deps: ["base64"]
//		},
		'datepicker': {
			deps: ["css!/lib/bootstrap-datepicker/dist/css/bootstrap-datepicker.min.css"]
		},
		'datepicker-cn': {
			deps: ["datepicker"]
		},
		'bootstrap': {
			deps: ["jquery"]
		},
		'jquery.ui': {
			deps: ["jquery"]
		}
	}
});
