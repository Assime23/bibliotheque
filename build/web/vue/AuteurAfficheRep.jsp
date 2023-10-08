<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="model.entities.Auteur" %>
<%
String message = (String) request.getAttribute("message");
List<Auteur> auteurs = (List<Auteur>) request.getAttribute("auteurs");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Enregistrer un auteur</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: sans-serif;
        }

        body {
            background-color: #F5F6F9;
        }

        header {
            padding: 20px;
            background-color: #063547;
            color: white;
            text-align: center;
        }

        h1 {
            text-align: center;
            margin-top: 50px;
            color: #47B99E;
        }

        form {
            width: 50%;
            margin: 0 auto;
            padding: 20px;
            background-color: white;
            border-radius: 10px;
        }

        label {
            display: block;
            margin-bottom: 10px;
            font-weight: bold;
            color: blue;
        }

        input[type="text"] {
            width: 100%;
            height: 30px;
            padding: 5px;
            border: 1px solid #ccc;
            border-radius: 5px;
            font-size: 14px;
            color: black;
        }

        input[type="submit"] {
            background-color: #47B99E;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            font-size: 16px;
            cursor: pointer;
            width: auto;
            max-width: 20%;
            margin-left: 50px;
            text-align: center;
        }

        p {
            margin-top: 20px;
            text-align: center;
            color: #47B99E;
        }

        h2 {
            margin-top: 30px;
            text-align: center;
            color: #47B99E;
        }

        table {
            width: 80%;
            margin: 0 auto;
            border-collapse: collapse;
        }

        th, td {
            padding: 10px;
            border: 1px solid #ccc;
            text-align: center;
        }

        th {
            background-color: #47B99E;
            color: white;
        }

        footer {
            padding: 20px;
            background-color: #063547;
            color: white;
            text-align: center;
        }

        footer ul.foter {
            list-style-type: none;
            text-align: center;
            margin-top: 20px;
        }

        footer ul.foter li {
            display: inline-block;
            margin-right: 20px;
        }

        footer ul.foter li a {
            text-decoration: none;
            color: white;
            font-weight: bold;
        }
    </style>
</head>
<body>
<header>
    <h1>Enregistrer un auteur</h1>
    <nav>
        <ul>
            <li><a href="#">Accueil</a></li>
            <li><a href="#">Dashboard</a></li>
        </ul>
    </nav>
</header>

<%-- Afficher le message s'il y en a un --%>
<% if (message != null && !message.isEmpty()) { %>
    <p><%= message %></p>
<% } %>

<form action="AuteurServletSet" method="post">
    <label for="numauteur">Numéro d'auteur:</label>
    <input type="text" id="numauteur" name="numauteur" required><br><br>

    <label for="nomauteur">Nom de l'auteur:</label>
    <input type="text" id="nomauteur" name="nomauteur" required><br><br>

    <label for="prenomauteur">Prénom de l'auteur:</label>
    <input type="text" id="prenomauteur" name="prenomauteur" required><br><br>

    <input type="submit" value="Enregistrer">
</form>

<%-- Afficher la liste des auteurs --%>
<% if (auteurs != null && !auteurs.isEmpty()) { %>
    <h2>Liste des auteurs:</h2>
    <table>
        <tr>
            <th>Numéro d'auteur</th>
            <th>Nom de l'auteur</th>
            <th>Prénom de l'auteur</th>
        </tr>
        <% for (Auteur auteur : auteurs) { %>
            <tr>
                <td><%= auteur.getNumauteur() %></td>
                <td><%= auteur.getNomauteur() %></td>
                <td><%= auteur.getPrenomauteur() %></td>
            </tr>
        <% } %>
    </table>
<% } %>

<footer>
    <ul class="foter">
        <li><a href="Apropos.html">A propos de la Bibliothèque</a></li>
        <li><a href="Liens.html">Liens utiles</a></li>
        <p>Copyright © 2023</p>
        <li><a href="Conditions.html">Conditions d'utilisation</a></li>
        <li><a href="Faq.html">Faq</a></li>
    </ul>
</footer>
</body>
</html>
