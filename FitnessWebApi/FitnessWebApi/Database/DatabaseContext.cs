﻿using FitnessWebApi.Database.Entities;

namespace FitnessWebApi.Database
{
	public partial class DatabaseContext : DbContext
	{
		public DatabaseContext(DbContextOptions<DatabaseContext> options) : base(options) { }

		#region Generated Properties
		public virtual DbSet<Gender> Gender { get; set; }
		public virtual DbSet<User> User { get; set; }
		#endregion

		protected override void OnModelCreating(ModelBuilder modelBuilder)
		{
			#region Generated Configurations
			modelBuilder.Entity<User>(e =>
			{
				e.HasIndex(e => e.Email).IsUnique();
				e.Property(e => e.Created_At).HasDefaultValueSql("getdate()");
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
