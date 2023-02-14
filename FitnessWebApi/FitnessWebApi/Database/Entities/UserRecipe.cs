namespace FitnessWebApi.Database.Entities
{
	public class UserRecipe
	{
		[Key]
		public int RecipeID { get; set; }

		public int UserID { get; set; }
		public User User { get; set; }

		[Column(TypeName = "nvarchar(128)")]
		public string RecipeName { get; set; }

		public double PortionAmount { get; set; }

		[Column(TypeName = "datetime2")]
		public DateTime TotalTime { get; set; }

		[Column(TypeName = "nvarchar(512)")]
		public string Instructions { get; set; }

		public ICollection<SizedProduct> SizedProducts { get; set; }
	}
}
