package com.alkemy.somosmas.models;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import com.sun.istack.NotNull;
import lombok.Data;

@Data
@Entity
@SQLDelete(sql="UPDATE testimonials SET deleted= true WHERE id=?")
@Where(clause="deleted=false")
@Table(name = "testimonials")
public class Testimonial {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private String name;
	
	private String image;
	private String content;
	
	@Column(name = "create_at")
	private LocalDateTime createAt;
	
	private boolean deleted = Boolean.FALSE;
	
	@PrePersist
	private void beforePersisting() {
	this.createAt= LocalDateTime.now();
	}
}
