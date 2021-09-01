<%@ page import="com.example.ezpaytest.Subscription" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<% Subscription subscription = new Subscription(request.getParameter("subscriptionPrice"), request.getParameter("subscriptionType"),
        request.getParameter("startDate"), request.getParameter("endDate"), request.getParameter("dayOfWeekMonth"));%>
<head>
    <meta charset="ISO-8859-1">
    <title>Ezpay-Subscription</title>
</head>
<body>
<div>
    <h1>Successfully Subscribed</h1>
    <table style="with: 85%">
        <tr>
            <td>Subscription Price ($)</td>
            <td><%= subscription.price %></td>
        </tr>
        <tr>
            <td>Subscription Type</td>
            <td><%= subscription.type %></td>
        </tr>
        <tr>
            <td>Invoice Dates</td>
        </tr>
        <tr>
            <td>
                <ul>
                    <% for(LocalDate date : subscription.invoiceDates){ %>
                    <li><%= date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) %></li>
                    <%}%>
                </ul>
            </td>
        </tr>
    </table>
</div>
</body>
</html>