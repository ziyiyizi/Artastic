package com.javaee.artastic.Artastic.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import com.javaee.artastic.Artastic.domain.Notification;

@Component
public interface NotificationDao extends JpaRepository<Notification, Long>{
	
	//public List<Notification> findByReceiverNameOrderByNotiTimeDesc(String receiverName);
	
	@Query(value="select * from notification where Receiver_Name=?1 order by Noti_Time desc limit 50", nativeQuery=true)
	public List<Notification> findByReceiverNameOrderByNotiTimeDesc(String receiverName);
	
	@Modifying
	@Query(value="update notification set Noti_State='1' where Receiver_Name = ?1 and Noti_Time < now()", nativeQuery=true)
	public int update(String receiverName);
	
	@Query(value="select count(*) from Notification where receiverName=:name and notiState='0'")
	public int countNotifyNum(@Param("name")String receiverName);
}
