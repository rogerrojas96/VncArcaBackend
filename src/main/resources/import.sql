--Roles
INSERT INTO roles VALUES (1, 'ROLE_ADMIN');
INSERT INTO roles VALUES (2, 'ROLE_DEFAULT_USER');
INSERT INTO roles VALUES (3, 'ROLE_VETERINARIO');
--Personas
insert into personas (cedula,nombre,apellidos,direccion,telefono,celular,correo) values ('0105661524','Roy','Morocho','San Joaquin','0453535','0980385997','roy@gmail.com');
insert into personas (cedula,nombre,apellidos,direccion,telefono,celular,correo) values ('0105661525','Estiven','Quishpi','Av Americas','0453536','0980385998','estiven@gmail.com');

--Veterinarios
insert into veterinarios (cargo)values('Principal')
--Veterinario - persona
insert into persona_veterinario (veterinario_id, persona_id)values(1,2)
--Usuarios
--pass "admin"
INSERT INTO usuarios (id,email,password,username,nombre,telefono) VALUES (1,'admin@gmail.com','$2a$10$9s26MbY0/Zl3aXQaAqrH0ObKHHDAq6NplfnBBH77510c1juIvpR8m','admin', 'Roy', '0911111111');

--Usuarios y rol
INSERT INTO usuario_roles VALUES (1,1);

--Animales
insert into animales (id,nombre,especie,caracteristicas,sexo,color,edad,raza,tamanyo,peso,fecha_nacimiento ,foto) values (1,'Tito','canino','qwerty','MACHO','Marron',3,'Mestizo','22',3, '2019-06-18','https://img.myloview.es/vinilos/perro-mestizo-sin-hogar-mirando-a-ti-400-141109517.jpg');