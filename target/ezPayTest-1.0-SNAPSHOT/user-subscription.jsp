<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <script>
        function endDateMinMax() {
            var startDate = document.forms["subscriptionForm"]["startDate"].value;
            var maxDate = new Date(startDate);
            maxDate.setDate(maxDate.getDate() + 90).toString();

            var maxDateString = (maxDate.getFullYear() + "-" + (((maxDate.getMonth() + 1) < 10 ? '0' : '') + (maxDate.getMonth() + 1)) + "-" + ((maxDate.getDate() < 10 ? '0' : '') + maxDate.getDate()));

            document.getElementById("endDate").valueAsDate = null;
            document.getElementById("endDate").disabled = false;
            document.getElementById("endDate").setAttribute("min", startDate);
            document.getElementById("endDate").setAttribute("max", maxDateString);

        }

        function typeOnChange() {
            var type = document.forms["subscriptionForm"]["subscriptionType"].value;
            /*var step = 1;

            if (type === "WEEKLY"){step = 7};
            if (type === "MONTHLY"){step = 30};

            document.getElementById("endDate").valueAsDate = null;
            document.getElementById("endDate").setAttribute("step", step);*/

            //Dropdown Set (dayOfWeekMonth)
            var selectElement = document.getElementById("dayOfWeekMonth");
            var days = ["MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY", "SUNDAY"];

            if (type === "WEEKLY"){
                document.getElementById('dayOfWeekMonth').innerText = null;
                document.getElementById("dayOfWeekMonth").disabled = false;
                for (var i = 0; i < days.length; i++){
                    var day = days[i];
                    selectElement.add(new Option(day, day));
                }
            }
            else if (type === "MONTHLY"){
                document.getElementById('dayOfWeekMonth').innerText = null;
                document.getElementById("dayOfWeekMonth").disabled = false;
                for (var i = 1; i <= 31; i++){
                    selectElement.add(new Option(i, i));
                }
            }
            else{
                document.getElementById('dayOfWeekMonth').innerText = null;
                document.getElementById("dayOfWeekMonth").disabled = true;
            }


        }
    </script>
    <meta charset="ISO-8859-1">
    <title>Ezpay-Subscription</title>
</head>
<body>
<div>
    <h1>User Subscription</h1>
    <form name="subscriptionForm" action="user-subscription-success.jsp" method="post" >
        <table style="with: 85%">
            <tr>
                <td>Subscription Price ($)</td>
                <td><input type="number" name="subscriptionPrice"  pattern="\d+" min="1" required/></td>
            </tr>
            <tr>
                <td>Subscription Type</td>
                <td>
                <select name="subscriptionType" id="subscriptionType"  onchange="typeOnChange()">
                    <option value="DAILY">DAILY</option>
                    <option value="WEEKLY">WEEKLY</option>
                    <option value="MONTHLY">MONTHLY</option>
                </select>
                </td>
            </tr>
            <tr>
                <td>Start Date</td>
                <td><input type="date" name="startDate" id="startDate"  onchange="endDateMinMax()" required/></td>
            </tr>
            <tr>
                <td>End Date</td>
                <td><input type="date" name="endDate" id="endDate"  disabled="true" required/></td>
                <%System.out.println("*****DATE" + request.getAttribute("startDate"));%>
            </tr>
            <tr>
                <td>Invoice Day/Date </td>
                <td><select name="dayOfWeekMonth" id="dayOfWeekMonth" disabled="true"/></td>
            </tr>
        </table>
        <input type="submit" value="Subscribe" style="margin-top: 15px;"/>
    </form>
</div>
</body>

</html>

