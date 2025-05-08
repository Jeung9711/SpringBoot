package com.example.board.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data @Getter @Setter
public class FileAtch {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String oName;
    String cName;

    @ManyToOne
    User user;

    @ManyToOne
    Board board;
}
