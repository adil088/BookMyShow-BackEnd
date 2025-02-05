package com.bookmyshow_experience.Book_my_show_experience.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookmyshow_experience.Book_my_show_experience.Errors.NotEnoughSeatsException;
import com.bookmyshow_experience.Book_my_show_experience.dbResponse.AppUser;
import com.bookmyshow_experience.Book_my_show_experience.dbResponse.Booking;
import com.bookmyshow_experience.Book_my_show_experience.dbResponse.Show;
import com.bookmyshow_experience.Book_my_show_experience.requestBody.CreateBookingRequestBody;
import com.bookmyshow_experience.Book_my_show_experience.utility.MailAPIUtil;

@Service
public class BookingService {

    @Autowired
    DatabaseAPIUtil databaseAPIUtil;
    MailAPIUtil mailAPIUtil;

    BookingService(MailAPIUtil mailAPIUtil) {
        this.mailAPIUtil = mailAPIUtil;
    }

    public Booking bookTicket(CreateBookingRequestBody bookingDetails) {
        UUID userId = bookingDetails.getUserId();
        UUID showId = bookingDetails.getShowId();
        int ticketCount = bookingDetails.getTotalSeats();
        String paymentMode = bookingDetails.getPaymentMode().toString();

        // call database to create booking
        AppUser user = databaseAPIUtil.getUserById(userId);
        Show show = databaseAPIUtil.getShowById(showId);

        if (show == null) {
            System.out.println("Show not found");
        }

        int leftTickets = show.getTotalTickets() - ticketCount;

        if (leftTickets < 0) {
            throw new NotEnoughSeatsException("No seats available");
        }

        show.setTotalTickets(leftTickets);
        show.setTicketsSold(show.getTicketsSold() + ticketCount);

        Booking booking = new Booking();

        booking.setShow(show);
        booking.setUser(user);
        booking.setPaymentMethod(paymentMode);
        booking.setTotalAmount(ticketCount * show.getTicketPrice());
        booking.setTotalSeats(ticketCount);

        // Database api make post call
        databaseAPIUtil.createBooking(booking);
        mailAPIUtil.sendBookingMail(booking);
        return booking;

    }
}
