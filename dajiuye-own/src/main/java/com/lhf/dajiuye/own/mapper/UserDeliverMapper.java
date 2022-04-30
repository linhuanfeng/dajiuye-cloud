package com.lhf.dajiuye.own.mapper;

import com.lhf.dajiuye.own.domain.Userdeliver;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserDeliverMapper {
    /**
     * 用户投递简历
     * @param userdeliver
     */
    @Insert("insert into user_deliver(from_user_id,to_hr_id,resume_url,job_id,state) " +
            "values(#{fromUserId},#{toHrId},#{resumeUrl},#{jobId},#{state})")
    void saveDeliver(Userdeliver userdeliver);

    /**
     * 更新简历投递状态
     * @param userdeliver
     */
    @Update("update user_deliver set state=#{state},message=#{message} where id=#{id}")
    void updateDeliver(Userdeliver userdeliver);
}
