package com.zfc.study.util;

import com.zfc.study.domain.dto.AdminUserDetails;
import com.zfc.study.domain.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author zufeichao
 * @ProjectName springboot-mysql-mybatisplus
 * @Description TODO
 * @Date 2019-10-09 9:26
 * @T: JwtTokenUtil
 **/
@Component
public class JwtTokenUtil {
    private static final Logger logger = LoggerFactory.getLogger(JwtTokenUtil.class);

    //private static final Long expiration = Long.parseLong("3600");

    //private static final String secret = "test2019";

    /**
     * 生成签名使用的密钥
     */
    @Value("${jwt.secret}")
    private String secret;
    /***
     * token有效时间
     **/
    @Value("${jwt.expiration}")
    private Long expiration;

/*    public static void main(String[] args) {
        JwtTokenUtil jtu = new JwtTokenUtil();
        User user = new User();
        user.setUserName("112223");
        AdminUserDetails userDetails = new AdminUserDetails(user);
        System.out.println(expiration);
        String token = jtu.generateToken(userDetails);
        System.out.println(token);

        String userName = jtu.getUserNameFromToken(token);
        System.out.println(userName);
        Claims claims = jtu.getClaimsFromToken(token);

        System.out.println(claims);

    }*/

    /**
     * 根据用户信息生成token
     **/
    public String generateToken(UserDetails userDetails){
        //私有的声明claims
        Map<String,Object> claims = new HashMap<>();
        claims.put("sub",userDetails.getUsername());
        claims.put("created",new Date());
        return generateToken(claims);
    }

    /**
     * 根据负责生成JWT的token
     * Jwts.builder() :new DefaultJwtBuilder()
     * setClaims(claims) :如果有私有声明，一定要先设置这个自己创建的私有的声明，这个是给builder的claim赋值，一旦写在标准的声明赋值之后，就是覆盖了那些标准的声明的
     * setExpiration(generateExpirationDate()) : 设置过期时间
     * signWith(SignatureAlgorithm.HS512,secret) :设置签名使用的签名算法和签名使用的秘钥
     *
     **/
    public String generateToken(Map<String,Object> claims){
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512,secret)
                .compact();
    }

    public String getUserNameFromToken(String token){
        String userName ;
        try{
            Claims claims = getClaimsFromToken(token);
            userName = claims.getSubject();
        } catch (Exception e){
            userName = null;
        }
        return  userName;
    }


    /**
     * 从token中获取JWT中的负载
     * Jwts.parser() : 得到DefaultJwtParser
     * setSigningKey(secret) : 设置签名的秘钥
     * parseClaimsJws(token) : 设置需要解析的jwt
     *
     **/
    private Claims getClaimsFromToken(String token){
        Claims claims = null;
        try{
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        }catch (Exception e){
            logger.info("JWT格式验证失败：{}",token);
        }
        return claims;
    }

    /**
     * 验证token是否还有效
     *
     * @param token       客户端传入的token
     * @param userDetails 从数据库中查询出来的用户信息
     */
    public boolean validateToken(String token, UserDetails userDetails){
        String username = getUserNameFromToken(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }



    /**
     * 生成token的过期时间
     **/
    private Date generateExpirationDate(){
        return new Date(System.currentTimeMillis() + expiration *1000);
    }

    /**
     * 判断token是否已经失效
     */
    private boolean isTokenExpired(String token){
        Date expiredDate = getExpiredDateFromToken(token);
        return expiredDate.before(new Date());
    }

    /**
     * 从token中获取过期时间
     */
    private Date getExpiredDateFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        return claims.getExpiration();
    }

    /**
     * 判断token是否可以被刷新
     */
    public boolean canRefresh(String token) {
        return !isTokenExpired(token);
    }

    /**
     * 刷新token
     */
    public String refreshToken(String token){
        Claims claims = getClaimsFromToken(token);
        claims.put("created",new Date());
        return generateToken(claims);
    }







}
