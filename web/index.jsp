<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    // Récupérer le message d'erreur depuis l'attribut de requête
    String errorMessage = (String) request.getAttribute("errorMessage");
%>
<!DOCTYPE html>
<html lang="fr">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Bibliothèque en ligne</title>
  <style>
    /* Styles CSS pour la mise en page */
        /* Styles CSS pour la mise en page */
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

    .container {
        width: 80%;
        max-width: 600px;
        margin: 0 auto;
        padding: 20px;
        background-color: white;
        border-radius: 10px;
        box-shadow: 0 0 5px rgba(0, 0, 0, 0.1);
    }

    form {
        margin-top: 20px;
    }

    label {
        display: block;
        margin-bottom: 10px;
        font-weight: bold;
        color: blue;
    }

    input[type="text"],
    input[type="password"],
    input[type="email"],
    input[type="date"],
    input[type="tel"] {
        width: 100%;
        height: 30px;
        padding: 5px;
        border: 1px solid #ccc;
        border-radius: 5px;
        font-size: 14px;
        color: black;
    }

    input[type="submit"] {
        display: block;
        width: 100%;
        padding: 10px 20px;
        margin-top: 20px;
        background-color: #47B99E;
        color: white;
        border: none;
        border-radius: 5px;
        font-size: 16px;
        cursor: pointer;
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

    footer ul.footer {
        list-style-type: none;
        text-align: center;
        margin-top: 20px;
    }

    footer ul.footer li {
        display: inline-block;
        margin-right: 20px;
    }

    footer ul.footer li a {
        text-decoration: none;
        color: white;
        font-weight: bold;
    }
    html, body {
    height: 100%;
}

body {
    display: flex;
    flex-direction: column;
}

footer {
    margin-top: auto;
}

    /* ... Votre style CSS ici ... */
  </style>
  <script>
    function redirectToServlet() {
      window.location.href = "addUser";
    }
    function redirectToServlet1() {
      window.location.href = "LoginServlet";
    }
  </script>
</head>
<body>
  <header>
    <h1>Bienvenue dans la Bibliothèque en ligne</h1>
  </header>

  <div class="container">
    <p>Échangez et partagez vos ressources documentaires avec d'autres étudiants.</p>
    <form action="LoginServlet" method="POST">
      

      <label for="matricule">Matricule ou adresse e-mail :</label>
      <input type="text" id="matricule" name="matricule" required>

      <label for="password">Mot de passe :</label>
      <input type="password" id="password" name="password" required>
      
      <!-- Afficher le message d'erreur s'il est présent -->
      <c:if test="${not empty errorMessage}">
        <p style="color: red;">${errorMessage}</p>
      </c:if>

      <input type="submit" value="Se connecter">
    </form>
    <p>Si vous n'avez pas de compte, veuillez vous <button onclick="redirectToServlet()">S'inscrire</button>.</p>
  </div>

  <footer>
    <ul class="footer">
      <li><a href="Apropos.html">À propos de la Bibliothèque</a></li>
      <li><a href="Liens.html">Liens utiles</a></li>
      <li>&copy; 2023</li>
      <li><a href="Conditions.html">Conditions d'utilisation</a></li>
      <li><a href="Faq.html">FAQ</a></li>
    </ul>
  </footer>
</body>
</html>
