<?php

require_once './utils/db_apis.php';
$db = new DBApis();

// json response array
$response = array();

if (isset($_POST['name']) && isset($_POST['email']) && isset($_POST['password'])||isset($_POST['contact'])) {

    // receiving the post params
    $name = $_POST['name'];
    $email = $_POST['email'];
    $password = $_POST['password'];
    $contact = $_POST['contact'];

    // check if user is already existed with the same email
    if ($db->isUserExists($email)) {
        // user already existed
        $response["success"] = FALSE;
        $response["msg"] = "User already existed with " . $email;
       // $str = json_encode($response);
            echo json_encode($response,JSON_FORCE_OBJECT);
    } else {
        // create a new user
        $user = $db->createUser($name, $email, $password, $contact);
        if ($user) {
            // user creted successfully
            $response["success"] = TRUE;
            $response["msg"] = "Successfully Registered ".$email;
            echo json_encode($response,JSON_FORCE_OBJECT);
        } else {
            // user failed to crete
            $response["success"] = FALSE;
            $response["msg"] = "Unknown error occurred in registration!";
           // $str = json_encode($response);
           echo  json_encode($response,JSON_FORCE_OBJECT);
        }
    }
} else {
    $response["success"] = FALSE;
    $response["msg"] = "Required parameters (name, email or password) is missing!";
//    $str = json_encode($response);
//            echo strip_tags($str);
    echo json_encode($response,JSON_FORCE_OBJECT);
}


