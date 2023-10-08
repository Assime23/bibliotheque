<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="model.entities.Utilisateur" %>
<%@ page import="model.entities.Document" %>
<%@ page import="services.UtilisateurManager" %>
<%@ page import="services.DocumentManager" %>
<%@ page import="jakarta.servlet.http.HttpServletRequest" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Ajouter un emprunt</title>
    <style>
        /* Styles CSS */
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
        <nav>
            <ul>
                <li><a href="index.html">Accueil</a></li>
                <li><a href="#">Dashboard</a></li>
                <li><a href="#" class="logout">Déconnecter</a></li>
            </ul>
        </nav>
        <h1>Ajouter un emprunt</h1>
    </header>

    <form action="EmpruntServletSet" method="post">
        
          <form action="EmpruntServletSet" method="post">
        <label for="numEmprunt">Numéro d'emprunt :</label>
        <input type="text" name="numEmprunt" id="numEmprunt" required><br><br>

        
       
        <label for="utilisateur">Utilisateur :</label>
        <select id="utilisateur" name="utilisateur" required>
            <%-- Première option pour l'utilisateur connecté, désactivée (non modifiable) --%>
            <% Utilisateur utilisateurConnecte = (Utilisateur) request.getSession().getAttribute("utilisateur"); %>
            <option value="<%= utilisateurConnecte.getNumutilisateur() %>" disabled selected><%= utilisateurConnecte.getNom() %></option>
            
            <%-- Options pour les autres utilisateurs, désactivées (non modifiables) --%>
            
        </select><br><br>

               <label for="document">Document :</label>
        <select id="document" name="document" required>
            <option value="" selected disabled hidden>Choisir un document</option>
            <% List<Document> documents = (List<Document>) request.getAttribute("documents"); %>
            <% if (documents != null) { %>
                <% for (Document document : documents) { %>
                    <option value="<%= document.getNumdocument() %>"><%= document.getNomdocument() %></option>
                <% } %>
            <% } %>
        </select><br><br>
        
          <label for="dateEmprunt">Date d'emprunt :</label>
        <input type="date" name="dateEmprunt" id="dateEmprunt" required><br><br>

        <label for="dateRetour">Date de retour :</label>
        <input type="date" name="dateRetour" id="dateRetour" required><br><br>

        <input type="submit" value="Enregistrer">
    </form>

    <footer>
        <ul class="foter">
            <li><a href="Apropos.html">À propos de la Bibliothèque</a></li>
            <li><a href="Liens.html">Liens utiles</a></li>
            <p>&copy; 2023</p>
            <li><a href="Conditions.html">Conditions d'utilisation</a></li>
            <li><a href="Faq.html">FAQ</a></li>
        </ul>
    </footer>
</body>
</html>
