package com.hanghae.degether.websocket.repository;


import com.hanghae.degether.project.repository.ProjectRepository;
import com.hanghae.degether.websocket.model.ChatRoom;
import com.hanghae.degether.websocket.service.RedisSubscriber;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Repository
@Slf4j
public class ChatRoomRepository {

    // 채팅방(topic)에 발행되는 메시지를 처리할 Listner
    private final RedisMessageListenerContainer redisMessageListener;
    private final RedisSubscriber redisSubscriber;
    private final RoomRepository roomRepository;
    private final ProjectRepository projectRepository;
    private final MessageRepository messageRepository;

    // Redis
    private static final String CHAT_ROOMS = "CHAT_ROOM";
    private final RedisTemplate<String, Object> redisTemplate;
    private HashOperations<String, String, ChatRoom> opsHashChatRoom;

    // 채팅방의 대화 메시지를 발행하기 위한 redis topic 정보. 서버별로 채팅방에 매치되는 topic정보를 Map에 넣어 roomId로 찾을수 있도록 한다.
    private static Map<String, ChannelTopic> topics;


    // 초기화
    @PostConstruct
    private void init() {
        opsHashChatRoom = redisTemplate.opsForHash();
        topics = new HashMap<>();
    }


    //채팅방 입장 : redis에 topic을 만들고 pub/sub 통신을 하기 위해 리스너를 설정한다.

    public void enterChatRoom(String roomId) {
        ChannelTopic topic = topics.get(roomId);
        if (topic == null) {
            topic = new ChannelTopic(roomId);
            redisMessageListener.addMessageListener(redisSubscriber, topic);
            log.info("메세지 리스너 작동확인 ");

            topics.put(roomId, topic);
        }
    }


     // 채팅방 생성 , 프로젝트 생성시 만들어진 projectid를 받아와서  roomid로 사용한다.
    @Transactional
    public ChatRoom createChatRoom(String roomId ) {

        ChatRoom chatRoom = ChatRoom.create(roomId);

        // redis 저장
        opsHashChatRoom.put(CHAT_ROOMS, roomId, chatRoom);

        // DB 저장
        roomRepository.save(chatRoom);
        return chatRoom;
    }


    // 토픽값을 roomid값으로 생성후 넘겨준다.
    public static ChannelTopic getTopic(String roomId) {
        ChannelTopic channelTopic = new ChannelTopic(roomId);
        log.info(channelTopic.toString());
        return channelTopic;

    }

}