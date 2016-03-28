<?php
require_once 'utils/db_apis.php';
$db = new DBApis();
//$user = $db->getUserByEmailAndPassword("", "");
$sql = "select * FROM users where email='123@qq.com'";
$result= mysqli_query($db->conn,$sql);
//echo mysqli_num_rows($result);
$row=mysqli_fetch_row($result);
echo $row[1];
