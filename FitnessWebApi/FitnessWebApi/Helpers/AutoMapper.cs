namespace FitnessWebApi.Helpers
{
	public class AutoMapper : Profile
	{
		public AutoMapper()
		{
			// CreateMap<, DirectResponse>();
			// CreateMap<, StaticResponse>();
			// CreateMap<Request, >();
			CreateMap<ActivityLevel, StaticActivityLevelResponse>();

			CreateMap<Gender, StaticGenderResponse>();

			CreateMap<MealTime, StaticMealTimeResponse>();

			CreateMap<Product, StaticProductResponse > ();
			CreateMap<ProductRequest, Product>();

			CreateMap<SizedProduct, DirectSizedProductResponse>();
			CreateMap<SizedProduct, StaticSizedProductResponse>();
			CreateMap<SizedProductRequest, SizedProduct>();

			CreateMap<User, DirectUserResponse>();
			CreateMap<User, StaticUserResponse>();
			CreateMap<UserRequest, User>();

			CreateMap<UserPlan, DirectUserPlanResponse>();
			CreateMap<UserPlan, StaticUserPlanResponse>();
			CreateMap<UserPlanRequest, UserPlan>();
		}
	}
}
