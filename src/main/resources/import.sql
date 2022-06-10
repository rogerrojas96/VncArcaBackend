--Roles
INSERT INTO roles VALUES (1, 'ROLE_ADMIN');
INSERT INTO roles VALUES (2, 'ROLE_DEFAULT_USER');
INSERT INTO roles VALUES (3, 'ROLE_VETERINARIO');

--Personas para ingresar en el sistema
insert into personas (cedula,nombre,apellidos,direccion,telefono,celular,correo) values ('0105661524','Roy','Morocho','San Joaquin','0453535','0980385997','admin@gmail.com');
	--Persona para ser Veterinarios
	insert into personas (cedula,nombre,apellidos,direccion,telefono,celular,correo) values ('0105661527','Daniel','Rivera','27 de febrero','0453538','0980385100','user@gmail.com');
	insert into personas (cedula,nombre,apellidos,direccion,telefono,celular,correo) values ('0105661525','Estiven','Quishpi','Av Americas','0453536','0980385998','veterianario@gmail.com');
	insert into personas (cedula,nombre,apellidos,direccion,telefono,celular,correo) values ('0105661526','Christian','Torres','9 de octubre','0453537','0980385999','veterianario2@gmail.com');
	insert into personas (cedula,nombre,apellidos,direccion,telefono,celular,correo) values ('0105736326','Rubén','Tacuri','Av Americas','0453536','0980385998','veterianario3@gmail.com');
	insert into personas (cedula,nombre,apellidos,direccion,telefono,celular,correo) values ('0200982163','Marco','Santander','9 de octubre','0453537','0980385999','veterianario4@gmail.com');
	insert into personas (cedula,nombre,apellidos,direccion,telefono,celular,correo) values ('0913537742','Martha','Palacios','Av Americas','0453536','0980385998','veterianario5@gmail.com');
	insert into personas (cedula,nombre,apellidos,direccion,telefono,celular,correo) values ('0401197298','María','Urgiles','9 de octubre','0453537','0980385999','veterianario6@gmail.com');
	insert into personas (cedula,nombre,apellidos,direccion,telefono,celular,correo) values ('0702648551','Joseline','Pezantes','Av Americas','0453536','0980385998','veterianario7@gmail.com');
	insert into personas (cedula,nombre,apellidos,direccion,telefono,celular,correo) values ('1103756134','Sthefany','Argudo','9 de octubre','0453537','0980385999','veterianario8@gmail.com');

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
INSERT INTO vncarcadb.animales (color_caracteristicas, edad, especie, fecha_nacimiento, foto, lugar_estancia, nombre, observaciones_procedencia, peso, procedencia, raza, sexo, tamanyo) VALUES('Marron', 3, 'canino', '2019-06-18', 'https://img.myloview.es/vinilos/perro-mestizo-sin-hogar-mirando-a-ti-400-141109517.jpg', 'CLÍNICA', 'qwerty', 'Encontrado en un bosque, etc.', 3.0, 'PARTICULAR', 'Mestizo', 'MACHO', '22');
INSERT INTO vncarcadb.animales (color_caracteristicas, edad, especie, fecha_nacimiento, foto, lugar_estancia, nombre, observaciones_procedencia, peso, procedencia, raza, sexo, tamanyo) VALUES('Marron', 3, 'canino', '2019-06-18', 'https://www.purina-latam.com/sites/g/files/auxxlc391/files/styles/large/public/purina-para-que-sirven-los-bigotes-de-los-gatos.png?itok=gr5dH8dL', 'CLÍNICA', 'chico', 'Encontrado en un bosque, etc.', 3.0, 'PARTICULAR', 'Mestizo', 'MACHO', '22');
INSERT INTO vncarcadb.animales (color_caracteristicas, edad, especie, fecha_nacimiento, foto, lugar_estancia, nombre, observaciones_procedencia, peso, procedencia, raza, sexo, tamanyo) VALUES('Marron', 3, 'canino', '2019-06-18', 'https://i.ytimg.com/vi/C7Au9sr0yDU/maxresdefault.jpg', 'CLÍNICA', 'tito', 'Encontrado en un bosque, etc.', 3.0, 'PARTICULAR', 'Mestizo', 'MACHO', '22');
INSERT INTO vncarcadb.animales (color_caracteristicas, edad, especie, fecha_nacimiento, foto, lugar_estancia, nombre, observaciones_procedencia, peso, procedencia, raza, sexo, tamanyo) VALUES('Marron', 3, 'canino', '2019-06-18', 'https://static.dw.com/image/60584047_303.jpg', 'CLÍNICA', 'rex', 'Encontrado en un bosque, etc.', 3.0, 'PARTICULAR', 'Mestizo', 'MACHO', '22');
INSERT INTO vncarcadb.animales (color_caracteristicas, edad, especie, fecha_nacimiento, foto, lugar_estancia, nombre, observaciones_procedencia, peso, procedencia, raza, sexo, tamanyo) VALUES('Marron', 3, 'canino', '2019-06-18', 'https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/portrait-of-labradoodle-with-humorous-expression-royalty-free-image-690356759-1542399719.jpg', 'CLÍNICA', 'pichu', 'Encontrado en un bosque, etc.', 3.0, 'PARTICULAR', 'Mestizo', 'MACHO', '22');
INSERT INTO vncarcadb.animales (color_caracteristicas, edad, especie, fecha_nacimiento, foto, lugar_estancia, nombre, observaciones_procedencia, peso, procedencia, raza, sexo, tamanyo) VALUES('Marron', 3, 'canino', '2019-06-18', 'https://estaticos.muyinteresante.es/media/cache/1140x_thumb/uploads/images/gallery/59c4f5655bafe82c692a7052/gato-marron_0.jpg', 'CLÍNICA', 'samael', 'Encontrado en un bosque, etc.', 3.0, 'PARTICULAR', 'Mestizo', 'MACHO', '22');
INSERT INTO vncarcadb.animales (color_caracteristicas, edad, especie, fecha_nacimiento, foto, lugar_estancia, nombre, observaciones_procedencia, peso, procedencia, raza, sexo, tamanyo) VALUES('Marron', 3, 'canino', '2019-06-18', 'https://res.cloudinary.com/dktx1oojk/image/upload/f_auto,q_90,w_1000,c_scale/web/globalassets/es/consejos/perro-consejos-5.jpg', 'CLÍNICA', 'tommy', 'Encontrado en un bosque, etc.', 3.0, 'PARTICULAR', 'Mestizo', 'MACHO', '22');
INSERT INTO vncarcadb.animales (color_caracteristicas, edad, especie, fecha_nacimiento, foto, lugar_estancia, nombre, observaciones_procedencia, peso, procedencia, raza, sexo, tamanyo) VALUES('Marron', 3, 'canino', '2019-06-18', 'https://www.tqpets.com.ec/wp-content/uploads/2018/08/personalidades-gatos-01.jpg', 'CLÍNICA', 'sancho', 'Encontrado en un bosque, etc.', 3.0, 'PARTICULAR', 'Mestizo', 'MACHO', '22');
INSERT INTO vncarcadb.animales (color_caracteristicas, edad, especie, fecha_nacimiento, foto, lugar_estancia, nombre, observaciones_procedencia, peso, procedencia, raza, sexo, tamanyo) VALUES('Marron', 3, 'canino', '2019-06-18', 'https://www.eltiempo.com/files/image_640_428/uploads/2021/11/28/61a3a0ac562fa.jpeg', 'CLÍNICA', 'chavo', 'Encontrado en un bosque, etc.', 3.0, 'PARTICULAR', 'Mestizo', 'MACHO', '22');

--FichasClinicas
insert into fichas_clinicas (fecha_ingreso,motivo_consulta,hallazgos,temperatura,conjuntiva,frecuencia_cardiaca,frecuencia_respiratoria,TRC,mucosas,esterilizacion ,alimentacion,pronostico,diagnostico_diferencial,examenes_solicitados,tipo_paciente,costo,veterinario_id,animal_id) values ('2019-06-18','dolor estomacal','ulceras en intestino',37.5,'Normal',130,15,'Bien', 'Normal',true,123.3,'Aun falta', 'Pendiente','Creo que seria solo un text aria, pero habria que ver que dicen', 'INTERNO',54.3,1,1),('2020-06-18','dolor Respiratorio','Pulmones con mucosa',38,'Alta',130,15,'Bien', 'Alta',false,123.3,'Aun falta', 'Pendiente','Creo que seria solo un text aria, pero habria que ver que dicen','INTERNO',54.3,2,1);
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


 --Medicamento
 INSERT INTO medicamento (id, nombre_comercial, nombre_generico, cantidad, precio) VALUES ( 1, 'vinamina B', 'vb', 4, 0.30)
 INSERT INTO medicamento (id, nombre_comercial, nombre_generico, cantidad, precio) VALUES ( 3, 'vinamina c', 'vc', 4, 0.30)

 --Medicacion
 INSERT INTO medicacion (id, descripcion_md, dosis, frecuencia, duracion, fecha_caducidad, id_medicamento) VALUES ( 1, 'vitamina para los huesos', '2', 'despues del almuerzo', '15 dias', '2022-06-02 16:30:00', 1 )
 INSERT INTO medicacion (id, descripcion_md, dosis, frecuencia, duracion, fecha_caducidad, id_medicamento) VALUES ( 2, 'vitamina para el estomago', '20', 'despues del almuerzo', '20 dias', '2022-06-02 16:30:00', 2 )


 --Donaciones
 insert into donaciones(cantidad, descripcion, id_persona) values(100.50, "Donacion de prueba", 3);
 insert into donaciones(cantidad, descripcion, id_persona) values(200.60, "Donacion de prueba", 3);
 insert into donaciones(cantidad, descripcion, id_persona) values(50.70, "Donacion de prueba", 4);
 insert into donaciones(cantidad, descripcion, id_persona) values(300.80, "Donacion de prueba", 5);
 insert into donaciones(cantidad, descripcion, id_persona) values(30.90, "Donacion de prueba", 4);
 insert into donaciones(cantidad, descripcion, id_persona) values(150.00, "Donacion de prueba", 2);

--Medicamentos
INSERT INTO medicamento (cantidad,nombre_comercial,nombre_generico,precio) VALUES(1,'ejemplo comercial','ejemplo generico',23.45),(12,'ejemplo comercial 2','ejemplo generico 2',32.43);

