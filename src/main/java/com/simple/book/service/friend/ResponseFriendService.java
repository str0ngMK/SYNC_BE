package com.simple.book.service.friend;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simple.book.entity.FriendReqEntity;
import com.simple.book.repository.FriendReqRepository;

import jakarta.servlet.http.HttpSession;

@Service
public class ResponseFriendService {
	@Autowired
	private FriendReqRepository friendReqRepository;

	public HashMap<String, Object> responseFriend(HttpSession session) {
		HashMap<String, Object> result = new HashMap<>();
		Object myId = session.getAttribute("id");
		if (myId != null) {
			List<FriendReqEntity> entityList = friendReqRepository.findByReqIdAndAcceptYn((String) myId, "R");
			if (entityList.size() != 0) {
				List<Object> userList = new ArrayList<>();
				for (FriendReqEntity entity : entityList) {
					String fromId = entity.getId();
					String insDate = entity.getInsDate();
					String insTime = entity.getInsTime();
					userList.add(putUserInfo(fromId, insDate, insTime));
				}
				result.put("result", userList);
			} else {
				result.put("result", "no_request_friend");
			}
		} else {
			result.put("result", "no_session");
		}
		return result;
	}
	
	private HashMap<String, Object> putUserInfo(String fromId, String insDate, String insTime){
		HashMap<String, Object> result = new HashMap<>();
		result.put("from_id", fromId);
		result.put("ins_date", insDate);
		result.put("ins_time", insTime);
		return result;
	}
}