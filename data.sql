/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  to
 * Created: 1 juil. 2023
 */

-- Changement dans la base 
ALTER TABLE anomalie RENAME TO anomalie_suivie;
ALTER TABLE anomalie_suivie DROP COLUMN id_categorie_anomalie;
ALTER TABLE suivie DROP COLUMN suivie;
ALTER TABLE parcelle ADD COLUMN longueur_epi DOUBLE;
DROP TABLE categorie_anomalie;

CREATE TABLE anomalie_recolte (
    id_anomalie SERIAL PRIMARY KEY,
    id_recolte SERIAL,
    avant DOUBLE PRECISION,
    apres DOUBLE PRECISION,
    id_type_anomalie SERIAL,
    FOREIGN KEY(id_recolte) REFERENCES recolte(id_recolte),
    FOREIGN KEY(id_type_anomalie) REFERENCES type_anomalie(id_type_anomalie)
);

-- Donnees de test pour les categories d'anomalie
INSERT INTO categorie_anomalie VALUES 
(1, 'Anomalie de suivie'),
(2, 'Anomalie de recolte');

-- Donnes de test pour les suivies
INSERT INTO suivie (id_suivie, id_parcelle, nb_pied, nb_epi, longueur_epi, verete, date_suivie) VALUES 
(DEFAULT, 1, 0, 0, 0, 0, '2023-07-01'),
(DEFAULT, 1, 30, 0, 0, 70, '2023-07-14'),
(DEFAULT, 1, 45, 3, 10, 80, '2023-08-01'),
(DEFAULT, 1, 50, 4, 13, 80, '2023-08-14'),
(DEFAULT, 1, 42, 4, 17, 85, '2023-09-01'),
(DEFAULT, 1, 40, 4, 23, 80, '2023-09-14');

INSERT INTO suivie (id_suivie, id_parcelle, nb_pied, nb_epi, longueur_epi, verete, date_suivie) VALUES 
(DEFAULT, 2, 0, 0, 0, 0, '2023-07-01'),
(DEFAULT, 2, 35, 2, 5, 50, '2023-07-14'),
(DEFAULT, 2, 48, 4, 12, 60, '2023-08-01'),
(DEFAULT, 2, 65, 3, 18, 70, '2023-08-14'),
(DEFAULT, 2, 65, 3, 17, 75, '2023-09-01'),
(DEFAULT, 2, 65, 3, 23, 80, '2023-09-14');

INSERT INTO suivie (id_suivie, id_parcelle, nb_pied, nb_epi, longueur_epi, verete, date_suivie) VALUES 
(DEFAULT, 3, 0, 0, 0, 0, '2023-07-10'),
(DEFAULT, 3, 20, 2, 3, 50, '2023-07-20'),
(DEFAULT, 3, 35, 3, 10, 60, '2023-08-10'),
(DEFAULT, 3, 42, 4, 16, 70, '2023-08-20'),
(DEFAULT, 3, 40, 4, 20, 75, '2023-09-10'),
(DEFAULT, 3, 40, 4, 25, 80, '2023-09-20');

-- View pour les parcelles et ces responsables
CREATE OR REPLACE VIEW v_parcelle_et_responsable AS
SELECT id_parcelle, p.nom as nom_parcelle, p.id_responsable, r.nom as nom_responsable, p.longueur_epi FROM
parcelle p JOIN responsable r ON p.id_responsable = r.id_responsable;

-- Test de prévision
INSERT INTO suivie (id_suivie, id_parcelle, nb_pied, nb_epi, longueur_epi, verete, date_suivie) VALUES 
(DEFAULT, 1, 200, 4, 20, 80, '2023-07-10'),
(DEFAULT, 2, 130, 3, 22, 75, '2023-07-10'),
(DEFAULT, 3, 400, 4, 25, 90, '2023-07-10'),
(DEFAULT, 4, 200, 3, 18, 60, '2023-08-10');

INSERT INTO recolte (id_recolte, nb_epi, longueur, poids, id_parcelle, date_recolte) VALUES (DEFAULT, 200, 20, 60, 1, '2023-09-10');
INSERT INTO recolte (id_recolte, nb_epi, longueur, poids, id_parcelle, date_recolte) VALUES (DEFAULT, 400, 22, 70, 2, '2023-09-10');

-- Insertion données de test aléas
INSERT INTO suivie (id_suivie, id_parcelle, nb_pied, nb_epi, longueur_epi, verete, date_suivie) VALUES 
(DEFAULT, 1, 200, 4, 5, 80, '2023-07-01'),
(DEFAULT, 2, 130, 3, 6, 75, '2023-07-01'),
(DEFAULT, 3, 400, 4, 5, 90, '2023-07-01'),
(DEFAULT, 4, 200, 3, 6, 60, '2023-07-01');

INSERT INTO suivie (id_suivie, id_parcelle, nb_pied, nb_epi, longueur_epi, verete, date_suivie) VALUES 
(DEFAULT, 1, 200, 4, 10, 80, '2023-07-14'),
(DEFAULT, 2, 130, 3, 11, 75, '2023-07-14'),
(DEFAULT, 3, 400, 4, 12, 90, '2023-07-14'),
(DEFAULT, 4, 200, 3, 7, 60, '2023-07-14');

INSERT INTO suivie (id_suivie, id_parcelle, nb_pied, nb_epi, longueur_epi, verete, date_suivie) VALUES 
(DEFAULT, 1, 200, 4, 20, 80, '2023-07-28'),
(DEFAULT, 2, 130, 3, 21, 75, '2023-07-28'),
(DEFAULT, 3, 400, 4, 14, 90, '2023-07-28'),
(DEFAULT, 4, 200, 3, 10, 60, '2023-07-28');

-- Insertion des données final
INSERT INTO suivie (id_suivie, id_parcelle, nb_pied, nb_epi, longueur_epi, verete, date_suivie) VALUES 
(DEFAULT, 1, 350, 5, 10, 20, '2023-07-01'),
(DEFAULT, 2, 600, 6, 11, 30, '2023-07-02'),
(DEFAULT, 3, 900, 4, 12, 25, '2023-07-03');

INSERT INTO suivie (id_suivie, id_parcelle, nb_pied, nb_epi, longueur_epi, verete, date_suivie) VALUES 
(DEFAULT, 1, 350, 5, 10, 20, '2023-07-15'),
(DEFAULT, 2, 600, 6, 11, 30, '2023-07-16'),
(DEFAULT, 3, 850, 4, 12, 25, '2023-07-17');