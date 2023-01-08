# EWeb

## 使用

**不要修改jar包的名字**

环境：Java环境

启动命令：

```shell
java -jar EWeb.jar
```

看到这个界面，说明启动成功

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

### 获取文本数据

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
    "code": 10000,
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

### 获取图片内容

直接使用上面的图片路径访问即可

方法：GET

```shell
/imgs/xx.png
```

xx范围：0~16

### get带参数请求

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
  "code": 10000,
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
  "code":11111,
  "msg":"order参数非法",
  "data":null
}
```

### 单文件上传

方法：post

接口：

```shell
/file/upload
```

参数：

文件 file

返回：

```json
{
    "success": true,
    "code": 10000,
    "message": "上传成功.文件路径为：E:\\codes\\Idear\\SobNetworkCourseServer\\target\\classes\\sobUpload\\0.png",
    "data": null
}
```

### 多文件上传

方法：post

接口：

```shell
/files/upload
```

参数：

文件列表 files

返回：

```json
{
    "success": true,
    "code": 10000,
    "message": "上传成功3个文件，路径：E:/codes/Idear/SobNetworkCourseServer/target/classes/sobUpload",
    "data": null
}
```



### 上传文件携带参数

方法：post

接口：

```
/file/params/upload
```

参数file，文件类型

description 描述 字符串类型

isFree是否免费“true/false” 字符串类型

返回：

```json
{success=true, code=10000, message='上传成功.文件路径为：E:\codes\Idear\SobNetworkCourseServer\target\classes\sobUpload\rBsADV3nxtKACoSfAAAPx8jyjF8169.png', data=your descriptions is --> "我是文件的描述内容..." isFree == > "false"}
```

### 提交评论

方法：post

接口

```shell
/post/comment
```

body json 字段

articleId:文章id

commentContent:评论内容

eg：

```json
{
	"articleId":"234123",
	"commentContent":"这是评论内容"
}
```

返回：

```json
{
    "success": true,
    "code": 10000,
    "message": "评论成功:这是评论内容",
    "data": null
}
```





### 提交字符串

方法：post

接口

```shell
/post/string
```

参数：

string 字符内容

```shell
 localhost:9102/post/string?string=内容测试内容
```

返回：

```json
{
    "success": true,
    "code": 10000,
    "message": "提交字符串成功:内容测试内容",
    "data": null
}
```



### 文件下载

方法：get

接口：

```shell
/download/{fileName}
```

fileName的取值为：[0,16]

返回：

文件流



### 登录接口

方法：post

接口



```shell
/login
```

参数：

userName 用户名

参数

password 密码

返回结果：

```json
{
    "success": true,
    "code": 10000,
    "message": "这是你提交上来的数据：拉大锯 - 12938471902387",
    "data": "2a142e7c-754e-413d-a662-77c27abc097c"
}
```



