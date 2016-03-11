开发流程

1. 搭建开发环境
	1.1 导入开发包
		mysql驱动：
		C3P0连接池：
		DBUtils开发包：
		JSTL开发包：
		log4j开发包：commons-fileupload-1.3.1.jar
		文件上传包：
	1.2 创建程序包
		domain
		dao
		dao.impl
		service
		service.impl
		web.manager
		web.client
		utils
		
		webroot\manager 保存后台相关的jsp
		webroot\cllient 保存前台相关的jsp
		webroot\images 保存网站图片
		
		前台分帧：index.jsp--client--head.jsp
							    --body.jsp
		后台分帧：manager.jsp--manage--head.jsp
								  --left.jsp
								  --body.jsp
	1.3 创建库
		create database bookstore;
	1.4 写网站的一些工具类
		CharacterEncoding
		HtmlFilter
		JdbcUtils
		DaoFactory

2. 分类模块
	create table category
	(
		id varchar(40) primary key,
		name varchar(100) not null unique,
		description varchar(255)
	);

3. 图书模块
	create table book
	   (
	  		id varchar(40) primary key,
	  		name varchar(100) not null unique,
	  		author varchar(100) not null,
	  		price double not null,
	 		image varchar(100),
	  		description varchar(255),
	  		category_id varchar(40),
	  		constraint category_id_FK foreign key(category_id) references category(id)
	   );
	 
4. 用户
	create table user
	(
		id varchar(40) primary key,
		username varchar(40) not null unique,
		password varchar(40) not null,
		phone varchar(40) not null,
		cellphone varchar(40) not null,
		email varchar(40) not null,
		address varchar(255) not null
	);

5. 订单
	create table orders
	(
		id varchar(40) primary key,
		ordertime datetime not null,
		price double not null,
		state boolean,
		user_id varchar(40),
		constraint user_id_FK foreign key(user_id) references user(id)
	);
	
	create table orderitem
	(
		id varchar(40) primary key,
		quantity int,
		price double,
		order_id varchar(40),
		book_id varchar(40),
		constraint order_id_FK foreign key(order_id) references orders(id),
		constraint book_id_FK foreign key(book_id) references book(id)
	);