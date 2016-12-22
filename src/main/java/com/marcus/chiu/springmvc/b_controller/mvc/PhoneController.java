package com.marcus.chiu.springmvc.b_controller.mvc;

import com.marcus.chiu.springmvc.c_service.service.PhoneService;
import com.marcus.chiu.springmvc.e_entity.entity.Employee;
import com.marcus.chiu.springmvc.e_entity.entity.Phone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;
import java.util.Locale;

/**
 * Created by marcus.chiu on 10/26/16.
 */
@Controller
@RequestMapping("/phone")
public class PhoneController {

    @Autowired
    PhoneService phoneService;

    /**
     * @param modelMap
     * @return String
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String listPhones(ModelMap modelMap) {
        List<Phone> phones = phoneService.findAllPhones();
        System.out.println("size of phone list: " + phones.size());
        modelMap.addAttribute("phones", phones);
        return "allphones";
    }

    /**
     * This method will provide the medium to add a new employee
     * handles GET request for the new employee registration page
     * @param model
     * @return
     */
    @RequestMapping(value = { "/new" }, method = RequestMethod.GET)
    public String newEmployee(ModelMap model) {
        Phone phone = new Phone();
        model.addAttribute("phone", phone);
        //model.addAttribute("employee", phone.getEmployee());
        model.addAttribute("edit", false);
        return "phone_registration";
    }

    /**
     * This method will provide the medium to update existing employee
     * Takes you to the registration page with employee details to fill in
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = { "/edit-{id}-phone" }, method = RequestMethod.GET)
    public String editPhone(@PathVariable Integer id, ModelMap model) {
        Phone phone = phoneService.findById(id);
        model.addAttribute("phone", phone);
        model.addAttribute("edit", true);
        return "phone_registration";
    }

    /**
     * This method will be called on form submission, handling POST request for
     * updating employee in database. It also validates the user input
     * @param phone
     * @param result
     * @param model
     * @param id
     * @return
     */
    @RequestMapping(value = { "/edit-{id}-phone" }, method = RequestMethod.POST)
    public String updatePhone(@Valid Phone phone, BindingResult result, ModelMap model, @PathVariable int id) {
        if (result.hasErrors()) {
            return "phone_registration";
        }

        phone.setId(id);

        phoneService.updatePhone(phone);

        model.addAttribute("success", "Phone with number: " + phone.getNumber()  + " updated successfully");
        return "phone_success";
    }
}
