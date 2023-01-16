namespace FitnessWebApi.DTOs.UserMeal
{
	public class UserMealRequest
	{
		public int UserID { get; set; }

		public string MealName { get; set; }

		public double PortionAmount { get; set; }

		public DateTime TotalTime { get; set; }

		public string Instructions { get; set; }

		public int MealTimeID { get; set; }

		public List<SizedProductRequest> SizedProducts = new List<SizedProductRequest>();
	}
}
