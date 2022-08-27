package com.gkhy.serviceoauth2.token;

import com.gkhy.serviceoauth2.config.AppProperties;
import com.gkhy.serviceoauth2.error.Oauth2Error;
import com.gkhy.serviceoauth2.model.UserFactory;
import io.jsonwebtoken.*;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Date;

@Service
@AllArgsConstructor
public class TokenProvider implements Serializable {

    private static final long serialVersionUID = 5777410839989209849L;
    private static final Logger logger = LoggerFactory.getLogger(TokenProvider.class);

    private final AppProperties appProperties;

    public String createToken(Authentication authentication) {
        UserFactory userPrincipal = (UserFactory) authentication.getPrincipal();

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + appProperties.getAuth().getTokenExpirationMsec());

        return Jwts.builder()
                .setSubject(Long.toString(userPrincipal.getId()))
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, appProperties.getAuth().getTokenSecret())
                .compact();
    }

    public Long getUserIdFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(appProperties.getAuth().getTokenSecret())
                .parseClaimsJws(token)
                .getBody();

        return Long.parseLong(claims.getSubject());
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(appProperties.getAuth().getTokenSecret()).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException ex) {
            logger.error(Oauth2Error.Expired_JWT_token);
        } catch (MalformedJwtException ex) {
            logger.error(Oauth2Error.Invalid_JWT_token);
        } catch (ExpiredJwtException ex) {
            logger.error(Oauth2Error.Expired_JWT_token);
        } catch (UnsupportedJwtException ex) {
            logger.error(Oauth2Error.Unsupported_JWT_token);
        } catch (IllegalArgumentException ex) {
            logger.error(Oauth2Error.JWT_is_Empty);
        }
        return false;
    }

}
