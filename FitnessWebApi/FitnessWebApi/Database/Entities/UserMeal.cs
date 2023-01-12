namespace FitnessWebApi.Database.Entities
{
	public class UserMeal
	{
		[Key]
		public int MealID { get; set; }

		public int UserID { get; set; }
		public User User { get; set; }

		[Column(TypeName = "nvarchar(64)")]
		public string MealName { get; set; }

		public double PortionAmount { get; set; }

		[Column(TypeName = "datetime2")]
		public DateTime TotalTime { get; set; }

		[Column(TypeName = "nvarchar(512)")]
		public string Instructions { get; set; }

		public int MealTimeID { get; set; }
		public MealTime MealTime { get; set; }

		public List<SizedProduct> SizedProducts { get; set; }
	}
}
