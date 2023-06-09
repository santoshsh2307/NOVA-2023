package com.nova.Owner.Service;

import java.util.List;
import java.util.Optional;

import com.nova.Owner.DTO.AppointmentDetails;
import com.nova.Owner.DTO.AppointmentDetailsDTO;
import com.nova.Owner.DTO.ShopDetailsDTO;
import com.nova.Owner.Entity.UserDetails;

public interface OwnerOREmployeeService {

	UserDetails createOwnerOREmployee(UserDetails details);

	Optional<UserDetails> findOwnerByUserNameAndPassword(String userName, String password);

	Optional<UserDetails> findOwnerByPhoneNumber(String ownerPhone);

	Optional<UserDetails> findOwnerByEmail(String email);

	List<ShopDetailsDTO> GetShopDetailsByOwnerId(Long ownerId);

	List<AppointmentDetailsDTO> GetAppoinemetByShopId(Long shopid);

	List<AppointmentDetails> GetAppointmentDetailsByAppointmentId(Long appointmentId);

}
