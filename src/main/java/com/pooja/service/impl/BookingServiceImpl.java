package com.pooja.service.impl;

import com.pooja.model.Booking;
//import com.pooja.model.Movie;
import com.pooja.repository.BookingRepository;
import com.pooja.repository.MovieRepository;
import com.pooja.service.BookingService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository repo ;

    public BookingServiceImpl(BookingRepository repo) {
        this.repo = repo;
    }
    @Override
    public Booking saveBooking(Booking booking) {
        repo.save(convertModeltoEntity(booking));
        return booking;

    }

    private static com.pooja.entity.Booking convertModeltoEntity(Booking bookingModel) {
        com.pooja.entity.Booking bookingEntity = new com.pooja.entity.Booking();
        bookingEntity.setId(bookingModel.getId());
        bookingEntity.setUserName(bookingModel.getUserName());
        bookingEntity.setBookingTime(bookingModel.getBookingTime());
        bookingEntity.setMovie(bookingModel.getMovie());
        return bookingEntity;
    }


    @Override
    public Optional<Booking> getBookingById(String id) {
        Optional<com.pooja.entity.Booking> bookingEntity = repo.findById(id);
        Booking bookingModel = new Booking();
        convertEntity2Model(bookingModel,bookingEntity.orElse(null));
        return Optional.of(bookingModel);
    }
    private static void convertEntity2Model(Booking bookingModel, com.pooja.entity.Booking bookingEntity) {
        if (bookingEntity != null) {
            bookingModel.setId(bookingEntity.getId());
            bookingModel.setUserName(bookingEntity.getUserName());
            bookingModel.setBookingTime(bookingEntity.getBookingTime());
            bookingModel.setMovie(bookingEntity.getMovie());
        }
    }

    @Override
    public List<Booking> getAllBookings() {
        return List.of();
    }

    @Override
    public Booking updateBooking(String id, Booking booking) {
        return null;
    }

    @Override
    public void deleteBooking(String id) {

    }

    @Override
    public List<Booking> getBookingsByMovieId(String movieId) {
        return List.of();
    }
// Implement the methods here
}


