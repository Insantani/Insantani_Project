<?php
session_start();
$unameErr = $passErr = "";
//check session if email exists
//if not redirect to login page (index.php)
if(isset($_SESSION['check']) and $_SESSION['check'] == false){
    echo "Wrong Username or Password";
	session_destroy();
}
elseif(isset($_SESSION['check']) and $_SESSION['check'] == true and isset($_SESSION['unameErr']) or isset($_SESSION['passErr'])){
	$unameErr = $_SESSION['unameErr'];
	$passErr = $_SESSION['passErr'];
	session_destroy();
} elseif (isset($_SESSION['user_id']))
{
	header("location:welcome.php");
}
?>


<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="style.css">
		<title>Sign in</title>
		<style>
			.error{
				color: red;
			}
		</style>
	</head>
	<body>
		<section class="loginform">
			
			<form class="form" method="post" action="checklogin.php" novalidate>
				<h1 class="top">Login</h1>
				<?php if (!(empty($unameErr) || empty($passErr))) { ?>
					<?php echo '<div class="error alert">' ?>
						<?php echo "<p>" .$unameErr."</p>";?>
						<?php echo "<p>" .$passErr."</p>";?>
					<?php echo '</div>' ?>
				<?php }?>
				<table class="middle">
					<tr>
						<td><label for="uname">Username</label></td>
						<td><input type="text" name="uname" placeholder="username"
						value="<?php if (!empty($_SESSION["user_id"])) echo $_SESSION['user_id'] ?>" >
						</td>
					</tr>
					<tr>
						<td><label for="password">Password</label></td>
						<td><input type="password" name="password" placeholder="password">
						</td>
					</tr>
					<tr>
						<td class="bottom" colspan="2"><input type="submit" value="Login"></td>
					</tr>
					<tr>
						<td class="help" colspan="2">Aren't registered yet? Register for an account first!</td>
				</table>
			</form>
		</section>
	</body>
</html>