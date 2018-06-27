/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.carrental.validator;

import com.mycompany.carrental.entity.Rents;
import com.mycompany.carrental.service.RentsService;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 *
 * @author Bolek
 */
@Component
public class RentalDateValidator implements Validator {

    @Autowired
    public RentsService rentsService;

    @Override
    public boolean supports(Class<?> type) {
        return Rents.class.isAssignableFrom(type);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Rents rent = (Rents) o;
        // pobranie wprowadzonych dat
        Date rentalDate = rent.getDateOfRental();
        Date returnDate = rent.getDateOfReturn();
        System.out.println(" - - - info about:");

        //pobranie wypozyczen
        List<Rents> allRents = rentsService.findActiveVehicleRents(rent.getVehicles());
        System.out.println(allRents);
        // ustalanie obecnej daty i wyzerowanie czasu
        // w celu porowniania z data pobrana
        Calendar now = Calendar.getInstance();
        now.set(Calendar.HOUR_OF_DAY, 0);
        now.set(Calendar.MINUTE, 0);
        now.set(Calendar.SECOND, 0);
        now.set(Calendar.MILLISECOND, 0);
        try {
            // sprawdzenie czy data nie jest z przeszlosci 
            if (rentalDate.before(now.getTime())) {
                errors.rejectValue("dateOfRental", "validator.RentalDateValidator.rentalDate");
            }
            // czy data zwortu nie jest wczesniejsza
            if (returnDate.before(rentalDate)) {
                errors.rejectValue("dateOfReturn", "validator.RentalDateValidator.returnDate");
            }
            // czy podane daty nie koliduja z innymi wypozyczeniami
            for (Rents otherRent : allRents) {
                if((rentalDate.before(otherRent.getDateOfReturn()) && rentalDate.after(otherRent.getDateOfRental())) 
                        || (returnDate.after(otherRent.getDateOfRental()) && returnDate.before(otherRent.getDateOfReturn())) ){
                    errors.rejectValue("dateOfRental", "validator.UserRegistrationValidator.wrongDates");
                }
            }

        } catch (NullPointerException exeption) {
            errors.rejectValue("dateOfReturn", "validator.RentalDateValidator.noDates");
        }

    }

}
