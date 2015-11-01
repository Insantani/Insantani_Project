-- phpMyAdmin SQL Dump
-- version 4.4.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Oct 29, 2015 at 05:15 AM
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

CREATE TABLE IF NOT EXISTS `article` (
  `article_id` tinyint(4) NOT NULL,
  `author` varchar(20) NOT NULL,
  `published_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `title` varchar(50) NOT NULL,
  `content` text NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `article`
--

INSERT INTO `article` (`article_id`, `author`, `published_at`, `title`, `content`) VALUES
(1, 'William Henry', '2015-10-24 09:36:35', 'To Eat A Carrot or Not to Eat', 'To Eat A Carrot or Not to Eat'),
(2, 'William Henry', '2015-10-28 14:33:11', 'Carrot can cure eye disease', 'Carrot is an quite big veggie. Many of us don''t like carrot because it is not delicious. However, it is one powerful veggie. it can cure your eye disease. If your child has an eye disease, such as minus, tell your child to consume more carrot');

-- --------------------------------------------------------

--
-- Table structure for table `article_tags`
--

CREATE TABLE IF NOT EXISTS `article_tags` (
  `article_id` tinyint(4) NOT NULL,
  `tags` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `article_tags`
--

INSERT INTO `article_tags` (`article_id`, `tags`) VALUES
(1, 'carrot'),
(2, 'carrot'),
(1, 'healthy'),
(2, 'healthy');

-- --------------------------------------------------------

--
-- Table structure for table `farmer`
--

CREATE TABLE IF NOT EXISTS `farmer` (
  `farmer_username` varchar(20) NOT NULL,
  `farmer_name` varchar(50) NOT NULL,
  `email` varchar(40) NOT NULL,
  `address` varchar(50) NOT NULL,
  `telephone_number` varchar(15) NOT NULL,
  `phone_number` varchar(15) NOT NULL,
  `rating` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `farmer`
--

INSERT INTO `farmer` (`farmer_username`, `farmer_name`, `email`, `address`, `telephone_number`, `phone_number`, `rating`) VALUES
('stevejobs', 'Steve Jobs', 'stevyj@gmail.com', 'Palo Alto', '', '08129530405', 5),
('williamhenry', 'William', 'william.hidayat@gmail.com', 'Sunrise Garden', '', '081287771252', 4.5);

-- --------------------------------------------------------

--
-- Table structure for table `nutrition_fact`
--

CREATE TABLE IF NOT EXISTS `nutrition_fact` (
  `nutrition_id` tinyint(4) NOT NULL,
  `product_id` tinyint(4) NOT NULL,
  `nutrition` varchar(10) NOT NULL,
  `size` double NOT NULL,
  `percentage` double NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `nutrition_fact`
--

INSERT INTO `nutrition_fact` (`nutrition_id`, `product_id`, `nutrition`, `size`, `percentage`) VALUES
(1, 1, 'Vitamin A', 10, 0.01),
(9, 1, 'Vitamin C', 100, 10);

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE IF NOT EXISTS `product` (
  `product_name` varchar(20) NOT NULL,
  `prod_price` int(6) NOT NULL,
  `stock_num` int(4) NOT NULL,
  `prod_desc` text NOT NULL,
  `id` tinyint(4) NOT NULL,
  `farmer_username` varchar(20) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`product_name`, `prod_price`, `stock_num`, `prod_desc`, `id`, `farmer_username`, `created_at`) VALUES
('Apple', 2500, 12, 'This is a red apple from a fresh productive land.', 1, 'stevejobs', '2015-10-29 03:32:48'),
('Green Apple', 4000, 30, 'This is the green apple. Taken from the name, you will be wondering what is green apple. You want it, come and buy it  ', 2, 'williamhenry', '2015-10-29 03:32:48'),
('Tomato', 100, 100, 'It''s red tomatoes day. Get your cheap red baby tomatoes now', 3, 'stevejobs', '2015-10-29 03:32:48');

-- --------------------------------------------------------

--
-- Table structure for table `tags`
--

CREATE TABLE IF NOT EXISTS `tags` (
  `tags` varchar(20) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tags`
--

INSERT INTO `tags` (`tags`, `created_at`) VALUES
('carrot', '2015-10-28 13:13:05'),
('healthy', '2015-10-28 13:13:05');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `user_id` tinyint(4) NOT NULL,
  `email` varchar(50) NOT NULL,
  `password` varchar(200) NOT NULL,
  `name` varchar(50) NOT NULL,
  `phone_number` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `article`
--
ALTER TABLE `article`
  ADD PRIMARY KEY (`article_id`);

--
-- Indexes for table `article_tags`
--
ALTER TABLE `article_tags`
  ADD PRIMARY KEY (`article_id`,`tags`),
  ADD KEY `fk_article_tags_tags` (`tags`);

--
-- Indexes for table `farmer`
--
ALTER TABLE `farmer`
  ADD PRIMARY KEY (`farmer_username`);

--
-- Indexes for table `nutrition_fact`
--
ALTER TABLE `nutrition_fact`
  ADD PRIMARY KEY (`nutrition_id`),
  ADD UNIQUE KEY `nutrion` (`nutrition`),
  ADD UNIQUE KEY `nutrition_id` (`nutrition_id`),
  ADD KEY `product_id` (`product_id`);

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id` (`id`),
  ADD KEY `farmer_id` (`farmer_username`);

--
-- Indexes for table `tags`
--
ALTER TABLE `tags`
  ADD PRIMARY KEY (`tags`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `article`
--
ALTER TABLE `article`
  MODIFY `article_id` tinyint(4) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `nutrition_fact`
--
ALTER TABLE `nutrition_fact`
  MODIFY `nutrition_id` tinyint(4) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=10;
--
-- AUTO_INCREMENT for table `product`
--
ALTER TABLE `product`
  MODIFY `id` tinyint(4) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `user_id` tinyint(4) NOT NULL AUTO_INCREMENT;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `article_tags`
--
ALTER TABLE `article_tags`
  ADD CONSTRAINT `fk_article_tags_article` FOREIGN KEY (`article_id`) REFERENCES `article` (`article_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_article_tags_tags` FOREIGN KEY (`tags`) REFERENCES `tags` (`tags`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `nutrition_fact`
--
ALTER TABLE `nutrition_fact`
  ADD CONSTRAINT `fk_nutrition_product` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`);

--
-- Constraints for table `product`
--
ALTER TABLE `product`
  ADD CONSTRAINT `fk_product_farmer` FOREIGN KEY (`farmer_username`) REFERENCES `farmer` (`farmer_username`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
