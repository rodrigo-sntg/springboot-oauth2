package br.com.sandev.rods.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;

public class User {
	private final String email;
    private final Set<Role> roles;
    private final LocalDate dateCreated;
    
    public User(
            @JsonProperty("email") String email,
            @JsonProperty("roles") Set<Role> roles,
            @JsonProperty("dateCreated") LocalDate dateCreated) {
        super();
        this.email = email;
        this.roles = roles;
        this.dateCreated = dateCreated;
    }
    
    public String getEmail() {
        return email;
    }
    public Set<Role> getRoles() {
        return roles;
    }
    public LocalDate getDateCreated() {
        return dateCreated;
    }
    public static enum Role {
        USER, ADMIN
    }
    private static final TypeReference<User> typeRef = new TypeReference<User>() {};
    public static TypeReference<User> typeRef() {
        return typeRef;
    }
    private static final TypeReference<List<User>> listTypeRef = new TypeReference<List<User>>() {};
    public static TypeReference<List<User>> listTypeRef() {
        return listTypeRef;
    }
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	@Column
    private String login;
	
	@Column
    private String password;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
