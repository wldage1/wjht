package com.sw.plugins.clientcenter.client.maintain.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sw.core.common.Constant;
import com.sw.core.controller.BaseController;
import com.sw.core.controller.CommonModelAndView;
import com.sw.core.data.entity.BaseEntity;
import com.sw.core.ucpaas.SMSRest;
import com.sw.core.util.CommonUtil;
import com.sw.plugins.clientcenter.client.maintain.entity.Client;
import com.sw.plugins.clientcenter.client.maintain.entity.Consume;
import com.sw.plugins.clientcenter.client.maintain.service.ClientService;
import com.sw.plugins.clientcenter.client.maintain.service.ConsumeService;
import com.sw.plugins.clientcenter.salon.feedback.entity.SalonFeedback;
import com.sw.plugins.usercenter.config.dynamic.entity.Dictionary;
import com.sw.plugins.usercenter.config.dynamic.service.DictionaryService;
import com.sw.plugins.usercenter.system.organization.entity.Organization;
import com.sw.plugins.usercenter.system.organization.service.OrganiztionService;
import com.sw.plugins.usercenter.system.user.entity.User;
import com.sw.plugins.usercenter.system.user.service.UserService;

/**
 * 类简要说明
 * 
 */
@Controller
public class ClientController extends BaseController {

	private static Logger logger = Logger.getLogger(ClientController.class);

	@Resource
	private ClientService clientService;
	@Resource
	private DictionaryService dictionaryService;
	@Resource
	private UserService userService;
	@Resource
    private OrganiztionService organiztionService;
	@Resource
	private ConsumeService consumeService;
	
	/**
	 * 跳转到列表页
	 * 
	 * @param client
	 * @param request
	 * @return
	 * @version 1.0
	 */
	@RequestMapping("/clientcenter/client/maintain/list")
	public CommonModelAndView list(Client client, HttpServletRequest request, Map<String, Object> model) {
		Object obj = new CommonModelAndView().getCurrentStatus(client, request);
		if (obj != null) {
			if (obj instanceof Client) {
				client = (Client) obj;
			}
		}
		CommonModelAndView commonModelAndView = new CommonModelAndView(request, client);
		try {
			Dictionary dictionary = new Dictionary();
			dictionary.setDictSortValue(Constant.DICT_TYPE_GENDER);
			List<Dictionary> sexList = dictionaryService.getList(dictionary);
			commonModelAndView.addObject("sexList", sexList);
			List<User> userList = userService.selectUserList(null);
			commonModelAndView.addObject("userList", userList);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		commonModelAndView.addObject("code", client.getC());
		model.put("client", client);
		return commonModelAndView;
	}

	/**
	 * 列表json
	 * 
	 * @param client
	 * @param request
	 * @return
	 * @version 1.0
	 */
	@RequestMapping("/clientcenter/client/maintain/grid")
	public CommonModelAndView json(Client client, HttpServletRequest request) {
		Map<String, Object> map = clientService.getGrid(client);
		return new CommonModelAndView("jsonView", map, client, request);
	}

	@RequestMapping("/clientcenter/client/maintain/orgMap")
	public Map orgMap(Client client, HttpServletRequest request) {
		String viewName = null;		
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Organization org = new Organization();
			org.setLevel(2);
			List<Organization> area = organiztionService.selectOrglist(org);
			map.put("area", area);
			org.setLevel(3);
			List<Organization> province = organiztionService.selectOrganizationlist(org);
			map.put("province", province);
			org.setLevel(4);
			List<Organization> city = organiztionService.selectOrganizationlist(org);
			map.put("city", city);
			org.setLevel(5);
			List<Organization> district = organiztionService.selectOrganizationlist(org);
			map.put("district", district);
			viewName = this.SUCCESS;
		} catch (Exception e) {
			viewName = this.ERROR;
			logger.error(e.getMessage());
		}
		return map;
	}

	/**
	 * 保存修改方法
	 * 
	 * @param member
	 * @param result
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/clientcenter/client/maintain/save", method = RequestMethod.POST)
	public CommonModelAndView save(@Valid Client client, BindingResult result, Map<String, Object> model) {
		// 实体bean验证
		boolean resultErrorFlag = result.hasErrors();
		String action = client.getAction();
		//两次卡号是否一致验证
		boolean cardNumErrorFlag = false;
		if(action.equals("sendcard")){
			if(!client.getCardNum().equals(client.getConfirmCardNum())){
				cardNumErrorFlag = true;
				result.rejectValue("cardNum", "already.client.cardNum");
			}else if(CommonUtil.isEmpty(client.getCardNum())  || CommonUtil.isEmpty(client.getConfirmCardNum())){
				cardNumErrorFlag = true;
				result.rejectValue("cardNum", "NotNull.client.cardNum");
			}
		}else{
			if(CommonUtil.isEmpty(client.getName())){
				resultErrorFlag = true;
				result.rejectValue("name", "NotEmpty.client.name");
			}
			if(CommonUtil.isEmpty(client.getPhone())){
				resultErrorFlag = true;
				result.rejectValue("phone", "Pattern.client.phone");
			}else{
				Client c = new Client();
				c.setCreator("0");
				c.setPhone(client.getPhone());
				try {
					Long count = clientService.getRecordCount(c);
					if(action.equals("create")){
						if(count >= 1){
							resultErrorFlag = true;
							result.rejectValue("phone", "already.client.mobilePhone");
						}
					}else if(action.equals("modify")){
						if(count > 1){
							resultErrorFlag = true;
							result.rejectValue("phone", "already.client.mobilePhone");
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (CommonUtil.isEmpty(client.getIdCard())) {
				resultErrorFlag = true;
				result.rejectValue("idCard", "NotEmpty.client.idCard");
			}
			if (CommonUtil.isEmpty(client.getEmail())) {
				resultErrorFlag = true;
				result.rejectValue("email", "NotEmpty.client.email");
			}
			if (CommonUtil.isEmpty(client.getBankName())) {
				resultErrorFlag = true;
				result.rejectValue("bankName", "NotEmpty.client.bankName");
			}
			if (CommonUtil.isEmpty(client.getBankNum())) {
				resultErrorFlag = true;
				result.rejectValue("bankNum", "NotEmpty.client.bankNum");
			}
		}
		
		try {
			Client tempClient = new Client();
			// 判断是新增还是修改
			if (client.getId() != null) {
				tempClient.setId(client.getId());
			}
			tempClient = new Client();
			if (resultErrorFlag || cardNumErrorFlag) {
				CommonModelAndView commonModelAndView = new CommonModelAndView(client);
				try {
					Dictionary dictionary = new Dictionary();
					dictionary.setDictSortValue(Constant.DICT_TYPE_LEVEL);
					List<Dictionary> levelList = dictionaryService.getList(dictionary);
					commonModelAndView.addObject("levelList", levelList);
					dictionary.setDictSortValue(Constant.DICT_TYPE_GENDER);
					List<Dictionary> genderList = dictionaryService.getList(dictionary);
					commonModelAndView.addObject("genderList", genderList);
					List<User> userList = userService.selectUserList(null);
					commonModelAndView.addObject("userList", userList);
						
					model.put("client", client);
					return commonModelAndView;
				} catch (Exception e) {
					logger.error(e.getMessage());
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		String viewName = null;
		try {
			if (client.getId() == null) {
				clientService.save(client);
				if(CommonUtil.isNotEmpty(client.getPhone()) && CommonUtil.isNotEmpty(client.getName())){
					//尊敬的用户{1}，您在我公司申请的购物卡，我公司已受理，客服会在24小时内与您联系。
					SMSRest.sendSms("22039", client.getPhone(), client.getName());
				}
			} else {
				clientService.update(client);
				Client c = clientService.getOneById(client);
				client.setName(c.getName());
				if(action.equals("sendcard") && CommonUtil.isNotEmpty(c.getPhone())){
					//尊敬的用户{1}，您的消费卡授信额度为300000元，卡片7日内将会寄出，请您注意查收并激活，感谢你对本公司的大力支持。
					SMSRest.sendSms("22040", c.getPhone(), c.getName());
				}
			}
			viewName = this.SUCCESS;

		} catch (Exception e) {
			logger.error(e.getMessage());
			viewName = this.ERROR;
		}
		CommonModelAndView commonModelAndView = new CommonModelAndView(viewName, client, messageSource);
		return commonModelAndView;
	}

	/**
	 * 跳转到创建页面
	 * 
	 * @param member
	 * @param request
	 * @return
	 * @version 1.0
	 */
	@RequestMapping("/clientcenter/client/maintain/create")
	public CommonModelAndView create(Client client, HttpServletRequest request) {
		CommonModelAndView commonModelAndView = new CommonModelAndView(request, client);
		commonModelAndView.addObject("client", client);
		try {
			Dictionary dictionary = new Dictionary();
			dictionary.setDictSortValue(Constant.DICT_TYPE_LEVEL);
			List<Dictionary> levelList = dictionaryService.getList(dictionary);
			commonModelAndView.addObject("levelList", levelList);
			dictionary.setDictSortValue(Constant.DICT_TYPE_GENDER);
			List<Dictionary> genderList = dictionaryService.getList(dictionary);
			commonModelAndView.addObject("genderList", genderList);
			List<User> userList = userService.selectUserList(null);
			commonModelAndView.addObject("userList", userList);
			
			User u = new User();
			u.setId(Long.valueOf(client.getCreator()));
			User user = userService.getRoleByUid(u);
			if(user != null && user.getRoleName().contains("代理")){
				commonModelAndView.addObject("roleInfo", "1");
				commonModelAndView.addObject("userid", client.getCreator());
			}else{
				commonModelAndView.addObject("roleInfo", "2");
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return commonModelAndView;
	}

	/**
	 * 跳转到修改页面
	 * 
	 * @param member
	 * @param request
	 * @return
	 * @version 1.0
	 */
	@RequestMapping("/clientcenter/client/maintain/modify")
	public CommonModelAndView modify(Client client, HttpServletRequest request, Map<String, Object> model) {
		String code = client.getC();
		if (client.getId() != null) {
			try {
				Client m = new Client();
				m.setId(client.getId());
				client = clientService.getOneById(m);
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		}
		client.setC(code);
		CommonModelAndView commonModelAndView = new CommonModelAndView(request, client);
		try {
			Dictionary dictionary = new Dictionary();
			dictionary.setDictSortValue(Constant.DICT_TYPE_LEVEL);
			List<Dictionary> levelList = dictionaryService.getList(dictionary);
			commonModelAndView.addObject("levelList", levelList);
			dictionary.setDictSortValue(Constant.DICT_TYPE_GENDER);
			List<Dictionary> genderList = dictionaryService.getList(dictionary);
			commonModelAndView.addObject("genderList", genderList);
			List<User> userList = userService.selectUserList(null);
			commonModelAndView.addObject("userList", userList);
			
			User u = new User();
			u.setId(Long.valueOf(client.getCreator()));
			User user = userService.getRoleByUid(u);
			if(user != null && user.getRoleName().contains("代理")){
				commonModelAndView.addObject("roleInfo", "1");
				commonModelAndView.addObject("userid", client.getCreator());
			}else{
				commonModelAndView.addObject("roleInfo", "2");
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		model.put("client", client);
		return commonModelAndView;
	}

	/**
	 * 审核
	 * @author wang.l
	 * @date 2016年3月15日
	 * @return Map<String,Object>
	 */
	@RequestMapping("/clientcenter/client/maintain/check")
	public CommonModelAndView check(Client client, HttpServletRequest request) {
		String viewName = null;
		try {
			if (client != null && client.getId() != null) {
				clientService.update(client);
				viewName = this.SUCCESS;
			}
		} catch (Exception e) {
			viewName = this.ERROR;
			logger.error(e.getMessage());
		}
		CommonModelAndView commonModelAndView = new CommonModelAndView(viewName, client, messageSource);
		return commonModelAndView;
	}
	
	/**
	 * 卡拒绝
	 * @author wang.l
	 * @date 2016年3月15日
	 * @return Map<String,Object>
	 */
	@RequestMapping("/clientcenter/client/maintain/refuse")
	public CommonModelAndView refuse(Client client, HttpServletRequest request) {
		String viewName = null;
		try {
			if (client != null && client.getId() != null) {
				clientService.update(client);
				
				Client c = clientService.getOneById(client);
				if(CommonUtil.isNotEmpty(c.getCardNum()) && CommonUtil.isNotEmpty(c.getPhone())){
					//尊敬的用户{1}，如有问题请与我公司联系。
					String cardNum = c.getCardNum().substring(c.getCardNum().length()-4);
					String content = c.getName() + "，您的卡尾号为"+ cardNum +"的消费卡已被我公司冻结";
					SMSRest.sendSms("23985", c.getPhone(), content);
				}
				
				viewName = this.SUCCESS;
			}
		} catch (Exception e) {
			viewName = this.ERROR;
			logger.error(e.getMessage());
		}
		CommonModelAndView commonModelAndView = new CommonModelAndView(viewName, client, messageSource);
		return commonModelAndView;
	}
	
	
	/**
	 * 卡激活
	 * @author wang.l
	 * @date 2016年3月15日
	 * @return Map<String,Object>
	 */
	@RequestMapping("/clientcenter/client/maintain/activation")
	public CommonModelAndView activation(Client client, HttpServletRequest request) {
		String viewName = null;
		try {
			if (client != null && client.getId() != null) {
				clientService.update(client);
				Client c = clientService.getOneById(client);
				if(CommonUtil.isNotEmpty(c.getCardNum()) && CommonUtil.isNotEmpty(c.getPhone())){
					//尊敬的用户{1}，您的卡号位数为{2}的消费卡现已激活，可用额度为300000元，此卡暂无提现额度，激活后可用马上购物。
					//尊敬的用户{1}您可以马上购物。
					String cardNum = c.getCardNum().substring(c.getCardNum().length()-4);
					String content = c.getName() + "，您的卡号位数为"+ cardNum +"的消费卡现已激活，可用额度为300000元，此卡暂无提现额度，激活后";
					SMSRest.sendSms("22041", c.getPhone(), content);
				}
				viewName = this.SUCCESS;
			}
		} catch (Exception e) {
			viewName = this.ERROR;
			logger.error(e.getMessage());
		}
		CommonModelAndView commonModelAndView = new CommonModelAndView(viewName, client, messageSource);
		return commonModelAndView;
	}
	
	
	/**
	 * 发卡
	 * @author wang.l
	 * @date 2016年3月15日
	 * @return Map<String,Object>
	 */
	@RequestMapping("/clientcenter/client/maintain/sendcard")
	public CommonModelAndView sendcard(Client client, HttpServletRequest request) {
		CommonModelAndView commonModelAndView = new CommonModelAndView(request, client);
		try {
			Client c = clientService.getOneById(client);
			client.setName(c.getName());
			if(CommonUtil.isEmpty(client.getCardType())){
				client.setCardType("0");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		commonModelAndView.addObject("client", client);
		return commonModelAndView;
	}
	
	@RequestMapping("/clientcenter/client/maintain/delete")
	public CommonModelAndView delete(Client client, HttpServletRequest request) {
		String viewName = null;
		try {
			if (client != null && client.getId() != null) {
				clientService.delete(client);
			} else if (client != null && client.getIds() != null) {
				clientService.deleteByArr(client);
			}
			viewName = this.SUCCESS;
		} catch (Exception e) {
			viewName = this.ERROR;
			logger.error(e.getMessage());
		}
		CommonModelAndView commonModelAndView = new CommonModelAndView(viewName, client, messageSource);
		return commonModelAndView;
	}

	@RequestMapping("/clientcenter/client/maintain/detail")
	public CommonModelAndView detail(Client client, HttpServletRequest request, Map<String, Object> model) {
		String code = client.getC();
		if (client.getId() != null) {
			try {
				Client m = new Client();
				m.setId(client.getId());
				client = clientService.getOneById(m);
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		}
		client.setC(code);
		CommonModelAndView commonModelAndView = new CommonModelAndView(request, client);
		try {
			Dictionary dictionary = new Dictionary();
			dictionary.setDictSortValue(Constant.DICT_TYPE_LEVEL);
			List<Dictionary> levelList = dictionaryService.getList(dictionary);
			commonModelAndView.addObject("levelList", levelList);
			dictionary.setDictSortValue(Constant.DICT_TYPE_GENDER);
			List<Dictionary> genderList = dictionaryService.getList(dictionary);
			commonModelAndView.addObject("genderList", genderList);
			List<User> userList = userService.selectUserList(null);
			commonModelAndView.addObject("userList", userList);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		model.put("client", client);
		return commonModelAndView;
	
	}

	/**
	 * 消费
	 * @author wang.l
	 * @date 2016年4月15日
	 * @return Map<String,Object>
	 */
	@RequestMapping("/clientcenter/client/maintain/consume")
	public CommonModelAndView consume(Consume consume, HttpServletRequest request) {
		Object obj = new CommonModelAndView().getCurrentStatus(consume, request);
		if (obj != null){
			if (obj instanceof SalonFeedback){
				consume = (Consume)obj;
			}
		} 
		CommonModelAndView commonModelAndView = new CommonModelAndView(request, consume);
		commonModelAndView.addObject("code", consume.getC());
		commonModelAndView.addObject("consume", consume);
		return commonModelAndView;
	}
	
	/**
	 * 列表json
	 * 
	 * @param client
	 * @param request
	 * @return
	 * @version 1.0
	 */
	@RequestMapping("/clientcenter/client/maintain/consumeGrid")
	public CommonModelAndView consumeGrid(Consume consume, HttpServletRequest request) {
		Map<String, Object> map = clientService.consumeGrid(consume);
		return new CommonModelAndView("jsonView", map, consume, request);
	}

	@RequestMapping("/clientcenter/client/maintain/delete_consume")
	public CommonModelAndView del(Consume consume, HttpServletRequest request) {
		String viewName = null;
		try {
			if (consume != null && consume.getId() != null) {
				consumeService.delete(consume);
			}
			viewName = this.SUCCESS;
		} catch (Exception e) {
			viewName = this.ERROR;
			logger.error(e.getMessage());
		}
		CommonModelAndView commonModelAndView = new CommonModelAndView(viewName, consume, messageSource);
		return commonModelAndView;
	}
	
	@RequestMapping("/clientcenter/client/maintain/carete_consume")
	public CommonModelAndView add(Consume consume, HttpServletRequest request) {
		CommonModelAndView commonModelAndView = new CommonModelAndView(request, consume);
		commonModelAndView.addObject("consume", consume);
		return commonModelAndView;
	}
	
	/**
	 * 保存修改方法
	 * 
	 * @param member
	 * @param result
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/clientcenter/client/maintain/saveConsume", method = RequestMethod.POST)
	public CommonModelAndView saveConsume(@Valid Consume consume, BindingResult result, Map<String, Object> model) {
		//两次卡号是否一致验证
		boolean cardNumErrorFlag = false;
		if(CommonUtil.isEmpty(consume.getMoney())){
			cardNumErrorFlag = true;
			result.rejectValue("money", "NotNull.client.money");
		}
		if(CommonUtil.isEmpty(consume.getDescription())){
			cardNumErrorFlag = true;
			result.rejectValue("description", "NotNull.client.description");
		}
		try {
			if (cardNumErrorFlag) {
				CommonModelAndView commonModelAndView = new CommonModelAndView(consume);
				try {
					model.put("consume", consume);
					return commonModelAndView;
				} catch (Exception e) {
					logger.error(e.getMessage());
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		String viewName = null;
		try {
			consumeService.save(consume);
			
			//获取客户信息
			Client client = new Client();
			client.setId(Long.valueOf(consume.getClientId()));
			client = clientService.getOneById(client);
			//尊敬的用户{1}，您的卡号位数为{2}的消费卡购买产品（{3}价值{4}元，余额{5}元）已得到商城确认，商品会在7-10个工作日寄出。
			//尊敬的用户{1}已得到商城确认，商品会在7-10个工作日寄出。
			String cardNum = client.getCardNum().substring(client.getCardNum().length()-4);
			Consume cm = consumeService.getMoneyTotal(consume);
			if(cm != null){
				int money = 300000 - Integer.parseInt(cm.getTotal());
				String content = "您的卡号位数为"+ cardNum +"的消费卡购买产品（"+ consume.getDescription() +"价值"+consume.getMoney()+"元，余额"+ money +"元）";
				SMSRest.sendSms("22042", client.getPhone(), content);
				Client c = new Client();
				c.setId(Long.valueOf(consume.getClientId()));
				c.setCredit(money+"");
				clientService.update(c);
			}
			
			viewName = this.SUCCESS;

		} catch (Exception e) {
			logger.error(e.getMessage());
			viewName = this.ERROR;
		}
		CommonModelAndView commonModelAndView = new CommonModelAndView(viewName, consume, messageSource);
		return commonModelAndView;
	}
	
	/**
	 * 获取下拉列表树信息
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping("/clientcenter/client/maintain/stree")
	public CommonModelAndView stree(String id,HttpServletRequest request){
		Map<String, Object> map = organiztionService.getSelectTree(id); 
		return new CommonModelAndView("jsonView",map); 		
	}
	@Override
	public String uploadfile(BaseEntity baseEntity, HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

}
