namespace FitnessWebApi.DTOs.UserPlan
{
	public class DirectUserPlanResponse
	{
		public int UserPlanID { get; set; }

		public double StartWeight { get; set; }

		public DateTime StartDate { get; set; }

		public double WeightGoal { get; set; }

		public double WeeklyGoal { get; set; }

		public List<DirectPlanProgressResponse> PlanProgress { get; set; } = new List<DirectPlanProgressResponse>();

		public StaticActivityLevelResponse ActivityLevel { get; set; }
	}
}
