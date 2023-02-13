namespace FitnessWebApi.DTOs.ProgressMeal
{
	public class StaticProgressMealResponse
	{
		public int ProgressMealID { get; set; }

		public int MealTimeID { get; set; }

		public List<DirectSizedProductResponse> SizedProducts { get; set; } = new List<DirectSizedProductResponse>();
	}
}
