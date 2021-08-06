package pers.darren.jwt;

import static io.jsonwebtoken.SignatureAlgorithm.HS256;
import static org.apache.commons.codec.binary.Base64.encodeBase64;

import java.util.Date;
import java.util.UUID;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;

/**
 * JJWT实践操作案例
 *
 * @CreatedBy Darren Luo
 * @CreatedTime Aug 5, 2021 1:26:07 PM
 */
public final class JJWTUtils {

    private static final long duration = 10_000;

    private static final byte[] secretKey = encodeBase64("my secret key".getBytes());

    public static void main(final String[] args) throws Exception {
        var jwt = createJWTWithSign();
        System.out.println("JWT with signature>>>" + jwt);
        System.out.println("=======================================================");
        // 模拟JWT过期场景，JWT的有效时间是10秒，使执行线程睡眠12秒以保证JWT过期
        Thread.sleep(12_000);
        try {
            // 解析使用签名算法的JWT
            System.out.println("JWT Claims with signature>>>" + parseJWTWithSign(jwt));
        } catch (final ExpiredJwtException e) {
            // 捕获到JWT已过期异常，从异常对象中获取Claims
            System.out.println("JWT Claims from exception>>>" + e.getClaims());
        }
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        jwt = createJWTWithoutSign();
        System.out.println("JWT without signature>>>" + jwt);
        System.out.println("=======================================================");
        System.out.println("JWT Claims without signature>>>" + parseJWTWithoutSign(jwt));
    }

    /**
     * 创建使用签名算法的JWT
     *
     * @CreatedBy Darren Luo
     * @CreatedTime Aug 5, 2021 2:10:40 PM
     * @return
     */
    public static String createJWTWithSign() {
        final var id = UUID.randomUUID().toString();
        final var timestamp = System.currentTimeMillis();

        return Jwts.builder().setHeaderParam("typ", "JWT").setHeaderParam("alg", "HS256").setId(id)
                .setSubject("JWT DEMO").setIssuer("JWT").setIssuedAt(new Date(timestamp))
                .setExpiration(new Date(timestamp + duration)).claim("name", "Darren Luo").claim("gender", "Male")
                .claim("age", 18).claim("username", "darrenluo1993").signWith(HS256, secretKey).compact();
    }

    /**
     * 创建未使用签名算法的JWT
     *
     * @CreatedBy Darren Luo
     * @CreatedTime Aug 5, 2021 4:04:05 PM
     * @return
     */
    public static String createJWTWithoutSign() {
        final var id = UUID.randomUUID().toString();
        final var timestamp = System.currentTimeMillis();

        return Jwts.builder().setHeaderParam("typ", "JWT").setId(id).setSubject("JWT DEMO").setIssuer("JWT")
                .setIssuedAt(new Date(timestamp)).setExpiration(new Date(timestamp + duration))
                .claim("name", "Darren Luo").claim("gender", "Male").claim("age", 18).claim("username", "darrenluo1993")
                .compact();
    }

    /**
     * 解析使用签名算法的JWT
     *
     * @CreatedBy Darren Luo
     * @CreatedTime Aug 5, 2021 3:54:35 PM
     * @param jwt
     * @return
     */
    public static Claims parseJWTWithSign(final String jwt) {
        // return Jwts.parser().setSigningKey(secretKey).parse(jwt).getBody();
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwt).getBody();
    }

    /**
     * 解析未使用签名算法的JWT
     *
     * @CreatedBy Darren Luo
     * @CreatedTime Aug 5, 2021 4:08:00 PM
     * @param jwt
     * @return
     */
    public static Claims parseJWTWithoutSign(final String jwt) {
        // return Jwts.parser().parse(jwt).getBody();
        return Jwts.parser().parseClaimsJwt(jwt).getBody();
    }
}