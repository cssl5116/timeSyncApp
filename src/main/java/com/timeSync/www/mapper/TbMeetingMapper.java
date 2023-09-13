package com.timeSync.www.mapper;

import com.timeSync.www.entity.TbMeeting;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
* @author fishx
* @description 针对表【tb_meeting(会议表)】的数据库操作Mapper
* @createDate 2023-08-23 12:03:43
* @Entity com.timeSync.www.entity.TbMeeting
*/
@Mapper
public interface TbMeetingMapper {
    //添加会议
    public int insertMeeting(TbMeeting entity);

    //查询会议列表分页数据
    public ArrayList<HashMap> searchMyMeetingListByPage(HashMap param);

    //查询某个会议的参会人在哪个部门
    public boolean searchMeetingMembersInSameDept(String uuid);

    //更新会议实例Id
    public int updateMeetingInstanceId(HashMap map);

    //根据Id搜索会议
    public HashMap searchMeetingById(int id);

    //搜索会议成员
    public ArrayList<HashMap> searchMeetingMembers(int id);

    //更新会议信息
    public int updateMeetingInfo(HashMap param);

    //根据Id删除会议
    public int deleteMeetingById(int id);

    //搜索一个月累的用户会议
    public List<String> searchUserMeetingInMonth(HashMap param);

}




