namespace FitnessWebApi.DTOs.ProgressMeal
{
	public class DirectProgressMealResponse
	{
		public int ProgressMealID { get; set; }

		public StaticMealTimeResponse MealTime { get; set; }

		public List<StaticSizedProductResponse> ProgressMealRroducts { get; set; }

		public StaticPlanProgressResponse PlanProgress { get; set; }
	}
}
