/*
 Highcharts JS v6.0.4 (2017-12-15)
 Highcharts Drilldown module

 Author: Torstein Honsi
 License: www.highcharts.com/license

*/
(function(m){"object"===typeof module&&module.exports?module.exports=m:m(Highcharts)})(function(m){(function(f){var m=f.animObject,z=f.noop,A=f.color,B=f.defaultOptions,k=f.each,q=f.extend,H=f.format,C=f.objectEach,u=f.pick,r=f.wrap,p=f.Chart,v=f.seriesTypes,D=v.pie,t=v.column,E=f.Tick,w=f.fireEvent,F=f.inArray,G=1;q(B.lang,{drillUpText:"\u25c1 Back to {series.name}"});B.drilldown={animation:{duration:500},drillUpButton:{position:{align:"right",x:-10,y:10}}};f.SVGRenderer.prototype.Element.prototype.fadeIn=
function(a){this.attr({opacity:.1,visibility:"inherit"}).animate({opacity:u(this.newOpacity,1)},a||{duration:250})};p.prototype.addSeriesAsDrilldown=function(a,b){this.addSingleSeriesAsDrilldown(a,b);this.applyDrilldown()};p.prototype.addSingleSeriesAsDrilldown=function(a,b){var d=a.series,c=d.xAxis,e=d.yAxis,g,h=[],x=[],l,n,m;m={colorIndex:u(a.colorIndex,d.colorIndex)};this.drilldownLevels||(this.drilldownLevels=[]);l=d.options._levelNumber||0;(n=this.drilldownLevels[this.drilldownLevels.length-
1])&&n.levelNumber!==l&&(n=void 0);b=q(q({_ddSeriesId:G++},m),b);g=F(a,d.points);k(d.chart.series,function(a){a.xAxis!==c||a.isDrilling||(a.options._ddSeriesId=a.options._ddSeriesId||G++,a.options._colorIndex=a.userOptions._colorIndex,a.options._levelNumber=a.options._levelNumber||l,n?(h=n.levelSeries,x=n.levelSeriesOptions):(h.push(a),x.push(a.options)))});a=q({levelNumber:l,seriesOptions:d.options,levelSeriesOptions:x,levelSeries:h,shapeArgs:a.shapeArgs,bBox:a.graphic?a.graphic.getBBox():{},color:a.isNull?
(new f.Color(A)).setOpacity(0).get():A,lowerSeriesOptions:b,pointOptions:d.options.data[g],pointIndex:g,oldExtremes:{xMin:c&&c.userMin,xMax:c&&c.userMax,yMin:e&&e.userMin,yMax:e&&e.userMax},resetZoomButton:this.resetZoomButton},m);this.drilldownLevels.push(a);c&&c.names&&(c.names.length=0);b=a.lowerSeries=this.addSeries(b,!1);b.options._levelNumber=l+1;c&&(c.oldPos=c.pos,c.userMin=c.userMax=null,e.userMin=e.userMax=null);d.type===b.type&&(b.animate=b.animateDrilldown||z,b.options.animation=!0)};p.prototype.applyDrilldown=
function(){var a=this.drilldownLevels,b;a&&0<a.length&&(b=a[a.length-1].levelNumber,k(this.drilldownLevels,function(a){a.levelNumber===b&&k(a.levelSeries,function(a){a.options&&a.options._levelNumber===b&&a.remove(!1)})}));this.resetZoomButton&&(this.resetZoomButton.hide(),delete this.resetZoomButton);this.pointer.reset();this.redraw();this.showDrillUpButton()};p.prototype.getDrilldownBackText=function(){var a=this.drilldownLevels;if(a&&0<a.length)return a=a[a.length-1],a.series=a.seriesOptions,H(this.options.lang.drillUpText,
a)};p.prototype.showDrillUpButton=function(){var a=this,b=this.getDrilldownBackText(),d=a.options.drilldown.drillUpButton,c,e;this.drillUpButton?this.drillUpButton.attr({text:b}).align():(e=(c=d.theme)&&c.states,this.drillUpButton=this.renderer.button(b,null,null,function(){a.drillUp()},c,e&&e.hover,e&&e.select).addClass("highcharts-drillup-button").attr({align:d.position.align,zIndex:7}).add().align(d.position,!1,d.relativeTo||"plotBox"))};p.prototype.drillUp=function(){for(var a=this,b=a.drilldownLevels,
d=b[b.length-1].levelNumber,c=b.length,e=a.series,g,h,f,l,n=function(c){var b;k(e,function(a){a.options._ddSeriesId===c._ddSeriesId&&(b=a)});b=b||a.addSeries(c,!1);b.type===f.type&&b.animateDrillupTo&&(b.animate=b.animateDrillupTo);c===h.seriesOptions&&(l=b)};c--;)if(h=b[c],h.levelNumber===d){b.pop();f=h.lowerSeries;if(!f.chart)for(g=e.length;g--;)if(e[g].options.id===h.lowerSeriesOptions.id&&e[g].options._levelNumber===d+1){f=e[g];break}f.xData=[];k(h.levelSeriesOptions,n);w(a,"drillup",{seriesOptions:h.seriesOptions});
l.type===f.type&&(l.drilldownLevel=h,l.options.animation=a.options.drilldown.animation,f.animateDrillupFrom&&f.chart&&f.animateDrillupFrom(h));l.options._levelNumber=d;f.remove(!1);l.xAxis&&(g=h.oldExtremes,l.xAxis.setExtremes(g.xMin,g.xMax,!1),l.yAxis.setExtremes(g.yMin,g.yMax,!1));h.resetZoomButton&&(a.resetZoomButton=h.resetZoomButton,a.resetZoomButton.show())}w(a,"drillupall");this.redraw();0===this.drilldownLevels.length?this.drillUpButton=this.drillUpButton.destroy():this.drillUpButton.attr({text:this.getDrilldownBackText()}).align();
this.ddDupes.length=[]};r(p.prototype,"showResetZoom",function(a){this.drillUpButton||a.apply(this,Array.prototype.slice.call(arguments,1))});t.prototype.animateDrillupTo=function(a){if(!a){var b=this,d=b.drilldownLevel;k(this.points,function(a){var b=a.dataLabel;a.graphic&&a.graphic.hide();b&&(b.hidden="hidden"===b.attr("visibility"),b.hidden||(b.hide(),a.connector&&a.connector.hide()))});f.syncTimeout(function(){b.points&&k(b.points,function(a,b){b=b===(d&&d.pointIndex)?"show":"fadeIn";var c="show"===
b?!0:void 0,e=a.dataLabel;if(a.graphic)a.graphic[b](c);e&&!e.hidden&&(e.fadeIn(),a.connector&&a.connector.fadeIn())})},Math.max(this.chart.options.drilldown.animation.duration-50,0));this.animate=z}};t.prototype.animateDrilldown=function(a){var b=this,d=this.chart.drilldownLevels,c,e=m(this.chart.options.drilldown.animation),g=this.xAxis;a||(k(d,function(a){b.options._ddSeriesId===a.lowerSeriesOptions._ddSeriesId&&(c=a.shapeArgs)}),c.x+=u(g.oldPos,g.pos)-g.pos,k(this.points,function(a){a.graphic&&
a.graphic.attr(c).animate(q(a.shapeArgs,{fill:a.color||b.color}),e);a.dataLabel&&a.dataLabel.fadeIn(e)}),this.animate=null)};t.prototype.animateDrillupFrom=function(a){var b=m(this.chart.options.drilldown.animation),d=this.group,c=d!==this.chart.columnGroup,e=this;k(e.trackerGroups,function(a){if(e[a])e[a].on("mouseover")});c&&delete this.group;k(this.points,function(e){var g=e.graphic,k=a.shapeArgs,l=function(){g.destroy();d&&c&&(d=d.destroy())};g&&(delete e.graphic,b.duration?g.animate(k,f.merge(b,
{complete:l})):(g.attr(k),l()))})};D&&q(D.prototype,{animateDrillupTo:t.prototype.animateDrillupTo,animateDrillupFrom:t.prototype.animateDrillupFrom,animateDrilldown:function(a){var b=this.chart.options.drilldown.animation,d=this.chart.drilldownLevels[this.chart.drilldownLevels.length-1].shapeArgs,c=d.start,e=(d.end-c)/this.points.length;a||(k(this.points,function(a,h){var g=a.shapeArgs;if(a.graphic)a.graphic.attr(f.merge(d,{start:c+h*e,end:c+(h+1)*e}))[b?"animate":"attr"](g,b)}),this.animate=null)}});
f.Point.prototype.doDrilldown=function(a,b,d){var c=this.series.chart,e=c.options.drilldown,g=(e.series||[]).length,f;c.ddDupes||(c.ddDupes=[]);for(;g--&&!f;)e.series[g].id===this.drilldown&&-1===F(this.drilldown,c.ddDupes)&&(f=e.series[g],c.ddDupes.push(this.drilldown));w(c,"drilldown",{point:this,seriesOptions:f,category:b,originalEvent:d,points:void 0!==b&&this.series.xAxis.getDDPoints(b).slice(0)},function(b){var c=b.point.series&&b.point.series.chart,d=b.seriesOptions;c&&d&&(a?c.addSingleSeriesAsDrilldown(b.point,
d):c.addSeriesAsDrilldown(b.point,d))})};f.Axis.prototype.drilldownCategory=function(a,b){C(this.getDDPoints(a),function(d){d&&d.series&&d.series.visible&&d.doDrilldown&&d.doDrilldown(!0,a,b)});this.chart.applyDrilldown()};f.Axis.prototype.getDDPoints=function(a){var b=[];k(this.series,function(d){var c,e=d.xData,f=d.points;for(c=0;c<e.length;c++)if(e[c]===a&&d.options.data[c]&&d.options.data[c].drilldown){b.push(f?f[c]:!0);break}});return b};E.prototype.drillable=function(){var a=this.pos,b=this.label,
d=this.axis,c="xAxis"===d.coll&&d.getDDPoints,e=c&&d.getDDPoints(a);c&&(b&&e.length?(b.drillable=!0,b.addClass("highcharts-drilldown-axis-label").on("click",function(b){d.drilldownCategory(a,b)})):b&&b.drillable&&(b.on("click",null),b.removeClass("highcharts-drilldown-axis-label")))};r(E.prototype,"addLabel",function(a){a.call(this);this.drillable()});r(f.Point.prototype,"init",function(a,b,d,c){var e=a.call(this,b,d,c);c=(a=b.xAxis)&&a.ticks[c];e.drilldown&&f.addEvent(e,"click",function(a){b.xAxis&&
!1===b.chart.options.drilldown.allowPointDrilldown?b.xAxis.drilldownCategory(e.x,a):e.doDrilldown(void 0,void 0,a)});c&&c.drillable();return e});r(f.Series.prototype,"drawDataLabels",function(a){var b=this.chart.options.drilldown.activeDataLabelStyle,d=this.chart.renderer;a.call(this);k(this.points,function(a){var c=a.options.dataLabels,f=u(a.dlOptions,c&&c.style,{});a.drilldown&&a.dataLabel&&("contrast"===b.color&&(f.color=d.getContrast(a.color||this.color)),c&&c.color&&(f.color=c.color),a.dataLabel.addClass("highcharts-drilldown-data-label"))},
this)});var y=function(a,b,d){a[d?"addClass":"removeClass"]("highcharts-drilldown-point")},I=function(a){a.call(this);k(this.points,function(a){a.drilldown&&a.graphic&&y(a.graphic,"pointer",!0)})},J=function(a,b){var d=a.apply(this,Array.prototype.slice.call(arguments,1));this.drilldown&&this.series.halo&&"hover"===b?y(this.series.halo,"pointer",!0):this.series.halo&&y(this.series.halo,"auto",!1);return d};C(v,function(a){r(a.prototype,"drawTracker",I);r(a.prototype.pointClass.prototype,"setState",
J)})})(m)});
