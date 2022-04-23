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