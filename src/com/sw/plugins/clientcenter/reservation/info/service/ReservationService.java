package com.sw.plugins.clientcenter.reservation.info.service;

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
import com.sw.plugins.clientcenter.reservation.info.entity.Reservation;
import com.sw.plugins.productcenter.product.maintain.entity.Product;
import com.sw.plugins.productcenter.product.maintain.service.ProductService;
import com.sw.plugins.usercenter.config.dynamic.entity.Dictionary;
import com.sw.plugins.usercenter.config.dynamic.service.DictionaryService;

@Service
public class ReservationService extends CommonService<Reservation> {

	@Resource
	private MemberService memberService;
	@Resource
	private DictionaryService dictionaryService;
	@Resource
	private ProductService productService;
	
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> getGrid(Reservation entity) {
		List<Object> list = new ArrayList<Object>();
		List<Reservation> resultList = null;
		resultList = getCommonDao().selectList("reservation.selectPaginated", entity); 
		Reservation res = null;
		for (Reservation reservation : resultList) {
			Map<String, Object> maprole = new Hashtable<String, Object>();
			List<Object> celllist = new ArrayList<Object>();
//			String disposeState = "";
//			if(reservation.getDisposeState() == 0){
//				disposeState = Constant.RESERVATION_DISPOSE_STATE_NO;
//			}
//			else{
//				disposeState = Constant.RESERVATION_DISPOSE_STATE_YES;
//			}
			String createTime = com.sw.core.util.DateUtil.dateToStringWithTime(reservation.getCreateTime());
			res = getMemberById(reservation);
			celllist.add(res.getName());
			celllist.add(res.getSexName());
			celllist.add(res.getClientGrade());
			celllist.add(reservation.getDisposeState());
			celllist.add(createTime);
			maprole.put("id", reservation.getId());
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

	@Override
	public void save(Reservation entity) throws Exception {
		super.getCommonDao().save("reservation.insert",entity);
	}

	@Override
	public void update(Reservation entity) throws Exception {
		super.getCommonDao().update("reservation.update", entity);
	}

	@Override
	public Long getAllRecordCount() throws Exception {
		return (Long)super.getCommonDao().selectObject("reservation.selectCount");
	}

	@Override
	public Long getRecordCount(Reservation entity) throws Exception {
		return (Long)super.getCommonDao().selectObject("reservation.selectCount", entity);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Reservation> getAll() throws Exception {
		return super.getCommonDao().selectList("reservation.select");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Reservation> getList(Reservation entity) throws Exception {
		return super.getCommonDao().selectList("reservation.select", entity);
	}
	
	@Override
	public void delete(Reservation entity) {
		super.getCommonDao().delete("reservation.delete", entity);
	}
	
	/**
	 *根据Id查询需要修改的数据
	 */
	@Override
	public Reservation getOneById(Reservation entity) throws Exception {
		if(entity == null)
			return null;
		Reservation reservation = (Reservation)super.getCommonDao().selectObject("reservation.select", entity);
		if(reservation != null){
			reservation.setDisposeState(reservation.getDisposeState());
			reservation.setProductName(getProductNameById(reservation.getProductId()));
			return getMemberById(reservation);
		}
		else{
			return null;
		}
	}
	
	@Override
	public void deleteAll() throws Exception {
		super.getCommonDao().delete("reservation.delete");
	}

	@Override
	public void deleteByIn(Reservation entity) throws Exception {
		super.getCommonDao().delete("reservation.deleteByIn");
	}

	@Override
	public void deleteByArr(Reservation entity) throws Exception {
		//假删除与还原
		if (entity != null && entity.getIds() != null && entity.getDelState() != null){
			for (String id : entity.getIds()){
				entity.setId(Long.parseLong(id));
				super.getCommonDao().delete("reservation.deleteReservation",entity);
			}
		}
		else{
			for (String id : entity.getIds()){
				entity.setId(Long.parseLong(id));
				delete(entity);
			}
		}
	}
	
	public String getProductNameById(Long prdId) throws Exception{
		Product prd = new Product();
		prd.setPrdId(prdId);
		Product product = productService.getOneById(prd);
		return product.getPrdName();
	}
	
	/**
	 *  根据会员ID查询数据并格式化
	 *  @return Reservation
	 *  @author Administrator
	 *  @version 1.0
	 *  </pre>
	 *  Created on :2012-5-7 下午6:41:31
	 *  LastModified:
	 *  History:
	 *  </pre>
	 */
	public Reservation getMemberById(Reservation reservation){
		Dictionary dictionary = new Dictionary();
		dictionary.setDictSortValue(Constant.DICT_TYPE_GENDER);
		Map<Long,Dictionary> genderMap = dictionaryService.getDictMap(dictionary);
		dictionary.setDictSortValue(Constant.DICT_TYPE_LEVEL);
		Map<Long,Dictionary> levelMap = dictionaryService.getDictMap(dictionary);
		Member MemberOne = new Member();
		MemberOne.setId(reservation.getMemberId());
		try{
			Member member = memberService.getOneById(MemberOne);
			reservation.setName(member.getMemberName());
			reservation.setMobilePhone(member.getMobilePhone());
			if(member.getSex()!=null && genderMap.get(member.getSex())!=null){
				reservation.setSexName(genderMap.get(member.getSex()).getName());
			}else{
				reservation.setSexName("");
			}
			if(member.getLevel()!=null && levelMap.get(member.getLevel())!=null){
				reservation.setClientGrade(levelMap.get(member.getLevel()).getName());
			}else{
				reservation.setClientGrade("");
			}
		}catch (Exception e) {
			DetailException.expDetail(e, ReservationService.class);
			e.printStackTrace();
		}
		return reservation;
	}

	@Override
	public String upload(BaseEntity baseEntity, HttpServletRequest request) {
		return null;
	}
	
}
