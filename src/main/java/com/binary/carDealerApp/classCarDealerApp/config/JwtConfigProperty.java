package com.binary.carDealerApp.classCarDealerApp.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for JWT (JSON Web Token) properties.
 * This class is responsible for binding JWT-related properties from the application's configuration file.
 *
 * Key points:
 * 1. Uses @Configuration to indicate this is a configuration class.
 * 2. Uses @ConfigurationProperties to bind properties with the "jwt" prefix from the configuration file.
 * 3. Provides getter and setter methods for JWT secret and expiration time.
 * 4. Allows for externalized configuration of JWT parameters.
 */
@Configuration("jwtConfigProps")
@ConfigurationProperties(prefix = "jwt")
public class JwtConfigProperty {

    /**
     * The secret key used for signing JWTs.
     */
    private String secret;

    /**
     * The expiration time for JWTs in milliseconds.
     */
    private long expiration;

    /**
     * Gets the JWT secret key.
     *
     * @return The secret key used for JWT signing.
     */
    public String getSecret() {
        return secret;
    }

    /**
     * Sets the JWT secret key.
     *
     * @param secret The secret key to be used for JWT signing.
     */
    public void setSecret(String secret) {
        this.secret = secret;
    }

    /**
     * Gets the JWT expiration time.
     *
     * @return The expiration time in milliseconds.
     */
    public long getExpiration() {
        return expiration;
    }

    /**
     * Sets the JWT expiration time.
     *
     * @param expiration The expiration time in milliseconds.
     */
    public void setExpiration(long expiration) {
        this.expiration = expiration;
    }
}
