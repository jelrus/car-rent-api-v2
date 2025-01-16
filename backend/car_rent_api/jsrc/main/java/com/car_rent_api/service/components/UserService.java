package com.car_rent_api.service.components;

import com.car_rent_api.persistence.models.dto.users.PersonalInfoFormData;
import com.car_rent_api.persistence.models.dto.users.PersonalInfoResponse;

public interface UserService {

    PersonalInfoResponse updateProfile(String id, PersonalInfoFormData data);

    PersonalInfoResponse getProfile(String id);
}