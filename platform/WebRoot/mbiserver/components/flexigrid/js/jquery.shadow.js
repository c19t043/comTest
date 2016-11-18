/**
 * shadow 1.0 - jQuery Plug-in
 * 
 * Licensed under the GPL:
 *   http://gplv3.fsf.org
 *
 * Copyright 2009 stworthy [ stworthy@gmail.com ] 
 * 
 * options:
 * 	hidden: boolean false to show the shadow and true to hide the shadow 
 * 	fit: boolean true to fit the parent container and false not
 * 	width: integer width The width in pixels of the shadow. Default: 8
 * 
 */
(function($j){
	$j.fn.shadow = function(options){
		
		return this.each(function(){
			
			// wrap the element and return the jQuery object
			function wrapElem(target) {
				var wraps = [
				             '<div class="shadow">',
				             '<div class="shadow-one">',
				             '<div class="shadow-corner-a"></div>',
				             '<div class="shadow-corner-b"></div>',
				             '<div class="shadow-two">',
				             '	<div class="shadow-three">',
				             '		<div class="shadow-four">',
				             '		</div>',
				             '	</div>',
				             '</div>',
				             '</div>',
				             '</div>'
				             ];
				
				var shadow = $j(wraps.join('')).insertAfter($j(target));
				$j(target).appendTo($j('.shadow-four', shadow));
				return shadow;
			}
			
			if ($j.data(this, 'shadow')) {
				$j.extend($j.data(this, 'shadow').options, options || {});
			} else {
				$j.data(this, 'shadow', {
					options: $j.extend({}, $j.fn.shadow.defaults, options || {}),
					shadow: wrapElem(this),
					oldWidth: $j(this).width(),	// the element old width and height
					oldHeight: $j(this).height()
				});
			}
			
			if (!$j.data(this, 'shadow').shadow) {
				$j.data(this, 'shadow').shadow = wrapElem(this);
			}
			
			var opts = $j.data(this, 'shadow').options;
			var shadow = $j.data(this, 'shadow').shadow;
			
			if (opts.hidden == true) {
				$j(this).insertAfter(shadow);
				shadow.remove();
				$j.data(this, 'shadow').shadow = null;
				return;
			}
			
			$j('.shadow-one', shadow).css({
				paddingLeft: opts.width * 2,
				paddingTop: opts.width * 2
			});
			$j('.shadow-corner-a', shadow).css({
				width: opts.width * 2,
				height: opts.width * 2
			});
			$j('.shadow-corner-b', shadow).css({
				width: opts.width * 2,
				height: opts.width * 2
			});
			$j('.shadow-three', shadow).css({
				left: opts.width * -2,
				top: opts.width * -2
			});
			$j('.shadow-four', shadow).css({
				left: opts.width,
				top: opts.width
			});
			
			if (opts.fit == true) {
				// make element and shadow fit the parent container
				
				var parent = $j(shadow).parent();	// the parent container
				
				if ($j.boxModel == true) {
					var delta = $j(this).outerWidth(true) - $j(this).width();
					$j(this).css({
						width: parent.width() - 2*opts.width - delta,
						height: parent.height() - 2*opts.width - delta
					});
					$j(shadow).css({
						width: parent.width(),
						height: parent.height()
					});
					$j('.shadow-one', shadow).css({
						width: parent.width() - 2*opts.width,
						height: parent.height() - 2*opts.width
					});
				
				} else {
					$j(this).css({
						width:'100%',
						height:'100%'
					});
					$j(shadow).css({
						width: parent.width(),
						height: parent.height()
					});
					$j('.shadow-one', shadow).css({
						width: parent.width(),
						height: parent.height()
					});
				}
			} else {
				// restore the element's width and height
				$j(this).width($j.data(this, 'shadow').oldWidth)
						.height($j.data(this, 'shadow').oldHeight);
				
				$j('.shadow-one', shadow).css({
					width:'100%',
					height:'100%'
				});
				
				if ($j.boxModel == true) {
					$j(shadow).css({
						width: $j(this).outerWidth(),
						height: $j(this).outerHeight()
					});
				} else {
					$j(shadow).css({
						width: $j.data(this, 'shadow').oldWidth + 2*opts.width,
						height: $j.data(this, 'shadow').oldHeight + 2*opts.width
					});
				}
			}
			
		});
	};
	
	$j.fn.shadow.defaults = {
			hidden: false,
			fit: false,
			width: 8
	};
})(jQuery);