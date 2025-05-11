package com.example.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.common.entity.MessageOutbox;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface MessageOutboxMapper extends BaseMapper<MessageOutbox> {

    /**
     * Find pending messages up to a certain limit.
     * Orders by create_time to process older messages first.
     */
    @Select("SELECT * FROM message_outbox WHERE status = 0 ORDER BY create_time ASC LIMIT #{limit}")
    List<MessageOutbox> findPendingMessages(@Param("limit") int limit);

    /**
     * Update message status by its internal ID.
     */
    @Update("UPDATE message_outbox SET status = #{status}, update_time = NOW() WHERE id = #{id}")
    int updateStatusById(@Param("id") Long id, @Param("status") int status);

    /**
     * Update message status and increment retry count by its internal ID.
     */
    @Update("UPDATE message_outbox SET status = #{status}, retry_count = retry_count + 1, update_time = NOW() WHERE id = #{id}")
    int updateStatusAndIncrementRetry(@Param("id") Long id, @Param("status") int status);
}