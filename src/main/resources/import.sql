--Roles
INSERT INTO roles VALUES (1, 'ROLE_ADMIN');
INSERT INTO roles VALUES (2, 'ROLE_DEFAULT_USER');
INSERT INTO roles VALUES (3, 'ROLE_VETERINARIO');

--Usuarios
--pass "admin"
INSERT INTO usuarios VALUES (1,'admin@gmail.com','$2a$10$9s26MbY0/Zl3aXQaAqrH0ObKHHDAq6NplfnBBH77510c1juIvpR8m','admin', 'Roy', '0911111111');

--Usuarios y rol
INSERT INTO usuario_roles VALUES (1,1);

--Animales
insert into animales (id,nombre,especie,caracteristicas,sexo,color,edad,raza,tamanyo,peso,fecha_nacimiento ,foto) values (1,'Tito','canino','qwerty','MACHO','Marron',3,'Mestizo','22',3, '2019-06-18','https://img.myloview.es/vinilos/perro-mestizo-sin-hogar-mirando-a-ti-400-141109517.jpg');