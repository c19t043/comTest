/**
 * linkbutton 1.0 - jQuery Plug-in
 * 
 * Licensed under the GPL:
 *   http://gplv3.fsf.org
 *
 * Copyright 2009 stworthy [ stworthy@gmail.com ] 
 */
(function($j){
	$j.fn.linkbutton = function(options){
		
		// wrap the link button, make sure to execute once
		function wrapButton(target) {
			$j(target).addClass('l-btn')
					 .wrapInner('<span class="l-btn-left"><span class="l-btn-text"></span></span>');
			var iconCls = $j(target).attr('icon');
			if (iconCls) {
				$j('.l-btn-text', target).addClass(iconCls).css('padding-left', '20px');
			}
		}
		
		return this.each(function(){
			var opts;
			var state = $j.data(this, 'linkbutton');
			if (state) {
				opts = $j.extend(state.options, options || {});
				state.options = opts;
			} else {
				wrapButton(this);
				opts = $j.extend({}, $j.fn.linkbutton.defaults, options || {});
				
				if ($j(this).hasClass('l-btn-plain')) {
					opts.plain = true;
				}
				
				// the button initialize state is disabled
				if ($j(this).attr('disabled')) {
					opts.disabled = true;
					$j(this).removeAttr('disabled');
				}
				
				state = {options: opts};
				
			}
			
			if (state.options.disabled) {
				var href = $j(this).attr('href');
				if (href) {
					state.href = href;
					$j(this).removeAttr('href');
				}
				var onclick = $j(this).attr('onclick');
				if (onclick) {
					state.onclick = onclick;
					$j(this).attr('onclick', null);
				}
				$j(this).addClass('l-btn-disabled');
			} else {
				if (state.href) {
					$j(this).attr('href', state.href);
				}
				if (state.onclick) {
					this.onclick = state.onclick;
				}
				$j(this).removeClass('l-btn-disabled');
			}
			
			if (state.options.plain == true) {
				$j(this).addClass('l-btn-plain');
			} else {
				$j(this).removeClass('l-btn-plain');
			}
			
			$j.data(this, 'linkbutton', state);	// save the button state
		});
	};
	
	$j.fn.linkbutton.defaults = {
			disabled: false,
			plain: false
	};
	
	$j(function(){
		$j('a.l-btn').linkbutton();
	});
})(jQuery);
