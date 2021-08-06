package pers.darren.jwt;

import static com.auth0.jwt.algorithms.Algorithm.HMAC256;
import static java.lang.System.currentTimeMillis;
import static java.util.UUID.randomUUID;
import static org.apache.commons.codec.binary.Base64.encodeBase64;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.Claim;

/**
 * Auth0 JWT实践操作案例
 *
 * @CreatedBy Darren Luo
 * @CreatedTime Aug 6, 2021 2:07:14 PM
 */
public final class Auth0JWTUtils {

    private static final long duration = 10_000;

    private static final byte[] secretKey = encodeBase64("my secret key".getBytes());

    public static void main(final String[] args) throws Exception {
        final var jwt = createJWT();
        System.out.println("JWT with signature>>>" + jwt);
        System.out.println("=======================================================");
        // 模拟JWT过期场景，JWT的有效时间是10秒，使执行线程睡眠12秒以保证JWT过期
        Thread.sleep(12_000);
        // 解析使用签名算法的JWT
        System.out.println("JWT Claims with signature>>>" + parseJWT(jwt, true));
    }

    /**
     * 创建JWT
     *
     * @CreatedBy Darren Luo
     * @CreatedTime Aug 6, 2021 3:12:47 PM
     * @return
     */
    public static String createJWT() {
        final var id = randomUUID().toString();
        final var timestamp = currentTimeMillis();
        final Map<String, Object> header = new HashMap<>();
        header.put("typ", "JWT");
        header.put("alg", "HS256");

        return JWT.create().withHeader(header).withJWTId(id).withSubject("JWT DEMO").withIssuer("JWT")
                .withIssuedAt(new Date(timestamp)).withExpiresAt(new Date(timestamp + duration))
                .withClaim("name", "Darren Luo").withClaim("gender", "Male").withClaim("age", 18)
                .withClaim("username", "darrenluo1993").sign(HMAC256(secretKey));
    }

    /**
     * 解析JWT
     *
     * @CreatedBy Darren Luo
     * @CreatedTime Aug 6, 2021 3:16:20 PM
     * @param jwt
     * @param printBeforeVerify
     * @return
     */
    public static Map<String, Claim> parseJWT(final String jwt, final boolean printBeforeVerify) {
        if (printBeforeVerify) {
            final var decodedJWT = JWT.decode(jwt);
            System.out.println("JWT Claims before verify>>>" + decodedJWT.getClaims());
            System.out.println("=======================================================");
            return JWT.require(HMAC256(secretKey)).build().verify(decodedJWT).getClaims();
        }
        return JWT.require(HMAC256(secretKey)).build().verify(jwt).getClaims();
    }
}