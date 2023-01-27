namespace FitnessWebApi.DTOs.PlanProgress
{
	public class PlanProgressRequest
	{
		public List<StaticProgressMealResponse> ProgressMeals { get; set; }

		public DateTime ProgressDate { get; set; }

		public double CurrentWeight { get; set; }

		public int UserPlanID { get; set; }
	}
}
