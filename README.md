Package Package
----------------------------
![image](https://github.com/lucas0x6b6f/spring4mvc-hibernate/blob/master/src/main/resources/image/package-explorer.png)


Import hibernateDB (MySQL)
----------------------------
There are many information of users in database.
![image](https://github.com/lucas0x6b6f/spring4mvc-hibernate/blob/master/src/main/resources/image/hibernateDB.png)


The Setting of the spring-database.xml
----------------------------
Changing setting of database connection.
``` XML
	<bean id="dataSource"
		class="org.springframework.jdbc.datasource.DriverManagerDataSource">
		<property name="driverClassName" value="com.mysql.jdbc.Driver" />
		<property name="url"
			value="jdbc:mysql://127.0.0.1:3306/hibernateDB" />
		<property name="username" value="root" />
		<property name="password" value="P@ssw0rd" />
	</bean>
```
>

mappingResources
``` XML
	<property name="mappingResources">
			 <list>
				<value>orm/Users.hbm.xml</value>
			</list>
	</property>
```
>

transactionManager
``` XML
	 <bean id = "transactionManager" 
	  class = "org.springframework.orm.hibernate4.HibernateTransactionManager">
        <property name = "sessionFactory" ref = "sessionFactory" />
    </bean>
    
	<tx:annotation-driven transaction-manager="transactionManager"/>	
```



The ORM Setting of the Users.hbm.xml
----------------------------
mapping from database.
```XML
<hibernate-mapping>
	<class name="com.lucasko.model.User" table="users" >
		<id name="username" type="string">
			<column name="username" length="45" />
			<generator class="assigned" />
		</id>
		<property name="gender" type="boolean">
			<column name="gender" not-null="true" />
		</property>

		<property name="team" type="string">
			<column name="team" length="10" not-null="true"  />
		</property>
		
		<property name="tel" type="string">
			<column name="tel" length="11"  not-null="true"  />
		</property>

	</class>
</hibernate-mapping>

```

Run Project on Server
-----------------------------
[http://localhost:8080/spring4mvc-hibernate/](http://localhost:8080/spring4mvc-hibernate/)

###1.Show All Users
``` JAVA
		public List<User> getUsers() {
		List<User> users = new ArrayList<User>();
		users = getSessionFactory().getCurrentSession().createQuery("from User").list();
		return users ;
	}

``` 
![image](https://github.com/lucas0x6b6f/spring4mvc-hibernate/blob/master/src/main/resources/image/index.png)

###2.Add User
addUser
``` JAVA
		public void saveOrUpdateUser(User user) {
	        	getSessionFactory()
				.getCurrentSession().saveOrUpdate(user.getUsername(), user);
	    }
``` 
![image](https://github.com/lucas0x6b6f/spring4mvc-hibernate/blob/master/src/main/resources/image/add.png)


###3.Update User
getUser
``` JAVA
	public User getUser(String username) {
		List<User> users = new ArrayList<User>();
		users = getSessionFactory()
				.getCurrentSession()
				.createQuery("from User where username=?")
				.setParameter(0, username)
				.list();
		if (users.size() > 0) {
			return users.get(0);
		}
		}
``` 
>

updateUser (saveOrUpdateUser)
``` JAVA
public void saveOrUpdateUser(User user) {
	        	getSessionFactory()
				.getCurrentSession().saveOrUpdate(user.getUsername(), user);
	    }
``` 
![image](https://github.com/lucas0x6b6f/spring4mvc-hibernate/blob/master/src/main/resources/image/update.png)



###4.Count (Group by)
``` JAVA
	public List<Count> getCount() {
		Session session = getSessionFactory().getCurrentSession() ;
		Criteria criteria = session.createCriteria(User.class)
				 .setProjection(Projections.projectionList()
						 .add(Projections.groupProperty("team"), "team")
						 .add(Projections.rowCount(), "count"))  ;
	             criteria.setResultTransformer(new AliasToBeanResultTransformer(
	     				Count.class));
	   List<Count> results = criteria.list() ;
		return results ;
	}
``` 
![image](https://github.com/lucas0x6b6f/spring4mvc-hibernate/blob/master/src/main/resources/image/count.png)
