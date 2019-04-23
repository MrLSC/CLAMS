package com.winconlab.clams.mapper;

import com.winconlab.clams.pojo.VideoComment;
import com.winconlab.clams.pojo.VideoCommentExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface VideoCommentMapper {
    int countByExample(VideoCommentExample example);

    int deleteByExample(VideoCommentExample example);

    int deleteByPrimaryKey(Integer commentId);

    int insert(VideoComment record);

    int insertSelective(VideoComment record);

    List<VideoComment> selectByExample(VideoCommentExample example);

    VideoComment selectByPrimaryKey(Integer commentId);

    int updateByExampleSelective(@Param("record") VideoComment record, @Param("example") VideoCommentExample example);

    int updateByExample(@Param("record") VideoComment record, @Param("example") VideoCommentExample example);

    int updateByPrimaryKeySelective(VideoComment record);

    int updateByPrimaryKey(VideoComment record);
}