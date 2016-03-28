# RxLoginAndRegister

主要包含客户端和服务端两个部分：

## 客户端

RxJava 和 Retrofit 结合使用完成基本的登录和注册功能

![image](https://raw.githubusercontent.com/feifei003603/RxLoginAndRegister/master/app/src/main/res/raw/login.jpg)

![image](https://github.com/feifei003603/RxLoginAndRegister/blob/master/app/src/main/res/raw/register.jpg?raw=true)

## 服务端

集成安装环境：WampServer 即在window下的apache、php和mysql的服务器软件
集成开发环境：Zend Studio12.5.1
数据传输类型：
```json
     {
         "success": true,
         "msg": "Login success",
         "result": {
             "name": "ss",
             "email": "123@qq.com",
             "contact": "123"
         }
     }
```
     


### 修改数据库配置文件
```php
<?php
/*
 * All database connection variables
 */
 
define("DB_HOST", "localhost");
define("DB_USER", "root");
define("DB_PASSWORD", "");
define("DB_NAME", "simplelogin");
```

### 数据库的连接 数据表的创建 相关数据操作api
```php
<?php
include_once 'config.php';

class DBApis
{

    public $conn;

    // constructor
    function __construct()
    {
        // require_once 'db_connect.php';
        // connecting to database
        $this->conn = mysqli_connect(DB_HOST, DB_USER, DB_PASSWORD, DB_NAME);

        if (mysqli_connect_errno($this->conn)) {
            return "Failed to connect to MySQL: " . mysqli_connect_error();
        }
    }

    // destructor
    function __destruct()
    {
        mysqli_close($this->conn);
    }

    /**
     * Creating new user
     * returns user details
     */
    public function createUser($name, $email, $password, $contact)
    {
        $hash = $this->hashSSHA($password);
        $encrypted_password = $hash["encrypted"]; // encrypted password
        $salt = $hash["salt"]; // salt
        $date = date("Y/m/d");
        $sql = "INSERT INTO users(name, email, encrypted_password, salt,contact, created_at) VALUES( '$name','$email',' $encrypted_password', ' $salt','$contact', '$date')";
        $result = mysqli_query($this->conn, $sql);

        if ($result) {

            return true;
        } else {
            return false;
        }
    }

    /**
     * Get user by email and password
     */
    public function getUserByEmailAndPassword($email, $password)
    {
        $sql = "select * FROM users where email='$email'";
        $result = mysqli_query($this->conn, $sql);
        // $row = mysqli_fetch_array($result);
        // $data = $row[0];
        // return $data; /
        if (mysqli_num_rows($result) > 0) {
            $row=mysqli_fetch_row($result);
            return $row;
        } else {
            return false;
        }
    }

    /**
     * Check user is exists
     */
    public function isUserExists($email)
    {
        $user_result = mysqli_query($this->conn, "select * from users where email='$email'");
        $rows = mysqli_num_rows($user_result);
        if ($rows > 0) {
            return true;
        }
        return false;
    }

    /**
     * Encrypting password
     *
     * @param
     *            password
     *            returns salt and encrypted password
     */
    public function hashSSHA($password)
    {
        $salt = sha1(rand());
        $salt = substr($salt, 0, 10);
        $encrypted = base64_encode(sha1($password . $salt, true) . $salt);
        $hash = array(
            "salt" => $salt,
            "encrypted" => $encrypted
        );
        return $hash;
    }

    /**
     * Decrypting password
     *
     * @param
     *            salt, password
     *            returns hash string
     */
    public function checkhashSSHA($salt, $password)
    {
        $hash = base64_encode(sha1($password . $salt, true) . $salt);

        return $hash;
    }
}

?>
```

### 登录
```php
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

```


### 注册

```php
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

```