namespace FitnessWebApi.DTOs.UserPlan
{
	public class StaticUserPlanResponse
	{
		public int UserPlanID { get; set; }

		public double StartWeight { get; set; }

		public double CurrentWeight { get; set; }

		public double WeightGoal { get; set; }

		public int ActivityLevelID { get; set; }
	}
}
