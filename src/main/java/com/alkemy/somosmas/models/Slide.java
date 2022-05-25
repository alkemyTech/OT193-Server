package com.alkemy.somosmas.models;



import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "slides")
@Getter
@Setter
public class Slide {

    public Slide() {
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private long id;

    private String imageUrl;

    @Column(name = "order_ong")
    private Integer order;

    @Column(columnDefinition = "TEXT")
    private String text;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "organizationId")
    private Organization organization;

   
   
  
   
}
