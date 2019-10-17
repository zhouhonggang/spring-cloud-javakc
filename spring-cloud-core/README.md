##**启动流程**
####**1.启动spring-cloud-eureka-server服务**
    eureka-server为分布式服务
    1.1 启动第1个EurekaServerApplication:
        Name:               EurekaServerApplication1
        Main class:         com.zhou.javakc.eureka.server.EurekaServerApplication
        Active profiles:    server1
            ~/resources/application-server1.yml 8001
    
    1.2 启动第2个EurekaServerApplication:
        Name:               EurekaServerApplication2
        Main class:         com.zhou.javakc.eureka.server.EurekaServerApplication
        Active profiles:    server2
            ~/resources/application-server2.yml 8002
        
####**2.启动spring-cloud-config-server服务**
    config-server为微服务配置管理中心
    启动ConfigServerApplication
    
####**3.启动spring-cloud-config-bus服务**
    config-bus为服务器配置自动刷新
    3.1 首先安装Erlang
        1: 设置环境变量，新建ERLANG_HOME
        2: 修改环境变量path，%ERLANG_HOME%\bin;
        3: cmd 检查 erl
    3.2 其次安装rabbitmq
        1: 设置环境变量，新建RABBITMQ_SERVER
        2: 修改环境变量path，%RABBITMQ_SERVER%\sbin;
        3: cmd rabbitmqctl status
            出错安装插件: rabbitmq-plugins.bat enable rabbitmq_management
        4: 运行rabbitmq环境
            cmd rabbitmq-server.bat 前台运行
            cmd rabbitmq-server.bat -detached 后台运行
            cmd cd ~rabbitmq\sbin 目录执行(rabbitmq-service.bat install) 设置开机自启
        5:浏览器访问http://localhost:15672
            账号:guest
            密码:guest
        6:cmd rabbitmqctl status
    3.3 启动ConfigBusApplication
####**4.启动spring-cloud-admin-server服务**
    admin-server为微服务监控中心
    启动AdminServerApplicationb
    访问中心地址: http://localhost:8070 
        账号: javakc
        密码: 123456
####**5.启动spring-cloud-gateway-api服务**
    gateway-api为微服务统一网关
    启动GatewayApiApplication            
    