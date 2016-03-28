# RxLoginAndRegister

主要包含客户端和服务端两个部分：

## 客户端

使用RxJava和Retrofit搭配完成基本的登录和注册功能
> * RxJava：一个在 Java VM 上使用可观测的序列来组成异步的、基于事件的程序的库（说到根上，它就是一个实现异步操作的库）
> * Retrofit: 类型安全的网络库，封装了okhttp


### 登录api

一个带有两个参数的post请求
```java
public interface LoginApi {
    @FormUrlEncoded
    @POST("/LoginServer/login.php")
    Observable<ResultReturn> login(@Field("email") String email, @Field("password") String password);
}
```

注意：具体使用方法请参考详细代码 ，其它相关概念不再赘述。


![image](https://raw.githubusercontent.com/feifei003603/RxLoginAndRegister/master/app/src/main/res/raw/login.jpg)

![image](https://github.com/feifei003603/RxLoginAndRegister/blob/master/app/src/main/res/raw/register.jpg?raw=true)

## 服务端

初衷：就是想自己搭建一个最最基本的服务器！

功能：完成了一个最基本的登录和注册流程

注意：代码结构相对简单！仅供需要的朋友进行参考

部分代码参考github上的一个demo，一时间找不到那个demo的链接了，同时根据本案例自身需求做了一些调整

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
