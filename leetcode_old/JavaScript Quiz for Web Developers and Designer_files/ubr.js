/*! UBR 6.3.0 - 2016-09-14T14:56:11.044Z */
!function(t){function e(r){if(n[r])return n[r].exports;var o=n[r]={exports:{},id:r,loaded:!1};return t[r].call(o.exports,o,o.exports,e),o.loaded=!0,o.exports}var n={};return e.m=t,e.c=n,e.p="/current/",e(0)}([function(t,e,n){"use strict";var r=n(1),o="https://usadmm.dotomi.com/v?trid=-1&site_id=-1&pid=-1&dtmid=-1&comId=-1&tid=-1&msgCampId=-1&bidServerId=-1&supplyType=-1&ver=6",i=n(2),a=n(4)(i.REPORT_PKG),s=n(3).provider(i.REPORT_PKG),c=n(5).provider(i.REPORT_PKG),u=function(){var t=function(t){var e=t.getAttribute(i.CONFIG_ATTR);return(-1!==t.src.indexOf("current/ubr.js")||-1!==t.src.indexOf("6.3.0/ubr.js"))&&e.length>0};return n(8)(t)||{parentNode:null}},d=function(t){a&&i&&(a(i.ACTIONS.UBR_ERROR,{ver:i.VER,edtl:[i.EDTL,"Unknown err",t.lineNumber,t.columnNumber,t.fileName,t.message||t.description||-1].join(",")}),c._setBaseUrl(r(u().parentNode)||o))};(new Image).src=o+"&etype="+i.ACTIONS.UBR_PING.data+"&ver="+i.VER+"&edtl="+i.EDTL+",ping";try{n(9)(window,"beforeunload",function(){c._setBaseUrl(o)}),function(){var t,e=u(),d=n(11).fromScript(e);s.signature=parseInt(d.debugBitMask,10)||1,a(i.ACTIONS.UBR_CONFIG,{trid:d.trx,ver:i.VER,edtl:[i.EDTL,d.url,d.width,d.height,!!d.adTarget,d.targetWidth,d.targetHeight].join(",")}),a(i.ACTIONS.UBR_CONFIG_FULL,{ver:i.VER,edtl:[i.EDTL,e.getAttribute(i.CONFIG_ATTR)].join(",")}),t=e.parentNode.style,t.position="relative",t.width="100%",t.height="100%",n(15).loadAd(d),c._setBaseUrl(r(e.parentNode)||o)}()}catch(p){d(p)}},function(t,e){"use strict";var n=function(t,e){var n,r,o;for(n=t.getElementsByTagName("img"),r=0;r<n.length;r++)if(o=n.item(r).src,o.indexOf(e)>-1)return o;return""};t.exports=function(t){return n(t,"dotomi.com/v?").replace("&etype=9999","").replace("&edtl=ping","")}},function(t,e,n){"use strict";var r=n(3).make,o=window.location||document.location;t.exports={CONFIG_ATTR:"data-cnvr-ubr",RENDERED_ATTR:"data-cnvr-script-loaded",REQUEST_TIMEOUT:15e3,ALIGN:{CENTER:"center"},OGO_VER:8,VER:6,EDTL:"6.3.0"+(o?"https:"===o.protocol?",secure":",insecure":",-1"),BORDERLESS_STYLE:"overflow:hidden;margin:0;padding:0;border:0;",REPORT_PKG:"com.conversantmedia.ubr",APPNEXUS_REPORT_PKG:"com.conversantmedia.ubr.appnexus",MAX_URL_SIZE:2e3,FAILED_XHR_CALL:66,FAILED_JSONP_CALL:67,REMOTE_SANDBOX:1,LOCAL_SANDBOX:2,EMBEDDED:3,NATIVE_IN_APP:4,ACTIONS:{AD_LOAD_START:r(parseInt("000000000000000000001000000001",2),"9000"),AD_CONTENT_RECEIVED:r(parseInt("000000000000000000010000000001",2),"9100"),AD_LOADER_CHOSEN:r(parseInt("000000000000000000100000000001",2),"9010"),UBR_ERROR:r(parseInt("000000000000000001000000000001",2),"9013"),UBR_WARNING:r(parseInt("000000000000000010000000000001",2),"9014"),UBR_CONFIG:r(parseInt("000000000000000100000000000001",2),"9020"),UBR_CONFIG_FULL:r(parseInt("000000000000001000000000000001",2),"9021"),UBR_PING:r(parseInt("000000000000010000000000000001",2),"9099"),GEOMETRY_ERROR:r(parseInt("000000000000100000000000000001",2),"9213"),SAFEFRAME_CAPABILITIES:r(parseInt("000000000001000000000000000001",2),"9200"),APPNEXUS_LOADED_UBR:r(parseInt("000000000010000000000000000001",2),"9305"),APPNEXUS_FINISHED:r(parseInt("000000000100000000000000000001",2),"9310"),APPNEXUS_WARNING:r(parseInt("000000001000000000000000000001",2),"9314"),APPNEXUS_ERROR:r(parseInt("000000010000000000000000000001",2),"9313"),APPNEXUS_AUDIT_CALL:r(parseInt("000000100000000000000000000001",2),"9398"),APPNEXUS_AD_CALL:r(parseInt("000001000000000000000000000001",2),"9397"),APPNEXUS_SCRIPT_START:r(parseInt("000010000000000000000000000001",2),"9399"),JSONP_DECISION:r(parseInt("000100000000000000000000000001",2),"9402"),LOADER_ERROR:r(parseInt("001000000000000000000000000001",2),"9413"),JAC_LOADED:r(parseInt("010000000000000000000000000001",2),"9410"),OGO_LOADED:r(parseInt("100000000000000000000000000001",2),"9420")}}},function(t,e){"use strict";var n=function(t){if(t&&0===(t&t))throw new Error("Exceeded maximum number of bits allowed by browser: "+t);return!0},r=function(t,e){n(t),this.bit=t,this.data=e},o=function(t,e){return new r(t,e)},i=function(t,e,r){var o="function"==typeof r?r:function(){},i=!!(t.bit&e);try{n(e)}catch(a){return o(a),a}return o(i),i},a=function(){var t=this;t.signature=0,t.make=o,t.compare=function(e,n){return i(e,t.signature,n)}},s={},c=function(){return new a},u=function(t){return s[t]||(s[t]=new a),s[t]};t.exports.validate=n,t.exports.make=o,t.exports.compare=i,t.exports.factory=c,t.exports.provider=u},function(t,e,n){"use strict";t.exports=function(t){var e,r,o=n(2);return t=t||o.REPORT_PKG,e=n(5).provider(t),r=n(3).provider(t),function(t,n){r.compare(t)&&(n.etype=t.data,e.logWithElapsedTime(n))}}},function(t,e,n){"use strict";var r,o=n(6),i=n(7).has,a=function(t){var e=new XMLHttpRequest({timeout:500});e.open("GET",t,!1),e.onreadystatechange=function(){e.readyState>=i("OPENED",e)&&e.abort()};try{e.send()}catch(n){}return t},s=function(t){return(new Image).src=t,t},c=function(t){var e,n=[];for(e in t)t.hasOwnProperty(e)&&n.push(encodeURIComponent(e)+"="+encodeURIComponent(t[e]));return n.join("&")},u=function(t){return/\?/.test(t)?"&"!==t.charAt(t.length-1)&&(t+="&"):t+="?",t},d=function(t,e){var n=window.navigator;return e&&n.userAgent.toLowerCase().indexOf("safari")>-1?a(t):s(t),t},p={},f=function(t,e){var n=e||o.factory();return new r(t,n)},l=function(t){return p[t]||(p[t]=new r(null,o.provider(t))),p[t]};r=function(t,e){var n=this,r=[],o=function(){for(var e=r.shift();e;)d(t+e),e=r.shift()};n.log=function(e,n,o){var i=c(e);if(t||o){o&&(o=u(o));var a=(o||t)+i;return d(a,n)}return r.push(i),i},n.logWithElapsedTime=function(t,r,o){return t.vtime=e.now()-e.startTime,n.log(t,r,o)},n._setBaseUrl=function(e){i("console")&&console.info,t=u(e),o()},n._setBaseURL=n._setBaseUrl,t&&(t=u(t))},t.exports={factory:f,provider:l}},function(t,e,n){"use strict";var r=n(7).can,o=n(7).has,i=function(){var t,e,n,i=this,a=window,s=document,c=3,u=25e3,d=o("performance")||o("mozPerformance")||o("msPerformance")||o("webkitPerformance")||!1,p=!!d,f=r(d,"timing")?d.timing:{},l=r(d,"navigation")?d.navigation:{},E=function(t,e,n){var r,o=-1,i=[],a=function(n){return"undefined"!=typeof e[t[n]]?e[t[n]]:-1};for(r=a(0);++o<t.length;)i[o]=a(o),n&&i[o]>0&&(i[o]-=r);return i},h=function(t,e,n){var r="u",o="f",i="s",a=r;return n>=e-t&&e-t>0?a=o:e-t>n&&e-t>0&&(a=i),a};i.now=function(){var t;return t=d&&d.now?n+d.now():Date.now?Date.now():(new Date).getTime(),Math.round(+t)},n=p&&f.navigationStart?f.navigationStart:i.now(),i.report=function(){var i,v,m,g,R=[-1,-1,-1,-1,-1,-1];return t=E(["navigationStart","unloadEventStart","unloadEventEnd","redirectStart","redirectEnd","fetchStart","domainLookupStart","domainLookupEnd","connectStart","secureConnectionStart","connectEnd","requestStart","responseStart","responseEnd","domLoading","domInteractive","domContentLoadedEventStart","domContentLoadedEventEnd","domComplete","loadEventStart","loadEventEnd","msFirstPaint"],f,!0),e=E(["redirectCount","type"],l),v=E(["responseEnd","domComplete"],f,!0),p&&o("chrome")&&r(d,"memory")&&(m=d.memory,g=m.usedJSHeapSize.toExponential(),R[0]=+g.split(".")[0],R[1]=+g.split("+")[1],g=m.jsHeapSizeLimit.toExponential(),R[2]=+g.split(".")[0],R[3]=+g.split("+")[1],R[4]=+(m.usedJSHeapSize/m.totalJSHeapSize*100).toFixed(0),R[5]=+(m.usedJSHeapSize/m.jsHeapSizeLimit*100).toFixed(0)),i=c+","+t.join(",")+","+e.join(",")+","+s.readyState.charAt(0)+","+h(v[0],v[1],u)+","+a.frames.length+","+s.getElementsByTagName("script").length+","+R.join(",")+","+n},i.supported=!!d,i.startTime=n,i.sinceStart=function(t){var e=i.now()-n;return+(t?e.toFixed(3):Math.round(e))}},a={},s=function(){return new i},c=function(t){return a[t]||(a[t]=new i),a[t]};t.exports={factory:s,provider:c}},function(t,e){"use strict";var n=function(t,e){return"undefined"!=typeof t[e]},r=function(t,e){return e=e||window,n(e,t)?e[t]:!1},o=function(t,e){return t.hasOwnProperty(e)},i=function(t,e){var n=function(){return!1},r=e?t[e]:window[t];return"function"==typeof r?r:n},a=function(t,e){var n;for(n in t)if(t.hasOwnProperty(n)&&e(n,t[n])===!1)break};t.exports={can:n,has:r,run:i,own:o,forIn:a}},function(t,e){"use strict";var n="data-cnvr-script-loaded",r="started",o=function(t,e){var o,i,a,s=e||document.currentScript,c=null;if(t="function"==typeof t?t:function(){return!0},s&&"SCRIPT"===s.nodeName&&t(s)===!0)c=s;else for(o=document.getElementsByTagName("script"),a=o.length-1;a>=0;a--)if(i=o[a],!i.getAttribute(n)&&t(i)===!0){c=i;break}return c&&"SCRIPT"===c.nodeName&&c.setAttribute(n,r),c};t.exports=o,t.exports.LOAD_ATTR=n,t.exports.LOAD_STARTED=r},function(t,e){"use strict";t.exports=function(t,e,n){var r;return t&&t.addEventListener?(t.addEventListener(e,n,!1),r=function(){t.removeEventListener(e,n,!1)}):t&&t.attachEvent?(t.attachEvent("on"+e,n),r=function(){t.detachEvent("on"+e,n)}):r=function(){},r}},function(module,exports){"use strict";var evaluator=function(v){var r={value:void 0,errors:null};try{r.value=eval(v)}catch(err){r.errors=err}return r};module.exports=evaluator},function(t,e,n){"use strict";var r=n(2),o=(n(12),n(13)),i=n(7).forIn,a=n(14),s=(n(4)(r.REPORT_PKG),n(3).provider(r.REPORT_PKG)),c=function(t){return t.slice(0,t.lastIndexOf("/"))},u={hostname:"usadmm.dotomi.com",pathname:"/dmm/servlet/rtb",protocol:"https:"},d=function(t){t.query.cg||(t.query.cg="91"),t.hostname=t.hostname||u.hostname,t.pathname=t.pathname||u.pathname,t.protocol=t.protocol||u.protocol;var e=o(t);return e},p={width:0,height:0,adTarget:null,align:"",scriptEl:null,trx:"",type:0,ubrChosenLoader:0,overrideMode:0,pixel:"",url:"",baseUrl:"",successEventId:-1,trid:"",pnid:0,supplyType:0,msgCampId:0,cmpType:0,nurl:"-1",proxyName:"",proxyVersion:"",dataCenterId:"",mwp:0,rurl:"",iblob:"",dvcid:"",ms:0,comId:0,tid:0,dtmid:0,utype:0,rt:0,dtm_server_id:"",bidServerId:"",dtype:"",pid:"",nopd:"",bessyUrl:"",debugBitMask:"1"},f=function(t){var e=this,n=document.body;t.etc&&"object"==typeof t.etc.url?e.url=d(t.etc.url):"object"==typeof t.url&&(e.url=d(t.url)),i(t,function(t,n){e[t]=n}),t.etc&&i(t.etc,function(t,n){e[t]=n}),e.width=parseInt(e.width,10),e.height=parseInt(e.height,10),e.adTarget=e.scriptEl&&e.scriptEl.parentNode?e.scriptEl.parentNode:n,e.targetWidth=parseInt(e.adTarget.clientWidth,10),e.targetHeight=parseInt(e.adTarget.clientHeight,10),e.debugBitMask=parseInt(e.debugBitMask,2),s.signature=e.debugBitMask,142689===t.msgCampId&&n&&n.clientWidth===e.width&&n.clientHeight===e.height&&window!==window.top&&(e.overrideMode=1,e.ubrChosenLoader=r.NATIVE_IN_APP),e.overrideMode||(t.supplyType>1&&t.supplyType<=4&&!window.gsUnified&&1===t.cmpType?(e.overrideMode=1,e.ubrChosenLoader=r.NATIVE_IN_APP):t.sandbox?(e.overrideMode=1,e.ubrChosenLoader=r.REMOTE_SANDBOX):e.ubrChosenLoader=parseInt(e.type,10))};f.fromScript=function(t){var e=a(t,r.CONFIG_ATTR,p);return e.scriptEl=t,e.baseUrl=c(t.src),new f(e)},t.exports=f},function(t,e,n){"use strict";var r=n(7).forIn,o=function(t,e){return r(e,function(e,n){"undefined"==typeof t[e]&&(t[e]=n)}),t};t.exports=o},function(t,e){"use strict";var n=function(t){try{return"string"==typeof t&&decodeURIComponent(t)!==t}catch(e){return!1}},r=function(t){var e,r,o=[];for(e in t)t.hasOwnProperty(e)&&(r=(t[e]||"").toString(),e=n(e)?e:encodeURIComponent(e),r=n(r)?r:encodeURIComponent(r),o.push(e+"="+r));return o.join("&")},o=function(t){var e=t.protocol||"",n=t.host,o=t.pathname||"",i=t.search||"",a=t.hash||"";return e.length>0&&":"!==e.slice(-1)&&(e+=":"),t.hostname&&(n=t.hostname,t.port&&(n+=":"+t.port)),n&&n.length>0&&(n="//"+n),"object"==typeof t.query&&null!==t.query&&(i=r(t.query)),i&&i.length>0&&"?"!==i.charAt(0)&&(i="?"+i),a&&a.length>0&&"#"!==a.charAt(0)&&(a="#"+a),e+n+o+i+a};t.exports=o},function(t,e,n){var r=n(7).forIn,o=n(7).can,i=n(10),a=function(t,e,n){var a,s=t.getAttribute(e);return n=n||{},s&&s.length>0&&(a="undefined"!=typeof window.JSON&&JSON.parse?JSON.parse(s):i("(function() { return "+s+"; })();").value),r(n,function(t,e){o(a,t)||(a[t]=e)}),a};t.exports=a},function(t,e,n){"use strict";var r=window,o=document,i=n(2),a=n(4)(i.REPORT_PKG),s=n(16);t.exports={loadAd:function(t,e){var c,u,d,p=t.ubrChosenLoader,f=t.type,l=-1,E=o.createElement("div"),h="cnvr-"+t.trx,v=[i.EDTL,t.pnid,t.supplyType,t.cmpType,p,t.overrideMode,f,+!!r.gsUnified],m=function(){c&&clearTimeout(c),e()},g=function(t,e){var n="";return t.width>0&&t.height>0&&!isNaN(t.width)&&!isNaN(t.height)?n="width:"+t.width+"px;height:"+t.height+"px;":a(i.ACTIONS.GEOMETRY_ERROR,{ver:i.VER,edtl:v.concat(t.width,t.height,o.documentElement.clientWidth,o.documentElement.clientHeight).join(",")}),n+=i.BORDERLESS_STYLE,t.align===i.ALIGN.CENTER&&(e.setAttribute("align","center"),n+="top: 50%; left: 50%; margin-top: -"+t.height/2+"px; margin-left: -"+t.width/2+"px; position: absolute"),e.setAttribute("style",n),n},R=function(){E.appendChild(t.ad),t.adTarget.appendChild(E),u.load(t,function(){m()})};switch(e=e||function(){},a(i.ACTIONS.AD_LOAD_START,{ver:i.VER,edtl:v.join(",")}),p===i.NATIVE_IN_APP?(l=i.NATIVE_IN_APP,u=n(19)):p===i.REMOTE_SANDBOX?(l=i.REMOTE_SANDBOX,u=n(21)):a(i.ACTIONS.LOADER_ERROR,{ver:i.VER,edtl:v.concat(l).join(",")}),a(i.ACTIONS.AD_LOADER_CHOSEN,{ver:i.VER,edtl:v.concat(l,(o.referrer===o.URL?"1":o.referrer)||"-1").join(",")}),d=t.url,c=setTimeout(function(){a(i.ACTIONS.UBR_WARNING,{ver:i.VER,edtl:v.concat(l,"Request Timeout Expired").join(",")})},i.REQUEST_TIMEOUT),E.id=h,E.setAttribute("style",g(t,E)),t.ad=u.makeDom(t),o.readyState){case"loading":n(23)(R);break;default:R()}s.detect(function(t,e){a(t,{ver:i.VER,edtl:e})}),d.length>i.MAX_URL_SIZE&&a(i.ACTIONS.UBR_WARNING,{ver:i.VER,edtl:v.concat("URLMAX",d.length).join(",")})}}},function(t,e,n){"use strict";var r=n(2),o=n(17).getMetrics,i=n(18).getVersion;t.exports={REPORT_VERSION:2,detect:function(t){var e,n,a;for(e=o(),e.unshift(i()),a=e.length,n=0;a>n;n++)"string"==typeof e[n]&&(e[n]=e[n].replace(/,/i,"_"));t(r.ACTIONS.SAFEFRAME_CAPABILITIES,this.REPORT_VERSION+",6.3.0"+("https:"===location.protocol?",secure":",insecure")+","+e.join(","))}}},function(t,e,n){"use strict";var r=n(7).has,o=-1,i=["cdn","ver","renderFile","hostFile","extFile","bootFile","to","auto","msgFile","debug"],a=["exp-ovr","exp-push","read-cookie","write-cookie"],s=function(t){return t=t||window,r("$sf",t)?t.$sf.ver||o+"":o+""},c=function(t){return t=t||window,r("$sf",t)?t.$sf.specVersion||o+"":o+""},u=function(t){t=t||window;var e=[];return r("$sf",t)&&r("info",t.$sf)?(e.push(t.$sf.info.errs.length||o),e.push(t.$sf.info.list.length||o)):e=[o,o],e},d=function(t){t=t||window;var e,n=[],a=i.length;if(r("$sf",t)&&r("host",t.$sf)&&r("conf",t.$sf.host))for(e=0;a>e;e++)n.push(null!==typeof t.$sf.host.conf[i[e]]?t.$sf.host.conf[i[e]]:o+"");else for(e=0;a>e;e++)n.push(o+"");return n},p=function(t){t=t||window;var e,n,i=[],s=a.length;if(r("$sf",t)&&r("ext",t.$sf)&&r("supports",t.$sf.ext))for(e=t.$sf.ext.supports(),n=0;s>n;n++)i.push(null!==typeof e[a[n]]?e[a[n]]?1:0:o);else for(n=0;s>n;n++)i.push(o);return i},f=function(t){return t=t||window,r("$sf",t)&&r("ext",t.$sf)&&r("inViewPercentage",t.$sf.ext)?t.$sf.ext.inViewPercentage():o},l=function(t){return t=t||window,r("$sf",t)&&r("ext",t.$sf)&&r("winHasFocus",t.$sf.ext)?t.$sf.ext.winHasFocus()?1:0:o},E=function(t){t=t||window;var e=[];return e.push(s(t)),e.push(c(t)),e.push(f(t)),e.push(l(t)),e=e.concat(u(t),d(t),p(t))};t.exports.getVersion=s,t.exports.getSpecVersion=c,t.exports.getInfo=u,t.exports.getConf=d,t.exports.getSupport=p,t.exports.getInView=f,t.exports.getWinFocus=l,t.exports.getMetrics=E},function(t,e,n){"use strict";var r=n(7).has,o=n(7).can,i=n(9);t.exports={ready:function(t,e){e=e||window,r("mraid",e)?i(e.mraid,"ready",function(){t()}):t()},getVersion:function(t){return t=t||window,o(r("mraid",t),"getVersion")?t.mraid.getVersion():"-1"}}},function(t,e,n){"use strict";var r=n(2),o=n(20),i=n(4)(r.REPORT_PKG),a=n(6).provider(r.REPORT_PKG),s=1e3,c=100,u=243;t.exports={makeDom:function(t){var e=document.createElement("div");return e.id="dtm_call",e.className="cnvr-ad cnvr-ad-native-in-app cnvr-ad-"+t.trx,e.setAttribute("style","width:"+t.width+"px;height:"+t.height+"px;"),e},load:function(t,e){var n,d,p,f,l,E,h,v=this.getOgoUrl,m=this.getJacUrl,g=v(t.scriptEl.src),R=m(t.scriptEl.src),T=2,I=100,A=100,O=I+A,_=window,N=function(t){throw new Error("Dep NA: "+t.join(","))},w=function(t,s){t=t.replace(/&?dres=rtb/,"").replace("dres%3Drtb","dres%3Djs").replace("?","?dres=js&").replace(/rurl(.+?)%3F/,"rurl$1%3Fdres%3Djs%26"),i(r.ACTIONS.JSONP_DECISION,{ver:r.VER,edtl:[r.EDTL,+(t!==f),t].join(",")}),o(t,function(){p=a.now()-n,i(r.ACTIONS.AD_CONTENT_RECEIVED,{ver:r.VER,edtl:[r.EDTL,r.REMOTE_SANDBOX,d.toFixed(4),p.toFixed(4),O-(I+A)].join(",")}),e()},N,0,s)},S=function(){d=a.now()-n,n=a.now(),f=t.url,l=t.bessyUrl,E=f,t.supplyType>=2&&t.supplyType<=4&&(_.gsUnified||(1===t.cmpType?(l+="&callback=cnvrCallback"+t.trx,E=l,h=t.ad,_["cnvrCallback"+t.trx]=function(e){t.pnid===u&&(e.nurl=t.nurl),_.conversant.mobile.jac.init(e)}):t.pnid===u&&_.conversant.mobile.jac.init({width:t.width,height:t.height,nURL:t.nurl,impTrackers:[],clickTracker:"",adId:0,proxyName:t.proxyName,proxyVersion:t.proxyVersion,richMedia:""}))),w(E,h)},y=function(){T-=1,i(r.ACTIONS.OGO_LOADED,{ver:r.VER,edtl:[r.EDTL,T,c-I].join(",")}),0===T&&S()},x=function(t){I-=1,I>0?setTimeout(o(g,y,x),s):N(t)},C=function(){T-=1,i(r.ACTIONS.JAC_LOADED,{ver:r.VER,edtl:[r.EDTL,T,c-A].join(",")}),0===T&&S()},L=function(t){A-=1,A>0?setTimeout(o(R,C,L),s):N(t)};n=a.now(),o(R,C,L),o(g,y,x)},getOgoUrl:function(t){var e=t.split("/");return"http"+("https:"===e[0]?"s://s-":"://")+"usweb.dotomi.com/renderer/ogo/"+r.OGO_VER+"/ogo.js"},getJacUrl:function(t){var e=t.split("/");return"http"+("https:"===e[0]?"s://secure.":"://")+"cdn.fastclick.net/clients/jac/jac.js"}}},function(t,e){"use strict";var n=document,r=n.getElementsByTagName("head")[0],o=function(t){return"function"==typeof t},i=function(t,e){t.onload=e;try{t.onreadystatechange=e,t.onerror=e}catch(n){}},a=function(t,e,o,a){var s,c=n.createElement("script");a||(a=r);var u=function(n){var r=c.readyState,o=null;return r&&"loaded"!==r&&"complete"!==r?!1:(n&&n.type&&"error"===n.type&&(o="script.onerror triggered"),s&&clearTimeout(s),i(c,void 0),e(o,t),!0)};c.onload=u,c.onreadystatechange=u,i(c,u),c.src=t,a.appendChild(c),o>0&&(s=setTimeout(function(){i(c,void 0),e(o+"ms timeout triggered",t)},o))},s=function(t,e,n,r,i){var s,c,u=0,d=[];e=o(e)?e:function(){},t&&!t.push&&(t=[t]);var p=function(t,r){t&&d.push(r),0===--u&&(o(n)&&d.length>0?n(d):e())};for(c=0;c<t.length;c++)s=t[c],++u,a(t[c],p,r,i)};t.exports=s},function(t,e,n){"use strict";var r=n(2),o=n(9),i=n(22),a=n(4)(r.REPORT_PKG);t.exports={makeDom:function(t){var e=t.url;e=e.replace("?","?dres=rtb&").replace(/rurl(.+?)%3F/,"rurl$1%3Fdres%3Drtb%26");var n=i(e,t.width,t.height);return a(r.ACTIONS.JSONP_DECISION,{ver:r.VER,edtl:[r.EDTL,0,e].join(",")}),n.id="cnvr-ad-"+t.trx,n.className="cnvr-ad-sandboxed-iframe cnvr-ad-"+t.trx,n},load:function(t,e){a(r.ACTIONS.AD_CONTENT_RECEIVED,{ver:r.VER,edtl:[r.EDTL,r.REMOTE_SANDBOX].join(",")}),o(t.ad,"load",function(){e()})}}},function(t,e,n){"use strict";var r=n(2),o=function(t,e,n){var o=document.createElement("iframe");return t&&(o.src=t),o.width=e,o.height=n,o.scrolling="no",o.frameBorder="0",o.setAttribute("frameborder","0"),o.setAttribute("style",r.BORDERLESS_STYLE),o};t.exports=o},function(t,e){"use strict";var n=function(t,e,n){var r,o=!1,i=e||window,a=i.contentDocument||i.document,s=function(){return"interactive"===a.readyState&&!!n||"complete"===a.readyState},c=function(t){s()&&!o?(o=!0,t()):o||(r=setTimeout(function(){c(t)},10))},u=function(t){o||(o=!0,r&&clearTimeout(r),t())};o||s()?u(t):(a.addEventListener?(a.addEventListener("DOMContentLoaded",function(){s()&&u(t)},!1),i.addEventListener("load",function(){u(t)},!1)):a.attachEvent&&(a.attachEvent("onreadystatechange",function(){s()&&u(t)}),a.attachEvent("onLoad",function(){u(t)})),c(t))};t.exports=n}]);