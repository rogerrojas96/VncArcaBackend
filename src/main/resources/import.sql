--Roles
--INSERT INTO roles VALUES (1, 'ROLE_ADMIN');
--INSERT INTO roles VALUES (2, 'ROLE_DEFAULT_USER');
--INSERT INTO roles VALUES (3, 'ROLE_VETERINARIO');

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
--INSERT INTO usuarios (id,password,username) VALUES (1,'$2a$10$9s26MbY0/Zl3aXQaAqrH0ObKHHDAq6NplfnBBH77510c1juIvpR8m','admin',1);

--Usuarios y rol
--INSERT INTO usuario_roles VALUES (1,1);

--FichasClinicas
insert into vncarcadb.fichas_clinicas (fecha_ingreso,motivo_consulta,hallazgos,temperatura,conjuntiva,frecuencia_cardiaca,frecuencia_respiratoria,TRC,mucosas,esterilizacion ,alimentacion,pronostico,diagnostico_diferencial,costo,veterinario_id,animal_id) values ('2019-06-18','dolor estomacal','ulceras en intestino',37.5,'Normal',130,15,'Bien', 'Normal',true,123.3,'Aun falta', 'Pendiente',54.3,1,1),('2020-06-18','dolor Respiratorio','Pulmones con mucosa',38,'Alta',130,15,'Bien', 'Alta',false,123.3,'Aun falta', 'Pendiente',54.3,2,1);

--Vacunas
INSERT INTO vncarcadb.vacuna (descripcion,nombre,tipo) VALUES ('Vacuna 1','Vacu','Tipo de vacuna ejemplo'),('Vacuna 2','Va','Tipo de vacuna ejemplo 2'),('Vacuna 3','Vac','Tipo de vacuna ejemplo 3');

--Carnet Vacunaciones

INSERT INTO vncarcadb.carnet_vacunaciones (fecha_aplicacion,fecha_proxima_aplicacion,vacuna_id,animal_id) VALUES ('2022-05-26','2022-06-26',1,1),('2022-05-30','2022-06-12',2,1);


	
--Animales
insert into vncarcadb.animales (id,nombre,especie,caracteristicas,sexo,color,edad,raza,tamanyo,peso,fecha_nacimiento ,foto) values (1,'Tito','canino','qwerty','MACHO','Marron',3,'Mestizo','22',3, '2019-06-18','https://img.myloview.es/vinilos/perro-mestizo-sin-hogar-mirando-a-ti-400-141109517.jpg');