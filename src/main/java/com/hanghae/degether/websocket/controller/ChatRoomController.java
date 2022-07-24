package com.hanghae.degether.websocket.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
@RequestMapping("/chat")
public class ChatRoomController {


    // 특정 채팅방 입장
    @PostMapping("/room/{roomId}")
    @ResponseBody
    public String roomInfo(@PathVariable String roomId) {
        return roomId;
    }


}
