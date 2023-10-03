package com.ecommercecrif.E_Commerce_application.service;

import com.ecommercecrif.E_Commerce_application.security.config.RsaKeyProperties;
import com.ecommercecrif.E_Commerce_application.model.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {


    private final RsaKeyProperties rsaKeys;

    public JwtService(RsaKeyProperties rsaKeys) {
        this.rsaKeys = rsaKeys;
    }

    public String extractUsername(String token){
        //Claims::getSubject Returns the JWT sub (subject) value or null if not present.
        return extractClaim(token, Claims::getSubject);
    }

   public String extractRole(String token){

       final Claims claims = extractAllClaims(token);

       return (String) claims.get("scope");
    }

    public <T> T extractClaim(String token, Function<Claims, T>claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public Claims extractAllClaims(String token) {
        String jwt = token.substring(7);
        //faccio il parsing dei Claim dal token e inserisco la signin key
        return Jwts
                .parserBuilder()
                .setSigningKey(rsaKeys.publicKey())
                .build()
                .parseClaimsJws(jwt)
                .getBody();
    }


    public boolean isTokenValidForResource(String token, UserEntity user){

        final String email = user.getEmail();
        return (email.equals(extractUsername(token)));
    }












    //metodo che controlla la validità del token passato in relazione ai valori dei campi dello userDetails passato
    public boolean isTokenValid(String token, UserDetails userDetails){
        final String username = userDetails.getUsername();
        return (username.equals(extractUsername(token)))&& !isTokenExpired(token);
    }

    //il metodo ritorna true se la data di expire è antecedente o uguale al momento attuale, false altrimenti
    private boolean isTokenExpired(String token) {
          //before() -> true if and only if the instant of time represented by this Date object is strictly earlier than the instant represented by when(nel nostrocaso la data attuale); false otherwise.
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
}






















/*    public String generateToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails
    ) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                //subject is userEmail
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                //expriation date a 24h
                .setExpiration(new Date(System.currentTimeMillis()+ 1000 * 60 * 24))
                .signWith(rsaKeys.privateKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    //se non vogliamo aggiungere extraclaims ma rifarci solo a Userdetails
    public String generateToken(Authentication authentication){
        return generateToken(new HashMap<>(), userDetailsService.loadUserByUsername(authentication.getName()));
    }*/