-- phpMyAdmin SQL Dump
-- version 4.4.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Oct 24, 2015 at 12:26 PM
-- Server version: 5.6.26
-- PHP Version: 5.6.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `insantani`
--
CREATE DATABASE IF NOT EXISTS `insantani` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `insantani`;

-- --------------------------------------------------------

--
-- Table structure for table `article`
--
-- Creation: Oct 24, 2015 at 09:16 AM
--

CREATE TABLE IF NOT EXISTS `article` (
  `article_id` tinyint(4) NOT NULL,
  `author` varchar(20) NOT NULL,
  `published_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `title` varchar(50) NOT NULL,
  `content` text NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- RELATIONS FOR TABLE `article`:
--

--
-- Dumping data for table `article`
--

INSERT INTO `article` (`article_id`, `author`, `published_at`, `title`, `content`) VALUES
(1, 'William Henry', '2015-10-24 09:36:35', 'To Eat A Carrot or Not to Eat', 'To Eat A Carrot or Not to Eat');

-- --------------------------------------------------------

--
-- Table structure for table `farmer`
--
-- Creation: Oct 24, 2015 at 06:09 AM
--

CREATE TABLE IF NOT EXISTS `farmer` (
  `farmer_id` tinyint(4) NOT NULL,
  `farmer_name` varchar(30) NOT NULL,
  `farmer_address` varchar(50) NOT NULL,
  `email` varchar(30) NOT NULL,
  `rating` double NOT NULL,
  `telephone_number` varchar(12) NOT NULL,
  `phone_number` varchar(15) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- RELATIONS FOR TABLE `farmer`:
--

--
-- Dumping data for table `farmer`
--

INSERT INTO `farmer` (`farmer_id`, `farmer_name`, `farmer_address`, `email`, `rating`, `telephone_number`, `phone_number`) VALUES
(3, 'Steve Jobs', 'Palo Alto', 'example@gmail.com', 5, '2015-10-24 1', '08129530405'),
(4, 'William', 'Jakarta Barat', 'william.hidayat@gmail.com', 4.5, '', '081287771252');

-- --------------------------------------------------------

--
-- Table structure for table `nutrition_fact`
--
-- Creation: Oct 24, 2015 at 07:15 AM
--

CREATE TABLE IF NOT EXISTS `nutrition_fact` (
  `nutrition_id` tinyint(4) NOT NULL,
  `product_id` tinyint(4) NOT NULL,
  `nutrion` varchar(10) NOT NULL,
  `size` double NOT NULL,
  `percentae` double NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

--
-- RELATIONS FOR TABLE `nutrition_fact`:
--   `product_id`
--       `product` -> `id`
--

--
-- Dumping data for table `nutrition_fact`
--

INSERT INTO `nutrition_fact` (`nutrition_id`, `product_id`, `nutrion`, `size`, `percentae`) VALUES
(1, 1, 'Vitamin A', 10, 0.01),
(9, 1, 'Vitamin C', 100, 10);

-- --------------------------------------------------------

--
-- Table structure for table `product`
--
-- Creation: Oct 24, 2015 at 09:17 AM
--

CREATE TABLE IF NOT EXISTS `product` (
  `product_name` varchar(20) NOT NULL,
  `prod_price` int(6) NOT NULL,
  `stock_num` int(4) NOT NULL,
  `prod_desc` text NOT NULL,
  `id` tinyint(4) NOT NULL,
  `farmer_id` tinyint(4) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- RELATIONS FOR TABLE `product`:
--   `farmer_id`
--       `farmer` -> `farmer_id`
--

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`product_name`, `prod_price`, `stock_num`, `prod_desc`, `id`, `farmer_id`, `created_at`) VALUES
('Apple', 2500, 12, 'This is a red apple from a fresh productive land.', 1, 3, '2015-10-24 09:17:31'),
('Green Apple', 4000, 30, 'This is the green apple. Taken from the name, you will be wondering what is green apple. You want it, come and buy it  ', 2, 4, '2015-10-24 09:17:31'),
('Tomato', 100, 100, 'It''s red tomatoes day. Get your cheap red baby tomatoes now', 3, 3, '2015-10-24 09:17:31');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `article`
--
ALTER TABLE `article`
  ADD PRIMARY KEY (`article_id`);

--
-- Indexes for table `farmer`
--
ALTER TABLE `farmer`
  ADD PRIMARY KEY (`farmer_id`);

--
-- Indexes for table `nutrition_fact`
--
ALTER TABLE `nutrition_fact`
  ADD PRIMARY KEY (`nutrition_id`),
  ADD UNIQUE KEY `nutrion` (`nutrion`),
  ADD UNIQUE KEY `nutrition_id` (`nutrition_id`),
  ADD KEY `product_id` (`product_id`);

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id` (`id`),
  ADD KEY `farmer_id` (`farmer_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `article`
--
ALTER TABLE `article`
  MODIFY `article_id` tinyint(4) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `farmer`
--
ALTER TABLE `farmer`
  MODIFY `farmer_id` tinyint(4) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `nutrition_fact`
--
ALTER TABLE `nutrition_fact`
  MODIFY `nutrition_id` tinyint(4) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=11;
--
-- AUTO_INCREMENT for table `product`
--
ALTER TABLE `product`
  MODIFY `id` tinyint(4) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=4;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `nutrition_fact`
--
ALTER TABLE `nutrition_fact`
  ADD CONSTRAINT `fk_nutrition_product` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`);

--
-- Constraints for table `product`
--
ALTER TABLE `product`
  ADD CONSTRAINT `fk_product_farmer` FOREIGN KEY (`farmer_id`) REFERENCES `farmer` (`farmer_id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
