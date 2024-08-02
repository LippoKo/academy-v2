package com.ctw.workstation.booking.boundary;

import com.ctw.workstation.booking.control.BookingService;
import com.ctw.workstation.booking.entity.Booking;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.jboss.logging.Logger;

import java.util.List;
import java.util.UUID;

@Path("/bookings")
public class BookingResource {

//    Logger logger = Logger.getLogger(BookingResource.class);



    @Inject
    BookingService bookingService;

    @GET
    public List<Booking> findAllRacks() {
//        logger.info("Logger message!");
        return bookingService.findAllBookings();
    }

    @GET
    @Path("/{id}")
    public Booking findById(@PathParam("id") UUID id) {
        String bookingId = "1231";
//        logger.info(" 0 Request for booking" + id + "of bookings");
//        logger.infof("1 Request another booking id %s from bookings with id %d", bookingId, id);
//        logger.infov("2 Request another booking id {0} from bookings with id {1}", bookingId, id);
        return bookingService.findById(id);
    }

    @POST
    @Transactional
    public Response createRack(Booking booking) {
        Booking newBooking = bookingService.addBooking(booking);
        return Response.status(Response.Status.CREATED).entity(newBooking).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateRack(@PathParam("id") UUID id, Booking updateBooking) {
        Booking updatedRack = bookingService.updateBooking(id, updateBooking);
        return Response.ok().entity(updatedRack).build();
    }

    @DELETE
    @Transactional
    @Path("/{id}")
    public Response deleteRack(@PathParam("id") UUID id) {
        bookingService.deleteBooking(id);
        return Response.noContent().build();
    }
}
