-- phpMyAdmin SQL Dump
-- version 5.1.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 29, 2024 at 05:35 PM
-- Server version: 10.4.24-MariaDB
-- PHP Version: 7.4.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `it355`
--

-- --------------------------------------------------------

--
-- Table structure for table `korisnik`
--

CREATE TABLE `korisnik` (
  `korisnik_id` int(11) NOT NULL,
  `korisnik_ime` text NOT NULL,
  `korisnik_email` text NOT NULL,
  `korisnik_sifra` text NOT NULL,
  `admin` tinyint(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `korisnik`
--

INSERT INTO `korisnik` (`korisnik_id`, `korisnik_ime`, `korisnik_email`, `korisnik_sifra`, `admin`) VALUES
(1, 'Djordje', 'djordje@gmail.com', 'djordje', 0),
(2, 'Djordje1', 'djordje1@gmail.com', 'djordje1', 0),
(3, 'admin', 'admin@gmail.com', 'admin', 1),
(4, 'DjordjeIZMopet', 'djordjeIZMopet@gmail.com', 'djordjeIZMopet', 0),
(6, 'new', 'new@gmail.com', 'new', 0);

-- --------------------------------------------------------

--
-- Table structure for table `korpa`
--

CREATE TABLE `korpa` (
  `korpa_id` int(11) NOT NULL,
  `korisnik_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `korpa`
--

INSERT INTO `korpa` (`korpa_id`, `korisnik_id`) VALUES
(1, 2),
(3, 6);

-- --------------------------------------------------------

--
-- Table structure for table `korpa_stavka`
--

CREATE TABLE `korpa_stavka` (
  `korpa_stavka_id` int(11) NOT NULL,
  `korpa_id` int(11) NOT NULL,
  `proizvod_id` int(11) NOT NULL,
  `kolicina` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `korpa_stavka`
--

INSERT INTO `korpa_stavka` (`korpa_stavka_id`, `korpa_id`, `proizvod_id`, `kolicina`) VALUES
(8, 1, 1, 2),
(9, 3, 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `proizvod`
--

CREATE TABLE `proizvod` (
  `proizvod_id` int(11) NOT NULL,
  `proizvod_ime` text NOT NULL,
  `proizvod_image_url` text NOT NULL,
  `cena` double NOT NULL,
  `opis` text NOT NULL,
  `tip` text NOT NULL,
  `proizvodjac_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `proizvod`
--

INSERT INTO `proizvod` (`proizvod_id`, `proizvod_ime`, `proizvod_image_url`, `cena`, `opis`, `tip`, `proizvodjac_id`) VALUES
(1, 'Novo Ime Proizvoda3', 'https://www.tri-o.rs/media/image/153953/mis-rm-mw-400-crni-182626.jpg', 12, 'Ovo je novi opis proizvoda3.', 'miss5', 2),
(3, 'tastaturaime', 'https://www.gamecentar.rs/media/catalog/product/cache/c2f3c0677390b484d4912739f0571d7f/3/2/32710-thumbnailaula-f3287-mehanicka-tastatura-blue-switch.jpg', 111, 'opistast', 'tastatura', 1);

-- --------------------------------------------------------

--
-- Table structure for table `proizvodjac`
--

CREATE TABLE `proizvodjac` (
  `proizvodjac_id` int(11) NOT NULL,
  `proizvodjac_ime` text NOT NULL,
  `opis` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `proizvodjac`
--

INSERT INTO `proizvodjac` (`proizvodjac_id`, `proizvodjac_ime`, `opis`) VALUES
(1, 'Samsung', 'Samsung opis'),
(2, 'Gigabyte', 'Gigabyte opis'),
(3, 'test', 'test opis');

-- --------------------------------------------------------

--
-- Table structure for table `recenzija`
--

CREATE TABLE `recenzija` (
  `recenzija_id` int(11) NOT NULL,
  `korisnik_id` int(11) NOT NULL,
  `proizvod_id` int(11) NOT NULL,
  `recenzija` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `recenzija`
--

INSERT INTO `recenzija` (`recenzija_id`, `korisnik_id`, `proizvod_id`, `recenzija`) VALUES
(1, 1, 1, 'Recc'),
(2, 2, 1, 'Odlican proizvod');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `korisnik`
--
ALTER TABLE `korisnik`
  ADD PRIMARY KEY (`korisnik_id`);

--
-- Indexes for table `korpa`
--
ALTER TABLE `korpa`
  ADD PRIMARY KEY (`korpa_id`),
  ADD KEY `korisnik_id` (`korisnik_id`);

--
-- Indexes for table `korpa_stavka`
--
ALTER TABLE `korpa_stavka`
  ADD PRIMARY KEY (`korpa_stavka_id`),
  ADD KEY `korpa_id` (`korpa_id`),
  ADD KEY `proizvod_id` (`proizvod_id`);

--
-- Indexes for table `proizvod`
--
ALTER TABLE `proizvod`
  ADD PRIMARY KEY (`proizvod_id`),
  ADD KEY `proizvodjac_id` (`proizvodjac_id`);

--
-- Indexes for table `proizvodjac`
--
ALTER TABLE `proizvodjac`
  ADD PRIMARY KEY (`proizvodjac_id`);

--
-- Indexes for table `recenzija`
--
ALTER TABLE `recenzija`
  ADD PRIMARY KEY (`recenzija_id`),
  ADD KEY `korisnik_id` (`korisnik_id`),
  ADD KEY `proizvod_id` (`proizvod_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `korisnik`
--
ALTER TABLE `korisnik`
  MODIFY `korisnik_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `korpa`
--
ALTER TABLE `korpa`
  MODIFY `korpa_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `korpa_stavka`
--
ALTER TABLE `korpa_stavka`
  MODIFY `korpa_stavka_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `proizvod`
--
ALTER TABLE `proizvod`
  MODIFY `proizvod_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `proizvodjac`
--
ALTER TABLE `proizvodjac`
  MODIFY `proizvodjac_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `recenzija`
--
ALTER TABLE `recenzija`
  MODIFY `recenzija_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `korpa`
--
ALTER TABLE `korpa`
  ADD CONSTRAINT `korpa_ibfk_1` FOREIGN KEY (`korisnik_id`) REFERENCES `korisnik` (`korisnik_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `korpa_stavka`
--
ALTER TABLE `korpa_stavka`
  ADD CONSTRAINT `korpa_stavka_ibfk_1` FOREIGN KEY (`korpa_id`) REFERENCES `korpa` (`korpa_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `korpa_stavka_ibfk_2` FOREIGN KEY (`proizvod_id`) REFERENCES `proizvod` (`proizvod_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `proizvod`
--
ALTER TABLE `proizvod`
  ADD CONSTRAINT `proizvod_ibfk_1` FOREIGN KEY (`proizvodjac_id`) REFERENCES `proizvodjac` (`proizvodjac_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `recenzija`
--
ALTER TABLE `recenzija`
  ADD CONSTRAINT `recenzija_ibfk_1` FOREIGN KEY (`korisnik_id`) REFERENCES `korisnik` (`korisnik_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `recenzija_ibfk_2` FOREIGN KEY (`proizvod_id`) REFERENCES `proizvod` (`proizvod_id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
