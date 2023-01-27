namespace FitnessWebApi.DTOs.ProgressMeal
{
	public class ProgressMealRequest
	{
		public int ProgressMealID { get; set; }

		public int MealTimeID { get; set; }
	
		public List<StaticSizedProductResponse> SizedProducts { get; set; }
	}
}
