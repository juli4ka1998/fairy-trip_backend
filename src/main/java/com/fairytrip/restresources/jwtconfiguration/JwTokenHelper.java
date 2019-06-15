package com.fairytrip.restresources.jwtconfiguration;

import com.fairytrip.data.entities.Admin;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import javax.validation.constraints.NotNull;
import javax.ws.rs.core.UriInfo;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

public class JwTokenHelper {

    private static JwTokenHelper jwTokenHelper = null;
    private static final long EXPIRATION_LIMIT = 60;
    private SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    private JwTokenHelper() {

    }

    public static JwTokenHelper getInstance() {
        if (jwTokenHelper == null)
            jwTokenHelper = new JwTokenHelper();
        return jwTokenHelper;
    }

    public String generatePrivateKey(Admin admin) {
        String login = admin.getLogin();
        String password = admin.getPassword();
        return Jwts
                .builder()
                .setSubject(login)
               // .setSubject(password)
                .setIssuedAt(new Date())
                .setExpiration(getExpirationDate())
                .signWith(key)
                .compact();
    }

    public void claimKey(String privateKey) throws ExpiredJwtException, MalformedJwtException {
        Jwts
            .parser()
            .setSigningKey(key)
            .parseClaimsJws(privateKey);
    }

    @NotNull
    public Date getExpirationDate() {
        TimeZone.setDefault(TimeZone.getTimeZone("GMT+0300"));
        long currentTimeMillis = System.currentTimeMillis();
        long expMilliSeconds = TimeUnit.MINUTES.toMillis(EXPIRATION_LIMIT);
//        Calendar time = Calendar.getInstance();
//        time.add(Calendar.MILLISECOND, -time.getTimeZone().getOffset(time.getTimeInMillis()));
//        Date date = time.getTime();
       // System.out.println(new Date(currentTimeMillis + expMilliSeconds));
        return new Date(currentTimeMillis + expMilliSeconds);
    }
}
