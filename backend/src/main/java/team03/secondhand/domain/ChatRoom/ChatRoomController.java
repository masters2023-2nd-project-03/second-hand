package team03.secondhand.domain.ChatRoom;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import team03.secondhand.domain.ChatRoom.dto.ChatRoomDataRequestDto;
import team03.secondhand.domain.ChatRoom.dto.ChatRoomDataResponseDto;
import team03.secondhand.domain.DataResponse;
import team03.secondhand.domain.StatusCode;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/chat")
public class ChatRoomController {

    private final ChatRoomService chatRoomService;

    // 채팅 리스트 화면
    @GetMapping("/room")
    public String rooms(Model model) {
        return "/chat/room";
    }

    @GetMapping("/room/test")
    public String roomsTest(Model model) {
        return "/chat/room_test";
    }


    // 모든 채팅방 목록 반환
    @GetMapping("/rooms")
    @ResponseBody
    public DataResponse<List<ChatRoomDataResponseDto.Info>> room(@RequestAttribute Long memberId) {
        List<ChatRoomDataResponseDto.Info> roomsData = chatRoomService.findAllRoomByMemberId(memberId);
        return new DataResponse<>(StatusCode.RESPONSE_SUCCESS, roomsData);
    }

    // 채팅방 생성
    @PostMapping("/room/create")
    @ResponseBody
    public DataResponse<ChatRoomDataResponseDto.Info> createRoom(@RequestAttribute Long memberId, @RequestBody ChatRoomDataRequestDto.create createRequest) {
        ChatRoomDataResponseDto.Info roomData = chatRoomService.createRoom(memberId, createRequest);
        return new DataResponse<>(StatusCode.RESPONSE_SUCCESS, roomData);
    }

    // 채팅방 입장 화면
    @GetMapping("/room/enter/{roomId}")
    public String roomDetail(Model model, @PathVariable String roomId) {
        model.addAttribute("roomId", roomId);
        return "/chat/roomdetail";
    }
    // 특정 채팅방 조회
    @GetMapping("/room/{roomId}")
    @ResponseBody
    public  DataResponse<ChatRoomDataResponseDto.Info> roomInfo(@PathVariable Long roomId) {
        ChatRoomDataResponseDto.Info roomData = chatRoomService.findRoomByRoomId(roomId);
        return new DataResponse<>(StatusCode.RESPONSE_SUCCESS, roomData);
    }

    @ResponseBody
    @GetMapping("/room/history/{roomId}")
    public DataResponse<ChatRoomDataResponseDto.ChatHistory> chatHistory(@PathVariable Long roomId) {
        ChatRoomDataResponseDto.ChatHistory chatHistory = chatRoomService.getChatHistory(roomId);
        return new DataResponse<>(StatusCode.RESPONSE_SUCCESS, chatHistory);
    }
}