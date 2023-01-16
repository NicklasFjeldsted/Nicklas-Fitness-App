namespace FitnessWebApi._Services
{
	public interface IProductService
	{
		public Task<List<StaticProductResponse>> GetAll();
		public Task<StaticProductResponse> GetByBarCode(string barCode);
		public Task<StaticProductResponse> Create(ProductRequest request);
		public Task<StaticProductResponse> Update(string barCode, ProductRequest request);
		public Task<StaticProductResponse> Delete(string barCode);
	}

	public class ProductService : IProductService
	{
		private readonly IProductRepository _repository;
		private readonly IMapper m_mapper;

		public ProductService(IProductRepository repository, IMapper mapper)
		{
			_repository = repository;
			m_mapper = mapper;
		}

		public async Task<List<StaticProductResponse>> GetAll()
		{
			List<Product> products = await _repository.GetAll();
			if (products != null)
			{
				return products.Select(product => m_mapper.Map<Product, StaticProductResponse>(product)).ToList();
			}

			return null;
		}

		public async Task<StaticProductResponse> GetByBarCode(string barCode)
		{
			Product product = await _repository.GetByBarCode(barCode);
			if (product != null)
			{
				return m_mapper.Map<StaticProductResponse>(product);
			}

			return null;
		}

		public async Task<StaticProductResponse> Create(ProductRequest request)
		{
			Product product = await _repository.Create(m_mapper.Map<Product>(request));
			if (product != null)
			{
				return m_mapper.Map<StaticProductResponse>(product);
			}

			return null;
		}

		public async Task<StaticProductResponse> Update(string barCode, ProductRequest request)
		{
			Product product = await _repository.Update(barCode, m_mapper.Map<Product>(request));
			if (product != null)
			{
				return m_mapper.Map<StaticProductResponse>(product);
			}

			return null;
		}

		public async Task<StaticProductResponse> Delete(string barCode)
		{
			Product product = await _repository.GetByBarCode(barCode);
			if (product != null)
			{
				return m_mapper.Map<StaticProductResponse>(await _repository.Delete(product));
			}

			return null;
		}
	}
}
