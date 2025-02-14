package com.binary.carDealerApp.classCarDealerApp.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * UserCredential Entity Class
 *
 * This class represents a UserCredential entity in the car dealer application.
 * It is mapped to a database table using JPA annotations and implements UserDetails
 * for Spring Security integration.
 *
 * Key Points:
 * 1. Entity Mapping: Uses @Entity annotation to mark it as a JPA entity.
 * 2. UserDetails Implementation: Implements UserDetails for Spring Security.
 * 3. ID Generation: Uses UUID strategy for ID generation.
 * 4. Role-based Authorization: Stores user role and provides authorities.
 */
@Entity
public class UserCredential implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String username;
    private String password;
    private String role;

    /**
     * Default constructor for JPA
     */
    public UserCredential() {}

    /**
     * Constructor with username, password, and role
     *
     * @param username The username of the user
     * @param password The password of the user
     * @param role The role of the user
     */
    public UserCredential(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    /**
     * Returns the authorities granted to the user.
     * This method is part of the UserDetails interface.
     *
     * @return A collection of GrantedAuthority objects
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + role));
    }

    /**
     * Returns the password used to authenticate the user.
     * This method is part of the UserDetails interface.
     *
     * @return The user's password
     */
    @Override
    public String getPassword() {
        return password;
    }

    /**
     * Returns the username used to authenticate the user.
     * This method is part of the UserDetails interface.
     *
     * @return The user's username
     */
    @Override
    public String getUsername() {
        return username;
    }

    /**
     * Returns the ID of the user credential
     *
     * @return The user credential's ID
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the ID of the user credential
     *
     * @param id The ID to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Sets the username of the user
     *
     * @param username The username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Sets the password of the user
     *
     * @param password The password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Returns the role of the user
     *
     * @return The user's role
     */
    public String getRole() {
        return role;
    }

    /**
     * Sets the role of the user
     *
     * @param role The role to set
     */
    public void setRole(String role) {
        this.role = role;
    }

    // The following methods are part of the UserDetails interface
    // They are not implemented in this class, so they return default values

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
