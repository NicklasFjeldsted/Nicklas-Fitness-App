namespace FitnessWebApi.DTOs.UserRecipe
{
	public class DirectUserRecipeResponse
	{
		public int RecipeID { get; set; }

		public StaticUserResponse User { get; set; }

		public string RecipeName { get; set; }

		public double PortionAmount { get; set; }

		public DateTime TotalTime { get; set; }

		public string Instructions { get; set; }

		public List<StaticSizedProductResponse> SizedProducts { get; set; }
	}
}
