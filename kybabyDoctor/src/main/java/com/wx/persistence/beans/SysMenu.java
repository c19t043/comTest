 package com.wx.persistence.beans;
 
 import com.wx.util.base.TreeView;
 
 public class SysMenu extends TreeView
 {
   private static final long serialVersionUID = 7612820883249865655L;
   private String menuUrl;
   private Integer menuClass;
   private Integer menuLevel;
   private String menuKey;
   
   public String getMenuUrl()
   {
     return this.menuUrl;
   }
 
   public String getMenuKey() {
	return menuKey;
}

public void setMenuKey(String menuKey) {
	this.menuKey = menuKey;
}

public void setMenuUrl(String menuUrl) {
     this.menuUrl = menuUrl;
   }
 
   public Integer getMenuClass() {
     return this.menuClass;
   }
 
   public void setMenuClass(Integer menuClass) {
     this.menuClass = menuClass;
   }
 
   public Integer getMenuLevel() {
     return this.menuLevel;
   }
 
   public void setMenuLevel(Integer menuLevel) {
     this.menuLevel = menuLevel;
   }
 }

