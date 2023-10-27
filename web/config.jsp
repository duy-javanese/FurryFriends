<%-- 
    Document   : config
    Created on : Oct 26, 2023, 8:26:36 PM
    Author     : DUY
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Trang Cấu Hình</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                margin: 0;
                padding: 0;
                background-color: #f4f4f4;
            }
            .container {
                max-width: 600px;
                margin: 0 auto;
                padding: 20px;
                background-color: #fff;
                border-radius: 5px;
                box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
            }
            label {
                display: block;
                margin-bottom: 5px;
                font-weight: bold;
            }
            input[type="text"],
            textarea {
                width: 100%;
                padding: 10px;
                margin-bottom: 10px;
                border: 1px solid #ccc;
                border-radius: 3px;
            }
            button {
                background-color: #007bff;
                color: #fff;
                border: none;
                border-radius: 3px;
                padding: 10px 20px;
                cursor: pointer;
            }
            button:hover {
                background-color: #0056b3;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <h1>Cấu hình hệ thống</h1>
            <form action="MainController" method="post">
                <label for="about">About Us:</label>
                <textarea id="about" name="about" rows="4" required></textarea>
                <button type="submit" name="action" value="UpdateAboutUs">Cập nhật</button>
            </form>

            <form action="MainController" method="post">
                <label for="phoneNumber">Contact:</label>
                <input type="text" id="phoneNumber" name="phone" required="">
                <button type="submit" name="action" value="UpdateContact">Cập nhật</button>
            </form>
            ${requestScope.ERROR}
        </div>
    </body>
</html>
