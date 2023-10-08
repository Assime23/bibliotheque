<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="model.entities.Filliere" %>
<%
String message = (String) request.getAttribute("message");
List<Filliere> fillieres = (List<Filliere>) request.getAttribute("fillieres");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Ajouter une filière</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: Arial, sans-serif;
        }

        body {
            background-color: #f5f5f5;
            padding: 20px;
        }

        header {
            background-color: #063547;
            padding: 20px;
            color: #fff;
            text-align: center;
        }

        h1 {
            color: #47B99E;
            margin-top: 20px;
        }

        form {
            background-color: #fff;
            padding: 20px;
            border-radius: 5px;
            margin-top: 20px;
        }

        label {
            display: block;
            margin-bottom: 10px;
            font-weight: bold;
            color: #47B99E;
        }

        input[type="text"] {
            width: 100%;
            height: 30px;
            padding: 5px;
            border: 1px solid #ccc;
            border-radius: 5px;
            font-size: 14px;
            color: #333;
        }

        input[type="submit"] {
            background-color: #47B99E;
            color: #fff;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            font-size: 16px;
            cursor: pointer;
        }

        p.message {
            color: #47B99E;
            margin-top: 20px;
            text-align: center;
        }

        h2 {
            color: #47B99E;
            margin-top: 30px;
            text-align: center;
        }

        table {
            width: 100%;
            margin-top: 20px;
            border-collapse: collapse;
        }

        th, td {
            padding: 10px;
            border: 1px solid #ccc;
            text-align: center;
        }

        th {
            background-color: #47B99E;
            color: #fff;
        }

        footer {
            background-color: #063547;
            color: #fff;
            padding: 20px;
            text-align: center;
            margin-top: 20px;
        }

        footer ul {
            list-style-type: none;
            margin-top: 20px;
            text-align: center;
        }

        footer ul li {
            display: inline-block;
            margin-right: 20px;
        }

        footer ul li a {
            text-decoration: none;
            color: #fff;
            font-weight: bold;
        }

        footer p {
            margin-top: 20px;
        }
    </style>
</head>
<body>
<header>
    <h1>Ajouter une filière</h1>
    <nav>
        <ul>
            <li><a href="index.html">Accueil</a></li>
            <li><a href="#">Dashboard</a></li>
        </ul>
    </nav>
</header>

<%-- Display the message if any --%>
<% if (message != null && !message.isEmpty()) { %>
    <p class="message"><%= message %></p>
<% } %>

<form action="FilliereServletSet" method="post">
    <label for="numfilliere">Numéro de filière:</label>
    <input type="text" id="numfilliere" name="numfilliere" required><br><br>

    <label for="nomfilliere">Nom de la filière:</label>
    <input type="text" id="nomfilliere" name="nomfilliere" required><br><br>

    <input type="submit" value="Enregistrer">
</form>

<%-- Display the list of fillieres --%>
<% if (fillieres != null && !fillieres.isEmpty()) { %>
    <h2>Liste des filières:</h2>
    <table>
        <tr>
            <th>Numéro de filière</th>
            <th>Nom de la filière</th>
        </tr>
        <% for (Filliere filliere : fillieres) { %>
            <tr>
                <td><%= filliere.getNumfilliere() %></td>
                <td><%= filliere.getNomfilliere() %></td>
            </tr>
        <% } %>
    </table>
<% } %>

<footer>
    <ul>
        <li><a href="Apropos.html">A propos de la Bibliothèque</a></li>
        <li><a href="Liens.html">Liens utiles</a></li>
        <li><a href="Conditions.html">Conditions d'utilisation</a></li>
        <li><a href="Faq.html">Faq</a></li>
    </ul>
    <p>&copy; 2023</p>
</footer>
</body>
</html>
