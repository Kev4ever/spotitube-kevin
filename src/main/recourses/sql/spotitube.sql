-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 21, 2018 at 04:50 PM
-- Server version: 10.1.35-MariaDB
-- PHP Version: 7.2.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `spotitube`
--

-- --------------------------------------------------------

--
-- Table structure for table `account`
--

CREATE TABLE `account` (
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `fullName` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `account`
--

INSERT INTO `account` (`username`, `password`, `fullName`) VALUES
('jim', '12345', 'Jim van de ligt'),
('kay', '223346', 'Verhaegh'),
('kevin', '123', 'Kevin van Schaijk'),
('randy', '123456', 'Randy Grouls');

-- --------------------------------------------------------

--
-- Table structure for table `playlist`
--

CREATE TABLE `playlist` (
  `id` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  `Account_username` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `playlist`
--

INSERT INTO `playlist` (`id`, `name`, `Account_username`) VALUES
(1, 'Electronic dance music', 'kevin'),
(2, 'Country ', 'kevin'),
(3, 'Pop', 'kevin'),
(4, 'Rap', 'jim');

-- --------------------------------------------------------

--
-- Table structure for table `sound`
--

CREATE TABLE `sound` (
  `Track_id` int(11) NOT NULL,
  `album` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sound`
--

INSERT INTO `sound` (`Track_id`, `album`) VALUES
(1, 'True'),
(2, 'Skrillex & Diplo Present Jack Ü'),
(3, 'Traveller');

-- --------------------------------------------------------

--
-- Table structure for table `token`
--

CREATE TABLE `token` (
  `token` varchar(100) NOT NULL,
  `Account_username` varchar(45) NOT NULL,
  `expiredate` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `token`
--

INSERT INTO `token` (`token`, `Account_username`, `expiredate`) VALUES
('12345', 'kevin', '2018-10-17 13:30:13'),
('7e5c4e9e-9a28-4d97-9176-c508b753a481', 'jim', '2018-10-22 16:10:01'),
('858e1030-e4a0-43a0-b632-5e4b08f89300', 'kevin', '2018-10-22 15:49:39'),
('893d2418-8e66-422f-8b1e-b52d46e2f15c', 'kevin', '2018-10-22 15:45:09'),
('e21610a7-00ac-46f4-88b0-37a90fe1213c', 'kevin', '2018-10-22 16:48:11');

-- --------------------------------------------------------

--
-- Table structure for table `track`
--

CREATE TABLE `track` (
  `id` int(11) NOT NULL,
  `title` varchar(45) NOT NULL,
  `performer` varchar(45) NOT NULL,
  `duration` int(11) NOT NULL,
  `playcount` int(11) NOT NULL,
  `url` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `track`
--

INSERT INTO `track` (`id`, `title`, `performer`, `duration`, `playcount`, `url`) VALUES
(1, ' Wake Me Up', 'Avicci', 185, 2, 'https://www.youtube.com/watch?v=IcrbM1l_BoI'),
(2, 'Where Are U Now', 'Skrillex', 200, 2, 'https://www.youtube.com/watch?v=nntGTK2Fhb0'),
(3, 'Tennessee Wiskey', 'Chris Stapleton', 250, 1, 'https://www.youtube.com/watch?v=4zAThXFOy2c'),
(4, 'City Bitch', 'Mini Thin', 300, 2, 'https://www.youtube.com/watch?v=qsNwDjUjbfs');

-- --------------------------------------------------------

--
-- Table structure for table `trackinplaylist`
--

CREATE TABLE `trackinplaylist` (
  `Track_id` int(11) NOT NULL,
  `Playlist_id` int(11) NOT NULL,
  `offlineAvailable` tinyint(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `trackinplaylist`
--

INSERT INTO `trackinplaylist` (`Track_id`, `Playlist_id`, `offlineAvailable`) VALUES
(1, 1, 1),
(2, 1, 0),
(3, 2, 1),
(4, 2, 0),
(4, 4, 0);

-- --------------------------------------------------------

--
-- Stand-in structure for view `tracks`
-- (See below for the actual view)
--
CREATE TABLE `tracks` (
`id` int(11)
,`title` varchar(45)
,`performer` varchar(45)
,`duration` int(11)
,`playcount` int(11)
,`url` varchar(45)
,`album` varchar(45)
,`description` varchar(45)
,`publicationDate` datetime
);

-- --------------------------------------------------------

--
-- Table structure for table `video`
--

CREATE TABLE `video` (
  `Track_id` int(11) NOT NULL,
  `description` varchar(45) DEFAULT 'undefined',
  `publicationDate` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `video`
--

INSERT INTO `video` (`Track_id`, `description`, `publicationDate`) VALUES
(4, 'country rap', '2018-10-17 13:30:13');

-- --------------------------------------------------------

--
-- Structure for view `tracks`
--
DROP TABLE IF EXISTS `tracks`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `tracks`  AS  select `track`.`id` AS `id`,`track`.`title` AS `title`,`track`.`performer` AS `performer`,`track`.`duration` AS `duration`,`track`.`playcount` AS `playcount`,`track`.`url` AS `url`,`sound`.`album` AS `album`,`video`.`description` AS `description`,`video`.`publicationDate` AS `publicationDate` from ((`track` left join `sound` on((`sound`.`Track_id` = `track`.`id`))) left join `video` on((`video`.`Track_id` = `track`.`id`))) ;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `account`
--
ALTER TABLE `account`
  ADD PRIMARY KEY (`username`);

--
-- Indexes for table `playlist`
--
ALTER TABLE `playlist`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_Playlist_Account1` (`Account_username`);

--
-- Indexes for table `sound`
--
ALTER TABLE `sound`
  ADD PRIMARY KEY (`Track_id`);

--
-- Indexes for table `token`
--
ALTER TABLE `token`
  ADD PRIMARY KEY (`token`,`Account_username`),
  ADD KEY `fk_Token_Account1` (`Account_username`);

--
-- Indexes for table `track`
--
ALTER TABLE `track`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `trackinplaylist`
--
ALTER TABLE `trackinplaylist`
  ADD PRIMARY KEY (`Track_id`,`Playlist_id`),
  ADD KEY `fk_TrackInPlaylist_Playlist1` (`Playlist_id`);

--
-- Indexes for table `video`
--
ALTER TABLE `video`
  ADD PRIMARY KEY (`Track_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `playlist`
--
ALTER TABLE `playlist`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `track`
--
ALTER TABLE `track`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `playlist`
--
ALTER TABLE `playlist`
  ADD CONSTRAINT `fk_Playlist_Account1` FOREIGN KEY (`Account_username`) REFERENCES `account` (`username`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `sound`
--
ALTER TABLE `sound`
  ADD CONSTRAINT `fk_Sound_Track1` FOREIGN KEY (`Track_id`) REFERENCES `track` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `token`
--
ALTER TABLE `token`
  ADD CONSTRAINT `fk_Token_Account1` FOREIGN KEY (`Account_username`) REFERENCES `account` (`username`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `trackinplaylist`
--
ALTER TABLE `trackinplaylist`
  ADD CONSTRAINT `fk_TrackInPlaylist_Playlist1` FOREIGN KEY (`Playlist_id`) REFERENCES `playlist` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_TrackInPlaylist_Track1` FOREIGN KEY (`Track_id`) REFERENCES `track` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `video`
--
ALTER TABLE `video`
  ADD CONSTRAINT `fk_Video_Track1` FOREIGN KEY (`Track_id`) REFERENCES `track` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
