using AnyCompanyEntertainmentDotNet.Models;
using Microsoft.EntityFrameworkCore;

namespace AnyCompanyEntertainmentDotNet
{
    public class AppDbContext : DbContext
    {
        public DbSet<Customer> Customers { get; set; }
        public DbSet<Order> Orders { get; set; }
        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            modelBuilder.Entity<Order>()
                       .Property(c => c.Id)
                       .ValueGeneratedNever(); 
        }
        protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
        {
            optionsBuilder.UseSqlServer("Data Source=GODFREYMASHA;Initial Catalog=AnyCompanyEntertainmentDB;Integrated Security=True;TrustServerCertificate=True;");
        }
    }
}
