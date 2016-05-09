/**
 * rwoms Copyright 2010 SINOWEL, Co.ltd .
 * All rights reserved.
 * Package:  com.sw.plugins.survey.question.maintain.service
 * FileName: QuestionRadioService.java
 * @version 1.0
 * @author zhaofeng
 * @created on 2012-5-23
 * @last Modified 
 * @history
 */
package com.sw.plugins.survey.question.maintain.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.sw.core.data.entity.BaseEntity;
import com.sw.core.exception.DetailException;
import com.sw.core.service.CommonService;
import com.sw.plugins.survey.question.maintain.entity.Question;
import com.sw.plugins.survey.question.maintain.entity.QuestionOption;

/**
 *  问题选项service
 *  @author zhaofeng
 *  @version 1.0
 *  </pre>
 *  Created on :下午1:55:26
 *  LastModified:
 *  History:
 *  </pre>
 */
@Service
public class QuestionOptionService extends CommonService<QuestionOption>{

	@Override
	public void save(QuestionOption entity) throws Exception {
		getCommonDao().insert("questionOption.insert" , entity);
		
	}

	@Override
	public void update(QuestionOption entity) throws Exception {
		getCommonDao().update("questionOption.update" , entity);
		
	}

	@Override
	public Long getRecordCount(QuestionOption entity) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getAllRecordCount() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<? extends BaseEntity> getList(QuestionOption entity)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<? extends BaseEntity> getAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(QuestionOption entity) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteByIn(QuestionOption entity) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteByArr(QuestionOption entity) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public QuestionOption getOneById(QuestionOption entity) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> getGrid(QuestionOption entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String upload(BaseEntity baseEntity, HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

    /**
     *  批量保存问题选项
     *  @param list
     *  @author zhaofeng
     *  @version 1.0
     *  </pre>
     *  Created on :2012-5-23 下午3:26:00
     *  LastModified:
     *  History:
     *  </pre>
     */
    public void insertBatch(List<QuestionOption> list){
    	for(QuestionOption q : list){
    		try {
				this.save(q);
			} catch (Exception e) {
				DetailException.expDetail(e, QuestionOptionService.class);
			}
    	}
    }
    
    /**
     *  批量保存问题选项
     *  @param list
     *  @author zhaofeng
     *  @version 1.0
     *  </pre>
     *  Created on :2012-5-24 下午2:58:52
     *  LastModified:
     *  History:
     *  </pre>
     */
    public void updateBatch(List<QuestionOption> list){
    	for(QuestionOption q : list){
    		try {
				this.update(q);
			} catch (Exception e) {
				DetailException.expDetail(e, QuestionOptionService.class);
			}
    	}
    }
    /**
     *  根据问题获取选项集合
     *  @param question
     *  @return
     *  @author zhaofeng
     *  @version 1.0
     *  </pre>
     *  Created on :2012-5-24 下午1:20:02
     *  LastModified:
     *  History:
     *  </pre>
     */
    public List<QuestionOption> getOptionsByQuestion(Question question){
    	List<QuestionOption> resultList = getCommonDao().selectList("questionOption.selectQuestionOptions", question); 
		return resultList;
    }
    /**
     *  删除问题下面的所有选项
     *  @param question
     *  @author zhaofeng
     *  @version 1.0
     *  </pre>
     *  Created on :2012-5-24 下午3:19:12
     *  LastModified:
     *  History:
     *  </pre>
     */
    public void deleteOptionByQuestion(Question question){
    	getCommonDao().delete("questionOption.deleteOptionByQuestion",question);
    }
}
