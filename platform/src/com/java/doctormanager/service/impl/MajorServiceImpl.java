package com.java.doctormanager.service.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.featherfly.commons.tree.Tree;
import org.featherfly.commons.tree.TreeNode;
import org.featherfly.commons.tree.component.MifTreeHelper;
import org.featherfly.commons.tree.component.MifTreeNode;
import org.featherfly.commons.tree.component.MifTreeNodeCreator;
import org.springframework.beans.BeanUtils;

import com.java.doctormanager.service.MajorService;
import com.java.doctormanager.vo.DoctorMajor;
import com.java.doctormanager.vo.Major;
import com.java.ec.common.PageSortModel;
import com.java.platform.user.service.ServiceImpl;

public class MajorServiceImpl extends ServiceImpl implements MajorService{

	@Override
	public <T> List<T> findPageByCriteria(PageSortModel arg0, Object arg1,
			Map<String, ?> arg2) {
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DoctorMajor> getMajors(PageSortModel model, DoctorMajor major) {
		StringBuilder condition = new StringBuilder();
		Map<String,Object> params = new HashMap<String,Object>();
		
		condition.append("from DoctorMajor c where 1=1 ");
		if(major!=null){
			if(StringUtils.isNotBlank(major.getMajor())){
				condition.append(" and c.major = :major");
				params.put("major", major.getMajor());
			}
			if(StringUtils.isNotBlank(major.getMajorStatus())){
				condition.append(" and c.majorStatus = :majorStatus");
				params.put("majorStatus", major.getMajorStatus());
			}
			if(major.getParent()!=null){
				if(StringUtils.isNotBlank(major.getParent().getMajor())){
					condition.append(" and c.parent.major = :pmajor");
					params.put("pmajor", major.getParent().getMajor());
				}
				if(major.getParent().getId()!=null){
					condition.append(" and c.parent.id = :pid");
					params.put("pid", major.getParent().getId());
				}
			}
		}else{
			condition.append(" and c.parent.id = null");
		}
		
		condition.append(" order by c.id asc ");
		
		List<DoctorMajor> tmp_list = null;
		if(model==null){
			tmp_list = super.list(condition.toString(), -1, -1, params);
		}else{
			tmp_list = (List<DoctorMajor>) super.listForEc(condition.toString(), model, params);
		}
		return tmp_list;
	}

	@Override
	public DoctorMajor saveOrUpdateMajor(DoctorMajor major) {
		Long id = major.getId();
		if(major.getParent()!=null&&major.getParent().getId()==null){
			major.setParent(null);
		}
		//二级节点的医生类型
		major.setDoctorType(getDoctorTypeOfSecondNode(major));
		if(id==null){
			super.add(major);
		}else{
			DoctorMajor qryMj = super.get(id, DoctorMajor.class);
			BeanUtils.copyProperties(major, qryMj, new String[]{"id"});	
			super.edit(qryMj);
			return qryMj;
		}
		return major;
	}
	/**
	 * 获取二级节点的医生类型
	 * @param major
	 * @return
	 */
	private String getDoctorTypeOfSecondNode(DoctorMajor major){
		DoctorMajor parentMajor = null;
		//获取父节点
		if(major.getParent()==null){
			return  null;
		}else{
			parentMajor = super.get(major.getParent().getId(), DoctorMajor.class);
		}
		
		DoctorMajor parentTop = null;
		if(parentMajor.getParent()==null){//父节点处于根节点
			return parentMajor.getMajor();
		}else{
			parentTop= super.get(parentMajor.getParent().getId(), DoctorMajor.class);
		}
		
		DoctorMajor parentTopTop = null;
		if(parentTop.getParent()==null){//父节点处于跟二级节点
			return  parentMajor.getMajor();
		}else{//三级及三级一下节点
			parentTopTop= super.get(parentTop.getParent().getId(), DoctorMajor.class);
		}
		
		//父节点处于跟三级节点
		return  parentMajor.getDoctorType();
	}
	@Override
	public List<Map<String, Object>> getIndexTree() {
		Tree<DoctorMajor> type = this.getTree();
		MifTreeHelper mifTreeHelper = new MifTreeHelper(type);	
		return mifTreeHelper.format(new MifTreeNodeCreator<DoctorMajor>() {
			//生成miftree需要的json格式的list,map封装格式
			public MifTreeNode createNode(TreeNode<DoctorMajor> node) {
				MifTreeNode mifNode = new MifTreeNode();
				//property
				Map<String, Object> propertyMap = new HashMap<String, Object>();
				propertyMap.put("name",node.getNodeObject().getMajor());				
				mifNode.setProperty(propertyMap);
				//state
				Map<String, Object> stateMap = new HashMap<String, Object>();
				stateMap.put("open", true);
				mifNode.setState(stateMap);
				//data
				Map<String, Object> dataMap = new HashMap<String, Object>();		
				dataMap.put("id", node.getNodeObject().getId());
				dataMap.put("name", node.getNodeObject().getMajor());

				mifNode.setData(dataMap);
				//pe
				mifNode.setType("folder");
				return mifNode;
			} 
		});
	}
	private Tree<DoctorMajor> getTree() {
		String hql = "FROM DoctorMajor c where c.majorStatus = 'Y' order by id asc";
		List<DoctorMajor> IndexList = getPersistProxy().getOrmPersistence().findByHQLQuery(hql);
		if(IndexList.isEmpty()) 
			return null;
		List<TreeNode<DoctorMajor>> nodeList = new ArrayList<TreeNode<DoctorMajor>>();
		for (DoctorMajor index : IndexList) {
			TreeNode<DoctorMajor> node = new TreeNode<DoctorMajor>(index.getId()+"");
			node.setNodeObject(index);
			node.setId(index.getId()+"");
			DoctorMajor parentIndex = index.getParent();
			if(IndexList.contains(parentIndex)){
				if(parentIndex!=null) {
					TreeNode<DoctorMajor> parentNode = new TreeNode<DoctorMajor>(parentIndex.getId()+"");
					parentNode.setNodeObject(parentIndex);
					node.setParentNode(parentNode);
					parentNode.setId(parentIndex.getId()+"");
				}
			}
			nodeList.add(node);
		}
		//将list树型化
		Tree<DoctorMajor> depTree = new Tree<DoctorMajor>(nodeList);
		depTree.sort(new Comparator<TreeNode<DoctorMajor>>() {
			public int compare(TreeNode<DoctorMajor> o1, TreeNode<DoctorMajor> o2) {
				if(o1 !=null && o2 !=null && o1.getNodeObject()!=null && o2.getNodeObject()!=null){
					return o1.getNodeObject().getId() < o2.getNodeObject().getId()
							?1:-1;
				}
				return 0;
			}
		});
		return depTree;
	}
}
