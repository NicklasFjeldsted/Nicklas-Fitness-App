namespace FitnessWebApi.DTOs.ProgressMeal
{
	public class DirectProgressMealResponse
	{
		public int ProgressMealID { get; set; }

		public StaticMealTimeResponse MealTime { get; set; }

		public List<StaticSizedProductResponse> SizedProducts { get; set; } = new List<StaticSizedProductResponse>();

		public StaticPlanProgressResponse PlanProgress { get; set; }
	}
}
