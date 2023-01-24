namespace FitnessWebApi.DTOs.UserRecipe
{
	public class UserRecipeRequest
	{
		public int RecipeID { get; set; }

		public int UserID { get; set; }

		public string RecipeName { get; set; }

		public double PortionAmount { get; set; }

		public DateTime TotalTime { get; set; }

		public string Instructions { get; set; }

		public List<SizedProductRequest> SizedProducts { get; set; } = new List<SizedProductRequest>();
	}
}
