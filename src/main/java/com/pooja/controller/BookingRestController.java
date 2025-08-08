package com.pooja.controller;
import com.pooja.model.Booking;
import com.pooja.model.Movie;
import com.pooja.service.BookingService;
import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingRestController {

    @Autowired
    BookingService bookingService;

    @PostMapping
    public ResponseEntity<String> createBooking(@RequestBody Booking booking) {
        Booking saved = bookingService.saveBooking(booking);
        return ResponseEntity.ok("Booking created with ID: " + saved.getId());
    }

    @GetMapping("/booking/{bookingId}")
    public ResponseEntity<List<Booking>> getBookingsForMovie(@PathVariable String bookingId) {
        return ResponseEntity.ok(bookingService.getBookingsByMovieId(bookingId));
    }
    @Operation(summary = "Update booking by ID", description = "Update existing booking details")
//    @ApiResponse(responseCode = "200", description = "Booking updated successfully")
    @PutMapping("/{id}")
    public ResponseEntity<String> updateBooking(@PathVariable String id, @Valid @org.springframework.web.bind.annotation.RequestBody Booking booking) {
        return ResponseEntity.ok(bookingService.updateBooking(id,booking).getId());
    }

    @Operation(summary = "Delete Booking by ID", description = "Deletes a booking based on the ID")
    @ApiResponse(responseCode = "200", description = "Booking deleted successfully")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBooking(@PathVariable String id) {
        bookingService.deleteBooking(id);
        return ResponseEntity.ok("Booking Deleted with ID: " + id);
    }
}
