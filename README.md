# EWeb

## 使用

**不要修改jar包的名字**

环境：Java环境

启动命令：

```shell
java -jar EWeb.jar
```

看到这个界面，说明启动成功

![image-20230109181901020](https://typora-1310202894.cos.ap-beijing.myqcloud.com/images/image-20230109181901020.png)

## 功能

- 文本请求 GET/POST
- 图片请求 GET/POST
- 文件下载 GET
- 文件上传 POST

## 基本路径

### 浏览器

本地浏览器访问地址为

```shell
http://localhost:8211
```

或者

```shell
http://127.0.0.1:8211
```

### 移动设备（同一局域网下）

通过以下shell命令查看PC的IP地址

Windows:

```
ipconfig
```

Mac:

```
ifconfig | grep "inet"
```

移动设备与PC连接同一个局域网的时候，移动设备访问地址为

```shell
ip:8211
```

### 模拟器

模拟器访问地址为

```shell
10.0.2.2:8211
```

## API

### 请求文本

#### 不带参数

方法：GET

接口：

```shell
/get/text
```

参数：无

返回：

```json
{
    "success": true,
    "code": 200,
    "message": "获取成功",
    "data": [
        {
        "userName": "Ethan",
        "cover": "/imgs/1.png",
        "id": "364872769081344",
        "title": "傅政华：我对孙力军唯命是从",
        "viewCount": 3400,
        "commentCount": 65,
        "publishTime": "2023-01-08T04:34:57.447+0000"
      },
      {
        "userName": "Ethan",
        "cover": "/imgs/8.png",
        "id": "364872769081345",
        "title": "俄议员建议从中国回购辽宁舰",
        "viewCount": 4552,
        "commentCount": 37,
        "publishTime": "2023-01-08T04:34:57.447+0000"
      },
      ...
    ]
}
```

#### 带参数

方法：GET

接口：

```shell
/get/param
```

参数：

- language 语言 ch 中文 en 英文
- order 0 顺序 1 逆序

返回：

```json
{
  "success": true,
  "code": 200,
  "msg": "GET带参数请求成功",
  "data": [
    {
      "language": "en",
      "order": "顺序"
    },
    [
      {
        "userName": "Ethan",
        "cover": "/com/ethan/eweb/imgs/9.png",
        "id": "366596086956032",
        "title": "Chinese premier meets with Turkmen president",
        "viewCount": 3532,
        "commentCount": 13,
        "publishTime": "2023-01-08T04:41:48.319+0000"
      },
      {
        "userName": "Ethan",
        "cover": "/com/ethan/eweb/imgs/8.png",
        "id": "366596091150336",
        "title": "PBA Commissioner's Cup Finals: Barangay Ginebra San Miguel vs. Bay Area Dragons",
        "viewCount": 2418,
        "commentCount": 72,
        "publishTime": "2023-01-08T04:41:48.319+0000"
      },
      ...
    ]
  ]
}
```

异常：

```json
{
  "success":false,
  "code":400,
  "msg":"order参数非法",
  "data":null
}
```

### 请求图片

方法：GET

接口：

```shell
/imgs/xx.png
```

xx取值范围[1,12]

### 提交文本

#### Query参数

方法：POST

接口：

```shell
/post/query
```

参数：

content 字符内容

```shell
 localhost:8211/post/query?content=EWeb很好用
```

返回：

```json
{
	"success": true,
	"code": 200,
	"msg": "提交文本成功：EWeb很好用",
	"data": null
}
```

#### Body参数

方法：POST

接口：

```shell
/post/body
```

参数：

articleId 文章id

commentContent 评论内容

<img src="https://typora-1310202894.cos.ap-beijing.myqcloud.com/images/image-20230108174454380.png" alt="image-20230108174454380" style="zoom:50%;" /> 

返回：

```json
{
	"success": true,
	"code": 10000,
	"msg": "提交文本成功：EWeb很好用",
	"data": null
}
```

### 文件上传

#### 单文件上传

方法：POST

接口：

```shell
/file/upload/single
```

参数（Body 内 form-data）：

file 要上传的文件

<img src="https://typora-1310202894.cos.ap-beijing.myqcloud.com/images/image-20230109113842393.png" alt="image-20230109113842393" style="zoom:50%;" /> 

返回：

```json
{
	"success": true,
	"code": 10000,
	"msg": "上传成功，路径为：upload/微信图片_20220517085109.jpg",
	"data": null
}
```

#### 多文件上传

方法：POST

接口：

```shell
/file/upload/multiple
```

参数（Body 内 form-data）：

files 要上传的文件们

<img src="https://typora-1310202894.cos.ap-beijing.myqcloud.com/images/image-20230109165932027.png" alt="image-20230109165932027" style="zoom:50%;" /> 

返回：

```json
{
	"success": true,
	"code": 10004,
	"msg": "上传成功2个文件，路径为：upload/微信图片_20220517085109.jpg upload/...",
	"data": null
}
```

#### 上传文件携带参数

方法：POST

接口：

```
/file/upload/param
```

参数（Body 内 form-data）：

file 要上传的文件

description 描述

<img src="https://typora-1310202894.cos.ap-beijing.myqcloud.com/images/image-20230109171333524.png" alt="image-20230109171333524" style="zoom:50%;" /> 

返回：

```json
{
	"success": true,
	"code": 10004,
	"msg": "成功上传，路径为：upload/微信图片_20220517085109.jpg",
	"data": "文件描述为：EWeb真好用"
}
```

### 文件下载

方法：GET

接口：

```shell
/file/download/{fileName}
```

fileName取值范围[1,12]

返回：

文件流

### 登录

方法：POST

接口：

```shell
/login
```

参数：

userName 用户名

password 密码

<img src="https://typora-1310202894.cos.ap-beijing.myqcloud.com/images/image-20230108174257061.png" alt="image-20230108174257061" style="zoom:50%;"  /> 

返回：

data 用户Token

```json
{
	"success": true,
	"code": 10000,
	"msg": "登录成功：Ethan",
	"data": "94a932b1-1b7d-4959-8767-2343886b0ee5"
}
```

## 状态码

如果遇到响应和预期不一致的问题，可以通过这个状态码表进行对照

| 状态码 | 含义           |
| ------ | -------------- |
| 10000  | 操作成功       |
| 10003  | 登录成功       |
| 10004  | 上传成功       |
| 10005  | 下载成功       |
| 11111  | 操作失败       |
| 11112  | 账号或密码错误 |
| 11122  | 上传失败       |
| 11124  | 下载失败       |



