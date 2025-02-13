package com.kaniya.resturentbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String menuName;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="Resturent_is")
    private Resturents resturents;

    @OneToMany(mappedBy = "menu" ,cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Dishes> dishes;


}
