package com.imaginnovate.afu.services;

import org.springframework.stereotype.Service;

import com.imaginnovate.afu.model.Requests;
import com.imaginnovate.afu.model.Services;
import com.imaginnovate.afu.model.UnregisteredUsers;
import com.imaginnovate.afu.model.Users;

@Service
public class RequestEmailBody {
        public String generateEmailBody(Requests request, Users user, UnregisteredUsers unregisteredUser,
                        Services services) {
                StringBuilder emailBody = new StringBuilder();
                emailBody.append("<html><head>");
                emailBody.append("<style>");
                emailBody.append(
                                "body { font-family: Arial, sans-serif; margin: 0; padding: 0; background-color: #f4f4f4; }");
                emailBody.append(
                                ".container { width: 75%; margin: 0 auto; background-color: #ffffff; padding: 20px; border-radius: 8px; }");
                emailBody.append(
                                ".header { background-color: #630876; color: #ffffff; padding: 20px; text-align: center; border-radius: 8px 8px 0 0; }");
                emailBody.append(".header img { width: 100px; height: 100px; border-radius: 50%; }");
                emailBody.append(
                                ".footer { text-align: center; padding: 20px; background-color: #630876; color: #ffffff; border-radius: 0 0 8px 8px; }");
                emailBody.append("table { width: 100%; border-collapse: collapse; margin: 20px 0; }");
                emailBody.append("th, td { border: 1px solid #cccccc; text-align: left; padding: 10px; }");
                emailBody.append("th { background-color: #f2f2f2; }");
                emailBody.append("tr:nth-child(even) { background-color: #f9f9f9; }");
                emailBody.append("</style>");
                emailBody.append("</head><body>");
                emailBody.append("<div class='container'>");
                emailBody.append("<div class='header'>");
                emailBody.append("<img class='sealImage' src='https://looka.com/s/195825981' alt='AFU Logo' />");
                emailBody.append("<h2>Request Booking Status</h2>");
                emailBody.append("</div>");
                emailBody.append("<h2>Request Details</h2>");
                emailBody.append("<table>");
                emailBody.append("<tr><th>Field</th><th>Value</th></tr>");
                emailBody.append("<tr><td>Id</td><td>").append(request.getId()).append("</td></tr>");
                String userEmail = (user != null && user.getEmail() != null) ? user.getEmail() : "Email not available";
                emailBody.append("<tr><td>User Email</td><td>").append(userEmail).append("</td></tr>");
                String unregisteredUserEmail = (unregisteredUser != null && unregisteredUser.getEmail() != null)
                                ? unregisteredUser.getEmail()
                                : "Email not available";
                emailBody.append("<tr><td>Unregistered User Email</td><td>").append(unregisteredUserEmail)
                                .append("</td></tr>");
                String serviceName = (services != null) ? services.getName() : "Service not found";
                emailBody.append("<tr><td>Service</td><td>").append(serviceName).append("</td></tr>");
                emailBody.append("<tr><td>Booking Date</td><td>")
                                .append(request.getBookingDate() != null ? request.getBookingDate().toString()
                                                : "Date not available")
                                .append("</td></tr>");
                emailBody.append("<tr><td>Description</td><td>")
                                .append(request.getDescription() != null ? request.getDescription()
                                                : "No description provided")
                                .append("</td></tr>");
                String location = request.getLocation() != null ? request.getLocation().toString()
                                : "Location not available";
                emailBody.append("<tr><td>Location</td><td>").append(location).append("</td></tr>");
                emailBody.append("</table>");
                emailBody.append("<p>Thank you for choosing AFU. Our team will reach out to you in a few minutes.</p>");
                emailBody.append("<div class='footer'>");
                emailBody.append("<p>&copy; 2024 AFU. All rights reserved.</p>");
                emailBody.append("<p>Contact: 9642627903</p>");
                emailBody.append("<p>Address: Near YSR Stadium, Madhurawada, Visakhapatnam</p>");
                emailBody.append("</div>");
                emailBody.append("</div>");
                emailBody.append("</body></html>");

                return emailBody.toString();
        }

}