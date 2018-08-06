define(['jquery', 'knockout', 'text!/pages/small/small.html','echarts-all','alert'],
	function($, ko, template) {
		smallViewModel = {
			data: {
				content: ko.observableArray([]),
				samples:ko.observableArray([]),
				samplesSelected:ko.observable({}),
				backings:ko.observableArray([]),
				backingSelecte:ko.observable({}),
				searchCondition:ko.observable({}),
				refects:ko.observableArray([]),
				transmissions:ko.observableArray([]),
				bondacusts:ko.observableArray([]),
				arverg:ko.observable({}),
				
			},
			init: function() {
				this.data.content([]);
			}
		};
		smallViewModel.caluation = function(){
			
		}
		smallViewModel.load= function(){
			smallViewModel.getSmaples();
			smallViewModel.getBacking();
		}
		smallViewModel.getSmaples = function(){
			$.ajax({
				type: 'GET',
				url: "/metaMng/small/sample",
				dataType: 'json',
				success: function(data) {
					if(data.success){
						smallViewModel.data.samples(data.data);
					}else{
						window.showFailMsg("获取样品信息失败"+data.message);
					}
				},
				error: function(req, textStatus, errorThrown) {
					window.showFailMsg("获取样品信息失败");
				}
			});
		}
		smallViewModel.getBacking = function(){
			$.ajax({
				type: 'GET',
				url: "/metaMng/small/backing",
				dataType: 'json',
				success: function(data) {
					if(data.success){
						smallViewModel.data.backings(data.data);
					}else{
						window.showFailMsg("获取样品信息失败"+data.message);
					}
				},
				error: function(req, textStatus, errorThrown) {
					window.showFailMsg("获取样品信息失败");
				}
			});
		}
		smallViewModel.search = function() {
			var sam = smallViewModel.data.samplesSelected();
			var back = smallViewModel.data.backingSelecte();
			if(sam==undefined || back==undefined){
				window.showFailMsg("请确认样品和背衬");
			}else{
				var condition = {
						"samplename":sam.name,
						"backgroundtype":back.name,
						"temparture":"1",
						"press":"15",
						"rateMin":"10",
						"rateMax":"300"
					}
				$.ajax({
					type: 'post',
					url: "./item/pageSearchCondition",
					//dataType: 'json',
					contentType:'application/json',
					data: JSON.stringify(condition),
					success: function(data) {
						smallViewModel.dealwithEchar(
								[
									{'name':'投射系数','data':data.data.transmissions},
									{'name':'反射系数','data':data.data.refects},
									{'name':'吸声系数','data':data.data.bondacusts}]
								);
						smallViewModel.data.arverg({
							'bondAvrg':data.data.bondAvrg,
							'refectAvrg':data.data.refectAvrg,
							'traAvrg':data.data.traAvrg
						});
					},
					error: function(req, textStatus, errorThrown) {
					}
				});
			}
			

		};
		
		smallViewModel.addAccount = function() {
			smallViewModel.data.content.push({realName:"", account:"", amount: ""});
		}
		
		smallViewModel.remove = function() {
			smallViewModel.data.content.remove(this);
		}
		/**
		 * 保证
		 * nama
		 * data
		 */
		smallViewModel.dealwithEchar = function(namesAnddatas){
			var names = [];
			var seriesVar = [];
			for(var i=0;i<namesAnddatas.length;i++){
				names.push(namesAnddatas[i].name);
				var se ={
						name:namesAnddatas[i].name,
					    type:'line',
					    data:namesAnddatas[i].data,
				}
				seriesVar.push(se);
			}
			
			var myChart = echarts.init(document.getElementById('echar')); 
			var option = {
				    title : {
				        text: '数据曲线',
				        subtext: '系数曲线'
				    },
				    tooltip : {
				        trigger: 'axis',
				        axisPointer:{
				            show: true,
				            type : 'cross',
				            lineStyle: {
				                type : 'dashed',
				                width : 1
				            }
				        },
				        formatter : function (params) {
				            return params.seriesName + ' : [ '
				                   + params.value[0] + ', ' 
				                   + params.value[1] + ' ]';
				        }
				    },
				    legend: {
				    	     x: 'right', // 'center' | 'left' | {number},
				         y: 'center', // 'center' | 'bottom' | {number}
				         orient: 'vertical', // 'vertical'|horizontal
				         data:names//['投射系数','反射系数','吸声系数']
				    },
				    toolbox: {
				        show : true,
				        feature : {
				            mark : {show: true},
				            dataZoom : {show: true},
				            dataView : {show: true, readOnly: false},
				            magicType : {show: true, type: ['line', 'bar']},
				            restore : {show: true},
				            saveAsImage : {show: true}
				        }
				    },
				    calculable : true,
				    xAxis : [
				        {
				            type: 'value',
				            name:'频率'
				        }
				    ],
				    yAxis : [
				        {
				            type: 'value',
				            axisLine: {
				                lineStyle: {
				                    color: '#dc143c'
				                }
				            },
				            name:'系数'
				        }
				    ],
				    series : seriesVar
				};
			myChart.setOption(option); 
				                    
		}
		var init = function() {
			smallViewModel.load();
			smallViewModel.dealwithEchar(
					[{'name':'投射系数','data':[]},
				{'name':'反射系数','data':[]},
				{'name':'吸声系数','data':[]}]
					);
		};

		return {
			'model': smallViewModel,
			'template': template,
			'init': init
		};

	});