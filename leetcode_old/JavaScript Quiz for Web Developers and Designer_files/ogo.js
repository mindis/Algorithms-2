/* 9.0.3 (ea4cb59) Fri, 12 Aug 2016 17:40:43 GMT */
var _$OGO$_;!function(){"use strict";if("object"!=typeof _$OGO$_){var _ogo,buildQueryString,getIMG,isEncoded,isCreativeReady=!1,isArray=function(e){return Array.hasOwnProperty("isArray")?Array.isArray(e):"[object Array]"===Object.prototype.toString.call(e)},creativeReadyCallbacks=[],eventCodes={MRAID_NOT_VISIBLE:"96",MRAID_VISIBLE:"95",OGO_GENERAL_ERROR:"10000",REPORT_MRAID_STATE:"10001",OGO_GENERAL_WARNING:"10002",OGO_DIAGNOSTIC:"10005",OGO_DIAGNOSTIC_2:"10006",OGO_MRAID_AVAILABLE:"10010",OGO_MRAID_NOT_AVAILABLE:"10011",OGO_EVAL_ERROR:"10013",OGO_MRAID_READY:"10020",OGO_MRAID_NOT_READY:"10030",OGO_MRAID_NOW_READY:"10040",OGO_CUSTOM_HTML_ADDED:"10050",OGO_LOADED:"10100",OGO_TRY_CATCH_ERROR:"10113",OGO_CREATIVE_LOADED:"10200"};Object.freeze&&Object.freeze(eventCodes),_ogo={VERSION:"9",shared:"shared",JSON:JSON,trace:window.console&&console.log||function(e){},_pixels:[]},_ogo.Rosetta=_ogo.Rosetta||{},getIMG=function(e){if(!e||"string"!=typeof e)return null;var t=Math.floor(1e6*Math.random()),i=e.indexOf("?")>-1?"&":"?",r=new Image;return r.src=e+i+"_cb="+t,r},isEncoded=function(e){try{return"string"==typeof e&&decodeURIComponent(e)!==e}catch(t){return!1}},buildQueryString=function(e){var t,i=encodeURIComponent,r=[];for(t in e)""!==t&&e.hasOwnProperty(t)&&"undefined"!=typeof e[t]&&(isEncoded(e[t])?r.push(i(t)+"="+e[t]):r.push(i(t)+"="+i(e[t])));return r.join("&")},_ogo.isDebugEnabled=function(){return 1==(document.cookie.match(/(^|; )cnvrdebug=([^;]*)/)||0)[2]},_ogo.setDebug=function(e){return document.cookie="cnvrdebug="+e+"; expires=Tue, 19 Jan 2038 00:00:00 GMT; path=/;",_ogo.isDebugEnabled()},_ogo.debugError=function(e){return window.console&&this.isDebugEnabled()&&console.error(e),e},_ogo.debugLog=function(e,t){var i="",r="";return this.isDebugEnabled()&&window.console&&(window.mocha?t="":(i="%c",t=t||"color:#FFED96;background-color:#000",r=this.dmo.trid?"(trid:"+this.dmo.trid+"): ":""),console.log(i+"[OGO "+_ogo.VERSION+"] "+r+e,t)),e},_ogo.getIMG=getIMG,_ogo.buildQueryString=buildQueryString,_ogo.onCreativeReady=function(e){return"function"!=typeof e?(_ogo.dmo.logError("Invalid function for _ogo.onCreativeReady"),!1):(isCreativeReady?e():creativeReadyCallbacks.push(e),!0)},_ogo.executeCustomHtml=function(){var e,t,i,r,n=_ogo.dmo;e=function(e){if(!isArray(e))return null;var t={};return Array.prototype.filter?e.filter(function(e){return!t[e]&&(t[e]=!0,e)}):e};var o=function(e,t){if(Array.prototype.indexOf)return t.indexOf(e);for(var i=0;i<t.length;i++)if(e===t[i])return i;return-1};for(n.customHtmlQueue=e(n.customHtmlQueue),r=n.customHtmlQueue.length||0,i=0;i<r;i++)if(t=n.customHtmlQueue[i],!(o(t,n.additionalHtml)>=0)){var a,d,s;"complete"===document.readyState||"interactive"===document.readyState?(d=document.createElement("ins"),d.innerHTML=t,document.currentScript?a=document.currentScript:(s=document.querySelectorAll("script"),a=s[s.length-1]),a.parentNode.insertBefore(d,a.nextSibling),this.dmo.logEvent(eventCodes.OGO_CUSTOM_HTML_ADDED,"After Page Load"),this.debugLog("Custom HTML Added (After Page Load):\n"+t)):(document.writeln(t),this.dmo.logEvent(eventCodes.OGO_CUSTOM_HTML_ADDED,"During Page Load"),this.debugLog("Custom HTML Added (During Page Load):\n"+t)),n.additionalHtml.push(t)}n.customHtmlQueue=[]},_ogo.addCustomHtml=function(e){return this.dmo&&isArray(this.dmo.customHtmlQueue)?this.dmo.customHtmlQueue.push(e):this.debugError(new Error("dmo has not been embedded")),_ogo.executeCustomHtml(),this.logMraidStatus(),!0},_ogo.firePixel=function(e){return this._pixels.push(this.getIMG(e)),!0},_ogo.logMraidStatus=function(){return window.mraid&&"function"==typeof window.mraid.getState?(this.dmo.logEvent(eventCodes.OGO_MRAID_AVAILABLE,"MRAID available"),"loading"===mraid.getState()?(mraid.addEventListener&&mraid.addEventListener("ready",function(){_ogo.dmo.logEvent(eventCodes.OGO_MRAID_NOW_READY,"MRAID is NOW ready")}),this.dmo.logEvent(eventCodes.OGO_MRAID_NOT_READY,"MRAID was not ready")):this.dmo.logEvent(eventCodes.OGO_MRAID_READY,"MRAID is ready"),!0):(this.dmo.logEvent(eventCodes.OGO_MRAID_NOT_AVAILABLE,"MRAID not available"),!1)},_ogo.embed=function(dmo){var adInfoScript,doRequire,mraidController,dmoEmbedCallElem,MRAIDControl,onCreativeLoaded,req,requireLoader,rosetta;this.dmo=dmo,dmo.additionalHtml=dmo.additionalHtml||[],dmo.customHtmlQueue=dmo.customHtmlQueue||[],dmo._createdTime=+new Date,window.MRAID_TEST&&(dmo.device_id=MRAID_TEST.device_id,void 0!==MRAID_TEST.impNotify&&(dmo.impNotify=MRAID_TEST.impNotify),dmo.nurl=MRAID_TEST.nurl,dmo.spDebug=MRAID_TEST.spDebug),dmo.flag={},dmo.eventIMGs=[],dmo.onceEventIDs={},dmo._eventCompleteCounter=0,dmo.opChk=function(e){var t,i=+new Date;return this.flag[e]?(t=i-this.flag[e],this.flag[e]=i,t>300):(this.flag[e]=i,!0)},dmo.open=function(e,t,i){var r=window;return e?(this.device_id&&(window.mraid?r=window.mraid:this.openHREF(e,t)?r=null:window.RAC&&(r=window.RAC)),r&&r.open?(t=t||"_blank",r.open(e,t,i),r):(this.logError("Can't Open Window: "+e),null)):null},dmo.isIOS=function(){return!!navigator.userAgent.match(/(iPad|iPhone|iPod)/g)},dmo.openHREF=function(e,t){var i;return!!this.isIOS()&&(i=document.createElement("a"),i.href=e,i.target=t||"_blank",document.body.appendChild(i),i.click(),document.body.removeChild(i),!0)},dmo.handleCommand=function(command,indexArray,fs,userAgent){var i,frame,rurl,phase,queryParams,url;if(userAgent=userAgent||navigator.userAgent,"_self"===fs&&userAgent.indexOf("Chrome")!==-1)return!1;if(command.indexOf("FSCommand")!==-1&&(command=command.split(":")[1]),!this.opChk(command))return!1;switch(command){case"clickprev":case"click":if(phase="click"===command?20:10,this.clickable===!1){dmo.logEvent(eventCodes.OGO_TRY_CATCH_ERROR,"clickable was set to false");break}i=0,frame=0,rurl="";try{indexArray=eval(indexArray)}catch(e){dmo.logEvent(eventCodes.OGO_EVAL_ERROR,dmo._eventCompleteCounter)}if(isArray(indexArray)&&indexArray.length)if(i=indexArray[0],indexArray.length>1&&(frame=indexArray[1]),indexArray.length>2){if(null===indexArray[2]||null===indexArray[2].match(/^(http)/))break;rurl=fs?indexArray[2]:encodeURI(indexArray[2])}else rurl=this.clickURL[i];else i=0,rurl=this.clickURL[0],frame=0;this.rurl=rurl,this.open(this.getRurl(i,phase,rurl,frame),"_blank");break;case"menuClick":queryParams={cname:this.companyName||"",cid:this.companyId||"",cmagic:this.companyMagic||"",msgid:this.msgCampId||""},url=this.menuURL+"?"+this.buildQueryString(queryParams),window.open(url,"userprefs","status=0,toolbar=0,location=0,menubar=0,directories=0,scrollbars=1,width=640,height=790");break;case"banner_load":case"banner_show":case"banner_live":case"menu_info":case"template_info":_ogo.debugLog("No-op command handled: "+command);break;default:if(null===command||null===command.match(/^(http)/)){_ogo.debugLog("Null command handled");break}this.open(command,"_self",indexArray)}return!0},dmo.getRurl=function(e,t,i,r){var n,o=isArray(this.clickURL)&&this.clickURL[0];if(!this.eventURL)return this.logError("Missing eventURL"),!1;if(this.cturl&&0===this.cturl.indexOf("http")){this.cturlIMG=new Image;try{this.cturlIMG.src=decodeURI(this.cturl)}catch(a){this.cturlIMG.src=this.cturl,_ogo.debugError(a)}}return this.clickURLCode&&this.clickURLCode.length>=e+1&&dmo.logEvent(this.clickURLCode[e]),n={opid:"2",rurli:e,phase:t||"",frame:r||"",rurl:i||o||""},this.eventURL+"&"+this.buildQueryString(n)},dmo.getIMG=getIMG,dmo.buildQueryString=buildQueryString,dmo.doViewNotify=function(){this.viewNotify&&"string"==typeof this.viewNotify&&(this.viewNotifyIMG=this.getIMG(this.viewNotify))},dmo.doImprNotify=function(){return!(this.nurl!==!1||!this.impNotify||this.imprNotifyIMG)&&(this.imprNotifyIMG=this.getIMG(this.impNotify),!0)},dmo.shouldUseVapi=function(){return!1},dmo.logEvent=function(e,t){var i,r=_$OGO$_.dmo,n=e===eventCodes.OGO_GENERAL_ERROR?"color:red;background-color:#000":"",o=null;if(i=t&&t.length?"; Detail: "+t:"",_ogo.debugLog("Event "+e+" Fired"+i,n),r.viewabilityPixelUrl){var a,d=new Date-r._createdTime,s={etype:e||"",edtl:t||"",vtime:d,ver:_ogo.VERSION},u=r.buildQueryString(s);a=r.viewabilityPixelUrl+"?"+u,r.eventIMGs.push(r.getIMG(a)),o=a}return o},dmo.logEventOnce=function(e,t){try{if(!this.onceEventIDs[e])return this.onceEventIDs[e]=!0,this.logEvent(e,t),!0}catch(i){_ogo.debugError(i)}return!1},dmo.logError=function(e,t){var i,r,n="(Stack Unavailable)";_$OGO$_.dmo.logEvent(eventCodes.OGO_GENERAL_ERROR,e||""),_$OGO$_.isDebugEnabled()&&(t instanceof Error?(r=t.stack||n,_$OGO$_.debugError(t)):r=(new Error).stack||n,i="[JS ERROR]: "+e+"\n"+r,alert(i))},dmo.logWarning=function(e){_$OGO$_.dmo.logEvent(eventCodes.OGO_GENERAL_WARNING,e)},MRAIDControl=function(){var e,t,i;if(this.creative=null,window.mraid&&"function"==typeof mraid.getState){if(this.hasMraid=!0,e=mraid.getState(),t="undefined","function"==typeof mraid.isViewable)try{t=mraid.isViewable()}catch(r){_ogo.debugError(r),t="exception"}if(i="undefined",mraid.getPlacementType)try{i=mraid.getPlacementType()}catch(r){_ogo.debugError(r),i="exception"}dmo.logEvent(eventCodes.REPORT_MRAID_STATE,e+": "+t+":"+i)}else this.hasMraid=!1},mraidController=new MRAIDControl,mraidController.checkReady=function(){var e;return!this.hasMraid||("default"===mraid.getState()&&"function"==typeof mraid.isViewable&&(e="true"===mraid.isViewable().toString()),e)},mraidController.onStateChange=function(){if(this.creative){var e=this.checkReady();"function"==typeof this.creative.enviromentReady&&this.creative.enviromentReady(e),this.hasMraid&&e&&(dmo.doImprNotify(),dmo.logEventOnce(eventCodes.MRAID_VISIBLE))}else dmo.logWarning("mraidController.onStateChange: missing creative")},mraidController.onCreativeReady=function(e){return this.creative=e,this.onStateChange(),!(!this.hasMraid||!mraid.addEventListener)&&("function"==typeof mraid.getState&&"loading"===mraid.getState()&&mraid.addEventListener("ready",function(){mraidController.onStateChange()}),mraid.addEventListener("stateChange",function(e){mraidController.onMraidStateChange(e)}),mraid.addEventListener("viewableChange",function(e){mraidController.onMraidViewChange(e)}),!0)},mraidController.onMraidViewChange=function(e){try{this.onStateChange()}catch(t){_ogo.debugError(t)}!e&&this.creative&&dmo.logEventOnce(eventCodes.MRAID_NOT_VISIBLE)},mraidController.onMraidStateChange=function(e){try{this.onStateChange()}catch(t){_ogo.debugError(t)}"hidden"===e&&this.creative&&dmo.logEventOnce(eventCodes.MRAID_NOT_VISIBLE)};var init=function(){var e=dmo.embedId?"#"+dmo.embedId+"_call":"UNDEFINED";try{if(dmo.doViewNotify(),dmoEmbedCallElem=document.querySelector(e),!dmoEmbedCallElem)return dmo.logError("ogo: embedId element does not exist: "+e),!1;req=[],dmo.objectSrc&&req.push(dmo.objectSrc),dmo.shouldUseVapi()&&req.push(dmo.viewabilityScriptUrl),dmo.embedElement=document.createElement("span"),dmo.embedElement.id=dmo.embedId,dmoEmbedCallElem.parentNode.insertBefore(dmo.embedElement,dmoEmbedCallElem),dmo.menuURL&&(dmo.embedElement=document.createElement("span"),dmo.embedElement.id=dmo.trid||"",adInfoScript=document.createElement("script"),adInfoScript.src=dmo.menuURL,adInfoScript.type="text/javascript",dmo.embedElement.appendChild(adInfoScript),dmoEmbedCallElem.parentNode.insertBefore(dmo.embedElement,dmoEmbedCallElem)),rosetta=_ogo.Rosetta,requireLoader=rosetta.requireJSLoader,doRequire=function(e,t,i,r){requireLoader.onError=i||function(e){dmo.logError(e)},requireLoader.config(r||{waitSeconds:10}),requireLoader(e,t)},onCreativeLoaded=function(){var e,t,i,r;dmo._eventCompleteCounter++,dmo.shouldUseVapi()?dmo.vapi=new _$OGO$_.vapi(dmo):dmo.vapi={},rosetta.loadedCreatives=rosetta.loadedCreatives||[],t=rosetta.creatives,t&&0!==t.length?(dmo._eventCompleteCounter>1&&dmo.logEvent(eventCodes.OGO_DIAGNOSTIC_2,dmo._eventCompleteCounter),i=t.shift(),rosetta.loadedCreatives.push({creative:i,dmo:dmo}),e=new i(dmo),r=function(){var t=0;for(mraidController.onCreativeReady(e),isCreativeReady=!0;t<creativeReadyCallbacks.length;t++)try{"function"==typeof creativeReadyCallbacks[t]&&creativeReadyCallbacks[t]()}catch(i){this.logError("onCreativeReady: error running creativeReadyCallbacks",i)}},"function"==typeof e.registerCallback?(e.registerCallback("creative_ready",r),e.enviromentReady(mraidController.checkReady())):r(),"function"==typeof e.init?(e.init(dmo.embedId,doRequire),dmo.logEvent(eventCodes.OGO_CREATIVE_LOADED,"Creative Loaded")):dmo.logError("onCreativeLoaded: creative.init is not a function")):dmo.logEvent(eventCodes.OGO_DIAGNOSTIC,dmo._eventCompleteCounter)},doRequire(req,onCreativeLoaded),dmo.logEvent(eventCodes.OGO_LOADED,"OGO Initialized")}catch(t){dmo.logError("Init Error",t)}};init()};var requirejs,require,define;_ogo.Rosetta.requireJSLoader=function(ha){function L(e){return"[object Function]"===R.call(e)}function M(e){return"[object Array]"===R.call(e)}function x(e,t){if(e){var i;for(i=0;i<e.length&&(!e[i]||!t(e[i],i,e));i+=1);}}function Y(e,t){if(e){var i;for(i=e.length-1;-1<i&&(!e[i]||!t(e[i],i,e));--i);}}function w(e,t){return la.call(e,t)}function g(e,t){return w(e,t)&&e[t]}function E(e,t){for(var i in e)if(w(e,i)&&t(e[i],i))break}function Z(e,t,i,r){return t&&E(t,function(t,n){!i&&w(e,n)||(!r||"object"!=typeof t||!t||M(t)||L(t)||t instanceof RegExp?e[n]=t:(e[n]||(e[n]={}),Z(e[n],t,i,r)))}),e}function y(e,t){return function(){return t.apply(e,arguments)}}function ia(e){throw e}function ja(e){if(!e)return e;var t=ha;return x(e.split("."),function(e){t=t[e]}),t}function G(e,t,i,r){return t=Error(t+"\nhttp://requirejs.org/docs/errors.html#"+e),t.requireType=e,t.requireModules=r,i&&(t.originalError=i),t}function ma(e){function t(e,t,i){var r,n,o,a,d,s,u,l;t=t&&t.split("/");var c=A.map,m=c&&c["*"];if(e){for(e=e.split("/"),n=e.length-1,A.nodeIdCompat&&V.test(e[n])&&(e[n]=e[n].replace(V,"")),"."===e[0].charAt(0)&&t&&(n=t.slice(0,t.length-1),e=n.concat(e)),n=e,o=0;o<n.length;o++)a=n[o],"."===a?(n.splice(o,1),--o):".."===a&&0!==o&&(1!==o||".."!==n[2])&&".."!==n[o-1]&&0<o&&(n.splice(o-1,2),o-=2);e=e.join("/")}if(i&&c&&(t||m)){n=e.split("/"),o=n.length;e:for(;0<o;--o){if(d=n.slice(0,o).join("/"),t)for(a=t.length;0<a;--a)if((i=g(c,t.slice(0,a).join("/")))&&(i=g(i,d))){r=i,s=o;break e}!u&&m&&g(m,d)&&(u=g(m,d),l=o)}!r&&u&&(r=u,s=l),r&&(n.splice(0,s,r),e=n.join("/"))}return(r=g(A.pkgs,e))?r:e}function i(e){F&&x(document.getElementsByTagName("script"),function(t){if(t.getAttribute("data-requiremodule")===e&&t.getAttribute("data-requirecontext")===_.contextName)return t.parentNode.removeChild(t),!0})}function r(e){var t=g(A.paths,e);if(t&&M(t)&&1<t.length)return t.shift(),_.require.undef(e),_.makeRequire(null,{skipMap:!0})([e]),!0}function n(e){var t,i=e?e.indexOf("!"):-1;return-1<i&&(t=e.substring(0,i),e=e.substring(i+1,e.length)),[t,e]}function o(e,i,r,o){var a,d,s=null,u=i?i.name:null,l=e,c=!0,m="";return e||(c=!1,e="_@r"+(Q+=1)),e=n(e),s=e[0],e=e[1],s&&(s=t(s,u,o),d=g(N,s)),e&&(s?m=d&&d.normalize?d.normalize(e,function(e){return t(e,u,o)}):-1===e.indexOf("!")?t(e,u,o):e:(m=t(e,u,o),e=n(m),s=e[0],m=e[1],r=!0,a=_.nameToUrl(m))),r=!s||d||r?"":"_unnormalized"+(P+=1),{prefix:s,name:m,parentMap:i,unnormalized:!!r,url:a,originalName:l,isDefine:c,id:(s?s+"!"+m:m)+r}}function a(e){var t=e.id,i=g(I,t);return i||(i=I[t]=new _.Module(e)),i}function d(e,t,i){var r=e.id,n=g(I,r);!w(N,r)||n&&!n.defineEmitComplete?(n=a(e),n.error&&"error"===t?i(n.error):n.on(t,i)):"defined"===t&&i(N[r])}function s(e,t){var i=e.requireModules,r=!1;t?t(e):(x(i,function(t){(t=g(I,t))&&(t.error=e,t.events.error&&(r=!0,t.emit("error",e)))}),r||k.onError(e))}function u(){W.length&&(x(W,function(e){var t=e[0];"string"==typeof t&&(_.defQueueMap[t]=!0),q.push(e)}),W=[])}function l(e){delete I[e],delete D[e]}function c(e,t,i){var r=e.map.id;e.error?e.emit("error",e.error):(t[r]=!0,x(e.depMaps,function(r,n){var o=r.id,a=g(I,o);!a||e.depMatched[n]||i[o]||(g(t,o)?(e.defineDep(n,N[o]),e.check()):c(a,t,i))}),i[r]=!0)}function m(){var e,t,n=(e=1e3*A.waitSeconds)&&_.startTime+e<(new Date).getTime(),o=[],a=[],d=!1,u=!0;if(!v){if(v=!0,E(D,function(e){var s=e.map,l=s.id;if(e.enabled&&(s.isDefine||a.push(e),!e.error))if(!e.inited&&n)r(l)?d=t=!0:(o.push(l),i(l));else if(!e.inited&&e.fetched&&s.isDefine&&(d=!0,!s.prefix))return u=!1}),n&&o.length)return e=G("timeout","Load timeout for modules: "+o,null,o),e.contextName=_.contextName,s(e);u&&x(a,function(e){c(e,{},{})}),n&&!t||!d||!F&&!ka||R||(R=setTimeout(function(){R=0,m()},50)),v=!1}}function f(e){w(N,e[0])||a(o(e[0],null,!0)).init(e[1],e[2])}function h(e){e=e.currentTarget||e.srcElement;var t=_.onScriptLoad;return e.detachEvent&&!da?e.detachEvent("onreadystatechange",t):e.removeEventListener("load",t,!1),t=_.onScriptError,e.detachEvent&&!da||e.removeEventListener("error",t,!1),{node:e,id:e&&e.getAttribute("data-requiremodule")}}function p(){var e;for(u();q.length;){if(e=q.shift(),null===e[0])return s(G("mismatch","Mismatched anonymous define() module: "+e[e.length-1]));f(e)}_.defQueueMap={}}var v,b,_,C,R,A={waitSeconds:7,baseUrl:"./",paths:{},bundles:{},pkgs:{},shim:{},config:{}},I={},D={},S={},q=[],N={},U={},j={},Q=1,P=1;return C={require:function(e){return e.require?e.require:e.require=_.makeRequire(e.map)},exports:function(e){if(e.usingExports=!0,e.map.isDefine)return e.exports?N[e.map.id]=e.exports:e.exports=N[e.map.id]={}},module:function(e){return e.module?e.module:e.module={id:e.map.id,uri:e.map.url,config:function(){return g(A.config,e.map.id)||{}},exports:e.exports||(e.exports={})}}},b=function(e){this.events=g(S,e.id)||{},this.map=e,this.shim=g(A.shim,e.id),this.depExports=[],this.depMaps=[],this.depMatched=[],this.pluginMaps={},this.depCount=0},b.prototype={init:function(e,t,i,r){r=r||{},this.inited||(this.factory=t,i?this.on("error",i):this.events.error&&(i=y(this,function(e){this.emit("error",e)})),this.depMaps=e&&e.slice(0),this.errback=i,this.inited=!0,this.ignore=r.ignore,r.enabled||this.enabled?this.enable():this.check())},defineDep:function(e,t){this.depMatched[e]||(this.depMatched[e]=!0,--this.depCount,this.depExports[e]=t)},fetch:function(){if(!this.fetched){this.fetched=!0,_.startTime=(new Date).getTime();var e=this.map;if(!this.shim)return e.prefix?this.callPlugin():this.load();_.makeRequire(this.map,{enableBuildCallback:!0})(this.shim.deps||[],y(this,function(){return e.prefix?this.callPlugin():this.load()}))}},load:function(){var e=this.map.url;U[e]||(U[e]=!0,_.load(this.map.id,e))},check:function(){if(this.enabled&&!this.enabling){var e,t,i=this.map.id;t=this.depExports;var r=this.exports,n=this.factory;if(this.inited){if(this.error)this.emit("error",this.error);else if(!this.defining){if(this.defining=!0,1>this.depCount&&!this.defined){if(L(n)){try{r=_.execCb(i,n,t,r)}catch(o){e=o}if(this.map.isDefine&&void 0===r&&((t=this.module)?r=t.exports:this.usingExports&&(r=this.exports)),e){if(this.events.error&&this.map.isDefine||k.onError!==ia)return e.requireMap=this.map,e.requireModules=this.map.isDefine?[this.map.id]:null,e.requireType=this.map.isDefine?"define":"require",s(this.error=e);"undefined"!=typeof console&&console.error?console.error(e):k.onError(e)}}else r=n;if(this.exports=r,this.map.isDefine&&!this.ignore&&(N[i]=r,k.onResourceLoad)){var a=[];x(this.depMaps,function(e){a.push(e.normalizedMap||e)}),k.onResourceLoad(_,this.map,a)}l(i),this.defined=!0}this.defining=!1,this.defined&&!this.defineEmitted&&(this.defineEmitted=!0,this.emit("defined",this.exports),this.defineEmitComplete=!0)}}else w(_.defQueueMap,i)||this.fetch()}},callPlugin:function(){var e=this.map,i=e.id,r=o(e.prefix);this.depMaps.push(r),d(r,"defined",y(this,function(r){var n,u,c=g(j,this.map.id),m=this.map.name,f=this.map.parentMap?this.map.parentMap.name:null,h=_.makeRequire(e.parentMap,{enableBuildCallback:!0});this.map.unnormalized?(r.normalize&&(m=r.normalize(m,function(e){return t(e,f,!0)})||""),u=o(e.prefix+"!"+m,this.map.parentMap),d(u,"defined",y(this,function(e){this.map.normalizedMap=u,this.init([],function(){return e},null,{enabled:!0,ignore:!0})})),(r=g(I,u.id))&&(this.depMaps.push(u),this.events.error&&r.on("error",y(this,function(e){this.emit("error",e)})),r.enable())):c?(this.map.url=_.nameToUrl(c),this.load()):(n=y(this,function(e){this.init([],function(){return e},null,{enabled:!0})}),n.error=y(this,function(e){this.inited=!0,this.error=e,e.requireModules=[i],E(I,function(e){0===e.map.id.indexOf(i+"_unnormalized")&&l(e.map.id)}),s(e)}),n.fromText=y(this,function(t,r){var d=e.name,u=o(d),l=T;r&&(t=r),l&&(T=!1),a(u),w(A.config,i)&&(A.config[d]=A.config[i]);try{k.exec(t)}catch(c){return s(G("fromtexteval","fromText eval for "+i+" failed: "+c,c,[i]))}l&&(T=!0),this.depMaps.push(u),_.completeLoad(d),h([d],n)}),r.load(e.name,h,n,A))})),_.enable(r,this),this.pluginMaps[r.id]=r},enable:function(){D[this.map.id]=this,this.enabling=this.enabled=!0,x(this.depMaps,y(this,function(e,t){var i,r;if("string"==typeof e){if(e=o(e,this.map.isDefine?this.map:this.map.parentMap,!1,!this.skipMap),this.depMaps[t]=e,i=g(C,e.id))return void(this.depExports[t]=i(this));this.depCount+=1,d(e,"defined",y(this,function(e){this.undefed||(this.defineDep(t,e),this.check())})),this.errback?d(e,"error",y(this,this.errback)):this.events.error&&d(e,"error",y(this,function(e){this.emit("error",e)}))}i=e.id,r=I[i],w(C,i)||!r||r.enabled||_.enable(e,this)})),E(this.pluginMaps,y(this,function(e){var t=g(I,e.id);t&&!t.enabled&&_.enable(e,this)})),this.enabling=!1,this.check()},on:function(e,t){var i=this.events[e];i||(i=this.events[e]=[]),i.push(t)},emit:function(e,t){x(this.events[e],function(e){e(t)}),"error"===e&&delete this.events[e]}},_={config:A,contextName:e,registry:I,defined:N,urlFetched:U,defQueue:q,defQueueMap:{},Module:b,makeModuleMap:o,nextTick:k.nextTick,onError:s,configure:function(e){e.baseUrl&&"/"!==e.baseUrl.charAt(e.baseUrl.length-1)&&(e.baseUrl+="/");var t=A.shim,i={paths:!0,bundles:!0,config:!0,map:!0};E(e,function(e,t){i[t]?(A[t]||(A[t]={}),Z(A[t],e,!0,!0)):A[t]=e}),e.bundles&&E(e.bundles,function(e,t){x(e,function(e){e!==t&&(j[e]=t)})}),e.shim&&(E(e.shim,function(e,i){M(e)&&(e={deps:e}),!e.exports&&!e.init||e.exportsFn||(e.exportsFn=_.makeShimExports(e)),t[i]=e}),A.shim=t),e.packages&&x(e.packages,function(e){var t;e="string"==typeof e?{name:e}:e,t=e.name,e.location&&(A.paths[t]=e.location),A.pkgs[t]=e.name+"/"+(e.main||"main").replace(na,"").replace(V,"")}),E(I,function(e,t){e.inited||e.map.unnormalized||(e.map=o(t,null,!0))}),(e.deps||e.callback)&&_.require(e.deps||[],e.callback)},makeShimExports:function(e){return function(){var t;return e.init&&(t=e.init.apply(ha,arguments)),t||e.exports&&ja(e.exports)}},makeRequire:function(r,n){function d(t,i,u){var l,c;return n.enableBuildCallback&&i&&L(i)&&(i.__requireJsBuild=!0),"string"==typeof t?L(i)?s(G("requireargs","Invalid require call"),u):r&&w(C,t)?C[t](I[r.id]):k.get?k.get(_,t,r,d):(l=o(t,r,!1,!0),l=l.id,w(N,l)?N[l]:s(G("notloaded",'Module name "'+l+'" has not been loaded yet for context: '+e+(r?"":". Use require([])")))):(p(),_.nextTick(function(){p(),c=a(o(null,r)),c.skipMap=n.skipMap,c.init(t,i,u,{enabled:!0}),m()}),d)}return n=n||{},Z(d,{isBrowser:F,toUrl:function(e){var i,n=e.lastIndexOf("."),o=e.split("/")[0];return-1!==n&&("."!==o&&".."!==o||1<n)&&(i=e.substring(n,e.length),e=e.substring(0,n)),_.nameToUrl(t(e,r&&r.id,!0),i,!0)},defined:function(e){return w(N,o(e,r,!1,!0).id)},specified:function(e){return e=o(e,r,!1,!0).id,w(N,e)||w(I,e)}}),r||(d.undef=function(e){u();var t=o(e,r,!0),n=g(I,e);n.undefed=!0,i(e),delete N[e],delete U[t.url],delete S[e],Y(q,function(t,i){t[0]===e&&q.splice(i,1)}),delete _.defQueueMap[e],n&&(n.events.defined&&(S[e]=n.events),l(e))}),d},enable:function(e){g(I,e.id)&&a(e).enable()},completeLoad:function(e){var t,i,n=g(A.shim,e)||{},o=n.exports;for(u();q.length;){if(i=q.shift(),null===i[0]){if(i[0]=e,t)break;t=!0}else i[0]===e&&(t=!0);f(i)}if(_.defQueueMap={},i=g(I,e),!t&&!w(N,e)&&i&&!i.inited){if(!(!A.enforceDefine||o&&ja(o)))return r(e)?void 0:s(G("nodefine","No define call for "+e,null,[e]));f([e,n.deps||[],n.exportsFn])}m()},nameToUrl:function(e,t,i){var r,n,o;if((r=g(A.pkgs,e))&&(e=r),r=g(j,e))return _.nameToUrl(r,t,i);if(k.jsExtRegExp.test(e))r=e+(t||"");else{for(r=A.paths,e=e.split("/"),n=e.length;0<n;--n)if(o=e.slice(0,n).join("/"),o=g(r,o)){M(o)&&(o=o[0]),e.splice(0,n,o);break}r=e.join("/"),r+=t||(/^data\:|\?/.test(r)||i?"":".js"),r=("/"===r.charAt(0)||r.match(/^[\w\+\.\-]+:/)?"":A.baseUrl)+r}return A.urlArgs?r+((-1===r.indexOf("?")?"?":"&")+A.urlArgs):r},load:function(e,t){k.load(_,e,t)},execCb:function(e,t,i,r){return t.apply(r,i)},onScriptLoad:function(e){("load"===e.type||oa.test((e.currentTarget||e.srcElement).readyState))&&(O=null,e=h(e),_.completeLoad(e.id))},onScriptError:function(e){var t=h(e);if(!r(t.id)){var i=[];return E(I,function(e,r){0!==r.indexOf("_@r")&&x(e.depMaps,function(e){return e.id===t.id&&i.push(r),!0})}),s(G("scripterror",'Script error for "'+t.id+(i.length?'", needed by: '+i.join(", "):'"'),e,[t.id]))}}},_.require=_.makeRequire(),_}function pa(){return O&&"interactive"===O.readyState?O:(Y(document.getElementsByTagName("script"),function(e){if("interactive"===e.readyState)return O=e}),O)}var k,C,D,I,P,J,O,Q,u,U,qa=/(\/\*([\s\S]*?)\*\/|([^:]|^)\/\/(.*)$)/gm,ra=/[^.]\s*require\s*\(\s*["']([^'"\s]+)["']\s*\)/g,V=/\.js$/,na=/^\.\//;C=Object.prototype;var R=C.toString,la=C.hasOwnProperty,F=!("undefined"==typeof window||"undefined"==typeof navigator||!window.document),ka=!F&&"undefined"!=typeof importScripts,oa=F&&"PLAYSTATION 3"===navigator.platform?/^complete$/:/^(complete|loaded)$/,da="undefined"!=typeof opera&&"[object Opera]"===opera.toString(),K={},v={},W=[],T=!1;if("undefined"==typeof define){if("undefined"!=typeof requirejs){if(L(requirejs))return;v=requirejs,requirejs=void 0}return"undefined"==typeof require||L(require)||(v=require,require=void 0),k=requirejs=function(e,t,i,r){var n,o="_";return M(e)||"string"==typeof e||(n=e,M(t)?(e=t,t=i,i=r):e=[]),n&&n.context&&(o=n.context),(r=g(K,o))||(r=K[o]=k.s.newContext(o)),n&&r.configure(n),r.require(e,t,i)},k.config=function(e){return k(e)},k.nextTick="undefined"!=typeof setTimeout?function(e){setTimeout(e,4)}:function(e){e()},require||(require=k),k.version="2.1.22",k.jsExtRegExp=/^\/|:|\?|\.js$/,k.isBrowser=F,C=k.s={contexts:K,newContext:ma},k({}),x(["toUrl","undef","defined","specified"],function(e){k[e]=function(){var t=K._;return t.require[e].apply(t,arguments)}}),F&&(D=C.head=document.getElementsByTagName("head")[0],I=document.getElementsByTagName("base")[0])&&(D=C.head=I.parentNode),k.onError=ia,k.createNode=function(e,t,i){return t=e.xhtml?document.createElementNS("http://www.w3.org/1999/xhtml","html:script"):document.createElement("script"),t.type=e.scriptType||"text/javascript",t.charset="utf-8",t.async=!0,t},k.load=function(e,t,i){var r,n=e&&e.config||{};if(F)return r=k.createNode(n,t,i),n.onNodeCreated&&n.onNodeCreated(r,n,t,i),r.setAttribute("data-requirecontext",e.contextName),r.setAttribute("data-requiremodule",t),!r.attachEvent||r.attachEvent.toString&&0>r.attachEvent.toString().indexOf("[native code")||da?(r.addEventListener("load",e.onScriptLoad,!1),r.addEventListener("error",e.onScriptError,!1)):(T=!0,r.attachEvent("onreadystatechange",e.onScriptLoad)),r.src=i,Q=r,I?D.insertBefore(r,I):D.appendChild(r),Q=null,r;if(ka)try{importScripts(i),e.completeLoad(t)}catch(o){e.onError(G("importscripts","importScripts failed for "+t+" at "+i,o,[t]))}},F&&!v.skipDataMain&&Y(document.getElementsByTagName("script"),function(e){if(D||(D=e.parentNode),P=e.getAttribute("data-main"))return u=P,v.baseUrl||(J=u.split("/"),u=J.pop(),U=J.length?J.join("/")+"/":"./",v.baseUrl=U),u=u.replace(V,""),k.jsExtRegExp.test(u)&&(u=P),v.deps=v.deps?v.deps.concat(u):[u],!0}),define=function(e,t,i){var r,n;"string"!=typeof e&&(i=t,t=e,e=null),M(t)||(i=t,t=null),!t&&L(i)&&(t=[],i.length&&(i.toString().replace(qa,"").replace(ra,function(e,i){t.push(i)}),t=(1===i.length?["require"]:["require","exports","module"]).concat(t))),T&&(r=Q||pa())&&(e||(e=r.getAttribute("data-requiremodule")),n=K[r.getAttribute("data-requirecontext")]),n?(n.defQueue.push([e,t,i]),n.defQueueMap[e]=!0):W.push([e,t,i])},define.amd={jQuery:!0},k.exec=function(b){return eval(b)},k(v),_ogo.Rosetta.require=require,_ogo.Rosetta.requirejs=requirejs,_ogo.Rosetta.define=define,require}}(this),_$OGO$_=_ogo}}();