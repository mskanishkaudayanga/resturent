package com.kaniya.resturentbackend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String CategoryName;

    @ManyToOne
    @JoinColumn(name="Menu_is")
    private Menu menu;

    @OneToMany(mappedBy = "category" ,cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Dishes> dishes;
}
