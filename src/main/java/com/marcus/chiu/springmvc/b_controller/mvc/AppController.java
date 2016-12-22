package com.marcus.chiu.springmvc.b_controller.mvc;

import com.marcus.chiu.springmvc.a_bean.BeanExample;
import com.marcus.chiu.springmvc.e_entity.entity.Employee;
import com.marcus.chiu.springmvc.c_service.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
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
 * Created by marcus.chiu on 10/17/16.
 * @Controller - indicate this class is a controller handling the
 * requests with pattern mapped by @RequestMapping
 */
@Controller
@RequestMapping("/")
public class AppController {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    MessageSource messageSource;

    /**
     * This method will list all existing employees
     * handles both the default URL '/' and '/list'
     * handles as handler for initial page of application
     * This method gets called multiple times during application startup
     * afterwards it only gets called once for each request
     * @param modelMap
     * @return String - in this case Spring will find allemployees.jsp
     */
    @RequestMapping(value = {"/", "/list" }, method = RequestMethod.GET)
    public String listEmployees(ModelMap modelMap) {
        List<Employee> employees = employeeService.findAllEmployees();
        modelMap.addAttribute("employees", employees);
        return "allemployees";
    }

    /**
     * This method will provide the medium to add a new employee
     * handles GET request for the new employee registration page
     * @param model
     * @return
     */
    @RequestMapping(value = { "/new" }, method = RequestMethod.GET)
    public String newEmployee(ModelMap model) {
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        model.addAttribute("edit", false);
        return "registration";
    }

    /**
     * This method will be called on form submission, handling POST request
     * for saving employee in database. It also validates the user input
     * @param employee - @Valid - asks Spring to validate the associated object(Employee)
     * @param bindingResult - contains outcome/errors of validation. BindingResult must
     *                      come right after the validated object else Spring won't be able
     *                      to validate.
     * @param modelMap
     * @return
     */
    @RequestMapping(value = { "/new" }, method = RequestMethod.POST)
    public String saveEmployee(@Valid Employee employee, BindingResult bindingResult, ModelMap modelMap) {
        System.out.println("attempting to save employee");

        if (bindingResult.hasErrors()) {
            System.out.println("bindingResult has errors");
            return "registration";
        }

        /*
         * Preferred way to achieve uniqueness of field [ssn] should be implementing custom @Unique annotation
         * and applying it on field [ssn] of Model class [Employee].
         *
         * Below mentioned peace of code [if block] is to demonstrate that you can fill custom errors outside the validation
         * framework as well while still using internationalized messages.
         */
        if(! employeeService.isEmployeeSsnUnique(employee.getId(), employee.getSsn())){
            System.out.println("ssn is not unique");
            FieldError ssnError =new FieldError(
                    "employee",
                    "ssn",
                    messageSource.getMessage(
                            "non.unique.ssn",
                            new String[]{employee.getSsn()},
                            Locale.getDefault()
                    )
            );
            bindingResult.addError(ssnError);
            return "registration";
        }

        employeeService.saveEmployee(employee);

        modelMap.addAttribute("success", "Employee " + employee.getName() + " registered successfully");
        return "success";
    }

    /**
     * This method will provide the medium to update existing employee
     * Takes you to the registration page with employee details to fill in
     * @param ssn
     * @param model
     * @return
     */
    @RequestMapping(value = { "/edit-{ssn}-employee" }, method = RequestMethod.GET)
    public String editEmployee(@PathVariable String ssn, ModelMap model) {
        System.out.println("/edit-{ssn}-employee GET");
        Employee employee = employeeService.findEmployeeBySsn(ssn);
        model.addAttribute("employee", employee);
        model.addAttribute("edit", true);
        return "registration";
    }

    /**
     * This method will be called on form submission, handling POST request for
     * updating employee in database. It also validates the user input
     * @param employee
     * @param result
     * @param model
     * @param ssn
     * @return
     */
    @RequestMapping(value = { "/edit-{ssn}-employee" }, method = RequestMethod.POST)
    public String updateEmployee(@Valid Employee employee, BindingResult result, ModelMap model, @PathVariable String ssn) {
        System.out.println("/edit-{ssn}-employee POST");
        if (result.hasErrors()) {
            return "registration";
        }

        if(! employeeService.isEmployeeSsnUnique(employee.getId(), employee.getSsn())){
            FieldError ssnError =new FieldError(
                    "employee",
                    "ssn",
                    messageSource.getMessage(
                            "non.unique.ssn",
                            new String[]{employee.getSsn()},
                            Locale.getDefault()
                    )
            );
            result.addError(ssnError);
            return "registration";
        }

        employeeService.updateEmployee(employee);

        model.addAttribute("success", "Employee " + employee.getName()  + " updated successfully");
        return "success";
    }

    /**
     * This method will delete an employee by its SSN value
     * @param ssn - @PathVariable indicates this parameter will be bound to variable
     *            in URI template
     * @return String
     */
    @RequestMapping(value = { "/delete-{ssn}-employee" }, method = RequestMethod.GET)
    public String deleteEmployee(@PathVariable String ssn) {
        employeeService.deleteEmployeeBySsn(ssn);
        return "redirect:/list";
    }
}
