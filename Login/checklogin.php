<?php
    include "validation.php";
	//check the username and password
    $servername = "localhost";
    $username='root';
    $password='';
	
	if($validName == 'success' and $validPass == 'success'){
        try {
        $conn = new PDO("mysql:host=$servername;dbname=insantani", $username, $password);
        // set the PDO error mode to exception
            $conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
        echo "Connected successfully"; 
        }
        catch(PDOException $e)
        {
        die('Could not connect to the database.');
        }

		$sp="set search_path to 'register'";
		$sql="SELECT * FROM users WHERE user_id ='".$uname."' AND password = '".$password."'";
		
		$result2 = pg_query($con,$sp);
		$result = pg_query($con,$sql);
		
		if(pg_num_rows($result) == 1){
			//email session list
			session_start();
			$_SESSION['user_id'] = $uname;
			//redirect to home page of application
			header("location:welcome.php");
		} else {
			session_start();
			$_SESSION['check'] = false;
			$_SESSION['unameErr'] = $unameErr;
			$_SESSION['passErr'] = $passErr;
			header("location:login.php");
		
		}
		pg_close($con); 
	}
	else{
		session_start();
		$_SESSION['check'] = true;
		$_SESSION['unameErr'] = $unameErr;
		$_SESSION['passErr'] = $passErr;
		header("location:login.php");
	}
?>
