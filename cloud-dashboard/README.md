# cloud dashboard
Hystrix dashboard 是断路器仪表盘，可以查看断路器相关服务情况。
## 本地仪表盘地址
http://localhost:8000/hystrix/
## 网站监控地址
http://localhost:8080/hystrix.stream
## 集成turbine的监控地址
在项目很多的情况下，
http://localhost:8000/turbine.stream


配置信息如下：
```yaml
turbine:
  app-config: store-web #使用turbine监控的项目名，多个之间用逗号分开
  aggregator:
    cluster-config: default,test-cluster #默认可以不填写，如果按集群进行监控该项需要填写
  cluster-name-expression: metadata['cluster'] #按集群进行监控。
#  cluster-name-expression: new String("default") #该模式监控所有项目，不支持按集群进行监控
```
在按群集进行监控需要在被监控项目中进行相应的配置
```yaml
eureka:
  instance:
    metadata-map:
      cluster: cluster-name
```
