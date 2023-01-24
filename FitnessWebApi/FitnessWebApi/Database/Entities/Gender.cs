namespace FitnessWebApi.Database.Entities
{
	public class Gender
	{
		[Key]
		public int GenderID { get; set; }

		[Column(TypeName = "nvarchar(48)")]
		public string GenderName { get; set; }
	}
}
