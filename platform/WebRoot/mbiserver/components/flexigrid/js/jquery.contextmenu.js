/// <reference path="../intellisense/jquery-1.2.6-vsdoc-cn.js" />
/* --------------------------------------------------	
参数说明
option: {width:Number, items:Array, onShow:Function, rule:JSON}
成员语法(三种形式)	-- para.items
-> {text:String, icon:String, type:String, alias:String, width:Number, items:Array}		--	菜单组
-> {text:String, icon:String, type:String, alias:String, action:Function }				--	菜单项
-> {type:String}																		--	菜单分隔线
--------------------------------------------------*/
(function($j) {
    function returnfalse() { return false; };
    $j.fn.contextmenu = function(option) {
        option = $j.extend({ alias: "cmroot", width: 150 }, option);
        var ruleName = null, target = null,
	    groups = {}, mitems = {}, actions = {}, showGroups = [],
        itemTpl = "<div class='b-m-$j[type]' unselectable=on><nobr unselectable=on><img src='$j[icon]' align='absmiddle'/><span unselectable=on>$j[text]</span></nobr></div>";
        var gTemplet = $j("<div/>").addClass("b-m-mpanel").attr("unselectable", "on").css("display", "none");
        var iTemplet = $j("<div/>").addClass("b-m-item").attr("unselectable", "on");
        var sTemplet = $j("<div/>").addClass("b-m-split");
        //创建菜单组
        var buildGroup = function(obj) {
            groups[obj.alias] = this;
            this.gidx = obj.alias;
            this.id = obj.alias;
            if (obj.disable) {
                this.disable = obj.disable;
                this.className = "b-m-idisable";
            }
            $j(this).width(obj.width).click(returnfalse).mousedown(returnfalse).appendTo($j("body"));
            obj = null;
            return this;
        };
        var buildItem = function(obj) {
            var T = this;
            T.title = obj.text;
            T.idx = obj.alias;
            T.gidx = obj.gidx;
            T.data = obj;
            T.innerHTML = itemTpl.replace(/\$j\[([^\]]+)\]/g, function() {
                return obj[arguments[1]];
            });
            if (obj.disable) {
                T.disable = obj.disable;
                T.className = "b-m-idisable";
            }
            obj.items && (T.group = true);
            obj.action && (actions[obj.alias] = obj.action);
            mitems[obj.alias] = T;
            T = obj = null;
            return this;
        };
        //添加菜单项
        var addItems = function(gidx, items) {
            var tmp = null;
            for (var i = 0; i < items.length; i++) {
                if (items[i].type == "splitLine") {
                    //菜单分隔线
                    tmp = sTemplet.clone()[0];
                } else {
                    items[i].gidx = gidx;
                    if (items[i].type == "group") {
                        //菜单组
                        buildGroup.apply(gTemplet.clone()[0], [items[i]]);
                        arguments.callee(items[i].alias, items[i].items);
                        items[i].type = "arrow";
                        tmp = buildItem.apply(iTemplet.clone()[0], [items[i]]);
                    } else {
                        //菜单项
                        items[i].type = "ibody";
                        tmp = buildItem.apply(iTemplet.clone()[0], [items[i]]);
                        $j(tmp).click(function(e) {
                            if (!this.disable) {
                                if ($j.isFunction(actions[this.idx])) {
                                    actions[this.idx].call(this, target);
                                }
                                hideMenuPane();
                            }
                            return false;
                        });

                    } //Endif
                    $j(tmp).bind("contextmenu", returnfalse).hover(overItem, outItem);
                } //Endif
                groups[gidx].appendChild(tmp);
                tmp = items[i] = items[i].items = null;
            } //Endfor
            gidx = items = null;
        };
        var overItem = function(e) {
            //如果菜单项不可用          
            if (this.disable)
                return false;
            hideMenuPane.call(groups[this.gidx]);
            //如果是菜单组
            if (this.group) {
                var pos = $j(this).offset();
                var width = $j(this).outerWidth();
                showMenuGroup.apply(groups[this.idx], [pos, width]);
            }
            this.className = "b-m-ifocus";
            return false;
        };
        //菜单项失去焦点
        var outItem = function(e) {
            //如果菜单项不可用
            if (this.disable )
                return false;
            if (!this.group) {
                //菜单项
                this.className = "b-m-item";
            } //Endif
            return false;
        };
        //在指定位置显示指定的菜单组
        var showMenuGroup = function(pos, width) {
            var bwidth = $j("body").width();
            var bheight = document.documentElement.clientHeight;
            var mwidth = $j(this).outerWidth();
            var mheight = $j(this).outerHeight();
            pos.left = (pos.left + width + mwidth > bwidth) ? (pos.left - mwidth < 0 ? 0 : pos.left - mwidth) : pos.left + width;
            pos.top = (pos.top + mheight > bheight) ? (pos.top - mheight + (width > 0 ? 25 : 0) < 0 ? 0 : pos.top - mheight + (width > 0 ? 25 : 0)) : pos.top;
            $j(this).css(pos).show();
            showGroups.push(this.gidx);
        };
        //隐藏菜单组
        var hideMenuPane = function() {
            var alias = null;
            for (var i = showGroups.length - 1; i >= 0; i--) {
                if (showGroups[i] == this.gidx)
                    break;
                alias = showGroups.pop();
                groups[alias].style.display = "none";
                mitems[alias] && (mitems[alias].className = "b-m-item");
            } //Endfor
            //CollectGarbage();
        };
        function applyRule(rule) {
            if (ruleName && ruleName == rule.name)
                return false;
            for (var i in mitems)
                disable(i, !rule.disable);
            for (var i = 0; i < rule.items.length; i++)
                disable(rule.items[i], rule.disable);
            ruleName = rule.name;
        };
        function disable(alias, disabled) {
            var item = mitems[alias];
            item.className = (item.disable = item.lastChild.disabled = disabled) ? "b-m-idisable" : "b-m-item";
        };

        /** 右键菜单显示 */
        function showMenu(e, menutarget) {
            target = menutarget;
            showMenuGroup.call(groups.cmroot, { left: e.pageX, top: e.pageY }, 0);
            $j(document).one('mousedown', hideMenuPane);
        }
        var $jroot = $j("#" + option.alias);
        var root = null;
        if ($jroot.length == 0) {
            root = buildGroup.apply(gTemplet.clone()[0], [option]);
            root.applyrule = applyRule;
            root.showMenu = showMenu;
            addItems(option.alias, option.items);
        }
        else {
            root = $jroot[0];
        }
        var me = $j(this).each(function() {
            return $j(this).bind('contextmenu', function(e) {
                var bShowContext = (option.onContextMenu && $j.isFunction(option.onContextMenu)) ? option.onContextMenu.call(this, e) : true;
                if (bShowContext) {
                    if (option.onShow && $j.isFunction(option.onShow)) {
                        option.onShow.call(this, root);
                    }
                    root.showMenu(e, this);
                }
                return false;
            });
        });
        //设置显示规则
        if (option.rule) {
            applyRule(option.rule);
        }
        gTemplet = iTemplet = sTemplet = itemTpl = buildGroup = buildItem = null;
        addItems = overItem = outItem = null;
        //CollectGarbage();
        return me;
    }
})(jQuery);