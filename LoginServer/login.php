<?php
require_once 'utils/db_apis.php';
$db = new DBApis();

// json response array
$response = array();
error_log(var_export($_POST,true));
//exit;

if (isset($_POST['email']) && isset($_POST['password'])) {

    // receiving the post params
    /* @var $_POST type */
    $email = $_POST['email'];
    $password = $_POST['password'];

    // get the user by email and password
    $user = $db->getUserByEmailAndPassword($email, $password);

    if ($user) {
        // use is found
        $response["success"] = TRUE;
        $response["msg"] = "Login success";
        $response["result"]["name"] = $user[1];
        $response["result"]["email"] = $user[2];
        $response["result"]["contact"] = $user[5];
        echo json_encode($response);
    } else {
        // user is not found with the credentials
        $response["success"] = FALSE;
        $response["msg"] = "Login credentials are wrong. Please try again!";
        echo json_encode($response);
    }
} else {
    // required post params is missing
    $response["sucess"] = FALSE;
    $response["msg"] = "Required parameters email or password is missing!";
    echo json_encode($response);
}
