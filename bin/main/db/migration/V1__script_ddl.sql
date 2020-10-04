CREATE TABLE `movie` (
  `id` int(11) NOT NULL ,
  `title` varchar(255) NOT NULL,
  `classification` varchar(255) NOT NULL,
  `release_Date` date NOT NULL,
  `trailer` varchar(255) NOT NULL,
  `website` varchar(255) NOT NULL,
  `director` varchar(255) NOT NULL,
  `original_language` varchar(255) NOT NULL,
  `sypnosys` varchar(255) NOT NULL,
  `id_gender` int(11) DEFAULT NULL
);

CREATE TABLE `gender` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL
);

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `password` varchar(255) NOT NULL,
  `nickname` varchar(255) NOT NULL,
  `token` varchar(255) NOT NULL
);

ALTER TABLE `movie`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_GENDER` (`id_gender`);

ALTER TABLE `gender`
  ADD PRIMARY KEY (`id`);

ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

ALTER TABLE `movie`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

ALTER TABLE `gender`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT; 

ALTER TABLE `MOVIE`
  ADD CONSTRAINT `FK_GENDER` FOREIGN KEY (`id_gender`) REFERENCES `gender` (`id`);
COMMIT;