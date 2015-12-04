-- phpMyAdmin SQL Dump
-- version 4.4.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Dec 04, 2015 at 02:47 AM
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
-- Creation: Nov 04, 2015 at 12:58 PM
--

CREATE TABLE IF NOT EXISTS `article` (
  `article_id` tinyint(4) NOT NULL,
  `author` varchar(20) NOT NULL,
  `published_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `title` varchar(50) NOT NULL,
  `content` text NOT NULL,
  `article_filename` varchar(100) NOT NULL,
  `article_filepath` varchar(100) NOT NULL,
  `article_picture_url` varchar(50) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- RELATIONS FOR TABLE `article`:
--

--
-- Dumping data for table `article`
--

INSERT INTO `article` (`article_id`, `author`, `published_at`, `title`, `content`, `article_filename`, `article_filepath`, `article_picture_url`) VALUES
(1, 'William Henry', '2015-11-04 13:05:51', 'To Eat A Carrot or Not to Eat', 'To Eat A Carrot or Not to Eat', 'eating_carrot.png', '\\products\\eating_carrot.png', ''),
(2, 'William Henry', '2015-11-04 13:06:02', 'Carrot can cure eye disease', 'Carrot is an quite big veggie. Many of us don''t like carrot because it is not delicious. However, it is one powerful veggie. it can cure your eye disease. If your child has an eye disease, such as minus, tell your child to consume more carrot', 'carrot.png', '\\products\\carrot.png', '');

-- --------------------------------------------------------

--
-- Table structure for table `article_picture`
--
-- Creation: Oct 29, 2015 at 05:39 AM
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
--   `article_id`
--       `article` -> `article_id`
--

-- --------------------------------------------------------

--
-- Table structure for table `article_tags`
--
-- Creation: Oct 28, 2015 at 01:10 PM
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
-- Creation: Dec 01, 2015 at 11:17 AM
--

CREATE TABLE IF NOT EXISTS `checkingout` (
  `checkingout_id` tinyint(4) NOT NULL,
  `user_id` varchar(100) NOT NULL,
  `address` varchar(100) NOT NULL,
  `product_id` tinyint(4) NOT NULL,
  `productQty` int(200) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT NULL,
  `status` varchar(25) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=latin1;

--
-- RELATIONS FOR TABLE `checkingout`:
--   `product_id`
--       `product` -> `id`
--   `user_id`
--       `oauth_users` -> `user_id`
--   `product_id`
--       `product` -> `id`
--   `user_id`
--       `oauth_users` -> `user_id`
--

--
-- Dumping data for table `checkingout`
--

INSERT INTO `checkingout` (`checkingout_id`, `user_id`, `address`, `product_id`, `productQty`, `created_at`, `updated_at`, `status`) VALUES
(1, 'william.hidayat@gmail.com563980a8a8f184.51608576', 'jalan surya asih 1 blok m/6', 1, 100, '2015-12-01 04:17:50', '2015-12-01 04:17:50', 'pending'),
(2, 'william.hidayat@gmail.com563980a8a8f184.51608576', 'jalan surya asih 1 blok m/6', 1, 5, '2015-12-01 04:19:35', '2015-12-01 04:19:35', 'pending'),
(3, 'william.hidayat@gmail.com563980a8a8f184.51608576', 'jalan surya asih 1 blok m/6', 1, 5, '2015-12-01 04:19:56', '2015-12-01 04:19:56', 'pending'),
(4, 'william.hidayat@gmail.com563980a8a8f184.51608576', 'jalan surya asih 1 blok m/6', 1, 5, '2015-12-01 04:28:41', '2015-12-01 04:28:41', 'pending'),
(5, 'william.hidayat@gmail.com563980a8a8f184.51608576', 'jalan surya asih 1 blok m/6', 1, 5, '2015-12-01 04:32:08', '2015-12-01 04:32:08', 'pending'),
(6, 'william.hidayat@gmail.com563980a8a8f184.51608576', 'jalan surya asih 1 blok m/6', 1, 5, '2015-12-01 04:38:30', '2015-12-01 04:38:30', 'pending'),
(7, 'william.hidayat@gmail.com563980a8a8f184.51608576', 'jalan surya asih 1 blok m/6', 1, 5, '2015-12-01 04:38:36', '2015-12-01 04:38:36', 'pending'),
(8, 'william.hidayat@gmail.com563980a8a8f184.51608576', 'jalan surya asih 1 blok m/6', 1, 5, '2015-12-01 04:38:48', '2015-12-01 04:38:48', 'pending'),
(9, 'william.hidayat@gmail.com563980a8a8f184.51608576', 'jalan surya asih 1 blok m/6', 1, 5, '2015-12-01 04:38:55', '2015-12-01 04:38:55', 'pending'),
(10, 'william.hidayat@gmail.com563980a8a8f184.51608576', 'jalan surya asih 1 blok m/6', 1, 5, '2015-12-01 04:39:37', '2015-12-01 04:39:37', 'pending'),
(11, 'william.hidayat@gmail.com563980a8a8f184.51608576', 'jalan surya asih 1 blok m/6', 1, 5, '2015-12-01 04:39:57', '2015-12-01 04:39:57', 'pending'),
(12, 'william.hidayat@gmail.com563980a8a8f184.51608576', 'jalan surya asih 1 blok m/6', 1, 5, '2015-12-01 04:40:22', '2015-12-01 04:40:22', 'pending'),
(13, 'william.hidayat@gmail.com563980a8a8f184.51608576', 'jalan surya asih 1 blok m/6', 1, 5, '2015-12-01 04:40:31', '2015-12-01 04:40:31', 'pending'),
(14, 'william.hidayat@gmail.com563980a8a8f184.51608576', 'jalan surya asih 1 blok m/6', 1, 5, '2015-12-01 04:40:40', '2015-12-01 04:40:40', 'pending'),
(15, 'william.hidayat@gmail.com563980a8a8f184.51608576', 'jalan surya asih 1 blok m/6', 3, 5, '2015-12-01 04:47:10', '2015-12-01 04:47:10', 'pending'),
(16, 'william.hidayat@gmail.com563980a8a8f184.51608576', 'jalan surya asih 1 blok m/6', 3, 73, '2015-12-01 04:47:35', '2015-12-01 04:47:35', 'pending'),
(17, 'william.hidayat@gmail.com563980a8a8f184.51608576', 'jalan surya asih 1 blok m/6', 3, 73, '2015-12-01 05:16:01', '2015-12-01 05:16:01', 'pending');

-- --------------------------------------------------------

--
-- Table structure for table `device_token`
--
-- Creation: Nov 28, 2015 at 05:36 AM
--

CREATE TABLE IF NOT EXISTS `device_token` (
  `user_id` varchar(100) NOT NULL,
  `device_token` varchar(500) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- RELATIONS FOR TABLE `device_token`:
--   `user_id`
--       `oauth_users` -> `user_id`
--   `user_id`
--       `oauth_users` -> `user_id`
--

--
-- Dumping data for table `device_token`
--

INSERT INTO `device_token` (`user_id`, `device_token`, `created_at`, `updated_at`) VALUES
('william.hidayat@gmail.com563980a8a8f184.51608576', 'cf4-e2KQptY:APA91bG0Mu-iY6PSsWTz4KGyb7e8kwv0yhvWhFspVQXTVQ5HSg0RQSgfVHZXXTN_GtclgjF4ydIYGrszW_kZDXaGAtdMB8QkUDGQU509v2kd712_ZtjfTQpOnXYTbXqLuWhTUZuE9-EE', '2015-11-28 05:49:07', NULL),
('william.hidayat@gmail.com563980a8a8f184.51608576', 'enAbe2f0NaA:APA91bHwpDCPXc57qSP-CICXwnPDn3Q646qxNBCeK_6z4g6K9UbFnOEWjwskrw13eH60pPMxdOx_23bnek6Preify-RKO6NxSYjcoeUsRWaUW3-nGCkBZd64bCayhfQvzAmACBeFr0DH', '2015-12-01 12:00:20', '2015-12-01 11:52:00');

-- --------------------------------------------------------

--
-- Table structure for table `farmer`
--
-- Creation: Nov 27, 2015 at 09:20 AM
--

CREATE TABLE IF NOT EXISTS `farmer` (
  `farmer_username` varchar(20) NOT NULL,
  `farmer_name` varchar(50) NOT NULL,
  `email` varchar(40) NOT NULL,
  `address` varchar(50) NOT NULL,
  `telephone_number` varchar(15) NOT NULL,
  `phone_number` varchar(15) NOT NULL,
  `rating` double NOT NULL,
  `latitude` double NOT NULL,
  `longitude` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- RELATIONS FOR TABLE `farmer`:
--

--
-- Dumping data for table `farmer`
--

INSERT INTO `farmer` (`farmer_username`, `farmer_name`, `email`, `address`, `telephone_number`, `phone_number`, `rating`, `latitude`, `longitude`) VALUES
('stevejobs', 'Steve Jobs', 'stevyj@gmail.com', 'Palo Alto', '', '08129530405', 5, -6.364537099999999, 106.82866779999995),
('williamhenry', 'William', 'william.hidayat@gmail.com', 'Sunrise Garden', '', '081287771252', 4.5, -6.1720106999999995, 106.76143359999999);

-- --------------------------------------------------------

--
-- Table structure for table `farmer_images`
--
-- Creation: Nov 28, 2015 at 07:45 AM
--

CREATE TABLE IF NOT EXISTS `farmer_images` (
  `images_id` tinyint(4) NOT NULL,
  `farmer_username` varchar(20) NOT NULL,
  `farmer_images_url` varchar(100) NOT NULL,
  `farmer_images_filepath` varchar(30) NOT NULL,
  `farmer_images_filename` varchar(30) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- RELATIONS FOR TABLE `farmer_images`:
--   `farmer_username`
--       `farmer` -> `farmer_username`
--   `farmer_username`
--       `farmer` -> `farmer_username`
--

-- --------------------------------------------------------

--
-- Table structure for table `farmer_review`
--
-- Creation: Dec 02, 2015 at 03:00 PM
--

CREATE TABLE IF NOT EXISTS `farmer_review` (
  `email` varchar(255) NOT NULL,
  `farmer_username` varchar(20) NOT NULL,
  `rating` int(11) NOT NULL,
  `comment` varchar(500) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `review_id` tinyint(4) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

--
-- RELATIONS FOR TABLE `farmer_review`:
--   `farmer_username`
--       `farmer` -> `farmer_username`
--   `farmer_username`
--       `farmer` -> `farmer_username`
--

--
-- Dumping data for table `farmer_review`
--

INSERT INTO `farmer_review` (`email`, `farmer_username`, `rating`, `comment`, `created_at`, `updated_at`, `review_id`) VALUES
('xxxxx', 'stevejobs', 5, 'good lahh', '2015-12-02 08:17:24', '2015-12-02 08:17:24', 3),
('epoch', 'stevejobs', 5, 'a bit smelly but still fresh enough', '2015-12-02 08:17:24', '2015-12-02 08:17:24', 4),
('', 'williamhenry', 4, '', '2015-12-02 08:17:24', '2015-12-02 08:17:24', 7);

-- --------------------------------------------------------

--
-- Table structure for table `farmer_updates`
--
-- Creation: Nov 29, 2015 at 11:07 AM
--

CREATE TABLE IF NOT EXISTS `farmer_updates` (
  `updates_id` tinyint(4) NOT NULL,
  `farmer_username` varchar(20) NOT NULL,
  `updates` varchar(150) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- RELATIONS FOR TABLE `farmer_updates`:
--   `farmer_username`
--       `farmer` -> `farmer_username`
--   `farmer_username`
--       `farmer` -> `farmer_username`
--

--
-- Dumping data for table `farmer_updates`
--

INSERT INTO `farmer_updates` (`updates_id`, `farmer_username`, `updates`, `created_at`, `updated_at`) VALUES
(1, 'stevejobs', 'Starting to plan mandarin orange, 5 lots', '2015-11-29 11:07:48', '2015-11-29 11:07:00'),
(2, 'williamhenry', 'Harvesting another green apple (Not for sale), 5 Kg.', '2015-11-29 11:09:05', '2015-11-29 11:09:05'),
(3, 'stevejobs', 'Hope this rainy season bring us a lot of luck, because all the land have becoming very dry', '2015-11-29 11:20:58', '2015-11-29 11:20:58');

-- --------------------------------------------------------

--
-- Table structure for table `follow`
--
-- Creation: Nov 28, 2015 at 05:04 PM
--

CREATE TABLE IF NOT EXISTS `follow` (
  `user_id` varchar(100) NOT NULL,
  `farmer_username` varchar(20) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- RELATIONS FOR TABLE `follow`:
--

-- --------------------------------------------------------

--
-- Table structure for table `migrations`
--
-- Creation: Nov 02, 2015 at 09:47 AM
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
-- Creation: Oct 28, 2015 at 01:05 PM
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
-- Creation: Nov 02, 2015 at 01:22 PM
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
('0d62e3df2410a84dd9344102d99c4d0f1c0bee3b', 'testclient', 'william.hidayat@gmail.com563980a8a8f184.51608576', '2015-11-08 21:44:20', 'read'),
('149d00a75007a98c7cfa8dda676b90a6432f4a98', 'testclient', 'william.hidayat@gmail.com563980a8a8f184.51608576', '2015-11-18 06:42:56', 'read'),
('1678482994b8e67fda37df3b6a4e6164ac6fe02a', 'testclient', 'william.hidayat@gmail.com563980a8a8f184.51608576', '2015-11-05 01:59:22', NULL),
('1c0769b3cb6e1de7b223fea9202f775d99dfafba', 'testclient', 'william.hidayat@gmail.com563980a8a8f184.51608576', '2015-11-14 00:58:19', 'read'),
('211c2fcc2d381e9c8b714cab87899afda22977e6', 'testclient', 'william.hidayat@gmail.com563980a8a8f184.51608576', '2015-11-08 21:58:45', 'read'),
('24cd66a23075ec61f80e9594d973914c7e68b124', 'testclient', 'william.hidayat@gmail.com563980a8a8f184.51608576', '2015-11-25 08:37:46', 'read'),
('367e443ec6f041cf4c91a4061befb385fc3022e3', 'testclient', 'william.hidayat@gmail.com563980a8a8f184.51608576', '2015-11-23 22:20:29', 'read'),
('3a13ca95ce0275314957ce65d8156a85af998056', 'testclient', 'william.hidayat12@gmail.com565e5c9ce0fb56.38511658', '2015-12-02 03:49:47', 'read'),
('3df53d05e2a6cec012ee1856858069b70b063c39', 'testclient', 'william.hidayat@gmail.com563980a8a8f184.51608576', '2015-11-04 22:52:41', NULL),
('4348c72c0bb2525718ab8384410f120815ea78c4', 'testclient', 'william.hidayat@gmail.com563980a8a8f184.51608576', '2015-11-16 09:41:11', 'read'),
('4a6199a578e4dfbf6b54b2fb1b5288a74b3219bf', 'testclient', 'william.hidayat@gmail.com563980a8a8f184.51608576', '2015-11-18 12:10:24', 'read'),
('4aa2a30fbe7dca2f899e1f95730f721f8cf8ba80', 'testclient', 'william.hidayat@gmail.com563980a8a8f184.51608576', '2015-11-04 23:05:09', NULL),
('52b1a59bf8cd19ed5e62938c254dc7b9657a7f66', 'testclient', 'william.hidayat@gmail.com563980a8a8f184.51608576', '2015-11-16 19:16:23', 'read'),
('5353a1dbb6edf8fc9189d371d29d1bd813053a21', 'testclient', 'william.hidayat@gmail.com563980a8a8f184.51608576', '2015-11-29 07:22:37', 'read'),
('549ef58e8e091f00649753e0a5832407279dfa02', 'testclient', 'william.hidayat@gmail.com563980a8a8f184.51608576', '2015-11-05 19:05:43', 'read'),
('57f4962e04f221e229854cf15afa0f89fd08874e', 'testclient', 'william.hidayat@gmail.com563980a8a8f184.51608576', '2015-11-18 10:04:17', 'read'),
('62a95f482c261413a295be6ebe1e9a0b1992706d', 'testclient', 'william.hidayat@gmail.com563980a8a8f184.51608576', '2015-12-01 06:15:42', 'read'),
('632024b400c2d96aa7916ae4648ef9673d358ac8', 'testclient', 'william.hidayat@gmail.com563980a8a8f184.51608576', '2015-11-06 03:49:46', 'read'),
('69cf73af7e3874f2fe82bdf9bb4c09be22e8155e', 'testclient', 'william.hidayat@gmail.com563980a8a8f184.51608576', '2015-12-01 03:06:24', 'read'),
('6ff23e04f97a22186c2d9ffc4299db0714e0964a', 'testclient', 'william.hidayat@gmail.com563980a8a8f184.51608576', '2015-11-30 22:32:49', 'read'),
('76693a48a52dd6678f0ff0a79b810565d08b2b1d', 'testclient', 'william.hidayat@gmail.com563980a8a8f184.51608576', '2015-11-05 02:42:06', 'read'),
('7c8720dc25887721133b05d4b1e4b46dbc5857cd', 'testclient', 'william.hidayat@gmail.com563980a8a8f184.51608576', '2015-11-08 22:01:22', 'read'),
('7df7e590ab7711837972e016b6aa0656d7eac22d', 'testclient', 'william.hidayat@gmail.com563980a8a8f184.51608576', '2015-11-19 18:37:18', 'read'),
('8485797eef36c6f4a8dde2e60edd74a0342a8564', 'testclient', 'william.hidayat@gmail.com563980a8a8f184.51608576', '2015-11-09 03:54:54', 'read'),
('8c00673d6c03f15ff7f396f73b8dfe7c5c17e7e0', 'testclient', 'william.hidayat@gmail.com563980a8a8f184.51608576', '2015-11-29 05:17:59', 'read'),
('8f51124303e2fb3c8b14fba5a70ce45c46e5f4b8', 'testclient', 'agungwy@gmail.com565d359d7f7e83.29266753', '2015-11-30 23:53:50', 'read'),
('9071e392320fc179586afc90eccaf8fd93d7cbb8', 'testclient', 'william.hidayat@gmail.com563980a8a8f184.51608576', '2015-11-07 00:20:26', 'read'),
('94aa0fea7ee8e0a22a20e445fe3e604459bde74e', 'testclient', 'william.hidayat@gmail.com563980a8a8f184.51608576', '2015-11-26 08:16:11', 'read'),
('a2ae523c97430779e9828ad81aee0805db001a7b', 'testclient', 'william.hidayat@gmail.com563980a8a8f184.51608576', '2015-11-05 02:33:36', NULL),
('ba4ca04b1eb7c17337da94620ccb7b8c00d72d58', 'testclient', 'william.hidayat@gmail.com563980a8a8f184.51608576', '2015-11-18 18:52:22', 'read'),
('c4f10ce093e2d9e30ce54d75ce4d2c586c17f985', 'testclient', 'william.hidayat@gmail.com563980a8a8f184.51608576', '2015-11-08 23:10:31', 'read'),
('cad1c5b4c0720c5c19680cbf620ca032f2a5fc16', 'testclient', 'william.hidayat12@gmail.com565e5c9ce0fb56.38511658', '2015-12-02 03:53:25', 'read'),
('d3aee5b9dfc2f670a51802493666ec47c2e41443', 'testclient', 'william.hidayat12@gmail.com565e5c9ce0fb56.38511658', '2015-12-02 03:35:56', 'read'),
('d6dd088438612173db43dbb9b0485bad1ff14dfb', 'testclient', 'william.hidayat@gmail.com563980a8a8f184.51608576', '2015-11-28 11:01:24', 'read'),
('dbdd18ca050b185a26fbdfe76a77f73fe9ed3bc3', 'testclient', 'william.hidayat@gmail.com563980a8a8f184.51608576', '2015-11-29 09:08:06', 'read'),
('de10ea78294498cffa22dfe6817d62af7d84f4bd', 'testclient', 'william.hidayat@gmail.com563980a8a8f184.51608576', '2015-11-18 19:49:54', 'read'),
('dfd737b76448a48e10ce84657add48fe0e793359', 'testclient', 'william.hidayat12@gmail.com565e5c9ce0fb56.38511658', '2015-12-02 03:49:55', 'read'),
('e22bfe2710cc0e81fa01390bafcc470ed525d84f', 'testclient', 'william.hidayat@gmail.com563980a8a8f184.51608576', '2015-11-29 04:15:00', 'read'),
('e475b8ca9a97008803f8e92be6e2544be4c6e12e', 'testclient', 'william.hidayat12@gmail.com565e5c9ce0fb56.38511658', '2015-12-02 03:52:55', 'read'),
('e528740c268e5440ad724c6c3de24729f8282676', 'testclient', 'william.hidayat@gmail.com563980a8a8f184.51608576', '2015-12-01 04:59:41', 'read');

-- --------------------------------------------------------

--
-- Table structure for table `oauth_authorization_codes`
--
-- Creation: Nov 02, 2015 at 09:56 AM
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
-- Creation: Nov 02, 2015 at 09:56 AM
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
-- Creation: Nov 02, 2015 at 09:56 AM
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
-- Creation: Nov 02, 2015 at 09:56 AM
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
('0e4502ea6dfe2eaf81068d803e6cd20e109c4b43', 'testclient', 'william.hidayat@gmail.com563980a8a8f184.51608576', '2015-12-02 05:42:56', 'read'),
('0f4fb2ca383e52d262b9983f758363a6a3bc7f75', 'testclient', 'william.hidayat12@gmail.com565e5c9ce0fb56.38511658', '2015-12-16 02:52:55', 'read'),
('1367cdbdd759e75fbb66812d2a556a5b75dcb48c', 'testclient', 'william.hidayat@gmail.com563980a8a8f184.51608576', '2015-11-22 20:44:20', 'read'),
('3247752a71b160ba7ec173d1d923ee9ae52435bb', 'testclient', 'william.hidayat@gmail.com563980a8a8f184.51608576', '2015-12-02 17:52:22', 'read'),
('3e3d464d018245c854b761e18429a963fae0545c', 'testclient', 'william.hidayat@gmail.com563980a8a8f184.51608576', '2015-11-19 01:33:36', NULL),
('3eab24e3abd5119dc328d7ee1822c3bc4e7d010d', 'testclient', 'william.hidayat@gmail.com563980a8a8f184.51608576', '2015-12-02 11:10:24', 'read'),
('62f9f63329ff3be298b7f12bc04814bf5b3a129c', 'testclient', 'william.hidayat@gmail.com563980a8a8f184.51608576', '2015-11-30 08:41:11', 'read'),
('67fd0016cbf804e87fc410f2d6cdeab371255415', 'testclient', 'william.hidayat@gmail.com563980a8a8f184.51608576', '2015-12-07 21:20:29', 'read'),
('7280f9b4387595a316c66e73f8a23b4572406ac1', 'testclient', 'william.hidayat@gmail.com563980a8a8f184.51608576', '2015-12-15 05:15:42', 'read'),
('7ffbefabceb77a94c4b7d06def8fd0133bb757b7', 'testclient', 'william.hidayat12@gmail.com565e5c9ce0fb56.38511658', '2015-12-16 02:53:25', 'read'),
('85b8c9f7bfd628127099ea494f79d483bfb0a06b', 'testclient', 'william.hidayat@gmail.com563980a8a8f184.51608576', '2015-12-02 09:04:17', 'read'),
('8b634cca6f3385574d248fa6f60c9a60754635bb', 'testclient', 'william.hidayat@gmail.com563980a8a8f184.51608576', '2015-12-13 03:15:00', 'read'),
('8c21f82044247008efb1ad29be42c27c3847cab9', 'testclient', 'william.hidayat12@gmail.com565e5c9ce0fb56.38511658', '2015-12-16 02:35:56', 'read'),
('8d0868d8fb6bbf12ef481ef830b29e30bf80aa83', 'testclient', 'agungwy@gmail.com565d359d7f7e83.29266753', '2015-12-14 22:53:50', 'read'),
('94413a407a7916d50e6ec0b8f8c4c669c403b3ba', 'testclient', 'william.hidayat@gmail.com563980a8a8f184.51608576', '2015-12-10 07:16:11', 'read'),
('9ae53af32ffb939a0c95f7617dbb88d82d6011c4', 'testclient', 'william.hidayat@gmail.com563980a8a8f184.51608576', '2015-11-30 18:16:23', 'read'),
('a31d335f870962013a7411f44c7d8dd77b499f99', 'testclient', 'william.hidayat@gmail.com563980a8a8f184.51608576', '2015-12-13 06:22:37', 'read'),
('ab958b3590b2782102b99e89a79b74d492ec4d3a', 'testclient', 'william.hidayat@gmail.com563980a8a8f184.51608576', '2015-12-03 17:37:18', 'read'),
('b7dea203ec0e2bbc1aa2756912f3a1b7f158ea1a', 'testclient', 'william.hidayat12@gmail.com565e5c9ce0fb56.38511658', '2015-12-16 02:49:55', 'read'),
('ba691724c5ddeea377df6a553db15b1f6a6f5072', 'testclient', 'william.hidayat@gmail.com563980a8a8f184.51608576', '2015-11-19 00:59:22', NULL),
('c2b952279538fb0affa92a2e001bd938ff1eb44c', 'testclient', 'william.hidayat@gmail.com563980a8a8f184.51608576', '2015-12-15 02:06:24', 'read'),
('c4d0cae45bc44c04e41923d3c94ddc7dddf11e00', 'testclient', 'william.hidayat@gmail.com563980a8a8f184.51608576', '2015-12-15 03:59:41', 'read'),
('d7bffcc6465f9e934e30bf0daf302bac67c2bf1c', 'testclient', 'william.hidayat@gmail.com563980a8a8f184.51608576', '2015-12-02 18:49:54', 'read'),
('e31cc3dcedb4eb1f9eddd8a801d2b44ea1499bf2', 'testclient', 'william.hidayat@gmail.com563980a8a8f184.51608576', '2015-12-14 21:32:49', 'read'),
('ec87c039d69e2b784680f02327a912d2dd9856a3', 'testclient', 'william.hidayat12@gmail.com565e5c9ce0fb56.38511658', '2015-12-16 02:49:47', 'read'),
('f37fa5f151d5da42a8c7a4c67714c1a3e65861a5', 'testclient', 'william.hidayat@gmail.com563980a8a8f184.51608576', '2015-12-12 10:01:24', 'read'),
('f3a3124287a3197e0fcc782fb2efad285fb271a3', 'testclient', 'william.hidayat@gmail.com563980a8a8f184.51608576', '2015-12-13 04:17:59', 'read'),
('f7df3564ebd9aba59480ed4e588c84322a3930aa', 'testclient', 'william.hidayat@gmail.com563980a8a8f184.51608576', '2015-12-09 07:37:46', 'read'),
('f9fd52806e85ebe1e885787f64a24ea3043482d4', 'testclient', 'william.hidayat@gmail.com563980a8a8f184.51608576', '2015-11-27 23:58:19', 'read'),
('fc3f3fe8996748d9e830e11a997b806e2fbe9b9a', 'testclient', 'william.hidayat@gmail.com563980a8a8f184.51608576', '2015-12-13 08:08:07', 'read');

-- --------------------------------------------------------

--
-- Table structure for table `oauth_scopes`
--
-- Creation: Nov 02, 2015 at 09:56 AM
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
-- Creation: Nov 04, 2015 at 03:36 AM
--

CREATE TABLE IF NOT EXISTS `oauth_users` (
  `user_id` varchar(100) NOT NULL,
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

INSERT INTO `oauth_users` (`user_id`, `email`, `password`, `name`, `address`, `phone_number`, `created_at`, `updated_at`) VALUES
('96323827423772672', 'bshaffer@gmail.com', '35d3f3604337a503fd2efd0d76591ddbd2fb6579', 'Brent', 'San Fransisco', '081287771252', '2015-11-02 13:43:27', NULL),
('agungwy@gmail.com565d359d7f7e83.29266753', 'agungwy@gmail.com', '$2y$10$0le3bRdw85KcDf5fk.22uOxHLODTziAfVi8iN.Or6Yv/oudyH6TCW', 'agung wirayogi', 'jalan surya asih 1 blok m/6, jakarta barat', '081287771252', '2015-11-30 22:52:29', '2015-11-30 22:52:29'),
('will.smith@gmail.com5645b8d15caa04.17807441', 'will.smith@gmail.com', '$2y$10$X2TbnN2zGvhMPpUucO3FyOt3gx9rUPPlOFqv4XvIDwdJirCDZROB2', 'will smith', 'jskt', '0899871212', '2015-11-13 03:17:53', '2015-11-13 03:17:53'),
('william.hidayat12@gmail.com565e5c9ce0fb56.38511658', 'william.hidayat12@gmail.com', '$2y$10$/e2s.F/GWUelHiM4mRii1egOcX0R2bkTcqWUpaLAaitQjetuc6UrO', 'william henry', 'jalan surya sarana 1 blok j/12', '0999123500', '2015-12-01 19:51:09', '2015-12-02 03:11:56'),
('william.hidayat@gmail.com563980a8a8f184.51608576', 'william.hidayat@gmail.com', '$2y$10$jC2zBfpzoTuBPCDST0x8se.so6N6ZOJwIhN4dLLfmnc.G80O.n6NS', 'william henry', 'sunrise garden', '08129530405', '2015-11-03 20:51:04', '2015-11-03 20:51:04'),
('yohana.kanisia@gmail.com563c16edbc47c4.47496157', 'yohana.kanisia@gmail.com', '$2y$10$nc.VkyrJWTrUMlf4JM40ZOsVbzPRXO4bAxfjH9on.1bXneZOHYfau', 'Yohana Kanisia', 'cibubur', '08129530405', '2015-11-05 19:56:45', '2015-11-05 19:56:45');

-- --------------------------------------------------------

--
-- Table structure for table `password_resets`
--
-- Creation: Nov 02, 2015 at 09:56 AM
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
-- Creation: Nov 27, 2015 at 08:57 AM
--

CREATE TABLE IF NOT EXISTS `product` (
  `product_name` varchar(20) NOT NULL,
  `prod_price` int(6) NOT NULL,
  `stock_num` int(4) NOT NULL,
  `prod_desc` text NOT NULL,
  `id` tinyint(4) NOT NULL,
  `farmer_username` varchar(20) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `product_filename` varchar(30) NOT NULL,
  `product_filepath` varchar(30) NOT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `product_picture_url` varchar(100) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- RELATIONS FOR TABLE `product`:
--   `farmer_username`
--       `farmer` -> `farmer_username`
--   `farmer_username`
--       `farmer` -> `farmer_username`
--

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`product_name`, `prod_price`, `stock_num`, `prod_desc`, `id`, `farmer_username`, `created_at`, `product_filename`, `product_filepath`, `updated_at`, `product_picture_url`) VALUES
('Apple', 1500, 935, 'This is a red apple from a fresh productive land.', 1, 'stevejobs', '2015-12-01 11:40:40', 'apple.png', '\\products\\apple.png', '2015-12-01 04:40:40', '/api/products/1/picture'),
('Green Apple', 4000, 65, 'This is the green apple. Taken from the name, you will be wondering what is green apple. You want it, come and buy it  ', 2, 'williamhenry', '2015-12-01 09:06:36', 'green_apple.png', '\\products\\green_apple.png', '2015-12-01 02:06:36', '/api/products/2/picture'),
('Tomato', 4500, 0, 'It''s red tomatoes day. Get your cheap red baby tomatoes now', 3, 'stevejobs', '2015-12-02 07:38:05', 'tomato.png', '\\products\\tomato.png', '2015-12-02 00:38:05', '/api/products/3/picture');

-- --------------------------------------------------------

--
-- Table structure for table `product_picture`
--
-- Creation: Oct 29, 2015 at 05:44 AM
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
-- Table structure for table `shopping_cart`
--
-- Creation: Nov 04, 2015 at 02:44 PM
--

CREATE TABLE IF NOT EXISTS `shopping_cart` (
  `product_id` tinyint(4) NOT NULL,
  `product_quantity` int(200) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT NULL,
  `user_id` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- RELATIONS FOR TABLE `shopping_cart`:
--   `product_id`
--       `product` -> `id`
--   `user_id`
--       `oauth_users` -> `user_id`
--   `product_id`
--       `product` -> `id`
--   `user_id`
--       `oauth_users` -> `user_id`
--

-- --------------------------------------------------------

--
-- Table structure for table `tags`
--
-- Creation: Oct 28, 2015 at 01:08 PM
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
-- Creation: Nov 01, 2015 at 11:43 AM
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
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- RELATIONS FOR TABLE `users`:
--

-- --------------------------------------------------------

--
-- Table structure for table `wish_list`
--
-- Creation: Nov 24, 2015 at 04:26 AM
--

CREATE TABLE IF NOT EXISTS `wish_list` (
  `user_id` varchar(100) NOT NULL,
  `product_id` tinyint(4) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- RELATIONS FOR TABLE `wish_list`:
--   `product_id`
--       `product` -> `id`
--   `user_id`
--       `oauth_users` -> `user_id`
--   `product_id`
--       `product` -> `id`
--   `user_id`
--       `oauth_users` -> `user_id`
--

--
-- Dumping data for table `wish_list`
--

INSERT INTO `wish_list` (`user_id`, `product_id`, `created_at`, `updated_at`) VALUES
('william.hidayat@gmail.com563980a8a8f184.51608576', 3, '2015-11-24 04:27:42', '2015-11-24 03:22:51');

-- --------------------------------------------------------

--
-- Table structure for table `wish_list_general`
--
-- Creation: Nov 24, 2015 at 04:33 AM
--

CREATE TABLE IF NOT EXISTS `wish_list_general` (
  `wish_list_id` tinyint(4) NOT NULL,
  `user_id` varchar(100) NOT NULL,
  `product_name` varchar(50) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00'
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- RELATIONS FOR TABLE `wish_list_general`:
--   `user_id`
--       `oauth_users` -> `user_id`
--   `user_id`
--       `oauth_users` -> `user_id`
--

--
-- Dumping data for table `wish_list_general`
--

INSERT INTO `wish_list_general` (`wish_list_id`, `user_id`, `product_name`, `created_at`, `updated_at`) VALUES
(1, 'william.hidayat@gmail.com563980a8a8f184.51608576', 'mango', '2015-11-24 04:34:22', '2015-11-24 04:10:58');

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
-- Indexes for table `checkingout`
--
ALTER TABLE `checkingout`
  ADD PRIMARY KEY (`checkingout_id`,`user_id`,`product_id`),
  ADD KEY `fk_checkingout_user` (`user_id`),
  ADD KEY `fk_checkingout_product` (`product_id`);

--
-- Indexes for table `device_token`
--
ALTER TABLE `device_token`
  ADD PRIMARY KEY (`device_token`,`user_id`),
  ADD KEY `fk_device_token_users` (`user_id`);

--
-- Indexes for table `farmer`
--
ALTER TABLE `farmer`
  ADD PRIMARY KEY (`farmer_username`);

--
-- Indexes for table `farmer_images`
--
ALTER TABLE `farmer_images`
  ADD PRIMARY KEY (`images_id`,`farmer_username`),
  ADD KEY `fk_farmer_images` (`farmer_username`);

--
-- Indexes for table `farmer_review`
--
ALTER TABLE `farmer_review`
  ADD PRIMARY KEY (`review_id`,`farmer_username`),
  ADD KEY `fk_review_farmer` (`farmer_username`);

--
-- Indexes for table `farmer_updates`
--
ALTER TABLE `farmer_updates`
  ADD PRIMARY KEY (`updates_id`,`farmer_username`),
  ADD KEY `fk_farmer_updates` (`farmer_username`);

--
-- Indexes for table `follow`
--
ALTER TABLE `follow`
  ADD PRIMARY KEY (`user_id`,`farmer_username`);

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
  ADD PRIMARY KEY (`user_id`),
  ADD UNIQUE KEY `email` (`email`),
  ADD UNIQUE KEY `user_id` (`user_id`);

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
  ADD PRIMARY KEY (`id`,`farmer_username`),
  ADD UNIQUE KEY `id` (`id`),
  ADD KEY `farmer_id` (`farmer_username`);

--
-- Indexes for table `product_picture`
--
ALTER TABLE `product_picture`
  ADD PRIMARY KEY (`product_id`);

--
-- Indexes for table `shopping_cart`
--
ALTER TABLE `shopping_cart`
  ADD PRIMARY KEY (`user_id`,`product_id`),
  ADD KEY `fk_shopping_cart_product` (`product_id`);

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
-- Indexes for table `wish_list`
--
ALTER TABLE `wish_list`
  ADD PRIMARY KEY (`user_id`,`product_id`),
  ADD KEY `fk_wish_list_product` (`product_id`);

--
-- Indexes for table `wish_list_general`
--
ALTER TABLE `wish_list_general`
  ADD PRIMARY KEY (`wish_list_id`,`user_id`),
  ADD KEY `fk_wish_list_general_users` (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `article`
--
ALTER TABLE `article`
  MODIFY `article_id` tinyint(4) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `checkingout`
--
ALTER TABLE `checkingout`
  MODIFY `checkingout_id` tinyint(4) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=18;
--
-- AUTO_INCREMENT for table `farmer_images`
--
ALTER TABLE `farmer_images`
  MODIFY `images_id` tinyint(4) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `farmer_review`
--
ALTER TABLE `farmer_review`
  MODIFY `review_id` tinyint(4) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=8;
--
-- AUTO_INCREMENT for table `farmer_updates`
--
ALTER TABLE `farmer_updates`
  MODIFY `updates_id` tinyint(4) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=4;
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
-- AUTO_INCREMENT for table `wish_list_general`
--
ALTER TABLE `wish_list_general`
  MODIFY `wish_list_id` tinyint(4) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;
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
-- Constraints for table `checkingout`
--
ALTER TABLE `checkingout`
  ADD CONSTRAINT `fk_checkingout_product` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_checkingout_user` FOREIGN KEY (`user_id`) REFERENCES `oauth_users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `device_token`
--
ALTER TABLE `device_token`
  ADD CONSTRAINT `fk_device_token_users` FOREIGN KEY (`user_id`) REFERENCES `oauth_users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `farmer_images`
--
ALTER TABLE `farmer_images`
  ADD CONSTRAINT `fk_farmer_images` FOREIGN KEY (`farmer_username`) REFERENCES `farmer` (`farmer_username`);

--
-- Constraints for table `farmer_review`
--
ALTER TABLE `farmer_review`
  ADD CONSTRAINT `fk_review_farmer` FOREIGN KEY (`farmer_username`) REFERENCES `farmer` (`farmer_username`);

--
-- Constraints for table `farmer_updates`
--
ALTER TABLE `farmer_updates`
  ADD CONSTRAINT `fk_farmer_updates` FOREIGN KEY (`farmer_username`) REFERENCES `farmer` (`farmer_username`);

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

--
-- Constraints for table `shopping_cart`
--
ALTER TABLE `shopping_cart`
  ADD CONSTRAINT `fk_shopping_cart_product` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_shopping_cart_users` FOREIGN KEY (`user_id`) REFERENCES `oauth_users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `wish_list`
--
ALTER TABLE `wish_list`
  ADD CONSTRAINT `fk_wish_list_product` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_wish_list_users` FOREIGN KEY (`user_id`) REFERENCES `oauth_users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `wish_list_general`
--
ALTER TABLE `wish_list_general`
  ADD CONSTRAINT `fk_wish_list_general_users` FOREIGN KEY (`user_id`) REFERENCES `oauth_users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
