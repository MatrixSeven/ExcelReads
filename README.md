# ExcelReads(简单Excel通用读取器)
## ExcelReads是什么?
* 这是一个通用的简单的Excel读取器
* 支持自定义JavaBean实体读取和HashMap自动读取
* 支持自定义扩展
* 支持自定义Sheet范围，数据开始行数
* 支持正则过滤数据格式
* JavaBean实体支持使用注解添加正则规则校验，HashMap支持数组规则校验
* 依赖POI，使用Maven构建

### 更新2016/11/8
* 支持数据过滤和处理是转换，基于事件模式
* 更新为Build模式创建实例
* 增加数据过滤、数据转换和数据排序回调接口
* 采用链式set方式进行

## 其他
* 自定义读取支持出简单的规范化数据格式，即典型的表头格式
* 可以继承 WapperMap 和 WapperObj进行扩展
* 直接使用ExcelFactory.getBeans进行获取，WapperObj则自己添加泛型
* 注解Value对应列标题，Required对应正则，可自己写正则表达式或者直接使用RegHelper
* 实体bean数据要比hashMap慢，7w条数据慢800ms，加入正则减慢速度(测试中加入了正则)
* 加在一起没有200行，就那么一写，勿喷。

## 使用方法说明
1. 本程序只能读取简单格式的xls文件，文件布局如下（标准的行列结构）：<br>

        | 标题1| 标题2| 标题3|
        ------|------|----
        foo | foo  | foo
        bar | bar  | bar
        baz | baz  | baz

 ## 效果
![ExcelReadshua](效果.png)
## 实体类截图
![ExcelReadshua](实体类.png)
## 继承关系
![ExcelReadshua](关系.png)
## 加载Map模式
![ExcelReadshua](pic1.png)
## 加载Bean模式
![ExcelReadshua](pic2.png)

* 邮件(hacker.kill07@gmail.com)
* QQ: 985390927
* weibo: [@Alden_情绪控](http://weibo.com/Sweets07)
* Blog: [http://sweets.cf](http://sweets.cf)