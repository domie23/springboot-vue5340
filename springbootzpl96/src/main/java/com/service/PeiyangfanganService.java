package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.PeiyangfanganEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.PeiyangfanganVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.PeiyangfanganView;


/**
 * 培养方案
 *
 * @author 
 * @email 
 * @date 2023-03-08 22:32:14
 */
public interface PeiyangfanganService extends IService<PeiyangfanganEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<PeiyangfanganVO> selectListVO(Wrapper<PeiyangfanganEntity> wrapper);
   	
   	PeiyangfanganVO selectVO(@Param("ew") Wrapper<PeiyangfanganEntity> wrapper);
   	
   	List<PeiyangfanganView> selectListView(Wrapper<PeiyangfanganEntity> wrapper);
   	
   	PeiyangfanganView selectView(@Param("ew") Wrapper<PeiyangfanganEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<PeiyangfanganEntity> wrapper);
   	

}

