package com.simple.book.domain.alarm.dto;

import java.sql.Timestamp;
import java.util.UUID;

import com.simple.book.domain.alarm.entity.Alarm;
import com.simple.book.domain.user.entity.User;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AlarmDto {
	private UUID alarmId;
	private User user;
	private String message;
	private Timestamp createdAt;
	private Timestamp updatedAt;
	
	@Builder
	public AlarmDto(UUID alarmId, User user, String message, Timestamp createdAt, Timestamp updatedAt) {
		this.alarmId=alarmId;
		this.user=user;
		this.message=message;
		this.createdAt=createdAt;
		this.updatedAt=updatedAt;
	}
	
	public Alarm toEntity() {
		return Alarm.builder()
				.alarmId(alarmId)
				.user(user)
				.message(message)
				.build();
	}
	
}
