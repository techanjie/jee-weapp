(function($) {
	$.fn.extend({
		insertAtCaret : function(myValue) {
			var $t = $(this)[0];
			// IE
			if (document.selection) {
				this.focus();
				sel = document.selection.createRange();
				sel.text = myValue;
				this.focus();
			} else
			// !IE
			if ($t.selectionStart || $t.selectionStart == "0") {
				var startPos = $t.selectionStart;
				var endPos = $t.selectionEnd;
				var scrollTop = $t.scrollTop;
				$t.value = $t.value.substring(0, startPos) + myValue
						+ $t.value.substring(endPos, $t.value.length);
				this.focus();
				$t.selectionStart = startPos + myValue.length;
				$t.selectionEnd = startPos + myValue.length;
				$t.scrollTop = scrollTop;
			} else {
				this.value += myValue;
				this.focus();
			}
		}
	})
})(jQuery);

/*
 * $.browser.mozilla = /firefox/.test(navigator.userAgent.toLowerCase());
 * $.browser.webkit = /webkit/.test(navigator.userAgent.toLowerCase());
 * $.browser.opera = /opera/.test(navigator.userAgent.toLowerCase());
 * $.browser.msie = /msie/.test(navigator.userAgent.toLowerCase());
 */

// JS全局对象 wangjun
var obz = obz
		|| {
			ctx : '',
			version : '2.0',
			uuid : function() {
				return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(
						/[xy]/g,
						function(c) {
							var r = Math.random() * 16 | 0, v = c == 'x' ? r
									: (r & 0x3 | 0x8);
							return v.toString(16);
						}).toUpperCase();
			},
			createDiv : function(divid) {
				var newDiv = document.getElementById(divid);
				if (!newDiv) {
					newDiv = document.createElement("DIV");
					newDiv.setAttribute("id", divid);
				}
				// 每次调用创建div都会清空div的内容
				$("#" + divid).empty();
				return newDiv;
			},
			// 调用jquery ui dialog 封装系统消息提示框
			// okCallback 点击确认的回调函数
			// cancelCallback 点击取消的回调函数
			showMessage : function(message, okCallback, cancelCallback) {
				var okCallbackFun = okCallback, cancelCallbackFun = cancelCallback;
				// use bootstrap dialog
				BootstrapDialog.confirm({
					title : "提示",
					message : message,
					btnCancelLabel : '取消', // <-- Default value is 'Cancel',
					btnOKLabel : '确定',
					closable : true,
					callback : function(result) {
						if (result) {
							if (okCallbackFun)
								okCallbackFun();
						} else {
							if (cancelCallbackFun) {
								cancelCallback();
							}
						}
					}
				});
				return false;
			},
			error : function(msg, callback) {
				if (callback)
					toastr.options.onHidden = callback;
				toastr.error(msg, "错误");
			},
			info : function(msg, callback) {
				if (callback)
					toastr.options.onHidden = callback;
				toastr.info(msg, "提示");
			},
			msg : function(msg, callback) {
				if (callback)
					toastr.options.onHidden = callback;
				toastr.success(msg, "提示");
			},
			warn : function(msg, callback) {
				if (callback)
					toastr.options.onHidden = callback;
				toastr.warning(msg, "警告");
			},
			
			open: function(title, url){
				var dialog = BootstrapDialog.show({
					size: BootstrapDialog.SIZE_WIDE,
					title: title,
			        message: $('<div></div>').load(url)
			    });
				return dialog;
			},
			
			open2: function(title, url, exec){
				var dialog = BootstrapDialog.show({
					size: BootstrapDialog.SIZE_WIDE,
					title: title,
			        message: $('<div></div>').load(url),
			        buttons : [ {// 设置关闭按钮
						label : '确定',
						action : function(dialogItself) {
							if(exec){
								exec(dialogItself);
							}
						}
					} ]
			    });
				return dialog;
			},

			showQrcode : function(wirlessUrl) {
				var title = "", content = "";
				if (wirlessUrl == "") {
					title = '<div style="text-align:center; color:red; text-decoration:underline; font-size:14px;">请绑定微信公众号</div>';
					content = '<div id="popOverBox" align="center"><a href="'
							+ obz.ctx + '/weixin/auth2">立即绑定</a></div>';
				} else if (wirlessUrl == "notverify") {
					title = '<div style="text-align:center; color:red; text-decoration:underline; font-size:14px;">公众号未认证</div>';
					content = '<div id="popOverBox" align="center"><a href="https://mp.weixin.qq.com/" target="_blank">请认证</a></div>';
				} else {
					title = '<div style="text-align:center; color:red; text-decoration:underline; font-size:14px;">微信扫描二维码</div>';
					content = '<div id="popOverBox" align="center"><img style="width:200px; height:200px" src="'
							+ obz.ctx
							+ '/qrcode/genio/?url='
							+ wirlessUrl
							+ '"/><br/>手机端链接<br/><input value="'
							+ wirlessUrl
							+ '" style="width:220px;"></input></div>';
				}
				$(".fa-qrcode").popover({
					trigger : 'manual',
					placement : 'right', // placement of the popover. also
					// can use top, bottom, left or
					// right
					title : title,
					html : 'true', // needed to show html of course
					content : content,
					animation : false
				}).on("mouseenter", function() {
					var _this = this;
					$(this).popover("show");
					$(this).siblings(".popover").on("mouseleave", function() {
						$(_this).popover('hide');
					});
				}).on("mouseleave", function() {
					var _this = this;
					setTimeout(function() {
						if (!$(".popover:hover").length) {
							$(_this).popover("hide");
						}
					}, 100);
				});
			}
		};

// 发送POST AJAX请求,返回JSON数据 wangjun
obz.ajaxJson = function(url, param, callback, failureCallback) {
	if(param){ 
		param.csrf_token = $("#CSRF_TOKEN_HIDDEN").val();
	}
	
	$.ajax({
		url : url,
		data : param,
		type : "POST",
		beforeSend : function() {},
		success : function(data) {
			if (data.state) {
				if (data.state == "fail") {
					obz.error(data.message == null ? "系统错误" : data.message);
					if (failureCallback != undefined) {
						failureCallback();
					} else {
						if (callback != undefined)
							callback(data);
					}
				} else if (data.state == "ok") {
					if (callback != undefined)
						callback(data);
				} else {
					obz.error("未知异常");
				}
				return;
			}

			if (data.Success == undefined) {
				// 兼容 IE
				data = $.parseJSON(data);
			}
			if (data.Success) {
				if (data.code && data.code != 200) {
					obz.error(data.msg == null ? "系统错误" : data.msg);
				}
				if (callback != undefined)
					callback(data);
			} else {
				if (failureCallback != undefined) {
					failureCallback();
				}
				// 验证拦截器使用，把错误信息显示在前台
				for ( var key in data) {
					// $("#"+key).text(data[key]);
					$("#" + key).addClass("has-error");
					$("#" + key).find("label").text(data[key]);
				}
			}
		},
		error : function(request) {
			if (failureCallback != undefined)
				failureCallback();
			else {
				if (typeof request != 'undefined' && request) {
					if (request.response) {
						alert(request.response);
					} else {
						alert("服务端没有响应,请联系客服......");
					}
					location.href = obz.ctx;
				}
			}
		}
	});
};

function isFloat(n) {
	return ((typeof n === 'number') && (n % 1 !== 0));
}
/**
 * 一个用作js模板替换的代码 template格式和数组格式如下 var template = "<div>
 * <h1>{title}</h1>
 * <p>
 * {content}
 * </p>
 * </div>"; var data =
 * [{title:"a",content:"aaaa"},{title:"b",content:"bbb"},{title:"c",content:"ccc"}];
 * 只需要数据格式对应
 */
obz.dataTemplate = function(template, data, dec) {
	var outPrint = "";
	for (var i = 0; i < data.length; i++) {
		var matchs = template.match(/\{[a-zA-Z0-9\.\$_-]+\}/gi);
		var temp = "";
		for (var j = 0; j < matchs.length; j++) {
			if (temp == "")
				temp = template;
			var re_match = matchs[j].replace(/[\{\}]/gi, "");
			if (dec && isFloat(data[i][re_match])) {
				temp = temp.replace(matchs[j], data[i][re_match].toFixed(dec));
			} else {
				temp = temp.replace(matchs[j], data[i][re_match]);
			}

		}
		outPrint += temp;
	}
	return outPrint;
};

obz.dataTemplate4obj = function(template, obj, dec) {
	var matchs = template.match(/\{[a-zA-Z0-9\.\$_-]+\}/gi);
	var temp = "";
	for (var j = 0; j < matchs.length; j++) {
		if (temp == "")
			temp = template;
		var re_match = matchs[j].replace(/[\{\}]/gi, "");
		if (dec && isFloat(obj[re_match])) {
			temp = temp.replace(matchs[j], obj[re_match].toFixed(dec));
		} else {
			temp = temp.replace(matchs[j], obj[re_match] ? obj[re_match] : "");
		}

	}
	return temp;
}

// 弹出对话框组件开始，基于JqueryUi进行封装 默认不使用iframe 传true才使用iframe
// 通过src远程加载对话框内容
// src 远程地址
// div 对话框div id值
// callback 远程load后回调方法
// isIframe 是否使用 Iframe装载
// 自动加载load mask遮罩层
obz.PopupDialog = function(src, div, callback, isIframe) {
	this.src = src;
	this.div = div;
	this.callback = callback;
	this.isIframe = isIframe;
}

obz.PopupDialog.prototype = {
	width : '90%',
	height : '100%',
	div : null,
	src : null,
	isIframe : null,
	popupDialog : null,
	callback : null,

	init : function() {
		$(document.body).css("overflow", "hidden");
		this.show();
	},

	show : function() {
		var newisIframe = this.isIframe;
		var newCallback = this.callback;
		var newSrc = this.src;
		var newDivid = this.div;

		if (newSrc.indexOf("?") < 0) {
			newSrc += "?";
		}
		newSrc += "&_=" + new Date().valueOf().toString();

		var temWidth = $(window).width();
		var temHeight = $(window).height();
		if (temWidth >= 768) {
			this.width = temWidth * 0.85;
			this.height = temHeight * 0.9;
		} else {
			this.width = temWidth - 20;
			this.height = temHeight - 20;
		}

		var thisObject = this;
		var newDiv = document.getElementById(newDivid);

		// 只第一次创建div的时候才使用div去load页面，如果需要刷新，请手动删除
		if (!newDiv) {
			newDiv = document.createElement("DIV");
			newDiv.setAttribute("id", newDivid);
			if (this.isIframe && this.isIframe == true) {// 使用IFrame情况
				// 创建IFrame
				var newFrame = document.getElementById("jqueryDialogFrame");
				if (!newFrame) {
					newFrame = document.createElement("IFRAME");
					newFrame.setAttribute("id", "jqueryDialogFrame");
					newFrame.setAttribute("name", "jqueryDialogFrame");
					newFrame.setAttribute("frameborder", "0");
					newFrame.setAttribute("width", "100%");
					newFrame.setAttribute("height", this.height - 20);
					newFrame.setAttribute("scrolling", "no");
					newDiv.appendChild(newFrame);
					document.body.appendChild(newDiv);
				}
			} else {// div直接Loading
				$(newDiv).mask("加载中...");
				if (newCallback) {
					$(newDiv).load(newSrc, newCallback);
				} else {
					$(newDiv).load(newSrc);
				}
			}
		}

		var openDialog = function() {
			if (newisIframe && newisIframe == true) {
				var newFrame = document.getElementById("jqueryDialogFrame");
				if (newFrame != null) {
					setTimeout(function() {
						newFrame.setAttribute("src", newSrc);
					}, 100);
				}
			}
			$(".ui-dialog.ui-widget").css("position", "fixed");
			$(".ui-dialog.ui-widget").css("top", "5%");
		}

		var closePopupDialog = function() {
			if (newisIframe && newisIframe == true) {
				var newFrame = document.getElementById("jqueryDialogFrame");
				if (newFrame != null) {
					newFrame.setAttribute("src", "");
				}
			}
			// 关闭后移除div,每次都请求后台
			$(document.body).css("overflow", "visible");
			$(newDiv).remove();
		}

		this.popupDialog = $(newDiv).dialog({
			modal : true,
			// title: this.title,
			// show: 'drop',
			// hide: 'scale',
			// minWidth: this.width,
			// minHeight: this.height,
			width : this.width,
			height : this.height,
			position : 'center',
			draggable : false,
			autoOpen : true,
			resizable : false,
			overlay : {
				opacity : 0.5,
				background : "black"
			},
			create : function() {
				$(this).closest(".ui-dialog").find(".ui-button").css({
					'height' : '28px'
				});
			},
			open : openDialog,
			close : closePopupDialog,
			zIndex : 15001
		});
	},

	close : function() {
		var result = false;
		if (this.popupDialog != null) {
			this.popupDialog.dialog("close");
			this.popupDialog = null;
			result = true;
		}
		return result;
	}
}
// 弹出框组件结束

// tab组件开始
// 基于JQuery UI 的tab组件进行封装 wangjun
obz.TabView = function(div, orientation, selectCallback) {
	this.div = div;
	this.orientation = orientation;
	this.selectFunction = selectCallback;
}

obz.TabView.prototype = {
	div : '',
	orientation : 'top',
	tabView : '',
	selectFunction : null, // callback function onSelect

	init : function() {
		// var thisObject = this;
		if (this.selectFunction)
			this.tabView = $("#" + this.div).tabs({
				heightStyleType : "fill",
				activate : this.selectFunction
			});
		else {
			this.tabView = $("#" + this.div).tabs({
				heightStyleType : "fill"
			});
		}
	},

	getTab : function(index) {
		// return this.tabView.getTab(index);
	},

	getActiveViewIndex : function() {
		var tabs = this.tabView.find("li");
		var activeTab = this.getActiveTab();
		return tabs.index(activeTab);
	},

	getActiveTab : function() {
		var activeTab = this.tabView.find("li.ui-tabs-active:first");
		if (activeTab.size() == 0) {
			activeTab = undefined;
		}
		return activeTab;
	},

	select : function(index) {
		// this.tabView.tabs("option", "active", $(index).index());
		this.tabView.tabs("option", "active", index);
		// this.tabView.tabs("option", "select" , index);
	},

	disable : function(index) {
		this.tabView.tabs("disable", index);
	}
}
// tab组件结束

// 分页组件开始
// 基于jquery page 插件进行封装 wangjun
obz.TableView = function(pageId, url, params, doDataListCallBack) {
	this.pageId = pageId;
	this.params = params;
	this.url = url;
	this.doDataListCallBack = doDataListCallBack;
}

obz.TableView.prototype = {

	setParams : function(params) {
		this.params = params || {};
	},

	pageClick : function(table, pageNo) {
		table.query(pageNo);
	},

	init : function() {
		/*
		 * this.pagediv = $('<div class="jqpager" style="margin-bottom: 1px;"></div>');
		 * $(document.body).append(this.pagediv);
		 */
		this.query();
	},

	query : function(currPage) {
		var me = this;
		if (currPage) {
			this.params.page = currPage;
		}
		$("#" + me.pageId).mask("加载中...");
		this.params.csrf_token = $("#CSRF_TOKEN_HIDDEN").val();
		obz.ajaxJson(this.url, this.params, function(resp) {
			$("#" + me.pageId).unmask();
			if (resp.code && resp.code != 200) {
				return;
			}
			if (resp.state && resp.state == "fail")
				return;

			var result = resp.data || {};
			if (result.totalRow) {
				if (currPage) {
					$("#" + me.pageId).pager({
						table : me,
						pagenumber : currPage,
						recordcount : result.totalRow,
						pagesize : result.pageSize,
						recordtext : '共 {0} 页, {1} 条记录',
						buttonClickCallback : me.pageClick
					});
				} else {
					$("#" + me.pageId).pager({
						table : me,
						recordcount : result.totalRow,
						pagesize : result.pageSize,
						recordtext : '共 {0} 页, {1} 条记录',
						buttonClickCallback : me.pageClick
					});
				}
			}

			if (me.doDataListCallBack)
				me.doDataListCallBack(result);
		});
	}
}
// 分页组件结束

// 树组件开始
obz.TreeView = function(element, options) {
	this.element = element;
	this.datalistList;
	this.container;
	this.callbacks = [];
	this.isInit = false;
};

obz.TreeView.prototype = {
	init : function() {
		var viewer = this;
		var deferreds = [];
		if (!this.isInit) {
			var dl = $.Deferred();
			deferreds.push(dl);
			$
					.ajax({
						url : this.url,
						dataType : "json",
						success : function(response) {
							viewer.datalistList = {};
							for ( var i in response) {
								if (response[i].value !== "") {
									viewer.datalistList[response[i].value] = response[i].label;
								}
							}
							dl.resolve();
						},
						error : function() {
							dl.resolve();
						}
					});
		} else {
			var dummy = $.Deferred();
			deferreds.push(dummy);
			dummy.resolve();
		}

		if (viewer.container === undefined) {
			viewer.container = $('<div id="dependencyTreeContainer"><div class="dt-buttons sticky-buttons"><a id="dt-collapse" class="button">'
					+ get_advtool_msg("dependency.tree.Collapse.All")
					+ '</a> <a id="dt-expand" class="button">'
					+ get_advtool_msg("dependency.tree.Expand.All")
					+ '</a><a id="dt-save" class="button">'
					+ get_advtool_msg("dependency.tree.Generate.Image")
					+ '</a></div><div id="dependencyTreeViewer"></div></div>');
			viewer.element.append(viewer.container);

			$(".dt-buttons #dt-collapse").click(function() {
				$('#dependencyTreeViewer').jstree('close_all');
				return false;
			});

			$(".dt-buttons #dt-expand").click(function() {
				$('#dependencyTreeViewer').jstree('open_all');
				return false;
			});

			$(".dt-buttons #dt-save")
					.click(
							function() {
								$(this)
										.prepend(
												'<i class="fa fa-spinner fa-spin"></i>');

								// create invisible div for canvas
								var div = $('<div id="dependencyTreeExport">');
								var iwidth = $('#dependencyTreeViewer').width() + 100;
								var iheight = $('#dependencyTreeViewer')
										.height() + 100;

								var part = -1;
								var size = 8000;
								if (iheight > 8000) {
									var tempH = iheight;
									var count = 2;
									do {
										tempH = iheight / count;
										tempH = Math.ceil(tempH / 100) * 100;
										count++;
									} while (tempH > 8000)
									iheight = tempH;
									size = tempH;

									part = 1;
								}

								$(div).css("padding", "50px").css("background",
										"#fff").css("overflow", "hidden")
										.width(iwidth).height(iheight);
								$(div).append(
										$('#dependencyTreeContainer').html());
								$(div).find(".dt-buttons").remove();
								$("body").append(div);

								viewer
										.generateImage(div, 0, size, $(
												'#dependencyTreeViewer')
												.height() + 100, part);
								return false;
							});
		}

		$(viewer.dataSelector).on(
				"change",
				function() {
					var activeTab = $('.builder_tool_tabs li.ui-tabs-active a')
							.attr("id");
					if (activeTab !== "treeViewer") {
						$("#dependencyTreeViewer").html("");
					}
				});

		$.when.apply($, deferreds).then(function() {
			viewer.isInit = true;
		});
	},
	render : function() {
		var viewer = this;

		if ($(viewer.element).find("#dependencyTreeViewer ul").length === 1) {
			return;
		}

		if ($(viewer.element).find(".dt-loading").length === 0) {
			$(viewer.element).prepend(
					'<i class="dt-loading fa fa-5x fa-spinner fa-spin"></i>');
		}

		if (!viewer.isInit) {
			// wait until it is initialized
			setTimeout(function() {
				viewer.render();
			}, 200);
		}

		var data = $(viewer.dataSelector).val();
		var jsonObj = JSON.parse(data);

		var deferreds = [];
		DependencyTree.elements = []; // clear previous data
		var tree = $.extend(true, {}, DependencyTree.Node);
		DependencyTree.Util.runMatchers(this, deferreds, tree, jsonObj);

		$.when
				.apply($, deferreds)
				.then(
						function() {
							DependencyTree.Util.cleanData(tree);
							$("#dependencyTreeViewer").jstree('destroy');
							$("#dependencyTreeViewer").html("");

							$('#dependencyTreeViewer').jstree({
								"types" : {
									"default" : {
										"icon" : "fa fa-cube"
									},
									"childs" : {
										"icon" : "fa fa-cubes"
									},
									"row" : {
										"icon" : "fa fa-bars"
									},
									"properties" : {
										"icon" : "fa fa-newspaper-o"
									}
								},
								"plugins" : [ "types", "wxmall" ],
								'core' : {
									'data' : tree
								}
							});

							$("#dependencyTreeContainer")
									.on(
											"click",
											".indicator",
											function() {
												var i = $(this)
														.data("callback");
												if (viewer.callbacks[i] !== undefined
														&& $
																.type(viewer.callbacks[i]) === "function") {
													viewer.callbacks[i]();
												}
											});

							$(viewer.element).find(".dt-loading").remove();
						});
	}
}
// 树组件结束

// 选择奖品组件开始
obz.selectPrizeCallBack;
obz.selectDialog;

obz.selectYouzanCallBack;
obz.selectYouzanDialog; // 选择有赞商品

obz.selectMyselfCallBack;
obz.selectMyselfDialog; // 选择自营商品

obz.doSelectPrzie = function(id, prizeName) {
	if (obz.selectPrizeCallBack) {
		obz.selectPrizeCallBack(id, prizeName);
	}
	if (selectDialog)
		selectDialog.close();
}
obz.selectPrize = function(params, callBack) {
	selectDialog = BootstrapDialog.show({
		size : BootstrapDialog.SIZE_WIDE,
		title : "选择奖品",
		message : $('<div></div>').load(obz.ctx + '/prize/selectPrize')
	});
	obz.selectPrizeCallBack = callBack;
}

// 选择有赞
obz.selectYouzanPrize = function(params, callBack) {
	selectYouzanDialog = BootstrapDialog.show({
		size : BootstrapDialog.SIZE_WIDE,
		title : "选择有赞商品",
		message : $('<div></div>').load(obz.ctx + '/prize/type/youzanList')
	});
	obz.doYouzanSelectPrzie = callBack;
}

obz.doYouzanSelectPrzie = function(id, prizeName) {
	if (obz.selectYouzanCallBack) {
		obz.selectYouzanCallBack(id, prizeName);
	}
	if (selectYouzanDialog)
		selectYouzanDialog.close();
}

//选择自营
obz.selectMyselfPrize = function(params, callBack) {
	selectMyselfDialog = BootstrapDialog.show({
		size : BootstrapDialog.SIZE_WIDE,
		title : "选择自营商品",
		message : $('<div></div>').load(obz.ctx + '/prize/type/myselfList')
	});
	obz.doMyselfSelectPrzie = callBack;
}

// 选择奖品组件结束

// =====================================================选择图片组件开始============================================
obz.selectImageCallBack;
obz.selectImageDialog;
obz.selectModelSingle = false;// 图片单选还是多选模式，默认是多选
obz.doSelectImage = function(selImgObjs) {
	if (obz.selectImageCallBack) {
		var callbackResp = obz.selectImageCallBack(selImgObjs);
		if (callbackResp) {
			if (selectImageDialog)
				selectImageDialog.close();
		}
	} else {
		if (selectImageDialog)
			selectImageDialog.close();
	}
}
// 选择图片私有方法
obz._selectImage = function(params, callBack) {
	var url = obz.ctx + "/upload/addImage";
	if (params.model) {
		obz.selectModelSingle = params.model;
	}
	selectImageDialog = BootstrapDialog.show({
		size : BootstrapDialog.SIZE_WIDE,
		title : "选择图片",
		message : $('<div></div>').load(url)
	});
	obz.selectImageCallBack = callBack;
}
// 多选图片
obz.selectImage = function(callBack) {
	obz._selectImage({
		model : false
	}, callBack);
}
// 单选图片
obz.selectImageOne = function(callBack) {
	obz._selectImage({
		model : true
	}, callBack);
}
// ======================================================选择图片组件结束============================================

// 上传图片
obz.uploadImageCallBack;
obz.uploadImageDialog;
obz.doUploadImage = function(obj) {
	if (obz.uploadImageCallBack) {
		obz.uploadImageCallBack(obj);
	}
	if (uploadImageDialog)
		uploadImageDialog.close();
}
obz.uploadImage = function(params, callBack) {
	var url = obz.ctx + "/upload/addUploadImage";
	if (params.groupId) {
		url = obz.ctx + '/upload/addUploadImage?groupId=' + params.groupId;
	}

	uploadImageDialog = BootstrapDialog.show({
		size : BootstrapDialog.SIZE_NORMAL,
		title : "上传图片",
		message : $('<div></div>').load(url)
	});
	obz.uploadImageCallBack = callBack;
}

// 添加规格
obz.addSpecificationDialog;
obz.addSpecificationCallBack;
obz.doAddSpecification = function(obj) {
	if (obz.addSpecificationCallBack) {
		obz.addSpecificationCallBack(obj);
	}
	if (addSpecificationDialog)
		addSpecificationDialog.close();
}
obz.addSpecification = function(params, callBack) {
	addSpecificationDialog = BootstrapDialog.show({
		size : BootstrapDialog.SIZE_NORMAL,
		title : "添加规格",
		message : $('<div></div>').load(
				obz.ctx + '/specification/addSpecification')
	});
	obz.addSpecificationCallBack = callBack;
}

// 添加用户
obz.addTouserDialog;
obz.addTouserCallBack;
obz.doAddTouser = function(obj) {
	if (obz.addTouserCallBack) {
		obz.addTouserCallBack(obj);
	}
	if (addTouserDialog)
		addTouserDialog.close();
}
obz.addTouser = function(params, callBack) {
	addTouserDialog = BootstrapDialog.show({
		size : BootstrapDialog.SIZE_WIDE,
		title : "选择投放客户",
		message : $('<div></div>').load(obz.ctx + '/customer/selectCustomer')
	});
	obz.addTouserCallBack = callBack;
}
