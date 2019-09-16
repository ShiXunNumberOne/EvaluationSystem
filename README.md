# Evaluation
教师评教系统
## 主要功能
* 系统用户,角色,权限增删改查,权限分配，权限配色<br/>
* 教师评教，学生评教
* 管理员开启评教<br/>
* 指标管理 选项管理<br/>
* 教学管理 （班级 课程 学院）<br/>

## 技术框架
* 核心框架：`SpringBoot`
* 安全框架：`Apache Shiro 1.3.2`
* 缓存框架：`Redis 4.0`
* 搜索框架：`Lucene 7.1`
* 任务调度：`quartz 2.3`
* 持久层框架：`MyBatis 3` <a href="http://baomidou.oschina.io/mybatis-plus-doc/#/" target="_blank">mybatisplus</a> 2.1.4
* 数据库连接池：`Alibaba Druid 1.0.2`
* 日志管理：`SLF4J 1.7`、`Log4j`
* 前端框架：`layui`
* 后台模板：<a href="http://layuicms.gitee.io/layuicms2.0/index.html" target="_blank">layuicms 2.0。</a>
* 富文本：<a href="http://www.wangeditor.com/" target="_blank">wangEditor</a>

### 开发环境
* IDE:`idea`
* DB:`Mysql5.7`  `Redis`(<a href="https://github.com/MicrosoftArchive/redis/releases" target="_blank">Window版本</a>,<a href="https://redis.io/download" target="_blank">Linux版本</a>)
* JDK:`JAVA 8`
* WEB:<del>Tomcat8</del> （采用springboot框架开发时,并没有用到额外的tomcat 用的框架自带的）

# 运行环境
* WEB服务器：`Weblogic`、`Tomcat`、`WebSphere`、`JBoss`、`Jetty` 等
* 数据库服务器：`Mysql5.7+`
* 操作系统：`Windows`、`Linux` (Linux 大小写特别敏感 特别要注意,还有Linux上没有微软雅黑字体,需要安装这个字体,用于生成验证码)
