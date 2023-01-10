-- One admin user, named admin1 with password 4dm1n and authority admin
INSERT INTO users(username,password,enabled) VALUES ('admin1','4dm1n',TRUE);
INSERT INTO authorities(created_date,creator,last_modified_date,modifier,id,username,authority) VALUES ('2023-1-7 00:00:00','admin','2023-1-7 00:00:00','admin',1,'admin1','admin');
INSERT INTO jugador (id,first_name,last_name,image,win,lost,time,mov,points,max_movs,min_movs,max_time,min_time,username)VALUES(1,'admin','admin','',0,0,'00:00:00',0,0,0,0,'','','admin1');

-- One jugador user, named mario with passwor mario
INSERT INTO users(username,password,enabled) VALUES ('marsannar2','mario',TRUE);
INSERT INTO authorities(created_date,creator,last_modified_date,modifier,id,username,authority) VALUES ('2023-1-7 00:00:00','admin','2023-1-7 00:00:00','admin',8,'marsannar2','jugador');
INSERT INTO jugador (id,first_name,last_name,image,win,lost,time,mov,points,max_movs,min_movs,max_time,min_time,username)VALUES(2,'mario','sanchez','',0,0,'00:00:00',0,0,0,0,'','','marsannar2');

-- -- One jugador jorsilman, named jorge with password jorge
INSERT INTO users(username,password,enabled) VALUES ('jorge','jorge',TRUE);
INSERT INTO authorities(created_date,creator,last_modified_date,modifier,id,username,authority) VALUES ('2023-1-7 00:00:00','admin','2023-1-7 00:00:00','admin',4,'jorge','jugador');
INSERT INTO jugador (id,first_name,last_name,image,win,lost,time,mov,points,max_movs,min_movs,max_time,min_time,username)VALUES(3,'jorge','sillero','',0,0,'00:00:00',0,0,0,0,'','','jorge');

-- -- One jugador barbaat, named barba with password barba
INSERT INTO users(username,password,enabled) VALUES ('barba','barba',TRUE);
INSERT INTO authorities(created_date,creator,last_modified_date,modifier,id,username,authority) VALUES ('2023-1-7 00:00:00','admin','2023-1-7 00:00:00','admin',5,'barba','jugador');
INSERT INTO jugador (id,first_name,last_name,image,win,lost,time,mov,points,max_movs,min_movs,max_time,min_time,username)VALUES(4,'barba','barba','',0,0,'00:00:00',0,0,0,0,'','','barba');

-- -- One jugador fracaralb, named fran with passwor fran
INSERT INTO users(username,password,enabled) VALUES ('fran','fran',TRUE);
INSERT INTO authorities(created_date,creator,last_modified_date,modifier,id,username,authority) VALUES ('2023-1-7 00:00:00','admin','2023-1-7 00:00:00','admin',6,'fran','jugador');
INSERT INTO jugador (id,first_name,last_name,image,win,lost,time,mov,points,max_movs,min_movs,max_time,min_time,username)VALUES(5,'fran','fran','',0,0,'00:00:00',0,0,0,0,'','','fran');

-- -- One jugador albgalhue, named gallego with password gallego
INSERT INTO users(username,password,enabled) VALUES ('gallego','gallego',TRUE);
INSERT INTO authorities(created_date,creator,last_modified_date,modifier,id,username,authority) VALUES ('2023-1-7 00:00:00','admin','2023-1-7 00:00:00','admin',7,'gallego','jugador');
INSERT INTO jugador (id,first_name,last_name,image,win,lost,time,mov,points,max_movs,min_movs,max_time,min_time,username)VALUES(6,'gallego','gallego','',0,0,'00:00:00',0,0,0,0,'','','gallego');

-- -- One jugador alvnavriv, named alvaro with password alvaro
INSERT INTO users(username,password,enabled) VALUES ('alvaro','alvaro',TRUE);
INSERT INTO authorities(created_date,creator,last_modified_date,modifier,id,username,authority) VALUES ('2023-1-7 00:00:00','admin','2023-1-7 00:00:00','admin',11,'alvaro','jugador');
INSERT INTO jugador (id,first_name,last_name,image,win,lost,time,mov,points,max_movs,min_movs,max_time,min_time,username)VALUES(7,'alvaro','alvaro','',0,0,'00:00:00',0,0,0,0,'','','alvaro');

INSERT INTO logros(id, name, description, is_unlocked, image, jugador_id) VALUES (1, 'Máquina de jugar','Has jugado 5 partidas', false, 'https://cdn-icons-png.flaticon.com/512/4319/4319081.png', 1);
INSERT INTO logros(id, name, description, is_unlocked, image, jugador_id) VALUES (2, 'No se te da nada mal','Has alcanzado los 100 puntos', false, 'https://cdn-icons-png.flaticon.com/512/4319/4319081.png', 1);
INSERT INTO logros(id, name, description, is_unlocked, image, jugador_id) VALUES (3, '¡Estás on fire!','Has alcanzado los 200 movimientos', false, 'https://cdn-icons-png.flaticon.com/512/4319/4319081.png', 1);

INSERT INTO logros(id, name, description, is_unlocked, image, jugador_id) VALUES (4, 'Máquina de jugar','Has jugado 5 partidas', false, 'https://cdn-icons-png.flaticon.com/512/4319/4319081.png', 2);
INSERT INTO logros(id, name, description, is_unlocked, image, jugador_id) VALUES (5, 'No se te da nada mal','Has alcanzado los 100 puntos', false, 'https://cdn-icons-png.flaticon.com/512/4319/4319081.png', 2);
INSERT INTO logros(id, name, description, is_unlocked, image, jugador_id) VALUES (6, '¡Estás on fire!','Has alcanzado los 200 movimientos', false, 'https://cdn-icons-png.flaticon.com/512/4319/4319081.png', 2);

INSERT INTO logros(id, name, description, is_unlocked, image, jugador_id) VALUES (7, 'Máquina de jugar','Has jugado 5 partidas', false, 'https://cdn-icons-png.flaticon.com/512/4319/4319081.png', 3);
INSERT INTO logros(id, name, description, is_unlocked, image, jugador_id) VALUES (8, 'No se te da nada mal','Has alcanzado los 100 puntos', false, 'https://cdn-icons-png.flaticon.com/512/4319/4319081.png', 3);
INSERT INTO logros(id, name, description, is_unlocked, image, jugador_id) VALUES (9, '¡Estás on fire!','Has alcanzado los 200 movimientos', false, 'https://cdn-icons-png.flaticon.com/512/4319/4319081.png', 3);

INSERT INTO logros(id, name, description, is_unlocked, image, jugador_id) VALUES (10, 'Máquina de jugar','Has jugado 5 partidas', false, 'https://cdn-icons-png.flaticon.com/512/4319/4319081.png', 4);
INSERT INTO logros(id, name, description, is_unlocked, image, jugador_id) VALUES (11, 'No se te da nada mal','Has alcanzado los 100 puntos', false, 'https://cdn-icons-png.flaticon.com/512/4319/4319081.png', 4);
INSERT INTO logros(id, name, description, is_unlocked, image, jugador_id) VALUES (12, '¡Estás on fire!','Has alcanzado los 200 movimientos', false, 'https://cdn-icons-png.flaticon.com/512/4319/4319081.png', 4);

INSERT INTO logros(id, name, description, is_unlocked, image, jugador_id) VALUES (13, 'Máquina de jugar','Has jugado 5 partidas', false, 'https://cdn-icons-png.flaticon.com/512/4319/4319081.png', 5);
INSERT INTO logros(id, name, description, is_unlocked, image, jugador_id) VALUES (14, 'No se te da nada mal','Has alcanzado los 100 puntos', false, 'https://cdn-icons-png.flaticon.com/512/4319/4319081.png', 5);
INSERT INTO logros(id, name, description, is_unlocked, image, jugador_id) VALUES (15, '¡Estás on fire!','Has alcanzado los 200 movimientos', false, 'https://cdn-icons-png.flaticon.com/512/4319/4319081.png', 5);


INSERT INTO logros(id, name, description, is_unlocked, image, jugador_id) VALUES (16, 'Máquina de jugar','Has jugado 5 partidas', false, 'https://cdn-icons-png.flaticon.com/512/4319/4319081.png', 6);
INSERT INTO logros(id, name, description, is_unlocked, image, jugador_id) VALUES (17, 'No se te da nada mal','Has alcanzado los 100 puntos', false, 'https://cdn-icons-png.flaticon.com/512/4319/4319081.png', 6);
INSERT INTO logros(id, name, description, is_unlocked, image, jugador_id) VALUES (18, '¡Estás on fire!','Has alcanzado los 200 movimientos', false, 'https://cdn-icons-png.flaticon.com/512/4319/4319081.png', 6);

INSERT INTO logros(id, name, description, is_unlocked, image, jugador_id) VALUES (19, 'Máquina de jugar','Has jugado 5 partidas', false, 'https://cdn-icons-png.flaticon.com/512/4319/4319081.png', 7);
INSERT INTO logros(id, name, description, is_unlocked, image, jugador_id) VALUES (20, 'No se te da nada mal','Has alcanzado los 100 puntos', false, 'https://cdn-icons-png.flaticon.com/512/4319/4319081.png', 7);
INSERT INTO logros(id, name, description, is_unlocked, image, jugador_id) VALUES (21, '¡Estás on fire!','Has alcanzado los 200 movimientos', false, 'https://cdn-icons-png.flaticon.com/512/4319/4319081.png', 7);

-- INSERT INTO partida(id,momento_inicio,momento_fin,num_movimientos,victoria,jugador_id) VALUES (1,'2022-11-28 19:36:30','2022-11-28 19:40:00',300,true,1);
-- INSERT INTO partida(id,momento_inicio,momento_fin,num_movimientos,victoria,jugador_id) VALUES (2,'2022-11-28 19:36:30','2022-11-28 20:05:00',100,true,1);
-- INSERT INTO partida(id,momento_inicio,momento_fin,num_movimientos,victoria,jugador_id) VALUES (3,'2022-11-28 19:36:30','2022-11-28 20:10:00',100,true,1);
-- INSERT INTO partida(id,momento_inicio,momento_fin,num_movimientos,victoria,jugador_id) VALUES (4,'2022-11-28 19:36:30','2022-11-28 20:32:00',400,true,1);
-- INSERT INTO partida(id,momento_inicio,momento_fin,num_movimientos,victoria,jugador_id) VALUES (5,'2022-11-28 19:36:30','2022-11-28 19:38:00',400,true,1);
