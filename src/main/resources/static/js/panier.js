function ajouterAuPanier(id){
    //envoyer une requette httpp a cette url ci-dessous
    fetch('/panier/ajouter',{
        //type de requette http que j'envoie
        method:"POST",
        //pour dire au server le format de donnee que j'envoie
        headers:{
            "content-Type":"application/json"
        },
        //ça me convertir l'objet en une chaine de caractere json
        body:JSON.stringify({
            id
        })
    })
}
function diminuerQuantiter(ligneDeCommandeId){
    fetch('/panier/diminuer/'+ligneDeCommandeId,{
        method:"PUT",
        headers:{
            "content-Type":"application/json"
        },
    }).then(()=>window.location.reload())
}
function augmenterQuantiter(ligneDeCommandeId){
    fetch('/panier/augmenter/'+ligneDeCommandeId,{
        method:"PUT",
        headers:{
            "content-Type":"application/json"
        }
    }).then(()=>window.location.reload())
}
function supprimerLigneDeCommande(ligneDeCommandeId){
    fetch('/supprimerLigneDeCommande/'+ligneDeCommandeId, {
        method:"PUT",
        headers:{
            "content-Type":"application/json"
        }
    }).then(()=>window.location.reload())
}
