namespace FitnessWebApi.Database.Entities
{
	public class ActivityLevel
	{
		[Key]
		public int ActivityLevelID { get; set; }

		[Column(TypeName = "nvarchar(64)")]
		public string ActivityLevelName { get; set; }

		public short DailyIntake { get; set; }
	}
}
