package team03.secondhand;

import net.bytebuddy.implementation.bind.annotation.Empty;
import org.apache.logging.log4j.util.Strings;
import org.springframework.lang.Nullable;
import org.springframework.messaging.Message;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

@Component
public class AuthorizationExtractor {
    public static final String AUTHORIZATION = "Authorization";
    public static final String ACCESS_TOKEN_TYPE = AuthorizationExtractor.class.getSimpleName() + ".ACCESS_TOKEN_TYPE";

    public String extract(HttpServletRequest request, String type) {
        Enumeration<String> headers = request.getHeaders(AUTHORIZATION);
        while (headers.hasMoreElements()) {
            String value = headers.nextElement();
            if (value.toLowerCase().startsWith(type.toLowerCase())) {
                return value.substring(type.length()).trim();
            }
        }

        return Strings.EMPTY;
    }

    public String extract(Message<?> message, String type) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
        String value = accessor.getFirstNativeHeader(AUTHORIZATION);
        if (value.toLowerCase().startsWith(type.toLowerCase())) {
            return value.substring(type.length()).trim();
        }

        return Strings.EMPTY;
    }

    public String extract(String tokenHead, String type) {
        if (tokenHead.toLowerCase().startsWith(type.toLowerCase())) {
            return tokenHead.substring(type.length()).trim();
        }

        return Strings.EMPTY;
    }

}