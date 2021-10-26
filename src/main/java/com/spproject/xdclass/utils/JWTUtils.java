package com.spproject.xdclass.utils;

import com.spproject.xdclass.model.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JWTUtils {

    private static  final  long EXPIRE = 6000 * 60 * 24 * 7;

    private static  final  String SECRET = "xdclass.net168";

    private  static final  String TOKEN_PREFIX = "xdclass";

    private  static final  String SUBJECT = "xdclass";

    public static String geneJsonWebToken(User user){

        String token = Jwts.builder().setSubject(SUBJECT)
                .claim("head_img",user.getHeadImg())
                .claim("id",user.getId())
                .claim("name",user.getName())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE))
                .signWith(SignatureAlgorithm.HS256,SECRET).compact();
        token = TOKEN_PREFIX + token;
        return token;
    }

    public  static Claims CheckJwt(String token){
        try{
            final Claims claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token.replace(TOKEN_PREFIX,"")).getBody();
            return claims;
        }catch (Exception e){
            return  null;
        }
    }
}
