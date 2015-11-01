<?php
	
	// define variables and set to empty values
	session_start();
	$unameErr = $passErr = "";
	$validName = $validPass = "";
		
	$uname = $password  = "";
		
	if ($_SERVER["REQUEST_METHOD"] == "POST"){
	
		if (empty($_POST["user_id"])) {
			$unameErr = "Username is required";
		}
		else {
			$uname = test_input($_POST["user_id"]);
			$_SESSION['user_id'] = $uname;
			unset($_SESSION['unameErr']);
			$validName="success";
		}

			
		if (empty($_POST["password"])){
			$passErr = "Password is required";
		}
		else{
			$password = test_input($_POST["password"]);
			$password = md5($password);
			$validPass = "success";
		}
		
	}
		
	function test_input($data)
	{
		 $data = trim($data);
		 $data = stripslashes($data);
		 $data = htmlspecialchars($data);
		 return $data;
	}
?>