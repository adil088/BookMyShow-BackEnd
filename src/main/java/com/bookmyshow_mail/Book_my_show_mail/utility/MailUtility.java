package com.bookmyshow_mail.Book_my_show_mail.utility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.bookmyshow_mail.Book_my_show_mail.requestBody.Booking;

import jakarta.mail.internet.MimeMessage;

@Service
public class MailUtility {

    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    TemplateEngine templateEngine;

    public void sendMail(
            String subjectLine,
            String mailType,
            String toEmail,
            String userName) throws Exception {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
        Context context = new Context();
        context.setVariable("userName", userName);
        mimeMessageHelper.setSubject(subjectLine);
        mimeMessageHelper.setTo(toEmail);
        String htmlEmail = templateEngine.process("user-registration-email", context);
        mimeMessageHelper.setText(htmlEmail, true);
        javaMailSender.send(mimeMessage);

    }

    public void sendTheaterRegistrationMail(String toEmail, String ownerName, String address, String subjectLine)
            throws Exception {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
        Context context = new Context();
        context.setVariable("ownerName", ownerName);
        context.setVariable("address", address);
        mimeMessageHelper.setSubject(subjectLine);
        mimeMessageHelper.setTo(toEmail);
        String htmlEmail = templateEngine.process("theater-registration-email", context);
        mimeMessageHelper.setText(htmlEmail, true);
        javaMailSender.send(mimeMessage);
    }

    public void sendHallRegistrationMail(String ownerEmail, String ownerName, String theaterLocation,
            String subjectLine, int seats)
            throws Exception {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
        Context context = new Context();
        context.setVariable("ownerName", ownerName);
        context.setVariable("address", theaterLocation);
        context.setVariable("seats", seats);
        mimeMessageHelper.setSubject(subjectLine);
        mimeMessageHelper.setTo(ownerEmail);
        String htmlEmail = templateEngine.process("hall-registration-email", context);
        mimeMessageHelper.setText(htmlEmail, true);
        javaMailSender.send(mimeMessage);
    }

    public void sendBookingMail(Booking booking)
            throws Exception {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
        Context context = new Context();
        context.setVariable("userName", booking.getUserName());
        context.setVariable("movieName", booking.getMovieName());
        context.setVariable("theaterName", booking.getTheaterName());
        context.setVariable("hallName", booking.getHallName());
        context.setVariable("theaterAddress", booking.getTheaterAddress());
        context.setVariable("totalTickets", booking.getTotalTickets());
        context.setVariable("totalAmountPaid", booking.getTotalAmountPaid());
        context.setVariable("paymentMethod", booking.getPaymentMethod());
        mimeMessageHelper
                .setSubject("Booking confirmation for " + booking.getUserName() + " at " + booking.getTheaterName());
        mimeMessageHelper.setTo(booking.getUserEmail());
        String htmlEmail = templateEngine.process("booking-email", context);
        mimeMessageHelper.setText(htmlEmail, true);
        javaMailSender.send(mimeMessage);
    }

}
