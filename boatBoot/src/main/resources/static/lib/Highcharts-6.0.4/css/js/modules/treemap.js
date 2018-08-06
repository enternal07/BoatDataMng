/*
 Highcharts JS v6.0.4 (2017-12-15)

 (c) 2014 Highsoft AS
 Authors: Jon Arild Nygard / Oystein Moseng

 License: www.highcharts.com/license
*/
(function(q){"object"===typeof module&&module.exports?module.exports=q:q(Highcharts)})(function(q){var D=function(d){var q=d.each,A=d.extend,r=d.pick;return{getColor:function(d,p){var v=p.levelMap,l=p.parentColorIndex,g=p.series,q=p.colors,k=g.points,w,y;d&&(k=k[d.i],v=v[d.levelDynamic]||{},(d=k&&("boolean"===typeof v.colorByPoint?v.colorByPoint:!!g.options.colorByPoint))&&(w=k.index%(q?q.length:g.chart.options.chart.colorCount)),y=r(k&&k.options.colorIndex,v&&v.colorIndex,w,l,p.colorIndex));return{color:void 0,
colorIndex:y}},setTreeValues:function p(d,l){var g=l.before,v=l.idRoot,k=l.mapIdToNode[v],w=l.points[d.i],y=w&&w.options||{},n=0,x=[];A(d,{levelDynamic:d.level-(("boolean"===typeof l.levelIsConstant?l.levelIsConstant:1)?0:k.level),name:r(w&&w.name,""),visible:v===d.id||("boolean"===typeof l.visible?l.visible:!1)});"function"===typeof g&&(d=g(d,l));q(d.children,function(g,r){var k=A({},l);A(k,{index:r,siblings:d.children.length,visible:d.visible});g=p(g,k);x.push(g);g.visible&&(n+=g.val)});d.visible=
0<n||d.visible;g=r(y.value,n);A(d,{children:x,childrenTotal:n,isLeaf:d.visible&&!n,val:g});return d}}}(q);(function(d,q){var A=d.seriesType,r=d.seriesTypes,D=d.map,p=d.merge,v=d.extend,l=d.noop,g=d.each,F=q.getColor,k=d.grep,w=d.isNumber,y=d.isString,n=d.pick,x=d.Series,G=d.stableSort,H=function(a,b,c){c=c||this;d.objectEach(a,function(t,e){b.call(c,t,e,a)})},C=d.reduce,B=function(a,b,c){c=c||this;a=b.call(c,a);!1!==a&&B(a,b,c)};A("treemap","scatter",{showInLegend:!1,marker:!1,dataLabels:{enabled:!0,
defer:!1,verticalAlign:"middle",formatter:function(){return this.point.name||this.point.id},inside:!0},tooltip:{headerFormat:"",pointFormat:"\x3cb\x3e{point.name}\x3c/b\x3e: {point.value}\x3cbr/\x3e"},ignoreHiddenPoint:!0,layoutAlgorithm:"sliceAndDice",layoutStartingDirection:"vertical",alternateStartingDirection:!1,levelIsConstant:!0,drillUpButton:{position:{align:"right",x:-10,y:10}}},{pointArrayMap:["value"],axisTypes:r.heatmap?["xAxis","yAxis","colorAxis"]:["xAxis","yAxis"],directTouch:!0,optionalAxis:"colorAxis",
getSymbol:l,parallelArrays:["x","y","value","colorValue"],colorKey:"colorValue",translateColors:r.heatmap&&r.heatmap.prototype.translateColors,colorAttribs:r.heatmap&&r.heatmap.prototype.colorAttribs,trackerGroups:["group","dataLabelsGroup"],getListOfParents:function(a,b){a=C(a||[],function(a,b,e){b=n(b.parent,"");void 0===a[b]&&(a[b]=[]);a[b].push(e);return a},{});H(a,function(a,t,e){""!==t&&-1===d.inArray(t,b)&&(g(a,function(a){e[""].push(a)}),delete e[t])});return a},getTree:function(){var a=D(this.data,
function(a){return a.id}),a=this.getListOfParents(this.data,a);this.nodeMap=[];return this.buildNode("",-1,0,a,null)},init:function(a,b){x.prototype.init.call(this,a,b);this.options.allowDrillToNode&&d.addEvent(this,"click",this.onClickDrillToNode)},buildNode:function(a,b,c,t,e){var d=this,f=[],h=d.points[b],E=0,z;g(t[a]||[],function(b){z=d.buildNode(d.points[b].id,b,c+1,t,a);E=Math.max(z.height+1,E);f.push(z)});b={id:a,i:b,children:f,height:E,level:c,parent:e,visible:!1};d.nodeMap[b.id]=b;h&&(h.node=
b);return b},setTreeValues:function(a){var b=this,c=b.options,d=b.nodeMap[b.rootNode],c="boolean"===typeof c.levelIsConstant?c.levelIsConstant:!0,e=0,m=[],f,h=b.points[a.i];g(a.children,function(a){a=b.setTreeValues(a);m.push(a);a.ignore||(e+=a.val)});G(m,function(a,c){return a.sortIndex-c.sortIndex});f=n(h&&h.options.value,e);h&&(h.value=f);v(a,{children:m,childrenTotal:e,ignore:!(n(h&&h.visible,!0)&&0<f),isLeaf:a.visible&&!e,levelDynamic:a.level-(c?0:d.level),name:n(h&&h.name,""),sortIndex:n(h&&
h.sortIndex,-f),val:f});return a},calculateChildrenAreas:function(a,b){var c=this,d=c.options,e=this.levelMap[a.levelDynamic+1],m=n(c[e&&e.layoutAlgorithm]&&e.layoutAlgorithm,d.layoutAlgorithm),f=d.alternateStartingDirection,h=[];a=k(a.children,function(a){return!a.ignore});e&&e.layoutStartingDirection&&(b.direction="vertical"===e.layoutStartingDirection?0:1);h=c[m](b,a);g(a,function(a,e){e=h[e];a.values=p(e,{val:a.childrenTotal,direction:f?1-b.direction:b.direction});a.pointValues=p(e,{x:e.x/c.axisRatio,
width:e.width/c.axisRatio});a.children.length&&c.calculateChildrenAreas(a,a.values)})},setPointValues:function(){var a=this.xAxis,b=this.yAxis;g(this.points,function(c){var d=c.node,e=d.pointValues,m,f;e&&d.visible?(d=Math.round(a.translate(e.x,0,0,0,1))-0,m=Math.round(a.translate(e.x+e.width,0,0,0,1))-0,f=Math.round(b.translate(e.y,0,0,0,1))-0,e=Math.round(b.translate(e.y+e.height,0,0,0,1))-0,c.shapeType="rect",c.shapeArgs={x:Math.min(d,m),y:Math.min(f,e),width:Math.abs(m-d),height:Math.abs(e-f)},
c.plotX=c.shapeArgs.x+c.shapeArgs.width/2,c.plotY=c.shapeArgs.y+c.shapeArgs.height/2):(delete c.plotX,delete c.plotY)})},setColorRecursive:function(a,b,c,d,e){var m=this,f=m&&m.chart,f=f&&f.options&&f.options.colors,t;if(a){t=F(a,{colors:f,index:d,levelMap:m.levelMap,parentColor:b,parentColorIndex:c,series:m,siblings:e});if(b=m.points[a.i])b.color=t.color,b.colorIndex=t.colorIndex;g(a.children||[],function(c,b){m.setColorRecursive(c,t.color,t.colorIndex,b,a.children.length)})}},algorithmGroup:function(a,
b,c,d){this.height=a;this.width=b;this.plot=d;this.startDirection=this.direction=c;this.lH=this.nH=this.lW=this.nW=this.total=0;this.elArr=[];this.lP={total:0,lH:0,nH:0,lW:0,nW:0,nR:0,lR:0,aspectRatio:function(a,c){return Math.max(a/c,c/a)}};this.addElement=function(a){this.lP.total=this.elArr[this.elArr.length-1];this.total+=a;0===this.direction?(this.lW=this.nW,this.lP.lH=this.lP.total/this.lW,this.lP.lR=this.lP.aspectRatio(this.lW,this.lP.lH),this.nW=this.total/this.height,this.lP.nH=this.lP.total/
this.nW,this.lP.nR=this.lP.aspectRatio(this.nW,this.lP.nH)):(this.lH=this.nH,this.lP.lW=this.lP.total/this.lH,this.lP.lR=this.lP.aspectRatio(this.lP.lW,this.lH),this.nH=this.total/this.width,this.lP.nW=this.lP.total/this.nH,this.lP.nR=this.lP.aspectRatio(this.lP.nW,this.nH));this.elArr.push(a)};this.reset=function(){this.lW=this.nW=0;this.elArr=[];this.total=0}},algorithmCalcPoints:function(a,b,c,d){var e,t,f,h,l=c.lW,z=c.lH,u=c.plot,k,n=0,r=c.elArr.length-1;b?(l=c.nW,z=c.nH):k=c.elArr[c.elArr.length-
1];g(c.elArr,function(a){if(b||n<r)0===c.direction?(e=u.x,t=u.y,f=l,h=a/f):(e=u.x,t=u.y,h=z,f=a/h),d.push({x:e,y:t,width:f,height:h}),0===c.direction?u.y+=h:u.x+=f;n+=1});c.reset();0===c.direction?c.width-=l:c.height-=z;u.y=u.parent.y+(u.parent.height-c.height);u.x=u.parent.x+(u.parent.width-c.width);a&&(c.direction=1-c.direction);b||c.addElement(k)},algorithmLowAspectRatio:function(a,b,c){var d=[],e=this,m,f={x:b.x,y:b.y,parent:b},h=0,l=c.length-1,k=new this.algorithmGroup(b.height,b.width,b.direction,
f);g(c,function(c){m=c.val/b.val*b.height*b.width;k.addElement(m);k.lP.nR>k.lP.lR&&e.algorithmCalcPoints(a,!1,k,d,f);h===l&&e.algorithmCalcPoints(a,!0,k,d,f);h+=1});return d},algorithmFill:function(a,b,c){var d=[],e,m=b.direction,f=b.x,h=b.y,k=b.width,l=b.height,n,r,p,q;g(c,function(c){e=c.val/b.val*b.height*b.width;n=f;r=h;0===m?(q=l,p=e/q,k-=p,f+=p):(p=k,q=e/p,l-=q,h+=q);d.push({x:n,y:r,width:p,height:q});a&&(m=1-m)});return d},strip:function(a,b){return this.algorithmLowAspectRatio(!1,a,b)},squarified:function(a,
b){return this.algorithmLowAspectRatio(!0,a,b)},sliceAndDice:function(a,b){return this.algorithmFill(!0,a,b)},stripes:function(a,b){return this.algorithmFill(!1,a,b)},translate:function(){var a=this,b=a.rootNode=n(a.rootNode,a.options.rootId,""),c,d;x.prototype.translate.call(a);a.levelMap=C(a.options.levels||[],function(a,c){a[c.level]=c;return a},{});d=a.tree=a.getTree();c=a.nodeMap[b];""===b||c&&c.children.length||(a.drillToNode("",!1),b=a.rootNode,c=a.nodeMap[b]);B(a.nodeMap[a.rootNode],function(c){var b=
!1,d=c.parent;c.visible=!0;if(d||""===d)b=a.nodeMap[d];return b});B(a.nodeMap[a.rootNode].children,function(a){var c=!1;g(a,function(a){a.visible=!0;a.children.length&&(c=(c||[]).concat(a.children))});return c});a.setTreeValues(d);a.axisRatio=a.xAxis.len/a.yAxis.len;a.nodeMap[""].pointValues=b={x:0,y:0,width:100,height:100};a.nodeMap[""].values=b=p(b,{width:b.width*a.axisRatio,direction:"vertical"===a.options.layoutStartingDirection?0:1,val:d.val});a.calculateChildrenAreas(d,b);a.colorAxis?a.translateColors():
a.options.colorByPoint||a.setColorRecursive(a.tree);a.options.allowDrillToNode&&(c=c.pointValues,a.xAxis.setExtremes(c.x,c.x+c.width,!1),a.yAxis.setExtremes(c.y,c.y+c.height,!1),a.xAxis.setScale(),a.yAxis.setScale());a.setPointValues()},drawDataLabels:function(){var a=this,b=k(a.points,function(a){return a.node.visible}),c,d;g(b,function(b){d=a.levelMap[b.node.levelDynamic];c={style:{}};b.node.isLeaf||(c.enabled=!1);d&&d.dataLabels&&(c=p(c,d.dataLabels),a._hasPointLabels=!0);b.shapeArgs&&(c.style.width=
b.shapeArgs.width,b.dataLabel&&b.dataLabel.css({width:b.shapeArgs.width+"px"}));b.dlOptions=p(c,b.options.dataLabels)});x.prototype.drawDataLabels.call(this)},alignDataLabel:function(a){r.column.prototype.alignDataLabel.apply(this,arguments);a.dataLabel&&a.dataLabel.attr({zIndex:(a.node.zIndex||0)+1})},drawPoints:function(){var a=this,b=k(a.points,function(a){return a.node.visible});g(b,function(c){var b="level-group-"+c.node.levelDynamic;a[b]||(a[b]=a.chart.renderer.g(b).attr({zIndex:1E3-c.node.levelDynamic}).add(a.group));
c.group=a[b]});r.column.prototype.drawPoints.call(this);this.colorAttribs&&g(this.points,function(a){a.graphic&&a.graphic.css(this.colorAttribs(a))},this);a.options.allowDrillToNode&&g(b,function(b){b.graphic&&(b.drillId=a.options.interactByLeaf?a.drillToByLeaf(b):a.drillToByGroup(b))})},onClickDrillToNode:function(a){var b=(a=a.point)&&a.drillId;y(b)&&(a.setState(""),this.drillToNode(b))},drillToByGroup:function(a){var b=!1;1!==a.node.level-this.nodeMap[this.rootNode].level||a.node.isLeaf||(b=a.id);
return b},drillToByLeaf:function(a){var b=!1;if(a.node.parent!==this.rootNode&&a.node.isLeaf)for(a=a.node;!b;)a=this.nodeMap[a.parent],a.parent===this.rootNode&&(b=a.id);return b},drillUp:function(){var a=this.nodeMap[this.rootNode];a&&y(a.parent)&&this.drillToNode(a.parent)},drillToNode:function(a,b){var c=this.nodeMap[a];this.idPreviousRoot=this.rootNode;this.rootNode=a;""===a?this.drillUpButton=this.drillUpButton.destroy():this.showDrillUpButton(c&&c.name||a);this.isDirty=!0;n(b,!0)&&this.chart.redraw()},
showDrillUpButton:function(a){var b=this;a=a||"\x3c Back";var c=b.options.drillUpButton,d,e;c.text&&(a=c.text);this.drillUpButton?(this.drillUpButton.placed=!1,this.drillUpButton.attr({text:a}).align()):(e=(d=c.theme)&&d.states,this.drillUpButton=this.chart.renderer.button(a,null,null,function(){b.drillUp()},d,e&&e.hover,e&&e.select).addClass("highcharts-drillup-button").attr({align:c.position.align,zIndex:7}).add().align(c.position,!1,c.relativeTo||"plotBox"))},buildKDTree:l,drawLegendSymbol:d.LegendSymbolMixin.drawRectangle,
getExtremes:function(){x.prototype.getExtremes.call(this,this.colorValueData);this.valueMin=this.dataMin;this.valueMax=this.dataMax;x.prototype.getExtremes.call(this)},getExtremesFromAll:!0,bindAxes:function(){var a={endOnTick:!1,gridLineWidth:0,lineWidth:0,min:0,dataMin:0,minPadding:0,max:100,dataMax:100,maxPadding:0,startOnTick:!1,title:null,tickPositions:[]};x.prototype.bindAxes.call(this);d.extend(this.yAxis.options,a);d.extend(this.xAxis.options,a)},utils:{recursive:B,reduce:C}},{getClassName:function(){var a=
d.Point.prototype.getClassName.call(this),b=this.series,c=b.options;this.node.level<=b.nodeMap[b.rootNode].level?a+=" highcharts-above-level":this.node.isLeaf||n(c.interactByLeaf,!c.allowDrillToNode)?this.node.isLeaf||(a+=" highcharts-internal-node"):a+=" highcharts-internal-node-interactive";return a},isValid:function(){return this.id||w(this.value)},setState:function(a){d.Point.prototype.setState.call(this,a);this.graphic&&this.graphic.attr({zIndex:"hover"===a?1:0})},setVisible:r.pie.prototype.pointClass.prototype.setVisible})})(q,
D)});
