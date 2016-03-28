# RxLoginAndRegister

主要包含客户端和服务端两个部分：

## 客户端

RxJava 和 Retrofit 结合使用完成基本的登录和注册功能

![image](https://raw.githubusercontent.com/feifei003603/RxLoginAndRegister/master/app/src/main/res/raw/login.jpg)

![image](https://github.com/feifei003603/RxLoginAndRegister/blob/master/app/src/main/res/raw/register.jpg?raw=true)

## 服务端

初衷：就是想自己搭建一个最最基本的服务器！

功能：完成了一个最基本的登录和注册流程

注意：代码结构相对简单！仅供需要的朋友进行参考

相关介绍：

> * 集成安装环境：WampServer 即在window下的apache、php和mysql的服务器软件
> * 集成开发环境：Zend Studio12.5.1
> * 数据传输类型：JSON
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
参考db_apis.php

### 登录
参考login.php

### 注册
参考register.php
