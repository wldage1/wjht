package com.sw.plugins.usercenter.system.organization.service;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.sw.core.data.entity.BaseEntity;
import com.sw.core.exception.DetailException;
import com.sw.core.service.CommonService;
import com.sw.core.util.DateUtil;
import com.sw.plugins.usercenter.system.organization.entity.Organization;
import com.sw.plugins.usercenter.system.role.entity.Role;

/**
 * Service实现类 - Service实现类基类
 */

@Service
public class OrganiztionService extends CommonService<Organization>{
	
	private static Logger logger = Logger.getLogger(OrganiztionService.class);
	
	//机构名称（Spring 注入）
	private String name;
	//机构代码（Spring 注入）
	private String code;
	
	
	public OrganiztionService() {
	}
	
	
	/**
	 * 默认初始化,包括用户，机构，字典类型
	 */
	public void init(){
		try
		{
			logger.debug("orgnization info is initializing");
			if (super.getCommonDao() != null){
				Organization selectOrganization = new Organization(); 
				selectOrganization.setParentId(Long.valueOf("0"));
				Organization organization = (Organization) super.getCommonDao().selectObject("organization.select", selectOrganization);
				if (organization == null){
					organization = new Organization();
					organization.setParentId(0l);
					organization.setName(name);
					organization.setCode(code);
					organization.setParentId(0l);
					organization.setParentName("");
					organization.setLevel(1);
					organization.setChildNode(1);
					organization.setPath(",0,1,");
					organization.setCreateTime(DateUtil.getCurrentDateTime());
					organization.setCreator(String.valueOf("-1"));
					save(organization);
				}
				else{
					organization.setName(name);
					organization.setCode(code);
					organization.setModifyTime(DateUtil.getCurrentDateTime());
					update(organization);
				}
			}
    		else{
    			throw new Exception("get commonDao error");
    		}
			logger.debug("orgnization info initialize finished");
		}catch (Exception e)
		{
			String debug = DetailException.expDetail(e, OrganiztionService.class);
			logger.debug("orgnization info fail");
			logger.debug(debug);
		}
	}	
	
	/**
	 * 保存或更新
	 * @param organization
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	public void saveOrUpdate(Organization organization) throws Exception{
		try {
			
			//如果id为空表示添加
			if (organization.getId() == null){
				//将当前机构的级别+1
				organization.setLevel(organization.getLevel() == null ? 1 :(organization.getLevel() + 1));
				//设置为叶子节点
				organization.setChildNode(1);
				//插入机构数据
				super.getCommonDao().save("organization.insert", organization);
				//修改当前机构的id
				organization.setId(organization.getGeneratedKey());
				//如果上级机构id为空，则直接设置上级机构id为0，机构级别为1
				if (organization.getParentId() == null){
					organization.setParentId(0l);
				}
				else{
					//如果ChildNode已经为0，则不用修改
					if (organization.getChildNode() != 0){
						//如果选择了上级机构，修改上级机构的ChildNode属性为0
						Organization tempOrganization = new Organization();
						tempOrganization.setId(organization.getParentId());
						tempOrganization.setChildNode(0);
						update(tempOrganization);
					}
				}
				//设置path
				String path = (organization.getPath() == null || organization.getPath().equals(""))?"," + organization.getGeneratedKey() + ",":organization.getPath() + organization.getGeneratedKey()+",";
				organization.setPath(path);
				//修改当前机构path
				update(organization);
			}
			else{
				//新父机构id
				long newParentId = organization.getParentId();
				//原机构id
				long oldParentId = organization.getOldParentId();
				//如果不相等，则处理原机构ChildNode属性
				if (newParentId != oldParentId){
					Organization condOrganization = new Organization();
					//原父机构id设置，并判断其下是否还存在其他子节点
					condOrganization.setParentId(organization.getOldParentId());
					Object countObj = super.getCommonDao().selectObject("organization.selectSubCountByParentId",condOrganization);
					long subOrgCount = 0;
					if (countObj instanceof Long){
						subOrgCount = (Long)countObj;
					}
					//如果原机构没有子节点，修改此机构的ChildNode属性为1（代表此节点为叶子节点）
					if (subOrgCount == 1){
						condOrganization.setId(organization.getOldParentId());
						condOrganization.setParentId(null);
						condOrganization.setChildNode(1);
						update(condOrganization);
					}
					//切换机构，设置当前机构的levee、path属性和当前机构父机构的ChildNode属性
					//设置父机构id，为查询条件
					condOrganization.setId(organization.getParentId());
					condOrganization.setParentId(null);
					condOrganization.setChildNode(null);
					Object orgObj = getOneById(condOrganization);
					if (orgObj instanceof Organization){
						int childNode = ((Organization)orgObj).getChildNode();
						//如果父机构childNode为1，则修改此属性为0
						if (childNode == 1){
							((Organization)orgObj).setChildNode(0);
							update(((Organization)orgObj));
						}
					}
					//设置当前机构的level为父机构级别+1
					organization.setLevel(((Organization)orgObj).getLevel() == null? 1 : ((Organization)orgObj).getLevel() + 1);
					//设置当前机构path
					String path = ((Organization)orgObj).getPath() == null?"," + organization.getId() + ",":((Organization)orgObj).getPath() + organization.getId()+",";
					//设置当前机构新path
					organization.setPath(path);
					//修改当前机构
					update(organization);
					//修改当期机构所有子机构的path
//					organization.setPath("," + organization.getId() + ",");
//					List<Organization> currentOrgSubList = this.getList(organization);
//					for (Organization torganization:currentOrgSubList){
//						if (torganization != null){
//							String tpath = torganization.getPath();
//							if (tpath != null){
//								tpath = tpath.replaceAll("," + oldParentId + ",", "," + newParentId + ",");
//								torganization.setPath(tpath);
//								super.getCommonDao().update("organization.updatePath", torganization);
//							}
//						}
//					}
					modifySubOrganization(organization);
				}
				else{
					//不更改机构level、childnode和path属性
					organization.setChildNode(null);
					organization.setLevel(null);
					organization.setPath(null);
					//若相等，直接处理当前机构
					update(organization);
					Organization temp = new Organization();
					temp.setParentId(organization.getId());
					List<Organization> organizationList = super.getCommonDao().selectList("organization.select", temp);
					//修改子机构
					for (int i = 0; i < organizationList.size(); i++) {
						organizationList.get(i).setParentName(organization.getName());
						super.getCommonDao().update("organization.update", organizationList.get(i));
					}
				}
			}
		} catch (Exception e) {
			DetailException.expDetail(e, OrganiztionService.class);
			throw e;
		}
	}	
	
	//递归修改子机构
	public void modifySubOrganization(Organization organization){
		Organization temp = new Organization();
		temp.setParentId(organization.getId());
		List<Organization> subList = super.getCommonDao().selectList("organization.select", temp);
		for (int i = 0; i < subList.size(); i++) {
			Organization o = subList.get(i);
			o.setLevel(organization.getLevel()+1);
			o.setPath(organization.getPath()+o.getId()+",");
			o.setParentName(organization.getName());
			super.getCommonDao().update("organization.update",o);
			modifySubOrganization(o);
		}
	}
	
	/**
	 * 机构删除
	 * @param organization
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	public void delete(Organization organization) throws Exception{
		try{
			//删除机构于角色的关系
//			RoleOrgMapper roleOrgMapper = new RoleOrgMapper();
//			roleOrgMapper.setOrgId(organization.getId());
//			super.getCommonDao().delete("roleOrgMapper.delete", roleOrgMapper);
			
			organization = getOneById(organization);
			super.getCommonDao().delete("organization.delete", organization);
			Organization condOrganization = new Organization();
			condOrganization.setParentId(organization.getParentId());
			List<Organization> list = super.getCommonDao().selectList("organization.select", condOrganization);
			if (list.size() <= 0){
				condOrganization = new Organization();
				condOrganization.setId(organization.getParentId());
				Organization org = getOneById(condOrganization);
				if (org != null){
					org.setChildNode(1);
					update(org);
				}
			}	
		}
		catch(Exception e){
			DetailException.expDetail(e, OrganiztionService.class);
			throw e;
		}
	}	
	
	/**
	 * 查询机构表格树
	 * @param organization
	 * @param nodeid
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Organization> getOrganizationTreeGrid(Organization organization,String nodeid){
		int nid = 0;
		Organization condOrganization = new Organization();
		try{
			nid = Integer.parseInt(nodeid);
		}
		catch (Exception e){
		}
		if (nid <= 0){
			condOrganization = new Organization();
			condOrganization.setParentId(Long.parseLong("0"));
			return super.getCommonDao().selectList("organization.select", condOrganization);
		}
		else{
			condOrganization = new Organization();
			condOrganization.setParentId(Long.parseLong(nodeid));
			return super.getCommonDao().selectList("organization.select", condOrganization);
		}
	}	
	
	/**
	 * 根据父机构id查询子机构列表
	 * @param parentKey
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Organization> getSubOrganization(String parentId){
		Organization condOrganization = new Organization();
		if (parentId == null){
			condOrganization = new Organization();
			condOrganization.setParentId(Long.parseLong("0"));
			return super.getCommonDao().selectList("organization.select", condOrganization);
		}
		else{
			condOrganization = new Organization();
			condOrganization.setParentId(Long.parseLong(parentId));
			return super.getCommonDao().selectList("organization.select", condOrganization);
		}
	}
	
	/**
	 * 机构列表树
	 * @param nodeid
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> getTree(String nodeid){
		List<Object> list = new ArrayList<Object>();
		List<Organization> resultList = null;
		Organization condOrganization = new Organization();
		if (nodeid != null && nodeid.length()>0){
			condOrganization = new Organization();
			condOrganization.setParentId(Long.parseLong(nodeid));
			resultList = super.getCommonDao().selectList("organization.select", condOrganization);
		}else{
			condOrganization = new Organization();
			condOrganization.setParentId(Long.parseLong("0"));
			resultList = super.getCommonDao().selectList("organization.select", condOrganization);
		}
		
		for (Organization organization : resultList){
			Map<String, Object> maporg = new Hashtable<String, Object>();
			List<Object> celllist = new ArrayList<Object>();
			celllist.add(organization.getChildNode());
			celllist.add(organization.getName());
			celllist.add(organization.getCode());
			celllist.add(null);
			//级别
			celllist.add(organization.getLevel());				
			//父节点
			celllist.add(organization.getParentId());
			if (organization.getChildNode() == 1){
				celllist.add(true);
			}
			else{
				celllist.add(false);
			}
			maporg.put("id", organization.getId());
			maporg.put("cell", celllist);
			list.add(maporg);
		}
		Map<String, Object> map = new Hashtable<String, Object>(); 
		map.put("rows", list);		
		return map;
	}
	
	/**
	 * 机构下拉列表树
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> getSelectTree(String id){
		List<Object> list = new ArrayList<Object>();
		List<Organization> resultList = null;
		Organization condOrganization = new Organization();
		if (id == null){
			condOrganization = new Organization();
			condOrganization.setParentId(0l);
			resultList = super.getCommonDao().selectList("organization.select", condOrganization);			
		}
		else{
			condOrganization = new Organization();
			condOrganization.setParentId(Long.parseLong(id));
			resultList = super.getCommonDao().selectList("organization.select", condOrganization);
		}
		if (resultList != null && resultList.size() > 0){
			for (Organization organization : resultList){
				Map<String, Object> maporg = new Hashtable<String, Object>(); 
				maporg.put("id", organization.getId());
				maporg.put("name", organization.getName());
				maporg.put("path", organization.getPath());
				maporg.put("olevel", organization.getLevel());
				maporg.put("childNode", organization.getChildNode());
				if (organization.getChildNode() == 1){
					maporg.put("isParent", "false");
				}
				else{
					maporg.put("isParent", "true");
				}
				list.add(maporg);
			}
		}
		Map<String, Object> map = new Hashtable<String, Object>(); 
		map.put("stree", list);		
		return map;
	}

	
	/**
	 *获取列表集合
	 */
	@Override
	@SuppressWarnings("unchecked")
	public Map<String, Object> getGrid(Organization entity) {
		//从request对象中获取页面信息
		int skip = entity.getRows() * entity.getPage() - entity.getRows();
		List<Object> list = new ArrayList<Object>();
		List<Organization> resultList = null;
		Organization condOrganization = new Organization();
		resultList = super.getCommonDao().selectPaginatedList("organization.select", condOrganization,skip,entity.getRows());
		for (Organization organization : resultList){
			Map<String, Object> maporg = new Hashtable<String, Object>();
			List<Object> celllist = new ArrayList<Object>();
			celllist.add(organization.getName());
			celllist.add(organization.getDescription());
			maporg.put("cell", celllist);
			list.add(maporg);
		}
		//记录数
		long record = 0;
		try {
//			record = getAllCount(entity);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		//页数
		int pageCount = (int)Math.ceil(record/(double) entity.getRows());
		Map<String, Object> map = new Hashtable<String, Object>(); 
		map.put("rows", list);	
		map.put("page", entity.getPage());
		map.put("total", pageCount);
		map.put("records", record);
		return map;
	}
	

	
	/**
	 *保存机构
	 */
	@Override
	public void save(Organization entity) throws Exception {
		super.getCommonDao().insert("organization.insert",entity);
	}


	/**
	 *更新机构
	 */
	@Override
	public void update(Organization entity) throws Exception {
		super.getCommonDao().update("organization.update", entity);
	}

	
	/**
	 *获取所有机构记录条数
	 */
	@Override
	public Long getAllRecordCount() throws Exception {
		return (Long) super.getCommonDao().selectObject("organization.count");
	}
	
	
	/**
	 *条件获取所有机构记录条数
	 */
	@Override
	public Long getRecordCount(Organization entity) throws Exception {
		return (Long) super.getCommonDao().selectObject("organization.count",entity);
	}


	/**
	 *所有机构列表
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Organization> getAll() throws Exception {
		return super.getCommonDao().selectList("organization.select");
	}

	/**
	 *条件查询机构列表
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Organization> getList(Organization entity) throws Exception {
		return super.getCommonDao().selectList("organization.select",entity);
	}
	
	@SuppressWarnings("unchecked")
	public List<Organization> selectOrganizationlist(Organization entity) throws Exception {
		if(entity.getLevel()==3){
			return super.getCommonDao().selectList("organization.selectThreeOrglist",entity);
		}else if(entity.getLevel()==4){
			return super.getCommonDao().selectList("organization.selectFourOrglist",entity);
		}else if(entity.getLevel()==5){
			return super.getCommonDao().selectList("organization.selectFiveOrglist",entity);
		}
		return null;
	}
	
	/**
	 *获取单个机构
	 */
	@Override
	public Organization getOneById(Organization entity) throws Exception {
		if(entity != null && entity.getId() != null)
			return (Organization) super.getCommonDao().selectObject("organization.select", entity);
		return null;
	}
	
	/**
	 *删除所有机构
	 */
	@Override
	public void deleteAll() throws Exception {
		super.getCommonDao().delete("organization.delete");
	}

	/**
	 *批量删除机构
	 */
	@Override
	public void deleteByIn(Organization entity) throws Exception {
		super.getCommonDao().delete("organization.deleteByIn");
	}

	@Override
	public void deleteByArr(Organization entity) throws Exception {
		Organization paramOrganization = new Organization();
		if (entity != null && entity.getIds() != null){
			for (String id : entity.getIds()){
				paramOrganization.setId(Long.parseLong(id));
				super.getCommonDao().delete("organization.delete",paramOrganization);
			}
		}
	}
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}


	@Override
	public String upload(BaseEntity baseEntity, HttpServletRequest request) {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		String realPath = request.getSession().getServletContext().getRealPath("/upload/");
		if (realPath == null){
			try {
				realPath = request.getSession().getServletContext().getResource("/").toString();
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}
		String filePathList = null;
		String realFileName = "";
		String tempPath = "";
		Iterator<String> iterator = multipartRequest.getFileNames();
		while(iterator.hasNext()){
			String fileName = iterator.next().toString();
			File nfile = new File(realPath + File.separator + tempPath,realFileName);
			//文件全名
			filePathList = File.separator + tempPath + File.separator + realFileName;
			List<MultipartFile> flist = multipartRequest.getFiles(fileName);
			for (MultipartFile mfile : flist) {
				byte[] bytes;
				try {
					bytes = mfile.getBytes();
					if (bytes.length != 0) {
						mfile.transferTo(nfile);
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
		return filePathList;
	}
	
	public Long getUserCount(Organization entity) throws Exception {
		Object obj = getCommonDao().selectObject("organization.selectUserCount",entity);
		if (obj instanceof Integer){
			return (Long)obj;
		}
		else if (obj instanceof Long){
			return (Long)obj;
		}
		return 0l;
	}
	
	@SuppressWarnings("unchecked")
	public List<Organization> selectOrglist(Organization entity) throws Exception {
		return super.getCommonDao().selectList("organization.selectOrglist",entity);
	}


	@SuppressWarnings("unchecked")
	public List<Organization> selectOrglistByRole(Organization org) {
		return super.getCommonDao().selectList("organization.selectOrglistByRole",org);
	}


	@SuppressWarnings("unchecked")
	public List<Organization> getOrgListByRole(Organization entity) throws Exception {
		entity.setLevel(2);
		return  (List<Organization>) super.getCommonDao().selectList("organization.selectOrglistByRole",entity);
	}
}