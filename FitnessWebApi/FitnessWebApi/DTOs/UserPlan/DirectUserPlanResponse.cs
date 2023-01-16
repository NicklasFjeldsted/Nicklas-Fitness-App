namespace FitnessWebApi.DTOs.UserPlan
{
	public class DirectUserPlanResponse
	{
		public int UserPlanID { get; set; }

		public double StartWeight { get; set; }

		public double CurrentWeight { get; set; }

		public double WeightGoal { get; set; }

		public StaticActivityLevelResponse ActivityLevel { get; set; }
	}
}
