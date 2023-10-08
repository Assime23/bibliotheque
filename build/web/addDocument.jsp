<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="model.entities.Dicipline" %>
<%@ page import="services.DiciplineManager" %>
<%@ page import="model.entities.Typedocument" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Ajouter un document</title>
     <style>
/* Styles généraux */
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

/* Styles de l'en-tête */
header {
  
     background-color: #063547;
    color: #fff;
    padding: 20px;
    text-align: center;
    margin-top: 20px;
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

/* Styles du pied de page */
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

/* Styles pour les éléments de l'en-tête et du pied de page */
header ul li,
footer ul li {
    display: inline-block;
    margin-right: 20px;
    color: #fff;
    font-weight: bold;
}


    </style>
</head>
<body>
    <header>
        <h1>Ajouter un document</h1>
        <nav>
            <ul>
               
                <li> <form action="deshEtudiant" method="post">
                        <input type="submit" value="Dashboard">
                    </form></li>
                <li> <form action="LogoutServlet" method="post">
                        <input type="submit" value="Déconnecter">
                    </form></li>
            </ul>
        </nav>
    </header>

    <form action="DocumentServletSet" method="post">
       

        <label for="titre">Titre :</label>
        <input type="text" name="titre" id="titre" required><br><br>

       <label for="etat">État :</label>
<select id="etat" name="etat">
    <option value="disponible">Disponible</option>
    <option value="indisponible">Indisponible</option>
</select><br><br>


        <label for="resume">Résumé :</label>
        <textarea name="resume" id="resume"></textarea><br><br>

        <label for="motcle">Mot-clé :</label>
        <input type="text" name="motcle" id="motcle"><br><br>

        <!-- Autres champs du formulaire pour les informations du document -->

        <% List<Dicipline> disciplines = (List<Dicipline>) request.getAttribute("disciplines"); %>
        <label for="dicipline">Discipline :</label>
        <select id="dicipline" name="dicipline" required>
            <option value="" selected disabled hidden>Choisir une discipline</option>
            <% if (disciplines != null) { %>
                <% for (Dicipline discipline : disciplines) { %>
                    <option value="<%= discipline.getNumdicipline() %>"><%= discipline.getNomdicipline() %></option>
                <% } %>
            <% } %>
        </select><br>
        
      <% List<Typedocument> typedocuments = (List<Typedocument>) request.getAttribute("documentTypes"); %>
<label for="typedocument">Type du document :</label>
<select id="typedocument" name="typedocument" required>
    <option value="" selected disabled hidden>Choisir le type de document</option>
    <% if (typedocuments != null) { %>
        <% for (Typedocument typedocument : typedocuments) { %>
            <option value="<%= typedocument.getNumtypedocument() %>"><%= typedocument.getNomtypedocument()%></option>
        <% } %>
    <% } %>
</select><br>


        <input type="submit" value="Enregistrer">
    </form>

    <footer>
         <ul class="foter">
            <li><a href="Apropos.html">A propos de la Bibliothèque</a></li>
            <li><a href="Liens.html">Liens utiles</a></li>
            <p>&copy; 2023</p>
            <li><a href="Conditions.html">Conditions d'utilisation</a></li>
            <li><a href="Faq.html">Faq</a></li>
        </ul>
    </footer>
</body>
</html>
