-- phpMyAdmin SQL Dump
-- version 3.3.0
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Oct 22, 2015 at 08:14 AM
-- Server version: 5.5.41
-- PHP Version: 5.5.9-1ubuntu4.9

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `reservation_system`
--

-- --------------------------------------------------------

--
-- Table structure for table `customers`
--

CREATE TABLE IF NOT EXISTS `customers` (
  `Customer_id` int(15) NOT NULL AUTO_INCREMENT,
  `Customer_Name` varchar(225) NOT NULL,
  `Email` varchar(225) NOT NULL,
  `Phone_Number` varchar(30) NOT NULL,
  PRIMARY KEY (`Customer_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=111 ;

--
-- Dumping data for table `customers`
--

INSERT INTO `customers` (`Customer_id`, `Customer_Name`, `Email`, `Phone_Number`) VALUES
(1, 'Vishnu', 'bompally.vishnu@gmail.com', '2147483647'),
(2, 'Harish', 'harish123@gmail.com', '2147483647'),
(3, 'Harish', 'harish123@gmail.com', '2147483647'),
(4, 'Hkk', 'hkk@gmail.com', '2147483647'),
(5, 'rachita', 'rachita45@gmail.com', '2147483647'),
(6, 'Aardaya', 'aradya@gmail.com', '456987123'),
(7, 'lol', 'lol@gmail.com', '987456123'),
(8, 'Vishnu', 'bompally.vishnu@gmail.com', '963125478'),
(9, 'Vishnu Bompally', 'bompally.vishnu@gmail.com', '9155453544'),
(10, 'Vishnu Bompally', 'bompally.vishnu@gmail.com', '9155453544'),
(11, 'Vishnu Bompally', 'bompally.vishnu@gmail.com', '9155453544'),
(12, 'Vishnu Bompally', 'bompally.vishnu@gmail.com', '9155453544'),
(13, 'Vishnu Bompally', 'bompally.vishnu@gmail.com', '9155453544'),
(14, 'Yadgiri', 'yadi@gmail.com', '132456978'),
(15, 'Yadi', 'yadi@gmail.com', '13245879'),
(16, 'Varun Luthur', 'varun.luthur@gmail.com', '915478932'),
(17, 'Ganguly', 'gan@gmail.com', '7894561231'),
(18, 'Vishnu Bompally', 'bompally.vishnu@gmail.com', '9155453544'),
(19, 'Bob', 'bob@gmail.com', '4789456123'),
(20, 'kim', 'kim@gmail.com', '4569871213'),
(21, 'jim', 'jim@knoesis.org', '4789651231'),
(22, 'john', 'joh@knoesi.org', '14598745'),
(23, 'kom', 'kom@gmail.com', '13645'),
(24, 'cool', 'cool@gmail.com', '13264589'),
(25, 'com', 'com@gmail.com', '14987446554'),
(26, 'Vishnu Bompally', 'bompally.vishnu@gmail.com', '9155453544'),
(27, 'pinky', 'pinky@gmail.com', '1234569871'),
(28, 'gim', 'bom@gmail.com', '132456987'),
(29, 'him', 'bom@gmail.com', '12346678'),
(30, 'kim', 'kim@gmial.com', '465789132'),
(101, 'Vishnu Bompally', 'bompally.vishnu@gmail.com', '9155453544'),
(102, 'Harish Goud', 'harish@gmail.com', '9568412345'),
(103, 'Shirisha', 'shirisha@gmail.com', '8974152368'),
(104, 'Praveen', 'prabveen123@gmail.com', '9886451237'),
(105, 'Praveen', 'praveen@gmail.com', '9844632145'),
(106, 'Kevin', 'kk@gmail.com', '9564123789'),
(107, 'Vishnu Bompally', 'bompally.vishnu@gmail.com', '9155453544'),
(108, 'Harish Malhotra', 'harim@gmail.com', '9866555555'),
(109, 'Kailash', 'km12@gmail.com', '7894561237'),
(110, 'Vishnu Bompally', 'bompally.vishnu@gmail.com', '9155453544');

-- --------------------------------------------------------

--
-- Table structure for table `ownersDetails`
--

CREATE TABLE IF NOT EXISTS `ownersDetails` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `phone` varchar(30) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`email`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `ownersDetails`
--

INSERT INTO `ownersDetails` (`id`, `Name`, `email`, `password`, `address`, `avatar`, `phone`) VALUES
(1, 'vicky', 'admin@vs.com', 'admin', 'Hyderbad', 'profile.png', '98480022338');

-- --------------------------------------------------------

--
-- Table structure for table `reservations`
--

CREATE TABLE IF NOT EXISTS `reservations` (
  `reservation_id` int(15) NOT NULL AUTO_INCREMENT,
  `Number_Of_Guests` int(16) NOT NULL,
  `reservation_date` date NOT NULL,
  `reservation_time` time NOT NULL,
  `customer_id` int(11) NOT NULL,
  `table_id` int(15) NOT NULL,
  `reservation_Status` varchar(25) NOT NULL,
  PRIMARY KEY (`reservation_id`),
  KEY `table_id` (`table_id`),
  KEY `customer_id` (`customer_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=88 ;

--
-- Dumping data for table `reservations`
--

INSERT INTO `reservations` (`reservation_id`, `Number_Of_Guests`, `reservation_date`, `reservation_time`, `customer_id`, `table_id`, `reservation_Status`) VALUES
(80, 5, '2015-11-24', '20:02:00', 103, 25, 'Available'),
(82, 7, '2015-11-17', '14:00:00', 105, 27, 'Available'),
(84, 5, '2015-10-25', '15:07:00', 107, 25, 'Available'),
(85, 5, '2015-10-24', '20:05:00', 108, 25, 'Available'),
(86, 7, '2015-10-17', '14:05:00', 109, 27, 'Available'),
(87, 5, '2015-11-18', '14:04:00', 110, 25, 'Available');

-- --------------------------------------------------------

--
-- Table structure for table `tables`
--

CREATE TABLE IF NOT EXISTS `tables` (
  `table_Id` int(15) NOT NULL AUTO_INCREMENT,
  `table_Capacity` int(15) NOT NULL,
  `table_info` varchar(30) NOT NULL,
  PRIMARY KEY (`table_Id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=28 ;

--
-- Dumping data for table `tables`
--

INSERT INTO `tables` (`table_Id`, `table_Capacity`, `table_info`) VALUES
(22, 2, 'Candle Light Table'),
(23, 3, 'Lovely Table'),
(24, 4, 'Family Table'),
(25, 5, 'Mega Table'),
(26, 6, 'Party Table'),
(27, 7, 'Big Table');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `reservations`
--
ALTER TABLE `reservations`
  ADD CONSTRAINT `reservations_ibfk_6` FOREIGN KEY (`table_id`) REFERENCES `tables` (`table_Id`),
  ADD CONSTRAINT `reservations_ibfk_5` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`Customer_id`);
