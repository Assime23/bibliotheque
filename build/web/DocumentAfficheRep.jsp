<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="model.entities.Document" %>
<%
List<Document> documents = (List<Document>) request.getAttribute("documents");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Liste des documents</title>
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
        <h1>Liste des documents</h1>
        <nav>
            <ul>
                <li><a href="index.html">Accueil</a></li>
                <li><a href="#">Dashboard</a></li>
                <li><a href="#">Déconnexion</a></li>
            </ul>
        </nav>
    </header>

    <%-- Afficher la liste des documents --%>
    <% if (documents != null && !documents.isEmpty()) { %>
        <table>
            <tr>
                <th>Numéro du document</th>
                <th>Titre</th>
                <th>État</th>
                <th>Résumé</th>
                <th>Mot-clé</th>
                <%-- Ajouter d'autres colonnes si nécessaire --%>
            </tr>
            <% for (Document document : documents) { %>
                <tr>
                    <td><%= document.getNumdocument() %></td>
                    <td><%= document.getNomdocument() %></td>
                    <td><%= document.getEtat() %></td>
                    <td><%= document.getResume() %></td>
                    <td><%= document.getMotcle() %></td>
                    <%-- Ajouter les valeurs des autres colonnes --%>
                </tr>
            <% } %>
        </table>
    <% } else { %>
        <p>Aucun document disponible.</p>
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
