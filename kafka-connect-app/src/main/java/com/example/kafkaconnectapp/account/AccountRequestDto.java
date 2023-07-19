package com.example.kafkaconnectapp.account;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AccountRequestDto {

	private String holder;

	private String funds;

	public AccountRequestDto(@JsonProperty String holder, @JsonProperty String funds) {
		this.holder = holder;
		this.funds = funds;
	}

	public AccountRequestDto() {}

	public String getHolder() {
		return holder;
	}

	public void setHolder(String holder) {
		this.holder = holder;
	}

	public String getFunds() {
		return funds;
	}

	public void setFunds(String funds) {
		this.funds = funds;
	}
}
