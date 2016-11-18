/**
 * draggable 1.0 - jQuery Plug-in
 * 
 * Licensed under the GPL:
 *   http://gplv3.fsf.org
 *
 * Copyright 2009 stworthy [ stworthy@gmail.com ] 
 */
(function($j){
	$j.fn.draggable = function(options){
		function doDown(e){
			$j.data(e.data.target, 'draggable').options.onStartDrag(e);
			return false;
		}
		
		function doMove(e){
			var dragData = e.data;
			var left = dragData.startLeft + e.pageX - dragData.startX;
			var top = dragData.startTop + e.pageY - dragData.startY;
			
			if (e.data.parnet != document.body) {
				if ($j.boxModel == true) {
					left += $j(e.data.parent).scrollLeft();
					top += $j(e.data.parent).scrollTop();
				}
			}
			
			var opts = $j.data(e.data.target, 'draggable').options;
			if (opts.axis == 'h') {
				$j(dragData.target).css('left', left);
			} else if (opts.axis == 'v') {
				$j(dragData.target).css('top', top);
			} else {
				$j(dragData.target).css({
					left: left,
					top: top
				});
			}
			$j.data(e.data.target, 'draggable').options.onDrag(e);
			return false;
		}
		
		function doUp(e){
			$j(document).unbind('.draggable');
			$j.data(e.data.target, 'draggable').options.onStopDrag(e);
			return false;
		}
		
		
		return this.each(function(){
			$j(this).css('position','absolute');
			
			var opts;
			var state = $j.data(this, 'draggable');
			if (state) {
				state.handle.unbind('.draggable');
				opts = $j.extend(state.options, options);
			} else {
				opts = $j.extend({}, $j.fn.draggable.defaults, options || {});
			}
			
			if (opts.disabled == true) {
				$j(this).css('cursor', 'default');
				return;
			}
			
			var handle = null;
            if (typeof opts.handle == 'undefined' || opts.handle == null){
                handle = $j(this);
            } else {
                handle = (typeof opts.handle == 'string' ? $j(opts.handle, this) : handle);
            }
			$j.data(this, 'draggable', {
				options: opts,
				handle: handle
			});
			
			// bind mouse event using event namespace draggable
			handle.bind('mousedown.draggable', {target:this}, onMouseDown);
			handle.bind('mousemove.draggable', {target:this}, onMouseMove);
			
			function onMouseDown(e) {
				if (checkArea(e) == false) return;

				var position = $j(e.data.target).position();
				var data = {
					startLeft: position.left,
					startTop: position.top,
					startX: e.pageX,
					startY: e.pageY,
					target: e.data.target,
					parent: $j(e.data.target).parent()[0]
				};
				
				$j(document).bind('mousedown.draggable', data, doDown);
				$j(document).bind('mousemove.draggable', data, doMove);
				$j(document).bind('mouseup.draggable', data, doUp);
			}
			
			function onMouseMove(e) {
				if (checkArea(e)){
					$j(this).css('cursor', 'move');
				} else {
					$j(this).css('cursor', 'default');
				}
			}
			
			// check if the handle can be dragged
			function checkArea(e) {
				var offset = $j(handle).offset();
				var width = $j(handle).outerWidth();
				var height = $j(handle).outerHeight();
				var t = e.pageY - offset.top;
				var r = offset.left + width - e.pageX;
				var b = offset.top + height - e.pageY;
				var l = e.pageX - offset.left;
				
				return Math.min(t,r,b,l) > opts.edge;
			}
			
		});
	};
	
	$j.fn.draggable.defaults = {
			handle: null,
			disabled: false,
			edge:0,
			axis:null,	// v or h
			onStartDrag: function(){},
			onDrag: function(){},
			onStopDrag: function(){}
	};
})(jQuery);