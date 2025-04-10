<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>MiniChatGPT</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        html, body {
            height: 100%;
            margin: 0;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: linear-gradient(135deg, #74ebd5, #acb6e5);
        }

        .full-page-container {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100%;
        }

        .chat-container {
            width: 100%;
            max-width: 700px;
            background-color: #ffffffee;
            padding: 30px;
            border-radius: 20px;
            box-shadow: 0 15px 40px rgba(0, 0, 0, 0.2);
            transition: all 0.3s ease;
        }

        .chat-container:hover {
            transform: scale(1.01);
        }

        h3 {
            color: #3e3e3e;
            font-weight: 600;
            text-align: center;
        }

        .user-msg, .bot-msg {
            margin: 15px 0;
            padding: 12px 20px;
            border-radius: 25px;
            max-width: 85%;
            word-wrap: break-word;
            animation: fadeIn 0.5s ease-in-out;
        }

        .user-msg {
            background-color: #d0ebff;
            color: #084298;
            text-align: right;
            margin-left: auto;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
        }

        .bot-msg {
            background-color: #f1f3f5;
            color: #333;
            text-align: left;
            margin-right: auto;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
        }

        .input-group input[type="text"] {
            border-radius: 30px 0 0 30px;
            padding-left: 20px;
        }

        .input-group .btn {
            border-radius: 0 30px 30px 0;
            padding: 10px 20px;
        }

        .btn-primary {
            background: linear-gradient(to right, #6a11cb, #2575fc);
            border: none;
        }

        .btn-success {
            background: linear-gradient(to right, #00b09b, #96c93d);
            border: none;
        }

        .alert {
            border-radius: 20px;
        }

        @keyframes fadeIn {
            from {
                opacity: 0;
                transform: translateY(10px);
            }
            to {
                opacity: 1;
                transform: translateY(0);
            }
        }

        hr {
            border-top: 2px solid #dee2e6;
            margin: 30px 0;
        }
    </style>
</head>
<body>

<div class="full-page-container">
    <div class="chat-container">

        <h3>🤖 Your Friend Ai</h3>

        <!-- Welcome Message -->
        <c:if test="${not empty message}">
            <div class="alert alert-info text-center">${message}</div>
        </c:if>

        <!-- Chat Display -->
        <c:if test="${not empty userInput}">
            <div class="user-msg"><strong>You:</strong> ${userInput}</div>
        </c:if>
        <c:if test="${not empty response}">
            <div class="bot-msg"><strong>Your Friend:</strong> ${response}</div>
        </c:if>

        <hr/>

        <!-- Ask Question Form -->
        <form action="ask" method="post" class="mb-3">
            <div class="input-group">
                <input type="text" name="userInput" class="form-control" placeholder="Ask your question..." required />
                <button class="btn btn-primary" type="submit">Ask</button>
            </div>
        </form>

        <!-- Set Name Form -->
        <form action="name" method="post">
            <div class="input-group">
                <input type="text" name="name" class="form-control" placeholder="Enter your name..." required />
                <button class="btn btn-success" type="submit">Your Name</button>
            </div>
        </form>

    </div>
</div>

</body>
</html>
