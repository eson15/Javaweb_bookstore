package domain;

public class Category {
	/*
	 create table category
	 (
		id varchar(40) primary key,
		name varchar(100) not null unique,
		description varchar(255)
	 );
	 */
	
	private String id;
	private String name;
	private String description;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	

}
