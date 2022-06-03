--Roles
INSERT INTO roles VALUES (1, 'ROLE_ADMIN');
INSERT INTO roles VALUES (2, 'ROLE_DEFAULT_USER');
INSERT INTO roles VALUES (3, 'ROLE_VETERINARIO');

--Personas para ingresar en el sistema
insert into personas (cedula,nombre,apellidos,direccion,telefono,celular,correo) values ('0105661524','Roy','Morocho','San Joaquin','0453535','0980385997','admin@gmail.com');
	--Persona para ser Veterinarios
	insert into personas (cedula,nombre,apellidos,direccion,telefono,celular,correo) values ('0105661525','Estiven','Quishpi','Av Americas','0453536','0980385998','veterianario@gmail.com');
	insert into personas (cedula,nombre,apellidos,direccion,telefono,celular,correo) values ('0105661526','Christian','Torres','9 de octubre','0453537','0980385999','veterianario2@gmail.com');

--Veterinarios
insert into veterinarios (id,cargo)values(1,'Principal')
insert into veterinarios (id,cargo)values(2,'Suplente')

-- Veterinario - persona
insert into veterinarios_personas (veterinario_id, persona_id)values(1,2)
insert into veterinarios_personas (veterinario_id, persona_id)values(2,3)

--Usuarios
--pass "admin"
INSERT INTO usuarios (email,enabled,password,username,persona_id) VALUES ('admin@gmail.com',True,'$2a$10$9s26MbY0/Zl3aXQaAqrH0ObKHHDAq6NplfnBBH77510c1juIvpR8m','admin',1);

--Usuarios y rol
INSERT INTO usuarios_roles VALUES (1,1);

--FichasClinicas
insert into fichas_clinicas (fecha_ingreso,motivo_consulta,hallazgos,temperatura,conjuntiva,frecuencia_cardiaca,frecuencia_respiratoria,TRC,mucosas,esterilizacion ,alimentacion,pronostico,diagnostico_diferencial,costo,veterinario_id,animal_id) values ('2019-06-18','dolor estomacal','ulceras en intestino',37.5,'Normal',130,15,'Bien', 'Normal',true,123.3,'Aun falta', 'Pendiente',54.3,1,1),('2020-06-18','dolor Respiratorio','Pulmones con mucosa',38,'Alta',130,15,'Bien', 'Alta',false,123.3,'Aun falta', 'Pendiente',54.3,2,1);

--Vacunas
INSERT INTO vacuna (descripcion,nombre,tipo) VALUES ('Vacuna 1','Vacu','Tipo de vacuna ejemplo'),('Vacuna 2','Va','Tipo de vacuna ejemplo 2'),('Vacuna 3','Vac','Tipo de vacuna ejemplo 3');

--Carnet Vacunaciones

INSERT INTO carnet_vacunaciones (fecha_aplicacion,fecha_proxima_aplicacion,vacuna_id,animal_id) VALUES ('2022-05-26','2022-06-26',1,1),('2022-05-30','2022-06-12',2,1);


--Animales
insert into animales(cituacion_actual, color_caracteristicas, edad, especie, fecha_nacimiento, nombre, peso, procedencia, raza, sexo, url_foto) values('REFUGIO', 'CAFE CLARO CON ALGUNAS ZONAS NEGRITAS DE SU PELAJE Y SU COLA TERMINA EN COLOR NEGRO', 4, 'CANINO', '2018-06-18', 'POPEYE', 9, 'RESCATADO EN EL SECTOR DE BAÑOS', 'MESTIZO', 'MACHO', 'https://img.myloview.es/vinilos/perro-mestizo-sin-hogar-mirando-a-ti-400-141109517.jpg');
insert into animales(cituacion_actual, color_caracteristicas, edad, especie, fecha_nacimiento, nombre, peso, procedencia, raza, sexo, url_foto) values ('ADOPTADO', 'NEGRO', 7, 'CANINO', '2019-06-18', 'PIPO', 7, 'RESCATADO DEL SECTOR DE LOMA ALTA DONDE SE USABA EN PELEAS CLANDESTINAS', 'COLOCAR RAZA', 'MACHO', 'https://www.elespectador.com/resizer/FIfoOIF2gmAEZPksir3nqAX4gPU=/525x350/filters:format(jpeg)/cloudfront-us-east-1.images.arcpublishing.com/elespectador/XQ5OB4SRZ5B5LD7S7QIRCLHTVY.jpg');
insert into animales(cituacion_actual, color_caracteristicas, edad, especie, fecha_nacimiento, nombre, peso, procedencia, raza, sexo, url_foto) values ('FALLECIDO', 'CAFE CON OREGITAS NEGRAS', 3, 'CANINO', '2019-06-18', 'PULGAS', 3, 'NO RESISTIO LA OPERACIÓN', 'COLOCAR RAZA', 'HEMBRA', 'https://t2.uc.ltmcdn.com/es/posts/9/5/5/10_cosas_que_los_perros_pueden_predecir_te_sorprenderas_43559_600.jpg');
insert into animales(cituacion_actual, color_caracteristicas, edad, especie, fecha_nacimiento, nombre, peso, procedencia, raza, sexo, url_foto) values ('REFUGIO', 'CAFE CON MANCHAS NEGRAS', 10, 'CANINO', '2019-06-18', 'RODOLFO', 10, 'RESCATADO DEL SECTOR UCUBAMBA', 'COLOCAR RAZA', 'HEMBRA', 'https://www.diariodesevilla.es/2021/07/19/mascotas/Lista-razas-perros-inteligentes_1593750767_141504796_667x375.jpg');
insert into animales(cituacion_actual, color_caracteristicas, edad, especie, fecha_nacimiento, nombre, peso, procedencia, raza, sexo, url_foto) values ('REFUGIO', 'NEGRO CENISO', 3, 'CANINO', '2019-06-18', 'JORGITO', 5, 'RESCATADO DE LA FERIA LIBRE', 'COLOCAR RAZA', 'MACHO', 'https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/portrait-of-labradoodle-with-humorous-expression-royalty-free-image-690356759-1542399719.jpg');
insert into animales(cituacion_actual, color_caracteristicas, edad, especie, fecha_nacimiento, nombre, peso, procedencia, raza, sexo, url_foto) values ('REFUGIO', 'NEGRO CON ALGUNAS MANCHAS CAFES EN EL HOCICO Y LAS OREJAS', 3, 'CANINO', '2019-06-18', 'BODY', 3, 'RESCATADO DE LA VENTA ILEGAL', 'COLOCAR RAZA', 'MACHO', 'http://images.ctfassets.net/n44u7kn3xh7u/3OpiSRvBhm60u0gsEqCyCy/c8040acd47b9adcf6ca0b31e04f9c061/perros-salchichas.jpg');
insert into animales(cituacion_actual, color_caracteristicas, edad, especie, fecha_nacimiento, nombre, peso, procedencia, raza, sexo, url_foto) values ('REFUGIO', 'BLANCO CON MANCHAS NEGRAS', 6, 'CANINO', '2019-06-18', 'BODY', 8, 'RESCATADO DE LA VENTA ILEGAL', 'COLOCAR RAZA', 'MACHO', 'https://www.webconsultas.com/sites/default/files/styles/wc_adaptive_image__small/public/temas/caracteristicas_dalmata.jpg');
insert into animales(cituacion_actual, color_caracteristicas, edad, especie, fecha_nacimiento, nombre, peso, procedencia, raza, sexo, url_foto) values ('REFUGIO', 'NEGRO CON BRANCO EN EL PECHO', 3, 'FELINO', '2015-05-26', 'POPEYE', 5, 'procedencia', 'SIN RAZA', 'MACHO', 'https://www.purina-latam.com/sites/g/files/auxxlc391/files/styles/large/public/purina-para-que-sirven-los-bigotes-de-los-gatos.png?itok=gr5dH8dL');
insert into animales(cituacion_actual, color_caracteristicas, edad, especie, fecha_nacimiento, nombre, peso, procedencia, raza, sexo, url_foto) values ('REFUGIO', 'BLANCO', 4, 'FELINO', '2015-05-26', 'PELUSA', 5, 'procedencia', 'SIN RAZA', 'MACHO', 'https://www.ecartelera.com/images/sets/400/485.jpg');
insert into animales(cituacion_actual, color_caracteristicas, edad, especie, fecha_nacimiento, nombre, peso, procedencia, raza, sexo, url_foto) values ('REFUGIO', 'CAFE CLARO', 4, 'FELINO', '2015-05-26', 'KITTY GALORE', 5, 'procedencia', 'SIN RAZA', 'MACHO', 'https://static.wikia.nocookie.net/characters/images/1/1b/TippettKittyGalore.jpg/revision/latest?cb=20160717031319');
insert into animales(cituacion_actual, color_caracteristicas, edad, especie, fecha_nacimiento, nombre, peso, procedencia, raza, sexo, url_foto) values ('REFUGIO', 'GRIS CON MANCHAS NEGRAS', 5, 'FELINO', '2018-05-26', 'MANCHAS', 7, 'procedencia', 'SIN RAZA', 'MACHO', 'https://i.ytimg.com/vi/Vgyb1sL2jhU/sddefault.jpg');
insert into animales(cituacion_actual, color_caracteristicas, edad, especie, fecha_nacimiento, nombre, peso, procedencia, raza, sexo, url_foto) values ('REFUGIO', 'BLANCO', 6, 'FELINO', '2015-05-26', 'MR TINKLES', 5, 'procedencia', 'SIN RAZA', 'MACHO', 'https://media.gq.com.mx/photos/5e4ed5b92ac66b000952c88a/master/w_1280,h_720,c_limit/Mr.%20Tinkles,%20Cats%20&%20Dogs.jpg');
insert into animales(cituacion_actual, color_caracteristicas, edad, especie, fecha_nacimiento, nombre, peso, procedencia, raza, sexo, url_foto) values ('REFUGIO', 'CAFE CLARO', 7, 'FELINO', '2017-05-26', 'MICHI', 5, 'procedencia', 'SIN RAZA', 'MACHO', 'https://i.ytimg.com/vi/wbf-jBF8YuU/maxresdefault.jpg');
insert into animales(cituacion_actual, color_caracteristicas, edad, especie, fecha_nacimiento, nombre, peso, procedencia, raza, sexo, url_foto) values ('REFUGIO', 'BLANCO CON MANCHAS NEGRAS EN LA CABEZA', 4, 'FELINO', '2013-05-26', 'TILINGO', 5, 'procedencia', 'SIN RAZA', 'MACHO', 'https://i.pinimg.com/originals/99/cf/f6/99cff66dae1a91bea21771c692da6259.jpg');
insert into animales(cituacion_actual, color_caracteristicas, edad, especie, fecha_nacimiento, nombre, peso, procedencia, raza, sexo, url_foto) values ('REFUGIO', 'GRIS MEDIO TIGRE', 3, 'FELINO', '2016-05-26', 'PACO', 5, 'procedencia', 'SIN RAZA', 'MACHO', 'https://cdn.wamiz.fr/cdn-cgi/image/quality=80,width=400,height=200,fit=cover/article/main-picture/5fe912d32df7d381109704.jpg');


--Servicios de Arca:
insert into servicios(descripcion, nombre, precio) values('Consulta general del animalito', 'Consulta Medica General', 20)
insert into servicios(descripcion, nombre, precio) values('Consulta para cirugia', 'Consulta Medica para Cirugia', 20)
insert into servicios(descripcion, nombre, precio) values('Desparacitacion de animalitos', 'Desparacitaciones', 5)
insert into servicios(descripcion, nombre, precio) values('Vacunacion de animalitos', 'Vacunas', 5.5)
insert into servicios(descripcion, nombre, precio) values('Esterilizacion de animalitos', 'Esterilizacion', 15.5)

--Citas
 insert into citas(estado, fecha_cita, motivo, nombre_cliente, id_veterinario) values(true, '2022-06-01 07:30:00', 'Consulta general', 'Jose Perez', 1)
 insert into citas(estado, fecha_cita, motivo, nombre_cliente, id_veterinario) values(true, '2022-06-01 09:30:00', 'Consulta cirugia', 'Martha Guaraca', 1)
 insert into citas(estado, fecha_cita, motivo, nombre_cliente, id_veterinario) values(true, '2022-06-01 14:30:00', 'Desparacitacion', 'Roberto Machado', 1)
 insert into citas(estado, fecha_cita, motivo, nombre_cliente, id_veterinario) values(true, '2022-06-01 16:30:00', 'Esterilizacion', 'Julia Peralta', 1)
 insert into citas(estado, fecha_cita, motivo, nombre_cliente, id_veterinario) values(true, '2022-06-02 16:30:00', 'Vacunacion', 'Ana Tenesaca', 1)

 --Detalle de citas
 insert into detalles_citas(id_cita, id_servicio) values(1, 1)
 insert into detalles_citas(id_cita, id_servicio) values(2, 2)
 insert into detalles_citas(id_cita, id_servicio) values(3, 3)
 insert into detalles_citas(id_cita, id_servicio) values(4, 5)
 insert into detalles_citas(id_cita, id_servicio) values(5, 4)