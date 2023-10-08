function validerFormulaire() {
    var prenom = document.getElementById("prenom").value;
    var date_naissance = document.getElementById("date_naissance").value;
    var adresse = document.getElementById("adresse").value;
    var filiere = document.getElementById("filiere").value;
    var telephone = document.getElementById("telephone").value;
    var email = document.getElementById("email").value;
  
    if (prenom == "" || date_naissance == "" || adresse == "" || filiere == "" || telephone == "" || email == "") {
      alert("Veuillez remplir tous les champs du formulaire");
      return false;
    }
    else {
      return true;
    }
  }


  