1. 整合mybatis
	1. 添加依赖

			<dependency>
				<groupId>org.mybatis.spring.boot</groupId>
				<artifactId>mybatis-spring-boot-starter</artifactId>
				<version>1.1.1</version>
			</dependency>
	2. 自动配置：Spring Boot通过org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration来实现对Mybatis的自动配置，可通过application.properties文件中前缀为mybatis的属性设置mybatis的信息。其自动配置的信息如下
		1. SqlSessionFactory对象
		2. SqlSessionTemplate对象
	3. 在配置类添加@MapperScan注解来开启对Mybatis的Mapper代理的扫描或者在代理类添加@Mapper注解
		
			@SpringBootApplication
			@MapperScan(basePackages="com.tlh.**.mapper")
			public class TlhApplication {
			
				public static void main(String[] args) {
					SpringApplication.run(TlhApplication.class, args);
				}
			
			}
	4. 在application.properties文件中指定实体列名的包及mapper文件的路径

			#mybatis
			mybatis.typeAliasesPackage=com.tlh.sys.entity
			mybatis.mapperLocations=classpath*:sqlmap/*Mapper.xml
	4. **注意事项**
		1. Mapper的映射文件必须放在src/main/resources目录下(原因为maven在进行编译之后不会将src/main/java下面的非java文件编译到target/classes目录下)
2. 整合shiro
	1. 添加依赖

			<dependency>
				<groupId>org.apache.shiro</groupId>
				<artifactId>shiro-spring-boot-web-starter</artifactId>
				<version>1.4.0-RC2</version>
			</dependency>
	2. 自动配置：Spring Boot通过org.apache.shiro.spring.config.web.autoconfigure包完成对shiro的自动配置，其自动配置的信息如下
		1. ShiroWebAutoConfiguration来自动配置SessionsSecurityManager、会话管理、记住我管理等核心对象，并且可以通过配置设置会话管理和记住我的cookie信息(具体属性名查看AbstractShiroWebConfiguration类)
			1. 说明
				1. 记住我：表示的是访问指定地址的资源是只要身份验证通过或RememberMe登录的都可以。
		2. ShiroWebFilterConfiguration来自动配置ShiroFilterFactoryBean和FilterRegistrationBean对象
	3. 在application.properties文件中配置如下信息

			shiro.web.enabled=true//可以设置，默认自动开启自动配置
			shiro.annotations.enabled=true
			//认证界面
			shiro.loginUrl=/login
			//认证成功的界面
			shiro.successUrl=/home
	4. 定义Realm对象和加密方式
		1. 定义类继承AuthorizingRealm抽象类处理用户认证和授权
		2. 在配置类中将自定义的Realm定义为bean
		3. 设置加密方式

				@SpringBootApplication
				@MapperScan(basePackages="com.tlh.**.mapper")
				public class TlhApplication {
				
					public static void main(String[] args) {
						SpringApplication.run(TlhApplication.class, args);
					}
				
					@Bean
					Realm realm(){
						CustomRealm customRealm=new CustomRealm();
						customRealm.setCredentialsMatcher(new HashedCredentialsMatcher(Md5Hash.ALGORITHM_NAME));
						return customRealm;
					}
					
					private class CustomRealm extends AuthorizingRealm{
				
						//授权
						@Override
						protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
							return null;
						}
				
						//认证
						@Override
						protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
							return null;
						}
						
					}
					
				}
	5. 配置Shiro权限过滤过滤器定义：Shiro可以通过配置文件实现基于URL的授权验证。

			@Bean
		    ShiroFilterChainDefinition shiroFilterChainDefinition(){
		        DefaultShiroFilterChainDefinition shiroFilterChainDefinition=new DefaultShiroFilterChainDefinition();
		        shiroFilterChainDefinition.addPathDefinition("/login","authc");
		        return shiroFilterChainDefinition;
		    }
		1. FilterChain定义格式： URL_Ant_Path_Expression = Path_Specific_Filter_Chain 
		2. 每个URL配置，表示匹配该URL的应用程序请求将由对应的过滤器进行验证。 
			1. 内置的权限过滤器(别名都在DefaultFilter枚举中定义)
				<table>
				    <tr>
				        <td>名称</td>
				        <td>实现类</td>
				        <td>说明</td>
				    </tr>
				    <tr>
				        <td>anon</td>
				        <td>org.apache.shiro.web.filter.authc.AnonymousFilter</td>
				        <td>不需要进行安全认证即可访问资源</td>
				    </tr>
				    <tr>
				        <td>authc</td>
				        <td>org.apache.shiro.web.filter.authc.FormAuthenticationFilter</td>
				        <td>需要进行认证才能访问资源</td>
				    </tr>
				    <tr>
				        <td>authcBasic</td>
				        <td>org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter</td>
				        <td>需要进行认证才能访问资源</td>
				    </tr>
				    <tr>
				        <td>perms</td>
				        <td>org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter</td>
				        <td>需要当前用户具有该权限才能访问</td>
				    </tr>
				    <tr>
				        <td>port</td>
				        <td>org.apache.shiro.web.filter.authz.PortFilter</td>
				        <td>要求访问资源的端口必须为指定的端口</td>
				    </tr>
				    <tr>
				        <td>rest</td>
				        <td>org.apache.shiro.web.filter.authz.HttpMethodPermissionFilter</td>
				        <td>请求方法过滤</td>
				    </tr>
				    <tr>
				        <td>roles</td>
				        <td>org.apache.shiro.web.filter.authz.RolesAuthorizationFilter</td>
				        <td>需要当前用户具有该角色才能访问</td>
				    </tr>
				    <tr>
				        <td>ssl</td>
				        <td>org.apache.shiro.web.filter.authz.SslFilter</td>
				        <td>SSL认证过滤</td>
				    </tr>
				    <tr>
				        <td>user</td>
				        <td>org.apache.shiro.web.filter.authc.UserFilter</td>
				        <td>指定的用户可以访问(认证过并且记住我的)</td>
				    </tr>
				</table>
4. thymeleaf整合shiro：https://github.com/theborakompanioni/thymeleaf-extras-shiro
	1. 添加依赖：thymeleaf-extras-shiro

			<dependency>
	            <groupId>com.github.theborakompanioni</groupId>
	            <artifactId>thymeleaf-extras-shiro</artifactId>
	            <version>2.0.0</version>
	        </dependency>
		1. 说明(不同版本的区别)
			1. 2.0.0:
				1. Thymeleaf version 3.0.2.RELEASE
				2. Shiro version 1.3.2
			3. 1.2.1：
				1. Thymeleaf version 2.1.4
				2. Shiro version 1.2.4
			3. 1.1.0：
				1. Thymeleaf version 2.1.0
				2. Shiro version 1.2.2
			3. 1.0.2：
				1. Thymeleaf version 2.0.18
				2. Shiro version 1.2.2
			3. 1.0.1：
				1. Thymeleaf version 2.0.15
				2. Shiro version 1.2.1 
	3. 在shiro的配置类中添加方言Bean

			@Bean
		    ShiroDialect shiroDialect(){
		        return new ShiroDialect();
		    }
	2. 界面引入命名空间

			<html xmlns:th="http://www.thymeleaf.org"
     			  xmlns:shiro="http://www.pollix.at/thymeleaf/shiro">
	3. 标签:(可以查看引入包中的Shiro-Dialect.xml文件)
		1. guest tag

				<p shiro:guest="">
				  Please <a href="login.html">Login</a>
				</p>
		2. user tag

				<p shiro:user="">
				  Welcome back John! Not John? Click <a href="login.html">here<a> to login.
				</p>
		3. authenticated tag

				<a shiro:authenticated="" href="updateAccount.html">Update your contact information</a>
		4. notAuthenticated tag

				<p shiro:notAuthenticated="">
				  Please <a href="login.html">login</a> in order to update your credit card information.
				</p>
		5. principal tag

				<p>Hello, <span shiro:principal=""></span>, how are you today?</p>
				或者
				<p>Hello, <shiro:principal/>, how are you today?</p>
		6. hasRole tag

				<a shiro:hasRole="administrator" href="admin.html">Administer the system</a>
		7. lacksRole tag

				<p shiro:lacksRole="administrator">
				  Sorry, you are not allowed to administer the system.
				</p>
		8. hasAllRoles tag

				<p shiro:hasAllRoles="developer, project manager">
				  You are a developer and a project manager.
				</p>
		8. hasAnyRoles tag

				<p shiro:hasAnyRoles="developer, project manager, administrator">
				  You are a developer, project manager, or administrator.
				</p>
		9. hasPermission tag

			<a shiro:hasPermission="user:create" href="createUser.html">Create a new User</a>
		10. lacksPermission tag

				<p shiro:lacksPermission="user:delete">
				  Sorry, you are not allowed to delete user accounts.
				</p>
		11. hasAllPermissions tag

				<p shiro:hasAllPermissions="user:create, user:delete">
				  You can create and delete users.
				</p>
		12. hasAnyPermissions tag

				<p shiro:hasAnyPermissions="user:create, user:delete">
				  You can create or delete users.
				</p>