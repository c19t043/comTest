package com.kybaby.domain;

/**
 * PeopleHandleRecord entity. @author MyEclipse Persistence Tools
 */

public class PeopleHandleRecord implements java.io.Serializable {

	// Fields

	private Long id;
	private String handlePeople;
	private Long peopleId;
	private String handleName;
	private String handleContent;
	private String handleTime;

	// Constructors

	/** default constructor */
	public PeopleHandleRecord() {
	}

	/** full constructor */
	public PeopleHandleRecord(String handlePeople, Long peopleId,
			String handleName, String handleContent, String handleTime) {
		this.handlePeople = handlePeople;
		this.peopleId = peopleId;
		this.handleName = handleName;
		this.handleContent = handleContent;
		this.handleTime = handleTime;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getHandlePeople() {
		return this.handlePeople;
	}

	public void setHandlePeople(String handlePeople) {
		this.handlePeople = handlePeople;
	}

	public Long getPeopleId() {
		return this.peopleId;
	}

	public void setPeopleId(Long peopleId) {
		this.peopleId = peopleId;
	}

	public String getHandleName() {
		return this.handleName;
	}

	public void setHandleName(String handleName) {
		this.handleName = handleName;
	}

	public String getHandleContent() {
		return this.handleContent;
	}

	public void setHandleContent(String handleContent) {
		this.handleContent = handleContent;
	}

	public String getHandleTime() {
		return this.handleTime;
	}

	public void setHandleTime(String handleTime) {
		this.handleTime = handleTime;
	}

}