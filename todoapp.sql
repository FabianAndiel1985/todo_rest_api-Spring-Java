-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Erstellungszeit: 13. Mrz 2023 um 19:07
-- Server-Version: 10.4.21-MariaDB
-- PHP-Version: 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Datenbank: `todoapp`
--

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `to_do`
--

CREATE TABLE `to_do` (
  `id` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `is_done` bit(1) NOT NULL,
  `user_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Daten für Tabelle `to_do`
--

INSERT INTO `to_do` (`id`, `description`, `is_done`, `user_id`) VALUES
(1, 'Mir was gönnen', b'1', 1),
(3, 'der beste sein und niemals aufgeben', b'0', 1),
(4, 'der beste sein und niemals aufgeben', b'0', 3),
(5, 'gut zu mir sein', b'0', 3);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `secret` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Daten für Tabelle `user`
--

INSERT INTO `user` (`id`, `email`, `password`, `secret`) VALUES
(1, 'fabian.andiel@yahoo.com', '12345', NULL),
(2, 'fabian.andiel@gmail.com', '12345', NULL),
(3, 'fabian.andiel@mail.com', '12345', '87ae83f9-fc6e-4fa3-835c-640047ca814c'),
(4, 'fabian.andiel2@gmail.com', '12345', 'c1e9e9b9-ae8d-4d61-a663-ea99e94660a7');

--
-- Indizes der exportierten Tabellen
--

--
-- Indizes für die Tabelle `to_do`
--
ALTER TABLE `to_do`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKoqbjkoh0uwsuvkaoxlwf14f8o` (`user_id`);

--
-- Indizes für die Tabelle `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_ob8kqyqqgmefl0aco34akdtpe` (`email`);

--
-- AUTO_INCREMENT für exportierte Tabellen
--

--
-- AUTO_INCREMENT für Tabelle `to_do`
--
ALTER TABLE `to_do`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT für Tabelle `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Constraints der exportierten Tabellen
--

--
-- Constraints der Tabelle `to_do`
--
ALTER TABLE `to_do`
  ADD CONSTRAINT `FKoqbjkoh0uwsuvkaoxlwf14f8o` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
