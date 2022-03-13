package com.lhf.dajiuye.own.mapper;

import com.lhf.dajiuye.own.domain.Message;
import com.lhf.dajiuye.own.domain.MessageDetail;
import com.lhf.dajiuye.own.domain.User;
import com.lhf.dajiuye.own.domain.Userdeliver;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserDataMapper {
    @Select("select * from user where open_id=#{openId}")
    User getUserByOpenId(@Param("openId") String openId);

    @Select("select * from user where id=#{id}")
    User getUserById(@Param("id") String id);

    /**
     *
     * 获取所有投递者
     * @param id hr的id
     * @param state 简历的状态
     * @return
     */
    @Select("<script> select d.id tempDeliverId,j.jobName temp_job_name,date(d.time) temp_deliver_time,u.resume,u.mobile,u.university ,u.city,u.qualification,u.graduation_year,u.avatar,u.nick_name,u.honor,d.from_user_id,d.to_hr_id,d.state,d.time,d.resume_url,d.job_id from user_deliver d" +
            " inner join user u on d.from_user_id=u.id" +
            " inner join job j on d.job_id=j.jobId and d.to_hr_id=#{id} <if test=\"''!=state\"> and d.state=#{state}</if> </script>")
    List<User> getUsers(@Param("id") String id, @Param("state") String state);

    @Insert("insert into user(" +
            "id,user_name,user_password,gender,birthday,temp_deliver_time,last_login_ip,com_id,nick_name," +
            "mobile,avatar,open_id,session_key,user_status,isHr,resume,city,province,university,qualification,graduation_year,honor,temp_job_name) values(" +
            "#{id},#{userName},#{userPassword},#{gender},#{birthday},#{tempDeliverTime},#{lastLoginIp},#{comId},#{nickName}," +
            "#{mobile},#{avatar},#{openId},#{sessionKey},#{userStatus},#{isHr},#{resume},#{city},#{province},#{university},#{qualification},#{graduationYear},#{honor},#{tempJobName})")
    @Options(useGeneratedKeys = true,keyProperty ="id",keyColumn = "id")
    int insertUser(User user);

    @Update("update user set user_name=#{userName},gender=#{gender},birthday=#{birthday},temp_deliver_time=#{tempDeliverTime}," +
            "last_login_ip=#{lastLoginIp},nick_name=#{nickName},"+
            "mobile=#{mobile},avatar=#{avatar},session_key=#{sessionKey},user_status=#{userStatus},isHr=#{isHr}" +
            " where open_id=#{openId}")
    int updateUser(User user);

    @Update("update user set resume=#{resumeUrl} where open_id=#{openId}")
    int updateUserResume(String resumeUrl, String openId);

    /**
     * 获取两个openId对应用户的所有消息
     * @param msg
     * @return
     */
    @Select("insert into message(from_open_id,to_open_id,news,create_time) values(#{fromOpenId},#{toOpenId},#{news},#{createTime})")
    void saveMessage(Message msg);

    @Deprecated
    @Select("SELECT `news` FROM message WHERE from_open_id=#{openId} UNION SELECT `news` FROM message WHERE to_open_id=#{openId}")
    List<Message> getAllMessage(String openId);

    /**
     * 切记拼接字符串时空格别漏了
     * @param openId
     * @return
     */
    @Select("SELECT m.`from_open_id`,u1.`avatar` from_avatar,u1.`nick_name` from_name,m.`to_open_id` to_open_id,u2.`avatar` to_avatar,u2.`nick_name` to_name,m.`news`,m.`create_time` FROM user u1 INNER JOIN message m ON u1.`open_id`=m.`from_open_id` AND m.`from_open_id`=#{openId} INNER JOIN user u2 ON u2.`open_id`=m.`to_open_id`"+
            "UNION ALL "+
            "SELECT m.`from_open_id`,u1.`avatar` from_avatar,u1.`nick_name` from_name,m.`to_open_id` to_open_id,u2.`avatar` to_avatar,u2.`nick_name` to_name,m.`news`,m.`create_time` FROM user u1 INNER JOIN message m ON u1.`open_id`=m.`from_open_id` INNER JOIN user u2 ON u2.`open_id`=m.`to_open_id` AND m.`to_open_id`=#{openId}")
    List<MessageDetail> getAllMessageDetail(String openId);

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
