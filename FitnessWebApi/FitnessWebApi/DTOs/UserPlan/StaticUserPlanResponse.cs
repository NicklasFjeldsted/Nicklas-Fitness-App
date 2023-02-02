namespace FitnessWebApi.DTOs.UserPlan
{
	public class StaticUserPlanResponse
	{
		public int UserPlanID { get; set; }

		public double StartWeight { get; set; }

		public DateTime StartDate { get; set; }

		public double WeightGoal { get; set; }

		public double WeeklyGoal { get; set; }

		public int ActivityLevelID { get; set; }
	}
}
