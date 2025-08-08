package com.pooja.service;

import com.pooja.model.Booking;

import java.util.List;
import java.util.Optional;

public interface BookingService {
    Booking saveBooking(Booking booking);
    Optional<Booking> getBookingById(String id);
    List<Booking> getAllBookings();
    Booking updateBooking(String id, Booking booking);
    void deleteBooking(String id);

    List<Booking> getBookingsByMovieId(String movieId);
}

