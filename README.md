# SpringMVC_HelloWorld
SpringMVC的一个hello world的demo,开启学习SpringMVC的大门。

SpringMVC 框架提供了一个DispatchServlet作为前端控制器来分派请求，同时提供灵活的配置处理程序映射，视图解析，语言环境和主题解析，并支持文件上传。SpringMVC还包含多种视图技术，例如 JSP,Velocity,Tiles,iText和POI等。SpringMVC分离了控制器。

SpringMVC具有如下特点:

* SpringMVC具有强大的灵活性，非侵入性和可配置性
* SpringMVC提供了一个前端控制器DispatcherServlet，开发者无须额外开发控制器对象
* SpringMVC分工明确，包括控制器，验证器，命令对象，模型对象，



创建一个项目名称为SpringMVC_HelloWorld

目录结构如下:

![2](http://p1aoqp63y.bkt.clouddn.com/QQ%E6%88%AA%E5%9B%BE20180225220023.png)



maven中的配置：

```
  <properties>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    <spring.version>4.3.8.RELEASE</spring.version>
    <jackson.version>2.7.3</jackson.version>
    <poi.version>3.14</poi.version>
  </properties>

  <dependencies>
    <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>3.8.1</version>
      <scope>test</scope>
    </dependency>

    <!--spring相关包-->
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-web</artifactId>
      <version>${spring.version}</version>
    </dependency>
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-webmvc</artifactId>
      <version>${spring.version}</version>
    </dependency>
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-core</artifactId>
      <version>${spring.version}</version>
    </dependency>
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-context-support</artifactId>
      <version>${spring.version}</version>
    </dependency>
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-oxm</artifactId>
      <version>${spring.version}</version>
    </dependency>
    <!-- spring事务相关-->
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-tx</artifactId>
      <version>${spring.version}</version>
    </dependency>
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-test</artifactId>
      <version>${spring.version}</version>
    </dependency>
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-jdbc</artifactId>
      <version>${spring.version}</version>
    </dependency>
    <!--aspectj start-->
    <dependency>
      <groupId>org.aspectj</groupId>
      <artifactId>aspectjweaver</artifactId>
      <version>1.6.2</version>
    </dependency>
    <dependency>
      <groupId>org.aspectj</groupId>
      <artifactId>aspectjrt</artifactId>
      <version>1.6.2</version>
    </dependency>

    <!--其他需要的包-->
    <dependency>
      <groupId>org.apache.commons</groupId>
      <artifactId>commons-lang3</artifactId>
      <version>3.4</version>
    </dependency>
    <dependency>
      <groupId>commons-fileupload</groupId>
      <artifactId>commons-fileupload</artifactId>
      <version>1.3.1</version>
    </dependency>

    <!-- json -->
    <dependency>
      <groupId>org.codehaus.jackson</groupId>
      <artifactId>jackson-mapper-asl</artifactId>
      <version>1.9.13</version>
    </dependency>

    <dependency>
      <groupId>com.alibaba</groupId>
      <artifactId>fastjson</artifactId>
      <version>1.2.11</version>
    </dependency>

    <dependency>
      <groupId>com.fasterxml.jackson.core</groupId>
      <artifactId>jackson-annotations</artifactId>
      <version>${jackson.version}</version>
    </dependency>

    <dependency>
      <groupId>com.fasterxml.jackson.core</groupId>
      <artifactId>jackson-core</artifactId>
      <version>${jackson.version}</version>
    </dependency>
    <dependency>
      <groupId>com.fasterxml.jackson.core</groupId>
      <artifactId>jackson-databind</artifactId>
      <version>${jackson.version}</version>
    </dependency>

    <!--j2ee相关包 servlet、jsp、jstl-->
    <dependency>
      <groupId>javax.servlet</groupId>
      <artifactId>javax.servlet-api</artifactId>
      <version>3.1.0</version>
    </dependency>
    <dependency>
      <groupId>javax.servlet.jsp</groupId>
      <artifactId>jsp-api</artifactId>
      <version>2.2</version>
    </dependency>
    <dependency>
      <groupId>javax.servlet</groupId>
      <artifactId>jstl</artifactId>
      <version>1.2</version>
    </dependency>

  </dependencies>
```



在resources下新建一个spring文件夹，里面创建一个spring-mvc.xml

```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:mvc="http://www.springframework.org/schema/mvc"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
                        http://www.springframework.org/schema/beans/spring-beans-3.2.xsd
                         http://www.springframework.org/schema/context
                        http://www.springframework.org/schema/context/spring-context-3.2.xsd
                        http://www.springframework.org/schema/mvc
                        http://www.springframework.org/schema/mvc/spring-mvc.xsd">
<!--启用spring的一些annotation -->
<context:annotation-config/>

<!-- 自动扫描该包，使SpringMVC认为包下用了@controller注解的类是控制器 -->
    <context:component-scan base-package="com.anlu.*.controller">
        <context:include-filter type="annotation" expression="org.springframework.stereotype.Controller"/>
    </context:component-scan>

    <!--扫描service-->
    <context:component-scan base-package="com.anlu"/>

<!--HandlerMapping 无需配置，springmvc可以默认启动-->

<!--静态资源映射-->
<!--本项目把静态资源放在了WEB-INF的statics目录下，资源映射如下-->
<!--<mvc:resources mapping="/css/**" location="/WEB-INF/statics/css/"/>-->
<!--<mvc:resources mapping="/js/**" location="/WEB-INF/statics/js/"/>-->
<!--<mvc:resources mapping="/image/**" location="/WEB-INF/statics/image/"/>-->

<!--但是项目部署到linux下发现WEB-INF的静态资源会出现无法解析的情况，但是本地tomcat访问正常，因此建议还是直接把静态资源放在webapp的statics下，映射配置如下-->
<mvc:resources mapping="/css/**" location="/statics/css/"/>
<mvc:resources mapping="/js/**" location="/statics/js/"/>
<mvc:resources mapping="/images/**" location="/statics/images/"/>

<!-- 配置注解驱动 可以将request参数与绑定到controller参数上 -->
<mvc:annotation-driven/>

<!-- 对模型视图名称的解析，即在模型视图名称添加前后缀(如果最后一个还是表示文件夹,则最后的斜杠不要漏了) 使用JSP-->
<!-- 默认的视图解析器 在上边的解析错误时使用 (默认使用html)- -->
<bean id="defaultViewResolver" class="org.springframework.web.servlet.view.InternalResourceViewResolver">
    <property name="viewClass" value="org.springframework.web.servlet.view.JstlView"/>
    <property name="prefix" value="/WEB-INF/views/"/><!--设置JSP文件的目录位置-->
    <property name="suffix" value=".jsp"/>
</bean>

<!-- springmvc文件上传需要配置的节点-->
<bean id="multipartResolver" class="org.springframework.web.multipart.commons.CommonsMultipartResolver">
    <property name="maxUploadSize" value="20971500"/>
    <property name="defaultEncoding" value="UTF-8"/>
    <property name="resolveLazily" value="true"/>
</bean>

    <!-- 支持返回json，用来处理json格式转换，避免IE执行ajax时，返回json出现下载文件 -->
    <bean class="org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter">
        <property name="messageConverters">
            <list >
                <ref bean="mappingJacksonHttpMessageConverter" />
            </list>
        </property>
    </bean>
    <bean id="mappingJacksonHttpMessageConverter" class="org.springframework.http.converter.json.MappingJackson2HttpMessageConverter">
        <property name="supportedMediaTypes">
            <list>
                <value>application/json;charset=UTF-8</value>
            </list>
        </property>
    </bean>
</beans>
```



其中有两个配置很关键：

* context:annotation-config/>:启用spring的一些annotation
* <mvc:annotation-driven/>:配置注解驱动 可以将request参数与绑定到controller参数上 



### 在WEB-INF下创建views文件夹，存放视图

然后新建一个hello.jsp





### Controller

```
@Controller
public class HelloController {

    @RequestMapping(value = "/hello")
    public String helloworld(ModelMap model){
        model.addAttribute("msg", "Hello Spring MVC Framework!");
        return "hello";
    }
}
```



