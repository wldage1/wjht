package com.sw.plugins.clientcenter.requirement.investment.service;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.sw.core.common.Constant;
import com.sw.core.data.entity.BaseEntity;
import com.sw.core.exception.DetailException;
import com.sw.core.service.CommonService;
import com.sw.plugins.clientcenter.member.maintain.entity.Member;
import com.sw.plugins.clientcenter.member.maintain.service.MemberService;
import com.sw.plugins.clientcenter.requirement.investment.entity.Investment;
import com.sw.plugins.clientcenter.requirement.productsort.entity.RequirementProductSort;
import com.sw.plugins.clientcenter.requirement.productsort.service.RequirementProductSortService;
import com.sw.plugins.usercenter.config.dynamic.entity.Dictionary;
import com.sw.plugins.usercenter.config.dynamic.service.DictionaryService;

@Service
public class InvestmentService extends CommonService<Investment> {
	
	@Resource
	private MemberService memberService;
	@Resource
	private RequirementProductSortService requirementProductSortService;
	@Resource
	private DictionaryService dictionaryService;
	
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> getGrid(Investment entity) {
		List<Object> list = new ArrayList<Object>();
		List<Investment> resultList = null;
		resultList = getCommonDao().selectList("investment.selectPaginated", entity); 
		Investment invest = new Investment();
		for (Investment investment : resultList) {
			Map<String, Object> maprole = new Hashtable<String, Object>();
			List<Object> celllist = new ArrayList<Object>();
			invest = getMemberById(investment);
			RequirementProductSort requirementProductSort = new RequirementProductSort();
			requirementProductSort.setId(invest.getProductId());
			//投资需求产品类型
			try {
				requirementProductSort = requirementProductSortService.getOneById(requirementProductSort);
			} catch (Exception e) {
				e.printStackTrace();
			}
			celllist.add(invest.getName());
			celllist.add(invest.getGender());
			celllist.add(invest.getMobilePhone());
			celllist.add(investment.getInvestScale());
			celllist.add(requirementProductSort.getName());
			maprole.put("id", investment.getId());
			maprole.put("cell", celllist);
			list.add(maprole);
		}
		
		// 记录数
		long record = 0;
		try {
			record = getRecordCount(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// 页数
		int pageCount = (int) Math.ceil(record / (double) entity.getRows());
		Map<String, Object> map = new Hashtable<String, Object>();
		map.put("rows", list);
		map.put("page", entity.getPage());
		map.put("total", pageCount);
		map.put("records", record);
		return map;
	}
	
	/**
	 *保存投资需求数据
	 */
	@Override
	public void save(Investment entity) throws Exception {
		super.getCommonDao().save("investment.insert", entity);
	}

	/**
	 *修改投资需求数据
	 */
	@Override
	public void update(Investment entity) throws Exception {
		super.getCommonDao().update("investment.update", entity);
	}


	@Override
	public Long getAllRecordCount() throws Exception {
		return (Long)super.getCommonDao().selectObject("investment.selectCount");
	}

	@Override
	public Long getRecordCount(Investment entity) throws Exception {
		return (Long)super.getCommonDao().selectObject("investment.selectCount", entity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Investment> getAll() throws Exception {
		return super.getCommonDao().selectList("investment.selectCount");
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Investment> getList(Investment entity) throws Exception {
		return super.getCommonDao().selectList("investment.selectCount", entity);
	}
	
	@Override
	public void delete(Investment entity) {
		super.getCommonDao().delete("investment.delete", entity);
	}

	/**
	 *根据Id查询需要修改的数据
	 */
	@Override
	public Investment getOneById(Investment entity) throws Exception {
		if(entity == null)
			return null;
		Investment investment = (Investment)super.getCommonDao().selectObject("investment.select", entity);
		investment = getMemberById(investment);
		investment.setCreateTimeView(com.sw.core.util.DateUtil.dateToStringWithTime(investment.getCreateTime()));
		return getDictNames(investment);
	}


	@Override
	public void deleteAll() throws Exception {
		super.getCommonDao().delete("investment.delete");
	}

	@Override
	public void deleteByIn(Investment entity) throws Exception {
		super.getCommonDao().delete("investment.deleteByIn");
	}

	@Override
	public void deleteByArr(Investment entity) throws Exception {
//		Investment paramInvestment = new Investment();
		//假删除与还原
		if (entity != null && entity.getIds() != null && entity.getState() != null){
			for (String id : entity.getIds()){
				entity.setId(Long.parseLong(id));
				super.getCommonDao().delete("investment.deleteinvestment",entity);
			}
		}else{
			for (String id : entity.getIds()){
				entity.setId(Long.parseLong(id));
				delete(entity);
			}
		}
	}
	
	/**
	 *  根据会员ID查询数据并格式化数据
	 *  @param investment
	 *  @return
	 *  @author Administrator
	 *  @version 1.0
	 *  </pre>
	 *  Created on :2012-5-9 下午5:57:31
	 *  LastModified:
	 *  History:
	 *  </pre>
	 */
	public Investment getMemberById(Investment investment){
		Dictionary dictionary = new Dictionary();
		dictionary.setDictSortValue(Constant.DICT_TYPE_GENDER);
		Map<Long,Dictionary> genderMap = dictionaryService.getDictMap(dictionary);
		Member MemberOne = new Member();
		MemberOne.setId(investment.getMemberId());
		try{
			Member member = memberService.getOneById(MemberOne);
			investment.setName(member.getMemberName());
			investment.setMobilePhone(member.getMobilePhone());
			if(member.getSex()!=null && genderMap.get(member.getSex())!=null){
				investment.setGender(genderMap.get(member.getSex()).getName());
			}else{
				investment.setGender("");
			}
		}catch (Exception e) {
			DetailException.expDetail(e, InvestmentService.class);
			e.printStackTrace();
		}
		return investment;
	}
	
	/**
	 *  根据字典类型取字段值
	 *  @param investment
	 *  @return
	 *  @author Administrator
	 *  @version 1.0
	 *  </pre>
	 *  Created on :2012-5-9 下午5:57:14
	 *  LastModified:
	 *  History:
	 *  </pre>
	 */
	public Investment getDictNames(Investment investment){
		Dictionary dictionary = new Dictionary();
		//投资期限
		dictionary.setDictSortValue(Constant.DICT_TYPE_INVESDEADLINE);
		Map<Long,Dictionary> invesDeadlineMap = dictionaryService.getDictMap(dictionary);
		//预期收益率
		dictionary.setDictSortValue(Constant.DICT_TYPE_YIELD);
		Map<Long,Dictionary> yieldMap = dictionaryService.getDictMap(dictionary);
		//预计投资时间	
		dictionary.setDictSortValue(Constant.DICT_TYPE_PREDICTTIME);
		Map<Long,Dictionary> predictTimeMap = dictionaryService.getDictMap(dictionary);
		//投资需求产品类型
		RequirementProductSort requirementProductSort = new RequirementProductSort();
		requirementProductSort.setId(investment.getProductId());
		try {
			requirementProductSort = requirementProductSortService.getOneById(requirementProductSort);
		} catch (Exception e) {
			e.printStackTrace();
		}
		investment.setProductName(requirementProductSort.getName());
		
		investment.setInvesDeadlineName(invesDeadlineMap.get(investment.getInvestDeadline()).getName());
		investment.setYieldName(yieldMap.get(investment.getYield()).getName());
		investment.setPredictTimeName(predictTimeMap.get(investment.getPredictTime()).getName());
//		investment.setProductName(productTypeMap.get(investment.getProductId()).getName());
		return investment;
	}
	
	@Override
	public String upload(BaseEntity baseEntity, HttpServletRequest request) {
		return null;
	}
	
}
