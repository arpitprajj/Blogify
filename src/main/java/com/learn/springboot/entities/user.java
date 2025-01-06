package com.learn.springboot.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="users")
@NoArgsConstructor
@Getter
@Setter
public class user {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

@Column(name="user_name",nullable=false,length=100)
private String name;

@Column(name="email",nullable=false,length=100)
private String email;

    @Column(name="password",nullable=false,length=100)
  private String password;
  private String about;



}
