package com.pooja.repository;

import com.pooja.entity.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


public interface BookingRepository extends JpaRepository<Booking, String> {
    List<Booking> findByMovieId(String movieId);
}
