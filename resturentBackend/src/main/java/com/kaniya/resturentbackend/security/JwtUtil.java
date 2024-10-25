package com.kaniya.resturentbackend.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


@CrossOrigin
public class JwtUtil {
    private String secret_key;

    public String extractId(String token){
        return extractClaim(token,Claims::getSubject);
    }

    public Date extractExpiration(String token){
        return extractClaim(token,Claims::getExpiration);
    }
    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
        final Claims claims=extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token){
        return Jwts.parser().setSigningKey(secret_key).parseClaimsJws(token).getBody();
    }

    public Boolean isExprired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private String createToken(Map<String,Object> claims ,long id){
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(String.valueOf(id))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*60*2))
                .signWith(SignatureAlgorithm.ES256,secret_key)
                .compact();
    }

    public String genarateToken(Long id){
        Map<String,Object> claims= new HashMap<>();
        return createToken(claims,id);
    }
    public Boolean validateToken(String token, Long id){
        final String extracted_id=extractId(token);
        return (extracted_id.equals(id)) && isExprired(token);
    }

}
