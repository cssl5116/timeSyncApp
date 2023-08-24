package com.timeSync.www.config.shiro;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author fishx
 * @version 1.0
 * @description: 1.生成令牌 2.获取id  3.鉴权
 * @date 2023/8/23 19:16
 */
@Component
public class JwtUtils {
  @Value("${time-sync.jwt.secret}")
  private String secret;

  @Value("${time-sync.jwt.expire}")
  private int expire;

  public String createToken(int userId) {
    // 日期偏移(过期时间)
    DateTime offset = DateUtil.offset(new Date(), DateField.DAY_OF_YEAR, 5);
    // 加密算法类
    Algorithm algorithm = Algorithm.HMAC256(secret);
    // 生成token
    return JWT.create().withClaim("userId", userId).withExpiresAt(offset).sign(algorithm);
  }

  public int getUserId(String token) {
    DecodedJWT decode = JWT.decode(token);
    return decode.getClaim("userId").asInt();
  }

  public void verifierToken(String token) {
    Algorithm algorithm = Algorithm.HMAC256(secret);
    JWTVerifier verifier = JWT.require(algorithm).build();
    verifier.verify(token);
  }
}
