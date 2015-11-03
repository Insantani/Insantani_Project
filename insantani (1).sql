-- phpMyAdmin SQL Dump
-- version 4.4.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Nov 03, 2015 at 04:34 PM
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
-- Creation: Nov 03, 2015 at 03:28 PM
--

CREATE TABLE IF NOT EXISTS `article` (
  `article_id` tinyint(4) NOT NULL,
  `author` varchar(20) NOT NULL,
  `published_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `title` varchar(50) NOT NULL,
  `content` text NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- RELATIONS FOR TABLE `article`:
--

--
-- Dumping data for table `article`
--

INSERT INTO `article` (`article_id`, `author`, `published_at`, `title`, `content`) VALUES
(1, 'William Henry', '2015-10-24 09:36:35', 'To Eat A Carrot or Not to Eat', 'To Eat A Carrot or Not to Eat'),
(2, 'William Henry', '2015-10-28 14:33:11', 'Carrot can cure eye disease', 'Carrot is an quite big veggie. Many of us don''t like carrot because it is not delicious. However, it is one powerful veggie. it can cure your eye disease. If your child has an eye disease, such as minus, tell your child to consume more carrot');

-- --------------------------------------------------------

--
-- Table structure for table `article_picture`
--
-- Creation: Nov 03, 2015 at 03:28 PM
--

CREATE TABLE IF NOT EXISTS `article_picture` (
  `article_id` tinyint(4) NOT NULL,
  `filename` varchar(100) NOT NULL,
  `file_path` varchar(400) NOT NULL,
  `posted_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- RELATIONS FOR TABLE `article_picture`:
--   `article_id`
--       `article` -> `article_id`
--

-- --------------------------------------------------------

--
-- Table structure for table `article_tags`
--
-- Creation: Nov 03, 2015 at 03:28 PM
--

CREATE TABLE IF NOT EXISTS `article_tags` (
  `article_id` tinyint(4) NOT NULL,
  `tags` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- RELATIONS FOR TABLE `article_tags`:
--   `article_id`
--       `article` -> `article_id`
--   `tags`
--       `tags` -> `tags`
--

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
-- Table structure for table `checkingout`
--
-- Creation: Nov 03, 2015 at 03:31 PM
--

CREATE TABLE IF NOT EXISTS `checkingout` (
  `email` varchar(30) NOT NULL,
  `address` varchar(100) NOT NULL,
  `product_id` tinyint(4) NOT NULL,
  `productQty` int(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- RELATIONS FOR TABLE `checkingout`:
--

-- --------------------------------------------------------

--
-- Table structure for table `farmer`
--
-- Creation: Nov 03, 2015 at 03:28 PM
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
-- RELATIONS FOR TABLE `farmer`:
--

--
-- Dumping data for table `farmer`
--

INSERT INTO `farmer` (`farmer_username`, `farmer_name`, `email`, `address`, `telephone_number`, `phone_number`, `rating`) VALUES
('stevejobs', 'Steve Jobs', 'stevyj@gmail.com', 'Palo Alto', '', '08129530405', 5),
('williamhenry', 'William', 'william.hidayat@gmail.com', 'Sunrise Garden', '', '081287771252', 4.5);

-- --------------------------------------------------------

--
-- Table structure for table `migrations`
--
-- Creation: Nov 03, 2015 at 03:28 PM
--

CREATE TABLE IF NOT EXISTS `migrations` (
  `migration` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `batch` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- RELATIONS FOR TABLE `migrations`:
--

--
-- Dumping data for table `migrations`
--

INSERT INTO `migrations` (`migration`, `batch`) VALUES
('2014_10_12_100000_create_password_resets_table', 1),
('2015_11_02_093027_create_oauth_tables', 1);

-- --------------------------------------------------------

--
-- Table structure for table `nutrition_fact`
--
-- Creation: Nov 03, 2015 at 03:28 PM
--

CREATE TABLE IF NOT EXISTS `nutrition_fact` (
  `nutrition_id` tinyint(4) NOT NULL,
  `product_id` tinyint(4) NOT NULL,
  `nutrition` varchar(10) NOT NULL,
  `size` double NOT NULL,
  `percentage` double NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

--
-- RELATIONS FOR TABLE `nutrition_fact`:
--   `product_id`
--       `product` -> `id`
--

--
-- Dumping data for table `nutrition_fact`
--

INSERT INTO `nutrition_fact` (`nutrition_id`, `product_id`, `nutrition`, `size`, `percentage`) VALUES
(1, 1, 'Vitamin A', 10, 0.01),
(9, 1, 'Vitamin C', 100, 10);

-- --------------------------------------------------------

--
-- Table structure for table `oauth_access_tokens`
--
-- Creation: Nov 03, 2015 at 03:28 PM
--

CREATE TABLE IF NOT EXISTS `oauth_access_tokens` (
  `access_token` varchar(40) NOT NULL,
  `client_id` varchar(80) NOT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  `expires` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `scope` varchar(2000) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- RELATIONS FOR TABLE `oauth_access_tokens`:
--

--
-- Dumping data for table `oauth_access_tokens`
--

INSERT INTO `oauth_access_tokens` (`access_token`, `client_id`, `user_id`, `expires`, `scope`) VALUES
('16fb1783cec85409a902da8e9f9a2552abfdd97e', 'testclient', 'bshaffer@gmail.com', '2015-11-02 13:32:47', NULL),
('193f4449658c48af90f6af44ab597f615325a8c3', 'testclient', 'bshaffer@gmail.com', '2015-11-02 07:33:00', NULL),
('23de59af76451bdb018cd12ddc7eaf13cb03037e', 'testclient', 'bshaffer@gmail.com', '2015-11-02 07:33:11', NULL),
('28724721da045d1393f65922bd82fe5d4a611ac7', 'testclient', 'bshaffer@gmail.com', '2015-11-02 13:32:47', NULL),
('486613c772743c6372501c0e43c719c0efb7dc9b', 'testclient', 'william.hidayat@gmail.com', '2015-11-02 08:27:59', NULL),
('71fd831b1b1eb7591223fe2385e1bef498bb4e74', 'testclient', 'bshaffer@gmail.com', '2015-11-02 13:32:47', NULL),
('80dad5b1fa004dd39aaf6f2d2ea731a45764705a', 'testclient', 'bshaffer@gmail.com', '2015-11-02 13:32:47', NULL),
('985cc31968308d816932bef019b733f9cd21a773', 'testclient', 'bshaffer@gmail.com', '2015-11-02 13:32:47', NULL),
('a8b10a15c829e791c943e59efee8bfb47ad0e3dd', 'testclient', 'bshaffer@gmail.com', '2015-11-02 13:32:47', NULL),
('b65a32efc5454e27cef1758030c736ef4f0aa716', 'testclient', 'bshaffer@gmail.com', '2015-11-02 13:32:47', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `oauth_authorization_codes`
--
-- Creation: Nov 03, 2015 at 03:28 PM
--

CREATE TABLE IF NOT EXISTS `oauth_authorization_codes` (
  `authorization_code` varchar(40) NOT NULL,
  `client_id` varchar(80) NOT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  `redirect_uri` varchar(2000) DEFAULT NULL,
  `expires` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `scope` varchar(2000) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- RELATIONS FOR TABLE `oauth_authorization_codes`:
--

-- --------------------------------------------------------

--
-- Table structure for table `oauth_clients`
--
-- Creation: Nov 03, 2015 at 03:28 PM
--

CREATE TABLE IF NOT EXISTS `oauth_clients` (
  `client_id` varchar(80) NOT NULL,
  `client_secret` varchar(80) NOT NULL,
  `redirect_uri` varchar(2000) NOT NULL,
  `grant_types` varchar(80) DEFAULT NULL,
  `scope` varchar(100) DEFAULT NULL,
  `user_id` varchar(80) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- RELATIONS FOR TABLE `oauth_clients`:
--

--
-- Dumping data for table `oauth_clients`
--

INSERT INTO `oauth_clients` (`client_id`, `client_secret`, `redirect_uri`, `grant_types`, `scope`, `user_id`) VALUES
('testclient', 'testpass', 'http://fake/', NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `oauth_jwt`
--
-- Creation: Nov 03, 2015 at 03:28 PM
--

CREATE TABLE IF NOT EXISTS `oauth_jwt` (
  `client_id` varchar(80) NOT NULL,
  `subject` varchar(80) DEFAULT NULL,
  `public_key` varchar(2000) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- RELATIONS FOR TABLE `oauth_jwt`:
--

-- --------------------------------------------------------

--
-- Table structure for table `oauth_refresh_tokens`
--
-- Creation: Nov 03, 2015 at 03:28 PM
--

CREATE TABLE IF NOT EXISTS `oauth_refresh_tokens` (
  `refresh_token` varchar(40) NOT NULL,
  `client_id` varchar(80) NOT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  `expires` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `scope` varchar(2000) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- RELATIONS FOR TABLE `oauth_refresh_tokens`:
--

--
-- Dumping data for table `oauth_refresh_tokens`
--

INSERT INTO `oauth_refresh_tokens` (`refresh_token`, `client_id`, `user_id`, `expires`, `scope`) VALUES
('0f54029fa313ff079afd2a5e0d920686f6f41fc3', 'testclient', 'bshaffer@gmail.com', '2015-11-02 13:31:55', NULL),
('25540edcbbaa8191278528b593cba9f08dc8ff84', 'testclient', 'bshaffer@gmail.com', '2015-11-16 06:33:00', NULL),
('85eea6d48aa2c24b775820665c66a6fd1c5515b9', 'testclient', 'bshaffer@gmail.com', '2015-11-02 13:31:55', NULL),
('99e5efbf9d3ba563f9103e800b1c130a0c579a49', 'testclient', 'bshaffer@gmail.com', '2015-11-02 13:31:55', NULL),
('9e7c26046813082602f4f2e9f4a19f177fddeec5', 'testclient', 'bshaffer@gmail.com', '2015-11-16 06:33:11', NULL),
('ae81a0c5e6d7926026924e56e29fae179e8de127', 'testclient', 'bshaffer@gmail.com', '2015-11-02 13:31:55', NULL),
('cd978859cd1cf19e38d861f9a5ebc024c7376fd4', 'testclient', 'bshaffer@gmail.com', '2015-11-02 13:31:55', NULL),
('d2cbdfaf041c6857d3243a4824c2f29d44bebd6b', 'testclient', 'bshaffer@gmail.com', '2015-11-02 13:31:55', NULL),
('db889dfd0e0ad9c064f84afe106f7a24b5cc8b2a', 'testclient', 'bshaffer@gmail.com', '2015-11-02 13:31:26', NULL),
('e86bcf7fd8d854318a203e5552ed755883a2706a', 'testclient', 'william.hidayat@gmail.com', '2015-11-16 07:27:59', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `oauth_scopes`
--
-- Creation: Nov 03, 2015 at 03:28 PM
--

CREATE TABLE IF NOT EXISTS `oauth_scopes` (
  `scope` text,
  `is_default` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- RELATIONS FOR TABLE `oauth_scopes`:
--

-- --------------------------------------------------------

--
-- Table structure for table `oauth_users`
--
-- Creation: Nov 03, 2015 at 03:28 PM
--

CREATE TABLE IF NOT EXISTS `oauth_users` (
  `email` varchar(255) NOT NULL,
  `password` varchar(2000) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `address` varchar(100) NOT NULL,
  `phone_number` varchar(15) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- RELATIONS FOR TABLE `oauth_users`:
--

--
-- Dumping data for table `oauth_users`
--

INSERT INTO `oauth_users` (`email`, `password`, `name`, `address`, `phone_number`, `created_at`, `updated_at`) VALUES
('bshaffer@gmail.com', '35d3f3604337a503fd2efd0d76591ddbd2fb6579', 'Brent', 'San Fransisco', '081287771252', '2015-11-02 13:43:27', NULL),
('william.hidayat@gmail.com', '$2y$10$00ZFksHvu0FxPKo43J3SkuDDz1mAXPrmmeYAPeSuzA4ruR97cXAEO', 'william henry', 'sunrise garden', '08129530405', '2015-11-02 06:51:02', '2015-11-02 06:51:02');

-- --------------------------------------------------------

--
-- Table structure for table `password_resets`
--
-- Creation: Nov 03, 2015 at 03:28 PM
--

CREATE TABLE IF NOT EXISTS `password_resets` (
  `email` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `token` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- RELATIONS FOR TABLE `password_resets`:
--

-- --------------------------------------------------------

--
-- Table structure for table `product`
--
-- Creation: Nov 03, 2015 at 03:28 PM
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
-- RELATIONS FOR TABLE `product`:
--   `farmer_username`
--       `farmer` -> `farmer_username`
--

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`product_name`, `prod_price`, `stock_num`, `prod_desc`, `id`, `farmer_username`, `created_at`) VALUES
('Apple', 2500, 12, 'This is a red apple from a fresh productive land.', 1, 'stevejobs', '2015-10-29 03:32:48'),
('Green Apple', 4000, 30, 'This is the green apple. Taken from the name, you will be wondering what is green apple. You want it, come and buy it  ', 2, 'williamhenry', '2015-10-29 03:32:48'),
('Tomato', 100, 100, 'It''s red tomatoes day. Get your cheap red baby tomatoes now', 3, 'stevejobs', '2015-10-29 03:32:48');

-- --------------------------------------------------------

--
-- Table structure for table `product_picture`
--
-- Creation: Nov 03, 2015 at 03:28 PM
--

CREATE TABLE IF NOT EXISTS `product_picture` (
  `product_id` tinyint(4) NOT NULL,
  `filename` varchar(100) NOT NULL,
  `file_path` varchar(200) NOT NULL,
  `posted_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- RELATIONS FOR TABLE `product_picture`:
--   `product_id`
--       `product` -> `id`
--

--
-- Dumping data for table `product_picture`
--

INSERT INTO `product_picture` (`product_id`, `filename`, `file_path`, `posted_at`) VALUES
(1, 'apple.png\r\n', 'Pictures/apple.png', '2015-11-03 14:34:37'),
(2, 'green_apple', 'PIctures/green_apple.png', '2015-11-03 15:08:29');

-- --------------------------------------------------------

--
-- Table structure for table `tags`
--
-- Creation: Nov 03, 2015 at 03:28 PM
--

CREATE TABLE IF NOT EXISTS `tags` (
  `tags` varchar(20) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- RELATIONS FOR TABLE `tags`:
--

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
-- Creation: Nov 03, 2015 at 03:28 PM
--

CREATE TABLE IF NOT EXISTS `users` (
  `user_id` tinyint(4) NOT NULL,
  `email` varchar(50) NOT NULL,
  `password` varchar(200) NOT NULL,
  `name` varchar(50) NOT NULL,
  `address` varchar(40) NOT NULL,
  `phone_number` varchar(15) NOT NULL,
  `remember_token` varchar(100) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- RELATIONS FOR TABLE `users`:
--

--
-- Indexes for dumped tables
--

--
-- Indexes for table `article`
--
ALTER TABLE `article`
  ADD PRIMARY KEY (`article_id`);

--
-- Indexes for table `article_picture`
--
ALTER TABLE `article_picture`
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
-- Indexes for table `oauth_access_tokens`
--
ALTER TABLE `oauth_access_tokens`
  ADD PRIMARY KEY (`access_token`);

--
-- Indexes for table `oauth_authorization_codes`
--
ALTER TABLE `oauth_authorization_codes`
  ADD PRIMARY KEY (`authorization_code`);

--
-- Indexes for table `oauth_clients`
--
ALTER TABLE `oauth_clients`
  ADD PRIMARY KEY (`client_id`);

--
-- Indexes for table `oauth_jwt`
--
ALTER TABLE `oauth_jwt`
  ADD PRIMARY KEY (`client_id`);

--
-- Indexes for table `oauth_refresh_tokens`
--
ALTER TABLE `oauth_refresh_tokens`
  ADD PRIMARY KEY (`refresh_token`);

--
-- Indexes for table `oauth_users`
--
ALTER TABLE `oauth_users`
  ADD PRIMARY KEY (`email`);

--
-- Indexes for table `password_resets`
--
ALTER TABLE `password_resets`
  ADD KEY `password_resets_email_index` (`email`),
  ADD KEY `password_resets_token_index` (`token`);

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id` (`id`),
  ADD KEY `farmer_id` (`farmer_username`);

--
-- Indexes for table `product_picture`
--
ALTER TABLE `product_picture`
  ADD PRIMARY KEY (`product_id`);

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
  MODIFY `user_id` tinyint(4) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `article_picture`
--
ALTER TABLE `article_picture`
  ADD CONSTRAINT `fk_article_article_picture` FOREIGN KEY (`article_id`) REFERENCES `article` (`article_id`);

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

--
-- Constraints for table `product_picture`
--
ALTER TABLE `product_picture`
  ADD CONSTRAINT `fk_product_product_picture` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
