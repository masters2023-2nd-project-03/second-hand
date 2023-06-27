package team03.secondhand.domain.global.handler;

import org.apache.logging.log4j.util.Strings;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.stereotype.Component;
import team03.secondhand.AuthorizationExtractor;
import team03.secondhand.JwtTokenProvider;
import team03.secondhand.error.MemberError;

@Component
public class StompHandler implements ChannelInterceptor {

    private AuthorizationExtractor authorizationExtractor;
    private JwtTokenProvider jwtTokenProvider;

    public StompHandler(AuthorizationExtractor authorizationExtractor, JwtTokenProvider jwtTokenProvider) {
        this.authorizationExtractor = authorizationExtractor;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        String token = authorizationExtractor.extract(message, "Bearer");

        if (Strings.EMPTY.equals(token)) {
            throw new MemberError.TokenIsNull();
        }
        if (!jwtTokenProvider.validateToken(token)) {
            throw new MemberError.TokenExpired();
        }

        return message;
    }
}
