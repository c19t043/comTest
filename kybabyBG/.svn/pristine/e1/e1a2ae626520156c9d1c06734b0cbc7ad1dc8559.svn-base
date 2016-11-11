package com.kybaby.action.admin;

import java.util.ArrayList;
import java.util.List;

import com.kybaby.action.BaseAction;
import com.kybaby.domain.Position;

public class PositionHandle extends BaseAction {

	private String mes="";
	private String action="";
	
	
	private String name;    //职称的名字
	private long rank;      //职称的级别
	private String status;  //职称状态
	private long updateId;  //修改职称的id
	
	
	private List allPosition=new ArrayList();// 返回的所有职称

	public String execute()
	{
		if(action.equals("all"))
		{
			System.out.println("positionHandle.action?action=all.........");
			allPosition=positionBo.getAllPosition();
            mes="成功";
            return "success";
		}
		if(action.equals("add"))
		{
			System.out.println("positionHandle.action?action=add.........");
			Position ps=positionBo.getPositionByName(name);
			if(ps==null)
			{
				Position addPs=new Position();
				addPs.setName(name);
				addPs.setRank(rank);
				addPs.setPositionStatus(status);
				baseBo.savePosition(addPs);
				mes="添加成功";
				return "success";
			}
			else
			{
				mes="已存在该职称";
				return "fail";
			}
		}
		if(action.equals("update"))
		{
			System.out.println("positionHandle.action?action=update.........");
			Position ps=positionBo.getPositionById(updateId);
			Position updatePs=positionBo.getPositionByName(name);
			if(updatePs==null||ps.getName().equals(name))
			{
				ps.setName(name);
				ps.setPositionStatus(status);
				ps.setRank(rank);
				baseBo.updatePosition(ps);
				mes="更新成功";
			    return "success";
			}
			else
			{
				mes="已存在该职称";
				return "fail";
			}
		}
		
		return "success";
	}

	public String getMes() {
		return mes;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setRank(long rank) {
		this.rank = rank;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setUpdateId(long updateId) {
		this.updateId = updateId;
	}

	public List getAllPosition() {
		return allPosition;
	}
}
