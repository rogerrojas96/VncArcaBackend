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


--Vacunas
INSERT INTO vacuna (descripcion,nombre,tipo) VALUES ('Vacuna 1','Vacu','Tipo de vacuna ejemplo'),('Vacuna 2','Va','Tipo de vacuna ejemplo 2'),('Vacuna 3','Vac','Tipo de vacuna ejemplo 3');



--Animales
INSERT INTO vncarcadb.animales (caracteristicas,color,edad,especie,fecha_nacimiento,foto,nombre,peso,raza,sexo,tamanyo) VALUES('qwerty','Marron',3,'canino','2019-06-18','https://img.myloview.es/vinilos/perro-mestizo-sin-hogar-mirando-a-ti-400-141109517.jpg','Tito',3.0,'Mestizo','MACHO','22'),('qwerty','Marron',3,'gato','2019-06-18','https://www.purina-latam.com/sites/g/files/auxxlc391/files/styles/large/public/purina-para-que-sirven-los-bigotes-de-los-gatos.png?itok=gr5dH8dL','Roco',3.0,'Mestizo','MACHO','22'),('qwerty','Marron',3,'canino','2019-06-18','https://i.ytimg.com/vi/C7Au9sr0yDU/maxresdefault.jpg','Pipo',3.0,'Mestizo','MACHO','22'),('qwerty','Marron',3,'gato','2019-06-18','https://static.dw.com/image/60584047_303.jpg','Pulgas',3.0,'Mestizo','MACHO','22'),('qwerty','Marron',3,'canino','2019-06-18','https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/portrait-of-labradoodle-with-humorous-expression-royalty-free-image-690356759-1542399719.jpg','Manchas',3.0,'Mestizo','MACHO','22'),('qwerty','Marron',3,'gato','2019-06-18','https://estaticos.muyinteresante.es/media/cache/1140x_thumb/uploads/images/gallery/59c4f5655bafe82c692a7052/gato-marron_0.jpg','Pepa',3.0,'Mestizo','MACHO','22'),('qwerty','Marron',3,'canino','2019-06-18','https://res.cloudinary.com/dktx1oojk/image/upload/f_auto,q_90,w_1000,c_scale/web/globalassets/es/consejos/perro-consejos-5.jpg','Sami',3.0,'Mestizo','MACHO','22'),('qwerty','Marron',3,'gato','2019-06-18','https://www.tqpets.com.ec/wp-content/uploads/2018/08/personalidades-gatos-01.jpg','Scott',3.0,'Mestizo','MACHO','22'),('qwerty','Marron',3,'gato','2019-06-18','https://www.eltiempo.com/files/image_640_428/uploads/2021/11/28/61a3a0ac562fa.jpeg','Scooby',3.0,'Mestizo','MACHO','22'),('qwerty','Marron',3,'canino','2019-06-18','https://mnademexico.com/wp-content/uploads/2021/06/DSC07294-2-1024x937.jpg','Toby',3.0,'Mestizo','MACHO','22');
INSERT INTO vncarcadb.animales (caracteristicas,color,edad,especie,fecha_nacimiento,foto,nombre,peso,raza,sexo,tamanyo) VALUES('qwerty','Marron',3,'gato','2019-06-18','https://dam.ngenespanol.com/wp-content/uploads/2021/01/gatos-australia3.jpg','Tommy',3.0,'Mestizo','MACHO','22'),('qwerty','Marron',3,'canino','2019-06-18','https://www.elespectador.com/resizer/FIfoOIF2gmAEZPksir3nqAX4gPU=/525x350/filters:format(jpeg)/cloudfront-us-east-1.images.arcpublishing.com/elespectador/XQ5OB4SRZ5B5LD7S7QIRCLHTVY.jpg','Bombon',3.0,'Mestizo','MACHO','22'),('qwerty','Marron',3,'gato','2019-06-18','https://www.latercera.com/resizer/UvfBPh1sqyoGpgeuWPdVqcZbmfU=/900x600/smart/cloudfront-us-east-1.images.arcpublishing.com/copesa/3T6LCQ24TJGTVOZTDHXWLQXGJM.jpg','Tilingo',3.0,'Mestizo','MACHO','22'),('qwerty','Marron',3,'canino','2019-06-18','https://dam.ngenespanol.com/wp-content/uploads/2021/02/perros-azules-1.jpg','Bruno',3.0,'Mestizo','MACHO','22'),('qwerty','Marron',3,'gato','2019-06-18','https://ichef.bbci.co.uk/news/640/cpsprodpb/10E9B/production/_109757296_gettyimages-1128004359.jpg','Roberto',3.0,'Mestizo','MACHO','22'),('qwerty','Marron',3,'canino','2019-06-18','https://www.65ymas.com/uploads/s1/76/14/09/bigstock-beautiful-portrait-dog-breed-b-419947822_1_621x621.jpeg','Bethobeen',3.0,'Mestizo','MACHO','22'),('qwerty','Marron',3,'gato','2019-06-18','https://i.blogs.es/9fca7a/gato/1366_2000.jpeg','Lucha',3.0,'Mestizo','MACHO','22'),	 ('qwerty','Marron',3,'canino','2019-06-18','https://static.nationalgeographic.es/files/styles/image_3200/public/nationalgeographic_2731042.jpg?w=710&h=474','Ruperto',3.0,'Mestizo','MACHO','22'),('qwerty','Marron',3,'gato','2019-06-18','https://static.nationalgeographic.es/files/styles/image_3200/public/nationalgeographic_1926777.jpg?w=1600&h=900','Lucas',3.0,'Mestizo','MACHO','22'),('qwerty','Marron',3,'canino','2019-06-18','https://urgenciesveterinaries.com/wp-content/uploads/2021/01/hipotermia-perros-survet-00.jpg','Enana',3.0,'Mestizo','MACHO','22');
INSERT INTO vncarcadb.animales (caracteristicas,color,edad,especie,fecha_nacimiento,foto,nombre,peso,raza,sexo,tamanyo) VALUES('qwerty','Marron',3,'gato','2019-06-18','https://www.fundacion-affinity.org/sites/default/files/el-gato-necesita-tener-acceso-al-exterior.jpg','Luna',3.0,'Mestizo','MACHO','22'),('qwerty','Marron',3,'canino','2019-06-18','https://blog.mascotaysalud.com/wp-content/uploads/2019/04/san-bernardo-2.jpg','Jorgito',3.0,'Mestizo','MACHO','22'),('qwerty','Marron',3,'gato','2019-06-18','https://www.diariodesevilla.es/2021/09/01/mascotas/Cosas-sabias-gatos_1606949991_143253922_1200x675.jpg','Dory',3.0,'Mestizo','MACHO','22'),('qwerty','Marron',3,'canino','2019-06-18','https://www.purina-latam.com/sites/g/files/auxxlc391/files/styles/social_share_large/public/purina-pro-plan-sarna-en-perros.jpg?itok=3p90nLVt','Nemo',3.0,'Mestizo','MACHO','22');

--FichasClinicas
insert into fichas_clinicas (fecha_ingreso,motivo_consulta,hallazgos,temperatura,conjuntiva,frecuencia_cardiaca,frecuencia_respiratoria,TRC,mucosas,esterilizacion ,alimentacion,pronostico,diagnostico_diferencial,costo,veterinario_id,animal_id) values ('2019-06-18','dolor estomacal','ulceras en intestino',37.5,'Normal',130,15,'Bien', 'Normal',true,123.3,'Aun falta', 'Pendiente',54.3,1,1),('2020-06-18','dolor Respiratorio','Pulmones con mucosa',38,'Alta',130,15,'Bien', 'Alta',false,123.3,'Aun falta', 'Pendiente',54.3,2,1);
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

INSERT INTO vncarcadb.medicamento (cantidad,nombre_comercial,nombre_generico,precio) VALUES(1,'ejemplo comercial','ejemplo generico',23.45),(12,'ejemplo comercial 2','ejemplo generico 2',32.43);
