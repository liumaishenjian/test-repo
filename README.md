# SQL查询工具

这是一个基于Spring Boot的SQL查询工具，支持自然语言查询和SQL查询两种模式。

## 功能特点

- 支持自然语言查询转换为SQL
- 支持直接SQL查询
- 分页查询支持
- 美观的Web界面
- 查询结果实时展示

## 技术栈

- Spring Boot 2.7.0
- MyBatis-Plus 3.5.2
- MySQL
- HTML/CSS/JavaScript

## 快速开始

1. 克隆项目
```bash
git clone https://github.com/liumaishenjian/test-repo.git
```

2. 配置数据库

复制`src/main/resources/application.properties.example`为`application.properties`，并修改数据库配置：

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/your_database
spring.datasource.username=your_username
spring.datasource.password=your_password
```

3. 启动项目
```bash
mvn spring-boot:run
```

4. 访问应用

打开浏览器访问：`http://localhost:8080`

## 使用说明

1. 自然语言模式
- 直接输入自然语言描述，如："查询最近一周的订单数据"

2. SQL模式
- 切换到SQL模式后，可直接输入SQL语句
- 仅支持SELECT查询操作

## 注意事项

- 为了安全考虑，仅支持SELECT查询操作
- 建议在查询中添加适当的限制条件，避免返回过大的数据集
- 请确保数据库连接信息安全保存，不要提交到代码仓库

## 许可证

MIT License