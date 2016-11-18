/**
 * dialog 1.0 - jQuery Plug-in
 * 
 * Licensed under the GPL:
 *   http://gplv3.fsf.org
 *
 * Copyright 2009 stworthy [ stworthy@gmail.com ] 
 * 
 * Dependencies:
 * 	shadow
 * 	draggable
 * 	resizable
 * 	linkbutton
 * 
 */
(function($j){
	function open(target) {
		var state = $j.data(target, 'dialog');
		var options = state.options;
		
		switch(options.showType) {
			case null:
				state.dialog.css('display', 'block');
				break;
			case 'slide':
				state.dialog.slideDown(options.showSpeed, function(){resize(target)});
				break;
			case 'fade':
				state.dialog.fadeIn(options.showSpeed, function(){resize(target)});
				break;
			case 'show':
				state.dialog.show(options.showSpeed, function(){resize(target)});
				break;
		}
		
		if (state.mask) {
			state.mask.css('display', 'block');
		}
		if (state.shadow) {
			state.shadow.css('display', 'block');
		}
		state.options.onOpen.call(target, target);
	}
	
	function close(target) {
		var state = $j.data(target, 'dialog');
		var options = state.options;
		
		if (state.options.onClose.call(target, target) == false) return;
		
		switch(options.showType) {
			case null:
				state.dialog.css('display', 'none');
				break;
			case 'slide':
				state.dialog.slideUp(options.showSpeed);
				break;
			case 'fade':
				state.dialog.fadeOut(options.showSpeed);
				break;
			case 'show':
				state.dialog.hide(options.showSpeed);
				break;
				
		}
		
		if (state.mask) {
			state.mask.css('display', 'none');
		}
		if (state.shadow) {
			state.shadow.css('display', 'none');
		}
		
		// destroy the dialog window and remove it from the dom
		if (options.destroyOnClose == true) {
			var timeout = options.showSpeed;
			if (options.showType == null) {
				timeout = 0;
			}
			setTimeout(function(){
				state.dialog.remove();
				if (state.mask) {
					state.mask.remove();
				}
				if (state.shadow) {
					state.shadow.remove();
				}
			}, timeout);
		}
	}
	
	function resize(target) {
		var dialog = $j.data(target, 'dialog').dialog;
		var content = $j('div.dialog-content', dialog);
		var height = $j(dialog).height() - $j('div.dialog-header', dialog).outerHeight(true);
		height -= $j('div.dialog-button', dialog).outerHeight(true);
		if ($j.boxModel == true) {
			height -= content.outerHeight(true) - content.height();
		} else {
			height += content.outerHeight(true) - content.outerHeight();
		}
		$j('div.dialog-content', dialog).css('height', height);
		
		if ($j.boxModel == false) {
			var width = $j(dialog).width();
			$j('div.dialog-content', dialog).css('width', width);
			$j('div.dialog-button', dialog).css('padding', '5px 0px 5px 0px;');
		}
		
		var shadow = $j.data(target, 'dialog').shadow;
		if (shadow) {
			shadow.css({
				top: parseInt(dialog.css('top')),
				left: parseInt(dialog.css('left')) - 5,
				width: dialog.outerWidth() + 10,
				height: dialog.outerHeight() + 5
			});
			$j('.dialog-shadow-inner', shadow).shadow({hidden:false});
		}
	}
	
	// create and return the dialog
	function create(target, opts) {
		var header = [
		              '<div class="dialog-header">',
		              '<div class="dialog-title">&nbsp;</div>',
		              '<div class="dialog-icon">&nbsp;</div>',
		              '<a href="javascript:void(0)" class="dialog-close"></a>',
		              '</div>'
		              ];
		
		var dialog = $j('<div class="dialog"></div>').width(opts.width);
		$j(target).before(dialog);
		dialog.append(header.join('')).append($j(target).addClass('dialog-content'));
		$j('a.dialog-close',dialog).click(function(){
			close(target);
		});
		
		if (opts.buttons) {
			var buttons = $j('<div class="dialog-button"></div>');
			for(var label in opts.buttons) {
				$j('<a></a>').attr('href', 'javascript:void(0)').addClass('l-btn').text(label)
							.css('margin-right', '10px')
							.bind('click', eval(opts.buttons[label]))
							.appendTo(buttons);
			}
			$j(dialog).append(buttons);
			$j('a.l-btn', buttons).linkbutton();
		}
			
		return dialog;
	}
	
	// center the dialog
	function center(dialog) {
		$j(dialog).css({
			left: ($j(window).width() - $j(dialog).outerWidth(true)) / 2 + $j(document).scrollLeft(),
			top: ($j(window).height() - $j(dialog).outerHeight(true)) / 2 + $j(document).scrollTop()
		});
	}
	
	$j.fn.dialog = function(options){
		// method invoking
		if (typeof options == 'string') {
			switch(options) {
				case 'options':	// return the first element's options
					return $j.data(this[0], 'dialog').options;
			}
		}
		
		options = options || {};
		
		return this.each(function(){
			var opts = null;
			var dialog = null;
			var state = $j.data(this, 'dialog');
			if (state) {
				opts = $j.extend(state.options, options || {});
				dialog = state.dialog;
			} else {
				opts = $j.extend({}, $j.fn.dialog.defaults, options || {});
				dialog = create(this, opts);
				$j.data(this, 'dialog', {
					options: opts,
					dialog: dialog
				});
				
				// read the options information from the tag
				if (!options.width) {
					options.width = opts.width = parseInt($j(this).css('width')) || opts.width;
				}
				if (!options.height) {
					options.height = opts.height = parseInt($j(this).css('height'));
				}
				if (options.top == null || options.top == undefined) {
					options.top = opts.top = parseInt($j(this).css('top')) || $j.fn.dialog.defaults.top;
				}
				if (options.left == null || options.left == undefined) {
					options.left = opts.left = parseInt($j(this).css('left')) || $j.fn.dialog.defaults.left;
				}
				if (!options.title) {
					opts.title = $j(this).attr('title') || opts.title;
				}
				
				$j(this).css('width', null);
				$j(this).css('height', null);
				if (opts.width) dialog.width(opts.width);
				if (opts.height) dialog.height(opts.height);
				center(dialog);
			}
			
			// set dialog position, width and height
			if (options.width) dialog.width(options.width);
			if (options.height) dialog.height(options.height);
			if (options.left != undefined && options.left != null) dialog.css('left', options.left);
			if (options.top != undefined && options.top != null) dialog.css('top', options.top);
			
			// set dialog title
			$j('div.dialog-title', dialog).html(opts.title);	
			if (/^[u4E00-u9FA5]/.test(opts.title) == false && $j.browser.msie) {
				$j('div.dialog-title', dialog).css('padding-top', '8px');
			}
			
			if (opts.iconCls) {
				$j('.dialog-header .dialog-icon', dialog).addClass(opts.iconCls);
				$j('.dialog-header .dialog-title', dialog).css('padding-left', '20px');
			} else {
				$j('.dialog-header .dialog-icon', dialog).attr('class', 'dialog-icon');
				$j('.dialog-header .dialog-title', dialog).css('padding-left', '5px');
			}
			
			var target = this;
			$j(dialog).draggable({
				handle: 'div.dialog-header',
				disabled: opts.draggable == false,
				onDrag:function(){
					resize(target);
				}
			});
			
			$j(dialog).resizable({
				disabled: opts.resizable == false,
				onResize: function(){
					resize(target);
				}
			});
			
			if ($j.data(this, 'dialog').mask) {
				$j.data(this, 'dialog').mask.remove();
			}
			if (opts.modal == true) {
				$j.data(this, 'dialog').mask = $j('<div class="dialog-mask"></div>')
						.css({
							zIndex: $j.fn.dialog.defaults.zIndex++,
							width: getPageArea().width,
							height: getPageArea().height
						})
						.appendTo($j(document.body));
			}
			
			if ($j.data(this, 'dialog').shadow) {
				$j.data(this, 'dialog').shadow.remove();
			}
			if (opts.shadow == true) {
				var shadow = $j('<div class="dialog-shadow"><div class="dialog-shadow-inner"></div></div>');
				$j('.dialog-shadow-inner', shadow).shadow({width:5, fit:true, hidden:true});
				$j.data(this, 'dialog').shadow = shadow
						.css('z-index', $j.fn.dialog.defaults.zIndex++)
						.insertAfter(dialog);
			}
			
			dialog.css('z-index', $j.fn.dialog.defaults.zIndex++);
			
			
			// load the href content
			if (options.href) {
					$j(this).load(options.href, null, function(){
						resize(target);
						opts.onLoad.apply(this, arguments);	// trigger the onLoad event
					});
			}
			
			if (opts.closed == false) {
				if (dialog.css('display') == 'none') {
					open(this);
					resize(this);	// resize the dialog
				}
			} else {
				if(opts.shadow == true){ //if closed&&shadow, hidden the shadow, debug by ngj 20091210
					$j.data(this, 'dialog').shadow.remove();
				}
				if (dialog.css('display') == 'block') {
					close(this);
				}
			}
			
		});
		
	};
	
	// when window resize, reset the width and height of the dialog's mask
	$j(window).resize(function(){
		$j('.dialog-mask').css({
			width: $j(window).width(),
			height: $j(window).height()
		});
		setTimeout(function(){
			$j('.dialog-mask').css({
				width: getPageArea().width,
				height: getPageArea().height
			});
		}, 50);
	});
	
	function getPageArea() {
		if (document.compatMode == 'BackCompat') {
			return {
				width: Math.max(document.body.scrollWidth, document.body.clientWidth),
				height: Math.max(document.body.scrollHeight, document.body.clientHeight)
			}
		} else {
			return {
				width: Math.max(document.documentElement.scrollWidth, document.documentElement.clientWidth),
				height: Math.max(document.documentElement.scrollHeight, document.documentElement.clientHeight)
			}
		}
	}
	
	
	$j.fn.dialog.defaults = {
		zIndex: 9000,
		title: 'title',
		closed: false,
		destroyOnClose: false,
		draggable: true,
		resizable: true,
		modal: false,
		shadow: true,
		width: 300,
		height: null,
		showType: null,
		showSpeed: 600,
		left: null,
		top: null,
		iconCls: null,
		href:null,
		onOpen: function(){},
		onClose: function(){},
		onLoad:function(){}
	};
})(jQuery);