package com.nfjd.mappers;


import com.nfjd.domain.ACT_HI_ACTINST;
import com.nfjd.pojo.mypojo.MixTask;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/29.
 */
@Component("MedalMixMapper")
public interface MedalMixMapper{

	/**
     * @return
     */
	List<MixTask> queryHistoryTaskState(String orderId,String procDefId);


	List<Map> getTaskMixList(List taskIdList);
	
	

}
