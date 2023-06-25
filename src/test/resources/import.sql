

INSERT INTO categorie (id, nom) VALUES (1, 'boisson chaude');
INSERT INTO categorie (id, nom) VALUES (2, 'dessert');

INSERT INTO type (id, nom) VALUES (1, 'the');
INSERT INTO type (id, nom) VALUES (2, 'sucré');
INSERT INTO type (id, nom) VALUES (3, 'salé');

INSERT INTO taille (id, name) VALUES (10, '15cl');
INSERT INTO taille (id, name) VALUES (1, '20cl');
INSERT INTO taille (id, name) VALUES (2, '50cl');
INSERT INTO taille (id, name) VALUES (4, '1/par');
INSERT INTO taille (id, name) VALUES (5, 'lot de 3');

INSERT INTO produit (id,nom, description, type_id) VALUES  (1,'tysane au pita','', 1);
INSERT INTO produit (id,nom, description, type_id) VALUES  (2,'tysane au vata','', 1);
INSERT INTO produit (id,nom, description, type_id) VALUES  (3,'tysane au kapha','',1);
INSERT INTO produit (id,nom, description, type_id) VALUES  (4,'tiramisu','', 2);
INSERT INTO produit (id,nom, description, type_id) VALUES  (5,'pain perdu','', 2);

INSERT INTO produit_tarif (prix, produit_id, taille_id) VALUES(2.9, 1, 10);
INSERT INTO produit_tarif (prix, produit_id, taille_id) VALUES(3.5, 1, 1);
INSERT INTO produit_tarif (prix, produit_id, taille_id) VALUES(4.5, 1, 2);
INSERT INTO produit_tarif (prix, produit_id, taille_id) VALUES(2.9, 2, 10);
INSERT INTO produit_tarif (prix, produit_id, taille_id) VALUES(3.5, 2, 1);
INSERT INTO produit_tarif (prix, produit_id, taille_id) VALUES(4.5, 2, 2);
INSERT INTO produit_tarif (prix, produit_id, taille_id) VALUES(2.9, 3, 10);
INSERT INTO produit_tarif (prix, produit_id, taille_id) VALUES(3.5, 3, 1);
INSERT INTO produit_tarif (prix, produit_id, taille_id) VALUES(4.5, 3, 2);
INSERT INTO produit_tarif (prix, produit_id, taille_id) VALUES(3.70, 4, 4);
INSERT INTO produit_tarif (prix, produit_id, taille_id) VALUES(3.80, 5, 5);

INSERT  INTO menu (id,nom, description, prix,image_url) VALUES(1,'Box matinale','',15.50,'/image/Box matinale.jpg');
INSERT  INTO menu (id,nom, description, prix,image_url) VALUES(2,'Box viennoiserie','',16.50,'/image/Box viennoiserie.webp');
INSERT  INTO menu (id,nom, description, prix,image_url) VALUES(3,'Chouquette','',5.50,'/image/chouquettes.jpg');

INSERT  INTO produit_categorie(produit_id, categorie_id) VALUES  (1,1);
INSERT  INTO produit_categorie(produit_id, categorie_id) VALUES  (2,1);
INSERT  INTO produit_categorie(produit_id, categorie_id) VALUES  (3,1);
INSERT  INTO produit_categorie(produit_id, categorie_id) VALUES  (4,2);
INSERT  INTO produit_categorie(produit_id, categorie_id) VALUES  (5,2);
INSERT INTO produit_menu (produit_id,taille_id, menu_id) VALUES(1,1,2);

INSERT INTO utilisateur (type, mode_passe, nom,prenom, email,adresse, code_postale,etage, num_tel, ville) VALUES ('admin' ,'$2y$10$CbwwyCMiNAzMxF2iTHGtXOiTY6iTXicdmfy.J58hTPGpyUJJsuEOC', 'abdou','diallo', 'diallo@example.com', '2 rue des tilleuls', '94470', 6, '0145991257', 'creteil');
INSERT INTO utilisateur (type,mode_passe, nom,prenom, email,adresse, code_postale,etage, num_tel, ville) VALUES ('client', '$2y$10$Vc6G4U0mVRhp2LiKWwPJS.hBnfcSfy47yTs7KhgdANu1fVg/FNu9O', 'lionel','messi', 'messi@psg.com','2 rue des tilleuls', '94470', 6, '0145991457', 'creteil');

INSERT INTO utilisateur (type, mode_passe, nom,prenom, email,adresse,code_postale,etage, num_tel, ville) VALUES  ('livreur', '$2y$10$ttf1hco6Oz72tufXMTYAbOA/7P5HqK7THHEakBC848oVCVOk/Oh42', 'tonton','tonton', 'capuccino@capu.com','2 rue des tilleuls', '94470', 6, '0145991457', 'creteil');
INSERT INTO panier (id,client_id,etat_Panier) VALUES (1,2,true);

INSERT INTO commande (id, date_commande, adresse, code_postale,ville,etage,date_livraison, client_id, livreur_id) VALUES (4, '2022-02-14 15:16:22.000000','141 rue des tilleuls','94470','boissy','','2022-02-14 15:30:11.000000', 2,3);


INSERT INTO ligne_de_commande (id, prix, quantiter, commande_id, produit_id, taille_id, panier_id,type ) VALUES  (9, 2.9, 2, 4, 1, 10,1,'ligneDeCommandeProduit');
INSERT INTO commune(id,nom,code_postale) values(1,'sucy','94450');


INSERT INTO livreur_commune (livreur_id, commune_id) VALUES (3, 1);

INSERT INTO photo_article (id, libele, url, produit_id) VALUES (1, 'tysane pita', '/image/tysane pita.jpg', 1);
INSERT INTO photo_article (id, libele, url, produit_id) VALUES (2, 'tysane vata', '/image/tysane vata.jpg', 2);
INSERT INTO photo_article (id, libele, url, produit_id) VALUES (3, 'tysane kapha', '/image/tysane kapha.jpg', 3);
INSERT INTO photo_article (id, libele, url, produit_id) VALUES (4, 'tiramisu', '/image/Tiramisu.jpg', 4);
INSERT INTO photo_article (id, libele, url, produit_id) VALUES (5, 'pain perdu', '/image/pain-perdu-05.webp', 5);
