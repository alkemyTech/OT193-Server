package com.alkemy.somosmas.models;

import java.time.LocalDateTime;

//import javax.management.relation.Role;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user")
@Getter
@Setter
@SQLDelete(sql = "UPDATE user SET deleted = true WHERE id_user=?")
@Where(clause = "deleted = false")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_user")
	private Long idUser;
	@Column(name = "first_name", nullable = false)
	private String firstName;
	@Column(name = "last_name", nullable = false)
	private String lastName;
	@Column(name = "email", nullable = false, unique = true)
	private String email;
	@Column(name = "pass", nullable = false)
	private String password;
	@Column(name = "photo")
	private String photo;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "role_id", insertable = false)
	private Role role;
//	@Column(name = "role_id", nullable = false)
//	private Long roleID;
	@Column(name = "created_at")
	private LocalDateTime createDate;

	@PrePersist
	private void beforePersisting() {
		this.createDate = LocalDateTime.now();
	}

	private Boolean deleted = false;
}