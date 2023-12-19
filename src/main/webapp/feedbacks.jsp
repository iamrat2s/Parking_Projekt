<%@ page import="com.example.parhausprj.Feedback" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.parhausprj.FeedbackTableGenerator" %>


<!DOCTYPE html>
<html lang="en">
<head>
  <title>Feedbacks</title>
  <link rel="stylesheet" type="text/css" href="feedbacks.css">
  <style>
    table {
      margin-bottom: 20px;
    }
    th, td {
      padding: 10px;
      text-align: left;
    }
  </style>
</head>
<body>
<h2>Alle Feedbacks:</h2>

<h3>Complains:</h3>
<%
  List<Feedback> feedbacks = (List<Feedback>) request.getAttribute("feedbackList");
  String complaintTable = FeedbackTableGenerator.generateComplaintTable(feedbacks);
  out.println(complaintTable);
%>

<h3>Feedbacks:</h3>
<%
  String feedbackTable = FeedbackTableGenerator.generateFeedbackTable(feedbacks);
  out.println(feedbackTable);
%>

</body>
</html>
