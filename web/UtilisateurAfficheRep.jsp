<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="model.entities.Utilisateur" %>
<%
String message = (String) request.getAttribute("message");
List<Utilisateur> utilisateurs = (List<Utilisateur>) request.getAttribute("utilisateurs");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Afficher les utilisateurs</title>
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
    <h1>Afficher les utilisateurs</h1>
    <nav>
        <ul>
            <li><a href="#">Accueil</a></li>
            <li><a href="#">Dashboard</a></li>
            <li><a href="#">Déconnexion</a></li>
        </ul>
    </nav>
</header>

<%-- Afficher le message s'il y en a un --%>
<% if (message != null && !message.isEmpty()) { %>
    <p><%= message %></p>
<% } %>

<%-- Afficher la liste des utilisateurs --%>
<% if (utilisateurs != null && !utilisateurs.isEmpty()) { %>
    <table>
        <tr>
            <th>ID</th>
            <th>Nom</th>
            <th>Prénom</th>
            <th>Email</th>
            <th>Adresse</th>
            <th>Téléphone</th>
            <th>Date de naissance</th>
            <th>Action</th>
        </tr>
        <% for (Utilisateur utilisateur : utilisateurs) { %>
            <tr>
                <td><%= utilisateur.getNumutilisateur() %></td>
                <td><%= utilisateur.getNom() %></td>
                <td><%= utilisateur.getPrenom() %></td>
                <td><%= utilisateur.getEmail() %></td>
                <td><%= utilisateur.getAdresse() %></td>
                <td><%= utilisateur.getTelephone() %></td>
                <td><%= utilisateur.getDatenaissance() %></td>
                <td>
                    <button>Modifier</button>
                    <button>Supprimer</button>
                </td>
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