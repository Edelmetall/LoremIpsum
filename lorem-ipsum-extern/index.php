<html>
<head>
	<title>LoremIpsum</title>
	<link rel="stylesheet" href="style.css">
	<link rel="icon" type="image/x-icon" href="logo.png">
</head>
<body>
<div class="bar"></div>
<div class="body">

<?php
# Get Variables from URL

$name = $_GET['name'];
$prename = $_GET['prename'];
$mail = $_GET['mail'];

$headers  = 'MIME-Version: 1.0' . "\r\n";
$headers .= 'Content-type: text/html; charset=iso-8859-1' . "\r\n";
$headers .= "From: no.reply.loremipsum@gmx.ch\r\n"."X-Mailer: php";

if(isset($_GET['pass'])) {
	$pass = $_GET['pass'];
	
	# Declare variables for mailing if password lost

	$subject = 'LoremIpsum Password Reset';
       	
	$message = '<html><body>';
	$message .= '<h1 style="color:#f40;"> Hi ' .$prename. '</h1>';
	$message .= '<p> Dein Passwort wurde zurückgesetzt. Das neue Passwort lautet: ' .$pass. '</p>';
	$message .= '<p> Bitte ändere dieses Passwort sobald wie möglich! <br /> <br /> </p>';
	$message .= '<p> Dein LoremIpsum-Team </p>';
	$message .= '</body></html>';	

	# Send mail
	
	mail($mail,$subject,$message,$headers);

	echo '<meta http-equiv="refresh" content="0;url=http://localhost:4200/login">';
} else {	
	# Declare variables for mailing
	
	$subject = 'Erfolgreiche Registrierung bei LoremIpsum';
	$message = 'Hallo ' .$prename. ' ' .$name. ' deine Anmeldung war erfolgreich <br />';
	$message .= '<p> Dein LoremIpsum-Team </p>';

	# Send mail
	
	mail($mail,$subject,$message,$headers);
	
	# Website for the user
	
	echo '<h1> Registration </h1> <br />';	
	echo 'Hallo ' .$prename. '<br />';
	echo '<br /> Du hast dich erfolgreich registriert. In Kürze wirst du eine Bestätigungsmail erhalten!  <br />';
	echo '<br /> <a href="http://localhost:4200/login"> <button type="submit" value="back"> Zurück zum Login </button> </a>';
}
?>

</div>
</body>
</html>
