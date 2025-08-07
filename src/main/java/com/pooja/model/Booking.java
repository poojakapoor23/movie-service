package  com.pooja.model;
import com.pooja.entity.Movie;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Booking {

    private String id;

    //    @NotBlank(message = "Name is required")
//    @Size(min = 2, max = 50, message = "Name should be between 2 and 50 characters")
    private String userName;

    private LocalDateTime bookingTime;

    //    @Size(min = 100, max = 500, message = "Seating capacity should be between 4 and 20 characters")
//    @Min(value = 100, message = "Seating capacity should be at least 100")
//    @Max(value = 500, message = "Seating capacity should be at most 500")
    @ManyToOne
    @JoinColumn(name = "movie_id", referencedColumnName = "id")
    private Movie movie;  // foreign key reference
}

