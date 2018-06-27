/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.carrental.validator;

import com.mycompany.carrental.entity.Users;
import com.mycompany.carrental.service.UsersService;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 *
 * @author Bolek
 */
@Component
public class UserRegistrationValidator implements Validator {
    
    private Pattern pattern;
    private Matcher matcher;

    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final String STRING_PATTERN = "[a-zA-Z\\p{L}]+";
    private static final String NAMES_PATTERN = "[a-zA-Z\\p{L}]{2,45}+";
    private static final String NICKNAME_PATTERN = "[a-zA-Z\\p{L}]{3,50}+";
    private static final String MOBILE_PATTERN = "[0-9]{9}";
    
    private UsersService usersService;

    @Autowired
    public UserRegistrationValidator(UsersService usersService) {
        this.usersService = usersService;
    }
    
    @Override
    public boolean supports(Class<?> type) {
        return Users.class.isAssignableFrom(type);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Users user = (Users) o;
        // pobranie wprowadzonych dat

        if (user.getName() != null || !user.getName().equals("")) {
            pattern = Pattern.compile(NAMES_PATTERN);
            matcher = pattern.matcher(user.getName());
            if (!matcher.matches()) {
                errors.rejectValue("name", "validator.UserRegistrationValidator.name");
            }
        }
        if (user.getSurname()!= null || !user.getSurname().equals("")) {
            pattern = Pattern.compile(NAMES_PATTERN);
            matcher = pattern.matcher(user.getSurname());
            if (!matcher.matches()) {
                errors.rejectValue("surname", "validator.UserRegistrationValidator.surname");
            }
        }
        if (user.getPhone()!= null) {
            pattern = Pattern.compile(MOBILE_PATTERN);
            matcher = pattern.matcher(user.getPhone());
            if (!matcher.matches()) {
                errors.rejectValue("phone", "validator.UserRegistrationValidator.phone");
            }
        }
        
        if (user.getCity()!= null && user.getCity().length() <= 30) {
            pattern = Pattern.compile(STRING_PATTERN);
            matcher = pattern.matcher(user.getCity());
            if (!matcher.matches()) {
                errors.rejectValue("city", "validator.UserRegistrationValidator.city");
            }
        }else{
            errors.rejectValue("city", "validator.UserRegistrationValidator.city2");
        }
        if (user.getStreet()!= null && user.getStreet().length() <= 45) {
            pattern = Pattern.compile(STRING_PATTERN);
            matcher = pattern.matcher(user.getStreet());
            if (!matcher.matches()) {
                errors.rejectValue("street", "validator.UserRegistrationValidator.street");
            }
        }else{
            errors.rejectValue("city", "validator.UserRegistrationValidator.street2");
        }
        if (user.getHouseNumber()== null || user.getHouseNumber().length() > 8 || user.getHouseNumber().equals("")) {
            errors.rejectValue("houseNumber", "validator.UserRegistrationValidator.houseNumber");
        }
        if (user.getPostalCode()== null || user.getPostalCode().length() != 5) {
            errors.rejectValue("postalCode", "validator.UserRegistrationValidator.postalCode");
        }
        // password, nicnkame i email
        // walidacja maila i sprawdzanie czy nie istnieje w bazie
        if (user.getEmail()!= null) {
            pattern = Pattern.compile(EMAIL_PATTERN);
            matcher = pattern.matcher(user.getEmail());
            if (!matcher.matches()) {
                errors.rejectValue("email", "validator.UserRegistrationValidator.email");
            }else{
                Users user2 = usersService.findOneByEmail(user.getEmail());
                if(user2 != null){
                    errors.rejectValue("email", "validator.UserRegistrationValidator.email2");
                }
            }
        }
        // walidacja nicnkname i sprawdzanie czy nie istnieje w bazie
        if (user.getNickname()!= null) {
            pattern = Pattern.compile(NICKNAME_PATTERN);
            matcher = pattern.matcher(user.getNickname());
            if (!matcher.matches()) {
                errors.rejectValue("nickname", "validator.UserRegistrationValidator.nickname");
            }else{
                Users user2 = usersService.findOneByUserNickname(user.getNickname());
                if(user2 != null){
                    errors.rejectValue("email", "validator.UserRegistrationValidator.nickname2");
                }
            }
        }
        // prosta walidacja hasla
        if (user.getPassword() == null || user.getPassword().length() > 255 || user.getPassword().length() < 6 ) {
            errors.rejectValue("password", "validator.UserRegistrationValidator.password");
        }

    }

}
