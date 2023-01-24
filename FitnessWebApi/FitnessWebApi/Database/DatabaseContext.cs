using FitnessWebApi.Database.Entities;

namespace FitnessWebApi.Database
{
	public class DatabaseContext : DbContext
	{
		public DatabaseContext(DbContextOptions<DatabaseContext> options) : base(options) { }

		#region Generated Properties
		public virtual DbSet<ActivityLevel> ActivityLevel { get; set; }
		public virtual DbSet<Gender> Gender { get; set; }
		public virtual DbSet<MealTime> MealTime { get; set; }
		public virtual DbSet<Product> Product { get; set; }
		public virtual DbSet<SizedProduct> SizedProduct { get; set; }
		public virtual DbSet<User> User { get; set; }
		public virtual DbSet<UserMeal> UserMeal { get; set; }
		public virtual DbSet<UserPlan> UserPlan { get; set; }
		public virtual DbSet<UserRecipe> UserRecipe { get; set; }
		#endregion

		protected override void OnModelCreating(ModelBuilder modelBuilder)
		{
			#region Generated Configurations
			modelBuilder.Entity<User>(e =>
			{
				e.HasIndex(e => e.Email).IsUnique();
				e.Property(e => e.Created_At).HasDefaultValueSql("getdate()");
			});

			modelBuilder.Entity<Product>(e =>
			{
				e.HasIndex(e => e.ProductCode).IsUnique();
			});
			#endregion

			#region Generated Data
			modelBuilder.Entity<Gender>().HasData(
				new Gender
				{
					GenderID = 1,
					GenderName = "Male"
				},
				new Gender
				{
					GenderID = 2,
					GenderName = "Female"
				});
			#endregion
		}
	}
}
