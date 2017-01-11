## 更新纪录

### 更新2017/01/11
* 增加AnyCol来对应FilterCol方法，只保留AnyCol类列
* 增加SetCellStyle，支持方法链式设置列单元格风格

### 更新2017/01/09
* 增加SetPath方法，随时切换保存路径
* 增加ConvertName方法，方便自定义Excel列名称
* 增加Flush方法，不在建议使用~~Save(@Deprecated)~~方法进行保存输出
* 增加SetOutputStream方法，可以放入自定义流，用于支持网页Response输出
* 处理一些小bug，完善异常提示信息

### 更新2017/01/06
* 整合注解，导出和读取使用同一个ExcelAnno注解
* 统一编码为UTF-8

### 更新2017/01/05
* 修复据库查询的导出(Object)递归越栈问题
* 增加新的xxx.Class定义类型导出，操作更简单
* 导出注解支持(自己使用seven.savewapper.anno.ExcelAnno类型注解)

### 更新2017/01/04
* 修复据库查询的导出(Map)空指针&下标越界问题
* 修复在Filter数据后出现空行问题
* 丢入Result参数后的操作就如同操作Map/Object一样

### 更新2017/01/01
* 增加ResExprotDBMap&ResExprotDBObj用于支持数据库导出
* 支持基于数据库查询的导出(Map),直接放入Result对象即可
* 支持基于数据库查询的导出(Object),直接放入Result对象即可

### 更新2016/11/30
* 增加了简单类型得写入，生成xls/xlsx
* 直接JavaBean类型写入，注解命名
* 支持Map key-value类型写入
* 写入时支持和读取一样得过滤加工排序等写法

### 更新2016/11/29
* 增加CreateMap By Key
* 去除无用泛型
* 增加xlsx支持

### 更新2016/11/8
* 支持数据过滤和处理是转换，基于事件模式
* 更新为Build模式创建实例
* 增加数据过滤、数据转换和数据排序回调接口
* 采用链式set方式进行

### 更新2016/06/04
* 创建项目工程
