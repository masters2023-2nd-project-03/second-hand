package team03.secondhand;

import com.github.scribejava.core.java8.Base64;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import team03.secondhand.domain.member.Member;
import team03.secondhand.domain.member.MemberService;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class JwtTokenProvider {
    private String secretKey = "llshlllshlllshlllshl";

    // 토큰 유효시간 30분
    private long tokenValidTime = 30 * 60 * 1000L;
    private final MemberService memberService;

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

    // JWT 토큰에서 인증 정보 조회
    public Boolean getAuthentication(String token) {
        Optional<Member> memberOptional = memberService.getMemberById(this.getMemberId(token));
        return memberOptional.isPresent();
    }

    public Long getMemberId(String token) {
        return Long.getLong(Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject());
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