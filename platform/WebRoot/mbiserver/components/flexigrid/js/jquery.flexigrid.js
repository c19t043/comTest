/// <reference path="../intellisense/jquery-1.2.6-vsdoc-cn.js" />
/// <reference path="../lib/blackbird.js" />
(function($j) {
    $j.addFlex = function(t, p) {
        if (t.grid) return false; //如果Grid已经存在则返回
        // 引用默认属性
        p = $j.extend({
            height: 200, //flexigrid插件的高度，单位为px
            width: 'auto', //宽度值，auto表示根据每列的宽度自动计算
            striped: true, //是否显示斑纹效果，默认是奇偶交互的形式
            novstripe: false,
            minwidth: 30, //列的最小宽度
            minheight: 80, //列的最小高度
            resizable: false, //resizable table是否可伸缩
            url: false, //ajax url,ajax方式对应的url地址
            method: 'POST', // data sending method,数据发送方式
            dataType: 'json', // type of data loaded,数据加载的类型，xml,json
            errormsg: '发生错误', //错误提升信息
            usepager: false, //是否分页
            nowrap: true, //是否不换行
            page: 1, //current page,默认当前页
            total: 1, //total pages,总页面数
            useRp: true, //use the results per page select box,是否可以动态设置每页显示的结果数
            rp: 25, // results per page,每页默认的结果数
            rpOptions: [10, 15, 20, 25, 40, 100], //可选择设定的每页结果数
            title: false, //是否包含标题
            pagestat: '显示记录从{from}到{to}，总数 {total} 条', //显示当前页和总页面的样式
            procmsg: '正在处理数据，请稍候 ...', //正在处理的提示信息
            query: '', //搜索查询的条件
            qtype: '', //搜索查询的类别
            qop: "Eq", //搜索的操作符
            nomsg: '没有符合条件的记录存在', //无结果的提示信息
            minColToggle: 1, //minimum allowed column to be hidden
            showToggleBtn: true, //show or hide column toggle popup
            hideOnSubmit: true, //显示遮盖
            showTableToggleBtn: false, //显示隐藏Grid 
            autoload: true, //自动加载
            blockOpacity: 0.5, //透明度设置
            onToggleCol: false, //当在行之间转换时
            onChangeSort: false, //当改变排序时
            onSuccess: false, //成功后执行
            onSubmit: false, // using a custom populate function,调用自定义的计算函数
            showcheckbox: false, //是否显示第一列的checkbox（用于全选）
            rowhandler: false, //是否启用行的扩展事情功能,在生成行时绑定事件，如双击，右键等
            rowbinddata: false,//配合上一个操作，如在双击事件中获取该行的数据
            extParam: {},//添加extParam参数可将外部参数动态注册到grid，实现如查询等操作
            //Style
            gridClass: "bbit-grid",
            onrowchecked: false//在每一行的的checkbox选中状态发生变化时触发某个事件
        }, p);

        $j(t)
		.show() //show if hidden
		.attr({ cellPadding: 0, cellSpacing: 0, border: 0 })  //remove padding and spacing
		.removeAttr('width') //remove width properties	
		;

        //create grid class
        var g = {
            hset: {},
            rePosDrag: function() {

                var cdleft = 0 - this.hDiv.scrollLeft;
                if (this.hDiv.scrollLeft > 0) cdleft -= Math.floor(p.cgwidth / 2);

                $j(g.cDrag).css({ top: g.hDiv.offsetTop + 1 });
                var cdpad = this.cdpad;

                $j('div', g.cDrag).hide();
                //update by xuanye ,避免jQuery :visible 无效的bug
                var i = 0;
                $j('thead tr:first th:visible', this.hDiv).each(function() {
                    if ($j(this).css("display") == "none") {
                        return;
                    }
                    var n = i;
                    //var n = $j('thead tr:first th:visible', g.hDiv).index(this);			 	  
                    var cdpos = parseInt($j('div', this).width());
                    var ppos = cdpos;
                    if (cdleft == 0)
                        cdleft -= Math.floor(p.cgwidth / 2);

                    cdpos = cdpos + cdleft + cdpad;

                    $j('div:eq(' + n + ')', g.cDrag).css({ 'left': cdpos + 'px' }).show();

                    cdleft = cdpos;
                    i++;
                }
				);

            },
            fixHeight: function(newH) {
                newH = false;
                if (!newH) newH = $j(g.bDiv).height();
                var hdHeight = $j(this.hDiv).height();
                $j('div', this.cDrag).each(
						function() {
						    $j(this).height(newH + hdHeight);
						}
					);

                var nd = parseInt($j(g.nDiv).height());

                if (nd > newH)
                    $j(g.nDiv).height(newH).width(200);
                else
                    $j(g.nDiv).height('auto').width('auto');

                $j(g.block).css({ height: newH, marginBottom: (newH * -1) });

                var hrH = g.bDiv.offsetTop + newH;
                if (p.height != 'auto' && p.resizable) hrH = g.vDiv.offsetTop;
                $j(g.rDiv).css({ height: hrH });

            },
            dragStart: function(dragtype, e, obj) { //default drag function start

                if (dragtype == 'colresize') //column resize
                {
                    $j(g.nDiv).hide(); $j(g.nBtn).hide();
                    var n = $j('div', this.cDrag).index(obj);
                    //var ow = $j('th:visible div:eq(' + n + ')', this.hDiv).width();
                    var ow = $j('th:visible:eq(' + n + ') div', this.hDiv).width();
                    $j(obj).addClass('dragging').siblings().hide();
                    $j(obj).prev().addClass('dragging').show();

                    this.colresize = { startX: e.pageX, ol: parseInt(obj.style.left), ow: ow, n: n };
                    $j('body').css('cursor', 'col-resize');
                }
                else if (dragtype == 'vresize') //table resize
                {
                    var hgo = false;
                    $j('body').css('cursor', 'row-resize');
                    if (obj) {
                        hgo = true;
                        $j('body').css('cursor', 'col-resize');
                    }
                    this.vresize = { h: p.height, sy: e.pageY, w: p.width, sx: e.pageX, hgo: hgo };

                }

                else if (dragtype == 'colMove') //column header drag
                {
                    $j(g.nDiv).hide(); $j(g.nBtn).hide();
                    this.hset = $j(this.hDiv).offset();
                    this.hset.right = this.hset.left + $j('table', this.hDiv).width();
                    this.hset.bottom = this.hset.top + $j('table', this.hDiv).height();
                    this.dcol = obj;
                    this.dcoln = $j('th', this.hDiv).index(obj);

                    this.colCopy = document.createElement("div");
                    this.colCopy.className = "colCopy";
                    this.colCopy.innerHTML = obj.innerHTML;
                    if ($j.browser.msie) {
                        this.colCopy.className = "colCopy ie";
                    }


                    $j(this.colCopy).css({ position: 'absolute', float: 'left', display: 'none', textAlign: obj.align });
                    $j('body').append(this.colCopy);
                    $j(this.cDrag).hide();

                }

                $j('body').noSelect();

            },
            reSize: function() {
                this.gDiv.style.width = p.width;
                this.bDiv.style.height = p.height;
            },
            dragMove: function(e) {

                if (this.colresize) //column resize
                {
                    var n = this.colresize.n;
                    var diff = e.pageX - this.colresize.startX;
                    var nleft = this.colresize.ol + diff;
                    var nw = this.colresize.ow + diff;
                    if (nw > p.minwidth) {
                        $j('div:eq(' + n + ')', this.cDrag).css('left', nleft);
                        this.colresize.nw = nw;
                    }
                }
                else if (this.vresize) //table resize
                {
                    var v = this.vresize;
                    var y = e.pageY;
                    var diff = y - v.sy;
                    if (!p.defwidth) p.defwidth = p.width;
                    if (p.width != 'auto' && !p.nohresize && v.hgo) {
                        var x = e.pageX;
                        var xdiff = x - v.sx;
                        var newW = v.w + xdiff;
                        if (newW > p.defwidth) {
                            this.gDiv.style.width = newW + 'px';
                            p.width = newW;
                        }
                    }
                    var newH = v.h + diff;
                    if ((newH > p.minheight || p.height < p.minheight) && !v.hgo) {
                        this.bDiv.style.height = newH + 'px';
                        p.height = newH;
                        this.fixHeight(newH);
                    }
                    v = null;
                }
                else if (this.colCopy) {
                    $j(this.dcol).addClass('thMove').removeClass('thOver');
                    if (e.pageX > this.hset.right || e.pageX < this.hset.left || e.pageY > this.hset.bottom || e.pageY < this.hset.top) {
                        //this.dragEnd();
                        $j('body').css('cursor', 'move');
                    }
                    else
                        $j('body').css('cursor', 'pointer');

                    $j(this.colCopy).css({ top: e.pageY + 10, left: e.pageX + 20, display: 'block' });
                }

            },
            dragEnd: function() {
                if (this.colresize) {
                    var n = this.colresize.n;
                    var nw = this.colresize.nw;
                    //$j('th:visible div:eq(' + n + ')', this.hDiv).css('width', nw);
                    $j('th:visible:eq(' + n + ') div', this.hDiv).css('width', nw);

                    $j('tr', this.bDiv).each(
									function() {
									    //$j('td:visible div:eq(' + n + ')', this).css('width', nw);
									    $j('td:visible:eq(' + n + ') div', this).css('width', nw);
									}
								);
                    this.hDiv.scrollLeft = this.bDiv.scrollLeft;
                    $j('div:eq(' + n + ')', this.cDrag).siblings().show();
                    $j('.dragging', this.cDrag).removeClass('dragging');
                    this.rePosDrag();
                    this.fixHeight();
                    this.colresize = false;
                }
                else if (this.vresize) {
                    this.vresize = false;
                }
                else if (this.colCopy) {
                    $j(this.colCopy).remove();
                    if (this.dcolt != null) {
                        if (this.dcoln > this.dcolt)
                        { $j('th:eq(' + this.dcolt + ')', this.hDiv).before(this.dcol); }
                        else
                        { $j('th:eq(' + this.dcolt + ')', this.hDiv).after(this.dcol); }
                        this.switchCol(this.dcoln, this.dcolt);
                        $j(this.cdropleft).remove();
                        $j(this.cdropright).remove();
                        this.rePosDrag();
                    }
                    this.dcol = null;
                    this.hset = null;
                    this.dcoln = null;
                    this.dcolt = null;
                    this.colCopy = null;
                    $j('.thMove', this.hDiv).removeClass('thMove');
                    $j(this.cDrag).show();
                }
                $j('body').css('cursor', 'default');
                $j('body').noSelect(false);
            },
            toggleCol: function(cid, visible) {
                var ncol = $j("th[axis='col" + cid + "']", this.hDiv)[0];
                var n = $j('thead th', g.hDiv).index(ncol);
                var cb = $j('input[value=' + cid + ']', g.nDiv)[0];
                if (visible == null) {
                    visible = ncol.hide;
                }
                if ($j('input:checked', g.nDiv).length < p.minColToggle && !visible) return false;
                if (visible) {
                    ncol.hide = false;
                    $j(ncol).show();
                    cb.checked = true;
                }
                else {
                    ncol.hide = true;
                    $j(ncol).hide();
                    cb.checked = false;
                }
                $j('tbody tr', t).each
							(
								function() {
								    if (visible)
								        $j('td:eq(' + n + ')', this).show();
								    else
								        $j('td:eq(' + n + ')', this).hide();
								}
							);
                this.rePosDrag();
                if (p.onToggleCol) p.onToggleCol(cid, visible);
                return visible;
            },
            switchCol: function(cdrag, cdrop) { //switch columns
                $j('tbody tr', t).each
					(
						function() {
						    if (cdrag > cdrop)
						        $j('td:eq(' + cdrop + ')', this).before($j('td:eq(' + cdrag + ')', this));
						    else
						        $j('td:eq(' + cdrop + ')', this).after($j('td:eq(' + cdrag + ')', this));
						}
					);
                //switch order in nDiv
                if (cdrag > cdrop)
                    $j('tr:eq(' + cdrop + ')', this.nDiv).before($j('tr:eq(' + cdrag + ')', this.nDiv));
                else
                    $j('tr:eq(' + cdrop + ')', this.nDiv).after($j('tr:eq(' + cdrag + ')', this.nDiv));
                if ($j.browser.msie && $j.browser.version < 7.0) $j('tr:eq(' + cdrop + ') input', this.nDiv)[0].checked = true;
                this.hDiv.scrollLeft = this.bDiv.scrollLeft;
            },
            scroll: function() {
                this.hDiv.scrollLeft = this.bDiv.scrollLeft;
                this.rePosDrag();
            },
            hideLoading: function() {
                $j('.pReload', this.pDiv).removeClass('loading');
                if (p.hideOnSubmit) $j(g.block).remove();
                $j('.pPageStat', this.pDiv).html(p.errormsg);
                this.loading = false;
            }
            ,
            addData: function(data) { //parse data                
                if (p.preProcess)
                { data = p.preProcess(data); }
                $j('.pReload', this.pDiv).removeClass('loading');
                this.loading = false;

                if (!data) {
                    $j('.pPageStat', this.pDiv).html(p.errormsg);
                    return false;
                }
                var temp = p.total;
                if (p.dataType == 'xml') {
                    p.total = +$j('rows total', data).text();
                }
                else {
                    p.total = data.total;
                }
                if (p.total < 0) {
                    p.total = temp;
                }
                if (p.total == 0) {
                    $j('tr, a, td, div', t).unbind();
                    $j(t).empty();
                    p.pages = 1;
                    p.page = 1;
                    this.buildpager();
                    $j('.pPageStat', this.pDiv).html(p.nomsg);
                    if (p.hideOnSubmit) $j(g.block).remove();
                    return false;
                }

                p.pages = Math.ceil(p.total / p.rp);

                if (p.dataType == 'xml')
                { p.page = +$j('rows page', data).text(); }
                else
                { p.page = data.page; }
                this.buildpager();



                var ths = $j('thead tr:first th', g.hDiv);
                var thsdivs = $j('thead tr:first th div', g.hDiv);
                var tbhtml = [];
                tbhtml.push("<tbody>");
                if (p.dataType == 'json') {
                    if (data.rows != null) {
                        $j.each(data.rows, function(i, row) {
                            tbhtml.push("<tr id='", "row", row.id, "'");

                            if (i % 2 && p.striped) {
                                tbhtml.push(" class='erow'");
                            }
                            if (p.rowbinddata) {
                                tbhtml.push("ch='", row.cell.join("_FG$jSP_"), "'");
                            }
                            tbhtml.push(">");
                            var trid = row.id;
                            $j(ths).each(function(j) {
                                var tddata = "";
                                var tdclass = "";
                                tbhtml.push("<td align='", this.align, "'");
                                var idx = $j(this).attr('axis').substr(3);

                                if (p.sortname && p.sortname == $j(this).attr('abbr')) {
                                    tdclass = 'sorted';
                                }
                                if (this.hide) {
                                    tbhtml.push(" style='display:none;'");
                                }
                                var width = thsdivs[j].style.width;
                                var div = [];
                                div.push("<div style='text-align:", this.align, ";width:", width, ";");
                                if (p.nowrap == false) {
                                    div.push("white-space:normal");
                                }
                                div.push("'>");
                                if (idx == "-1") { //checkbox
                                    div.push("<input type='checkbox' id='chk_", row.id, "' class='itemchk' value='", row.id, "'/>");
                                    if (tdclass != "") {
                                        tdclass += " chboxtd";
                                    } else {
                                        tdclass += "chboxtd";
                                    }
                                }
                                else {
                                    var divInner = row.cell[idx] || "&nbsp;";
                                    if (this.process) {
                                        divInner = this.process(divInner, trid);
                                    }
                                    div.push(divInner);
                                }
                                div.push("</div>");
                                if (tdclass != "") {
                                    tbhtml.push(" class='", tdclass, "'");
                                }
                                tbhtml.push(">", div.join(""), "</td>");
                            });
                            tbhtml.push("</tr>");
                        }
					    );
                    }

                } else if (p.dataType == 'xml') {
                    i = 1;
                    $j("rows row", data).each
				(
				 	function() {
				 	    i++;
				 	    var robj = this;
				 	    var arrdata = new Array();
				 	    $j("cell", robj).each(function() {
				 	        arrdata.push($j(this).text());
				 	    });
				 	    var nid = $j(this).attr('id');
				 	    tbhtml.push("<tr id='", "row", nid, "'");
				 	    if (i % 2 && p.striped) {
				 	        tbhtml.push(" class='erow'");
				 	    }
				 	    if (p.rowbinddata) {
				 	        tbhtml.push("ch='", arrdata.join("_FG$jSP_"), "'");
				 	    }
				 	    tbhtml.push(">");
				 	    var trid = nid;
				 	    $j(ths).each(function(j) {
				 	        tbhtml.push("<td align='", this.align, "'");
				 	        if (this.hide) {
				 	            tbhtml.push(" style='display:none;'");
				 	        }
				 	        var tdclass = "";
				 	        var tddata = "";
				 	        var idx = $j(this).attr('axis').substr(3);

				 	        if (p.sortname && p.sortname == $j(this).attr('abbr')) {
				 	            tdclass = 'sorted';
				 	        }
				 	        var width = thsdivs[j].style.width;

				 	        var div = [];
				 	        div.push("<div style='text-align:", this.align, ";width:", width, ";");
				 	        if (p.nowrap == false) {
				 	            div.push("white-space:normal");
				 	        }
				 	        div.push("'>");

				 	        if (idx == "-1") { //checkbox
				 	            div.push("<input type='checkbox' id='chk_", nid, "' class='itemchk' value='", nid, "'/>");
				 	            if (tdclass != "") {
				 	                tdclass += " chboxtd";
				 	            } else {
				 	                tdclass += "chboxtd";
				 	            }
				 	        }
				 	        else {
				 	            var divInner = arrdata[idx] || "&nbsp;";
				 	            if (p.rowbinddata) {
				 	                tddata = arrdata[idx] || "";
				 	            }
				 	            if (this.process) {
				 	                divInner = this.process(divInner, trid);
				 	            }
				 	            div.push(divInner);
				 	        }
				 	        div.push("</div>");
				 	        if (tdclass != "") {
				 	            tbhtml.push(" class='", tdclass, "'");
				 	        }
				 	        tbhtml.push(" axis='", tddata, "'", ">", div.join(""), "</td>");
				 	    });
				 	    tbhtml.push("</tr>");
				 	}
				);

                }
                tbhtml.push("</tbody>");
                $j(t).html(tbhtml.join(""));

                //this.rePosDrag();
                this.addRowProp();
                if (p.onSuccess) p.onSuccess();
                if (p.hideOnSubmit) $j(g.block).remove(); //$j(t).show();
                this.hDiv.scrollLeft = this.bDiv.scrollLeft;
                if ($j.browser.opera) $j(t).css('visibility', 'visible');

            },
            changeSort: function(th) { //change sortorder

                if (this.loading) return true;

                $j(g.nDiv).hide(); $j(g.nBtn).hide();

                if (p.sortname == $j(th).attr('abbr')) {
                    if (p.sortorder == 'asc') p.sortorder = 'desc';
                    else p.sortorder = 'asc';
                }

                $j(th).addClass('sorted').siblings().removeClass('sorted');
                $j('.sdesc', this.hDiv).removeClass('sdesc');
                $j('.sasc', this.hDiv).removeClass('sasc');
                $j('div', th).addClass('s' + p.sortorder);
                p.sortname = $j(th).attr('abbr');

                if (p.onChangeSort)
                    p.onChangeSort(p.sortname, p.sortorder);
                else
                    this.populate();

            },
            buildpager: function() { //rebuild pager based on new properties

                $j('.pcontrol input', this.pDiv).val(p.page);
                $j('.pcontrol span', this.pDiv).html(p.pages);

                var r1 = (p.page - 1) * p.rp + 1;
                var r2 = r1 + p.rp - 1;

                if (p.total < r2) r2 = p.total;

                var stat = p.pagestat;

                stat = stat.replace(/{from}/, r1);
                stat = stat.replace(/{to}/, r2);
                stat = stat.replace(/{total}/, p.total);
                $j('.pPageStat', this.pDiv).html(stat);
            },
            populate: function() { //get latest data 
                //log.trace("开始访问数据源");
                if (this.loading) return true;
                if (p.onSubmit) {
                    var gh = p.onSubmit();
                    if (!gh) return false;
                }
                this.loading = true;
                if (!p.url) return false;
                $j('.pPageStat', this.pDiv).html(p.procmsg);
                $j('.pReload', this.pDiv).addClass('loading');
                $j(g.block).css({ top: g.bDiv.offsetTop });
                if (p.hideOnSubmit) $j(this.gDiv).prepend(g.block); //$j(t).hide();
                if ($j.browser.opera) $j(t).css('visibility', 'hidden');
                if (!p.newp) p.newp = 1;
                if (p.page > p.pages) p.page = p.pages;
                //var param = {page:p.newp, rp: p.rp, sortname: p.sortname, sortorder: p.sortorder, query: p.query, qtype: p.qtype};
                var param = [
					 { name: 'page', value: p.newp }
					, { name: 'rp', value: p.rp }
					, { name: 'sortname', value: p.sortname }
					, { name: 'sortorder', value: p.sortorder }
					, { name: 'query', value: p.query }
					, { name: 'qtype', value: p.qtype }
					, { name: 'qop', value: p.qop }
				];
                //param = jQuery.extend(param, p.extParam);
                if (p.extParam) {
                    for (var pi = 0; pi < p.extParam.length; pi++) param[param.length] = p.extParam[pi];
                }


                $j.ajax({
                    type: p.method,
                    url: p.url,
                    data: param,
                    dataType: p.dataType,
                    success: function(data) { if (data != null && data.error != null) { if (p.onError) { p.onError(data); g.hideLoading(); } } else { g.addData(data); } },
                    error: function(data) { try { if (p.onError) { p.onError(data); } else { alert("获取数据发生异常;") } g.hideLoading(); } catch (e) { } }
                });
            },
            doSearch: function() {
                var queryType = $j('select[name=qtype]', g.sDiv).val();
                var qArrType = queryType.split("$j");
                var index = -1;
                if (qArrType.length != 3) {
                    p.qop = "Eq";
                    p.qtype = queryType;
                }
                else {
                    p.qop = qArrType[1];
                    p.qtype = qArrType[0];
                    index = parseInt(qArrType[2]);
                }
                p.query = $j('input[name=q]', g.sDiv).val();
                //添加验证代码
                if (p.query != "" && p.searchitems && index >= 0 && p.searchitems.length > index) {
                    if (p.searchitems[index].reg) {
                        if (!p.searchitems[index].reg.test(p.query)) {
                            alert("你的输入不符合要求!");
                            return;
                        }
                    }
                }
                p.newp = 1;
                this.populate();
            },
            changePage: function(ctype) { //change page

                if (this.loading) return true;

                switch (ctype) {
                    case 'first': p.newp = 1; break;
                    case 'prev': if (p.page > 1) p.newp = parseInt(p.page) - 1; break;
                    case 'next': if (p.page < p.pages) p.newp = parseInt(p.page) + 1; break;
                    case 'last': p.newp = p.pages; break;
                    case 'input':
                        var nv = parseInt($j('.pcontrol input', this.pDiv).val());
                        if (isNaN(nv)) nv = 1;
                        if (nv < 1) nv = 1;
                        else if (nv > p.pages) nv = p.pages;
                        $j('.pcontrol input', this.pDiv).val(nv);
                        p.newp = nv;
                        break;
                }

                if (p.newp == p.page) return false;

                if (p.onChangePage)
                    p.onChangePage(p.newp);
                else
                    this.populate();

            },
            cellProp: function(n, ptr, pth) {
                var tdDiv = document.createElement('div');
                if (pth != null) {
                    if (p.sortname == $j(pth).attr('abbr') && p.sortname) {
                        this.className = 'sorted';
                    }
                    $j(tdDiv).css({ textAlign: pth.align, width: $j('div:first', pth)[0].style.width });
                    if (pth.hide) $j(this).css('display', 'none');
                }
                if (p.nowrap == false) $j(tdDiv).css('white-space', 'normal');

                if (this.innerHTML == '') this.innerHTML = '&nbsp;';

                //tdDiv.value = this.innerHTML; //store preprocess value
                tdDiv.innerHTML = this.innerHTML;

                var prnt = $j(this).parent()[0];
                var pid = false;
                if (prnt.id) pid = prnt.id.substr(3);
                if (pth != null) {
                    if (pth.process)
                    { pth.process(tdDiv, pid); }
                }
                $j("input.itemchk", tdDiv).each(function() {
                    $j(this).click(function() {
                        if (this.checked) {
                            $j(ptr).addClass("trSelected");
                        }
                        else {
                            $j(ptr).removeClass("trSelected");
                        }
                        if (p.onrowchecked) {
                            p.onrowchecked.call(this);
                        }
                    });
                });
                $j(this).empty().append(tdDiv).removeAttr('width'); //wrap content
                //add editable event here 'dblclick',如果需要可编辑在这里添加可编辑代码 
            },
            addCellProp: function() {
                var $jgF = this.cellProp;

                $j('tbody tr td', g.bDiv).each
					(
						function() {
						    var n = $j('td', $j(this).parent()).index(this);
						    var pth = $j('th:eq(' + n + ')', g.hDiv).get(0);
						    var ptr = $j(this).parent();
						    $jgF.call(this, n, ptr, pth);
						}
					);
                $jgF = null;
            },
            getCheckedRows: function() {
                var ids = [];
                $j(":checkbox:checked", g.bDiv).each(function() {
                    ids.push($j(this).val());
                });
                return ids;
            },
            getCellDim: function(obj) // get cell prop for editable event
            {
                var ht = parseInt($j(obj).height());
                var pht = parseInt($j(obj).parent().height());
                var wt = parseInt(obj.style.width);
                var pwt = parseInt($j(obj).parent().width());
                var top = obj.offsetParent.offsetTop;
                var left = obj.offsetParent.offsetLeft;
                var pdl = parseInt($j(obj).css('paddingLeft'));
                var pdt = parseInt($j(obj).css('paddingTop'));
                return { ht: ht, wt: wt, top: top, left: left, pdl: pdl, pdt: pdt, pht: pht, pwt: pwt };
            },
            rowProp: function() {
                if (p.rowhandler) {
                    p.rowhandler(this);
                }
                if ($j.browser.msie && $j.browser.version < 7.0) {
                    $j(this).hover(function() { $j(this).addClass('trOver'); }, function() { $j(this).removeClass('trOver'); });
                }
            },
            addRowProp: function() {
                var $jgF = this.rowProp;
                $j('tbody tr', g.bDiv).each(
                    function() {
                        $j("input.itemchk", this).each(function() {
                            var ptr = $j(this).parent().parent().parent();
                            $j(this).click(function() {
                                if (this.checked) {
                                    ptr.addClass("trSelected");
                                }
                                else {
                                    ptr.removeClass("trSelected");
                                }
                                if (p.onrowchecked) {
                                    p.onrowchecked.call(this);
                                }
                            });
                        });
                        $jgF.call(this);
                    }
                );
                $jgF = null;
            },
            checkAllOrNot: function(parent) {
                var ischeck = $j(this).attr("checked");
                $j('tbody tr', g.bDiv).each(function() {
                    if (ischeck) {
                        $j(this).addClass("trSelected");
                    }
                    else {
                        $j(this).removeClass("trSelected");
                    }
                });
                $j("input.itemchk", g.bDiv).each(function() {
                    this.checked = ischeck;
                    //Raise Event
                    if (p.onrowchecked) {
                        p.onrowchecked.call(this);
                    }
                });
            },
            pager: 0
        };

        //create model if any
        if (p.colModel) {
            thead = document.createElement('thead');
            tr = document.createElement('tr');
            //p.showcheckbox ==true;
            if (p.showcheckbox) {
                var cth = jQuery('<th/>');
                var cthch = jQuery('<input type="checkbox"/>');
                cthch.addClass("noborder")
                cth.addClass("cth").attr({ 'axis': "col-1", width: "22", "isch": true }).append(cthch);
                $j(tr).append(cth);
            }
            for (i = 0; i < p.colModel.length; i++) {
                var cm = p.colModel[i];
                var th = document.createElement('th');

                th.innerHTML = cm.display;

                if (cm.name && cm.sortable)
                    $j(th).attr('abbr', cm.name);

                //th.idx = i;
                $j(th).attr('axis', 'col' + i);

                if (cm.align)
                    th.align = cm.align;

                if (cm.width)
                    $j(th).attr('width', cm.width);

                if (cm.hide) {
                    th.hide = true;
                }
                if (cm.toggle != undefined) {
                    th.toggle = cm.toggle
                }
                if (cm.process) {
                    th.process = cm.process;
                }

                $j(tr).append(th);
            }
            $j(thead).append(tr);
            $j(t).prepend(thead);
        } // end if p.colmodel	

        //init divs
        g.gDiv = document.createElement('div'); //create global container
        g.mDiv = document.createElement('div'); //create title container
        g.hDiv = document.createElement('div'); //create header container
        g.bDiv = document.createElement('div'); //create body container
        g.vDiv = document.createElement('div'); //create grip
        g.rDiv = document.createElement('div'); //create horizontal resizer
        g.cDrag = document.createElement('div'); //create column drag
        g.block = document.createElement('div'); //creat blocker
        g.nDiv = document.createElement('div'); //create column show/hide popup
        g.nBtn = document.createElement('div'); //create column show/hide button
        g.iDiv = document.createElement('div'); //create editable layer
        g.tDiv = document.createElement('div'); //create toolbar
        g.sDiv = document.createElement('div');

        if (p.usepager) g.pDiv = document.createElement('div'); //create pager container
        g.hTable = document.createElement('table');

        //set gDiv
        g.gDiv.className = p.gridClass;
        if (p.width != 'auto') g.gDiv.style.width = p.width + 'px';

        //add conditional classes
        if ($j.browser.msie)
            $j(g.gDiv).addClass('ie');

        if (p.novstripe)
            $j(g.gDiv).addClass('novstripe');

        $j(t).before(g.gDiv);
        $j(g.gDiv)
		.append(t)
		;

        //set toolbar
        if (p.buttons) {
            g.tDiv.className = 'tDiv';
            var tDiv2 = document.createElement('div');
            tDiv2.className = 'tDiv2';

            for (i = 0; i < p.buttons.length; i++) {
                var btn = p.buttons[i];
                if (!btn.separator) {
                    var btnDiv = document.createElement('div');
                    btnDiv.className = 'fbutton';
                    btnDiv.innerHTML = "<div><span>" + btn.displayname + "</span></div>";
                    if (btn.title) {
                        btnDiv.title = btn.title;
                    }
                    if (btn.bclass)
                        $j('span', btnDiv)
							.addClass(btn.bclass);
                    btnDiv.onpress = btn.onpress;
                    btnDiv.name = btn.name;
                    if (btn.onpress) {
                        $j(btnDiv).click
							(
								function() {
								    this.onpress(this.name, g.gDiv);
								}
							);
                    }
                    $j(tDiv2).append(btnDiv);
                    if ($j.browser.msie && $j.browser.version < 7.0) {
                        $j(btnDiv).hover(function() { $j(this).addClass('fbOver'); }, function() { $j(this).removeClass('fbOver'); });
                    }

                } else {
                    $j(tDiv2).append("<div class='btnseparator'></div>");
                }
            }
            $j(g.tDiv).append(tDiv2);
            $j(g.tDiv).append("<div style='clear:both'></div>");
            $j(g.gDiv).prepend(g.tDiv);
        }

        //set hDiv
        g.hDiv.className = 'hDiv';

        $j(t).before(g.hDiv);

        //set hTable
        g.hTable.cellPadding = 0;
        g.hTable.cellSpacing = 0;
        $j(g.hDiv).append('<div class="hDivBox"></div>');
        $j('div', g.hDiv).append(g.hTable);
        var thead = $j("thead:first", t).get(0);
        if (thead) $j(g.hTable).append(thead);
        thead = null;

        if (!p.colmodel) var ci = 0;

        //setup thead			
        $j('thead tr:first th', g.hDiv).each
			(
			 	function() {
			 	    var thdiv = document.createElement('div');
			 	    if ($j(this).attr('abbr')) {
			 	        $j(this).click(
								function(e) {
								    if (!$j(this).hasClass('thOver')) return false;
								    var obj = (e.target || e.srcElement);
								    if (obj.href || obj.type) return true;
								    g.changeSort(this);
								}
							);

			 	        if ($j(this).attr('abbr') == p.sortname) {
			 	            this.className = 'sorted';
			 	            thdiv.className = 's' + p.sortorder;
			 	        }
			 	    }

			 	    if (this.hide) $j(this).hide();

			 	    if (!p.colmodel && !$j(this).attr("isch")) {
			 	        $j(this).attr('axis', 'col' + ci++);
			 	    }


			 	    $j(thdiv).css({ textAlign: this.align, width: this.width + 'px' });
			 	    thdiv.innerHTML = this.innerHTML;

			 	    $j(this).empty().append(thdiv).removeAttr('width');
			 	    if (!$j(this).attr("isch")) {
			 	        $j(this).mousedown(function(e) {
			 	            g.dragStart('colMove', e, this);
			 	        })
						.hover(
							function() {

							    if (!g.colresize && !$j(this).hasClass('thMove') && !g.colCopy) $j(this).addClass('thOver');

							    if ($j(this).attr('abbr') != p.sortname && !g.colCopy && !g.colresize && $j(this).attr('abbr')) $j('div', this).addClass('s' + p.sortorder);
							    else if ($j(this).attr('abbr') == p.sortname && !g.colCopy && !g.colresize && $j(this).attr('abbr')) {
							        var no = '';
							        if (p.sortorder == 'asc') no = 'desc';
							        else no = 'asc';
							        $j('div', this).removeClass('s' + p.sortorder).addClass('s' + no);
							    }

							    if (g.colCopy) {

							        var n = $j('th', g.hDiv).index(this);

							        if (n == g.dcoln) return false;



							        if (n < g.dcoln) $j(this).append(g.cdropleft);
							        else $j(this).append(g.cdropright);

							        g.dcolt = n;

							    } else if (!g.colresize) {
							        var thsa = $j('th:visible', g.hDiv);
							        var nv = -1;
							        for (var i = 0, j = 0, l = thsa.length; i < l; i++) {
							            if ($j(thsa[i]).css("display") != "none") {
							                if (thsa[i] == this) {
							                    nv = j;
							                    break;
							                }
							                j++;
							            }
							        }
							        // var nv = $j('th:visible', g.hDiv).index(this);
							        var onl = parseInt($j('div:eq(' + nv + ')', g.cDrag).css('left'));
							        var nw = parseInt($j(g.nBtn).width()) + parseInt($j(g.nBtn).css('borderLeftWidth'));
							        nl = onl - nw + Math.floor(p.cgwidth / 2);

							        $j(g.nDiv).hide(); $j(g.nBtn).hide();

							        $j(g.nBtn).css({ 'left': nl, top: g.hDiv.offsetTop }).show();

							        var ndw = parseInt($j(g.nDiv).width());

							        $j(g.nDiv).css({ top: g.bDiv.offsetTop });

							        if ((nl + ndw) > $j(g.gDiv).width())
							            $j(g.nDiv).css('left', onl - ndw + 1);
							        else
							            $j(g.nDiv).css('left', nl);


							        if ($j(this).hasClass('sorted'))
							            $j(g.nBtn).addClass('srtd');
							        else
							            $j(g.nBtn).removeClass('srtd');

							    }

							},
							function() {
							    $j(this).removeClass('thOver');
							    if ($j(this).attr('abbr') != p.sortname) $j('div', this).removeClass('s' + p.sortorder);
							    else if ($j(this).attr('abbr') == p.sortname) {
							        var no = '';
							        if (p.sortorder == 'asc') no = 'desc';
							        else no = 'asc';

							        $j('div', this).addClass('s' + p.sortorder).removeClass('s' + no);
							    }
							    if (g.colCopy) {
							        $j(g.cdropleft).remove();
							        $j(g.cdropright).remove();
							        g.dcolt = null;
							    }
							})
						; //wrap content
			 	    }
			 	}
			);

        //set bDiv
        g.bDiv.className = 'bDiv';
        $j(t).before(g.bDiv);
        $j(g.bDiv)
		.css({ height: (p.height == 'auto') ? 'auto' : p.height + "px" })
		.scroll(function(e) { g.scroll() })
		.append(t)
		;

        if (p.height == 'auto') {
            $j('table', g.bDiv).addClass('autoht');
        }

        //add td properties
        if (p.url == false || p.url == "") {
            g.addCellProp();
            //add row properties
            g.addRowProp();
        }

        //set cDrag

        var cdcol = $j('thead tr:first th:first', g.hDiv).get(0);

        if (cdcol != null) {
            g.cDrag.className = 'cDrag';
            g.cdpad = 0;

            g.cdpad += (isNaN(parseInt($j('div', cdcol).css('borderLeftWidth'))) ? 0 : parseInt($j('div', cdcol).css('borderLeftWidth')));
            g.cdpad += (isNaN(parseInt($j('div', cdcol).css('borderRightWidth'))) ? 0 : parseInt($j('div', cdcol).css('borderRightWidth')));
            g.cdpad += (isNaN(parseInt($j('div', cdcol).css('paddingLeft'))) ? 0 : parseInt($j('div', cdcol).css('paddingLeft')));
            g.cdpad += (isNaN(parseInt($j('div', cdcol).css('paddingRight'))) ? 0 : parseInt($j('div', cdcol).css('paddingRight')));
            g.cdpad += (isNaN(parseInt($j(cdcol).css('borderLeftWidth'))) ? 0 : parseInt($j(cdcol).css('borderLeftWidth')));
            g.cdpad += (isNaN(parseInt($j(cdcol).css('borderRightWidth'))) ? 0 : parseInt($j(cdcol).css('borderRightWidth')));
            g.cdpad += (isNaN(parseInt($j(cdcol).css('paddingLeft'))) ? 0 : parseInt($j(cdcol).css('paddingLeft')));
            g.cdpad += (isNaN(parseInt($j(cdcol).css('paddingRight'))) ? 0 : parseInt($j(cdcol).css('paddingRight')));

            $j(g.bDiv).before(g.cDrag);

            var cdheight = $j(g.bDiv).height();
            var hdheight = $j(g.hDiv).height();

            $j(g.cDrag).css({ top: -hdheight + 'px' });

            $j('thead tr:first th', g.hDiv).each
			(
			 	function() {
			 	    var cgDiv = document.createElement('div');
			 	    $j(g.cDrag).append(cgDiv);
			 	    if (!p.cgwidth) p.cgwidth = $j(cgDiv).width();
			 	    $j(cgDiv).css({ height: cdheight + hdheight })
						.mousedown(function(e) { g.dragStart('colresize', e, this); })
						;
			 	    if ($j.browser.msie && $j.browser.version < 7.0) {
			 	        g.fixHeight($j(g.gDiv).height());
			 	        $j(cgDiv).hover(
								function() {
								    g.fixHeight();
								    $j(this).addClass('dragging')
								},
								function() { if (!g.colresize) $j(this).removeClass('dragging') }
							);
			 	    }
			 	}
			);

            //g.rePosDrag();

        }


        //add strip		
        if (p.striped)
            $j('tbody tr:odd', g.bDiv).addClass('erow');


        if (p.resizable && p.height != 'auto') {
            g.vDiv.className = 'vGrip';
            $j(g.vDiv)
		.mousedown(function(e) { g.dragStart('vresize', e) })
		.html('<span></span>');
            $j(g.bDiv).after(g.vDiv);
        }

        if (p.resizable && p.width != 'auto' && !p.nohresize) {
            g.rDiv.className = 'hGrip';
            $j(g.rDiv)
		.mousedown(function(e) { g.dragStart('vresize', e, true); })
		.html('<span></span>')
		.css('height', $j(g.gDiv).height())
		;
            if ($j.browser.msie && $j.browser.version < 7.0) {
                $j(g.rDiv).hover(function() { $j(this).addClass('hgOver'); }, function() { $j(this).removeClass('hgOver'); });
            }
            $j(g.gDiv).append(g.rDiv);
        }

        // add pager
        if (p.usepager) {
            g.pDiv.className = 'pDiv';
            g.pDiv.innerHTML = '<div class="pDiv2"></div>';
            $j(g.bDiv).after(g.pDiv);
            var html = '<div class="pGroup"><div class="pFirst pButton" title="转到第一页"><span></span></div><div class="pPrev pButton" title="转到上一页"><span></span></div> </div><div class="btnseparator"></div> <div class="pGroup"><span class="pcontrol">当前第 <input type="text" size="1" value="1" />页，总页数 <span> 1 </span></span></div><div class="btnseparator"></div><div class="pGroup"> <div class="pNext pButton" title="转到下一页"><span></span></div><div class="pLast pButton" title="转到最后一页"><span></span></div></div><div class="btnseparator"></div><div class="pGroup"> <div class="pReload pButton" title="刷新"><span></span></div> </div> <div class="btnseparator"></div><div class="pGroup"><span class="pPageStat"></span></div>';
            $j('div', g.pDiv).html(html);

            $j('.pReload', g.pDiv).click(function() { g.populate() });
            $j('.pFirst', g.pDiv).click(function() { g.changePage('first') });
            $j('.pPrev', g.pDiv).click(function() { g.changePage('prev') });
            $j('.pNext', g.pDiv).click(function() { g.changePage('next') });
            $j('.pLast', g.pDiv).click(function() { g.changePage('last') });
            $j('.pcontrol input', g.pDiv).keydown(function(e) { if (e.keyCode == 13) g.changePage('input') });
            if ($j.browser.msie && $j.browser.version < 7) $j('.pButton', g.pDiv).hover(function() { $j(this).addClass('pBtnOver'); }, function() { $j(this).removeClass('pBtnOver'); });

            if (p.useRp) {
                var opt = "";
                for (var nx = 0; nx < p.rpOptions.length; nx++) {
                    if (p.rp == p.rpOptions[nx]) sel = 'selected="selected"'; else sel = '';
                    opt += "<option value='" + p.rpOptions[nx] + "' " + sel + " >" + p.rpOptions[nx] + "&nbsp;&nbsp;</option>";
                };
                $j('.pDiv2', g.pDiv).prepend("<div class='pGroup'>每页 <select name='rp'>" + opt + "</select>条</div> <div class='btnseparator'></div>");
                $j('select', g.pDiv).change(
					function() {
					    if (p.onRpChange)
					        p.onRpChange(+this.value);
					    else {
					        p.newp = 1;
					        p.rp = +this.value;
					        g.populate();
					    }
					}
				);
            }

            //add search button
            if (p.searchitems) {
                $j('.pDiv2', g.pDiv).prepend("<div class='pGroup'> <div class='pSearch pButton'><span></span></div> </div>  <div class='btnseparator'></div>");
                $j('.pSearch', g.pDiv).click(function() { $j(g.sDiv).slideToggle('fast', function() { $j('.sDiv:visible input:first', g.gDiv).trigger('focus'); }); });
                //add search box
                g.sDiv.className = 'sDiv';

                sitems = p.searchitems;

                var sopt = "";
                var op = "Eq";
                for (var s = 0; s < sitems.length; s++) {
                    if (p.qtype == '' && sitems[s].isdefault == true) {
                        p.qtype = sitems[s].name;
                        sel = 'selected="selected"';
                    } else sel = '';
                    if (sitems[s].operater == "Like") {
                        op = "Like";
                    }
                    else {
                        op = "Eq";
                    }
                    sopt += "<option value='" + sitems[s].name + "$j" + op + "$j" + s + "' " + sel + " >" + sitems[s].display + "&nbsp;&nbsp;</option>";
                }

                if (p.qtype == '') p.qtype = sitems[0].name;

                $j(g.sDiv).append("<div class='sDiv2'>快速检索：<input type='text' size='30' name='q' class='qsbox' /> <select name='qtype'>" + sopt + "</select> <input type='button' name='qclearbtn' value='清空' /></div>");

                $j('input[name=q],select[name=qtype]', g.sDiv).keydown(function(e) { if (e.keyCode == 13) g.doSearch() });
                $j('input[name=qclearbtn]', g.sDiv).click(function() { $j('input[name=q]', g.sDiv).val(''); p.query = ''; g.doSearch(); });
                $j(g.bDiv).after(g.sDiv);

            }

        }
        $j(g.pDiv, g.sDiv).append("<div style='clear:both'></div>");

        // add title
        if (p.title) {
            g.mDiv.className = 'mDiv';
            g.mDiv.innerHTML = '<div class="ftitle">' + p.title + '</div>';
            $j(g.gDiv).prepend(g.mDiv);
            if (p.showTableToggleBtn) {
                $j(g.mDiv).append('<div class="ptogtitle" title="收缩/展开"><span></span></div>');
                $j('div.ptogtitle', g.mDiv).click
					(
					 	function() {
					 	    $j(g.gDiv).toggleClass('hideBody');
					 	    $j(this).toggleClass('vsble');
					 	}
					);
            }
            //g.rePosDrag();
        }

        //setup cdrops
        g.cdropleft = document.createElement('span');
        g.cdropleft.className = 'cdropleft';
        g.cdropright = document.createElement('span');
        g.cdropright.className = 'cdropright';

        //add block
        g.block.className = 'gBlock';
        var blockloading = $j("<div/>");
        blockloading.addClass("loading");
        $j(g.block).append(blockloading);
        var gh = $j(g.bDiv).height();
        var gtop = g.bDiv.offsetTop;
        $j(g.block).css(
		{
		    width: g.bDiv.style.width,
		    height: gh,
		    position: 'relative',
		    marginBottom: (gh * -1),
		    zIndex: 1,
		    top: gtop,
		    left: '0px'
		}
		);
        $j(g.block).fadeTo(0, p.blockOpacity);

        // add column control
        if ($j('th', g.hDiv).length) {
            g.nDiv.className = 'nDiv';
            g.nDiv.innerHTML = "<table cellpadding='0' cellspacing='0'><tbody></tbody></table>";
            $j(g.nDiv).css(
			{
			    marginBottom: (gh * -1),
			    display: 'none',
			    top: gtop
			}
			).noSelect()
			;

            var cn = 0;


            $j('th div', g.hDiv).each
			(
			 	function() {
			 	    var kcol = $j("th[axis='col" + cn + "']", g.hDiv)[0];
			 	    if (kcol == null) return;
			 	    var chkall = $j("input[type='checkbox']", this);
			 	    if (chkall.length > 0) {
			 	        chkall[0].onclick = g.checkAllOrNot;
			 	        return;
			 	    }
			 	    if (kcol.toggle == false || this.innerHTML == "") {
			 	        cn++;
			 	        return;
			 	    }
			 	    var chk = 'checked="checked"';
			 	    if (kcol.style.display == 'none') chk = '';

			 	    $j('tbody', g.nDiv).append('<tr><td class="ndcol1"><input type="checkbox" ' + chk + ' class="togCol noborder" value="' + cn + '" /></td><td class="ndcol2">' + this.innerHTML + '</td></tr>');
			 	    cn++;
			 	}
			);

            if ($j.browser.msie && $j.browser.version < 7.0)
                $j('tr', g.nDiv).hover
				(
				 	function() { $j(this).addClass('ndcolover'); },
					function() { $j(this).removeClass('ndcolover'); }
				);

            $j('td.ndcol2', g.nDiv).click
			(
			 	function() {
			 	    if ($j('input:checked', g.nDiv).length <= p.minColToggle && $j(this).prev().find('input')[0].checked) return false;
			 	    return g.toggleCol($j(this).prev().find('input').val());
			 	}
			);

            $j('input.togCol', g.nDiv).click
			(
			 	function() {

			 	    if ($j('input:checked', g.nDiv).length < p.minColToggle && this.checked == false) return false;
			 	    $j(this).parent().next().trigger('click');
			 	    //return false;
			 	}
			);


            $j(g.gDiv).prepend(g.nDiv);

            $j(g.nBtn).addClass('nBtn')
			.html('<div></div>')
            //.attr('title', 'Hide/Show Columns')
			.click
			(
			 	function() {
			 	    $j(g.nDiv).toggle(); return true;
			 	}
			);

            if (p.showToggleBtn)
                $j(g.gDiv).prepend(g.nBtn);

        }

        // add date edit layer
        $j(g.iDiv)
		.addClass('iDiv')
		.css({ display: 'none' })
		;
        $j(g.bDiv).append(g.iDiv);

        // add flexigrid events
        $j(g.bDiv)
		.hover(function() { $j(g.nDiv).hide(); $j(g.nBtn).hide(); }, function() { if (g.multisel) g.multisel = false; })
		;
        $j(g.gDiv)
		.hover(function() { }, function() { $j(g.nDiv).hide(); $j(g.nBtn).hide(); })
		;

        //add document events
        $j(document)
		.mousemove(function(e) { g.dragMove(e) })
		.mouseup(function(e) { g.dragEnd() })
		.hover(function() { }, function() { g.dragEnd() })
		;

        //browser adjustments
        if ($j.browser.msie && $j.browser.version < 7.0) {
            $j('.hDiv,.bDiv,.mDiv,.pDiv,.vGrip,.tDiv, .sDiv', g.gDiv)
			.css({ width: '100%' });
            $j(g.gDiv).addClass('ie6');
            if (p.width != 'auto') $j(g.gDiv).addClass('ie6fullwidthbug');
        }

        g.rePosDrag();
        g.fixHeight();

        //make grid functions accessible
        t.p = p;
        t.grid = g;

        // load data
        if (p.url && p.autoload) {
            g.populate();
        }

        return t;

    };

    var docloaded = false;

    $j(document).ready(function() { docloaded = true });

    $j.fn.flexigrid = function(p) {

        return this.each(function() {
            if (!docloaded) {
                $j(this).hide();
                var t = this;
                $j(document).ready
					(
						function() {
						    $j.addFlex(t, p);
						}
					);
            } else {
                $j.addFlex(this, p);
            }
        });

    }; //end flexigrid

    $j.fn.flexReload = function(p) { // function to reload grid

        return this.each(function() {
            if (this.grid && this.p.url) this.grid.populate();
        });

    }; //end flexReload
    //重新指定宽度和高度
    $j.fn.flexResize = function(w, h) {
        var p = { width: w, height: h };
        return this.each(function() {
            if (this.grid) {
                $j.extend(this.p, p);
                this.grid.reSize();
            }
        });
    }
    $j.fn.ChangePage = function(type) {
        return this.each(function() {
            if (this.grid) {
                this.grid.changePage(type);
            }
        })
    }
    $j.fn.flexOptions = function(p) { //function to update general options

        return this.each(function() {
            if (this.grid) $j.extend(this.p, p);
        });

    }; //end flexOptions
    $j.fn.GetOptions = function() {
        if (this[0].grid) {
            return this[0].p;
        }
        return null;
    }
    $j.fn.getCheckedRows = function() {
        if (this[0].grid) {
            return this[0].grid.getCheckedRows();
        }
        return [];
    }
    $j.fn.flexToggleCol = function(cid, visible) { // function to reload grid

        return this.each(function() {
            if (this.grid) this.grid.toggleCol(cid, visible);
        });

    }; //end flexToggleCol

    $j.fn.flexAddData = function(data) { // function to add data to grid

        return this.each(function() {
            if (this.grid) this.grid.addData(data);
        });

    };

    $j.fn.noSelect = function(p) { //no select plugin by me :-)
        if (p == null)
            prevent = true;
        else
            prevent = p;

        if (prevent) {

            return this.each(function() {
                if ($j.browser.msie || $j.browser.safari) $j(this).bind('selectstart', function() { return false; });
                else if ($j.browser.mozilla) {
                    $j(this).css('MozUserSelect', 'none');
                    $j('body').trigger('focus');
                }
                else if ($j.browser.opera) $j(this).bind('mousedown', function() { return false; });
                else $j(this).attr('unselectable', 'on');
            });

        } else {


            return this.each(function() {
                if ($j.browser.msie || $j.browser.safari) $j(this).unbind('selectstart');
                else if ($j.browser.mozilla) $j(this).css('MozUserSelect', 'inherit');
                else if ($j.browser.opera) $j(this).unbind('mousedown');
                else $j(this).removeAttr('unselectable', 'on');
            });

        }

    }; //end noSelect

})(jQuery);