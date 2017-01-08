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
		1. ShiroWebAutoConfiguration来自动配置SessionsSecurityManager对象
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
			1. 内置的权限过滤器
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