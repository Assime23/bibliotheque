// Tableau des livres (exemples)
const livres = [
    {
      titre: 'Titre du livre 1',
      resume: 'Résumé du livre 1',
      etat: 'disponible'
    },
    {
      titre: 'Titre du livre 2',
      resume: 'Résumé du livre 2',
      etat: 'prêté'
    },
    {
      titre: 'Titre du livre 3',
      resume: 'Résumé du livre 3',
      etat: 'disponible'
    }
    // Ajouter les autres livres ici
  ];
  
  // Sélectionne le corps de la table
  const tableBody = document.querySelector('table tbody');
  
  // Ajoute une ligne pour chaque livre dans le tableau
  livres.forEach((livre, index) => {
    // Crée une nouvelle ligne
    const newRow = document.createElement('tr');
  
    // Ajoute les cellules pour le titre, le résumé, l'état et le bouton "Emprunter" du livre
    const titreCell = document.createElement('td');
    titreCell.textContent = livre.titre;
    newRow.appendChild(titreCell);
  
    const resumeCell = document.createElement('td');
    resumeCell.textContent = livre.resume;
    newRow.appendChild(resumeCell);
  
    const etatCell = document.createElement('td');
    etatCell.textContent = livre.etat;
    newRow.appendChild(etatCell);
  
    const emprunterCell = document.createElement('td');
    const emprunterBtn = document.createElement('button');
    emprunterBtn.textContent = 'Emprunter';
    emprunterBtn.addEventListener('click', () => {
      if (livre.etat === 'disponible') {
        livre.etat = 'prêté';
        etatCell.textContent = livre.etat;
        emprunterBtn.disabled = true;
      }
    });
    emprunterCell.appendChild(emprunterBtn);
    newRow.appendChild(emprunterCell);
  
    // Ajoute la ligne à la table
    tableBody.appendChild(newRow);
  });
  