<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="model.entities.Filliere" %>
<%@ page import="services.UtilisateurManager" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Ajouter un utilisateur</title>
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
         <h1>Inscription</h1>
    </header>

    <form action="UtilisateurServletSet1" method="post">
        <label for="numUtilisateur">INE :</label>
        <input type="text" name="numUtilisateur" id="numUtilisateur" required><br><br>

        <label for="nomUtilisateur">Nom :</label>
        <input type="text" name="nomUtilisateur" id="nomUtilisateur" required><br><br>

        <label for="prenomUtilisateur">Prénom :</label>
        <input type="text" name="prenomUtilisateur" id="prenomUtilisateur" required><br><br>

        <label for="dateNaissanceUtilisateur">Date de naissance :</label>
        <input type="date" name="dateNaissanceUtilisateur" id="dateNaissanceUtilisateur" required><br><br>
                <label for="emailUtilisateur">Email :</label>
        <input type="email" name="emailUtilisateur" id="emailUtilisateur" required><br><br>

       
  <% List<Filliere> fillieres = (List<Filliere>) request.getAttribute("fillieres"); %>
<label for="filliereUtilisateur">Fillière :</label>
<select id="filliereUtilisateur" name="filliereUtilisateur" required>
    <option value="" selected disabled hidden>Choisir une fillière</option>
    <% if (fillieres != null) { %>
        <% for (Filliere filliere : fillieres) { %>
            <option value="<%= filliere.getNumfilliere() %>"><%= filliere.getNomfilliere() %></option>
        <% } %>
    <% } %>
</select><br>

        <label for="adresseUtilisateur">Adresse :</label>
        <input type="text" name="adresseUtilisateur" id="adresseUtilisateur" required><br><br>

        <label for="telephoneUtilisateur">Téléphone :</label>
        <input type="tel" name="telephoneUtilisateur" id="telephoneUtilisateur"><br><br>

        <label for="motdepasseUtilisateur">Mot de passe :</label>
        <input type="password" name="motdepasseUtilisateur" id="motdepasseUtilisateur" pattern=".{8,}" title="Le mot de passe doit contenir au moins 8 caractères" required><br><br>




        <input type="submit" value="Inscription">
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
