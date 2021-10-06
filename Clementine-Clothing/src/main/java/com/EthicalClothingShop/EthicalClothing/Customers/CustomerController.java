package com.EthicalClothingShop.EthicalClothing.Customers;

import com.EthicalClothingShop.EthicalClothing.ClothingLine.ClothingItem;
import org.javatuples.Pair;
import org.javatuples.Quartet;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;

@RestController
@RequestMapping(path="api/customers")
public class CustomerController {
    private CustomerService customerService;

    // constructor
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    // methods
    @PostMapping("/create_account")
    public void addNewCustomer(@RequestParam String firstName,
                               @RequestParam String lastName,
                               @RequestParam String email,
                               @RequestParam String mobile,
                               @RequestParam String firstLineBillingAddress,
                               @RequestParam (required = false) String secondLineBillingAddress,
                               @RequestParam String billingCityOrTown,
                               @RequestParam String billingCountyOrState,
                               @RequestParam String billingPostcode,
                               @RequestParam String firstLineDeliveryAddress,
                               @RequestParam (required = false) String secondLineDeliveryAddress,
                               @RequestParam String deliveryCityOrTown,
                               @RequestParam String deliveryCountyOrState,
                               @RequestParam String deliveryPostcode,
                               @RequestParam String password) {
        try {
            customerService.addNewCustomerAccount(firstName, lastName, email,
                    mobile, firstLineBillingAddress, secondLineBillingAddress,
                    billingCityOrTown, billingCountyOrState, billingPostcode, firstLineDeliveryAddress,
                    secondLineDeliveryAddress, deliveryCityOrTown, deliveryCountyOrState, deliveryPostcode, password);
        } catch(Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, e.getMessage(), e);
        }
    }

    @DeleteMapping("/logged_in/delete-account")
    public void customerDeletesAccount(@RequestParam String email,
                                       @RequestParam String password) {

        customerService.deleteCustomer(email, password);
    }

    // customer should already be logged in first to edit account details
    @PutMapping("/logged_in/edit-account")
    public void customerEditsAccountDetails(@RequestParam(required = false) String firstName,
                                            @RequestParam(required = false) String lastName,
                                            @RequestParam(required = false) String mobileNumber) {

        customerService.editCustomer(firstName, lastName, mobileNumber);
    }



    // method for editing basket items is needed @PutMapping will involve increasing and decreasing quantity in basket
    @PutMapping("/logged_in/basket")
    public void customerEditsBasketContents(@RequestParam boolean isIncreasingQuantity,
                                            @RequestParam int clothingId) {
        // only edits quantity of items
        customerService.editItemQuantityInBasket(clothingId, isIncreasingQuantity);
    }

    @GetMapping("/logged_in/account_details")
    public Quartet<String, String, String, String> getCustomerAccountDetails() {
        try {
            Customer customerAccountDetails = customerService.getCustomer();

            Quartet<String, String, String, String> accountDetails = new Quartet<String, String, String, String>(customerAccountDetails.getFirstName(),
                    customerAccountDetails.getLastName(),
                    customerAccountDetails.getEmail(),
                    customerAccountDetails.getMobileNumber());
            return accountDetails;
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED, e.getMessage(), e);
        }
    }


    @DeleteMapping("/{clothingId}")
    public void customerRemovesItemFromBasket(@RequestParam int clothingId) {
        //this will remove item from basket, regardless of quantity
        customerService.removeItemFromBasket(clothingId);
    }


    @PostMapping("/logged_in/add_new_address")
    public void addCustomerAddress(@RequestParam String firstLineAddress,
                                      @RequestParam (required = false) String secondLineAddress,
                                      @RequestParam String cityOrTown,
                                      @RequestParam String countyOrState,
                                      @RequestParam String postcode) {
        customerService.addCustomerAddress(firstLineAddress, secondLineAddress, cityOrTown, countyOrState, postcode);
    }


    @PostMapping("/logged_in")
    public void customerWantsToLogIn(@RequestParam String email,
                                     @RequestParam String password) {
        Customer customerAccount = customerService.findCustomer(email, password);
        customerService.setCustomer(customerAccount);
    }


    @GetMapping("/makePurchase")
    // for when a customer makes a purchase, they get an order ref back
    public int customerWantsOrderReference() {
        int orderId = 0;
        try {
            orderId = customerService.customerMakesPurchase();
        } catch(Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
        return orderId;
    }


    @PostMapping("/{type}-{material}")
    public void customerAddsItemToBasket(@PathVariable String type,
                                         @PathVariable String material,
                                         @RequestParam String subtype,
                                         @RequestParam String color,
                                         @RequestParam String size,
                                         @RequestParam int quantity) {
        try {
            customerService.addItemsToBasket(type, subtype, material, color, size, quantity);
        } catch(Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED, e.getMessage(), e);
        }
    }


    //method for customer viewing their basket using a @GetMapping
    @GetMapping("/logged_in/basket")
    public ArrayList<Pair<ClothingItem, Integer>> getCustomerBasketContent() {
        try {
            return (customerService.getCustomerBasketContent());
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED, e.getMessage(), e);
        }
    }





// Didn't have enough time to make all these methods, ask and we can try to build it

    //    @GetMapping("/logged_in/address_book")
//    public Quintet<String, String, String, String, String> getCustomerAddressBook() {
//        customerService.getCustomerAddressBook();
//    }

//    @PutMapping("/logged_in/address_book/edit_def_deliv_address")
//
//    @PutMapping("/logged_in/address_book/edit_def_bill_address")

}















//    @GetMapping
//    public List<Customer> getAllCustomers (){
//        return customerService.getAllCustomers();
//    }

//    @GetMapping("{customerID}")
//    public Customer getCustomerById(@PathVariable("customerID") int customerID) {
//        return customerService.getCustomer(customerID);
//    }




//    @PutMapping
//    public void updateAccountDetails(@RequestBody Customer customer){
//        customerService.updateAccountDetails(customer);
//    }
//    @DeleteMapping("{customerName}")
//    public void deleteCustomer(@PathVariable("customerName") Customer customerName){
//        customerService.removeCustomer(customerName);
//
//
//    }
//    @PostMapping
//    public void addNewCustomer(@RequestBody Customer customer){
//        customerService.addCustomer(customer);
//    }
