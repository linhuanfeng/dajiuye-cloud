package com.lhf.dajiuye.own.utils;

import com.lhf.dajiuye.own.domain.MessageDetail;
import com.lhf.dajiuye.own.domain.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessageUtil {
    /**
     * 将所有消息归类成主人和 A 的合并在一起，和 B 合并在一起。。。
     * @param allMessage
     * @param ownId 主人
     * @return
     */
    public static List mergeMsg(List<MessageDetail> allMessage,String ownId){
        Map<User,List<MessageDetail>> mergeMap=new HashMap<>();
        for (MessageDetail message : allMessage) {
            if(!message.getFromOpenId().equals(ownId)){
                // 当发消息的是对面
                boolean has=false;
                for (Map.Entry<User, List<MessageDetail>> entry : mergeMap.entrySet()) {
                    User key = entry.getKey();
                    if(key.getOpenId().equals(message.getFromOpenId())){
                        // 把同一个人的消息放进同一个key中
                        entry.getValue().add(message);
                        has=true;
                        break;
                    }
                }
                if(!has){
                    // 新的聊天者
                    User user = new User();
                    user.setOpenId(message.getFromOpenId());
                    user.setAvatar(message.getFromAvatar());
                    user.setNickName(message.getFromName());
                    List<MessageDetail> list=new ArrayList<>();
                    list.add(message);
                    mergeMap.put(user,list);
                }
            }else{
                // 当发消息的是主人
                boolean has=false;
                for (Map.Entry<User, List<MessageDetail>> entry : mergeMap.entrySet()) {
                    User key = entry.getKey();
                    if(key.getOpenId().equals(message.getToOpenId())){
                        // 把同一个人的消息放进同一个key中
                        entry.getValue().add(message);
                        has=true;
                        break;
                    }
                }
                if(!has){
                    // 新的聊天者
                    User user = new User();
                    user.setOpenId(message.getToOpenId());
                    user.setAvatar(message.getToAvatar());
                    user.setNickName(message.getToName());
                    List<MessageDetail> list=new ArrayList<>();
                    list.add(message);
                    mergeMap.put(user,list);
                }
            }
        }
        for (Map.Entry<User, List<MessageDetail>> entry : mergeMap.entrySet()) {
            System.out.print("用户："+entry.getKey());
            System.out.println(" 个数："+entry.getValue().size());
        }
        System.out.println();
        // 我把这个merge把转为list
        List<List<MessageDetail>> lists=new ArrayList<>(mergeMap.values());
        for (List<MessageDetail> value : mergeMap.values()) {
            System.out.println(value);
        }
        return lists;
    }
}
