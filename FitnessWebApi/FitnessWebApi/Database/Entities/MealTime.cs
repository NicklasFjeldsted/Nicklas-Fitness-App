namespace FitnessWebApi.Database.Entities
{
	public class MealTime
	{
		[Key]
		public int MealTimeID { get; set; }

		[Column(TypeName = "nvarchar(48)")]
		public string MealTimeName { get; set; }
	}
}
