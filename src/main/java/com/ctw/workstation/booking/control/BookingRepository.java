package com.ctw.workstation.booking.control;

import com.ctw.workstation.booking.entity.Booking;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class BookingRepository implements PanacheRepository<Booking> {

    public List<Booking> findOverlappingBookings(LocalDateTime book_from, LocalDateTime book_to) {
        return find("book_from <= ?1 and book_to >= ?2", book_to, book_from).list();
    }

    public void createBooking(Booking booking) {
        persist(booking);
    }

    public List<Booking> getAllBookings() {
        return listAll();
    }

    public Booking getById(UUID id) {
        return find("id", id).firstResult();
    }

    public void updateBooking(Booking booking) {
        getEntityManager().merge(booking);
    }

    public void removeBooking(UUID id) {
        Booking booking = getById(id);
        delete(booking);
    }
}
