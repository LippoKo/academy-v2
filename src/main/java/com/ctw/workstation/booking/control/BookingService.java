package com.ctw.workstation.booking.control;

import com.ctw.workstation.booking.entity.Booking;
import com.ctw.workstation.enums.Status;
import com.ctw.workstation.rack.control.RackRepository;
import com.ctw.workstation.rack.entity.Rack;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class BookingService {

    @Inject
    BookingRepository bookingRepository;

    @Inject
    RackRepository rackRepository;

    @Transactional
    public Booking addBooking(Booking booking) {

        List<Booking> overlappingBooking = bookingRepository.findOverlappingBookings(
                booking.getBook_from(), booking.getBook_to()
        );

        if (!overlappingBooking.isEmpty()) {
            throw new RuntimeException("There are conflicting bookings for this period.");
        }

        bookingRepository.createBooking(booking);

        Rack rack = rackRepository.getById(booking.getRack_id());
        if (rack == null) {
           throw new RuntimeException("Rack not found");
        }
        if (rack.getStatus() == Status.REPAIR || rack.getStatus() == Status.OUTDATED) {
            throw new RuntimeException("Rack is currently under repair, or outdated and cannot be reserved.");
        }
        rack.setStatus(String.valueOf(Status.BOOKED));
        rackRepository.updateRack(rack);
        return booking;
    }

    public List<Booking> findAllBookings() {
        return bookingRepository.getAllBookings();
    }

    public Booking findById(UUID id) {
        return bookingRepository.getById(id);
    }

    @Transactional
    public Booking updateBooking(UUID id, Booking updatedBooking) {
        Booking oldBooking = findById(id);
        oldBooking.setBook_from(updatedBooking.getBook_from());
        oldBooking.setBook_to(updatedBooking.getBook_to());
        bookingRepository.updateBooking(oldBooking);
        return oldBooking;
    }

    @Transactional
    public void deleteBooking(UUID id) {
        bookingRepository.removeBooking(id);
    }

}
