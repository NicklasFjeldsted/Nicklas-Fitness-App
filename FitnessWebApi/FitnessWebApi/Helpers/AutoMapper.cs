namespace FitnessWebApi.Helpers
{
	public class AutoMapper : Profile
	{
		public AutoMapper()
		{
			// CreateMap< , DirectResponse>();
			// CreateMap< , StaticResponse>();
			// CreateMap<Request, >();

			CreateMap <Product, StaticProductResponse > ();
			CreateMap<ProductRequest, Product>();
		}
	}
}
