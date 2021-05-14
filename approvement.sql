-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 14, 2021 at 06:02 PM
-- Server version: 10.4.18-MariaDB
-- PHP Version: 7.3.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `healthcared`
--

-- --------------------------------------------------------

--
-- Table structure for table `approvement`
--

CREATE TABLE `approvement` (
  `app_ID` int(11) NOT NULL,
  `app_status` varchar(30) NOT NULL,
  `app_Details` varchar(200) NOT NULL,
  `app_date` varchar(12) NOT NULL,
  `endorser_type` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `approvement`
--

INSERT INTO `approvement` (`app_ID`, `app_status`, `app_Details`, `app_date`, `endorser_type`) VALUES
(0, 'approved', 'shoping item', '12.5.2021', 'invester');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `approvement`
--
ALTER TABLE `approvement`
  ADD PRIMARY KEY (`app_ID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
