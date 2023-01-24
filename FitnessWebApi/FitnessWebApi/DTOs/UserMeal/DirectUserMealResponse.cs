namespace FitnessWebApi.DTOs.UserMeal
{
	public class DirectUserMealResponse
	{
		public int MealID { get; set; }

		public StaticUserResponse User { get; set; }

		public string MealName { get; set; }

		public double PortionAmount { get; set; }

		public DateTime TotalTime { get; set; }

		public string Instructions { get; set; }

		public StaticMealTimeResponse MealTime { get; set; }

		public List<StaticSizedProductResponse> SizedProducts { get; set; }
	}
}
