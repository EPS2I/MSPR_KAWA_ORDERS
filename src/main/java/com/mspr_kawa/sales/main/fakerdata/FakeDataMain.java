//package com.mspr_kawa.orders.main.fakerdata;
//
//import com.github.javafaker.Faker;
//import com.mspr_kawa.db.main.model.*;
//import com.mspr_kawa.orders.main.model.Sale;
//import com.mspr_kawa.orders.main.repository.SaleRepository;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.util.HashSet;
//import java.util.Set;
//
//@Component
//public class FakeDataMain {
//
//    private static final Logger logger = LoggerFactory.getLogger(FakeDataMain.class);
//
//    private final SaleRepository saleRepository;
//    private final Faker faker;
//
//    @Autowired
//    public FakeDataMain(Faker faker, SaleRepository saleRepository) {
//        this.faker = faker;
//        this.saleRepository = saleRepository;
//        logger.info("Starting database initialization with fake data");
//        this.initDB();
//    }
//
//    private void initDB() {
//        logger.info("Initializing database with fake data");
//        for (int i = 0; i < 10; i++) {
//            Sale sale = this.generateFakeOrder(customer);
//            logger.info("Generated sale for customer: {}", sale);
//            sale.setProducts(products);
//            this.saleRepository.save(sale);
//            logger.info("Saved sale with products: {}", sale);
//        }
//    }
//
//    private Customer generateFakeCustomer() {
//        Customer customer = new Customer();
//        customer.setFirstName(faker.name().firstName());
//        customer.setLastName(faker.name().lastName());
//        customer.setUsername(faker.name().username());
//        customer.setName(customer.getFirstName() + " " + customer.getLastName());
//
//        Adress address = new Adress();
//        address.setPostalCode(faker.address().zipCode());
//        address.setCity(faker.address().city());
//        customer.setAdress(address);
//
//        Company company = new Company();
//        company.setCompanyName(faker.company().name());
//        customer.setCompany(company);
//
//        ProfileCustomer profile = new ProfileCustomer();
//        profile.setFirstName(faker.name().firstName());
//        profile.setLastName(faker.name().lastName());
//        customer.setProfile(profile);
//
//        return customer;
//    }
//
//    private Product generateFakeProduct() {
//
//        Details details = new Details();
//        details.setDescription(faker.lorem().characters(50, 200));
//        details.setColor(faker.commerce().color());
//        details.setPrice((float) faker.number().randomDouble(2, 0, 1000));
//
//        Product product = new Product();
//        product.setName(faker.commerce().productName());
//        product.setStock(faker.random().nextInt(10, 1000));
//        product.setDetails(details);
//        return product;
//    }
//
//    private Sale generateFakeOrder(Customer customer) {
//        Sale sale = new Sale();
//        sale.setCustomer(customer);
//        return sale;
//    }
//}
