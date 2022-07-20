package com.hanghae.degether.WebSocket.service;

import org.springframework.data.redis.core.RedisTemplate;
import com.hanghae.degether.WebSocket.dto.MessageDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class RedisPublisher {

    private final RedisTemplate<String, Object> redisTemplate;

    /*
     * websocket 에서 받아온 메세지를 convertAndsend를 통하여 Redis의 메세지 리스너로 발행
     */

    public void publish(ChannelTopic topic, MessageDto messageDto) {
        redisTemplate.convertAndSend(topic.getTopic(), messageDto);
        log.info("발행 완료!");
    }

}
