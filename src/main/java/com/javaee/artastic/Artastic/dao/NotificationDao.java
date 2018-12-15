package com.javaee.artastic.Artastic.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import com.javaee.artastic.Artastic.domain.Notification;

@Component
public interface NotificationDao extends JpaRepository<Notification, Long>{
	List<Notification> findByReceiverNameOrderByNotiTimeDesc(String receiverName);
	
//	@Query("select * from Notification where receiverName=:receiverName and notiState='0' order by notiTime desc")
//	List<Notification> findByReceiverNameOrderByNotiTimeDesc(@Param("receiverName")String receiverName);
	
	@Modifying
	@Query(value="update notification set Noti_State='1' where Receiver_Name = ?1 and Noti_Time < now()", nativeQuery=true)
	public int update(String receiverName);
}
