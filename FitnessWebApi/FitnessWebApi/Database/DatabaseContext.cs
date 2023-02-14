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
		public virtual DbSet<PlanProgress> PlanProgress { get; set; }
		public virtual DbSet<ProgressMeal> ProgressMeal { get; set; }
		#endregion

		protected override void OnModelCreating(ModelBuilder modelBuilder)
		{
			#region Generated Configurations
			modelBuilder.Entity<User>(e =>
			{
				e.HasIndex(x => x.Email).IsUnique();
				e.Property(x => x.Created_At).HasDefaultValueSql("getdate()");
			});

			modelBuilder.Entity<UserPlan>(e =>
			{
				e.HasOne(x => x.User).WithOne(x => x.UserPlan).HasForeignKey<User>(x => x.UserPlanID);
			});

			modelBuilder.Entity<Product>(e =>
			{
				e.HasIndex(x => x.ProductCode).IsUnique();
			});

			modelBuilder.Entity<PlanProgress>(e =>
			{
				e.HasOne(e => e.UserPlan).WithMany(x => x.PlanProgress).OnDelete(DeleteBehavior.Restrict);
			});

			modelBuilder.Entity<SizedProduct>(e =>
			{
				e.HasOne(x => x.ProgressMeal).WithMany(y => y.SizedProducts).OnDelete(DeleteBehavior.Restrict);
				e.HasOne(x => x.UserRecipe).WithMany(y => y.SizedProducts).OnDelete(DeleteBehavior.Restrict);
				e.HasOne(x => x.UserMeal).WithMany(y => y.SizedProducts).OnDelete(DeleteBehavior.Restrict);
			});


			modelBuilder.Entity<ProgressMeal>(e =>
			{
				e.HasOne(x => x.PlanProgress).WithMany(x => x.ProgressMeals).HasForeignKey(x => x.ProgressMealID).OnDelete(DeleteBehavior.Cascade);
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

			modelBuilder.Entity<UserPlan>().HasData(
				new UserPlan
				{
					UserPlanID = 1,
					StartWeight = 79,
					StartDate = new DateTime(2023, 1, 31),
					WeightGoal = 85,
					WeeklyGoal = 0.5,
					ActivityLevelID = 5
				});

			modelBuilder.Entity<User>().HasData(
				new User
				{
					UserID = 1,
					Email = "example.com",
					Password = BC.HashPassword("Passw0rd"),
					FirstName = "Nicklas",
					LastName = "Osbeck",
					Height = 181,
					UserPlanID = 1,
					GenderID = 1,
					BirthdayDate = new DateTime(2003, 1, 29),
					Created_At = DateTime.UtcNow,
					Modified_At = DateTime.UtcNow,
					Last_Login = DateTime.UtcNow,
				});

			modelBuilder.Entity<Product>().HasData(
				new Product
				{
					ProductID = 1,
					ProductName = "Makrel i tomat",
					ProductManufacturer = "REMA 1000",
					ProductCode = " 7032069719657",
					EnergyAmount = 160,
					FatAmount = 9.3,
					SaturatedFatAmount = 1.9,
					CarbohydrateAmount = 6.2,
					SugarAmount = 4,
					FiberAmount = 0,
					ProteinAmount = 12,
					SaltAmount = 0.63,
					
				});

			modelBuilder.Entity<PlanProgress>(e =>
			{
				e.HasData(new PlanProgress
				{
					PlanProgressID = 1,
					ProgressDate = new DateTime(2023, 1, 31),
					CurrentWeight = 79,
					UserPlanID = 1
				});
			});


			modelBuilder.Entity<ActivityLevel>().HasData(
				new ActivityLevel
				{
					ActivityLevelID = 1,
					ActivityLevelName = "Female-Sedentary",
					DailyIntake = 1800
				},
				new ActivityLevel
				{
					ActivityLevelID = 2,
					ActivityLevelName = "Female-Moderately",
					DailyIntake = 2000
				},
				new ActivityLevel
				{
					ActivityLevelID = 3,
					ActivityLevelName = "Female-Active",
					DailyIntake = 2400
				},
				new ActivityLevel 
				{
					ActivityLevelID = 4,
					ActivityLevelName = "Male-Sedentary",
					DailyIntake = 2200
				},
				new ActivityLevel
				{
					ActivityLevelID = 5,
					ActivityLevelName = "Male-Moderately",
					DailyIntake = 2800
				},
				new ActivityLevel
				{
					ActivityLevelID = 6,
					ActivityLevelName = "Male-Active",
					DailyIntake = 3200
				});

			modelBuilder.Entity<MealTime>().HasData(
				new MealTime
				{
					MealTimeID = 1,
					MealTimeName = "Breakfast"
				},
				new MealTime
				{
					MealTimeID = 2,
					MealTimeName = "Lunch"
				},
				new MealTime
				{
					MealTimeID = 3,
					MealTimeName = "Dinner"
				},
				new MealTime
				{
					MealTimeID = 4,
					MealTimeName = "Snack"
				});
			#endregion
		}
	}
}
