/*
  Highcharts JS v6.0.4 (2017-12-15)

 Support for parallel coordinates in Highcharts

 (c) 2010-2017 Pawel Fus

 License: www.highcharts.com/license
*/
(function(g){"object"===typeof module&&module.exports?module.exports=g:g(Highcharts)})(function(g){(function(c){function g(b){var a=this.series&&this.series.chart,d=b.apply(this,Array.prototype.slice.call(arguments,1)),u,h;a&&a.hasParallelCoordinates&&!n(d.formattedValue)&&(h=a.yAxis[this.x],a=h.options,a=(u=v(a.tooltipValueFormat,a.labels.format))?c.format(u,q(this,{value:this.y})):h.isDatetimeAxis?c.dateFormat(a.dateTimeLabelFormats[h.tickPositions.info.unitName],this.y):a.categories?a.categories[this.y]:
this.y,d.formattedValue=d.point.formattedValue=a);return d}var r=c.Series.prototype,t=c.Chart.prototype,p=c.Axis.prototype,v=c.pick,l=c.each,e=c.wrap,m=c.merge,y=c.erase,w=c.splat,q=c.extend,n=c.defined,z=c.arrayMin,A=c.arrayMax,x={opposite:!0,type:"category"};c.setOptions({chart:{parallelCoordinates:!1,parallelAxes:{title:{text:"",reserveSpace:!1},labels:{x:0,y:4,align:"center",reserveSpace:!1},offset:0}}});e(t,"init",function(b,a,d){var c=w(a.yAxis||{}),h=c.length,e=[];if(this.hasParallelCoordinates=
a.chart&&a.chart.parallelCoordinates){for(this.setParallelInfo(a);h<=this.parallelInfo.counter;h++)e.push({});a=m({legend:{enabled:!1}},a,{boost:{seriesThreshold:Number.MAX_SAFE_INTEGER},plotOptions:{series:{boostThreshold:Number.MAX_SAFE_INTEGER}}});a.yAxis=c.concat(e);a.xAxis=m(x,w(a.xAxis||{})[0])}return b.call(this,a,d)});e(t,"update",function(b,a){a.chart&&(n(a.chart.parallelCoordinates)&&(this.hasParallelCoordinates=a.chart.parallelCoordinates),this.hasParallelCoordinates&&a.chart.parallelAxes&&
(this.options.chart.parallelAxes=m(this.options.chart.parallelAxes,a.chart.parallelAxes),l(this.yAxis,function(a){a.update({},!1)})));return b.apply(this,Array.prototype.slice.call(arguments,1))});q(t,{setParallelInfo:function(b){var a=this;b=b.series;a.parallelInfo={counter:0};l(b,function(d){d.data&&(a.parallelInfo.counter=Math.max(a.parallelInfo.counter,d.data.length-1))})}});p.keepProps.push("parallelPosition");e(p,"setOptions",function(b,a){var d=this.chart,c=["left","width","height","top"];
b.apply(this,Array.prototype.slice.call(arguments,1));d.hasParallelCoordinates&&(d.inverted&&(c=c.reverse()),this.isXAxis?this.options=m(this.options,x,a):(this.options=m(this.options,this.chart.options.chart.parallelAxes,a),this.parallelPosition=v(this.parallelPosition,d.yAxis.length),this.setParallelPosition(c,this.options)))});e(p,"getSeriesExtremes",function(b){if(this.chart&&this.chart.hasParallelCoordinates&&!this.isXAxis){var a=this.parallelPosition,d=[];l(this.series,function(b){n(b.yData[a])&&
d.push(b.yData[a])});this.dataMin=z(d);this.dataMax=A(d)}else b.apply(this,Array.prototype.slice.call(arguments,1))});q(p,{setParallelPosition:function(b,a){a[b[0]]=100*(this.parallelPosition+.5)/(this.chart.parallelInfo.counter+1)+"%";this[b[1]]=a[b[1]]=0;this[b[2]]=a[b[2]]=null;this[b[3]]=a[b[3]]=null}});e(r,"bindAxes",function(b){if(this.chart.hasParallelCoordinates){var a=this;l(this.chart.axes,function(b){a.insert(b.series);b.isDirty=!0});a.xAxis=this.chart.xAxis[0];a.yAxis=this.chart.yAxis[0]}else b.apply(this,
Array.prototype.slice.call(arguments,1))});e(r,"translate",function(b){b.apply(this,Array.prototype.slice.call(arguments,1));var a=this.chart,d=this.points,c=d&&d.length,e=Number.MAX_VALUE,g,f,k;if(this.chart.hasParallelCoordinates){for(k=0;k<c;k++)f=d[k],n(f.y)?(f.plotX=f.clientX=a.inverted?a.plotHeight-a.yAxis[k].top+a.plotTop:a.yAxis[k].left-a.plotLeft,f.plotY=a.yAxis[k].translate(f.y,!1,!0,null,!0),void 0!==g&&(e=Math.min(e,Math.abs(f.plotX-g))),g=f.plotX,f.isInside=a.isInsidePlot(f.plotX,f.plotY,
a.inverted)):f.isNull=!0;this.closestPointRangePx=e}});e(r,"destroy",function(b){if(this.chart.hasParallelCoordinates){var a=this;l(this.chart.axes||[],function(b){b&&b.series&&(y(b.series,a),b.isDirty=b.forceRedraw=!0)})}b.apply(this,Array.prototype.slice.call(arguments,1))});l(["line","spline"],function(b){e(c.seriesTypes[b].prototype.pointClass.prototype,"getLabelConfig",g)})})(g)});
