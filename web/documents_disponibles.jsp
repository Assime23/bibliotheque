<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="model.entities.Document" %>
<%@ page import="model.entities.Dicipline" %>
<%@ page import="model.entities.Utilisateur" %>
<%@ page import="model.entities.Typedocument" %>
<%@ page import="jakarta.servlet.http.HttpServletRequest" %>

<%
// Récupérer l'objet Utilisateur depuis le backend

Utilisateur utilisateur = (Utilisateur) request.getAttribute("utilisateur");
//Collection<Document> documents = utilisateur.getDocumentCollection();
%>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Documents Disponibles</title>
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
 <h1>Documents Disponibles</h1>      <nav>
            <ul>
                <li> <form action="addDocument" >
                        <input type="submit" value="Ajouter un document">
                    </form></li>
               
                     <li> <form action="recherche" method="post">
                        <input type="submit" value="Recherche">
                         </form></li><!-- comment -->
                <li> <form action="LogoutServlet" method="post">
                        <input type="submit" value="Déconnecter">
                    </form></li>
                    
                    
                    
            </ul>
        </nav>
  </header>
   
    <table border="1">
        <tr>
            <th>Nom Document</th>
            <th>Résumé</th>
             <th>Mots-clés</th>
             <th>Discipline</th>
            <th>Type de Document</th>
            <th>Propriétaire</th>
            <!-- Ajoutez d'autres colonnes si nécessaire -->
        </tr>
        <%-- Parcours de la liste des documents disponibles --%>
        <% List<Document> documents = (List<Document>) request.getAttribute("documents");
           for (Document document : documents) { %>
            <tr>
                <td><%= document.getNomdocument() %></td>
                <td><%= document.getResume() %></td>
                <td><%= document.getMotcle() %></td>
                <td><%= document.getFkNumdicipline().getNomdicipline() %></td>
                <td><%= document.getFkTypedocument().getNomtypedocument() %></td>
                <td><%= document.getFkUtilisateur().getNom() %></td>
                <!-- Ajoutez d'autres colonnes si nécessaire -->
                 <td>
            <form action="addEmprunt" >
                <input type="hidden" name="numdocument" value="<%= document.getNumdocument() %>">
                <input type="submit" value="Emprunt">
            </form>
        </td>
            </tr>
        <% } %>
    </table>
    
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
