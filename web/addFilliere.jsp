<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Ajouter une filière</title>
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
    </style>
</head>
<body>
    <header>
        <h1>Ajouter une filière</h1>
        <nav>
            <ul>
                <li><a href="#">Accueil</a></li>
                <li><a href="#">Dashboard</a></li>
            </ul>
        </nav>
    </header>
    
    <form action="FilliereServletSet" method="post">
        <label for="numfilliere">Numéro de filière:</label>
        <input type="text" id="numfilliere" name="numfilliere" required><br><br>

        <label for="nomfilliere">Nom de la filière:</label>
        <input type="text" id="nomfilliere" name="nomfilliere" required><br><br>

        <input type="submit" value="Enregistrer">
    </form>

    <footer>
        <ul class="footer">
            <li><a href="Apropos.html">A propos de la Bibliothèque</a></li>
            <li><a href="Liens.html">Liens utiles</a></li>
            <p>&copy; 2023</p>
            <li><a href="Conditions.html">Conditions d'utilisation</a></li>
            <li><a href="Faq.html">Faq</a></li>
        </ul>
    </footer>
</body>
</html>
