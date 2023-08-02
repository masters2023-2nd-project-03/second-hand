package team03.secondhand.domain.chat;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import team03.secondhand.AuthorizationExtractor;
import team03.secondhand.JwtTokenProvider;
import team03.secondhand.domain.chat.dto.ChatDataRequestDto;

@RequiredArgsConstructor
@Controller
public class ChatController {

    private final ChatService chatService;
    private final AuthorizationExtractor authorizationExtractor;
    private final JwtTokenProvider jwtTokenProvider;

    @MessageMapping("/chat/message")
    public void sendMessage(@RequestBody ChatDataRequestDto.Message message,
                            @Header("Authorization") String tokenHead) {
        String jwt = authorizationExtractor.extract(tokenHead, "Bearer");
        Long memberId = jwtTokenProvider.getMemberId(jwt);

        chatService.sendMessage(memberId, message);
    }
}
