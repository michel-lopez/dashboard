package com.tealorange.dashboard.tmp;

import java.util.ArrayList;
import java.util.List;

import com.github.javafaker.Faker;
import com.tealorange.dashboard.model.Dashboard;
import com.tealorange.dashboard.model.Query;
import com.tealorange.dashboard.model.configuration.DashboardColumn;
import com.tealorange.dashboard.model.configuration.DashboardConfiguration;
import com.tealorange.dashboard.restrepository.DashboardRepository;
import com.tealorange.dashboard.tmp.model.Address;
import com.tealorange.dashboard.tmp.model.Order;
import com.tealorange.dashboard.tmp.model.Product;
import com.tealorange.dashboard.tmp.restrepository.OrderRepository;
import com.tealorange.dashboard.tmp.restrepository.ProductRepository;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class TestDataApplicationRunner implements ApplicationRunner {

    private DashboardRepository dashboardRepository;
    private OrderRepository orderRepository;
    private ProductRepository productRepository;

    public TestDataApplicationRunner(DashboardRepository aDashboardRepository, OrderRepository aOrderRepository,
            ProductRepository aProductRepository) {
        this.dashboardRepository = aDashboardRepository;
        this.orderRepository = aOrderRepository;
        this.productRepository = aProductRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        createDashboards(dashboardRepository);
        createOrderTestData(orderRepository, productRepository);
    }
    

	private void createDashboards(DashboardRepository aDashboardRepository) {
		DashboardColumn idColumn = new DashboardColumn("id", "o.order_id");
		DashboardColumn nameColumn = new DashboardColumn("name", "o.name");
		
		DashboardConfiguration configuration = new DashboardConfiguration();
		configuration.getColumns().add(idColumn);
		configuration.getColumns().add(nameColumn);
		configuration.setBaseSqlQuery("SELECT * FROM order o");

		Dashboard dashboard = new Dashboard("Test dashboard");
		dashboard.setConfiguration(configuration);

		Query query = new Query("To Belgium");
		query.setSql("WHERE o.to_country = 'BE'");
		dashboard.getQueries().add(query);

		query = new Query("To France");
		query.setSql("WHERE o.to_country = 'FR'");
		dashboard.getQueries().add(query);

		aDashboardRepository.save(dashboard);

		aDashboardRepository.save(new Dashboard("Test dashboard 2"));
    }
    

	private void createOrderTestData(OrderRepository aOrderRepository, ProductRepository aProductRepository) {
		Faker faker = new Faker();

		List<Product> products = new ArrayList<>();
		for (int i = 0; i < 15; i++) {
			products.add(new Product(faker.beer().hop()));
			products.add(new Product(faker.beer().malt()));
		}
		aProductRepository.saveAll(products);

		for (int i = 0; i < 500; i++) {

			com.github.javafaker.Address fakerAddress = faker.address();
			
			Address address = new Address();
			int index = i % 3;
			String person = switch (index) {
				case 0 -> faker.gameOfThrones().character();
				case 1 -> faker.harryPotter().character();
				case 2 -> faker.howIMetYourMother().character();
				default -> throw new IllegalArgumentException("Unexpected value: " + index);
			};
			address.setClient(person);

			address.setStreetName(fakerAddress.streetName());
			address.setStreetNumber(fakerAddress.buildingNumber());
			address.setZipCode(fakerAddress.zipCode());
			address.setCountryCode(fakerAddress.countryCode());
			
			Order order = new Order();
			order.setAddress(address);
			order.setProduct(products.get(i % products.size()));

			aOrderRepository.save(order);
		}
	}
}