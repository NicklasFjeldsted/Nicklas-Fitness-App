namespace FitnessWebApi.DTOs.ProgressMeal
{
	public class DirectProgressMealResponse
	{
		public int ProgressMealID { get; set; }

		public StaticMealTimeResponse MealTime { get; set; }

		public List<DirectSizedProductResponse> SizedProducts { get; set; } = new List<DirectSizedProductResponse>();

		public StaticPlanProgressResponse PlanProgress { get; set; }
	}
}
