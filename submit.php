<?php
$servername = "id17513492_easyshare";
$username = "id17513492_vaibhav";
$password = "M[~vO4bu&=DNU#>]";

$ttext=$_POST['ttext'];
$uniqueId=$_POST['uniqueId'];
echo $uniqueId;
echo $ttext;
// Create connection
$conn = mysqli_connect("localhost",$username,$password,$servername);
//$conn = new mysqli($servername, $username, $password);

// Check connection
if ($conn->connect_error) {
  die("Connection failed: " . $conn->connect_error);
  echo $conn->connect_error;
}
else
{
$sql = "UPDATE links SET isFilled='1',text='".$ttext."' WHERE UniqueID='".$uniqueId."'";
if ($conn->query($sql) === TRUE) {
  echo "Record updated successfully";
} else {
  echo "Error updating record: " . $conn->error;
}

$conn->close();
}



?>