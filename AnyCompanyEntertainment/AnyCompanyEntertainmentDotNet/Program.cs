// See https://aka.ms/new-console-template for more information
using AnyCompanyEntertainmentDotNet;
using AnyCompanyEntertainmentDotNet.Models;
using AnyCompanyEntertainmentDotNet.Services.Interfaces;
using AnyCompanyEntertainmentDotNet.Services.Repositories.Interfaces;
using AnyCompanyEntertainmentDotNet.Services.Repositories;
using AnyCompanyEntertainmentDotNet.Services;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.EntityFrameworkCore;

Console.WriteLine("AnyCompany Entertainment");
Console.WriteLine();

//Dependency Injections
// Configure services
var serviceProvider = new ServiceCollection()
        .AddScoped<AppDbContext>()
        .AddTransient<ICustomerRepository, CustomerRepository>()
        .AddTransient<IOrderRepository, OrderRepository>()
        .AddTransient<IOrderService, OrderService>()
        .BuildServiceProvider();

using (var dbContext = serviceProvider.GetService<AppDbContext>())
{
    // Resolve the service
    var orderService = serviceProvider.GetRequiredService<IOrderService>();
    var customerRepo = serviceProvider.GetRequiredService<ICustomerRepository>();

    Customer? firstCustomer = new Customer { Name = "Godfrey Masha", country = "SA", dateOfBirth = new DateTime(2000, 6, 3) };
    Customer secondCustomer = new Customer { Name = "Stan Doe", country = "SA", dateOfBirth = new DateTime(1990, 5, 1) };

    Order firstOrder = new Order { Id = 1, ProductName = "Product 1", Price = 10.99m };
    Order secondOrder = new Order { Id = 2, ProductName = "Product 2", Price = 19.99m };
    //Add customer with no order
    customerRepo.Add(firstCustomer);

    //Add new customer with order
    if (secondCustomer != null)
    {
        secondCustomer.Orders.Add(new Order() { Id = 3, ProductName = "Product 2", Price = 10.99m });
        customerRepo.Add(secondCustomer);
    }
    //retrieve customer by name
    firstCustomer = customerRepo.GetCustomerByName(firstCustomer.Name);
    //place orders to existing customer
    if (firstCustomer != null)
    {
        orderService.PlaceOrder(firstCustomer.Id, firstOrder);
        orderService.PlaceOrder(firstCustomer.Id, secondOrder);
    }
    else 
    {
        Console.WriteLine($"customer {firstCustomer!.Name} not found");
    }

    Console.WriteLine();

    // Retrieve a customer and their orders
    var customer = orderService.GetCustomerWithOrders(firstCustomer.Id);
    if (customer != null)
    {
        Console.WriteLine($"Customer: {customer.Name}");
        Console.WriteLine("Orders:");
        foreach (var o in customer.Orders)
        {
            Console.WriteLine($"- Product: {o.ProductName}, Price: {o.Price:C}");
        }
    }
    else
    {
        Console.WriteLine("Customer not found.");
    }
    Console.WriteLine();
    // Load all customers with their orders
    var customers = orderService.GetAllCustomersWithOrders();
    foreach (var c in customers)
    {
        Console.WriteLine($"Customer: {c.Name}");
        Console.WriteLine("Orders:");
        foreach (var o in c.Orders)
        {
            Console.WriteLine($"- Product: {o.ProductName}, Price: {o.Price:C}");
        }
    }

}


   