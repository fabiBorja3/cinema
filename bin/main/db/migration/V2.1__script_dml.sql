INSERT INTO `city` (`id`, `name`) VALUES (NULL, 'BOGOTA');
INSERT INTO `city` (`id`, `name`) VALUES (NULL, 'CALI');
INSERT INTO `cinema` (`id`, `name`, `address`, `phone`, `id_city`) VALUES (NULL, 'UNICENTRO', 'Ak. 15 #124-30, Bogotá, Cundinamarca', '7789975', '1');
INSERT INTO `cinema` (`id`, `name`, `address`, `phone`, `id_city`) VALUES (NULL, 'PALMETTO PLAZA', 'Cl. 9 #48-81, Cali, Valle del Cauca', '8824565', '2');
INSERT INTO `person` (`id`, `name`, `lastname`, `address`, `phone`, `mail`) VALUES (NULL, 'KELLY', 'VASCO', 'CALLE 15A 23-55', '3124578986', 'KELLY@GMAIL.COM');
INSERT INTO `theater` (`id`, `total_capacity`, `id_movie`) VALUES (NULL, '20', '1');
INSERT INTO `theater` (`id`, `total_capacity`, `id_movie`) VALUES (NULL, '25', '2');
INSERT INTO `reservation` (`id`, `date`, `id_theater`, `id_person`) VALUES (NULL, '2020-10-20', '1', '1');
INSERT INTO `reservation` (`id`, `date`, `id_theater`, `id_person`) VALUES (NULL, '2020-10-22', '2', '1');