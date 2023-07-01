/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  to
 * Created: 1 juil. 2023
 */

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
CREATE VIEW v_parcelle_et_responsable AS
SELECT id_parcelle, p.nom as nom_parcelle, p.id_responsable, r.nom as nom_responsable FROM
parcelle p JOIN responsable r ON p.id_responsable = r.id_responsable;