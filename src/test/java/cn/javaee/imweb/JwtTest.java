package cn.javaee.imweb;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import org.junit.Test;

import java.security.Key;

public class JwtTest {

    @Test
    public void firstTest() {
        // We need a signing key, so we'll create one just for this example. Usually
        // the key would be read from your application configuration instead.
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        // 将密钥转换为字符串来存储
        String secretString = Encoders.BASE64.encode(key.getEncoded());
        System.out.println(secretString);
        // 解码
        byte[] bytes = Decoders.BASE64.decode(secretString);
        // 重新生成密钥对象
        Key key2 = Keys.hmacShaKeyFor(bytes);
        // 再次编码
        System.out.println("isEquals: " + Encoders.BASE64.encode(key2.getEncoded()).equals(secretString));
        // 生成token
        String jws = Jwts.builder().setSubject("Joe").signWith(key).compact();

        System.out.println(jws);
        // 验证token，并从token中解析出数据
        assert Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jws).getBody().getSubject().equals("Joe");
    }

}
