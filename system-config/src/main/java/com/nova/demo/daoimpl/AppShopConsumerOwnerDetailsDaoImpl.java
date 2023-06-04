package com.nova.demo.daoimpl;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nova.demo.DTO.AppShopConsumerOwnerDetails;
import com.nova.demo.dao.AppShopConsumerOwnerDetailsDao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

@Repository
public class AppShopConsumerOwnerDetailsDaoImpl implements AppShopConsumerOwnerDetailsDao{
	
	@Autowired
    private EntityManager entityManager;

    @Override
    public List<AppShopConsumerOwnerDetails> getAllAppoinmentByShopIdAndDate(Long shopId, LocalDate date) {
        String sql = "SELECT sla.app_date AS appoinmentDate, sla.slot_time AS appoinmentTime, sla.id AS slotId, " +
                "sla.appointment_status AS appoinmentStatus, sd.shop_name AS shopName, sd.shop_address AS shopAddress, " +
                "sd.phone AS shopPhone, sd.latitude AS latitude, sd.longitude AS longitude, " +
                "con.id AS consumerId, con.username AS consumerName, con.phone AS consumerPhone, " +
                "own.id AS onwerId, own.username AS ownerName, own.phone AS ownerPhone " +
                "FROM slot_availibility AS sla " +
                "JOIN shop_details AS sd ON sd.id = sla.shop_id " +
                "JOIN user_details AS con ON con.id = sla.user_id " +
                "JOIN user_details AS own ON own.id = sd.user_id " +
                " WHERE sd.id = :shopId AND sla.app_date = :date ";

        Query query = entityManager.createNativeQuery(sql)
        .setParameter("shopId", shopId)
        .setParameter("date", Date.valueOf(date));
        
        List<Object[]> resultList = query.getResultList();
        
        List<AppShopConsumerOwnerDetails> appoinmentDetailsList = new ArrayList<>();

        for (Object[] result : resultList) {
            AppShopConsumerOwnerDetails details = new AppShopConsumerOwnerDetails();
            details.setAppoinmentDate(((Date) result[0]).toLocalDate());
            details.setAppoinmentTime(((Time) result[1]).toLocalTime());
            details.setSlotId((Long) result[2]);
            details.setAppoinmentStatus((String) result[3]);
            details.setShopName((String) result[4]);
            details.setShopAddress((String) result[5]);
            details.setShopPhone((String) result[6]);
            details.setLatitude((Long) result[7]);
            details.setLongitude((Long) result[8]);
            details.setConsumerId((Long) result[9]);
            details.setConsumerName((String) result[10]);
            details.setConsumerPhone((String) result[11]);
            details.setOnwerId((Long) result[12]);
            details.setOwnerName((String) result[13]);
            details.setOwnerPhone((String) result[14]);
            appoinmentDetailsList.add(details);
        }

        return appoinmentDetailsList;

        //return resultList;
    }

}