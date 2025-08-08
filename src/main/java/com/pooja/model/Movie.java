package  com.pooja.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Movie {

      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
       private String id;

//    @NotBlank(message = "Name is required")
//    @Size(min = 2, max = 50, message = "Name should be between 2 and 50 characters")
    private String title;

    private String genre;

    //    @Size(min = 100, max = 500, message = "Seating capacity should be between 4 and 20 characters")
//    @Min(value = 100, message = "Seating capacity should be at least 100")
//    @Max(value = 500, message = "Seating capacity should be at most 500")
    private int duration;
}

