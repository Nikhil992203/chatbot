<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>MiniChatgpt</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f8f9fa;
            padding-top: 40px;
        }
        .chat-container {
            max-width: 600px;
            margin: auto;
            background-color: #fff;
            padding: 25px;
            border-radius: 15px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }
        .bot-msg, .user-msg {
            margin: 10px 0;
        }
        .bot-msg {
            text-align: left;
            color: #212529;
        }
        .user-msg {
            text-align: right;
            color: #0d6efd;
        }
    </style>
</head>
<body>

<div class="chat-container">
    <h3 class="text-center mb-4">🤖 Your Friend Ai</h3>

    <!-- Welcome Message -->
    <c:if test="${not empty message}">
        <div class="alert alert-info text-center">${message}</div>
    </c:if>

    <!-- Chat Display -->
    <c:if test="${not empty userInput}">
        <div class="user-msg"><strong>You:</strong> ${userInput}</div>
    </c:if>
    <c:if test="${not empty response}">
        <div class="bot-msg"><strong>your friend:</strong> ${response}</div>
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
            <button class="btn btn-success" type="submit">your Name</button>
        </div>
    </form>
</div>

</body>
</html>
