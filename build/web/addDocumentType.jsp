<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Ajouter un domaine</title>
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
        <h1>Ajouter un domaine</h1>
        <nav>
            <ul>
                <li><a href="#">Accueil</a></li>
                <li><a href="#">Dashboard</a></li>
                <li><a href="#" class="logout">Déconnecter</a></li>
            </ul>
        </nav>
    </header>
    
    <form action="TypedocumentServletSet" method="post">
        <label for="numTypedocument">Numéro de domaine :</label>
        <input type="text" name="numTypedocument" id="numTypedocument" required><br><br>

        <label for="libelleTypedocument">Nom du domaine :</label>
        <input type="text" name="libelleTypedocument" id="libelleTypedocument" required><br><br>

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
