

INSERT INTO categorie (id, nom) VALUES (1, 'boisson chaude');
INSERT INTO categorie (id, nom) VALUES (2, 'dessert');

INSERT INTO type (id, nom) VALUES (1, 'the');
INSERT INTO type (id, nom) VALUES (2, 'sucré');
INSERT INTO type (id, nom) VALUES (3, 'salé');

INSERT INTO produit (id,nom, description, type_id) VALUES  (1,'tysane au pita','', 1);
INSERT INTO produit (id,nom, description, type_id) VALUES  (2,'tysane au vata','', 1);
INSERT INTO produit (id,nom, description, type_id) VALUES  (3,'tysane au kapha','',1);
INSERT INTO produit (id,nom, description, type_id) VALUES  (4,'tiramisu','', 2);
INSERT INTO produit (id,nom, description, type_id) VALUES  (5,'pain perdu','', 2);
INSERT  INTO menu (id,nom, description, prix) VALUES(1,'Box matinale','',15.50);
INSERT  INTO menu (id,nom, description, prix) VALUES(2,'Box viennoiserie','',16.50);
INSERT  INTO menu (id,nom, description, prix) VALUES(3,'Chouquette','',5.50);

INSERT  INTO produit_categorie(produit_id, categorie_id) VALUES  (1,1);
INSERT  INTO produit_categorie(produit_id, categorie_id) VALUES  (2,1);
INSERT  INTO produit_categorie(produit_id, categorie_id) VALUES  (3,1);
INSERT  INTO produit_categorie(produit_id, categorie_id) VALUES  (4,2);
INSERT  INTO produit_categorie(produit_id, categorie_id) VALUES  (5,2);
    INSERT INTO produit_menu (produit_id, menu_id) VALUES(1,1);

INSERT INTO utilisateur (id, mode_passe, prenom, role, user_name, livreur_id) VALUES (1, '123', 'abdou', 'admin', 'diallo', NULL);
INSERT INTO utilisateur (id, mode_passe, prenom, role, user_name, livreur_id) VALUES (2, '213', 'lionel', 'client', 'messi', NULL);

INSERT INTO utilisateur (id, mode_passe, prenom, role, user_name, livreur_id) VALUES  (3, '345', 'tonton', 'livreur', 'capuccino', null);
INSERT INTO livreur (id, adresse, code_postale, nom, num_tel, prenom, ville, utilisateur_id) VALUES (2, '3 rue george', '94450', 'capuccino', '651123456', 'tonton', 'sucy', 3);
INSERT INTO client (id, adresse, code_postale, etage, mail, nom, numero_tel, prenom, ville, utilisateur_id) VALUES (1, '2 rue des tilleuls', '94470', 6, 'dias94@gmail.com', 'messi', '0145991457', 'lionel', 'creteil', 2);
INSERT INTO panier (id,client_id,etat_Panier) VALUES (1,1,true);
UPDATE  utilisateur SET livreur_id=2 where id=3;

INSERT INTO commande (id, date_commande, adresse, code_postale,ville,etage,date_livraison, client_id, livreur_id) VALUES (4, '2022-02-14 15:16:22.000000','141 rue des tilleuls','94470','boissy','','2022-02-14 15:30:11.000000', 1,2);
INSERT INTO taille (id, name) VALUES (10, '15cl');
INSERT INTO taille (id, name) VALUES (1, '20cl');
INSERT INTO taille (id, name) VALUES (2, '50cl');
INSERT INTO taille (id, name) VALUES (4, '1/par');
INSERT INTO taille (id, name) VALUES (5, 'lot de 3');

INSERT INTO ligne_de_commande (id, prix, quantiter, commande_id, produit_id, taille_id, panier_id,type ) VALUES  (9, 2.9, 2, 4, 1, 10,1,'ligneDeCommandeProduit');
INSERT INTO commune(id,nom,code_postale) values(1,'sucy','94450');


INSERT INTO livreur_commune (livreur_id, commune_id) VALUES (2, 1);

INSERT INTO photo_article (id, libele, url, produit_id) VALUES (1, 'photo thé blanc', 'https://www.bebe-au-naturel.com/images/rep_articles/grandes/29153.jpg', 1);



INSERT INTO produit_tarif (tarif, produit_id, taille_id) VALUES(2.9, 1, 10);
INSERT INTO produit_tarif (tarif, produit_id, taille_id) VALUES(3.5, 1, 1);
INSERT INTO produit_tarif (tarif, produit_id, taille_id) VALUES(4.5, 1, 2);
INSERT INTO produit_tarif (tarif, produit_id, taille_id) VALUES(2.9, 2, 10);
INSERT INTO produit_tarif (tarif, produit_id, taille_id) VALUES(3.5, 2, 1);
INSERT INTO produit_tarif (tarif, produit_id, taille_id) VALUES(4.5, 2, 2);
INSERT INTO produit_tarif (tarif, produit_id, taille_id) VALUES(2.9, 3, 10);
INSERT INTO produit_tarif (tarif, produit_id, taille_id) VALUES(3.5, 3, 1);
INSERT INTO produit_tarif (tarif, produit_id, taille_id) VALUES(4.5, 3, 2);
INSERT INTO produit_tarif (tarif, produit_id, taille_id) VALUES(3.70, 4, 4);
INSERT INTO produit_tarif (tarif, produit_id, taille_id) VALUES(3.80, 5, 5);




