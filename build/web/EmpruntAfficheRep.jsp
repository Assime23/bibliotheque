<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="model.entities.Emprunt" %>
<%
String message = (String) request.getAttribute("message");
List<Emprunt> emprunts = (List<Emprunt>) request.getAttribute("emprunts");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Afficher les emprunts</title>
    <style>
    html, body {
            height: 100%;
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


        body {
            display: flex;
            flex-direction: column;
            margin: 0;
        }

        header {
            flex-shrink: 0;
            padding: 20px;
            background-color: #063547;
            color: white;
            text-align: center;
        }

        h1 {
            margin-top: 50px;
            color: #47B99E;
        }

        nav ul {
            list-style-type: none;
            text-align: center;
            margin-top: 20px;
        }

        nav ul li {
            display: inline-block;
            margin-right: 20px;
        }

        nav ul li a {
            text-decoration: none;
            color: white;
            font-weight: bold;
        }

        nav ul li a.logout {
            color: red;
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

        input[type="text"], input[type="password"], input[type="email"], input[type="date"], input[type="tel"] {
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

        footer {
            flex-shrink: 0;
            padding: 20px;
            background-color: #063547;
            color: white;
            text-align: center;
            margin-top: auto;
        }

        footer ul {
            list-style-type: none;
            text-align: center;
            margin-top: 20px;
        }

        footer ul li {
            display: inline-block;
            margin-right: 20px;
        }

        footer ul li a {
            text-decoration: none;
            color: white;
            font-weight: bold;
        }    </style>
</head>
<body>
    <header>
        <h1>Afficher les emprunts</h1>
        <nav>
            <ul>
                <li><a href="index.html">Accueil</a></li>
                <li><a href="#">Dashboard</a></li>
                <li><a href="#">Déconnexion</a></li>
            </ul>
        </nav>
    </header>
    
    <%-- Afficher le message s'il y en a un --%>
    <% if (message != null && !message.isEmpty()) { %>
        <p><%= message %></p>
    <% } %>

    <%-- Afficher la liste des emprunts --%>
    <% if (emprunts != null && !emprunts.isEmpty()) { %>
        <h2>Liste des emprunts :</h2>
        <table>
            <tr>
                <th>Numéro de l'emprunt</th>
                <th>Date de l'emprunt</th>
                <th>Date de retour</th>
                <th>Utilisateur</th>
                <th>Document</th>
                <th>Action</th>
            </tr>
            <% for (Emprunt emprunt : emprunts) { %>
                <tr>
                    <td><%= emprunt.getNumemprunt() %></td>
                    <td><%= emprunt.getDateemprunt() %></td>
                    <td><%= emprunt.getDateretour() %></td>
                    <td><%= emprunt.getFkUtilisateur().getNom() %></td>
                    <td><%= emprunt.getFkDocument().getNomdocument() %></td>
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
