CREATE TABLE `city` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL
);

CREATE TABLE `cinema` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `address` varchar(255) NOT NULL,
  `phone` varchar(255) NOT NULL,
  `id_city` int(11) DEFAULT NULL
);

CREATE TABLE `person` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `lastname` varchar(255) NOT NULL,
  `address` varchar(255) NOT NULL,
  `phone` varchar(255) NOT NULL,
  `mail` varchar(255) NOT NULL
);

CREATE TABLE `theater` (
  `id` int(11) NOT NULL,
  `total_capacity` varchar(255) NOT NULL,
  `id_movie` int(11) DEFAULT NULL
);

CREATE TABLE `reservation` (
  `id` int(11) NOT NULL,
  `date` date NOT NULL,
  `id_theater` int(11) DEFAULT NULL,
  `id_person` int(11) DEFAULT NULL
);

ALTER TABLE `city`
  ADD PRIMARY KEY (`id`);

ALTER TABLE `cinema`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_CITY` (`id_city`);

ALTER TABLE `person`
  ADD PRIMARY KEY (`id`);

ALTER TABLE `theater`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_MOVIE` (`id_movie`);

ALTER TABLE `reservation`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_THEATER` (`id_theater`);

ALTER TABLE `reservation`
  ADD KEY `FK_PERSON` (`id_person`);

ALTER TABLE `city`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

ALTER TABLE `cinema`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

ALTER TABLE `person`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

ALTER TABLE `theater`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;   
  
ALTER TABLE `reservation`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;   

ALTER TABLE `cinema`
  ADD CONSTRAINT `FK_CITY` FOREIGN KEY (`id_city`) REFERENCES `city` (`id`);

ALTER TABLE `theater`
  ADD CONSTRAINT `FK_MOVIE` FOREIGN KEY (`id_movie`) REFERENCES `movie` (`id`);

ALTER TABLE `reservation`
  ADD CONSTRAINT `FK_THEATER` FOREIGN KEY (`id_theater`) REFERENCES `theater` (`id`);

ALTER TABLE `reservation`
  ADD CONSTRAINT `FK_PERSON` FOREIGN KEY (`id_person`) REFERENCES `person` (`id`);