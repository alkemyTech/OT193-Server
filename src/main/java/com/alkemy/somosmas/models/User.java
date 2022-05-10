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
@Table(name = "users")
@Getter
@Setter
@SQLDelete(sql = "UPDATE users SET deleted = true WHERE idUser=?")
@Where(clause = "delete = false")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idUser;
	@Column(name = "firstName", nullable = false)
	private String firstName;
	@Column(name = "lastName", nullable = false)
	private String lastName;
	@Column(name = "email", nullable = false, unique = true)
	private String email;
	@Column(name = "password", nullable = false)
	private String password;
	@Column(name = "photo")
	private String photo;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "role_id", insertable = false)
	private Role role;
	//@Column(name = "role_id", nullable = false)
	//private Long roleID;
	@Column(name = "createDate")
	private LocalDateTime createDate;

	@PrePersist
	private void beforePersisting() {
		this.createDate = LocalDateTime.now();
	}

	private boolean deleted = Boolean.FALSE;
}