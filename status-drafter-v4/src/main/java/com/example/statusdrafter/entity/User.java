package com.example.statusdrafter.entity;
import jakarta.persistence.*; import jakarta.validation.constraints.*; import java.time.LocalDateTime; import java.util.*;
@Entity @Table(name="users", uniqueConstraints={@UniqueConstraint(columnNames="username"),@UniqueConstraint(columnNames="email")})
public class User {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @NotBlank @Size(max=100) private String name;
 @NotBlank @Size(min=3,max=50) private String username;
 @NotBlank @Email @Size(max=150) private String email;
 @NotBlank private String password;
 @Enumerated(EnumType.STRING) @Column(nullable=false) private Role role=Role.EMPLOYEE;
 @Column(nullable=false) private boolean active=true;
 @Column(name="created_at",nullable=false) private LocalDateTime createdAt;
 @PrePersist void pre(){createdAt=LocalDateTime.now();}
 public User(){} public User(String n,String u,String e,String p,Role r){name=n;username=u;email=e;password=p;role=r;}
 public Long getId(){return id;} public String getName(){return name;} public void setName(String v){name=v;} public String getUsername(){return username;} public void setUsername(String v){username=v;} public String getEmail(){return email;} public void setEmail(String v){email=v;} public String getPassword(){return password;} public void setPassword(String v){password=v;} public Role getRole(){return role;} public void setRole(Role v){role=v;} public boolean isActive(){return active;} public void setActive(boolean v){active=v;} public LocalDateTime getCreatedAt(){return createdAt;}
 public enum Role { EMPLOYEE, MANAGER, ADMIN }
}
