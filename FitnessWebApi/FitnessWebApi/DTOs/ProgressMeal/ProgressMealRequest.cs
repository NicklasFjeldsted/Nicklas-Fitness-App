namespace FitnessWebApi.DTOs.ProgressMeal
{
	public class ProgressMealRequest
	{
		public int MealTimeID { get; set; }
	
		public List<SizedProductRequest> SizedProducts { get; set; }
	}
}
