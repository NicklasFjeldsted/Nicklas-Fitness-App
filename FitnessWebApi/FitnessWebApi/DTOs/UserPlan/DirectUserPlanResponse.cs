namespace FitnessWebApi.DTOs.UserPlan
{
	public class DirectUserPlanResponse
	{
		public int UserPlanID { get; set; }

		public double StartWeight { get; set; }

		public DateTime StartDate { get; set; }

		public double WeightGoal { get; set; }

		public double WeeklyGoal { get; set; }

		public List<StaticPlanProgressResponse> PlanProgress { get; set; } = new List<StaticPlanProgressResponse>();

		public StaticActivityLevelResponse ActivityLevel { get; set; }
	}
}
