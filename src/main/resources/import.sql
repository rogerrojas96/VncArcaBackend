--Roles
INSERT INTO roles VALUES (1, 'ROLE_ADMIN');
INSERT INTO roles VALUES (2, 'ROLE_DEFAULT_USER');
INSERT INTO roles VALUES (3, 'ROLE_VETERINARIO');

--Personas para ingresar en el sistema
insert into personas (cedula,nombre,apellidos,direccion,telefono,celular,correo) values ('0105661524','Roy','Morocho','San Joaquin','0453535','0980385997','roy.morocho.est@tecazuay.edu.ec');
	--Persona para ser Veterinarios
	insert into personas (cedula,nombre,apellidos,direccion,telefono,celular,correo) values ('0105661527','Daniel','Rivera','27 de febrero','0453538','0980385100','christian.lucero.est@tecazuay.edu.ec');
	insert into personas (cedula,nombre,apellidos,direccion,telefono,celular,correo) values ('0105661525','Estiven','Quishpi','Av Americas','0453536','0980385998','jessica.vera.est@tecazuay.edu.ec');
	insert into personas (cedula,nombre,apellidos,direccion,telefono,celular,correo) values ('0105661526','Christian','Torres','9 de octubre','0453537','0980385999','maria.toledo.est@tecazuay.edu.ec');
	insert into personas (cedula,nombre,apellidos,direccion,telefono,celular,correo) values ('0105736326','Rubén','Tacuri','Av Americas','0453536','0980385998','edisson.nauta.est@tecazuay.edu.ec');
	insert into personas (cedula,nombre,apellidos,direccion,telefono,celular,correo) values ('0200982163','Marco','Santander','9 de octubre','0453537','0980385999','edisson.nauta.est@tecazuay.edu.ec');
	insert into personas (cedula,nombre,apellidos,direccion,telefono,celular,correo) values ('0913537742','Martha','Palacios','Av Americas','0453536','0980385998','edisson.nauta.est@tecazuay.edu.ec');
	insert into personas (cedula,nombre,apellidos,direccion,telefono,celular,correo) values ('0401197298','María','Urgiles','9 de octubre','0453537','0980385999','edisson.nauta.est@tecazuay.edu.ec');
	insert into personas (cedula,nombre,apellidos,direccion,telefono,celular,correo) values ('0702648551','Joseline','Pezantes','Av Americas','0453536','0980385998','eduardo.mendieta.t@gmail.com');
	insert into personas (cedula,nombre,apellidos,direccion,telefono,celular,correo) values ('1103756134','Sthefany','Argudo','9 de octubre','0453537','0980385999','edisson.nauta.est@tecazuay.edu.ec');

--Veterinarios
insert into veterinarios (id,cargo)values(1,'Principal')
insert into veterinarios (id,cargo)values(2,'Suplente')

-- Veterinario - persona
insert into veterinarios_personas (veterinario_id, persona_id)values(1,2)
insert into veterinarios_personas (veterinario_id, persona_id)values(2,3)

--Usuarios
--pass "admin"
INSERT INTO usuarios (enabled,password,username,persona_id) VALUES (True,'$2a$10$9s26MbY0/Zl3aXQaAqrH0ObKHHDAq6NplfnBBH77510c1juIvpR8m','admin',1);
INSERT INTO usuarios (enabled,password,username,persona_id) VALUES (True,'$2a$10$9s26MbY0/Zl3aXQaAqrH0ObKHHDAq6NplfnBBH77510c1juIvpR8m','veterinario1',2);
INSERT INTO usuarios (enabled,password,username,persona_id) VALUES (True,'$2a$10$9s26MbY0/Zl3aXQaAqrH0ObKHHDAq6NplfnBBH77510c1juIvpR8m','veterinario2',3);
INSERT INTO usuarios (enabled,password,username,persona_id) VALUES (False,'$2a$10$9s26MbY0/Zl3aXQaAqrH0ObKHHDAq6NplfnBBH77510c1juIvpR8m','user',4);

--Usuarios y rol
INSERT INTO usuarios_roles VALUES (1,1);
INSERT INTO usuarios_roles VALUES (4,2);
INSERT INTO usuarios_roles VALUES (2,3);


--Vacunas
INSERT INTO vacuna (descripcion,nombre,tipo) VALUES ('Vacuna 1','Vacu','Tipo de vacuna ejemplo'),('Vacuna 2','Va','Tipo de vacuna ejemplo 2'),('Vacuna 3','Vac','Tipo de vacuna ejemplo 3');



--Animales
insert into animales_refugio(adoptado, color_caracteristicas, edad, especie, fecha_nacimiento, id_imagen_animal_cld, lugar_estancia, nombre, nombre_imagen_animal_cld, observaciones_procedencia, peso, procedencia, raza, sexo, url_imagen_animal_cld,deleted) values(true, 'cafe', 2, 'Canino', '2022-06-28', 'vm6mhuyvlxub7iv98ivr', 'REFUGIO', 'Juanito', 'perro1', 'Estaba abandonada al pie de un arbol', 1.3, 'ECU911', 'Mezclado', 'MACHO', 'http://res.cloudinary.com/doko2wtms/image/upload/v1656473870/vm6mhuyvlxub7iv98ivr.png',0);
insert into animales_refugio(adoptado, color_caracteristicas, edad, especie, fecha_nacimiento, id_imagen_animal_cld, lugar_estancia, nombre, nombre_imagen_animal_cld, observaciones_procedencia, peso, procedencia, raza, sexo, ,deleted) values(true, 'cafe', 2, 'Canino', '2022-06-28', 'alyxbomtwryfo9ezvttl', 'REFUGIO', 'Pepita', 'perro2', 'Estaba abandonada al pie de un arbol', 1.3, 'ECU911', 'Mezclado', 'HEMBRA', 'http://res.cloudinary.com/doko2wtms/image/upload/v1656474384/alyxbomtwryfo9ezvttl.jpg',0);
insert into animales_refugio(adoptado, color_caracteristicas, edad, especie, fecha_nacimiento, id_imagen_animal_cld, lugar_estancia, nombre, nombre_imagen_animal_cld, observaciones_procedencia, peso, procedencia, raza, sexo, url_imagen_animal_cld, deleted) values(true, 'cafe', 2, 'Canino', '2022-06-28', 'l3sifbairuqmqewtrjuo', 'REFUGIO', 'Jorgito', 'perro3', 'Estaba abandonada al pie de un arbol', 1.3, 'ECU911', 'Mezclado', 'MACHO', 'http://res.cloudinary.com/doko2wtms/image/upload/v1656474500/l3sifbairuqmqewtrjuo.jpg',0);
insert into animales_refugio(adoptado, color_caracteristicas, edad, especie, fecha_nacimiento, id_imagen_animal_cld, lugar_estancia, nombre, nombre_imagen_animal_cld, observaciones_procedencia, peso, procedencia, raza, sexo, url_imagen_animal_cld, deleted) values(true, 'cafe', 2, 'Canino', '2022-06-28', 'jkxpeg30fd3cf3uuo2ue', 'REFUGIO', 'Pulgas', 'perro4', 'Estaba abandonada al pie de un arbol', 1.3, 'ECU911', 'Mezclado', 'MACHO', 'http://res.cloudinary.com/doko2wtms/image/upload/v1656474567/jkxpeg30fd3cf3uuo2ue.jpg',0);
insert into animales_refugio(adoptado, color_caracteristicas, edad, especie, fecha_nacimiento, id_imagen_animal_cld, lugar_estancia, nombre, nombre_imagen_animal_cld, observaciones_procedencia, peso, procedencia, raza, sexo, url_imagen_animal_cld, deleted) values(true, 'cafe', 2, 'Canino', '2022-06-28', 'waddkr6vpu3r9cll7rkq', 'REFUGIO', 'Martina', 'perro5', 'Estaba abandonada al pie de un arbol', 1.3, 'ECU911', 'Mezclado', 'HEMBRA', 'http://res.cloudinary.com/doko2wtms/image/upload/v1656474640/waddkr6vpu3r9cll7rkq.jpg',0);
insert into animales_refugio(adoptado, color_caracteristicas, edad, especie, fecha_nacimiento, id_imagen_animal_cld, lugar_estancia, nombre, nombre_imagen_animal_cld, observaciones_procedencia, peso, procedencia, raza, sexo, url_imagen_animal_cld, deleted) values(true, 'cafe', 2, 'Canino', '2022-06-28', 'k7bku0kfvf48us7ruq2r', 'REFUGIO', 'Sofia', 'perro6', 'Estaba abandonada al pie de un arbol', 1.3, 'ECU911', 'Mezclado', 'HEMBRA', 'http://res.cloudinary.com/doko2wtms/image/upload/v1656474713/k7bku0kfvf48us7ruq2r.jpg',0);
insert into animales_refugio(adoptado, color_caracteristicas, edad, especie, fecha_nacimiento, id_imagen_animal_cld, lugar_estancia, nombre, nombre_imagen_animal_cld, observaciones_procedencia, peso, procedencia, raza, sexo, url_imagen_animal_cld, deleted) values(false, 'cafe', 2, 'Canino', '2022-06-28', 'tz2a4198jtxqhnzimu1j', 'REFUGIO', 'Tukin', 'perro7', 'Estaba abandonada al pie de un arbol', 1.3, 'ECU911', 'Mezclado', 'MACHO', 'http://res.cloudinary.com/doko2wtms/image/upload/v1656474786/tz2a4198jtxqhnzimu1j.jpg',0);
insert into animales_refugio(adoptado, color_caracteristicas, edad, especie, fecha_nacimiento, id_imagen_animal_cld, lugar_estancia, nombre, nombre_imagen_animal_cld, observaciones_procedencia, peso, procedencia, raza, sexo, url_imagen_animal_cld, deleted) values(false, 'cafe', 2, 'Canino', '2022-06-28', 'cjurgfh5h6vianmw90a9', 'REFUGIO', 'Keke', 'perro8', 'Estaba abandonada al pie de un arbol', 1.3, 'ECU911', 'Mezclado', 'MACHO', 'http://res.cloudinary.com/doko2wtms/image/upload/v1656474841/cjurgfh5h6vianmw90a9.jpg',0);
insert into animales_refugio(adoptado, color_caracteristicas, edad, especie, fecha_nacimiento, id_imagen_animal_cld, lugar_estancia, nombre, nombre_imagen_animal_cld, observaciones_procedencia, peso, procedencia, raza, sexo, url_imagen_animal_cld, deleted) values(false, 'cafe', 2, 'Canino', '2022-06-28', 'butoehl3caaoyygyevuq', 'REFUGIO', 'Freddpa', 'perro9', 'Estaba abandonada al pie de un arbol', 1.3, 'ECU911', 'Mezclado', 'HEMBRA', 'http://res.cloudinary.com/doko2wtms/image/upload/v1656474918/butoehl3caaoyygyevuq.jpg',0);
insert into animales_refugio(adoptado, color_caracteristicas, edad, especie, fecha_nacimiento, id_imagen_animal_cld, lugar_estancia, nombre, nombre_imagen_animal_cld, observaciones_procedencia, peso, procedencia, raza, sexo, url_imagen_animal_cld, deleted) values(false, 'cafe', 2, 'Canino', '2022-06-28', 'lopwfdbbw3m35sm8vqhi', 'REFUGIO', 'Cesarin', 'perro10', 'Estaba abandonada al pie de un arbol', 1.3, 'ECU911', 'Mezclado', 'MACHO', 'http://res.cloudinary.com/doko2wtms/image/upload/v1656475000/lopwfdbbw3m35sm8vqhi.jpg',0);
INSERT INTO animales_refugio (adoptado,color_caracteristicas,deleted,edad,especie,fecha_nacimiento,id_imagen_animal_cld,lugar_estancia,nombre,nombre_imagen_animal_cld,observaciones_procedencia,peso,procedencia,raza,sexo,url_imagen_animal_cld) VALUES(0,'cafe',1,12,'Canina','2022-04-12','tkctukrrj008wm8ct1g9','CLINICA','PUBG','d','N/A',23.3,'ECU911','Pug','MACHO','http://res.cloudinary.com/doko2wtms/image/upload/v1656534603/tkctukrrj008wm8ct1g9.jpg'),(0,'cafe',1,12,'Canina','2022-04-12','fdeuagu2ezb8tbqzzehf','CLINICA','PUBG','d','N/A',23.3,'ECU911','Pug','MACHO','http://res.cloudinary.com/doko2wtms/image/upload/v1656534619/fdeuagu2ezb8tbqzzehf.jpg');

--FichasClinicas
insert into fichas_clinicas (fecha_ingreso,motivo_consulta,hallazgos,temperatura,conjuntiva,frecuencia_cardiaca,frecuencia_respiratoria,TRC,mucosas,esterilizacion ,alimentacion,pronostico,diagnostico_diferencial,examenes_solicitados,tipo_paciente,costo,veterinario_id,animal_id) values ('2019-06-18','dolor estomacal','ulceras en intestino',37.5,'Normal',130,15,'Bien', 'Normal','SI',123.3,'Aun falta', 'Pendiente','Creo que seria solo un text aria, pero habria que ver que dicen', 'INTERNO',54.3,1,1),('2020-06-18','dolor Respiratorio','Pulmones con mucosa',38,'Alta',130,15,'Bien', 'Alta',false,123.3,'Aun falta', 'Pendiente','Creo que seria solo un text aria, pero habria que ver que dicen','INTERNO',54.3,2,1);

--Carnet Vacunaciones
INSERT INTO carnet_vacunaciones (fecha_aplicacion,fecha_proxima_aplicacion,vacuna_id,animal_id) VALUES ('2022-05-26','2022-06-26',1,1),('2022-05-30','2022-06-12',2,1);

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


 --Medicamentos
 insert into medicamentos(cantidad, nombre_comercial, nombre_generico, precio) values(100, 'Medicamento Comercial 1', 'Medicamento Genérico 1', 10.5);
 insert into medicamentos(cantidad, nombre_comercial, nombre_generico, precio) values(200, 'Medicamento Comercial 2', 'Medicamento Genérico 2', 20.5);
 insert into medicamentos(cantidad, nombre_comercial, nombre_generico, precio) values(60, 'Medicamento Comercial 3', 'Medicamento Genérico 3', 5.5);
 insert into medicamentos(cantidad, nombre_comercial, nombre_generico, precio) values(75, 'Medicamento Comercial 4', 'Medicamento Genérico 4', 10);
 insert into medicamentos(cantidad, nombre_comercial, nombre_generico, precio) values(92, 'Medicamento Comercial 5', 'Medicamento Genérico 5', 11.7);
 insert into medicamentos(cantidad, nombre_comercial, nombre_generico, precio) values(151, 'Medicamento Comercial 6', 'Medicamento Genérico 6', 12.57);

 --Tratamientos
 insert into tratamientos(descripcion, estado, fecha_aplicacion, indicaciones, id_ficha_clinica) values('Descripcion 1', 'Activo', '2022-10-10', 'Indicación 1', 1);
 insert into tratamientos(descripcion, estado, fecha_aplicacion, indicaciones, id_ficha_clinica) values('Descripcion 2', 'Terminado', '2022-10-09', 'Indicación 2', 2);

 --Medicaciones
 insert into medicaciones(descripcion_md, dosis, duracion, fecha_caducidad, frecuencia, id_medicamento, id_tratamiento) values('descripcion_md 1', '5', 'duracion 1', '2022-11-12', 'frecuencia 1', 1, 1);
 insert into medicaciones(descripcion_md, dosis, duracion, fecha_caducidad, frecuencia, id_medicamento, id_tratamiento) values('descripcion_md 2', '4', 'duracion 2', '2022-11-13', 'frecuencia 2', 2, 1);
 insert into medicaciones(descripcion_md, dosis, duracion, fecha_caducidad, frecuencia, id_medicamento, id_tratamiento) values('descripcion_md 3', '3', 'duracion 3', '2022-11-14', 'frecuencia 3', 3, 1);
 insert into medicaciones(descripcion_md, dosis, duracion, fecha_caducidad, frecuencia, id_medicamento, id_tratamiento) values('descripcion_md 4', '2', 'duracion 4', '2022-11-15', 'frecuencia 4', 4, 2);
 insert into medicaciones(descripcion_md, dosis, duracion, fecha_caducidad, frecuencia, id_medicamento, id_tratamiento) values('descripcion_md 5', '1', 'duracion 5', '2022-11-16', 'frecuencia 5', 5, 2);
 insert into medicaciones(descripcion_md, dosis, duracion, fecha_caducidad, frecuencia, id_medicamento, id_tratamiento) values('descripcion_md 6', '0', 'duracion 6', '2022-11-17', 'frecuencia 6', 6, 2);

 --Donaciones
 insert into donaciones(cantidad, descripcion, id_persona) values(100.50, "Donacion de prueba", 3);
 insert into donaciones(cantidad, descripcion, id_persona) values(200.60, "Donacion de prueba", 3);
 insert into donaciones(cantidad, descripcion, id_persona) values(50.70, "Donacion de prueba", 4);
 insert into donaciones(cantidad, descripcion, id_persona) values(300.80, "Donacion de prueba", 5);
 insert into donaciones(cantidad, descripcion, id_persona) values(30.90, "Donacion de prueba", 4);
 insert into donaciones(cantidad, descripcion, id_persona) values(150.00, "Donacion de prueba", 2);

--Adoptantes
insert into adoptantes(nickname_facebook, telefono_familiar, id_persona) values('Mary Urgiles', '0911111111', 8);
insert into adoptantes(nickname_facebook, telefono_familiar, id_persona) values('Tu nenita', '0922222222', 9);
insert into adoptantes(nickname_facebook, telefono_familiar, id_persona) values('Teffa', '0933333333', 10);

--Adopciones
insert into adopciones(descripcion, fecha_adopcion, id_adoptante, id_animal) values('prueba', '2022-05-10', 1, 1);
insert into adopciones(descripcion, fecha_adopcion, id_adoptante, id_animal) values('prueba', '2022-05-10', 1, 2);
insert into adopciones(descripcion, fecha_adopcion, id_adoptante, id_animal) values('prueba', '2022-05-11', 2, 3);
insert into adopciones(descripcion, fecha_adopcion, id_adoptante, id_animal) values('prueba', '2022-05-11', 2, 4);
insert into adopciones(descripcion, fecha_adopcion, id_adoptante, id_animal) values('prueba', '2022-05-11', 2, 5);
insert into adopciones(descripcion, fecha_adopcion, id_adoptante, id_animal) values('prueba', '2022-05-12', 3, 6);

--Seguimientos
insert into seguimientos_adopciones(respuesta_adoptante, estado_seguimiento, fecha_seguimiento, mensaje_seguimiento, id_adopcion) values('Respuesta', false, '2022-10-01', 'mensaje', 3);
insert into seguimientos_adopciones(respuesta_adoptante, estado_seguimiento, fecha_seguimiento, mensaje_seguimiento, id_adopcion) values('Respuesta', false, '2022-10-01', 'mensaje', 4);
insert into seguimientos_adopciones(respuesta_adoptante, estado_seguimiento, fecha_seguimiento, mensaje_seguimiento, id_adopcion) values('Respuesta', false, '2022-10-01', 'mensaje', 5);


--Alarmas
INSERT INTO event_alarms (body,checked,event_day,event_type,animal_id) VALUES('Re',0,'2022-06-21','VACUNA',1),('qwqwwq',0,'2022-06-23','TRATAMIENTO',2),('sadasd',1,'2022-06-24','TRATAMIENTO',3),('dwedwed',1,'2022-07-12','VACUNA',1),('2rrr3',0,'2022-06-25','TRATAMIENTO',3);
