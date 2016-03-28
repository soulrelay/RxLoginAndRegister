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
