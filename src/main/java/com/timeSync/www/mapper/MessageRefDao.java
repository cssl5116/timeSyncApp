package com.timeSync.www.mapper;

import com.mongodb.client.result.UpdateResult;
import com.timeSync.www.entity.MessageRefEntity;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * @author fishx
 * @version 1.0
 * @description: TODO
 * @date 2023/9/3 13:40
 */
@Repository
public class MessageRefDao {
  @Resource
  private MongoTemplate mongoTemplate;

  public String insert(MessageRefEntity messageRef) {
    messageRef = mongoTemplate.save(messageRef,"message_ref");
    return messageRef.get_id();
  }

  /**
   * 查询未读消息数量
   *
   * @param userId userId
   * @return 未读消息数量
   */
  public long searchUnreadCount(int userId) {
    Query query = new Query(Criteria.where("readFlag")
        .is(false).and("receiverId").is(userId));
    return mongoTemplate.count(query, MessageRefEntity.class);
  }

  /**
   * 查询新接收消息数量
   *
   * @param userId userId
   * @return 新接收消息数量
   */
  public long searchLastCount(int userId) {
    Query query = new Query(Criteria.where("lastFlag").is(true).and("receiverId").is(userId));
    Update update = new Update();
    update.set("lastFlag", false);
    UpdateResult updateResult = mongoTemplate.updateMulti(query, update, "message_ref");
    return updateResult.getModifiedCount();
  }

  /**
   * 把未读消息变更为已读消息
   *
   * @param id 消息id
   * @return 受影响行数
   */
  public long updateUnreadMessage(String id) {
    Query query = new Query(Criteria.where("messageId").is(id));
    Update update = new Update();
    update.set("readFlag", true);
    UpdateResult updateResult = mongoTemplate.updateFirst(query, update, "message_ref");
    return updateResult.getModifiedCount();
  }

  /**
   * 根据ID删除ref消息
   * @param id 消息id
   * @return 受影响行数
   */
  public long deleteMessageRefById(String id) {
    Query query = new Query(Criteria.where("messageId").is(id));
    return mongoTemplate.remove(query, "message_ref").getDeletedCount();
  }

  /**
   * 删除某个用户全部消息
   * @param userId 用户id
   * @return 受影响行数
   */
  public long deleteUserMessageRef(String userId) {
    Query query = new Query(Criteria.where("receiverId").is(userId));
    return mongoTemplate.remove(query,"message_ref").getDeletedCount();
  }
}
