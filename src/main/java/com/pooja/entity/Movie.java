package com.pooja.entity;

//import com.pooja.model.City;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
@Table(name = "movies")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Movie {

//    private String id;  // You mentioned id should be String

    @Id
//   @GeneratedValue(strategy = GenerationType.UUID) // ✅ for String UUID
    private String id;

    private String title;
    private String genre;
    private int duration;
}