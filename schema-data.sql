CREATE SCHEMA IF NOT EXISTS "public";

CREATE SEQUENCE "public".anomalie_id_anomalie_seq START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE "public".caracteristique_id_caracteristique_seq START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE "public".categorie_anomalie_id_categorie_anomalie_seq START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE "public".parcelle_id_seq START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE "public".recolte_id_recolte_seq START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE "public".responsable_id_seq START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE "public".suivie_id_suivie_seq START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE "public".type_anomalie_id_type_anomalie_seq START WITH 1 INCREMENT BY 1;

CREATE  TABLE "public".caracteristique ( 
	id_caracteristique   integer DEFAULT nextval('caracteristique_id_caracteristique_seq'::regclass) NOT NULL  ,
	longueur_min         double precision  NOT NULL  ,
	CONSTRAINT pk_caracteristique PRIMARY KEY ( id_caracteristique )
 );

CREATE  TABLE "public".categorie_anomalie ( 
	id_categorie_anomalie integer DEFAULT nextval('categorie_anomalie_id_categorie_anomalie_seq'::regclass) NOT NULL  ,
	valeur               varchar(50)  NOT NULL  ,
	CONSTRAINT pk_categorie_anomalie PRIMARY KEY ( id_categorie_anomalie )
 );

CREATE  TABLE "public".responsable ( 
	id_responsable       integer DEFAULT nextval('responsable_id_seq'::regclass) NOT NULL  ,
	nom                  varchar(35)  NOT NULL  ,
	CONSTRAINT pk_responsable PRIMARY KEY ( id_responsable )
 );

CREATE  TABLE "public".type_anomalie ( 
	id_type_anomalie     integer DEFAULT nextval('type_anomalie_id_type_anomalie_seq'::regclass) NOT NULL  ,
	valeur               varchar(50)  NOT NULL  ,
	CONSTRAINT pk_type_anomalie PRIMARY KEY ( id_type_anomalie )
 );

CREATE  TABLE "public".parcelle ( 
	id_parcelle          integer DEFAULT nextval('parcelle_id_seq'::regclass) NOT NULL  ,
	nom                  varchar(35)  NOT NULL  ,
	id_responsable       integer  NOT NULL  ,
	CONSTRAINT pk_parcelle PRIMARY KEY ( id_parcelle ),
	CONSTRAINT fk_parcelle_responsable FOREIGN KEY ( id_responsable ) REFERENCES "public".responsable( id_responsable )   
 );

CREATE  TABLE "public".recolte ( 
	id_recolte           integer DEFAULT nextval('recolte_id_recolte_seq'::regclass) NOT NULL  ,
	nb_epi               double precision  NOT NULL  ,
	longueur             double precision  NOT NULL  ,
	poids                double precision  NOT NULL  ,
	id_parcelle          integer  NOT NULL  ,
	date_recolte         date DEFAULT CURRENT_DATE   ,
	CONSTRAINT pk_recolte PRIMARY KEY ( id_recolte ),
	CONSTRAINT fk_recolte_parcelle FOREIGN KEY ( id_parcelle ) REFERENCES "public".parcelle( id_parcelle )   
 );

CREATE  TABLE "public".suivie ( 
	id_suivie            integer DEFAULT nextval('suivie_id_suivie_seq'::regclass) NOT NULL  ,
	id_parcelle          integer  NOT NULL  ,
	nb_pied              double precision  NOT NULL  ,
	nb_epi               double precision  NOT NULL  ,
	longueur_epi         double precision  NOT NULL  ,
	date_suivie          date DEFAULT CURRENT_DATE NOT NULL  ,
	verete               double precision  NOT NULL  ,
	CONSTRAINT pk_suivie PRIMARY KEY ( id_suivie ),
	CONSTRAINT fk_suivie_parcelle FOREIGN KEY ( id_parcelle ) REFERENCES "public".parcelle( id_parcelle )   
 );

CREATE  TABLE "public".anomalie ( 
	id_anomalie          integer DEFAULT nextval('anomalie_id_anomalie_seq'::regclass) NOT NULL  ,
	id_suivie            integer  NOT NULL  ,
	valeur               varchar(50)  NOT NULL  ,
	avant                double precision  NOT NULL  ,
	apres                double precision  NOT NULL  ,
	id_type_anomalie     integer  NOT NULL  ,
	id_categorie_anomalie integer  NOT NULL  ,
	CONSTRAINT pk_anomalie PRIMARY KEY ( id_anomalie ),
	CONSTRAINT fk_anomalie_categorie_anomalie FOREIGN KEY ( id_categorie_anomalie ) REFERENCES "public".categorie_anomalie( id_categorie_anomalie )   ,
	CONSTRAINT fk_anomalie_suivie FOREIGN KEY ( id_suivie ) REFERENCES "public".suivie( id_suivie )   ,
	CONSTRAINT fk_anomalie_type_anomalie FOREIGN KEY ( id_type_anomalie ) REFERENCES "public".type_anomalie( id_type_anomalie )   
 );

CREATE VIEW "public".v_parcelle_et_responsable AS  SELECT p.id_parcelle,
    p.nom AS nom_parcelle,
    p.id_responsable,
    r.nom AS nom_responsable
   FROM (parcelle p
     JOIN responsable r ON ((p.id_responsable = r.id_responsable)));

INSERT INTO "public".caracteristique( id_caracteristique, longueur_min ) VALUES ( 1, 20.0);
INSERT INTO "public".responsable( id_responsable, nom ) VALUES ( 1, 'John');
INSERT INTO "public".responsable( id_responsable, nom ) VALUES ( 2, 'Jean');
INSERT INTO "public".responsable( id_responsable, nom ) VALUES ( 3, 'Jeanne');
INSERT INTO "public".responsable( id_responsable, nom ) VALUES ( 4, 'Vero');
INSERT INTO "public".type_anomalie( id_type_anomalie, valeur ) VALUES ( 1, 'Diminution du nombre de pied');
INSERT INTO "public".type_anomalie( id_type_anomalie, valeur ) VALUES ( 2, 'Diminution du nombre d''epi');
INSERT INTO "public".type_anomalie( id_type_anomalie, valeur ) VALUES ( 3, 'Diminution du taux de verete');
INSERT INTO "public".type_anomalie( id_type_anomalie, valeur ) VALUES ( 4, 'Manque de croissance');
INSERT INTO "public".parcelle( id_parcelle, nom, id_responsable ) VALUES ( 1, 'Parcelle 1', 1);
INSERT INTO "public".parcelle( id_parcelle, nom, id_responsable ) VALUES ( 2, 'Parcelle 2', 1);
INSERT INTO "public".parcelle( id_parcelle, nom, id_responsable ) VALUES ( 3, 'Parcelle 3', 2);
INSERT INTO "public".parcelle( id_parcelle, nom, id_responsable ) VALUES ( 4, 'Parcelle 4', 2);
INSERT INTO "public".parcelle( id_parcelle, nom, id_responsable ) VALUES ( 5, 'Parcelle 5', 3);
INSERT INTO "public".parcelle( id_parcelle, nom, id_responsable ) VALUES ( 6, 'Parcelle 6', 3);
INSERT INTO "public".parcelle( id_parcelle, nom, id_responsable ) VALUES ( 7, 'Parcelle 7', 4);
INSERT INTO "public".parcelle( id_parcelle, nom, id_responsable ) VALUES ( 8, 'Parcelle 8', 4);
INSERT INTO "public".parcelle( id_parcelle, nom, id_responsable ) VALUES ( 9, 'Parcelle 9', 4);
INSERT INTO "public".recolte( id_recolte, nb_epi, longueur, poids, id_parcelle, date_recolte ) VALUES ( 1, 654.0, 30.0, 150.0, 1, '2023-07-01');
INSERT INTO "public".suivie( id_suivie, id_parcelle, nb_pied, nb_epi, longueur_epi, date_suivie, verete ) VALUES ( 1, 1, 0.0, 0.0, 0.0, '2023-07-01', 0.0);
INSERT INTO "public".suivie( id_suivie, id_parcelle, nb_pied, nb_epi, longueur_epi, date_suivie, verete ) VALUES ( 2, 1, 30.0, 0.0, 0.0, '2023-07-14', 70.0);
INSERT INTO "public".suivie( id_suivie, id_parcelle, nb_pied, nb_epi, longueur_epi, date_suivie, verete ) VALUES ( 3, 1, 45.0, 3.0, 10.0, '2023-08-01', 80.0);
INSERT INTO "public".suivie( id_suivie, id_parcelle, nb_pied, nb_epi, longueur_epi, date_suivie, verete ) VALUES ( 4, 1, 50.0, 4.0, 13.0, '2023-08-14', 80.0);
INSERT INTO "public".suivie( id_suivie, id_parcelle, nb_pied, nb_epi, longueur_epi, date_suivie, verete ) VALUES ( 5, 1, 42.0, 4.0, 17.0, '2023-09-01', 85.0);
INSERT INTO "public".suivie( id_suivie, id_parcelle, nb_pied, nb_epi, longueur_epi, date_suivie, verete ) VALUES ( 6, 1, 40.0, 4.0, 23.0, '2023-09-14', 80.0);
INSERT INTO "public".suivie( id_suivie, id_parcelle, nb_pied, nb_epi, longueur_epi, date_suivie, verete ) VALUES ( 7, 2, 0.0, 0.0, 0.0, '2023-07-01', 0.0);
INSERT INTO "public".suivie( id_suivie, id_parcelle, nb_pied, nb_epi, longueur_epi, date_suivie, verete ) VALUES ( 8, 2, 35.0, 2.0, 5.0, '2023-07-14', 50.0);
INSERT INTO "public".suivie( id_suivie, id_parcelle, nb_pied, nb_epi, longueur_epi, date_suivie, verete ) VALUES ( 9, 2, 48.0, 4.0, 12.0, '2023-08-01', 60.0);
INSERT INTO "public".suivie( id_suivie, id_parcelle, nb_pied, nb_epi, longueur_epi, date_suivie, verete ) VALUES ( 10, 2, 65.0, 3.0, 18.0, '2023-08-14', 70.0);
INSERT INTO "public".suivie( id_suivie, id_parcelle, nb_pied, nb_epi, longueur_epi, date_suivie, verete ) VALUES ( 11, 2, 65.0, 3.0, 17.0, '2023-09-01', 75.0);
INSERT INTO "public".suivie( id_suivie, id_parcelle, nb_pied, nb_epi, longueur_epi, date_suivie, verete ) VALUES ( 12, 2, 65.0, 3.0, 23.0, '2023-09-14', 80.0);
INSERT INTO "public".suivie( id_suivie, id_parcelle, nb_pied, nb_epi, longueur_epi, date_suivie, verete ) VALUES ( 13, 3, 0.0, 0.0, 0.0, '2023-07-10', 0.0);
INSERT INTO "public".suivie( id_suivie, id_parcelle, nb_pied, nb_epi, longueur_epi, date_suivie, verete ) VALUES ( 14, 3, 20.0, 2.0, 3.0, '2023-07-20', 50.0);
INSERT INTO "public".suivie( id_suivie, id_parcelle, nb_pied, nb_epi, longueur_epi, date_suivie, verete ) VALUES ( 15, 3, 35.0, 3.0, 10.0, '2023-08-10', 60.0);
INSERT INTO "public".suivie( id_suivie, id_parcelle, nb_pied, nb_epi, longueur_epi, date_suivie, verete ) VALUES ( 16, 3, 42.0, 4.0, 16.0, '2023-08-20', 70.0);
INSERT INTO "public".suivie( id_suivie, id_parcelle, nb_pied, nb_epi, longueur_epi, date_suivie, verete ) VALUES ( 17, 3, 40.0, 4.0, 20.0, '2023-09-10', 75.0);
INSERT INTO "public".suivie( id_suivie, id_parcelle, nb_pied, nb_epi, longueur_epi, date_suivie, verete ) VALUES ( 18, 3, 40.0, 4.0, 25.0, '2023-09-20', 80.0);
