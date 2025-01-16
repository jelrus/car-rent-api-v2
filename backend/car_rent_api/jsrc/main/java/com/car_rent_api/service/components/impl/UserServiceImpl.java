package com.car_rent_api.service.components.impl;

import com.car_rent_api.config.Resources;
import com.car_rent_api.config.TableKeys;
import com.car_rent_api.exception.ExistenceException;
import com.car_rent_api.persistence.dao.components.UserDao;
import com.car_rent_api.persistence.models.dto.users.PersonalInfoFormData;
import com.car_rent_api.persistence.models.dto.users.PersonalInfoResponse;
import com.car_rent_api.persistence.models.entity.User;
import com.car_rent_api.service.components.UserService;
import com.car_rent_api.utils.components.ImageUploader;

public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final ImageUploader imageUploader;

    public UserServiceImpl(UserDao userDao, ImageUploader imageUploader) {
        this.userDao = userDao;
        this.imageUploader = imageUploader;
    }

    @Override
    public PersonalInfoResponse updateProfile(String id, PersonalInfoFormData data) {
        checkUser(id);
        User user = userDao.findById(id);

        user.toBuilder()
                .imageUrl(isFieldNullOrBlank(data.getAvatar()) ? user.getImageUrl() : generateAndLoadImage(user, data))
                .firstName(isFieldNullOrBlank(data.getFirstName()) ? user.getFirstName() : data.getFirstName())
                .lastName(isFieldNullOrBlank(data.getLastName()) ? user.getLastName() : data.getLastName())
                .username()
                .phoneNumber(isFieldNullOrBlank(data.getPhoneNumber()) ? user.getPhoneNumber() : data.getPhoneNumber())
                .postalCode(isFieldNullOrBlank(data.getPostalCode()) ? user.getPostalCode() : data.getPostalCode())
                .country(isFieldNullOrBlank(data.getCountry()) ? user.getCountry() : data.getCountry())
                .city(isFieldNullOrBlank(data.getCity()) ? user.getCity() : data.getCity())
                .street(isFieldNullOrBlank(data.getStreet()) ? user.getStreet() : data.getStreet())
                .build();

        userDao.put(user);

        return toPersonalInfoResponse(user);
    }

    @Override
    public PersonalInfoResponse getProfile(String id) {
        checkUser(id);
        return toPersonalInfoResponse(userDao.findById(id));
    }

    private void checkUser(String clientId) {
        if (!userDao.isExistsById(clientId)) {
            throw new ExistenceException("User was not found");
        }
    }

    private boolean isFieldNullOrBlank(String value) {
        return value == null || value.isBlank();
    }

    private String generateAndLoadImage(User user, PersonalInfoFormData data) {
        return imageUploader.builder()
                .region(Resources.REGION)
                .bucketName(Resources.S3_BUCKET)
                .key("static/images/users/" + user.getSkId().replace(TableKeys.USER_SK_PREFIX, "") + ".jpg")
                .base64Image(data.getAvatar())
                .build();
    }

    private PersonalInfoResponse toPersonalInfoResponse(User user) {
        return PersonalInfoResponse.builder()
                .clientId(user.getSkId().replace(TableKeys.USER_SK_PREFIX, ""))
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .imageUrl(user.getImageUrl())
                .postalCode(user.getPostalCode())
                .country(user.getCountry())
                .city(user.getCity())
                .street(user.getStreet())
                .phoneNumber(user.getPhoneNumber())
                .email(user.getEmail())
                .build();
    }
}