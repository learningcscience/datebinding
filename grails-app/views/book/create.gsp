<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 1/6/2022
  Time: 1:50 PM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title></title>
</head>

<body>


<g:form action="save">


    Title: <g:textField name="title"></g:textField>

    <br>



    Published:








    <input type="text" name="published_dayMonthYear" />



    <input type="text" name="published_hourMin" id="startDateTime_hourMin" value="8:00" maxLength="5" size="3.5" pattern="^([1-9]|[1][0-2]):[0-5][0-9]$" autocomplete="off" placeholder="hh:ss" />
    <select name="published_meridian" id="startDateTime_meridian" >
        <option value="AM" selected="selected" >AM</option>
        <option value="PM" >PM</option>
    </select>


    <select name="published_timeZone" id="startDateTime_timeZone" >
        <option value="US/Hawaii" >(GMT-10:00) US/Hawaii</option>
        <option value="US/Alaska" >(GMT-09:00) US/Alaska</option>
        <option value="US/Pacific" >(GMT-08:00) US/Pacific</option>
        <option value="US/Arizona" >(GMT-07:00) US/Arizona</option>
        <option value="US/Mountain" selected="selected" >(GMT-07:00) US/Mountain</option>
        <option value="US/Central" >(GMT-06:00) US/Central</option>
        <option value="US/Eastern" >(GMT-05:00) US/Eastern</option>
        <option value="US/East-Indiana" >(GMT-05:00) US/East-Indiana</option>
    </select>







    <br>


    <g:submitButton name="Submit"></g:submitButton>

</g:form>



</body>
</html>