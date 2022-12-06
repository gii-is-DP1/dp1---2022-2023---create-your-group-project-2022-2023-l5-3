-- One admin user, named admin1 with password 4dm1n and authority admin
INSERT INTO users(username,password,enabled) VALUES ('admin1','4dm1n',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (1,'admin1','admin');
INSERT INTO jugador VALUES(1,'admin','','admin',0,0,0,0,'00:00:00','admin1');
--maxnummov , minnummov, numtotalmov, numtotalpuntos, ganadas, perdidas

-- One jugador user, named mario with passwor mario
INSERT INTO users(username,password,enabled) VALUES ('marsannar2','mario',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (8,'marsannar2','jugador');
INSERT INTO jugador VALUES(7,'mario','sanchez','',6,7,5,7,'23:00:53','marsannar2');

-- One jugador jorsilman, named jorge with password jorge
INSERT INTO users(username,password,enabled) VALUES ('jorge','jorge',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (4,'jorge','jugador');
INSERT INTO jugador VALUES(3,'jorge','','sillero',6,7,5,7,'23:00:53','jorge');

-- One jugador barbaat, named barba with password barba
INSERT INTO users(username,password,enabled) VALUES ('barba','barba',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (5,'barba','jugador');
INSERT INTO jugador VALUES(4,'barba','','aaa',200,100,0,0,'23:00:53','barba');

-- One jugador fracaralb, named fran with passwor fran
INSERT INTO users(username,password,enabled) VALUES ('fran','fran',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (6,'fran','jugador');
INSERT INTO jugador VALUES(5,'aaa','','aaa',6,7,5,7,'23:00:53','fran');

-- One jugador albgalhue, named gallego with password gallego
INSERT INTO users(username,password,enabled) VALUES ('gallego','gallego',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (7,'gallego','jugador');
INSERT INTO jugador VALUES(6,'aaa','','aaa',6,7,5,7,'23:00:53','gallego');

-- One jugador alvnavriv, named alvaro with password alvaro
INSERT INTO users(username,password,enabled) VALUES ('alvaro','alvaro',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (11,'alvaro','jugador');
INSERT INTO jugador VALUES(8,'aaa','','aaa',6,7,5,7,'23:00:53','alvaro');


INSERT INTO logros(id, name, description, is_unlocked, image, jugador_id) VALUES (1, 'Máquina de jugar','Has jugado 5 partidas', false, '', 1);
INSERT INTO logros(id, name, description, is_unlocked, image, jugador_id) VALUES (2, 'No se te da nada mal','Has alcanzado los 100 puntos', false, '', 1);
INSERT INTO logros(id, name, description, is_unlocked, image, jugador_id) VALUES (3, '¡Estás on fire!','Has alcanzado los 200 movimientos', false, '', 1);

INSERT INTO logros(id, name, description, is_unlocked, image, jugador_id) VALUES (4, 'Máquina de jugar','Has jugado 5 partidas', false, '', 7);
INSERT INTO logros(id, name, description, is_unlocked, image, jugador_id) VALUES (5, 'No se te da nada mal','Has alcanzado los 100 puntos', false, '', 7);
INSERT INTO logros(id, name, description, is_unlocked, image, jugador_id) VALUES (6, '¡Estás on fire!','Has alcanzado los 200 movimientos', false, '', 7);

INSERT INTO logros(id, name, description, is_unlocked, image, jugador_id) VALUES (7, 'Máquina de jugar','Has jugado 5 partidas', false, '', 3);
INSERT INTO logros(id, name, description, is_unlocked, image, jugador_id) VALUES (8, 'No se te da nada mal','Has alcanzado los 100 puntos', false, '', 3);
INSERT INTO logros(id, name, description, is_unlocked, image, jugador_id) VALUES (9, '¡Estás on fire!','Has alcanzado los 200 movimientos', false, '', 3);

INSERT INTO logros(id, name, description, is_unlocked, image, jugador_id) VALUES (10, 'Máquina de jugar','Has jugado 5 partidas', false, '', 4);
INSERT INTO logros(id, name, description, is_unlocked, image, jugador_id) VALUES (11, 'No se te da nada mal','Has alcanzado los 100 puntos', false, '', 4);
INSERT INTO logros(id, name, description, is_unlocked, image, jugador_id) VALUES (12, '¡Estás on fire!','Has alcanzado los 200 movimientos', false, '', 4);

INSERT INTO logros(id, name, description, is_unlocked, image, jugador_id) VALUES (13, 'Máquina de jugar','Has jugado 5 partidas', false, '', 5);
INSERT INTO logros(id, name, description, is_unlocked, image, jugador_id) VALUES (14, 'No se te da nada mal','Has alcanzado los 100 puntos', false, '', 5);
INSERT INTO logros(id, name, description, is_unlocked, image, jugador_id) VALUES (15, '¡Estás on fire!','Has alcanzado los 200 movimientos', false, '', 5);


INSERT INTO logros(id, name, description, is_unlocked, image, jugador_id) VALUES (16, 'Máquina de jugar','Has jugado 5 partidas', false, '', 6);
INSERT INTO logros(id, name, description, is_unlocked, image, jugador_id) VALUES (17, 'No se te da nada mal','Has alcanzado los 100 puntos', false, '', 6);
INSERT INTO logros(id, name, description, is_unlocked, image, jugador_id) VALUES (18, '¡Estás on fire!','Has alcanzado los 200 movimientos', false, '', 6);

INSERT INTO logros(id, name, description, is_unlocked, image, jugador_id) VALUES (19, 'Máquina de jugar','Has jugado 5 partidas', false, '', 8);
INSERT INTO logros(id, name, description, is_unlocked, image, jugador_id) VALUES (20, 'No se te da nada mal','Has alcanzado los 100 puntos', false, '', 8);
INSERT INTO logros(id, name, description, is_unlocked, image, jugador_id) VALUES (21, '¡Estás on fire!','Has alcanzado los 200 movimientos', false, '', 8);

INSERT INTO partida(id,momento_inicio,momento_fin,num_movimientos,victoria,jugador_id) VALUES (1,'2022-11-28 19:36:30','2022-11-28 19:40:00',300,true,1);
INSERT INTO partida(id,momento_inicio,momento_fin,num_movimientos,victoria,jugador_id) VALUES (2,'2022-11-28 19:36:30','2022-11-28 20:10:00',100,true,4);
INSERT INTO partida(id,momento_inicio,momento_fin,num_movimientos,victoria,jugador_id) VALUES (3,'2022-11-28 19:36:30','2022-11-28 20:10:00',100,true,3);
--INSERT INTO partida(id,momento_inicio,momento_fin,num_movimientos,victoria,jugador_id) VALUES (4,'2022-11-28 19:36:30','2022-11-28 19:40:00',400,true,1);
--INSERT INTO partida(id,momento_inicio,momento_fin,num_movimientos,victoria,jugador_id) VALUES (5,'2022-11-28 19:36:30','2022-11-28 19:40:00',400,true,1);
