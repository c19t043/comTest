package com.wx.domain;

/**
 * WxMenu entity. @author MyEclipse Persistence Tools
 */

public class WxMenu implements java.io.Serializable {

	// Fields

	private Integer menuId;
	private String menuName;
	private String menuUrl;
	private Integer parentId;
	private Integer menuLevel;
	private Integer menuClass;
	private String menuCodepath;
	private String menuIcon;
	private String menuNamepath;
	private String menuKey;

	// Constructors

	/** default constructor */
	public WxMenu() {
	}

	/** full constructor */
	public WxMenu(String menuName, String menuUrl, Integer parentId,
			Integer menuLevel, Integer menuClass, String menuCodepath,
			String menuIcon, String menuNamepath, String menuKey) {
		this.menuName = menuName;
		this.menuUrl = menuUrl;
		this.parentId = parentId;
		this.menuLevel = menuLevel;
		this.menuClass = menuClass;
		this.menuCodepath = menuCodepath;
		this.menuIcon = menuIcon;
		this.menuNamepath = menuNamepath;
		this.menuKey = menuKey;
	}

	// Property accessors

	public Integer getMenuId() {
		return this.menuId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

	public String getMenuName() {
		return this.menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuUrl() {
		return this.menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}

	public Integer getParentId() {
		return this.parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Integer getMenuLevel() {
		return this.menuLevel;
	}

	public void setMenuLevel(Integer menuLevel) {
		this.menuLevel = menuLevel;
	}

	public Integer getMenuClass() {
		return this.menuClass;
	}

	public void setMenuClass(Integer menuClass) {
		this.menuClass = menuClass;
	}

	public String getMenuCodepath() {
		return this.menuCodepath;
	}

	public void setMenuCodepath(String menuCodepath) {
		this.menuCodepath = menuCodepath;
	}

	public String getMenuIcon() {
		return this.menuIcon;
	}

	public void setMenuIcon(String menuIcon) {
		this.menuIcon = menuIcon;
	}

	public String getMenuNamepath() {
		return this.menuNamepath;
	}

	public void setMenuNamepath(String menuNamepath) {
		this.menuNamepath = menuNamepath;
	}

	public String getMenuKey() {
		return this.menuKey;
	}

	public void setMenuKey(String menuKey) {
		this.menuKey = menuKey;
	}

}