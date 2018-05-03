#store web
网站,用于对外提供服务

## 日志与 logstash 集成
* 添加logstash依赖
```xml
<dependency>
    <groupId>net.logstash.logback</groupId>
    <artifactId>logstash-logback-encoder</artifactId>
    <version>5.0</version>
</dependency>
```
* 日志配置文件logback.xml,该配置文件的product字段使用的是spring boot配置文件内的内容。
```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration>
<configuration>
    <springProperty scope="context" name="projectName" source="spring.application.name" defaultValue="log"/>

    <appender name="LOGSTASH" class="net.logstash.logback.appender.LogstashTcpSocketAppender">
        <destination>172.16.10.121:4560</destination>
        <encoder charset="UTF-8" class="net.logstash.logback.encoder.LogstashEncoder" >
            <customFields>{"project":"${projectName}"}</customFields>
        </encoder>
    </appender>

    <include resource="org/springframework/boot/logging/logback/base.xml"/>

    <root level="INFO">
        <appender-ref ref="LOGSTASH" />
        <appender-ref ref="CONSOLE" />
    </root>

</configuration>
```
* logstash 配置
```json
input {    
    tcp {  
        port => 4560
        codec => json_lines
        id   => "tcp_input"
    }   
}
output{
  elasticsearch { 
      hosts => ["localhost:9200"] 
      index => "logstash-%{project}-%{+YYYY.MM.dd}"
  }
  stdout { codec => rubydebug }
}
```
这种方式可以每个项目按日期生成，方便elasticsearch进行管理和日志清理

## zipkin 跟踪
* zipkin软件另外安装，安装后配置zipkin地址。Docker安装方法
```bash
docker run -d -p 9411:9411 openzipkin/zipkin
```
 * pom.xml配置
 ```xml
<dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-sleuth</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-zipkin</artifactId>
        </dependency>
```
* 配置文件
```yaml
spring:
    #链路跟踪 zipkin 配置
  zipkin:
    base-url: http://172.16.10.121:9411/
  sleuth:
    sampler:
      percentage: 1.0
      #关闭Rxjava跟踪。在配置feign.hystrix.enabled=true时会出现无意义的Rxjava跟踪信息
    rxjava:
      schedulers:
        hook:
          enabled: false
        ignoredthreads: ["RxIoScheduler"]
```

[spring-cloud-sleuth](https://cloud.spring.io/spring-cloud-sleuth/)