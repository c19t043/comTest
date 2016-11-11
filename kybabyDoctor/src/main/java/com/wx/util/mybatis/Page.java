 package com.wx.util.mybatis;
 
 import java.io.Serializable;
 
 public class Page
   implements Serializable
 {
   private static final long serialVersionUID = -3390469753961799282L;
   private int rows = 10;
   private int totalPage;
   private int totalResult;
   private int page = 1;
   private int currentResult;
   private boolean entityOrField;
   private String pageStr;
 
   public int getTotalPage()
   {
     if (this.totalResult % this.rows == 0)
       this.totalPage = (this.totalResult / this.rows);
     else
       this.totalPage = (this.totalResult / this.rows + 1);
     return this.totalPage;
   }
   public void setTotalPage(int totalPage) {
     this.totalPage = totalPage;
   }
   public int getTotalResult() {
     return this.totalResult;
   }
   public void setTotalResult(int totalResult) {
     this.totalResult = totalResult;
   }
   public String getPageStr() {
     StringBuffer sb = new StringBuffer();
     if (this.totalResult > 0) {
       sb.append("\t<ul>\n");
       if (this.page == 1) {
         sb.append("\t<li class=\"pageinfo\">首页</li>\n");
         sb.append("\t<li class=\"pageinfo\">上页</li>\n");
       } else {
         sb.append("\t<li><a href=\"#@\" onclick=\"nextPage(1)\">首页</a></li>\n");
         sb.append("\t<li><a href=\"#@\" onclick=\"nextPage(" + (this.page - 1) + ")\">上页</a></li>\n");
       }
       int showTag = 3;
       int startTag = 1;
       if (this.page > showTag) {
         startTag = this.page - 1;
       }
       int endTag = startTag + showTag - 1;
       for (int i = startTag; (i <= this.totalPage) && (i <= endTag); i++) {
         if (this.page == i)
           sb.append("<li class=\"current\">" + i + "</li>\n");
         else
           sb.append("\t<li><a href=\"#@\" onclick=\"nextPage(" + i + ")\">" + i + "</a></li>\n");
       }
       if (this.page == this.totalPage) {
         sb.append("\t<li class=\"pageinfo\">下页</li>\n");
         sb.append("\t<li class=\"pageinfo\">尾页</li>\n");
       } else {
         sb.append("\t<li><a href=\"#@\" onclick=\"nextPage(" + (this.page + 1) + ")\">下页</a></li>\n");
         sb.append("\t<li><a href=\"#@\" onclick=\"nextPage(" + this.totalPage + ")\">尾页</a></li>\n");
       }
       sb.append("\t<li class=\"pageinfo\">第" + this.page + "页</li>\n");
       sb.append("\t<li class=\"pageinfo\">共" + this.totalPage + "页</li>\n");
       sb.append("</ul>\n");
       sb.append("<script type=\"text/javascript\">\n");
       sb.append("function nextPage(page){");
       sb.append("\tif(true && document.forms[0]){\n");
       sb.append("\t\tvar url = document.forms[0].getAttribute(\"action\");\n");
       sb.append("\t\tif(url.indexOf('?')>-1){url += \"&" + (this.entityOrField ? "page" : "page") + "=\";}\n");
       sb.append("\t\telse{url += \"?" + (this.entityOrField ? "page" : "page") + "=\";}\n");
       sb.append("\t\tdocument.forms[0].action = url+page;\n");
       sb.append("\t\tdocument.forms[0].submit();\n");
       sb.append("\t}else{\n");
       sb.append("\t\tvar url = document.location+'';\n");
       sb.append("\t\tif(url.indexOf('?')>-1){\n");
       sb.append("\t\t\tif(url.indexOf('page')>-1){\n");
       sb.append("\t\t\t\tvar reg = /page=\\d*/g;\n");
       sb.append("\t\t\t\turl = url.replace(reg,'page=');\n");
       sb.append("\t\t\t}else{\n");
       sb.append("\t\t\t\turl += \"&" + (this.entityOrField ? "page" : "page") + "=\";\n");
       sb.append("\t\t\t}\n");
       sb.append("\t\t}else{url += \"?" + (this.entityOrField ? "page" : "page") + "=\";}\n");
       sb.append("\t\tdocument.location = url + page;\n");
       sb.append("\t}\n");
       sb.append("}\n");
       sb.append("</script>\n");
     }
     this.pageStr = sb.toString();
     return this.pageStr;
   }
 
   public int getPage() {
     if (this.page <= 0)
       this.page = 1;
     if (this.page > getTotalPage())
       this.page = getTotalPage();
     return this.page;
   }
   public void setPage(int page) {
     this.page = page;
   }
   public int getRows() {
     return this.rows;
   }
   public void setRows(int rows) {
     this.rows = rows;
   }
   public int getCurrentResult() {
     this.currentResult = ((getPage() - 1) * getRows());
     if (this.currentResult < 0)
       this.currentResult = 0;
     return this.currentResult;
   }
   public void setCurrentResult(int currentResult) {
     this.currentResult = currentResult;
   }
   public boolean isEntityOrField() {
     return this.entityOrField;
   }
   public void setEntityOrField(boolean entityOrField) {
     this.entityOrField = entityOrField;
   }
 }
