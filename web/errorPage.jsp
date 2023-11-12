<%-- 
    Document   : errorPage
    Created on : Oct 5, 2023, 1:23:19 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error Page</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f4f4f4;
                color: #333;
                margin: 0;
                padding: 0;
                display: flex;
                align-items: center;
                justify-content: center;
                height: 100vh;
            }

            h1 {
                color: #d9534f;
                display: flex;
                align-items: center;
                justify-content: center;
            }

            div {
                text-align: center;
            }

            a {
                display: inline-block;
                padding: 10px 20px;
                background-color: #5bc0de; /* Bootstrap's info color for emphasis */
                color: #fff;
                text-decoration: none;
                border-radius: 5px;
                transition: background-color 0.3s ease;
            }

            a:hover {
                background-color: #46b8da; /* Darker shade for hover effect */
            }
        </style>
    </head>
    <body>
        <div>
            <h1>Có lỗi xảy ra!</h1>
            <p>Xin lỗi, đã có lỗi xảy ra trong quá trình xử lý yêu cầu của bạn.</p>
            <a href="/home">Quay lại trang chủ</a>
        </div>
    </body>
</html>

