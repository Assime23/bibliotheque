function changerPage() {
    let menuDeroulant = document.getElementById("menu_deroulant");
    let optionSelectionnee = menuDeroulant.options[menuDeroulant.selectedIndex].value;
    let estConnecte = false; // Remplacez cette ligne avec votre propre code pour vérifier si l'utilisateur est connecté
    
    if (optionSelectionnee === "enregistrer_doc" && !estConnecte) {
      if (confirm("Vous devez vous connecter ou vous inscrire pour enregistrer un document. Voulez-vous vous inscrire maintenant ?")) {
        window.location.href = "inscription.html";
      }
    } else if (optionSelectionnee === "emprunt_doc" && !estConnecte) {
      if (confirm("Vous devez vous connecter ou vous inscrire pour emprunter un document. Voulez-vous vous inscrire maintenant ?")) {
        window.location.href = "inscription.html";
      }
    } else if (optionSelectionnee === "enregistrer_doc") {
      window.location.href = "document.html";
    } else if (optionSelectionnee === "emprunt_doc") {
      window.location.href = "emprunt_doc.html";
    }
  }

  
  
  