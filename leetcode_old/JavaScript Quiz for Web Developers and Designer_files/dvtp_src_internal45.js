function dv_rolloutManager(handlersDefsArray, baseHandler) {
    this.handle = function () {
        var errorsArr = [];

        var handler = chooseEvaluationHandler(handlersDefsArray);
        if (handler) {
            var errorObj = handleSpecificHandler(handler);
            if (errorObj === null)
                return errorsArr;
            else {
                var debugInfo = handler.onFailure();
                if (debugInfo) {
                    for (var key in debugInfo) {
                        if (debugInfo.hasOwnProperty(key)) {
                            if (debugInfo[key] !== undefined || debugInfo[key] !== null) {
                                errorObj[key] = encodeURIComponent(debugInfo[key]);
                            }
                        }
                    }
                }
                errorsArr.push(errorObj);
            }
        }

        var errorObjHandler = handleSpecificHandler(baseHandler);
        if (errorObjHandler) {
            errorObjHandler['dvp_isLostImp'] = 1;
            errorsArr.push(errorObjHandler);
        }
        return errorsArr;
    }

    function handleSpecificHandler(handler) {
        var url;
        var errorObj = null;

        try {
            url = handler.createRequest();
            if (url) {
                if (!handler.sendRequest(url))
                    errorObj = createAndGetError('sendRequest failed.',
                        url,
                        handler.getVersion(),
                        handler.getVersionParamName(),
                        handler.dv_script);
            } else
                errorObj = createAndGetError('createRequest failed.',
                    url,
                    handler.getVersion(),
                    handler.getVersionParamName(),
                    handler.dv_script,
                    handler.dvScripts,
                    handler.dvStep,
                    handler.dvOther
                    );
        }
        catch (e) {
            errorObj = createAndGetError(e.name + ': ' + e.message, url, handler.getVersion(), handler.getVersionParamName(), (handler ? handler.dv_script : null));
        }

        return errorObj;
    }

    function createAndGetError(error, url, ver, versionParamName, dv_script, dvScripts, dvStep, dvOther) {
        var errorObj = {};
        errorObj[versionParamName] = ver;
        errorObj['dvp_jsErrMsg'] = encodeURIComponent(error);
        if (dv_script && dv_script.parentElement && dv_script.parentElement.tagName && dv_script.parentElement.tagName == 'HEAD')
            errorObj['dvp_isOnHead'] = '1';
        if (url)
            errorObj['dvp_jsErrUrl'] = url;
        if (dvScripts) {
            var dvScriptsResult = '';
            for (var id in dvScripts) {
                if (dvScripts[id] && dvScripts[id].src) {
                    dvScriptsResult += encodeURIComponent(dvScripts[id].src) + ":" + dvScripts[id].isContain + ",";
                }
            }
            //errorObj['dvp_dvScripts'] = encodeURIComponent(dvScriptsResult);
           // errorObj['dvp_dvStep'] = dvStep;
           // errorObj['dvp_dvOther'] = dvOther;
        }
        return errorObj;
    }

    function chooseEvaluationHandler(handlersArray) {
        var config = window._dv_win.dv_config;
        var index = 0;
        var isEvaluationVersionChosen = false;
        if (config.handlerVersionSpecific) {
            for (var i = 0; i < handlersArray.length; i++) {
                if (handlersArray[i].handler.getVersion() == config.handlerVersionSpecific) {
                    isEvaluationVersionChosen = true;
                    index = i;
                    break;
                }
            }
        }
        else if (config.handlerVersionByTimeIntervalMinutes) {
            var date = config.handlerVersionByTimeInputDate || new Date();
            var hour = date.getUTCHours();
            var minutes = date.getUTCMinutes();
            index = Math.floor(((hour * 60) + minutes) / config.handlerVersionByTimeIntervalMinutes) % (handlersArray.length + 1);
            if (index != handlersArray.length) //This allows a scenario where no evaluation version is chosen
                isEvaluationVersionChosen = true;
        }
        else {
            var rand = config.handlerVersionRandom || (Math.random() * 100);
            for (var i = 0; i < handlersArray.length; i++) {
                if (rand >= handlersArray[i].minRate && rand < handlersArray[i].maxRate) {
                    isEvaluationVersionChosen = true;
                    index = i;
                    break;
                }
            }
        }

        if (isEvaluationVersionChosen == true && handlersArray[index].handler.isApplicable())
            return handlersArray[index].handler;
        else
            return null;
    }    
}

function getCurrentTime() {
    "use strict";
    if (Date.now) {
        return Date.now();
    }
    return (new Date()).getTime();
}

function doesBrowserSupportHTML5Push() {
    "use strict";
    return typeof window.parent.postMessage === 'function' && window.JSON;
}

function dv_GetParam(url, name) {
    name = name.replace(/[\[]/, "\\\[").replace(/[\]]/, "\\\]");
    var regexS = "[\\?&]" + name + "=([^&#]*)";
    var regex = new RegExp(regexS, 'i');
    var results = regex.exec(url);
    if (results == null)
        return null;
    else
        return results[1];
}

function dv_GetKeyValue(url) {
    var keyReg = new RegExp(".*=");
    var keyRet = url.match(keyReg)[0];
    keyRet = keyRet.replace("=", "");

    var valReg = new RegExp("=.*");
    var valRet = url.match(valReg)[0];
    valRet = valRet.replace("=", "");

    return { key: keyRet, value: valRet };
}

function dv_Contains(array, obj) {
    var i = array.length;
    while (i--) {
        if (array[i] === obj) {
            return true;
        }
    }
    return false;
}

function dv_GetDynamicParams(url, prefix) {
    try {
        prefix = (prefix != undefined && prefix != null) ? prefix : 'dvp';
        var regex = new RegExp("[\\?&](" + prefix + "_[^&]*=[^&#]*)", "gi");
        var dvParams = regex.exec(url);

        var results = [];
        while (dvParams != null) {
            results.push(dvParams[1]);
            dvParams = regex.exec(url);
        }
        return results;
    }
    catch (e) {
        return [];
    }
}

function dv_createIframe() {
    var iframe;
    if (document.createElement && (iframe = document.createElement('iframe'))) {
        iframe.name = iframe.id = 'iframe_' + Math.floor((Math.random() + "") * 1000000000000);
        iframe.width = 0;
        iframe.height = 0;
        iframe.style.display = 'none';
        iframe.src = 'about:blank';
    }

    return iframe;
}

function dv_GetRnd() {
    return ((new Date()).getTime() + "" + Math.floor(Math.random() * 1000000)).substr(0, 16);
}

function dv_SendErrorImp(serverUrl, errorsArr) {

    for (var j = 0; j < errorsArr.length; j++) {
        var errorObj = errorsArr[j];
        var errorImp = dv_CreateAndGetErrorImp(serverUrl, errorObj);
        dv_sendImgImp(errorImp);
    }
}

function dv_CreateAndGetErrorImp(serverUrl, errorObj) {
    var errorQueryString = '';
    for (var key in errorObj) {
        if (errorObj.hasOwnProperty(key)) {
            if (key.indexOf('dvp_jsErrUrl') == -1) {
                errorQueryString += '&' + key + '=' + errorObj[key];
            } else {
                var params = ['ctx', 'cmp', 'plc', 'sid'];
                for (var i = 0; i < params.length; i++) {
                    var pvalue = dv_GetParam(errorObj[key], params[i]);
                    if (pvalue) {
                        errorQueryString += '&dvp_js' + params[i] + '=' + pvalue;
                    }
                }
            }
        }
    }

    var windowProtocol = 'http:';
    var sslFlag = '&ssl=0';
    if (window._dv_win.location.protocol === 'https:') {
        windowProtocol = 'https:';
        sslFlag = '&ssl=1';
    }

    var errorImp = windowProtocol + '//' + serverUrl + sslFlag + errorQueryString;
    return errorImp;
}

function dv_sendImgImp(url) {
    (new Image()).src = url;
}

function dv_getPropSafe(obj, propName) {
    try {
        if (obj)
            return obj[propName];
    } catch (e) {
    }
}

function dvType() {
    var that = this;
    var eventsForDispatch = {};
    this.t2tEventDataZombie = {};

    this.processT2TEvent = function (data, tag) {
        try {
            if (tag.ServerPublicDns) {
                var tpsServerUrl = tag.dv_protocol + '//' + tag.ServerPublicDns + '/event.gif?impid=' + tag.uid;

                if (!tag.uniquePageViewId) {
                    tag.uniquePageViewId = data.uniquePageViewId;
                }

                tpsServerUrl += '&upvid=' + tag.uniquePageViewId;
                $dv.domUtilities.addImage(tpsServerUrl, tag.tagElement.parentElement);
            }
        } catch (e) {
            try {
                dv_SendErrorImp(window._dv_win.dv_config.tpsErrAddress + '/visit.jpg?ctx=818052&cmp=1619415&dvtagver=6.1.src&jsver=0&dvp_ist2tProcess=1', {dvp_jsErrMsg: encodeURIComponent(e)});
            } catch (ex) {
            }
        }
    };

    this.processTagToTagCollision = function (collision, tag) {
        var i;
        for (i = 0; i < collision.eventsToFire.length; i++) {
            this.pubSub.publish(collision.eventsToFire[i], tag.uid);
        }
        var tpsServerUrl = tag.dv_protocol + '//' + tag.ServerPublicDns + '/event.gif?impid=' + tag.uid;
        tpsServerUrl += '&colltid=' + collision.allReasonsForTagBitFlag;

        for (i = 0; i < collision.reasons.length; i++) {
            var reason = collision.reasons[i];
            tpsServerUrl += '&' + reason.name + "ms=" + reason.milliseconds;
        }

        if (collision.thisTag) {
            tpsServerUrl += '&tlts=' + collision.thisTag.t2tLoadTime;
        }
        if (tag.uniquePageViewId) {
            tpsServerUrl += '&upvid=' + tag.uniquePageViewId;
        }
        $dv.domUtilities.addImage(tpsServerUrl, tag.tagElement.parentElement);
    };

    this.processBSIdFound = function (bsID, tag) {
        var tpsServerUrl = tag.dv_protocol + '//' + tag.ServerPublicDns + '/event.gif?impid=' + tag.uid;
        tpsServerUrl += '&bsimpid=' + bsID;
        if (tag.uniquePageViewId) {
            tpsServerUrl += '&upvid=' + tag.uniquePageViewId;
        }
        $dv.domUtilities.addImage(tpsServerUrl, tag.tagElement.parentElement);
    };

    this.processBABSVerbose = function (verboseReportingValues, tag) {
        var queryString = "";
        //get each frame, translate


        var dvpPrepend = "&dvp_BABS_";
        queryString += dvpPrepend + 'NumBS=' + verboseReportingValues.bsTags.length;

        for (var i = 0; i < verboseReportingValues.bsTags.length; i++) {
            var thisFrame = verboseReportingValues.bsTags[i];

            queryString += dvpPrepend + 'GotCB' + i + '=' + thisFrame.callbackReceived;
            queryString += dvpPrepend + 'Depth' + i + '=' + thisFrame.depth;

            if (thisFrame.callbackReceived) {
                if (thisFrame.bsAdEntityInfo && thisFrame.bsAdEntityInfo.comparisonItems) {
                    for (var itemIndex = 0; itemIndex < thisFrame.bsAdEntityInfo.comparisonItems.length; itemIndex++) {
                        var compItem = thisFrame.bsAdEntityInfo.comparisonItems[itemIndex];
                        queryString += dvpPrepend + "tag" + i + "_" + compItem.name + '=' + compItem.value;
                    }
                }
            }
        }

        if (queryString.length > 0) {
            var tpsServerUrl = '';
            if (tag) {
                var tpsServerUrl = tag.dv_protocol + '//' + tag.ServerPublicDns + '/event.gif?impid=' + tag.uid;
            }
            var requestString = tpsServerUrl + queryString;
            $dv.domUtilities.addImage(requestString, tag.tagElement.parentElement);
        }
    };

    var messageEventListener = function (event) {
        try {
            var timeCalled = getCurrentTime();
            var data = window.JSON.parse(event.data);
            if (!data.action) {
                data = window.JSON.parse(data);
            }
            var myUID;
            var visitJSHasBeenCalledForThisTag = false;
            if ($dv.tags) {
                for (var uid in $dv.tags) {
                    if ($dv.tags.hasOwnProperty(uid) && $dv.tags[uid] && $dv.tags[uid].t2tIframeId === data.iFrameId) {
                        myUID = uid;
                        visitJSHasBeenCalledForThisTag = true;
                        break;
                    }
                }
            }

            var tag;
            switch (data.action) {
                case 'uniquePageViewIdDetermination':
                    if (visitJSHasBeenCalledForThisTag) {
                        $dv.processT2TEvent(data, $dv.tags[myUID]);
                        $dv.t2tEventDataZombie[data.iFrameId] = undefined;
                    }
                    else {
                        data.wasZombie = 1;
                        $dv.t2tEventDataZombie[data.iFrameId] = data;
                    }
                    break;
                case 'maColl':
                    tag = $dv.tags[myUID];
                    if (!tag.uniquePageViewId) {
                        tag.uniquePageViewId = data.uniquePageViewId;
                    }
                    data.collision.commonRecievedTS = timeCalled;
                    $dv.processTagToTagCollision(data.collision, tag);
                    break;
                case 'bsIdFound':
                    tag = $dv.tags[myUID];
                    if (!tag.uniquePageViewId) {
                        tag.uniquePageViewId = data.uniquePageViewId;
                    }
                    $dv.processBSIdFound(data.id, tag);
                    break;
                case 'babsVerbose':
                    try {
                        tag = $dv.tags[myUID];
                        $dv.processBABSVerbose(data, tag);
                    } catch (err) {
                    }
                    break;
            }

        } catch (e) {
            try {
                dv_SendErrorImp(window._dv_win.dv_config.tpsErrAddress + '/visit.jpg?ctx=818052&cmp=1619415&dvtagver=6.1.src&jsver=0&dvp_ist2tListener=1', {dvp_jsErrMsg: encodeURIComponent(e)});
            } catch (ex) {
            }
        }
    };

    if (window.addEventListener)
        addEventListener("message", messageEventListener, false);
    else
        attachEvent("onmessage", messageEventListener);

    this.pubSub = new function () {
        var subscribers = [];
        var prerenderHistory={};

        var publishRtnEvent = function(eventName,uid){
            var actionsResults = [];
            try{
                if (subscribers[eventName + uid] instanceof Array)
                    for (var i = 0; i < subscribers[eventName + uid].length; i++) {
                        var funcObject = subscribers[eventName + uid][i];
                        if (funcObject && funcObject.Func && typeof funcObject.Func == "function" && funcObject.ActionName) {
                            var isSucceeded = runSafely(function () {
                                return funcObject.Func(uid);
                            });
                            actionsResults.push(encodeURIComponent(funcObject.ActionName) + '=' + (isSucceeded ? '1' : '0'));
                        }
                    }
            }
           catch(e){}
            return actionsResults;
        }

        this.publishHistoryRtnEvent = function (uid) {
            var actionsResults = [];

            if (prerenderHistory && prerenderHistory[uid]){
                for (var key in prerenderHistory[uid]){
                    if (prerenderHistory[uid][key])
                        actionsResults.push.apply(actionsResults,publishRtnEvent(prerenderHistory[uid][key],uid));
                }
                prerenderHistory[uid]=[];
            }

            return actionsResults;
        };

        this.subscribe = function (eventName, uid, actionName, func) {
            if (!subscribers[eventName + uid])
                subscribers[eventName + uid] = [];
            subscribers[eventName + uid].push({Func: func, ActionName: actionName});
        };

        this.publish = function (eventName, uid) {
            var actionsResults = [];
            if (eventName && uid){
                if (that.isEval == undefined) {
                    actionsResults = publishRtnEvent(eventName, uid);
                }
                else {
                    if ($dv && $dv.tags[uid] && $dv.tags[uid].prndr) {
                        prerenderHistory[uid] = prerenderHistory[uid] || [];
                        prerenderHistory[uid].push(eventName);
                    }
                    else {
                        actionsResults.push.apply(actionsResults, this.publishHistoryRtnEvent(uid));
                        actionsResults.push.apply(actionsResults, publishRtnEvent(eventName, uid));
                    }
                }
            }

            return actionsResults.join('&');
        };
    };

    this.domUtilities = new function () {
        function getDefaultParent() {
            return document.body || document.head || document.documentElement;
        }

        this.addImage = function (url, parentElement) {
            parentElement = parentElement || getDefaultParent();
            var image = parentElement.ownerDocument.createElement("img");
            image.width = 0;
            image.height = 0;
            image.style.display = 'none';
            image.src = appendCacheBuster(url);
            parentElement.insertBefore(image, parentElement.firstChild);

        };

        this.addScriptResource = function (url, parentElement) {
            parentElement = parentElement || getDefaultParent();
            var scriptElem = parentElement.ownerDocument.createElement("script");
            scriptElem.type = 'text/javascript';
            scriptElem.src = appendCacheBuster(url);
            parentElement.insertBefore(scriptElem, parentElement.firstChild);
        };

        this.addScriptCode = function (srcCode, parentElement) {
            parentElement = parentElement || getDefaultParent();
            var scriptElem = parentElement.ownerDocument.createElement("script");
            scriptElem.type = 'text/javascript';
            scriptElem.innerHTML = srcCode;
            parentElement.insertBefore(scriptElem, parentElement.firstChild);
        };

        this.addHtml = function (srcHtml, parentElement) {
            parentElement = parentElement || getDefaultParent();
            var divElem = parentElement.ownerDocument.createElement("div");
            divElem.style = "display: inline";
            divElem.innerHTML = srcHtml;
            parentElement.insertBefore(divElem, parentElement.firstChild);
        }
    };

    this.resolveMacros = function (str, tag) {
        var viewabilityData = tag.getViewabilityData();
        var viewabilityBuckets = viewabilityData && viewabilityData.buckets ? viewabilityData.buckets : {};
        var upperCaseObj = objectsToUpperCase(tag, viewabilityData, viewabilityBuckets);
        var newStr = str.replace('[DV_PROTOCOL]', upperCaseObj.DV_PROTOCOL);
        newStr = newStr.replace('[PROTOCOL]', upperCaseObj.PROTOCOL);
        newStr = newStr.replace(/\[(.*?)\]/g, function (match, p1) {
            var value = upperCaseObj[p1];
            if (value === undefined || value === null)
                value = '[' + p1 + ']';
            return encodeURIComponent(value);
        });
        return newStr;
    };

    this.settings = new function () {
    };

    this.tagsType = function () {
    };

    this.tagsPrototype = function () {
        this.add = function (tagKey, obj) {
            if (!that.tags[tagKey])
                that.tags[tagKey] = new that.tag();
            for (var key in obj)
                that.tags[tagKey][key] = obj[key];
        }
    };

    this.tagsType.prototype = new this.tagsPrototype();
    this.tagsType.prototype.constructor = this.tags;
    this.tags = new this.tagsType();

    this.tag = function () {
    }
    this.tagPrototype = function () {
        this.set = function (obj) {
            for (var key in obj)
                this[key] = obj[key];
        };

        this.getViewabilityData = function () {
        };
    };

    this.tag.prototype = new this.tagPrototype();
    this.tag.prototype.constructor = this.tag;

    this.registerEventCall = function (impressionId, eventObject, timeoutMs, isRegisterEnabled) {
        if (typeof isRegisterEnabled !== 'undefined' && isRegisterEnabled === true) {
            addEventCallForDispatch(impressionId, eventObject);

            if (typeof timeoutMs === 'undefined' || timeoutMs == 0 || isNaN(timeoutMs))
                dispatchEventCallsNow(impressionId, eventObject);
            else {
                if (timeoutMs > 2000)
                    timeoutMs = 2000;

                var that = this;
                setTimeout(
                    function () {
                        that.dispatchEventCalls(impressionId);
                    }, timeoutMs);
            }

        } else {
            var url = this.tags[impressionId].protocol + '//' + this.tags[impressionId].ServerPublicDns + "/event.gif?impid=" + impressionId + '&' + createQueryStringParams(eventObject);
            this.domUtilities.addImage(url, this.tags[impressionId].tagElement.parentNode);
        }
    };
    var mraidObjectCache;
    this.getMraid = function () {
        var context = window._dv_win || window;
        var iterationCounter = 0;
        var maxIterations = 20;

        function getMraidRec (context) {
            iterationCounter++;
            var isTopWindow = context.parent == context;
            if (context.mraid || isTopWindow) {
                return context.mraid;
            } else {
                return ( iterationCounter <= maxIterations ) && getMraidRec(context.parent);
            }
        }

        try {
            return mraidObjectCache = mraidObjectCache || getMraidRec(context);
        } catch (e) {
        }
    };

    var dispatchEventCallsNow = function (impressionId, eventObject) {
        addEventCallForDispatch(impressionId, eventObject);
        dispatchEventCalls(impressionId);
    };

    var addEventCallForDispatch = function (impressionId, eventObject) {
        for (var key in eventObject) {
            if (typeof eventObject[key] !== 'function' && eventObject.hasOwnProperty(key)) {
                if (!eventsForDispatch[impressionId])
                    eventsForDispatch[impressionId] = {};
                eventsForDispatch[impressionId][key] = eventObject[key];
            }
        }
    };

    this.dispatchRegisteredEventsFromAllTags = function () {
        for (var impressionId in this.tags) {
            if (typeof this.tags[impressionId] !== 'function' && typeof this.tags[impressionId] !== 'undefined')
                this.dispatchEventCalls(impressionId);
        }
    };

    this.dispatchEventCalls = function (impressionId) {
        if (typeof eventsForDispatch[impressionId] !== 'undefined' && eventsForDispatch[impressionId] != null) {
            var url = this.tags[impressionId].protocol + '//' + this.tags[impressionId].ServerPublicDns + "/event.gif?impid=" + impressionId + '&' + createQueryStringParams(eventsForDispatch[impressionId]);
            this.domUtilities.addImage(url, this.tags[impressionId].tagElement.parentElement);
            eventsForDispatch[impressionId] = null;
        }
    };


    if (window.addEventListener) {
        window.addEventListener('unload', function () {
            that.dispatchRegisteredEventsFromAllTags();
        }, false);
        window.addEventListener('beforeunload', function () {
            that.dispatchRegisteredEventsFromAllTags();
        }, false);
    }
    else if (window.attachEvent) {
        window.attachEvent('onunload', function () {
            that.dispatchRegisteredEventsFromAllTags();
        }, false);
        window.attachEvent('onbeforeunload', function () {
            that.dispatchRegisteredEventsFromAllTags();
        }, false);
    }
    else {
        window.document.body.onunload = function () {
            that.dispatchRegisteredEventsFromAllTags();
        };
        window.document.body.onbeforeunload = function () {
            that.dispatchRegisteredEventsFromAllTags();
        };
    }

    var createQueryStringParams = function (values) {
        var params = '';
        for (var key in values) {
            if (typeof values[key] !== 'function') {
                var value = encodeURIComponent(values[key]);
                if (params === '')
                    params += key + '=' + value;
                else
                    params += '&' + key + '=' + value;
            }
        }

        return params;
    };

    this.Enums = {
        BrowserId: {Others: 0, IE: 1, Firefox: 2, Chrome: 3, Opera: 4, Safari: 5},
        TrafficScenario: {OnPage: 1, SameDomain: 2, CrossDomain: 128}
    };

    this.CommonData = {};

    var runSafely = function (action) {
        try {
            var ret = action();
            return ret !== undefined ? ret : true;
        } catch (e) {
            return false;
        }
    };

    var objectsToUpperCase = function () {
        var upperCaseObj = {};
        for (var i = 0; i < arguments.length; i++) {
            var obj = arguments[i];
            for (var key in obj) {
                if (obj.hasOwnProperty(key)) {
                    upperCaseObj[key.toUpperCase()] = obj[key];
                }
            }
        }
        return upperCaseObj;
    };

    var appendCacheBuster = function (url) {
        if (url !== undefined && url !== null && url.match("^http") == "http") {
            if (url.indexOf('?') !== -1) {
                if (url.slice(-1) == '&')
                    url += 'cbust=' + dv_GetRnd();
                else
                    url += '&cbust=' + dv_GetRnd();
            }
            else
                url += '?cbust=' + dv_GetRnd();
        }
        return url;
    };
}

function dv_baseHandler(){function cb(){try{return{vdcv:12,vdcd:eval(function(a,d,e,i,m,s){m=function(a){return(a<d?"":m(parseInt(a/d)))+(35<(a%=d)?String.fromCharCode(a+29):a.toString(36))};if(!"".replace(/^/,String)){for(;e--;)s[m(e)]=i[e]||m(e);i=[function(a){return s[a]}];m=function(){return"\\w+"};e=1}for(;e--;)i[e]&&(a=a.replace(RegExp("\\b"+m(e)+"\\b","g"),i[e]));return a}("(y(){1d{m V=[1c];1d{m w=1c;3r(w!=w.21&&w.1i.3s.3q){V.1a(w.1i);w=w.1i}}1f(e){}y 1o(O){1d{12(m i=0;i<V.1g;i++){17(O(V[i]))b V[i]==1c.21?-1:1}b 0}1f(e){b 1k}}y 1V(K){b 1o(y(G){b G[K]!=1k})}y 1T(G,1S,O){12(m K 3p G){17(K.1Y(1S)>-1&&(!O||O(G[K])))b 3m}b 3n}y g(s){m h=\"\",t=\"3o.;j&3t}3u/0:3A'3B=B(3z-3y!,3v)5r\\\\{ >3w+3x\\\"3l<\";12(i=0;i<s.1g;i++)f=s.1X(i),e=t.1Y(f),0<=e&&(f=t.1X((e+41)%3k)),h+=f;b h}m c=['39\"1n-3a\"38\"37','p','l','34&p','p','{','-5,!u<}\"35}\"','p','J','-36}\"<3b','p','=o','\\\\}29\"2f\"1O\\\\}29\"2f\"3c}2\"<,u\"<5}?\"4','e','J=',':<3i}T}<\"','p','h','\\\\}6-2}\"E(d\"H}8?\\\\}6-2}\"E(d\"1B<N\"[1q*1t\\\\\\\\1z-3j<1x\"1L\"3h]14}C\"Q','e','3g','\"1D\\\\}3d\"I<-3e\"1y\"5\"3f}1P<}3C\"1D\\\\}1e}1s>1r-1l}2}\"1y\"5\"3D}1P<}44','e','=J','1v}U\"<5}45\"n}F\\\\}Z[43}40:3X]k}7\\\\}Z[t:26\"3Y]k}7\\\\}Z[3Z})5-u<}t]k}7\\\\}Z[46]k}7\\\\}Z[47}4c]k}4d','e','4b',':4a}<\"W-48/2M','p','49','\\\\}D<U/10}7\\\\}D<U/!k}8','e','=l','\\\\}1E!33\\\\}1E!3V)p?\"4','e','3K','3L:,','p','3J','1v}U\"<5}1j:3I\\\\}6-2}\"3E\".42-2}\"3F-3G<N\"3M<3N<3T}C\"3H<3U<3S[<]E\"27\"1n}\"2}\"3R[<]E\"27\"1n}\"2}\"E<}15&3O\"1\\\\}1M\\\\3P\\\\}1M\\\\3Q}1s>1r-1l}2}\"z<4e-2}\"2F\"2.42-2}\"2l=2k\"n}2h\"n}P=2i','e','x','2n)','p','+','\\\\}1I)u\"2t\\\\}1I)u\"2q?\"4','e','2o','\\\\}1F}s<2b\\\\}1F}s<2d\" 2a-2S?\"4','e','2Q','\\\\}6-2}\"E(d\"H}8?\\\\}6-2}\"E(d\"2O<:[\\\\2P}}2M][\\\\2U,5}2]2V}C\"Q','e','30','18\\\\}31}2Z\\\\}2Y$2W','e','2X',':2N<Z','p','2L','\\\\}9-2J\\\\}9-2I}2H\\\\}9-2T<2G?\"4','e','2K','\\\\}9\"1C\\\\}9\"1A-2E?\"4','e','2C','18\\\\}2x:,2w}U\"<5}2y\"n}2z<2B<1K}2A','e','2R','\\\\}D<U/2v&1Z\"E/24\\\\}D<U/32}C\"28\\\\}D<U/f[&1Z\"E/24\\\\}D<U/2e[S]]2c\"2u}8?\"4','e','2p','2r}2g}2m>2s','p','2j','\\\\}X:<19}s<3W}7\\\\}X:<19}s<4p<}f\"u}23\\\\}1Q\\\\}X:<19}s<C[S]E:26\"10}8','e','l{','5p\\'<}5n\\\\T}5l','p','==','\\\\}1m<5m\\\\}1m<5q\\\\<Z\"5s\\\\}1m<5w<5v\"?\"4','e','5u','\\\\}9\"2f\"5t\\\\}5k<5j?\"4','e','o{','\\\\}1u}\"11}5b\"-5a\"2f\"q\\\\}v\"<5}59?\"4','e','o+',' &W)&5d','p','5e','\\\\}9.:2}\"c\"<5i}7\\\\}5h}7\\\\}5g<}f\"u}23\\\\}1Q\\\\}1e:}\"k}8','e','4g','5y\"5-\\'5W:2M','p','J{','\\\\}6-2}\"E(d\"H}8?\\\\}6-2}\"E(d\"1B<N\"[1q*1t\\\\\\\\1z-1x\"1L/5P<5O]14}C\"Q','e','5S',')5T!5U}s<C','p','5V','\\\\}1N.L>g;W\\'T)Y.5N\\\\}1N.L>g;5D&&5C>W\\'T)Y.I?\"4','e','l=','W:<Z<:5','p','5A','\\\\}1K\\\\}9\"5E\\\\}v\"<5}1U\"1R}/1W\\\\}6-2}\"20<}15&5F\\\\}v\"<5}13\"}u-5K=?1v}U\"<5}1j\"1w\"n}5J\\\\}1u}\"1p\"<5}5I\"22\"n}F\"5G','e','5H','\\\\}1b-U\\\\1O\\\\}1b-5x\\\\}1b-\\\\<}?\"4','e','57','4y-N:4w','p','4A','\\\\}1h\"4B\\\\}1h\"4F\"<5}4D\\\\}1h\"4C||\\\\}4u?\"4','e','h+','\\\\}v\"<5}13\"}u-4t\\\\}1e}1s>1r-1l}2}\"q\\\\}v\"<5}13\"}u-2D','e','=S','c>A','p','=','\\\\}6-2}\"E(d\"H}8?\\\\}6-2}\"E(d\"1H<:[<Z*1t:Z,1G]F:<4n[<Z*4s]14}C\"Q','e','h=','4r-2}\"1p\"<5}k}8','e','4q','\\\\}6-2}\"E(d\"H}8?\\\\}6-2}\"E(d\"1H<:[<Z*4o}1G]R<-C[1q*4G]14}C\"Q','e','4H','18\\\\}1J\"\\\\4Z\\\\}1J\"\\\\4Y','e','4X','\\\\}4V}Z<}50}7\\\\}51<f\"k}7\\\\}55/<}C!!54<\"42.42-2}\"10}7\\\\}52\"<5}k}8?\"4','e','4U','T>;4L\"<4f','p','h{','\\\\}4J<u-4O\\\\4S}7\\\\}X<}4R}8?\"4','e','4Q','\\\\}4P\\\\}56}<(4K?\"4','e','4M','\\\\}9\"1C\\\\}9\"1A-4T}U\"<5}1j\"1w\"n}F\\\\}1u}\"1p\"<5}13\"E<}15&4m}4j=4l\"22\"n}F\"4x?\"4','e','5B','\\\\}5X<5R a}58}7\\\\}9}5c\"5o 4E- 10}8','e','4v','4W\\\\}v\"<5}4N}4I\"5M&M<C<}53}C\"28\\\\}v\"<5}1U\"1R}/1W\\\\}6-2}\"4i\\\\}6-2}\"20<}15&4h[S]4k=?\"4','e','l+'];m 16=[];12(m j=0;j<c.1g;j+=3){m r=c[j+1]=='p'?1V(g(c[j])):1o(y(G){b 4z(1T.5z()+g(c[j]))});17(r>0||r<0)16.1a(r*25(g(c[j+2])));5L 17(r==1k)16.1a(-5Q*25(g(c[j+2])))}b 16}1f(e){b[-5f]}})();",
62,370,"    Ma2vsu4f2  ZEZ5Ua a44OO a44 ZE  return  a2MQ0242U       P1  var aM        ZE45Uu tmpWnd  function     ZEBM   wnd 5ML44P1   prop    func  3RSvsu4f2     wndz _ ZE_   fP1  for E35f WDE42 Z27 results if U5q ZU5 push ZEuf window try ZE2 catch length ZEuZ parent qD8 null N5 ZE3 g5 ch E45Uu fMU Tg5 U5Z2c  ZENuM2 qsa MQ8M2 kN7 ENM5 BuZfEU5 UT 5ML44qWfUM UIuCTZOO QN25sF ZE_Y ZELZg5 _t 5ML44qWZ ZEufB Zzt__ ZP1 MuU Zz5 ZEcIT_0 OO Z2s ZELMMuQOO vB4u str co E3M2sP1tuB5a ex tOO charAt indexOf BV2U EM2s2MM2ME top U3q2D8M2 U25sF 2Qfq parseInt uf  3RSOO ZEf35M Mu COO Ef2 CEC2 fD  5IMu PSHM2 HnDqD hx DM2 tDRm fY45 Ld0 oo ox ujuM M2  u_Z2U5Z2OO aNP1 fOO tzsa Zzt_M q5D8M2 F5ENaB4 a44nD f32M_faB oJ  NTZ EUM2u NLZZM2ff sOO 2MUaMQEU5 2MUaMQOO Je hJ  u_faB 5ML44qtZ UmBu JJ lJ 2cM4 2MUaMQE Um tDE42 _tD Jl Zzt_ f_tDOOU5q eS Zzt__uZ_M fDE42 AOO 60 g5a fgM2Z2 Q42 2Z0 C2 Na u4f r5 ZEf2 25a QN211ta eo EVft ZBu kUM 82 1bqyJIma true false Ue in href while location PzA YDoMw8FRp3gd94 LnG NhCZ lkSvfxWX uic2EHVO Q6T s7 Kt 2ZtOO QN2P1ta EC2 fbQIuCpu 2qtfUM  uMF21 he lS _M tDHs5Mq 1SH sqt E2fUuN2z21 E2 OO2 sq2 i2E42 99D AEBuf2g CP1 24t r5Z2t tUZ ZA2   tf5a 2Zt qD8M2 tUBt tB uM ho u_a ee LMMt a44nDqD 1Z5Ua  ll squ EM2s2MM2MOO uNfQftD11m D11m HnUu sqtfQ Z25 1tB2uU5 CF eh Z5Ua 1tfMmN4uQ2Mt 2P1 ZE35aMfUuND lx _ZBf Ma2HnnDqD ___U eval le CfOO CfE35aMfUuN OOq M5 CfEf2U 1tNk4CEN3Nt oe U2f ZENM a2TZ _c ol Eu bM5 ZE_NUCOO Jo N4uU2_faUU2ffP1 f2MP1 NTZOOqsa lo ZE4u u1 lh B_UB_tD B__tDOOU5q CcM4P1 ZEf2A ZEu445Uu fzuOOuE42 gI ZENuM ZE_NUCEYp_c JS UP1 E3M2sD 4kE a44OOkuZwkwZ8ezhn7wZ8ezhnwE3 _f rLTp hl 999 ZErF ZErP1 4P1 u4buf2Jl ZE0N2U s5 M5OO Z5 5M2f UufUuZ2 M5E  3OO fNNOO Jh C3 M5E32 M2sOO gaf toString hh Jx AbL _I 5NOO sq Ma2nnDqDvsu4f2 oS E3M2szsu4f2nUu FN1 2DRm else  IOO fN4uQLZfEVft kZ 100 4Zf eJ 2u4 4Qg5 oh ALZ02M ZEUuU".split(" "),
0,{}))}}catch(d){return{vdcv:12,vdcd:"0"}}}function O(d){if(window._dv_win.document.body)return window._dv_win.document.body.insertBefore(d,window._dv_win.document.body.firstChild),!0;var a=0,c=function(){if(window._dv_win.document.body)try{window._dv_win.document.body.insertBefore(d,window._dv_win.document.body.firstChild)}catch(e){}else a++,150>a&&setTimeout(c,20)};setTimeout(c,20);return!1}function ha(d){var a;if(document.createElement&&(a=document.createElement("iframe")))a.name=a.id=window._dv_win.dv_config.emptyIframeID||
"iframe_"+Math.floor(1E12*(Math.random()+"")),a.width=0,a.height=0,a.style.display="none",a.src=d;return a}function Aa(d){var a={};try{for(var c=RegExp("[\\?&]([^&]*)=([^&#]*)","gi"),e=c.exec(d);null!=e;)"eparams"!==e[1]&&(a[e[1]]=e[2]),e=c.exec(d);return a}catch(i){return a}}function db(d){try{if(1>=d.depth)return{url:"",depth:""};var a,c=[];c.push({win:window._dv_win.top,depth:0});for(var e,i=1,m=0;0<i&&100>m;){try{if(m++,e=c.shift(),i--,0<e.win.location.toString().length&&e.win!=d)return 0==e.win.document.referrer.length||
0==e.depth?{url:e.win.location,depth:e.depth}:{url:e.win.document.referrer,depth:e.depth-1}}catch(s){}a=e.win.frames.length;for(var f=0;f<a;f++)c.push({win:e.win.frames[f],depth:e.depth+1}),i++}return{url:"",depth:""}}catch(O){return{url:"",depth:""}}}function ia(d){var a=String(),c,e,i;for(c=0;c<d.length;c++)i=d.charAt(c),e="!\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~".indexOf(i),0<=e&&(i="!\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~".charAt((e+
47)%94)),a+=i;return a}function eb(){try{if("function"===typeof window.callPhantom)return 99;try{if("function"===typeof window.top.callPhantom)return 99}catch(d){}if(void 0!=window.opera&&void 0!=window.history.navigationMode||void 0!=window.opr&&void 0!=window.opr.addons&&"function"==typeof window.opr.addons.installExtension)return 4;if(void 0!=window.chrome&&"function"==typeof window.chrome.csi&&"function"==typeof window.chrome.loadTimes&&void 0!=document.webkitHidden&&(!0==document.webkitHidden||
!1==document.webkitHidden))return 3;if(void 0!=document.isConnected&&void 0!=document.webkitHidden&&(!0==document.webkitHidden||!1==document.webkitHidden))return 6;if(void 0!=window.mozInnerScreenY&&"number"==typeof window.mozInnerScreenY&&void 0!=window.mozPaintCount&&0<=window.mozPaintCount&&void 0!=window.InstallTrigger&&void 0!=window.InstallTrigger.install)return 2;if(void 0!=document.uniqueID&&"string"==typeof document.uniqueID&&(void 0!=document.documentMode&&0<=document.documentMode||void 0!=
document.all&&"object"==typeof document.all||void 0!=window.ActiveXObject&&"function"==typeof window.ActiveXObject)||window.document&&window.document.updateSettings&&"function"==typeof window.document.updateSettings)return 1;var a=!1;try{var c=document.createElement("p");c.innerText=".";c.style="text-shadow: rgb(99, 116, 171) 20px -12px 2px";a=void 0!=c.style.textShadow}catch(e){}return(0<Object.prototype.toString.call(window.HTMLElement).indexOf("Constructor")||window.webkitAudioPannerNode&&window.webkitConvertPointFromNodeToPage)&&
a&&void 0!=window.innerWidth&&void 0!=window.innerHeight?5:0}catch(i){return 0}}this.createRequest=function(){var d,a,c;window._dv_win.$dv.isEval=1;window._dv_win.$dv.DebugInfo={};var e=!1,i=!1,m,s=!1,f=window._dv_win,Ba=0,Ca=!1,Da=getCurrentTime();window._dv_win.t2tTimestampData=[{dvTagCreated:Da}];var P;try{for(P=0;10>=P;P++)if(null!=f.parent&&f.parent!=f)if(0<f.parent.location.toString().length)f=f.parent,Ba++,s=!0;else{s=!1;break}else{0==P&&(s=!0);break}}catch(ub){s=!1}var G;0==f.document.referrer.length?
G=f.location:s?G=f.location:(G=f.document.referrer,Ca=!0);var Ea="",ja=null,ka=null;try{window._dv_win.external&&(ja=void 0!=window._dv_win.external.QueuePageID?window._dv_win.external.QueuePageID:null,ka=void 0!=window._dv_win.external.CrawlerUrl?window._dv_win.external.CrawlerUrl:null)}catch(vb){Ea="&dvp_extErr=1"}if(!window._dv_win._dvScriptsInternal||!window._dv_win.dvProcessed||0==window._dv_win._dvScriptsInternal.length)return null;var Q=window._dv_win._dvScriptsInternal.pop(),C=Q.script;this.dv_script_obj=
Q;this.dv_script=C;window._dv_win.t2tTimestampData[0].dvWrapperLoadTime=Q.loadtime;window._dv_win.dvProcessed.push(Q);var b=C.src;if(void 0!=window._dv_win.$dv.CommonData.BrowserId&&void 0!=window._dv_win.$dv.CommonData.BrowserVersion&&void 0!=window._dv_win.$dv.CommonData.BrowserIdFromUserAgent)d=window._dv_win.$dv.CommonData.BrowserId,a=window._dv_win.$dv.CommonData.BrowserVersion,c=window._dv_win.$dv.CommonData.BrowserIdFromUserAgent;else{for(var Fa=dv_GetParam(b,"useragent"),Ga=Fa?decodeURIComponent(Fa):
navigator.userAgent,D=[{id:4,brRegex:"OPR|Opera",verRegex:"(OPR/|Version/)"},{id:1,brRegex:"MSIE|Trident/7.*rv:11|rv:11.*Trident/7|Edge/",verRegex:"(MSIE |rv:| Edge/)"},{id:2,brRegex:"Firefox",verRegex:"Firefox/"},{id:0,brRegex:"Mozilla.*Android.*AppleWebKit(?!.*Chrome.*)|Linux.*Android.*AppleWebKit.* Version/.*Chrome",verRegex:null},{id:0,brRegex:"AOL/.*AOLBuild/|AOLBuild/.*AOL/|Puffin|Maxthon|Valve|Silk|PLAYSTATION|PlayStation|Nintendo|wOSBrowser",verRegex:null},{id:3,brRegex:"Chrome",verRegex:"Chrome/"},
{id:5,brRegex:"Safari|(OS |OS X )[0-9].*AppleWebKit",verRegex:"Version/"}],la=0,Ha="",w=0;w<D.length;w++)if(null!=Ga.match(RegExp(D[w].brRegex))){la=D[w].id;if(null==D[w].verRegex)break;var ma=Ga.match(RegExp(D[w].verRegex+"[0-9]*"));if(null!=ma)var fb=ma[0].match(RegExp(D[w].verRegex)),Ha=ma[0].replace(fb[0],"");break}var Ia=eb();d=Ia;a=Ia===la?Ha:"";c=la;window._dv_win.$dv.CommonData.BrowserId=d;window._dv_win.$dv.CommonData.BrowserVersion=a;window._dv_win.$dv.CommonData.BrowserIdFromUserAgent=
c}var x,gb="https:"===window._dv_win.location.protocol?"https:":"http:",na=!0,oa=window.parent.postMessage&&window.JSON,Ja=!1;if("0"==dv_GetParam(b,"t2te")||window._dv_win.dv_config&&!0===window._dv_win.dv_config.supressT2T)Ja=!0;if(oa&&!1===Ja&&5!=window._dv_win.$dv.CommonData.BrowserId)try{var pa=window._dv_win.dv_config.t2turl||"https://cdn3.doubleverify.com/t2tv7.html";x=ha(pa);na=O(x)}catch(wb){}window._dv_win.$dv.DebugInfo.dvp_HTML5=oa?"1":"0";var R=dv_GetParam(b,"region")||"",S="http:",Ka=
"0";"https"==b.match("^https")&&"https"==window._dv_win.location.toString().match("^https")&&(S="https:",Ka="1");try{for(var hb=f,qa=f,ra=0;10>ra&&qa!=window._dv_win.top;)ra++,qa=qa.parent;hb.depth=ra;var La=db(f);dv_aUrlParam="&aUrl="+encodeURIComponent(La.url);dv_aUrlDepth="&aUrlD="+La.depth;dv_referrerDepth=f.depth+Ba;Ca&&f.depth--}catch(xb){dv_aUrlDepth=dv_aUrlParam=dv_referrerDepth=f.depth=""}for(var Ma=dv_GetDynamicParams(b,"dvp"),T=dv_GetDynamicParams(b,"dvpx"),U=0;U<T.length;U++){var Na=dv_GetKeyValue(T[U]);
T[U]=Na.key+"="+encodeURIComponent(Na.value)}"41"==R&&(R=50>100*Math.random()?"41":"8",Ma.push("dvp_region="+R));var Oa=Ma.join("&"),Pa=T.join("&"),ib=window._dv_win.dv_config.tpsAddress||"tps"+R+".doubleverify.com",H="visit.js";switch(dv_GetParam(b,"dvapi")){case "1":H="dvvisit.js";break;case "5":H="query.js";break;default:H="visit.js"}window._dv_win.$dv.DebugInfo.dvp_API=H;for(var V="ctx cmp ipos sid plc adid crt btreg btadsrv adsrv advid num pid crtname unit chnl uid scusrid tagtype sr dt isdvvid dup app sup".split(" "),
I=[],p=0;p<V.length;p++){var sa=dv_GetParam(b,V[p])||"";I.push(V[p]+"="+sa);""!==sa&&(window._dv_win.$dv.DebugInfo["dvp_"+V[p]]=sa)}var jb=dv_GetParam(b,"isdvvid")||"",Qa=dv_GetParam(b,"tagtype")||"";if(1!=jb&&("video"==Qa||"1"==Qa)){I.push("prplyd=1");var Ra="AICB_"+(window._dv_win.dv_config&&window._dv_win.dv_config.dv_GetRnd?window._dv_win.dv_config.dv_GetRnd():dv_GetRnd());window._dv_win[Ra]=function(){e=!0;window._dv_win.$dv&&!0==i&&window._dv_win.$dv.registerEventCall(m,{prplyd:0})};var kb=
I.join("&"),Sa=window._dv_win.document.createElement("script"),pa=S+"//cdn.doubleverify.com/dvvid_src.js?"+kb+"&AICB="+Ra;Sa.src=pa;window._dv_win.document.body.appendChild(Sa)}for(var ta="turl icall dv_callback useragent xff timecheck".split(" "),p=0;p<ta.length;p++){var Ta=dv_GetParam(b,ta[p]);null!=Ta&&I.push(ta[p]+"="+(Ta||""))}var lb=I.join("&"),y;var mb=function(){try{return!!window.sessionStorage}catch(a){return!0}},nb=function(){try{return!!window.localStorage}catch(a){return!0}},ob=function(){var a=
document.createElement("canvas");if(a.getContext&&a.getContext("2d")){var b=a.getContext("2d");b.textBaseline="top";b.font="14px 'Arial'";b.textBaseline="alphabetic";b.fillStyle="#f60";b.fillRect(0,0,62,20);b.fillStyle="#069";b.fillText("!image!",2,15);b.fillStyle="rgba(102, 204, 0, 0.7)";b.fillText("!image!",4,17);return a.toDataURL()}return null};try{var q=[];q.push(["lang",navigator.language||navigator.browserLanguage]);q.push(["tz",(new Date).getTimezoneOffset()]);q.push(["hss",mb()?"1":"0"]);
q.push(["hls",nb()?"1":"0"]);q.push(["odb",typeof window.openDatabase||""]);q.push(["cpu",navigator.cpuClass||""]);q.push(["pf",navigator.platform||""]);q.push(["dnt",navigator.doNotTrack||""]);q.push(["canv",ob()]);var k=q.join("=!!!=");if(null==k||""==k)y="";else{for(var J=function(b){for(var a="",d,e=7;0<=e;e--)d=b>>>4*e&15,a+=d.toString(16);return a},pb=[1518500249,1859775393,2400959708,3395469782],k=k+String.fromCharCode(128),z=Math.ceil((k.length/4+2)/16),A=Array(z),h=0;h<z;h++){A[h]=Array(16);
for(var B=0;16>B;B++)A[h][B]=k.charCodeAt(64*h+4*B)<<24|k.charCodeAt(64*h+4*B+1)<<16|k.charCodeAt(64*h+4*B+2)<<8|k.charCodeAt(64*h+4*B+3)}A[z-1][14]=8*(k.length-1)/Math.pow(2,32);A[z-1][14]=Math.floor(A[z-1][14]);A[z-1][15]=8*(k.length-1)&4294967295;for(var W=1732584193,X=4023233417,Y=2562383102,Z=271733878,$=3285377520,l=Array(80),E,n,t,u,aa,h=0;h<z;h++){for(var g=0;16>g;g++)l[g]=A[h][g];for(g=16;80>g;g++)l[g]=(l[g-3]^l[g-8]^l[g-14]^l[g-16])<<1|(l[g-3]^l[g-8]^l[g-14]^l[g-16])>>>31;E=W;n=X;t=Y;u=
Z;aa=$;for(g=0;80>g;g++){var Ua=Math.floor(g/20),qb=E<<5|E>>>27,F;c:{switch(Ua){case 0:F=n&t^~n&u;break c;case 1:F=n^t^u;break c;case 2:F=n&t^n&u^t&u;break c;case 3:F=n^t^u;break c}F=void 0}var rb=qb+F+aa+pb[Ua]+l[g]&4294967295;aa=u;u=t;t=n<<30|n>>>2;n=E;E=rb}W=W+E&4294967295;X=X+n&4294967295;Y=Y+t&4294967295;Z=Z+u&4294967295;$=$+aa&4294967295}y=J(W)+J(X)+J(Y)+J(Z)+J($)}}catch(yb){y=null}y=null!=y?"&aadid="+y:"";var Va=b,b=(window._dv_win.dv_config.visitJSURL||S+"//"+ib+"/"+H)+"?"+lb+"&dvtagver=6.1.src&srcurlD="+
f.depth+"&curl="+(null==ka?"":encodeURIComponent(ka))+"&qpgid="+(null==ja?"":ja)+"&ssl="+Ka+"&refD="+dv_referrerDepth+"&htmlmsging="+(oa?"1":"0")+y+Ea,v=window._dv_win.$dv.getMraid();v&&(b+="&ismraid=1");var ua;a:{try{if("object"==typeof window.$ovv||"object"==typeof window.parent.$ovv){ua=!0;break a}}catch(zb){}ua=!1}ua&&(b+="&isovv=1");var sb=b,j="";try{var r=window._dv_win,j=j+("&chro="+(void 0===r.chrome?"0":"1")),j=j+("&hist="+(r.history?r.history.length:"")),j=j+("&winh="+r.innerHeight),j=j+
("&winw="+r.innerWidth),j=j+("&wouh="+r.outerHeight),j=j+("&wouw="+r.outerWidth);r.screen&&(j+="&scah="+r.screen.availHeight,j+="&scaw="+r.screen.availWidth)}catch(Ab){}b=sb+(j||"");"http:"==b.match("^http:")&&"https"==window._dv_win.location.toString().match("^https")&&(b+="&dvp_diffSSL=1");var Wa=C&&C.parentElement&&C.parentElement.tagName&&"HEAD"===C.parentElement.tagName;if(!1===na||Wa)b+="&dvp_isBodyExistOnLoad="+(na?"1":"0"),b+="&dvp_isOnHead="+(Wa?"1":"0");Oa&&(b+="&"+Oa);Pa&&(b+="&"+Pa);var K=
"srcurl="+encodeURIComponent(G);window._dv_win.$dv.DebugInfo.srcurl=G;var ba;var ca=window._dv_win[ia("=@42E:@?")][ia("2?46DE@C~C:8:?D")];if(ca&&0<ca.length){var va=[];va[0]=window._dv_win.location.protocol+"//"+window._dv_win.location.hostname;for(var da=0;da<ca.length;da++)va[da+1]=ca[da];ba=va.reverse().join(",")}else ba=null;ba&&(K+="&ancChain="+encodeURIComponent(ba));var L=dv_GetParam(b,"uid");null==L?(L=dv_GetRnd(),b+="&uid="+L):(L=dv_GetRnd(),b=b.replace(/([?&]uid=)(?:[^&])*/i,"$1"+L));var wa=
4E3;/MSIE (\d+\.\d+);/.test(navigator.userAgent)&&7>=new Number(RegExp.$1)&&(wa=2E3);var Xa=navigator.userAgent.toLowerCase();if(-1<Xa.indexOf("webkit")||-1<Xa.indexOf("chrome")){var Ya="&referrer="+encodeURIComponent(window._dv_win.location);b.length+Ya.length<=wa&&(b+=Ya)}dv_aUrlParam.length+dv_aUrlDepth.length+b.length<=wa&&(b+=dv_aUrlDepth,K+=dv_aUrlParam);var Za=cb(),b=b+("&vavbkt="+Za.vdcd),b=b+("&lvvn="+Za.vdcv),b=b+("&"+this.getVersionParamName()+"="+this.getVersion()),b=b+("&eparams="+encodeURIComponent(ia(K))),
b=b+("&brid="+d+"&brver="+a+"&bridua="+c);window._dv_win.$dv.DebugInfo.dvp_BRID=d;window._dv_win.$dv.DebugInfo.dvp_BRVR=a;window._dv_win.$dv.DebugInfo.dvp_BRIDUA=c;var M;void 0!=window._dv_win.$dv.CommonData.Scenario?M=window._dv_win.$dv.CommonData.Scenario:(M=this.getTrafficScenarioType(window._dv_win),window._dv_win.$dv.CommonData.Scenario=M);b+="&tstype="+M;window._dv_win.$dv.DebugInfo.dvp_TS=M;var ea="";try{window.top==window?ea="1":window.top.location.host==window.location.host&&(ea="2")}catch(Bb){ea=
"3"}var fa=window._dv_win.document.visibilityState,$a=function(){var a=!1;try{a=v&&"function"===typeof v.getState&&"loading"===v.getState()}catch(d){b+="&dvp_mrgsf=1"}return a},xa=$a();if("prerender"===fa||xa)b+="&prndr=1",xa&&(b+="&dvp_mrprndr=1");var ab="dvCallback_"+(window._dv_win.dv_config&&window._dv_win.dv_config.dv_GetRnd?window._dv_win.dv_config.dv_GetRnd():dv_GetRnd()),tb=this.dv_script;window._dv_win[ab]=function(a,d,c,f){var g=getCurrentTime();i=!0;m=c;d.$uid=c;d=Aa(Va);a.tags.add(c,d);
d=Aa(b);a.tags[c].set(d);a.tags[c].beginVisitCallbackTS=g;a.tags[c].set({tagElement:tb,dv_protocol:S,protocol:gb,uid:c});a.tags[c].ImpressionServedTime=getCurrentTime();a.tags[c].getTimeDiff=function(){return(new Date).getTime()-this.ImpressionServedTime};try{"undefined"!=typeof f&&null!==f&&(a.tags[c].ServerPublicDns=f),a.tags[c].adServingScenario=ea,a.tags[c].t2tIframeCreationTime=Da,a.tags[c].t2tProcessed=!1,a.tags[c].t2tIframeId=x.id,a.tags[c].t2tIframeWindow=x.contentWindow,$dv.t2tEventDataZombie[x.id]&&
(a.tags[c].uniquePageViewId=$dv.t2tEventDataZombie[x.id].uniquePageViewId,$dv.processT2TEvent($dv.t2tEventDataZombie[x.id],a.tags[c]))}catch(j){}!0==e&&a.registerEventCall(c,{prplyd:0});if("prerender"===fa)if("prerender"!==window._dv_win.document.visibilityState&&"unloaded"!==visibilityStateLocal)a.tags[c].set({prndr:0}),a.registerEventCall(c,{prndr:0}),a&&a.pubsub&&a.pubsub.publishHistoryRtnEvent(c);else{var h;"undefined"!==typeof window._dv_win.document.hidden?h="visibilitychange":"undefined"!==
typeof window._dv_win.document.mozHidden?h="mozvisibilitychange":"undefined"!==typeof window._dv_win.document.msHidden?h="msvisibilitychange":"undefined"!==typeof window._dv_win.document.webkitHidden&&(h="webkitvisibilitychange");var k=function(){var b=window._dv_win.document.visibilityState;"prerender"===fa&&("prerender"!==b&&"unloaded"!==b)&&(fa=b,a.tags[c].set({prndr:0}),a.registerEventCall(c,{prndr:0}),a&&a.pubsub&&a.pubsub.publishHistoryRtnEvent(c),window._dv_win.document.removeEventListener(h,
k))};window._dv_win.document.addEventListener(h,k,!1)}else if(xa){var l=function(){"function"===typeof v.removeEventListener&&v.removeEventListener("ready",l);a.tags[c].set({prndr:0});a.registerEventCall(c,{prndr:0});a&&a.pubsub&&a.pubsub.publishHistoryRtnEvent(c)};$a()?"function"===typeof v.addEventListener&&v.addEventListener("ready",l):(a.tags[c].set({prndr:0}),a.registerEventCall(c,{prndr:0}),a&&a.pubsub&&a.pubsub.publishHistoryRtnEvent(c))}};for(var bb,ga="auctionid vermemid source buymemid anadvid ioid cpgid cpid sellerid pubid advcode iocode cpgcode cpcode pubcode prcpaid auip auua".split(" "),
ya=[],N=0;N<ga.length;N++){var za=dv_GetParam(Va,ga[N]);null!=za&&(ya.push("dvp_"+ga[N]+"="+za),ya.push(ga[N]+"="+za))}(bb=ya.join("&"))&&(b+="&"+bb);return b+"&jsCallback="+ab};this.sendRequest=function(d){var a;a=this.getVersionParamName();var c=this.getVersion(),e={};e[a]=c;e.dvp_jsErrUrl=d;e.dvp_jsErrMsg=encodeURIComponent("Error loading visit.js");window._dv_win.dv_config=window._dv_win.dv_config||{};window._dv_win.dv_config.tpsErrAddress=window._dv_win.dv_config.tpsAddress||"tps30.doubleverify.com";
a='try{ script.onerror = function(){ try{(new Image()).src = "'+dv_CreateAndGetErrorImp(window._dv_win.dv_config.tpsErrAddress+"/visit.jpg?ctx=818052&cmp=1619415&dvtagver=6.1.src&dvp_isLostImp=1",e)+'";}catch(e){}}}catch(e){}';a='<html><head></head><body><script id="TPSCall" type="text/javascript" src="'+d+'"><\/script><script type="text/javascript">var script = document.getElementById("TPSCall"); if (script && script.readyState) { script.onreadystatechange = function() { if (script.readyState == "complete") document.close(); }; if(script.readyState == "complete") document.close(); } else document.close(); '+
a+"<\/script></body></html>";c=ha("about:blank");this.dv_script.id=c.id.replace("iframe","script");dv_GetParam(d,"uid");O(c);d=dv_getPropSafe(c,"contentDocument")||dv_getPropSafe(dv_getPropSafe(c,"contentWindow"),"document")||dv_getPropSafe(window._dv_win.frames[c.name],"document");window._dv_win.t2tTimestampData.push({beforeVisitCall:getCurrentTime()});if(d){d.open();if(c=c.contentWindow||window._dv_win.frames[c.name])c.$dv=window._dv_win.$dv;d.write(a)}else d=a.replace(/'/g,"\\'"),d='javascript: (function(){document.open(); document.domain="'+
window.document.domain+"\"; window.$dv = window.parent.$dv; document.write('"+encodeURIComponent(d)+"');})()",c=ha(d),this.dv_script.id=c.id.replace("iframe","script"),O(c);return!0};this.isApplicable=function(){return!0};this.onFailure=function(){window._dv_win._dvScriptsInternal.unshift(this.dv_script_obj);var d=window._dv_win.dvProcessed,a=this.dv_script_obj;null!=d&&(void 0!=d&&a)&&(a=d.indexOf(a),-1!=a&&d.splice(a,1));return window._dv_win.$dv.DebugInfo};this.getTrafficScenarioType=function(d){var d=
d||window,a=d._dv_win.$dv.Enums.TrafficScenario;try{if(d.top==d)return a.OnPage;for(var c=0;d.parent!=d&&1E3>c;){if(d.parent.document.domain!=d.document.domain)return a.CrossDomain;d=d.parent;c++}return a.SameDomain}catch(e){}return a.CrossDomain};this.getVersionParamName=function(){return"jsver"};this.getVersion=function(){return"88"}};


function dv_src_main(dv_baseHandlerIns, dv_handlersDefs) {

    this.baseHandlerIns = dv_baseHandlerIns;
    this.handlersDefs = dv_handlersDefs;

    this.exec = function () {
        try {
            window._dv_win = (window._dv_win || window);
            window._dv_win.$dv = (window._dv_win.$dv || new dvType());

            window._dv_win.dv_config = window._dv_win.dv_config || {};
            window._dv_win.dv_config.tpsErrAddress = window._dv_win.dv_config.tpsAddress || 'tps30.doubleverify.com';

            var errorsArr = (new dv_rolloutManager(this.handlersDefs, this.baseHandlerIns)).handle();
            if (errorsArr && errorsArr.length > 0)
                dv_SendErrorImp(window._dv_win.dv_config.tpsErrAddress + '/visit.jpg?ctx=818052&cmp=1619415&dvtagver=6.1.src', errorsArr);
        }
        catch (e) {
            try {
                dv_SendErrorImp(window._dv_win.dv_config.tpsErrAddress + '/visit.jpg?ctx=818052&cmp=1619415&dvtagver=6.1.src&jsver=0&dvp_isLostImp=1', { dvp_jsErrMsg: encodeURIComponent(e) });
            } catch (e) { }
        }
    }
}

try {
    window._dv_win = window._dv_win || window;
    var dv_baseHandlerIns = new dv_baseHandler();
	

    var dv_handlersDefs = [];
    (new dv_src_main(dv_baseHandlerIns, dv_handlersDefs)).exec();
} catch (e) { }

