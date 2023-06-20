package team03.secondhand;

import com.github.scribejava.core.java8.Base64;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Date;

@RequiredArgsConstructor
@Component
public class JwtTokenProvider {

    // TODO: 공개 되면 안됨
    private String secretKey = "llshlllshlllshlllshl";

    // 토큰 유효시간 30분
    private long tokenValidTime = 30 * 60 * 1000L;

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String createToken(String memberId) {
        Claims claims = Jwts.claims().setSubject(memberId); // JWT payload 에 저장되는 정보단위
        Date now = new Date();
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + tokenValidTime))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }


    public Long getMemberId(String token) {
        // TODO: 숫자가 아닌 다른 값이 들어오면?
        return Long.parseLong(Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject());
    }

    // 토큰의 유효성 + 만료일자 확인
    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }
}