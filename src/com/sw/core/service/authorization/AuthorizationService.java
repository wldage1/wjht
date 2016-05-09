package com.sw.core.service.authorization;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.sw.core.data.entity.Authorization;
import com.sw.core.data.entity.BaseEntity;
import com.sw.core.service.CommonService;

/**
 * Service实现类 - Service实现类基类
 */

@Service
public class AuthorizationService extends CommonService<Authorization>{
	
	public AuthorizationService() {
	}
	/**
	 * 根据权限级别进行权限信息查询
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Authorization> getAuthorizationByLevel(String level){
		Authorization authorization = new Authorization();
		authorization.setLevel(level);
		return super.getCommonDao().selectList("authorization.select", authorization);
	}
	
	/**
	 * 查询所有权限
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Authorization> getAllAuthorization(){
		return super.getCommonDao().selectList("authorization.select");
	}
	
	/**
	 * 根据code查询权限信息
	 * @return
	 * @author
	 */
	public Authorization getAuthorizationByCode(String code){
		Authorization authorization = new Authorization();
		authorization.setCode(code);
		return (Authorization) super.getCommonDao().selectObject("authorization.select", authorization);
	}
	
	/**
	 * 查询控制器信息
	 * @param code
	 * @return
	 */
	public String getController(String code){
		Authorization authorization = new Authorization();
		authorization.setCode(code);
		authorization= (Authorization) super.getCommonDao().selectObject("authorization.select",authorization);
		return authorization.getController();		
	}
	
	/**
	 * 根据权限代码查询子权限
	 * @param code
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Authorization> getSubAuthorizationByCode(String code){
		Authorization authorization = new Authorization();
		authorization.setParentCode(code);
		return super.getCommonDao().selectList("authorization.select", authorization);
	}
	@Override
	public Map<String, Object> getGrid(Authorization entity) {
		return null;
	}
	@Override
	public void save(Authorization entity) throws Exception {
		
	}
	@Override
	public void update(Authorization entity) throws Exception {
		
	}

	@Override
	public List<? extends BaseEntity> getAll() throws Exception {
		return null;
	}
	@Override
	public void delete(Authorization entity) {
		
	}
	@Override
	public Authorization getOneById(Authorization entity) throws Exception {
		return null;
	}
	@Override
	public Long getRecordCount(Authorization entity) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Long getAllRecordCount() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<? extends BaseEntity> getList(Authorization entity) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void deleteAll() throws Exception {
		super.getCommonDao().delete("authorization.delete");
	}

	@Override
	public void deleteByIn(Authorization entity) throws Exception {
		super.getCommonDao().delete("authorization.deleteByIn");
	}

	@Override
	public void deleteByArr(Authorization entity) throws Exception {
		Authorization paramAuthorization = new Authorization();
		if (entity != null && entity.getIds() != null){
			for (String id : entity.getIds()){
				paramAuthorization.setId(Long.parseLong(id));
				super.getCommonDao().delete("authorization.delete",paramAuthorization);
			}
		}
	}
	@Override
	public String upload(BaseEntity baseEntity, HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}	
}