/**
 * resizable 1.0 - jQuery Plug-in
 * 
 * Licensed under the GPL:
 *   http://gplv3.fsf.org
 *
 * Copyright 2009 stworthy [ stworthy@gmail.com ] 
 */
(function($j){
	$j.fn.resizable = function(options){
		function doDown(e){
			$j.data(e.data.target, 'resizable').options.onStartResize(e);
			return false;
		}
		
		function doMove(e){
			var resizeData = e.data;
			var options = $j.data(resizeData.target, 'resizable').options;
			var target = resizeData.target;
			if (resizeData.dir.indexOf('e') != -1) {
				var width = resizeData.startWidth + e.pageX - resizeData.startX;
				if ($j.boxModel == false) {
					width += resizeData.deltaWidth;
				}
				width = Math.min(
							Math.max(width, options.minWidth),
							options.maxWidth
						);
				$j(target).css('width', width + 'px');
			}
			if (resizeData.dir.indexOf('s') != -1) {
				var height = resizeData.startHeight + e.pageY - resizeData.startY;
				if ($j.boxModel == false) {
					height += resizeData.deltaHeight;
				}
				height = Math.min(
							Math.max(height, options.minHeight),
							options.maxHeight
						);
				$j(target).css('height', height + 'px');
			}
			if (resizeData.dir.indexOf('w') != -1) {
				var width = resizeData.startWidth - e.pageX + resizeData.startX;
				if ($j.boxModel == false) {
					width += resizeData.deltaWidth;
				}
				if (width >= options.minWidth && width <= options.maxWidth) {
					var left = resizeData.startLeft + e.pageX - resizeData.startX;
					$j(target).css({
						left: left + 'px',
						width: width + 'px'
					});
				}
			}
			if (resizeData.dir.indexOf('n') != -1) {
				var height = resizeData.startHeight - e.pageY + resizeData.startY;
				if ($j.boxModel == false) {
					height += resizeData.deltaHeight;
				}
				if (height >= options.minHeight && height <= options.maxHeight) {
					var top = resizeData.startTop + e.pageY - resizeData.startY;
					$j(target).css({
						top: top + 'px',
						height: height + 'px'
					});
				}
			}
			$j.data(e.data.target, 'resizable').options.onResize(e);
			return false;
		}
		
		function doUp(e){
			$j(document).unbind('.resizable');
			$j.data(e.data.target, 'resizable').options.onStopResize(e);
			return false;
		}
		
		return this.each(function(){
			var opts = null;
			var state = $j.data(this, 'resizable');
			if (state) {
				$j(this).unbind('.resizable');
				opts = $j.extend(state.options, options || {});
			} else {
				opts = $j.extend({}, $j.fn.resizable.defaults, options || {});
			}
			
			if (opts.disabled == true) {
				return;
			}
			
			$j.data(this, 'resizable', {
				options: opts
			});
			
			var target = this;
			
			// bind mouse event using namespace resizable
			$j(this).bind('mousemove.resizable', onMouseMove)
				   .bind('mousedown.resizable', onMouseDown);
			
			function onMouseMove(e) {
				var dir = getDirection(e);
				if (dir == '') {
					$j(target).css('cursor', 'default');
				} else {
					$j(target).css('cursor', dir + '-resize');
				}
			}
			
			function onMouseDown(e) {
				var dir = getDirection(e);
				if (dir == '') return;
				
				var data = {
					target: this,
					dir: dir,
					startLeft: getCssValue('left'),
					startTop: getCssValue('top'),
					startX: e.pageX,
					startY: e.pageY,
					startWidth: $j(target).width(),
					startHeight: $j(target).height(),
					deltaWidth: $j(target).outerWidth() - $j(target).width(),
					deltaHeight: $j(target).outerHeight() - $j(target).height()
				};
				$j(document).bind('mousedown.resizable', data, doDown);
				$j(document).bind('mousemove.resizable', data, doMove);
				$j(document).bind('mouseup.resizable', data, doUp);
			}
			
			// get the resize direction
			function getDirection(e) {
				var dir = '';
				var offset = $j(target).offset();
				var width = $j(target).outerWidth();
				var height = $j(target).outerHeight();
				var edge = opts.edge;
				if (e.pageY > offset.top && e.pageY < offset.top + edge) {
					dir += 'n';
				} else if (e.pageY < offset.top + height && e.pageY > offset.top + height - edge) {
					dir += 's';
				}
				if (e.pageX > offset.left && e.pageX < offset.left + edge) {
					dir += 'w';
				} else if (e.pageX < offset.left + width && e.pageX > offset.left + width - edge) {
					dir += 'e';
				}
				return dir;
			}
			
			function getCssValue(css) {
				var val = parseInt($j(target).css(css));
				if (isNaN(val)) {
					return 0;
				} else {
					return val;
				}
			}
			
		});
	};
	
	$j.fn.resizable.defaults = {
			disabled:false,
			minWidth: 10,
			minHeight: 10,
			maxWidth: 10000,//$j(document).width(),
			maxHeight: 10000,//$j(document).height(),
			edge:5,
			onStartResize: function(){},
			onResize: function(){},
			onStopResize: function(){}
	};
	
})(jQuery);