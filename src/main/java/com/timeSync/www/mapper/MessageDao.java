package com.timeSync.www.mapper;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONObject;
import com.timeSync.www.entity.MessageEntity;
import com.timeSync.www.entity.MessageRefEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @author fishx
 * @version 1.0
 * @description: 消息模块的持久层
 * @date 2023/9/3 13:02
 */
@Repository
public class MessageDao {
  @Resource
  private MongoTemplate mongoTemplate;

  public String insert(MessageEntity message) {
    Date sendTime = message.getSendTime();
    message.setSendTime(DateUtil.offset(sendTime, DateField.HOUR, 8));
    message = mongoTemplate.save(message, "message");
    return message.get_id();
  }

  public List<HashMap> searchMessageByPage(int userId, long start, int length) {
    JSONObject json = new JSONObject();
    json.set("$toString", "$_id");
    Aggregation aggregation = Aggregation.newAggregation(
        Aggregation.addFields().addField("id").withValue(json).build(),
        Aggregation.lookup("message_ref", "id", "messageId", "ref"),
        Aggregation.match(Criteria.where("ref.receiverId").is(userId)),
        Aggregation.sort(Sort.by(Sort.Direction.DESC, "sendTime")),
        Aggregation.skip(start),
        Aggregation.limit(length)
    );
    AggregationResults<HashMap> results = mongoTemplate.aggregate(aggregation, "message", HashMap.class);
    List<HashMap> list = results.getMappedResults();
    list.forEach(item -> {
      List<MessageRefEntity> refList = (List<MessageRefEntity>) item.get("ref");
      MessageRefEntity entity = refList.get(0);
      Boolean readFlag = entity.getReadFlag();
      String readId = entity.get_id();
      item.put("readFlag", readFlag);
      item.put("readId", readId);
      item.remove("ref");
      item.remove("_id");
      Date sendTime = (Date) item.get("sendTime");
      sendTime = DateUtil.offset(sendTime, DateField.HOUR, -8);
      String today = DateUtil.today();
      if (today.equals(DateUtil.date(sendTime).toDateStr())) {
        item.put("sendTime", DateUtil.format(sendTime, "HH:mm"));
      } else {
        item.put("sendTime", DateUtil.format(sendTime, "yyyy/MM/dd"));
      }
    });
    return list;
  }

  public HashMap searchMessageById(String id) {
    HashMap map = mongoTemplate.findById(id, HashMap.class, "message");
    Date sendTime = (Date) map.get("sendTime");
    sendTime = DateUtil.offset(sendTime, DateField.HOUR, -8);
    map.replace("sendTime", DateUtil.format(sendTime, "yyyy-MM-dd HH:mm"));
    return map;
  }

  public int totalCount() {
    JSONObject json = new JSONObject();
    json.set("$toString", "$_id");
    Aggregation aggregation = Aggregation.newAggregation(
        Aggregation.addFields().addField("id").withValue(json).build(),
        Aggregation.lookup("message_ref", "id", "messageId", "ref"),
        Aggregation.sort(Sort.by(Sort.Direction.DESC, "sendTime")),
        Aggregation.count().as("count"),
        Aggregation.project("count")
    );
    AggregationResults<HashMap> aggregate = mongoTemplate.aggregate(aggregation, "message", HashMap.class);
    List<HashMap> mappedResults = aggregate.getMappedResults();
    if (CollectionUtil.isNotEmpty(mappedResults)) {
      return (int) mappedResults.get(0).get("count");
    } else {
      return 0;
    }
  }

  public List<HashMap> searchList(long start, Integer length) {
    JSONObject json = new JSONObject();
    json.set("$toString", "$_id");
    Aggregation aggregation = Aggregation.newAggregation(
        Aggregation.addFields().addField("id").withValue(json).build(),
        Aggregation.lookup("message_ref", "id", "messageId", "ref"),
        Aggregation.sort(Sort.by(Sort.Direction.DESC, "sendTime")),
        Aggregation.skip(start),
        Aggregation.limit(length)
    );
    AggregationResults<HashMap> results = mongoTemplate.aggregate(aggregation, "message", HashMap.class);
    List<HashMap> list = results.getMappedResults();
    return list;
  }
}
